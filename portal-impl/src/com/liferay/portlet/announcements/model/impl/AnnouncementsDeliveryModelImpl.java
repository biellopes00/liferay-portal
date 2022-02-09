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

package com.liferay.portlet.announcements.model.impl;

import com.liferay.announcements.kernel.model.AnnouncementsDelivery;
import com.liferay.announcements.kernel.model.AnnouncementsDeliveryModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Blob;
import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the AnnouncementsDelivery service. Represents a row in the &quot;AnnouncementsDelivery&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>AnnouncementsDeliveryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AnnouncementsDeliveryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnnouncementsDeliveryImpl
 * @generated
 */
@JSON(strict = true)
public class AnnouncementsDeliveryModelImpl
	extends BaseModelImpl<AnnouncementsDelivery>
	implements AnnouncementsDeliveryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a announcements delivery model instance should use the <code>AnnouncementsDelivery</code> interface instead.
	 */
	public static final String TABLE_NAME = "AnnouncementsDelivery";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"deliveryId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"type_", Types.VARCHAR}, {"email", Types.BOOLEAN},
		{"sms", Types.BOOLEAN}, {"website", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("deliveryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("email", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("sms", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("website", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table AnnouncementsDelivery (mvccVersion LONG default 0 not null,deliveryId LONG not null primary key,companyId LONG,userId LONG,type_ VARCHAR(75) null,email BOOLEAN,sms BOOLEAN,website BOOLEAN)";

	public static final String TABLE_SQL_DROP =
		"drop table AnnouncementsDelivery";

	public static final String ORDER_BY_JPQL =
		" ORDER BY announcementsDelivery.deliveryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY AnnouncementsDelivery.deliveryId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean ENTITY_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean FINDER_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean COLUMN_BITMASK_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TYPE_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long DELIVERYID_COLUMN_BITMASK = 8L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.announcements.kernel.model.AnnouncementsDelivery"));

	public AnnouncementsDeliveryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _deliveryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setDeliveryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _deliveryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return AnnouncementsDelivery.class;
	}

	@Override
	public String getModelClassName() {
		return AnnouncementsDelivery.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<AnnouncementsDelivery, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<AnnouncementsDelivery, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AnnouncementsDelivery, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((AnnouncementsDelivery)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<AnnouncementsDelivery, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<AnnouncementsDelivery, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(AnnouncementsDelivery)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<AnnouncementsDelivery, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<AnnouncementsDelivery, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, AnnouncementsDelivery>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			AnnouncementsDelivery.class.getClassLoader(),
			AnnouncementsDelivery.class, ModelWrapper.class);

		try {
			Constructor<AnnouncementsDelivery> constructor =
				(Constructor<AnnouncementsDelivery>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<AnnouncementsDelivery, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<AnnouncementsDelivery, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<AnnouncementsDelivery, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<AnnouncementsDelivery, Object>>();
		Map<String, BiConsumer<AnnouncementsDelivery, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<AnnouncementsDelivery, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", AnnouncementsDelivery::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<AnnouncementsDelivery, Long>)
				AnnouncementsDelivery::setMvccVersion);
		attributeGetterFunctions.put(
			"deliveryId", AnnouncementsDelivery::getDeliveryId);
		attributeSetterBiConsumers.put(
			"deliveryId",
			(BiConsumer<AnnouncementsDelivery, Long>)
				AnnouncementsDelivery::setDeliveryId);
		attributeGetterFunctions.put(
			"companyId", AnnouncementsDelivery::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<AnnouncementsDelivery, Long>)
				AnnouncementsDelivery::setCompanyId);
		attributeGetterFunctions.put(
			"userId", AnnouncementsDelivery::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<AnnouncementsDelivery, Long>)
				AnnouncementsDelivery::setUserId);
		attributeGetterFunctions.put("type", AnnouncementsDelivery::getType);
		attributeSetterBiConsumers.put(
			"type",
			(BiConsumer<AnnouncementsDelivery, String>)
				AnnouncementsDelivery::setType);
		attributeGetterFunctions.put("email", AnnouncementsDelivery::getEmail);
		attributeSetterBiConsumers.put(
			"email",
			(BiConsumer<AnnouncementsDelivery, Boolean>)
				AnnouncementsDelivery::setEmail);
		attributeGetterFunctions.put("sms", AnnouncementsDelivery::getSms);
		attributeSetterBiConsumers.put(
			"sms",
			(BiConsumer<AnnouncementsDelivery, Boolean>)
				AnnouncementsDelivery::setSms);
		attributeGetterFunctions.put(
			"website", AnnouncementsDelivery::getWebsite);
		attributeSetterBiConsumers.put(
			"website",
			(BiConsumer<AnnouncementsDelivery, Boolean>)
				AnnouncementsDelivery::setWebsite);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_mvccVersion = mvccVersion;
	}

	@JSON
	@Override
	public long getDeliveryId() {
		return _deliveryId;
	}

	@Override
	public void setDeliveryId(long deliveryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_deliveryId = deliveryId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_companyId = companyId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCompanyId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("companyId"));
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalUserId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("userId"));
	}

	@JSON
	@Override
	public String getType() {
		if (_type == null) {
			return "";
		}
		else {
			return _type;
		}
	}

	@Override
	public void setType(String type) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_type = type;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalType() {
		return getColumnOriginalValue("type_");
	}

	@JSON
	@Override
	public boolean getEmail() {
		return _email;
	}

	@JSON
	@Override
	public boolean isEmail() {
		return _email;
	}

	@Override
	public void setEmail(boolean email) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_email = email;
	}

	@JSON
	@Override
	public boolean getSms() {
		return _sms;
	}

	@JSON
	@Override
	public boolean isSms() {
		return _sms;
	}

	@Override
	public void setSms(boolean sms) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_sms = sms;
	}

	@JSON
	@Override
	public boolean getWebsite() {
		return _website;
	}

	@JSON
	@Override
	public boolean isWebsite() {
		return _website;
	}

	@Override
	public void setWebsite(boolean website) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_website = website;
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (!Objects.equals(
					entry.getValue(), getColumnValue(entry.getKey()))) {

				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), AnnouncementsDelivery.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public AnnouncementsDelivery toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, AnnouncementsDelivery>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		AnnouncementsDeliveryImpl announcementsDeliveryImpl =
			new AnnouncementsDeliveryImpl();

		announcementsDeliveryImpl.setMvccVersion(getMvccVersion());
		announcementsDeliveryImpl.setDeliveryId(getDeliveryId());
		announcementsDeliveryImpl.setCompanyId(getCompanyId());
		announcementsDeliveryImpl.setUserId(getUserId());
		announcementsDeliveryImpl.setType(getType());
		announcementsDeliveryImpl.setEmail(isEmail());
		announcementsDeliveryImpl.setSms(isSms());
		announcementsDeliveryImpl.setWebsite(isWebsite());

		announcementsDeliveryImpl.resetOriginalValues();

		return announcementsDeliveryImpl;
	}

	@Override
	public AnnouncementsDelivery cloneWithOriginalValues() {
		AnnouncementsDeliveryImpl announcementsDeliveryImpl =
			new AnnouncementsDeliveryImpl();

		announcementsDeliveryImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		announcementsDeliveryImpl.setDeliveryId(
			this.<Long>getColumnOriginalValue("deliveryId"));
		announcementsDeliveryImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		announcementsDeliveryImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		announcementsDeliveryImpl.setType(
			this.<String>getColumnOriginalValue("type_"));
		announcementsDeliveryImpl.setEmail(
			this.<Boolean>getColumnOriginalValue("email"));
		announcementsDeliveryImpl.setSms(
			this.<Boolean>getColumnOriginalValue("sms"));
		announcementsDeliveryImpl.setWebsite(
			this.<Boolean>getColumnOriginalValue("website"));

		return announcementsDeliveryImpl;
	}

	@Override
	public int compareTo(AnnouncementsDelivery announcementsDelivery) {
		long primaryKey = announcementsDelivery.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AnnouncementsDelivery)) {
			return false;
		}

		AnnouncementsDelivery announcementsDelivery =
			(AnnouncementsDelivery)object;

		long primaryKey = announcementsDelivery.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<AnnouncementsDelivery> toCacheModel() {
		AnnouncementsDeliveryCacheModel announcementsDeliveryCacheModel =
			new AnnouncementsDeliveryCacheModel();

		announcementsDeliveryCacheModel.mvccVersion = getMvccVersion();

		announcementsDeliveryCacheModel.deliveryId = getDeliveryId();

		announcementsDeliveryCacheModel.companyId = getCompanyId();

		announcementsDeliveryCacheModel.userId = getUserId();

		announcementsDeliveryCacheModel.type = getType();

		String type = announcementsDeliveryCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			announcementsDeliveryCacheModel.type = null;
		}

		announcementsDeliveryCacheModel.email = isEmail();

		announcementsDeliveryCacheModel.sms = isSms();

		announcementsDeliveryCacheModel.website = isWebsite();

		return announcementsDeliveryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<AnnouncementsDelivery, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<AnnouncementsDelivery, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AnnouncementsDelivery, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(AnnouncementsDelivery)this);

			if (value == null) {
				sb.append("null");
			}
			else if (value instanceof Blob || value instanceof Date ||
					 value instanceof Map || value instanceof String) {

				sb.append(
					"\"" + StringUtil.replace(value.toString(), "\"", "'") +
						"\"");
			}
			else {
				sb.append(value);
			}

			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<AnnouncementsDelivery, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<AnnouncementsDelivery, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AnnouncementsDelivery, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((AnnouncementsDelivery)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, AnnouncementsDelivery>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _deliveryId;
	private long _companyId;
	private long _userId;
	private String _type;
	private boolean _email;
	private boolean _sms;
	private boolean _website;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<AnnouncementsDelivery, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((AnnouncementsDelivery)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("mvccVersion", _mvccVersion);
		_columnOriginalValues.put("deliveryId", _deliveryId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("type_", _type);
		_columnOriginalValues.put("email", _email);
		_columnOriginalValues.put("sms", _sms);
		_columnOriginalValues.put("website", _website);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("type_", "type");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("deliveryId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("userId", 8L);

		columnBitmasks.put("type_", 16L);

		columnBitmasks.put("email", 32L);

		columnBitmasks.put("sms", 64L);

		columnBitmasks.put("website", 128L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private AnnouncementsDelivery _escapedModel;

}