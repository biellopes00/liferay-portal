/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.oauth2.provider.service.base;

import com.liferay.oauth2.provider.model.OAuth2Authorization;
import com.liferay.oauth2.provider.service.OAuth2AuthorizationService;
import com.liferay.oauth2.provider.service.OAuth2AuthorizationServiceUtil;
import com.liferay.oauth2.provider.service.persistence.OAuth2AuthorizationFinder;
import com.liferay.oauth2.provider.service.persistence.OAuth2AuthorizationPersistence;
import com.liferay.oauth2.provider.service.persistence.OAuth2ScopeGrantPersistence;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.util.PortalUtil;

import java.lang.reflect.Field;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the o auth2 authorization remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.oauth2.provider.service.impl.OAuth2AuthorizationServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.oauth2.provider.service.impl.OAuth2AuthorizationServiceImpl
 * @generated
 */
public abstract class OAuth2AuthorizationServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, IdentifiableOSGiService, OAuth2AuthorizationService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>OAuth2AuthorizationService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>OAuth2AuthorizationServiceUtil</code>.
	 */
	@Deactivate
	protected void deactivate() {
		_setServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			OAuth2AuthorizationService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		oAuth2AuthorizationService = (OAuth2AuthorizationService)aopProxy;

		_setServiceUtilService(oAuth2AuthorizationService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return OAuth2AuthorizationService.class.getName();
	}

	protected Class<?> getModelClass() {
		return OAuth2Authorization.class;
	}

	protected String getModelClassName() {
		return OAuth2Authorization.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				oAuth2AuthorizationPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	private void _setServiceUtilService(
		OAuth2AuthorizationService oAuth2AuthorizationService) {

		try {
			Field field = OAuth2AuthorizationServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, oAuth2AuthorizationService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected
		com.liferay.oauth2.provider.service.OAuth2AuthorizationLocalService
			oAuth2AuthorizationLocalService;

	protected OAuth2AuthorizationService oAuth2AuthorizationService;

	@Reference
	protected OAuth2AuthorizationPersistence oAuth2AuthorizationPersistence;

	@Reference
	protected OAuth2AuthorizationFinder oAuth2AuthorizationFinder;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected OAuth2ScopeGrantPersistence oAuth2ScopeGrantPersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		OAuth2AuthorizationServiceBaseImpl.class);

}