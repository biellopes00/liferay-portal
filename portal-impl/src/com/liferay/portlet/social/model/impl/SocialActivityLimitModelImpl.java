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

package com.liferay.portlet.social.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.social.kernel.model.SocialActivityLimit;
import com.liferay.social.kernel.model.SocialActivityLimitModel;

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
 * The base model implementation for the SocialActivityLimit service. Represents a row in the &quot;SocialActivityLimit&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>SocialActivityLimitModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SocialActivityLimitImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivityLimitImpl
 * @generated
 */
public class SocialActivityLimitModelImpl
	extends BaseModelImpl<SocialActivityLimit>
	implements SocialActivityLimitModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a social activity limit model instance should use the <code>SocialActivityLimit</code> interface instead.
	 */
	public static final String TABLE_NAME = "SocialActivityLimit";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"activityLimitId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"classNameId", Types.BIGINT}, {"classPK", Types.BIGINT},
		{"activityType", Types.INTEGER}, {"activityCounterName", Types.VARCHAR},
		{"value", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("activityLimitId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("activityType", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("activityCounterName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("value", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table SocialActivityLimit (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,activityLimitId LONG not null,groupId LONG,companyId LONG,userId LONG,classNameId LONG,classPK LONG,activityType INTEGER,activityCounterName VARCHAR(75) null,value VARCHAR(75) null,primary key (activityLimitId, ctCollectionId))";

	public static final String TABLE_SQL_DROP =
		"drop table SocialActivityLimit";

	public static final String ORDER_BY_JPQL =
		" ORDER BY socialActivityLimit.activityLimitId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY SocialActivityLimit.activityLimitId ASC";

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
	public static final long ACTIVITYCOUNTERNAME_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ACTIVITYTYPE_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CLASSNAMEID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CLASSPK_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ACTIVITYLIMITID_COLUMN_BITMASK = 64L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.social.kernel.model.SocialActivityLimit"));

	public SocialActivityLimitModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _activityLimitId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setActivityLimitId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _activityLimitId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SocialActivityLimit.class;
	}

	@Override
	public String getModelClassName() {
		return SocialActivityLimit.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<SocialActivityLimit, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<SocialActivityLimit, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SocialActivityLimit, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((SocialActivityLimit)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<SocialActivityLimit, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<SocialActivityLimit, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(SocialActivityLimit)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<SocialActivityLimit, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<SocialActivityLimit, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<SocialActivityLimit, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<SocialActivityLimit, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<SocialActivityLimit, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", SocialActivityLimit::getMvccVersion);
			attributeGetterFunctions.put(
				"ctCollectionId", SocialActivityLimit::getCtCollectionId);
			attributeGetterFunctions.put(
				"activityLimitId", SocialActivityLimit::getActivityLimitId);
			attributeGetterFunctions.put(
				"groupId", SocialActivityLimit::getGroupId);
			attributeGetterFunctions.put(
				"companyId", SocialActivityLimit::getCompanyId);
			attributeGetterFunctions.put(
				"userId", SocialActivityLimit::getUserId);
			attributeGetterFunctions.put(
				"classNameId", SocialActivityLimit::getClassNameId);
			attributeGetterFunctions.put(
				"classPK", SocialActivityLimit::getClassPK);
			attributeGetterFunctions.put(
				"activityType", SocialActivityLimit::getActivityType);
			attributeGetterFunctions.put(
				"activityCounterName",
				SocialActivityLimit::getActivityCounterName);
			attributeGetterFunctions.put(
				"value", SocialActivityLimit::getValue);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<SocialActivityLimit, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<SocialActivityLimit, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<SocialActivityLimit, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<SocialActivityLimit, Long>)
					SocialActivityLimit::setMvccVersion);
			attributeSetterBiConsumers.put(
				"ctCollectionId",
				(BiConsumer<SocialActivityLimit, Long>)
					SocialActivityLimit::setCtCollectionId);
			attributeSetterBiConsumers.put(
				"activityLimitId",
				(BiConsumer<SocialActivityLimit, Long>)
					SocialActivityLimit::setActivityLimitId);
			attributeSetterBiConsumers.put(
				"groupId",
				(BiConsumer<SocialActivityLimit, Long>)
					SocialActivityLimit::setGroupId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<SocialActivityLimit, Long>)
					SocialActivityLimit::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<SocialActivityLimit, Long>)
					SocialActivityLimit::setUserId);
			attributeSetterBiConsumers.put(
				"classNameId",
				(BiConsumer<SocialActivityLimit, Long>)
					SocialActivityLimit::setClassNameId);
			attributeSetterBiConsumers.put(
				"classPK",
				(BiConsumer<SocialActivityLimit, Long>)
					SocialActivityLimit::setClassPK);
			attributeSetterBiConsumers.put(
				"activityType",
				(BiConsumer<SocialActivityLimit, Integer>)
					SocialActivityLimit::setActivityType);
			attributeSetterBiConsumers.put(
				"activityCounterName",
				(BiConsumer<SocialActivityLimit, String>)
					SocialActivityLimit::setActivityCounterName);
			attributeSetterBiConsumers.put(
				"value",
				(BiConsumer<SocialActivityLimit, String>)
					SocialActivityLimit::setValue);

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
	public long getCtCollectionId() {
		return _ctCollectionId;
	}

	@Override
	public void setCtCollectionId(long ctCollectionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_ctCollectionId = ctCollectionId;
	}

	@Override
	public long getActivityLimitId() {
		return _activityLimitId;
	}

	@Override
	public void setActivityLimitId(long activityLimitId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_activityLimitId = activityLimitId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_groupId = groupId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalGroupId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("groupId"));
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
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_classPK = classPK;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalClassPK() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("classPK"));
	}

	@Override
	public int getActivityType() {
		return _activityType;
	}

	@Override
	public void setActivityType(int activityType) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_activityType = activityType;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalActivityType() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("activityType"));
	}

	@Override
	public String getActivityCounterName() {
		if (_activityCounterName == null) {
			return "";
		}
		else {
			return _activityCounterName;
		}
	}

	@Override
	public void setActivityCounterName(String activityCounterName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_activityCounterName = activityCounterName;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalActivityCounterName() {
		return getColumnOriginalValue("activityCounterName");
	}

	@Override
	public String getValue() {
		if (_value == null) {
			return "";
		}
		else {
			return _value;
		}
	}

	@Override
	public void setValue(String value) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_value = value;
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
			getCompanyId(), SocialActivityLimit.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SocialActivityLimit toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, SocialActivityLimit>
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
		SocialActivityLimitImpl socialActivityLimitImpl =
			new SocialActivityLimitImpl();

		socialActivityLimitImpl.setMvccVersion(getMvccVersion());
		socialActivityLimitImpl.setCtCollectionId(getCtCollectionId());
		socialActivityLimitImpl.setActivityLimitId(getActivityLimitId());
		socialActivityLimitImpl.setGroupId(getGroupId());
		socialActivityLimitImpl.setCompanyId(getCompanyId());
		socialActivityLimitImpl.setUserId(getUserId());
		socialActivityLimitImpl.setClassNameId(getClassNameId());
		socialActivityLimitImpl.setClassPK(getClassPK());
		socialActivityLimitImpl.setActivityType(getActivityType());
		socialActivityLimitImpl.setActivityCounterName(
			getActivityCounterName());
		socialActivityLimitImpl.setValue(getValue());

		socialActivityLimitImpl.resetOriginalValues();

		return socialActivityLimitImpl;
	}

	@Override
	public SocialActivityLimit cloneWithOriginalValues() {
		SocialActivityLimitImpl socialActivityLimitImpl =
			new SocialActivityLimitImpl();

		socialActivityLimitImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		socialActivityLimitImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		socialActivityLimitImpl.setActivityLimitId(
			this.<Long>getColumnOriginalValue("activityLimitId"));
		socialActivityLimitImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		socialActivityLimitImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		socialActivityLimitImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		socialActivityLimitImpl.setClassNameId(
			this.<Long>getColumnOriginalValue("classNameId"));
		socialActivityLimitImpl.setClassPK(
			this.<Long>getColumnOriginalValue("classPK"));
		socialActivityLimitImpl.setActivityType(
			this.<Integer>getColumnOriginalValue("activityType"));
		socialActivityLimitImpl.setActivityCounterName(
			this.<String>getColumnOriginalValue("activityCounterName"));
		socialActivityLimitImpl.setValue(
			this.<String>getColumnOriginalValue("value"));

		return socialActivityLimitImpl;
	}

	@Override
	public int compareTo(SocialActivityLimit socialActivityLimit) {
		long primaryKey = socialActivityLimit.getPrimaryKey();

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

		if (!(object instanceof SocialActivityLimit)) {
			return false;
		}

		SocialActivityLimit socialActivityLimit = (SocialActivityLimit)object;

		long primaryKey = socialActivityLimit.getPrimaryKey();

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
	public CacheModel<SocialActivityLimit> toCacheModel() {
		SocialActivityLimitCacheModel socialActivityLimitCacheModel =
			new SocialActivityLimitCacheModel();

		socialActivityLimitCacheModel.mvccVersion = getMvccVersion();

		socialActivityLimitCacheModel.ctCollectionId = getCtCollectionId();

		socialActivityLimitCacheModel.activityLimitId = getActivityLimitId();

		socialActivityLimitCacheModel.groupId = getGroupId();

		socialActivityLimitCacheModel.companyId = getCompanyId();

		socialActivityLimitCacheModel.userId = getUserId();

		socialActivityLimitCacheModel.classNameId = getClassNameId();

		socialActivityLimitCacheModel.classPK = getClassPK();

		socialActivityLimitCacheModel.activityType = getActivityType();

		socialActivityLimitCacheModel.activityCounterName =
			getActivityCounterName();

		String activityCounterName =
			socialActivityLimitCacheModel.activityCounterName;

		if ((activityCounterName != null) &&
			(activityCounterName.length() == 0)) {

			socialActivityLimitCacheModel.activityCounterName = null;
		}

		socialActivityLimitCacheModel.value = getValue();

		String value = socialActivityLimitCacheModel.value;

		if ((value != null) && (value.length() == 0)) {
			socialActivityLimitCacheModel.value = null;
		}

		return socialActivityLimitCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<SocialActivityLimit, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<SocialActivityLimit, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SocialActivityLimit, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(SocialActivityLimit)this);

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

		private static final Function<InvocationHandler, SocialActivityLimit>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					SocialActivityLimit.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _activityLimitId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private long _classNameId;
	private long _classPK;
	private int _activityType;
	private String _activityCounterName;
	private String _value;

	public <T> T getColumnValue(String columnName) {
		Function<SocialActivityLimit, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((SocialActivityLimit)this);
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
		_columnOriginalValues.put("ctCollectionId", _ctCollectionId);
		_columnOriginalValues.put("activityLimitId", _activityLimitId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("classPK", _classPK);
		_columnOriginalValues.put("activityType", _activityType);
		_columnOriginalValues.put("activityCounterName", _activityCounterName);
		_columnOriginalValues.put("value", _value);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("ctCollectionId", 2L);

		columnBitmasks.put("activityLimitId", 4L);

		columnBitmasks.put("groupId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("userId", 32L);

		columnBitmasks.put("classNameId", 64L);

		columnBitmasks.put("classPK", 128L);

		columnBitmasks.put("activityType", 256L);

		columnBitmasks.put("activityCounterName", 512L);

		columnBitmasks.put("value", 1024L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private SocialActivityLimit _escapedModel;

}