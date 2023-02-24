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

package com.liferay.commerce.inventory.model.impl;

import com.liferay.commerce.inventory.model.CommerceInventoryAudit;
import com.liferay.commerce.inventory.model.CommerceInventoryAuditModel;
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
 * The base model implementation for the CommerceInventoryAudit service. Represents a row in the &quot;CIAudit&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceInventoryAuditModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceInventoryAuditImpl}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryAuditImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceInventoryAuditModelImpl
	extends BaseModelImpl<CommerceInventoryAudit>
	implements CommerceInventoryAuditModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce inventory audit model instance should use the <code>CommerceInventoryAudit</code> interface instead.
	 */
	public static final String TABLE_NAME = "CIAudit";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"CIAuditId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"sku", Types.VARCHAR},
		{"logType", Types.VARCHAR}, {"logTypeSettings", Types.CLOB},
		{"quantity", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("CIAuditId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("sku", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("logType", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("logTypeSettings", Types.CLOB);
		TABLE_COLUMNS_MAP.put("quantity", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CIAudit (mvccVersion LONG default 0 not null,CIAuditId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,sku VARCHAR(75) null,logType VARCHAR(75) null,logTypeSettings TEXT null,quantity INTEGER)";

	public static final String TABLE_SQL_DROP = "drop table CIAudit";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceInventoryAudit.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CIAudit.createDate DESC";

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
	public static final long CREATEDATE_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SKU_COLUMN_BITMASK = 4L;

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

	public CommerceInventoryAuditModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceInventoryAuditId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceInventoryAuditId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceInventoryAuditId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceInventoryAudit.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceInventoryAudit.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceInventoryAudit, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceInventoryAudit, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceInventoryAudit, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CommerceInventoryAudit)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceInventoryAudit, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceInventoryAudit, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceInventoryAudit)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceInventoryAudit, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceInventoryAudit, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map
			<String, Function<CommerceInventoryAudit, Object>>
				_attributeGetterFunctions;

		static {
			Map<String, Function<CommerceInventoryAudit, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<CommerceInventoryAudit, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", CommerceInventoryAudit::getMvccVersion);
			attributeGetterFunctions.put(
				"commerceInventoryAuditId",
				CommerceInventoryAudit::getCommerceInventoryAuditId);
			attributeGetterFunctions.put(
				"companyId", CommerceInventoryAudit::getCompanyId);
			attributeGetterFunctions.put(
				"userId", CommerceInventoryAudit::getUserId);
			attributeGetterFunctions.put(
				"userName", CommerceInventoryAudit::getUserName);
			attributeGetterFunctions.put(
				"createDate", CommerceInventoryAudit::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", CommerceInventoryAudit::getModifiedDate);
			attributeGetterFunctions.put("sku", CommerceInventoryAudit::getSku);
			attributeGetterFunctions.put(
				"logType", CommerceInventoryAudit::getLogType);
			attributeGetterFunctions.put(
				"logTypeSettings", CommerceInventoryAudit::getLogTypeSettings);
			attributeGetterFunctions.put(
				"quantity", CommerceInventoryAudit::getQuantity);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<CommerceInventoryAudit, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<CommerceInventoryAudit, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<CommerceInventoryAudit, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<CommerceInventoryAudit, Long>)
					CommerceInventoryAudit::setMvccVersion);
			attributeSetterBiConsumers.put(
				"commerceInventoryAuditId",
				(BiConsumer<CommerceInventoryAudit, Long>)
					CommerceInventoryAudit::setCommerceInventoryAuditId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<CommerceInventoryAudit, Long>)
					CommerceInventoryAudit::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<CommerceInventoryAudit, Long>)
					CommerceInventoryAudit::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<CommerceInventoryAudit, String>)
					CommerceInventoryAudit::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<CommerceInventoryAudit, Date>)
					CommerceInventoryAudit::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<CommerceInventoryAudit, Date>)
					CommerceInventoryAudit::setModifiedDate);
			attributeSetterBiConsumers.put(
				"sku",
				(BiConsumer<CommerceInventoryAudit, String>)
					CommerceInventoryAudit::setSku);
			attributeSetterBiConsumers.put(
				"logType",
				(BiConsumer<CommerceInventoryAudit, String>)
					CommerceInventoryAudit::setLogType);
			attributeSetterBiConsumers.put(
				"logTypeSettings",
				(BiConsumer<CommerceInventoryAudit, String>)
					CommerceInventoryAudit::setLogTypeSettings);
			attributeSetterBiConsumers.put(
				"quantity",
				(BiConsumer<CommerceInventoryAudit, Integer>)
					CommerceInventoryAudit::setQuantity);

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
	public long getCommerceInventoryAuditId() {
		return _commerceInventoryAuditId;
	}

	@Override
	public void setCommerceInventoryAuditId(long commerceInventoryAuditId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceInventoryAuditId = commerceInventoryAuditId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public Date getOriginalCreateDate() {
		return getColumnOriginalValue("createDate");
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
	public String getSku() {
		if (_sku == null) {
			return "";
		}
		else {
			return _sku;
		}
	}

	@Override
	public void setSku(String sku) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_sku = sku;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalSku() {
		return getColumnOriginalValue("sku");
	}

	@JSON
	@Override
	public String getLogType() {
		if (_logType == null) {
			return "";
		}
		else {
			return _logType;
		}
	}

	@Override
	public void setLogType(String logType) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_logType = logType;
	}

	@JSON
	@Override
	public String getLogTypeSettings() {
		if (_logTypeSettings == null) {
			return "";
		}
		else {
			return _logTypeSettings;
		}
	}

	@Override
	public void setLogTypeSettings(String logTypeSettings) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_logTypeSettings = logTypeSettings;
	}

	@JSON
	@Override
	public int getQuantity() {
		return _quantity;
	}

	@Override
	public void setQuantity(int quantity) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_quantity = quantity;
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
			getCompanyId(), CommerceInventoryAudit.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceInventoryAudit toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceInventoryAudit>
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
		CommerceInventoryAuditImpl commerceInventoryAuditImpl =
			new CommerceInventoryAuditImpl();

		commerceInventoryAuditImpl.setMvccVersion(getMvccVersion());
		commerceInventoryAuditImpl.setCommerceInventoryAuditId(
			getCommerceInventoryAuditId());
		commerceInventoryAuditImpl.setCompanyId(getCompanyId());
		commerceInventoryAuditImpl.setUserId(getUserId());
		commerceInventoryAuditImpl.setUserName(getUserName());
		commerceInventoryAuditImpl.setCreateDate(getCreateDate());
		commerceInventoryAuditImpl.setModifiedDate(getModifiedDate());
		commerceInventoryAuditImpl.setSku(getSku());
		commerceInventoryAuditImpl.setLogType(getLogType());
		commerceInventoryAuditImpl.setLogTypeSettings(getLogTypeSettings());
		commerceInventoryAuditImpl.setQuantity(getQuantity());

		commerceInventoryAuditImpl.resetOriginalValues();

		return commerceInventoryAuditImpl;
	}

	@Override
	public CommerceInventoryAudit cloneWithOriginalValues() {
		CommerceInventoryAuditImpl commerceInventoryAuditImpl =
			new CommerceInventoryAuditImpl();

		commerceInventoryAuditImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		commerceInventoryAuditImpl.setCommerceInventoryAuditId(
			this.<Long>getColumnOriginalValue("CIAuditId"));
		commerceInventoryAuditImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		commerceInventoryAuditImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		commerceInventoryAuditImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		commerceInventoryAuditImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		commerceInventoryAuditImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		commerceInventoryAuditImpl.setSku(
			this.<String>getColumnOriginalValue("sku"));
		commerceInventoryAuditImpl.setLogType(
			this.<String>getColumnOriginalValue("logType"));
		commerceInventoryAuditImpl.setLogTypeSettings(
			this.<String>getColumnOriginalValue("logTypeSettings"));
		commerceInventoryAuditImpl.setQuantity(
			this.<Integer>getColumnOriginalValue("quantity"));

		return commerceInventoryAuditImpl;
	}

	@Override
	public int compareTo(CommerceInventoryAudit commerceInventoryAudit) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), commerceInventoryAudit.getCreateDate());

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

		if (!(object instanceof CommerceInventoryAudit)) {
			return false;
		}

		CommerceInventoryAudit commerceInventoryAudit =
			(CommerceInventoryAudit)object;

		long primaryKey = commerceInventoryAudit.getPrimaryKey();

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
	public CacheModel<CommerceInventoryAudit> toCacheModel() {
		CommerceInventoryAuditCacheModel commerceInventoryAuditCacheModel =
			new CommerceInventoryAuditCacheModel();

		commerceInventoryAuditCacheModel.mvccVersion = getMvccVersion();

		commerceInventoryAuditCacheModel.commerceInventoryAuditId =
			getCommerceInventoryAuditId();

		commerceInventoryAuditCacheModel.companyId = getCompanyId();

		commerceInventoryAuditCacheModel.userId = getUserId();

		commerceInventoryAuditCacheModel.userName = getUserName();

		String userName = commerceInventoryAuditCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceInventoryAuditCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceInventoryAuditCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceInventoryAuditCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceInventoryAuditCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commerceInventoryAuditCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceInventoryAuditCacheModel.sku = getSku();

		String sku = commerceInventoryAuditCacheModel.sku;

		if ((sku != null) && (sku.length() == 0)) {
			commerceInventoryAuditCacheModel.sku = null;
		}

		commerceInventoryAuditCacheModel.logType = getLogType();

		String logType = commerceInventoryAuditCacheModel.logType;

		if ((logType != null) && (logType.length() == 0)) {
			commerceInventoryAuditCacheModel.logType = null;
		}

		commerceInventoryAuditCacheModel.logTypeSettings = getLogTypeSettings();

		String logTypeSettings =
			commerceInventoryAuditCacheModel.logTypeSettings;

		if ((logTypeSettings != null) && (logTypeSettings.length() == 0)) {
			commerceInventoryAuditCacheModel.logTypeSettings = null;
		}

		commerceInventoryAuditCacheModel.quantity = getQuantity();

		return commerceInventoryAuditCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceInventoryAudit, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceInventoryAudit, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceInventoryAudit, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(CommerceInventoryAudit)this);

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

		private static final Function<InvocationHandler, CommerceInventoryAudit>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					CommerceInventoryAudit.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _commerceInventoryAuditId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _sku;
	private String _logType;
	private String _logTypeSettings;
	private int _quantity;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CommerceInventoryAudit, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommerceInventoryAudit)this);
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
		_columnOriginalValues.put("CIAuditId", _commerceInventoryAuditId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("sku", _sku);
		_columnOriginalValues.put("logType", _logType);
		_columnOriginalValues.put("logTypeSettings", _logTypeSettings);
		_columnOriginalValues.put("quantity", _quantity);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("CIAuditId", "commerceInventoryAuditId");

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

		columnBitmasks.put("CIAuditId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("userId", 8L);

		columnBitmasks.put("userName", 16L);

		columnBitmasks.put("createDate", 32L);

		columnBitmasks.put("modifiedDate", 64L);

		columnBitmasks.put("sku", 128L);

		columnBitmasks.put("logType", 256L);

		columnBitmasks.put("logTypeSettings", 512L);

		columnBitmasks.put("quantity", 1024L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommerceInventoryAudit _escapedModel;

}