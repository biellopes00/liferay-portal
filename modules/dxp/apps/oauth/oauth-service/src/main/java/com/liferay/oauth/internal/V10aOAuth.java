/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.oauth.internal;

import com.liferay.oauth.configuration.OAuthConfigurationValues;
import com.liferay.oauth.internal.listener.V10aOAuthDebugCacheListener;
import com.liferay.oauth.model.OAuthApplication;
import com.liferay.oauth.model.OAuthUser;
import com.liferay.oauth.service.OAuthApplicationLocalService;
import com.liferay.oauth.service.OAuthUserLocalService;
import com.liferay.oauth.util.DefaultOAuthAccessor;
import com.liferay.oauth.util.DefaultOAuthConsumer;
import com.liferay.oauth.util.DefaultOAuthMessage;
import com.liferay.oauth.util.DefaultOAuthValidator;
import com.liferay.oauth.util.OAuth;
import com.liferay.oauth.util.OAuthAccessor;
import com.liferay.oauth.util.OAuthAccessorConstants;
import com.liferay.oauth.util.OAuthConsumer;
import com.liferay.oauth.util.OAuthMessage;
import com.liferay.oauth.util.OAuthValidator;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.SingleVMPool;
import com.liferay.portal.kernel.cluster.ClusterExecutor;
import com.liferay.portal.kernel.cluster.ClusterRequest;
import com.liferay.portal.kernel.cluster.FutureClusterResponses;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.io.Deserializer;
import com.liferay.portal.kernel.io.Serializer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.oauth.OAuthException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import java.nio.ByteBuffer;

import java.util.List;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.oauth.server.OAuthServlet;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Raymond Augé
 * @author Igor Beslic
 */
@Component(immediate = true, service = OAuth.class)
public class V10aOAuth implements OAuth {

	@Override
	public String addParameters(String url, String... parameters)
		throws OAuthException {

		try {
			return net.oauth.OAuth.addParameters(url, parameters);
		}
		catch (IOException ioe) {
			throw new OAuthException(ioe);
		}
	}

	@Override
	public void authorize(
			OAuthAccessor oAuthAccessor, long userId,
			ServiceContext serviceContext)
		throws PortalException {

		Boolean authorized = (Boolean)oAuthAccessor.getProperty(
			OAuthAccessorConstants.AUTHORIZED);

		if ((authorized != null) && authorized.booleanValue() &&
			Validator.isNotNull(oAuthAccessor.getRequestToken())) {

			throw new OAuthException(net.oauth.OAuth.Problems.TOKEN_EXPIRED);
		}

		oAuthAccessor.setProperty(
			OAuthAccessorConstants.AUTHORIZED, Boolean.TRUE);
		oAuthAccessor.setProperty(OAuthAccessorConstants.USER_ID, userId);

		_put(oAuthAccessor.getRequestToken(), oAuthAccessor);
	}

	@Override
	public void formEncode(
			String token, String tokenSecret, OutputStream outputStream)
		throws OAuthException {

		List<net.oauth.OAuth.Parameter> parameters = net.oauth.OAuth.newList(
			net.oauth.OAuth.OAUTH_TOKEN, token,
			net.oauth.OAuth.OAUTH_TOKEN_SECRET, tokenSecret);

		try {
			net.oauth.OAuth.formEncode(parameters, outputStream);
		}
		catch (IOException ioe) {
			throw new OAuthException(ioe);
		}
	}

	@Override
	public void generateAccessToken(
			OAuthAccessor oAuthAccessor, long userId,
			ServiceContext serviceContext)
		throws PortalException {

		Boolean authorized = (Boolean)oAuthAccessor.getProperty(
			OAuthAccessorConstants.AUTHORIZED);

		if ((authorized != null) && authorized.booleanValue() &&
			Validator.isNotNull(oAuthAccessor.getAccessToken())) {

			throw new OAuthException(net.oauth.OAuth.Problems.TOKEN_EXPIRED);
		}

		OAuthConsumer oAuthConsumer = oAuthAccessor.getOAuthConsumer();

		OAuthApplication oAuthApplication = oAuthConsumer.getOAuthApplication();

		String consumerKey = oAuthApplication.getConsumerKey();

		String token = randomizeToken(consumerKey);

		oAuthAccessor.setAccessToken(token);

		oAuthAccessor.setRequestToken(null);

		String tokenSecret = randomizeToken(consumerKey.concat(token));

		oAuthAccessor.setTokenSecret(tokenSecret);

		OAuthUser oAuthUser = _oAuthUserLocalService.fetchOAuthUser(
			userId, oAuthApplication.getOAuthApplicationId());

		if (oAuthUser == null) {
			_oAuthUserLocalService.addOAuthUser(
				userId, oAuthApplication.getOAuthApplicationId(),
				oAuthAccessor.getAccessToken(), oAuthAccessor.getTokenSecret(),
				serviceContext);
		}
		else {
			if (oAuthApplication.isShareableAccessToken()) {
				oAuthAccessor.setAccessToken(oAuthUser.getAccessToken());
				oAuthAccessor.setTokenSecret(oAuthUser.getAccessSecret());
			}
			else {
				_oAuthUserLocalService.updateOAuthUser(
					userId, oAuthUser.getOAuthApplicationId(),
					oAuthAccessor.getAccessToken(),
					oAuthAccessor.getTokenSecret(), serviceContext);
			}
		}

		_put(token, oAuthAccessor);
	}

	@Override
	public void generateRequestToken(OAuthAccessor oAuthAccessor) {
		OAuthConsumer oAuthConsumer = oAuthAccessor.getOAuthConsumer();

		OAuthApplication oAuthApplication = oAuthConsumer.getOAuthApplication();

		String consumerKey = oAuthApplication.getConsumerKey();

		oAuthAccessor.setAccessToken(null);

		String token = randomizeToken(consumerKey);

		oAuthAccessor.setRequestToken(token);

		String tokenSecret = randomizeToken(consumerKey.concat(token));

		oAuthAccessor.setTokenSecret(tokenSecret);

		_put(token, oAuthAccessor);
	}

	@Override
	public OAuthAccessor getOAuthAccessor(OAuthMessage oAuthMessage)
		throws OAuthException {

		String token = null;

		try {
			token = oAuthMessage.getToken();
		}
		catch (IOException ioe) {
			throw new OAuthException(ioe);
		}

		OAuthAccessor oAuthAccessor = (OAuthAccessor)_portalCache.get(token);

		if (oAuthAccessor == null) {
			throw new OAuthException(net.oauth.OAuth.Problems.TOKEN_EXPIRED);
		}

		return oAuthAccessor;
	}

	@Override
	public OAuthConsumer getOAuthConsumer(OAuthMessage requestMessage)
		throws PortalException {

		String consumerKey = null;

		try {
			consumerKey = requestMessage.getConsumerKey();
		}
		catch (IOException ioe) {
			throw new OAuthException(ioe);
		}

		OAuthApplication oAuthApplication =
			_oAuthApplicationLocalService.fetchOAuthApplication(consumerKey);

		if (oAuthApplication == null) {
			throw new OAuthException(
				net.oauth.OAuth.Problems.CONSUMER_KEY_REFUSED);
		}

		return new DefaultOAuthConsumer(oAuthApplication);
	}

	@Override
	public OAuthMessage getOAuthMessage(HttpServletRequest httpServletRequest) {
		return getOAuthMessage(httpServletRequest, null);
	}

	@Override
	public OAuthMessage getOAuthMessage(
		HttpServletRequest httpServletRequest, String url) {

		return new DefaultOAuthMessage(
			OAuthServlet.getMessage(httpServletRequest, url));
	}

	@Override
	public OAuthMessage getOAuthMessage(PortletRequest portletRequest) {
		return getOAuthMessage(portletRequest, null);
	}

	@Override
	public OAuthMessage getOAuthMessage(
		PortletRequest portletRequest, String url) {

		return getOAuthMessage(
			_portal.getHttpServletRequest(portletRequest), url);
	}

	@Override
	public void handleException(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Exception exception,
			boolean sendBody)
		throws OAuthException {

		if (exception.getCause() != null) {
			exception = (Exception)exception.getCause();
		}

		try {
			OAuthServlet.handleException(
				httpServletResponse, exception,
				OAuthConfigurationValues.OAUTH_REALM, sendBody);
		}
		catch (Exception e) {
			throw new OAuthException(e);
		}
	}

	@Override
	public String randomizeToken(String token) {
		return DigesterUtil.digestHex(
			Digester.MD5, token, PwdGenerator.getPassword());
	}

	@Override
	public void validateOAuthMessage(
			OAuthMessage oAuthMessage, OAuthAccessor accessor)
		throws OAuthException {

		_oAuthValidator.validateOAuthMessage(oAuthMessage, accessor);
	}

	protected static OAuthAccessor deserialize(byte[] bytes) {
		Deserializer deserializer = new Deserializer(ByteBuffer.wrap(bytes));

		try {
			return deserializer.readObject();
		}
		catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}

		return null;
	}

	@Activate
	protected void activate() {
		_oAuthValidator = new DefaultOAuthValidator();

		_portalCache =
			(PortalCache<Serializable, Object>)_singleVMPool.getPortalCache(
				V10aOAuth.class.getName());

		if (_log.isDebugEnabled()) {
			_portalCache.registerPortalCacheListener(
				new V10aOAuthDebugCacheListener<>());
		}
	}

	protected byte[] serialize(OAuthAccessor oAuthAccessor) {
		Serializer serializer = new Serializer();

		serializer.writeObject((DefaultOAuthAccessor)oAuthAccessor);

		ByteBuffer byteBuffer = serializer.toByteBuffer();

		return byteBuffer.array();
	}

	@SuppressWarnings("unused")
	private static void _put(String key, byte[] bytes) {
		OAuthAccessor oAuthAccessor = deserialize(bytes);

		_portalCache.put(key, oAuthAccessor);
	}

	private void _notifyCluster(String key, OAuthAccessor oAuthAccessor)
		throws Exception {

		MethodHandler putMethodHandler = new MethodHandler(
			_putMethodKey, key, serialize(oAuthAccessor));

		ClusterRequest clusterRequest = ClusterRequest.createMulticastRequest(
			putMethodHandler, true);

		FutureClusterResponses futureClusterResponses =
			_clusterExecutor.execute(clusterRequest);

		futureClusterResponses.get();
	}

	private void _put(String key, OAuthAccessor oAuthAccessor) {
		_portalCache.put(key, oAuthAccessor);

		if (_clusterExecutor.isEnabled()) {
			try {
				_notifyCluster(key, oAuthAccessor);

				if (_log.isDebugEnabled()) {
					_log.debug("Notified cluster");
				}
			}
			catch (Exception se) {
				_log.error("Unable to notify cluster", se);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(V10aOAuth.class);

	private static final MethodKey _putMethodKey = new MethodKey(
		V10aOAuth.class, "_put", String.class, byte[].class);

	@Reference
	private ClusterExecutor _clusterExecutor;

	@Reference
	private OAuthApplicationLocalService _oAuthApplicationLocalService;

	@Reference
	private OAuthUserLocalService _oAuthUserLocalService;

	private OAuthValidator _oAuthValidator;

	@Reference
	private Portal _portal;

	private PortalCache<Serializable, Object> _portalCache;

	@Reference
	private SingleVMPool _singleVMPool;

}