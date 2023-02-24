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

package com.liferay.portal.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserNotificationDelivery;
import com.liferay.portal.kernel.model.UserNotificationDeliveryModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

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
 * The base model implementation for the UserNotificationDelivery service. Represents a row in the &quot;UserNotificationDelivery&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>UserNotificationDeliveryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UserNotificationDeliveryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserNotificationDeliveryImpl
 * @generated
 */
public class UserNotificationDeliveryModelImpl
	extends BaseModelImpl<UserNotificationDelivery>
	implements UserNotificationDeliveryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a user notification delivery model instance should use the <code>UserNotificationDelivery</code> interface instead.
	 */
	public static final String TABLE_NAME = "UserNotificationDelivery";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT},
		{"userNotificationDeliveryId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"portletId", Types.VARCHAR}, {"classNameId", Types.BIGINT},
		{"notificationType", Types.INTEGER}, {"deliveryType", Types.INTEGER},
		{"deliver", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userNotificationDeliveryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("portletId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("notificationType", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("deliveryType", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("deliver", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table UserNotificationDelivery (mvccVersion LONG default 0 not null,userNotificationDeliveryId LONG not null primary key,companyId LONG,userId LONG,portletId VARCHAR(200) null,classNameId LONG,notificationType INTEGER,deliveryType INTEGER,deliver BOOLEAN)";

	public static final String TABLE_SQL_DROP =
		"drop table UserNotificationDelivery";

	public static final String ORDER_BY_JPQL =
		" ORDER BY userNotificationDelivery.userNotificationDeliveryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY UserNotificationDelivery.userNotificationDeliveryId ASC";

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
	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long DELIVERYTYPE_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long NOTIFICATIONTYPE_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long PORTLETID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERNOTIFICATIONDELIVERYID_COLUMN_BITMASK = 32L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.UserNotificationDelivery"));

	public UserNotificationDeliveryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _userNotificationDeliveryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setUserNotificationDeliveryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userNotificationDeliveryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return UserNotificationDelivery.class;
	}

	@Override
	public String getModelClassName() {
		return UserNotificationDelivery.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<UserNotificationDelivery, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<UserNotificationDelivery, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserNotificationDelivery, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((UserNotificationDelivery)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<UserNotificationDelivery, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<UserNotificationDelivery, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(UserNotificationDelivery)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<UserNotificationDelivery, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<UserNotificationDelivery, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map
			<String, Function<UserNotificationDelivery, Object>>
				_attributeGetterFunctions;

		static {
			Map<String, Function<UserNotificationDelivery, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<UserNotificationDelivery, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", UserNotificationDelivery::getMvccVersion);
			attributeGetterFunctions.put(
				"userNotificationDeliveryId",
				UserNotificationDelivery::getUserNotificationDeliveryId);
			attributeGetterFunctions.put(
				"companyId", UserNotificationDelivery::getCompanyId);
			attributeGetterFunctions.put(
				"userId", UserNotificationDelivery::getUserId);
			attributeGetterFunctions.put(
				"portletId", UserNotificationDelivery::getPortletId);
			attributeGetterFunctions.put(
				"classNameId", UserNotificationDelivery::getClassNameId);
			attributeGetterFunctions.put(
				"notificationType",
				UserNotificationDelivery::getNotificationType);
			attributeGetterFunctions.put(
				"deliveryType", UserNotificationDelivery::getDeliveryType);
			attributeGetterFunctions.put(
				"deliver", UserNotificationDelivery::getDeliver);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<UserNotificationDelivery, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<UserNotificationDelivery, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<UserNotificationDelivery, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<UserNotificationDelivery, Long>)
					UserNotificationDelivery::setMvccVersion);
			attributeSetterBiConsumers.put(
				"userNotificationDeliveryId",
				(BiConsumer<UserNotificationDelivery, Long>)
					UserNotificationDelivery::setUserNotificationDeliveryId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<UserNotificationDelivery, Long>)
					UserNotificationDelivery::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<UserNotificationDelivery, Long>)
					UserNotificationDelivery::setUserId);
			attributeSetterBiConsumers.put(
				"portletId",
				(BiConsumer<UserNotificationDelivery, String>)
					UserNotificationDelivery::setPortletId);
			attributeSetterBiConsumers.put(
				"classNameId",
				(BiConsumer<UserNotificationDelivery, Long>)
					UserNotificationDelivery::setClassNameId);
			attributeSetterBiConsumers.put(
				"notificationType",
				(BiConsumer<UserNotificationDelivery, Integer>)
					UserNotificationDelivery::setNotificationType);
			attributeSetterBiConsumers.put(
				"deliveryType",
				(BiConsumer<UserNotificationDelivery, Integer>)
					UserNotificationDelivery::setDeliveryType);
			attributeSetterBiConsumers.put(
				"deliver",
				(BiConsumer<UserNotificationDelivery, Boolean>)
					UserNotificationDelivery::setDeliver);

			_attributeSetterBiConsumers = Collections.unmodifiableMap(
				(Map)attributeSetterBiConsumers);
		}

	}

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

	@Override
	public long getUserNotificationDeliveryId() {
		return _userNotificationDeliveryId;
	}

	@Override
	public void setUserNotificationDeliveryId(long userNotificationDeliveryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userNotificationDeliveryId = userNotificationDeliveryId;
	}

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

	@Override
	public String getPortletId() {
		if (_portletId == null) {
			return "";
		}
		else {
			return _portletId;
		}
	}

	@Override
	public void setPortletId(String portletId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_portletId = portletId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalPortletId() {
		return getColumnOriginalValue("portletId");
	}

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_classNameId = classNameId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalClassNameId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("classNameId"));
	}

	@Override
	public int getNotificationType() {
		return _notificationType;
	}

	@Override
	public void setNotificationType(int notificationType) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_notificationType = notificationType;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalNotificationType() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("notificationType"));
	}

	@Override
	public int getDeliveryType() {
		return _deliveryType;
	}

	@Override
	public void setDeliveryType(int deliveryType) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_deliveryType = deliveryType;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalDeliveryType() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("deliveryType"));
	}

	@Override
	public boolean getDeliver() {
		return _deliver;
	}

	@Override
	public boolean isDeliver() {
		return _deliver;
	}

	@Override
	public void setDeliver(boolean deliver) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_deliver = deliver;
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
			getCompanyId(), UserNotificationDelivery.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public UserNotificationDelivery toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, UserNotificationDelivery>
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
		UserNotificationDeliveryImpl userNotificationDeliveryImpl =
			new UserNotificationDeliveryImpl();

		userNotificationDeliveryImpl.setMvccVersion(getMvccVersion());
		userNotificationDeliveryImpl.setUserNotificationDeliveryId(
			getUserNotificationDeliveryId());
		userNotificationDeliveryImpl.setCompanyId(getCompanyId());
		userNotificationDeliveryImpl.setUserId(getUserId());
		userNotificationDeliveryImpl.setPortletId(getPortletId());
		userNotificationDeliveryImpl.setClassNameId(getClassNameId());
		userNotificationDeliveryImpl.setNotificationType(getNotificationType());
		userNotificationDeliveryImpl.setDeliveryType(getDeliveryType());
		userNotificationDeliveryImpl.setDeliver(isDeliver());

		userNotificationDeliveryImpl.resetOriginalValues();

		return userNotificationDeliveryImpl;
	}

	@Override
	public UserNotificationDelivery cloneWithOriginalValues() {
		UserNotificationDeliveryImpl userNotificationDeliveryImpl =
			new UserNotificationDeliveryImpl();

		userNotificationDeliveryImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		userNotificationDeliveryImpl.setUserNotificationDeliveryId(
			this.<Long>getColumnOriginalValue("userNotificationDeliveryId"));
		userNotificationDeliveryImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		userNotificationDeliveryImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		userNotificationDeliveryImpl.setPortletId(
			this.<String>getColumnOriginalValue("portletId"));
		userNotificationDeliveryImpl.setClassNameId(
			this.<Long>getColumnOriginalValue("classNameId"));
		userNotificationDeliveryImpl.setNotificationType(
			this.<Integer>getColumnOriginalValue("notificationType"));
		userNotificationDeliveryImpl.setDeliveryType(
			this.<Integer>getColumnOriginalValue("deliveryType"));
		userNotificationDeliveryImpl.setDeliver(
			this.<Boolean>getColumnOriginalValue("deliver"));

		return userNotificationDeliveryImpl;
	}

	@Override
	public int compareTo(UserNotificationDelivery userNotificationDelivery) {
		long primaryKey = userNotificationDelivery.getPrimaryKey();

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

		if (!(object instanceof UserNotificationDelivery)) {
			return false;
		}

		UserNotificationDelivery userNotificationDelivery =
			(UserNotificationDelivery)object;

		long primaryKey = userNotificationDelivery.getPrimaryKey();

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
	public CacheModel<UserNotificationDelivery> toCacheModel() {
		UserNotificationDeliveryCacheModel userNotificationDeliveryCacheModel =
			new UserNotificationDeliveryCacheModel();

		userNotificationDeliveryCacheModel.mvccVersion = getMvccVersion();

		userNotificationDeliveryCacheModel.userNotificationDeliveryId =
			getUserNotificationDeliveryId();

		userNotificationDeliveryCacheModel.companyId = getCompanyId();

		userNotificationDeliveryCacheModel.userId = getUserId();

		userNotificationDeliveryCacheModel.portletId = getPortletId();

		String portletId = userNotificationDeliveryCacheModel.portletId;

		if ((portletId != null) && (portletId.length() == 0)) {
			userNotificationDeliveryCacheModel.portletId = null;
		}

		userNotificationDeliveryCacheModel.classNameId = getClassNameId();

		userNotificationDeliveryCacheModel.notificationType =
			getNotificationType();

		userNotificationDeliveryCacheModel.deliveryType = getDeliveryType();

		userNotificationDeliveryCacheModel.deliver = isDeliver();

		return userNotificationDeliveryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<UserNotificationDelivery, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<UserNotificationDelivery, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserNotificationDelivery, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(UserNotificationDelivery)this);

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

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, UserNotificationDelivery>
				_escapedModelProxyProviderFunction =
					ProxyUtil.getProxyProviderFunction(
						UserNotificationDelivery.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _userNotificationDeliveryId;
	private long _companyId;
	private long _userId;
	private String _portletId;
	private long _classNameId;
	private int _notificationType;
	private int _deliveryType;
	private boolean _deliver;

	public <T> T getColumnValue(String columnName) {
		Function<UserNotificationDelivery, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((UserNotificationDelivery)this);
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
		_columnOriginalValues.put(
			"userNotificationDeliveryId", _userNotificationDeliveryId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("portletId", _portletId);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("notificationType", _notificationType);
		_columnOriginalValues.put("deliveryType", _deliveryType);
		_columnOriginalValues.put("deliver", _deliver);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("userNotificationDeliveryId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("userId", 8L);

		columnBitmasks.put("portletId", 16L);

		columnBitmasks.put("classNameId", 32L);

		columnBitmasks.put("notificationType", 64L);

		columnBitmasks.put("deliveryType", 128L);

		columnBitmasks.put("deliver", 256L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private UserNotificationDelivery _escapedModel;

}