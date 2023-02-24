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

package com.liferay.commerce.qualifier.model.impl;

import com.liferay.commerce.qualifier.model.CommerceQualifierEntry;
import com.liferay.commerce.qualifier.model.CommerceQualifierEntryModel;
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
 * The base model implementation for the CommerceQualifierEntry service. Represents a row in the &quot;CommerceQualifierEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceQualifierEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceQualifierEntryImpl}.
 * </p>
 *
 * @author Riccardo Alberti
 * @see CommerceQualifierEntryImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceQualifierEntryModelImpl
	extends BaseModelImpl<CommerceQualifierEntry>
	implements CommerceQualifierEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce qualifier entry model instance should use the <code>CommerceQualifierEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommerceQualifierEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT},
		{"commerceQualifierEntryId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"sourceClassNameId", Types.BIGINT}, {"sourceClassPK", Types.BIGINT},
		{"sourceCQualifierMetadataKey", Types.VARCHAR},
		{"targetClassNameId", Types.BIGINT}, {"targetClassPK", Types.BIGINT},
		{"targetCQualifierMetadataKey", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceQualifierEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("sourceClassNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("sourceClassPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("sourceCQualifierMetadataKey", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("targetClassNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("targetClassPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("targetCQualifierMetadataKey", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommerceQualifierEntry (mvccVersion LONG default 0 not null,commerceQualifierEntryId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,sourceClassNameId LONG,sourceClassPK LONG,sourceCQualifierMetadataKey VARCHAR(75) null,targetClassNameId LONG,targetClassPK LONG,targetCQualifierMetadataKey VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table CommerceQualifierEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceQualifierEntry.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommerceQualifierEntry.createDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SOURCECLASSNAMEID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SOURCECLASSPK_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TARGETCLASSNAMEID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TARGETCLASSPK_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CREATEDATE_COLUMN_BITMASK = 16L;

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

	public CommerceQualifierEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceQualifierEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceQualifierEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceQualifierEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceQualifierEntry.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceQualifierEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceQualifierEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceQualifierEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceQualifierEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CommerceQualifierEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceQualifierEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceQualifierEntry, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceQualifierEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceQualifierEntry, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceQualifierEntry, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map
			<String, Function<CommerceQualifierEntry, Object>>
				_attributeGetterFunctions;

		static {
			Map<String, Function<CommerceQualifierEntry, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<CommerceQualifierEntry, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", CommerceQualifierEntry::getMvccVersion);
			attributeGetterFunctions.put(
				"commerceQualifierEntryId",
				CommerceQualifierEntry::getCommerceQualifierEntryId);
			attributeGetterFunctions.put(
				"companyId", CommerceQualifierEntry::getCompanyId);
			attributeGetterFunctions.put(
				"userId", CommerceQualifierEntry::getUserId);
			attributeGetterFunctions.put(
				"userName", CommerceQualifierEntry::getUserName);
			attributeGetterFunctions.put(
				"createDate", CommerceQualifierEntry::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", CommerceQualifierEntry::getModifiedDate);
			attributeGetterFunctions.put(
				"sourceClassNameId",
				CommerceQualifierEntry::getSourceClassNameId);
			attributeGetterFunctions.put(
				"sourceClassPK", CommerceQualifierEntry::getSourceClassPK);
			attributeGetterFunctions.put(
				"sourceCommerceQualifierMetadataKey",
				CommerceQualifierEntry::getSourceCommerceQualifierMetadataKey);
			attributeGetterFunctions.put(
				"targetClassNameId",
				CommerceQualifierEntry::getTargetClassNameId);
			attributeGetterFunctions.put(
				"targetClassPK", CommerceQualifierEntry::getTargetClassPK);
			attributeGetterFunctions.put(
				"targetCommerceQualifierMetadataKey",
				CommerceQualifierEntry::getTargetCommerceQualifierMetadataKey);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<CommerceQualifierEntry, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<CommerceQualifierEntry, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<CommerceQualifierEntry, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<CommerceQualifierEntry, Long>)
					CommerceQualifierEntry::setMvccVersion);
			attributeSetterBiConsumers.put(
				"commerceQualifierEntryId",
				(BiConsumer<CommerceQualifierEntry, Long>)
					CommerceQualifierEntry::setCommerceQualifierEntryId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<CommerceQualifierEntry, Long>)
					CommerceQualifierEntry::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<CommerceQualifierEntry, Long>)
					CommerceQualifierEntry::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<CommerceQualifierEntry, String>)
					CommerceQualifierEntry::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<CommerceQualifierEntry, Date>)
					CommerceQualifierEntry::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<CommerceQualifierEntry, Date>)
					CommerceQualifierEntry::setModifiedDate);
			attributeSetterBiConsumers.put(
				"sourceClassNameId",
				(BiConsumer<CommerceQualifierEntry, Long>)
					CommerceQualifierEntry::setSourceClassNameId);
			attributeSetterBiConsumers.put(
				"sourceClassPK",
				(BiConsumer<CommerceQualifierEntry, Long>)
					CommerceQualifierEntry::setSourceClassPK);
			attributeSetterBiConsumers.put(
				"sourceCommerceQualifierMetadataKey",
				(BiConsumer<CommerceQualifierEntry, String>)
					CommerceQualifierEntry::
						setSourceCommerceQualifierMetadataKey);
			attributeSetterBiConsumers.put(
				"targetClassNameId",
				(BiConsumer<CommerceQualifierEntry, Long>)
					CommerceQualifierEntry::setTargetClassNameId);
			attributeSetterBiConsumers.put(
				"targetClassPK",
				(BiConsumer<CommerceQualifierEntry, Long>)
					CommerceQualifierEntry::setTargetClassPK);
			attributeSetterBiConsumers.put(
				"targetCommerceQualifierMetadataKey",
				(BiConsumer<CommerceQualifierEntry, String>)
					CommerceQualifierEntry::
						setTargetCommerceQualifierMetadataKey);

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
	public long getCommerceQualifierEntryId() {
		return _commerceQualifierEntryId;
	}

	@Override
	public void setCommerceQualifierEntryId(long commerceQualifierEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceQualifierEntryId = commerceQualifierEntryId;
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
	public long getSourceClassNameId() {
		return _sourceClassNameId;
	}

	@Override
	public void setSourceClassNameId(long sourceClassNameId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_sourceClassNameId = sourceClassNameId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalSourceClassNameId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("sourceClassNameId"));
	}

	@JSON
	@Override
	public long getSourceClassPK() {
		return _sourceClassPK;
	}

	@Override
	public void setSourceClassPK(long sourceClassPK) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_sourceClassPK = sourceClassPK;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalSourceClassPK() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("sourceClassPK"));
	}

	@JSON
	@Override
	public String getSourceCommerceQualifierMetadataKey() {
		if (_sourceCommerceQualifierMetadataKey == null) {
			return "";
		}
		else {
			return _sourceCommerceQualifierMetadataKey;
		}
	}

	@Override
	public void setSourceCommerceQualifierMetadataKey(
		String sourceCommerceQualifierMetadataKey) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_sourceCommerceQualifierMetadataKey =
			sourceCommerceQualifierMetadataKey;
	}

	@JSON
	@Override
	public long getTargetClassNameId() {
		return _targetClassNameId;
	}

	@Override
	public void setTargetClassNameId(long targetClassNameId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_targetClassNameId = targetClassNameId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalTargetClassNameId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("targetClassNameId"));
	}

	@JSON
	@Override
	public long getTargetClassPK() {
		return _targetClassPK;
	}

	@Override
	public void setTargetClassPK(long targetClassPK) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_targetClassPK = targetClassPK;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalTargetClassPK() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("targetClassPK"));
	}

	@JSON
	@Override
	public String getTargetCommerceQualifierMetadataKey() {
		if (_targetCommerceQualifierMetadataKey == null) {
			return "";
		}
		else {
			return _targetCommerceQualifierMetadataKey;
		}
	}

	@Override
	public void setTargetCommerceQualifierMetadataKey(
		String targetCommerceQualifierMetadataKey) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_targetCommerceQualifierMetadataKey =
			targetCommerceQualifierMetadataKey;
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
			getCompanyId(), CommerceQualifierEntry.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceQualifierEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceQualifierEntry>
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
		CommerceQualifierEntryImpl commerceQualifierEntryImpl =
			new CommerceQualifierEntryImpl();

		commerceQualifierEntryImpl.setMvccVersion(getMvccVersion());
		commerceQualifierEntryImpl.setCommerceQualifierEntryId(
			getCommerceQualifierEntryId());
		commerceQualifierEntryImpl.setCompanyId(getCompanyId());
		commerceQualifierEntryImpl.setUserId(getUserId());
		commerceQualifierEntryImpl.setUserName(getUserName());
		commerceQualifierEntryImpl.setCreateDate(getCreateDate());
		commerceQualifierEntryImpl.setModifiedDate(getModifiedDate());
		commerceQualifierEntryImpl.setSourceClassNameId(getSourceClassNameId());
		commerceQualifierEntryImpl.setSourceClassPK(getSourceClassPK());
		commerceQualifierEntryImpl.setSourceCommerceQualifierMetadataKey(
			getSourceCommerceQualifierMetadataKey());
		commerceQualifierEntryImpl.setTargetClassNameId(getTargetClassNameId());
		commerceQualifierEntryImpl.setTargetClassPK(getTargetClassPK());
		commerceQualifierEntryImpl.setTargetCommerceQualifierMetadataKey(
			getTargetCommerceQualifierMetadataKey());

		commerceQualifierEntryImpl.resetOriginalValues();

		return commerceQualifierEntryImpl;
	}

	@Override
	public CommerceQualifierEntry cloneWithOriginalValues() {
		CommerceQualifierEntryImpl commerceQualifierEntryImpl =
			new CommerceQualifierEntryImpl();

		commerceQualifierEntryImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		commerceQualifierEntryImpl.setCommerceQualifierEntryId(
			this.<Long>getColumnOriginalValue("commerceQualifierEntryId"));
		commerceQualifierEntryImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		commerceQualifierEntryImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		commerceQualifierEntryImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		commerceQualifierEntryImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		commerceQualifierEntryImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		commerceQualifierEntryImpl.setSourceClassNameId(
			this.<Long>getColumnOriginalValue("sourceClassNameId"));
		commerceQualifierEntryImpl.setSourceClassPK(
			this.<Long>getColumnOriginalValue("sourceClassPK"));
		commerceQualifierEntryImpl.setSourceCommerceQualifierMetadataKey(
			this.<String>getColumnOriginalValue("sourceCQualifierMetadataKey"));
		commerceQualifierEntryImpl.setTargetClassNameId(
			this.<Long>getColumnOriginalValue("targetClassNameId"));
		commerceQualifierEntryImpl.setTargetClassPK(
			this.<Long>getColumnOriginalValue("targetClassPK"));
		commerceQualifierEntryImpl.setTargetCommerceQualifierMetadataKey(
			this.<String>getColumnOriginalValue("targetCQualifierMetadataKey"));

		return commerceQualifierEntryImpl;
	}

	@Override
	public int compareTo(CommerceQualifierEntry commerceQualifierEntry) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), commerceQualifierEntry.getCreateDate());

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

		if (!(object instanceof CommerceQualifierEntry)) {
			return false;
		}

		CommerceQualifierEntry commerceQualifierEntry =
			(CommerceQualifierEntry)object;

		long primaryKey = commerceQualifierEntry.getPrimaryKey();

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
	public CacheModel<CommerceQualifierEntry> toCacheModel() {
		CommerceQualifierEntryCacheModel commerceQualifierEntryCacheModel =
			new CommerceQualifierEntryCacheModel();

		commerceQualifierEntryCacheModel.mvccVersion = getMvccVersion();

		commerceQualifierEntryCacheModel.commerceQualifierEntryId =
			getCommerceQualifierEntryId();

		commerceQualifierEntryCacheModel.companyId = getCompanyId();

		commerceQualifierEntryCacheModel.userId = getUserId();

		commerceQualifierEntryCacheModel.userName = getUserName();

		String userName = commerceQualifierEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceQualifierEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceQualifierEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceQualifierEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceQualifierEntryCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commerceQualifierEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceQualifierEntryCacheModel.sourceClassNameId =
			getSourceClassNameId();

		commerceQualifierEntryCacheModel.sourceClassPK = getSourceClassPK();

		commerceQualifierEntryCacheModel.sourceCommerceQualifierMetadataKey =
			getSourceCommerceQualifierMetadataKey();

		String sourceCommerceQualifierMetadataKey =
			commerceQualifierEntryCacheModel.sourceCommerceQualifierMetadataKey;

		if ((sourceCommerceQualifierMetadataKey != null) &&
			(sourceCommerceQualifierMetadataKey.length() == 0)) {

			commerceQualifierEntryCacheModel.
				sourceCommerceQualifierMetadataKey = null;
		}

		commerceQualifierEntryCacheModel.targetClassNameId =
			getTargetClassNameId();

		commerceQualifierEntryCacheModel.targetClassPK = getTargetClassPK();

		commerceQualifierEntryCacheModel.targetCommerceQualifierMetadataKey =
			getTargetCommerceQualifierMetadataKey();

		String targetCommerceQualifierMetadataKey =
			commerceQualifierEntryCacheModel.targetCommerceQualifierMetadataKey;

		if ((targetCommerceQualifierMetadataKey != null) &&
			(targetCommerceQualifierMetadataKey.length() == 0)) {

			commerceQualifierEntryCacheModel.
				targetCommerceQualifierMetadataKey = null;
		}

		return commerceQualifierEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceQualifierEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceQualifierEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceQualifierEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(CommerceQualifierEntry)this);

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

		private static final Function<InvocationHandler, CommerceQualifierEntry>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					CommerceQualifierEntry.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _commerceQualifierEntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _sourceClassNameId;
	private long _sourceClassPK;
	private String _sourceCommerceQualifierMetadataKey;
	private long _targetClassNameId;
	private long _targetClassPK;
	private String _targetCommerceQualifierMetadataKey;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CommerceQualifierEntry, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommerceQualifierEntry)this);
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
			"commerceQualifierEntryId", _commerceQualifierEntryId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("sourceClassNameId", _sourceClassNameId);
		_columnOriginalValues.put("sourceClassPK", _sourceClassPK);
		_columnOriginalValues.put(
			"sourceCQualifierMetadataKey", _sourceCommerceQualifierMetadataKey);
		_columnOriginalValues.put("targetClassNameId", _targetClassNameId);
		_columnOriginalValues.put("targetClassPK", _targetClassPK);
		_columnOriginalValues.put(
			"targetCQualifierMetadataKey", _targetCommerceQualifierMetadataKey);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put(
			"sourceCQualifierMetadataKey",
			"sourceCommerceQualifierMetadataKey");
		attributeNames.put(
			"targetCQualifierMetadataKey",
			"targetCommerceQualifierMetadataKey");

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

		columnBitmasks.put("commerceQualifierEntryId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("userId", 8L);

		columnBitmasks.put("userName", 16L);

		columnBitmasks.put("createDate", 32L);

		columnBitmasks.put("modifiedDate", 64L);

		columnBitmasks.put("sourceClassNameId", 128L);

		columnBitmasks.put("sourceClassPK", 256L);

		columnBitmasks.put("sourceCQualifierMetadataKey", 512L);

		columnBitmasks.put("targetClassNameId", 1024L);

		columnBitmasks.put("targetClassPK", 2048L);

		columnBitmasks.put("targetCQualifierMetadataKey", 4096L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommerceQualifierEntry _escapedModel;

}