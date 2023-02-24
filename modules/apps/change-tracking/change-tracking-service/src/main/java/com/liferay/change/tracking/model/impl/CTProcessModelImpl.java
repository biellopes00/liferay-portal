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

package com.liferay.change.tracking.model.impl;

import com.liferay.change.tracking.model.CTProcess;
import com.liferay.change.tracking.model.CTProcessModel;
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
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

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
 * The base model implementation for the CTProcess service. Represents a row in the &quot;CTProcess&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CTProcessModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CTProcessImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CTProcessImpl
 * @generated
 */
@JSON(strict = true)
public class CTProcessModelImpl
	extends BaseModelImpl<CTProcess> implements CTProcessModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ct process model instance should use the <code>CTProcess</code> interface instead.
	 */
	public static final String TABLE_NAME = "CTProcess";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctProcessId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP}, {"ctCollectionId", Types.BIGINT},
		{"backgroundTaskId", Types.BIGINT}, {"type_", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctProcessId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("backgroundTaskId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CTProcess (mvccVersion LONG default 0 not null,ctProcessId LONG not null primary key,companyId LONG,userId LONG,createDate DATE null,ctCollectionId LONG,backgroundTaskId LONG,type_ INTEGER)";

	public static final String TABLE_SQL_DROP = "drop table CTProcess";

	public static final String ORDER_BY_JPQL =
		" ORDER BY ctProcess.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CTProcess.createDate DESC";

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
	public static final long CTCOLLECTIONID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TYPE_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CREATEDATE_COLUMN_BITMASK = 8L;

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

	public CTProcessModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _ctProcessId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCtProcessId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ctProcessId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CTProcess.class;
	}

	@Override
	public String getModelClassName() {
		return CTProcess.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CTProcess, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CTProcess, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CTProcess, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((CTProcess)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CTProcess, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CTProcess, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CTProcess)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CTProcess, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CTProcess, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<CTProcess, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<CTProcess, Object>> attributeGetterFunctions =
				new LinkedHashMap<String, Function<CTProcess, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", CTProcess::getMvccVersion);
			attributeGetterFunctions.put(
				"ctProcessId", CTProcess::getCtProcessId);
			attributeGetterFunctions.put("companyId", CTProcess::getCompanyId);
			attributeGetterFunctions.put("userId", CTProcess::getUserId);
			attributeGetterFunctions.put(
				"createDate", CTProcess::getCreateDate);
			attributeGetterFunctions.put(
				"ctCollectionId", CTProcess::getCtCollectionId);
			attributeGetterFunctions.put(
				"backgroundTaskId", CTProcess::getBackgroundTaskId);
			attributeGetterFunctions.put("type", CTProcess::getType);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<CTProcess, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<CTProcess, ?>> attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<CTProcess, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<CTProcess, Long>)CTProcess::setMvccVersion);
			attributeSetterBiConsumers.put(
				"ctProcessId",
				(BiConsumer<CTProcess, Long>)CTProcess::setCtProcessId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<CTProcess, Long>)CTProcess::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId", (BiConsumer<CTProcess, Long>)CTProcess::setUserId);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<CTProcess, Date>)CTProcess::setCreateDate);
			attributeSetterBiConsumers.put(
				"ctCollectionId",
				(BiConsumer<CTProcess, Long>)CTProcess::setCtCollectionId);
			attributeSetterBiConsumers.put(
				"backgroundTaskId",
				(BiConsumer<CTProcess, Long>)CTProcess::setBackgroundTaskId);
			attributeSetterBiConsumers.put(
				"type", (BiConsumer<CTProcess, Integer>)CTProcess::setType);

			_attributeSetterBiConsumers = Collections.unmodifiableMap(
				(Map)attributeSetterBiConsumers);
		}

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
	public long getCtProcessId() {
		return _ctProcessId;
	}

	@Override
	public void setCtProcessId(long ctProcessId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_ctProcessId = ctProcessId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCtCollectionId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
	}

	@JSON
	@Override
	public long getBackgroundTaskId() {
		return _backgroundTaskId;
	}

	@Override
	public void setBackgroundTaskId(long backgroundTaskId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_backgroundTaskId = backgroundTaskId;
	}

	@JSON
	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
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
	public int getOriginalType() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("type_"));
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
			getCompanyId(), CTProcess.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CTProcess toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CTProcess>
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
		CTProcessImpl ctProcessImpl = new CTProcessImpl();

		ctProcessImpl.setMvccVersion(getMvccVersion());
		ctProcessImpl.setCtProcessId(getCtProcessId());
		ctProcessImpl.setCompanyId(getCompanyId());
		ctProcessImpl.setUserId(getUserId());
		ctProcessImpl.setCreateDate(getCreateDate());
		ctProcessImpl.setCtCollectionId(getCtCollectionId());
		ctProcessImpl.setBackgroundTaskId(getBackgroundTaskId());
		ctProcessImpl.setType(getType());

		ctProcessImpl.resetOriginalValues();

		return ctProcessImpl;
	}

	@Override
	public CTProcess cloneWithOriginalValues() {
		CTProcessImpl ctProcessImpl = new CTProcessImpl();

		ctProcessImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		ctProcessImpl.setCtProcessId(
			this.<Long>getColumnOriginalValue("ctProcessId"));
		ctProcessImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		ctProcessImpl.setUserId(this.<Long>getColumnOriginalValue("userId"));
		ctProcessImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		ctProcessImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		ctProcessImpl.setBackgroundTaskId(
			this.<Long>getColumnOriginalValue("backgroundTaskId"));
		ctProcessImpl.setType(this.<Integer>getColumnOriginalValue("type_"));

		return ctProcessImpl;
	}

	@Override
	public int compareTo(CTProcess ctProcess) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(), ctProcess.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CTProcess)) {
			return false;
		}

		CTProcess ctProcess = (CTProcess)object;

		long primaryKey = ctProcess.getPrimaryKey();

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

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<CTProcess> toCacheModel() {
		CTProcessCacheModel ctProcessCacheModel = new CTProcessCacheModel();

		ctProcessCacheModel.mvccVersion = getMvccVersion();

		ctProcessCacheModel.ctProcessId = getCtProcessId();

		ctProcessCacheModel.companyId = getCompanyId();

		ctProcessCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			ctProcessCacheModel.createDate = createDate.getTime();
		}
		else {
			ctProcessCacheModel.createDate = Long.MIN_VALUE;
		}

		ctProcessCacheModel.ctCollectionId = getCtCollectionId();

		ctProcessCacheModel.backgroundTaskId = getBackgroundTaskId();

		ctProcessCacheModel.type = getType();

		return ctProcessCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CTProcess, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CTProcess, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CTProcess, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((CTProcess)this);

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

		private static final Function<InvocationHandler, CTProcess>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					CTProcess.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctProcessId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private long _ctCollectionId;
	private long _backgroundTaskId;
	private int _type;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CTProcess, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CTProcess)this);
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
		_columnOriginalValues.put("ctProcessId", _ctProcessId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("ctCollectionId", _ctCollectionId);
		_columnOriginalValues.put("backgroundTaskId", _backgroundTaskId);
		_columnOriginalValues.put("type_", _type);
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

		columnBitmasks.put("ctProcessId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("userId", 8L);

		columnBitmasks.put("createDate", 16L);

		columnBitmasks.put("ctCollectionId", 32L);

		columnBitmasks.put("backgroundTaskId", 64L);

		columnBitmasks.put("type_", 128L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CTProcess _escapedModel;

}