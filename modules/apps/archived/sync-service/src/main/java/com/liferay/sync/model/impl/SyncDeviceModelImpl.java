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

package com.liferay.sync.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
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
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.sync.model.SyncDevice;
import com.liferay.sync.model.SyncDeviceModel;

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
 * The base model implementation for the SyncDevice service. Represents a row in the &quot;SyncDevice&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>SyncDeviceModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SyncDeviceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SyncDeviceImpl
 * @generated
 */
@JSON(strict = true)
public class SyncDeviceModelImpl
	extends BaseModelImpl<SyncDevice> implements SyncDeviceModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a sync device model instance should use the <code>SyncDevice</code> interface instead.
	 */
	public static final String TABLE_NAME = "SyncDevice";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"syncDeviceId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"type_", Types.VARCHAR},
		{"buildNumber", Types.BIGINT}, {"featureSet", Types.INTEGER},
		{"hostname", Types.VARCHAR}, {"status", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("syncDeviceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("buildNumber", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("featureSet", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("hostname", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table SyncDevice (uuid_ VARCHAR(75) null,syncDeviceId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,type_ VARCHAR(75) null,buildNumber LONG,featureSet INTEGER,hostname VARCHAR(75) null,status INTEGER)";

	public static final String TABLE_SQL_DROP = "drop table SyncDevice";

	public static final String ORDER_BY_JPQL =
		" ORDER BY syncDevice.syncDeviceId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY SyncDevice.syncDeviceId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERNAME_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SYNCDEVICEID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	public SyncDeviceModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _syncDeviceId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSyncDeviceId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _syncDeviceId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SyncDevice.class;
	}

	@Override
	public String getModelClassName() {
		return SyncDevice.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<SyncDevice, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<SyncDevice, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SyncDevice, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((SyncDevice)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<SyncDevice, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<SyncDevice, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(SyncDevice)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<SyncDevice, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<SyncDevice, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<SyncDevice, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<SyncDevice, Object>> attributeGetterFunctions =
				new LinkedHashMap<String, Function<SyncDevice, Object>>();

			attributeGetterFunctions.put("uuid", SyncDevice::getUuid);
			attributeGetterFunctions.put(
				"syncDeviceId", SyncDevice::getSyncDeviceId);
			attributeGetterFunctions.put("companyId", SyncDevice::getCompanyId);
			attributeGetterFunctions.put("userId", SyncDevice::getUserId);
			attributeGetterFunctions.put("userName", SyncDevice::getUserName);
			attributeGetterFunctions.put(
				"createDate", SyncDevice::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", SyncDevice::getModifiedDate);
			attributeGetterFunctions.put("type", SyncDevice::getType);
			attributeGetterFunctions.put(
				"buildNumber", SyncDevice::getBuildNumber);
			attributeGetterFunctions.put(
				"featureSet", SyncDevice::getFeatureSet);
			attributeGetterFunctions.put("hostname", SyncDevice::getHostname);
			attributeGetterFunctions.put("status", SyncDevice::getStatus);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<SyncDevice, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<SyncDevice, ?>> attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<SyncDevice, ?>>();

			attributeSetterBiConsumers.put(
				"uuid", (BiConsumer<SyncDevice, String>)SyncDevice::setUuid);
			attributeSetterBiConsumers.put(
				"syncDeviceId",
				(BiConsumer<SyncDevice, Long>)SyncDevice::setSyncDeviceId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<SyncDevice, Long>)SyncDevice::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId", (BiConsumer<SyncDevice, Long>)SyncDevice::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<SyncDevice, String>)SyncDevice::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<SyncDevice, Date>)SyncDevice::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<SyncDevice, Date>)SyncDevice::setModifiedDate);
			attributeSetterBiConsumers.put(
				"type", (BiConsumer<SyncDevice, String>)SyncDevice::setType);
			attributeSetterBiConsumers.put(
				"buildNumber",
				(BiConsumer<SyncDevice, Long>)SyncDevice::setBuildNumber);
			attributeSetterBiConsumers.put(
				"featureSet",
				(BiConsumer<SyncDevice, Integer>)SyncDevice::setFeatureSet);
			attributeSetterBiConsumers.put(
				"hostname",
				(BiConsumer<SyncDevice, String>)SyncDevice::setHostname);
			attributeSetterBiConsumers.put(
				"status",
				(BiConsumer<SyncDevice, Integer>)SyncDevice::setStatus);

			_attributeSetterBiConsumers = Collections.unmodifiableMap(
				(Map)attributeSetterBiConsumers);
		}

	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_uuid = uuid;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalUuid() {
		return getColumnOriginalValue("uuid_");
	}

	@JSON
	@Override
	public long getSyncDeviceId() {
		return _syncDeviceId;
	}

	@Override
	public void setSyncDeviceId(long syncDeviceId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_syncDeviceId = syncDeviceId;
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
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userName = userName;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalUserName() {
		return getColumnOriginalValue("userName");
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedDate = modifiedDate;
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

	@JSON
	@Override
	public long getBuildNumber() {
		return _buildNumber;
	}

	@Override
	public void setBuildNumber(long buildNumber) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_buildNumber = buildNumber;
	}

	@JSON
	@Override
	public int getFeatureSet() {
		return _featureSet;
	}

	@Override
	public void setFeatureSet(int featureSet) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_featureSet = featureSet;
	}

	@JSON
	@Override
	public String getHostname() {
		if (_hostname == null) {
			return "";
		}
		else {
			return _hostname;
		}
	}

	@Override
	public void setHostname(String hostname) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_hostname = hostname;
	}

	@JSON
	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_status = status;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(SyncDevice.class.getName()));
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
			getCompanyId(), SyncDevice.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SyncDevice toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, SyncDevice>
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
		SyncDeviceImpl syncDeviceImpl = new SyncDeviceImpl();

		syncDeviceImpl.setUuid(getUuid());
		syncDeviceImpl.setSyncDeviceId(getSyncDeviceId());
		syncDeviceImpl.setCompanyId(getCompanyId());
		syncDeviceImpl.setUserId(getUserId());
		syncDeviceImpl.setUserName(getUserName());
		syncDeviceImpl.setCreateDate(getCreateDate());
		syncDeviceImpl.setModifiedDate(getModifiedDate());
		syncDeviceImpl.setType(getType());
		syncDeviceImpl.setBuildNumber(getBuildNumber());
		syncDeviceImpl.setFeatureSet(getFeatureSet());
		syncDeviceImpl.setHostname(getHostname());
		syncDeviceImpl.setStatus(getStatus());

		syncDeviceImpl.resetOriginalValues();

		return syncDeviceImpl;
	}

	@Override
	public SyncDevice cloneWithOriginalValues() {
		SyncDeviceImpl syncDeviceImpl = new SyncDeviceImpl();

		syncDeviceImpl.setUuid(this.<String>getColumnOriginalValue("uuid_"));
		syncDeviceImpl.setSyncDeviceId(
			this.<Long>getColumnOriginalValue("syncDeviceId"));
		syncDeviceImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		syncDeviceImpl.setUserId(this.<Long>getColumnOriginalValue("userId"));
		syncDeviceImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		syncDeviceImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		syncDeviceImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		syncDeviceImpl.setType(this.<String>getColumnOriginalValue("type_"));
		syncDeviceImpl.setBuildNumber(
			this.<Long>getColumnOriginalValue("buildNumber"));
		syncDeviceImpl.setFeatureSet(
			this.<Integer>getColumnOriginalValue("featureSet"));
		syncDeviceImpl.setHostname(
			this.<String>getColumnOriginalValue("hostname"));
		syncDeviceImpl.setStatus(
			this.<Integer>getColumnOriginalValue("status"));

		return syncDeviceImpl;
	}

	@Override
	public int compareTo(SyncDevice syncDevice) {
		long primaryKey = syncDevice.getPrimaryKey();

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

		if (!(object instanceof SyncDevice)) {
			return false;
		}

		SyncDevice syncDevice = (SyncDevice)object;

		long primaryKey = syncDevice.getPrimaryKey();

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
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<SyncDevice> toCacheModel() {
		SyncDeviceCacheModel syncDeviceCacheModel = new SyncDeviceCacheModel();

		syncDeviceCacheModel.uuid = getUuid();

		String uuid = syncDeviceCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			syncDeviceCacheModel.uuid = null;
		}

		syncDeviceCacheModel.syncDeviceId = getSyncDeviceId();

		syncDeviceCacheModel.companyId = getCompanyId();

		syncDeviceCacheModel.userId = getUserId();

		syncDeviceCacheModel.userName = getUserName();

		String userName = syncDeviceCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			syncDeviceCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			syncDeviceCacheModel.createDate = createDate.getTime();
		}
		else {
			syncDeviceCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			syncDeviceCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			syncDeviceCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		syncDeviceCacheModel.type = getType();

		String type = syncDeviceCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			syncDeviceCacheModel.type = null;
		}

		syncDeviceCacheModel.buildNumber = getBuildNumber();

		syncDeviceCacheModel.featureSet = getFeatureSet();

		syncDeviceCacheModel.hostname = getHostname();

		String hostname = syncDeviceCacheModel.hostname;

		if ((hostname != null) && (hostname.length() == 0)) {
			syncDeviceCacheModel.hostname = null;
		}

		syncDeviceCacheModel.status = getStatus();

		return syncDeviceCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<SyncDevice, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<SyncDevice, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SyncDevice, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((SyncDevice)this);

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

		private static final Function<InvocationHandler, SyncDevice>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					SyncDevice.class, ModelWrapper.class);

	}

	private String _uuid;
	private long _syncDeviceId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _type;
	private long _buildNumber;
	private int _featureSet;
	private String _hostname;
	private int _status;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<SyncDevice, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((SyncDevice)this);
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

		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put("syncDeviceId", _syncDeviceId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("type_", _type);
		_columnOriginalValues.put("buildNumber", _buildNumber);
		_columnOriginalValues.put("featureSet", _featureSet);
		_columnOriginalValues.put("hostname", _hostname);
		_columnOriginalValues.put("status", _status);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");
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

		columnBitmasks.put("uuid_", 1L);

		columnBitmasks.put("syncDeviceId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("userId", 8L);

		columnBitmasks.put("userName", 16L);

		columnBitmasks.put("createDate", 32L);

		columnBitmasks.put("modifiedDate", 64L);

		columnBitmasks.put("type_", 128L);

		columnBitmasks.put("buildNumber", 256L);

		columnBitmasks.put("featureSet", 512L);

		columnBitmasks.put("hostname", 1024L);

		columnBitmasks.put("status", 2048L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private SyncDevice _escapedModel;

}