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

package com.liferay.asset.list.model.impl;

import com.liferay.asset.list.model.AssetListEntryUsage;
import com.liferay.asset.list.model.AssetListEntryUsageModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
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
 * The base model implementation for the AssetListEntryUsage service. Represents a row in the &quot;AssetListEntryUsage&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>AssetListEntryUsageModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AssetListEntryUsageImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetListEntryUsageImpl
 * @generated
 */
public class AssetListEntryUsageModelImpl
	extends BaseModelImpl<AssetListEntryUsage>
	implements AssetListEntryUsageModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a asset list entry usage model instance should use the <code>AssetListEntryUsage</code> interface instead.
	 */
	public static final String TABLE_NAME = "AssetListEntryUsage";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR}, {"assetListEntryUsageId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"classNameId", Types.BIGINT}, {"containerKey", Types.VARCHAR},
		{"containerType", Types.BIGINT}, {"key_", Types.VARCHAR},
		{"plid", Types.BIGINT}, {"type_", Types.INTEGER},
		{"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("assetListEntryUsageId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("containerKey", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("containerType", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("key_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("plid", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table AssetListEntryUsage (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,assetListEntryUsageId LONG not null,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,classNameId LONG,containerKey VARCHAR(255) null,containerType LONG,key_ VARCHAR(255) null,plid LONG,type_ INTEGER,lastPublishDate DATE null,primary key (assetListEntryUsageId, ctCollectionId))";

	public static final String TABLE_SQL_DROP =
		"drop table AssetListEntryUsage";

	public static final String ORDER_BY_JPQL =
		" ORDER BY assetListEntryUsage.assetListEntryUsageId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY AssetListEntryUsage.assetListEntryUsageId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CONTAINERKEY_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CONTAINERTYPE_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long KEY_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long PLID_COLUMN_BITMASK = 64L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TYPE_COLUMN_BITMASK = 128L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 256L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ASSETLISTENTRYUSAGEID_COLUMN_BITMASK = 512L;

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

	public AssetListEntryUsageModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _assetListEntryUsageId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAssetListEntryUsageId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _assetListEntryUsageId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return AssetListEntryUsage.class;
	}

	@Override
	public String getModelClassName() {
		return AssetListEntryUsage.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<AssetListEntryUsage, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<AssetListEntryUsage, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AssetListEntryUsage, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((AssetListEntryUsage)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<AssetListEntryUsage, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<AssetListEntryUsage, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(AssetListEntryUsage)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<AssetListEntryUsage, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<AssetListEntryUsage, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<AssetListEntryUsage, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<AssetListEntryUsage, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<AssetListEntryUsage, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", AssetListEntryUsage::getMvccVersion);
			attributeGetterFunctions.put(
				"ctCollectionId", AssetListEntryUsage::getCtCollectionId);
			attributeGetterFunctions.put("uuid", AssetListEntryUsage::getUuid);
			attributeGetterFunctions.put(
				"assetListEntryUsageId",
				AssetListEntryUsage::getAssetListEntryUsageId);
			attributeGetterFunctions.put(
				"groupId", AssetListEntryUsage::getGroupId);
			attributeGetterFunctions.put(
				"companyId", AssetListEntryUsage::getCompanyId);
			attributeGetterFunctions.put(
				"userId", AssetListEntryUsage::getUserId);
			attributeGetterFunctions.put(
				"userName", AssetListEntryUsage::getUserName);
			attributeGetterFunctions.put(
				"createDate", AssetListEntryUsage::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", AssetListEntryUsage::getModifiedDate);
			attributeGetterFunctions.put(
				"classNameId", AssetListEntryUsage::getClassNameId);
			attributeGetterFunctions.put(
				"containerKey", AssetListEntryUsage::getContainerKey);
			attributeGetterFunctions.put(
				"containerType", AssetListEntryUsage::getContainerType);
			attributeGetterFunctions.put("key", AssetListEntryUsage::getKey);
			attributeGetterFunctions.put("plid", AssetListEntryUsage::getPlid);
			attributeGetterFunctions.put("type", AssetListEntryUsage::getType);
			attributeGetterFunctions.put(
				"lastPublishDate", AssetListEntryUsage::getLastPublishDate);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<AssetListEntryUsage, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<AssetListEntryUsage, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<AssetListEntryUsage, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<AssetListEntryUsage, Long>)
					AssetListEntryUsage::setMvccVersion);
			attributeSetterBiConsumers.put(
				"ctCollectionId",
				(BiConsumer<AssetListEntryUsage, Long>)
					AssetListEntryUsage::setCtCollectionId);
			attributeSetterBiConsumers.put(
				"uuid",
				(BiConsumer<AssetListEntryUsage, String>)
					AssetListEntryUsage::setUuid);
			attributeSetterBiConsumers.put(
				"assetListEntryUsageId",
				(BiConsumer<AssetListEntryUsage, Long>)
					AssetListEntryUsage::setAssetListEntryUsageId);
			attributeSetterBiConsumers.put(
				"groupId",
				(BiConsumer<AssetListEntryUsage, Long>)
					AssetListEntryUsage::setGroupId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<AssetListEntryUsage, Long>)
					AssetListEntryUsage::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<AssetListEntryUsage, Long>)
					AssetListEntryUsage::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<AssetListEntryUsage, String>)
					AssetListEntryUsage::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<AssetListEntryUsage, Date>)
					AssetListEntryUsage::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<AssetListEntryUsage, Date>)
					AssetListEntryUsage::setModifiedDate);
			attributeSetterBiConsumers.put(
				"classNameId",
				(BiConsumer<AssetListEntryUsage, Long>)
					AssetListEntryUsage::setClassNameId);
			attributeSetterBiConsumers.put(
				"containerKey",
				(BiConsumer<AssetListEntryUsage, String>)
					AssetListEntryUsage::setContainerKey);
			attributeSetterBiConsumers.put(
				"containerType",
				(BiConsumer<AssetListEntryUsage, Long>)
					AssetListEntryUsage::setContainerType);
			attributeSetterBiConsumers.put(
				"key",
				(BiConsumer<AssetListEntryUsage, String>)
					AssetListEntryUsage::setKey);
			attributeSetterBiConsumers.put(
				"plid",
				(BiConsumer<AssetListEntryUsage, Long>)
					AssetListEntryUsage::setPlid);
			attributeSetterBiConsumers.put(
				"type",
				(BiConsumer<AssetListEntryUsage, Integer>)
					AssetListEntryUsage::setType);
			attributeSetterBiConsumers.put(
				"lastPublishDate",
				(BiConsumer<AssetListEntryUsage, Date>)
					AssetListEntryUsage::setLastPublishDate);

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

	@Override
	public long getAssetListEntryUsageId() {
		return _assetListEntryUsageId;
	}

	@Override
	public void setAssetListEntryUsageId(long assetListEntryUsageId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_assetListEntryUsageId = assetListEntryUsageId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCompanyId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("companyId"));
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
	public String getContainerKey() {
		if (_containerKey == null) {
			return "";
		}
		else {
			return _containerKey;
		}
	}

	@Override
	public void setContainerKey(String containerKey) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_containerKey = containerKey;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalContainerKey() {
		return getColumnOriginalValue("containerKey");
	}

	@Override
	public long getContainerType() {
		return _containerType;
	}

	@Override
	public void setContainerType(long containerType) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_containerType = containerType;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalContainerType() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("containerType"));
	}

	@Override
	public String getKey() {
		if (_key == null) {
			return "";
		}
		else {
			return _key;
		}
	}

	@Override
	public void setKey(String key) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_key = key;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalKey() {
		return getColumnOriginalValue("key_");
	}

	@Override
	public long getPlid() {
		return _plid;
	}

	@Override
	public void setPlid(long plid) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_plid = plid;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalPlid() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("plid"));
	}

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

	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_lastPublishDate = lastPublishDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(AssetListEntryUsage.class.getName()),
			getClassNameId());
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
			getCompanyId(), AssetListEntryUsage.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public AssetListEntryUsage toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, AssetListEntryUsage>
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
		AssetListEntryUsageImpl assetListEntryUsageImpl =
			new AssetListEntryUsageImpl();

		assetListEntryUsageImpl.setMvccVersion(getMvccVersion());
		assetListEntryUsageImpl.setCtCollectionId(getCtCollectionId());
		assetListEntryUsageImpl.setUuid(getUuid());
		assetListEntryUsageImpl.setAssetListEntryUsageId(
			getAssetListEntryUsageId());
		assetListEntryUsageImpl.setGroupId(getGroupId());
		assetListEntryUsageImpl.setCompanyId(getCompanyId());
		assetListEntryUsageImpl.setUserId(getUserId());
		assetListEntryUsageImpl.setUserName(getUserName());
		assetListEntryUsageImpl.setCreateDate(getCreateDate());
		assetListEntryUsageImpl.setModifiedDate(getModifiedDate());
		assetListEntryUsageImpl.setClassNameId(getClassNameId());
		assetListEntryUsageImpl.setContainerKey(getContainerKey());
		assetListEntryUsageImpl.setContainerType(getContainerType());
		assetListEntryUsageImpl.setKey(getKey());
		assetListEntryUsageImpl.setPlid(getPlid());
		assetListEntryUsageImpl.setType(getType());
		assetListEntryUsageImpl.setLastPublishDate(getLastPublishDate());

		assetListEntryUsageImpl.resetOriginalValues();

		return assetListEntryUsageImpl;
	}

	@Override
	public AssetListEntryUsage cloneWithOriginalValues() {
		AssetListEntryUsageImpl assetListEntryUsageImpl =
			new AssetListEntryUsageImpl();

		assetListEntryUsageImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		assetListEntryUsageImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		assetListEntryUsageImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		assetListEntryUsageImpl.setAssetListEntryUsageId(
			this.<Long>getColumnOriginalValue("assetListEntryUsageId"));
		assetListEntryUsageImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		assetListEntryUsageImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		assetListEntryUsageImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		assetListEntryUsageImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		assetListEntryUsageImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		assetListEntryUsageImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		assetListEntryUsageImpl.setClassNameId(
			this.<Long>getColumnOriginalValue("classNameId"));
		assetListEntryUsageImpl.setContainerKey(
			this.<String>getColumnOriginalValue("containerKey"));
		assetListEntryUsageImpl.setContainerType(
			this.<Long>getColumnOriginalValue("containerType"));
		assetListEntryUsageImpl.setKey(
			this.<String>getColumnOriginalValue("key_"));
		assetListEntryUsageImpl.setPlid(
			this.<Long>getColumnOriginalValue("plid"));
		assetListEntryUsageImpl.setType(
			this.<Integer>getColumnOriginalValue("type_"));
		assetListEntryUsageImpl.setLastPublishDate(
			this.<Date>getColumnOriginalValue("lastPublishDate"));

		return assetListEntryUsageImpl;
	}

	@Override
	public int compareTo(AssetListEntryUsage assetListEntryUsage) {
		long primaryKey = assetListEntryUsage.getPrimaryKey();

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

		if (!(object instanceof AssetListEntryUsage)) {
			return false;
		}

		AssetListEntryUsage assetListEntryUsage = (AssetListEntryUsage)object;

		long primaryKey = assetListEntryUsage.getPrimaryKey();

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
	public CacheModel<AssetListEntryUsage> toCacheModel() {
		AssetListEntryUsageCacheModel assetListEntryUsageCacheModel =
			new AssetListEntryUsageCacheModel();

		assetListEntryUsageCacheModel.mvccVersion = getMvccVersion();

		assetListEntryUsageCacheModel.ctCollectionId = getCtCollectionId();

		assetListEntryUsageCacheModel.uuid = getUuid();

		String uuid = assetListEntryUsageCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			assetListEntryUsageCacheModel.uuid = null;
		}

		assetListEntryUsageCacheModel.assetListEntryUsageId =
			getAssetListEntryUsageId();

		assetListEntryUsageCacheModel.groupId = getGroupId();

		assetListEntryUsageCacheModel.companyId = getCompanyId();

		assetListEntryUsageCacheModel.userId = getUserId();

		assetListEntryUsageCacheModel.userName = getUserName();

		String userName = assetListEntryUsageCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			assetListEntryUsageCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			assetListEntryUsageCacheModel.createDate = createDate.getTime();
		}
		else {
			assetListEntryUsageCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			assetListEntryUsageCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			assetListEntryUsageCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		assetListEntryUsageCacheModel.classNameId = getClassNameId();

		assetListEntryUsageCacheModel.containerKey = getContainerKey();

		String containerKey = assetListEntryUsageCacheModel.containerKey;

		if ((containerKey != null) && (containerKey.length() == 0)) {
			assetListEntryUsageCacheModel.containerKey = null;
		}

		assetListEntryUsageCacheModel.containerType = getContainerType();

		assetListEntryUsageCacheModel.key = getKey();

		String key = assetListEntryUsageCacheModel.key;

		if ((key != null) && (key.length() == 0)) {
			assetListEntryUsageCacheModel.key = null;
		}

		assetListEntryUsageCacheModel.plid = getPlid();

		assetListEntryUsageCacheModel.type = getType();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			assetListEntryUsageCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			assetListEntryUsageCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return assetListEntryUsageCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<AssetListEntryUsage, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<AssetListEntryUsage, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AssetListEntryUsage, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(AssetListEntryUsage)this);

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

		private static final Function<InvocationHandler, AssetListEntryUsage>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					AssetListEntryUsage.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private long _assetListEntryUsageId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _classNameId;
	private String _containerKey;
	private long _containerType;
	private String _key;
	private long _plid;
	private int _type;
	private Date _lastPublishDate;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<AssetListEntryUsage, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((AssetListEntryUsage)this);
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
		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put(
			"assetListEntryUsageId", _assetListEntryUsageId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("containerKey", _containerKey);
		_columnOriginalValues.put("containerType", _containerType);
		_columnOriginalValues.put("key_", _key);
		_columnOriginalValues.put("plid", _plid);
		_columnOriginalValues.put("type_", _type);
		_columnOriginalValues.put("lastPublishDate", _lastPublishDate);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");
		attributeNames.put("key_", "key");
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

		columnBitmasks.put("ctCollectionId", 2L);

		columnBitmasks.put("uuid_", 4L);

		columnBitmasks.put("assetListEntryUsageId", 8L);

		columnBitmasks.put("groupId", 16L);

		columnBitmasks.put("companyId", 32L);

		columnBitmasks.put("userId", 64L);

		columnBitmasks.put("userName", 128L);

		columnBitmasks.put("createDate", 256L);

		columnBitmasks.put("modifiedDate", 512L);

		columnBitmasks.put("classNameId", 1024L);

		columnBitmasks.put("containerKey", 2048L);

		columnBitmasks.put("containerType", 4096L);

		columnBitmasks.put("key_", 8192L);

		columnBitmasks.put("plid", 16384L);

		columnBitmasks.put("type_", 32768L);

		columnBitmasks.put("lastPublishDate", 65536L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private AssetListEntryUsage _escapedModel;

}