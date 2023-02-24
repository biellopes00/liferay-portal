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

package com.liferay.commerce.price.list.model.impl;

import com.liferay.commerce.price.list.model.CommercePriceListOrderTypeRel;
import com.liferay.commerce.price.list.model.CommercePriceListOrderTypeRelModel;
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
 * The base model implementation for the CommercePriceListOrderTypeRel service. Represents a row in the &quot;CommercePriceListOrderTypeRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommercePriceListOrderTypeRelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommercePriceListOrderTypeRelImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceListOrderTypeRelImpl
 * @generated
 */
@JSON(strict = true)
public class CommercePriceListOrderTypeRelModelImpl
	extends BaseModelImpl<CommercePriceListOrderTypeRel>
	implements CommercePriceListOrderTypeRelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce price list order type rel model instance should use the <code>CommercePriceListOrderTypeRel</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommercePriceListOrderTypeRel";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR}, {"CPriceListOrderTypeRelId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"commercePriceListId", Types.BIGINT},
		{"commerceOrderTypeId", Types.BIGINT}, {"priority", Types.INTEGER},
		{"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CPriceListOrderTypeRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commercePriceListId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceOrderTypeId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("priority", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommercePriceListOrderTypeRel (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,CPriceListOrderTypeRelId LONG not null,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commercePriceListId LONG,commerceOrderTypeId LONG,priority INTEGER,lastPublishDate DATE null,primary key (CPriceListOrderTypeRelId, ctCollectionId))";

	public static final String TABLE_SQL_DROP =
		"drop table CommercePriceListOrderTypeRel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commercePriceListOrderTypeRel.priority ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommercePriceListOrderTypeRel.priority ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMMERCEORDERTYPEID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMMERCEPRICELISTID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 4L;

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
	public static final long PRIORITY_COLUMN_BITMASK = 16L;

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

	public CommercePriceListOrderTypeRelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commercePriceListOrderTypeRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommercePriceListOrderTypeRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commercePriceListOrderTypeRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommercePriceListOrderTypeRel.class;
	}

	@Override
	public String getModelClassName() {
		return CommercePriceListOrderTypeRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommercePriceListOrderTypeRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommercePriceListOrderTypeRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommercePriceListOrderTypeRel, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(CommercePriceListOrderTypeRel)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommercePriceListOrderTypeRel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommercePriceListOrderTypeRel, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommercePriceListOrderTypeRel)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommercePriceListOrderTypeRel, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommercePriceListOrderTypeRel, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map
			<String, Function<CommercePriceListOrderTypeRel, Object>>
				_attributeGetterFunctions;

		static {
			Map<String, Function<CommercePriceListOrderTypeRel, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String,
						 Function<CommercePriceListOrderTypeRel, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", CommercePriceListOrderTypeRel::getMvccVersion);
			attributeGetterFunctions.put(
				"ctCollectionId",
				CommercePriceListOrderTypeRel::getCtCollectionId);
			attributeGetterFunctions.put(
				"uuid", CommercePriceListOrderTypeRel::getUuid);
			attributeGetterFunctions.put(
				"commercePriceListOrderTypeRelId",
				CommercePriceListOrderTypeRel::
					getCommercePriceListOrderTypeRelId);
			attributeGetterFunctions.put(
				"companyId", CommercePriceListOrderTypeRel::getCompanyId);
			attributeGetterFunctions.put(
				"userId", CommercePriceListOrderTypeRel::getUserId);
			attributeGetterFunctions.put(
				"userName", CommercePriceListOrderTypeRel::getUserName);
			attributeGetterFunctions.put(
				"createDate", CommercePriceListOrderTypeRel::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", CommercePriceListOrderTypeRel::getModifiedDate);
			attributeGetterFunctions.put(
				"commercePriceListId",
				CommercePriceListOrderTypeRel::getCommercePriceListId);
			attributeGetterFunctions.put(
				"commerceOrderTypeId",
				CommercePriceListOrderTypeRel::getCommerceOrderTypeId);
			attributeGetterFunctions.put(
				"priority", CommercePriceListOrderTypeRel::getPriority);
			attributeGetterFunctions.put(
				"lastPublishDate",
				CommercePriceListOrderTypeRel::getLastPublishDate);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<CommercePriceListOrderTypeRel, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<CommercePriceListOrderTypeRel, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String,
						 BiConsumer<CommercePriceListOrderTypeRel, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<CommercePriceListOrderTypeRel, Long>)
					CommercePriceListOrderTypeRel::setMvccVersion);
			attributeSetterBiConsumers.put(
				"ctCollectionId",
				(BiConsumer<CommercePriceListOrderTypeRel, Long>)
					CommercePriceListOrderTypeRel::setCtCollectionId);
			attributeSetterBiConsumers.put(
				"uuid",
				(BiConsumer<CommercePriceListOrderTypeRel, String>)
					CommercePriceListOrderTypeRel::setUuid);
			attributeSetterBiConsumers.put(
				"commercePriceListOrderTypeRelId",
				(BiConsumer<CommercePriceListOrderTypeRel, Long>)
					CommercePriceListOrderTypeRel::
						setCommercePriceListOrderTypeRelId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<CommercePriceListOrderTypeRel, Long>)
					CommercePriceListOrderTypeRel::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<CommercePriceListOrderTypeRel, Long>)
					CommercePriceListOrderTypeRel::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<CommercePriceListOrderTypeRel, String>)
					CommercePriceListOrderTypeRel::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<CommercePriceListOrderTypeRel, Date>)
					CommercePriceListOrderTypeRel::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<CommercePriceListOrderTypeRel, Date>)
					CommercePriceListOrderTypeRel::setModifiedDate);
			attributeSetterBiConsumers.put(
				"commercePriceListId",
				(BiConsumer<CommercePriceListOrderTypeRel, Long>)
					CommercePriceListOrderTypeRel::setCommercePriceListId);
			attributeSetterBiConsumers.put(
				"commerceOrderTypeId",
				(BiConsumer<CommercePriceListOrderTypeRel, Long>)
					CommercePriceListOrderTypeRel::setCommerceOrderTypeId);
			attributeSetterBiConsumers.put(
				"priority",
				(BiConsumer<CommercePriceListOrderTypeRel, Integer>)
					CommercePriceListOrderTypeRel::setPriority);
			attributeSetterBiConsumers.put(
				"lastPublishDate",
				(BiConsumer<CommercePriceListOrderTypeRel, Date>)
					CommercePriceListOrderTypeRel::setLastPublishDate);

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
	public long getCommercePriceListOrderTypeRelId() {
		return _commercePriceListOrderTypeRelId;
	}

	@Override
	public void setCommercePriceListOrderTypeRelId(
		long commercePriceListOrderTypeRelId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commercePriceListOrderTypeRelId = commercePriceListOrderTypeRelId;
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
	public long getCommercePriceListId() {
		return _commercePriceListId;
	}

	@Override
	public void setCommercePriceListId(long commercePriceListId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commercePriceListId = commercePriceListId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCommercePriceListId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("commercePriceListId"));
	}

	@JSON
	@Override
	public long getCommerceOrderTypeId() {
		return _commerceOrderTypeId;
	}

	@Override
	public void setCommerceOrderTypeId(long commerceOrderTypeId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceOrderTypeId = commerceOrderTypeId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCommerceOrderTypeId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("commerceOrderTypeId"));
	}

	@JSON
	@Override
	public int getPriority() {
		return _priority;
	}

	@Override
	public void setPriority(int priority) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_priority = priority;
	}

	@JSON
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
			PortalUtil.getClassNameId(
				CommercePriceListOrderTypeRel.class.getName()));
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
			getCompanyId(), CommercePriceListOrderTypeRel.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommercePriceListOrderTypeRel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommercePriceListOrderTypeRel>
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
		CommercePriceListOrderTypeRelImpl commercePriceListOrderTypeRelImpl =
			new CommercePriceListOrderTypeRelImpl();

		commercePriceListOrderTypeRelImpl.setMvccVersion(getMvccVersion());
		commercePriceListOrderTypeRelImpl.setCtCollectionId(
			getCtCollectionId());
		commercePriceListOrderTypeRelImpl.setUuid(getUuid());
		commercePriceListOrderTypeRelImpl.setCommercePriceListOrderTypeRelId(
			getCommercePriceListOrderTypeRelId());
		commercePriceListOrderTypeRelImpl.setCompanyId(getCompanyId());
		commercePriceListOrderTypeRelImpl.setUserId(getUserId());
		commercePriceListOrderTypeRelImpl.setUserName(getUserName());
		commercePriceListOrderTypeRelImpl.setCreateDate(getCreateDate());
		commercePriceListOrderTypeRelImpl.setModifiedDate(getModifiedDate());
		commercePriceListOrderTypeRelImpl.setCommercePriceListId(
			getCommercePriceListId());
		commercePriceListOrderTypeRelImpl.setCommerceOrderTypeId(
			getCommerceOrderTypeId());
		commercePriceListOrderTypeRelImpl.setPriority(getPriority());
		commercePriceListOrderTypeRelImpl.setLastPublishDate(
			getLastPublishDate());

		commercePriceListOrderTypeRelImpl.resetOriginalValues();

		return commercePriceListOrderTypeRelImpl;
	}

	@Override
	public CommercePriceListOrderTypeRel cloneWithOriginalValues() {
		CommercePriceListOrderTypeRelImpl commercePriceListOrderTypeRelImpl =
			new CommercePriceListOrderTypeRelImpl();

		commercePriceListOrderTypeRelImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		commercePriceListOrderTypeRelImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		commercePriceListOrderTypeRelImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		commercePriceListOrderTypeRelImpl.setCommercePriceListOrderTypeRelId(
			this.<Long>getColumnOriginalValue("CPriceListOrderTypeRelId"));
		commercePriceListOrderTypeRelImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		commercePriceListOrderTypeRelImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		commercePriceListOrderTypeRelImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		commercePriceListOrderTypeRelImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		commercePriceListOrderTypeRelImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		commercePriceListOrderTypeRelImpl.setCommercePriceListId(
			this.<Long>getColumnOriginalValue("commercePriceListId"));
		commercePriceListOrderTypeRelImpl.setCommerceOrderTypeId(
			this.<Long>getColumnOriginalValue("commerceOrderTypeId"));
		commercePriceListOrderTypeRelImpl.setPriority(
			this.<Integer>getColumnOriginalValue("priority"));
		commercePriceListOrderTypeRelImpl.setLastPublishDate(
			this.<Date>getColumnOriginalValue("lastPublishDate"));

		return commercePriceListOrderTypeRelImpl;
	}

	@Override
	public int compareTo(
		CommercePriceListOrderTypeRel commercePriceListOrderTypeRel) {

		int value = 0;

		if (getPriority() < commercePriceListOrderTypeRel.getPriority()) {
			value = -1;
		}
		else if (getPriority() > commercePriceListOrderTypeRel.getPriority()) {
			value = 1;
		}
		else {
			value = 0;
		}

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

		if (!(object instanceof CommercePriceListOrderTypeRel)) {
			return false;
		}

		CommercePriceListOrderTypeRel commercePriceListOrderTypeRel =
			(CommercePriceListOrderTypeRel)object;

		long primaryKey = commercePriceListOrderTypeRel.getPrimaryKey();

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
	public CacheModel<CommercePriceListOrderTypeRel> toCacheModel() {
		CommercePriceListOrderTypeRelCacheModel
			commercePriceListOrderTypeRelCacheModel =
				new CommercePriceListOrderTypeRelCacheModel();

		commercePriceListOrderTypeRelCacheModel.mvccVersion = getMvccVersion();

		commercePriceListOrderTypeRelCacheModel.ctCollectionId =
			getCtCollectionId();

		commercePriceListOrderTypeRelCacheModel.uuid = getUuid();

		String uuid = commercePriceListOrderTypeRelCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			commercePriceListOrderTypeRelCacheModel.uuid = null;
		}

		commercePriceListOrderTypeRelCacheModel.
			commercePriceListOrderTypeRelId =
				getCommercePriceListOrderTypeRelId();

		commercePriceListOrderTypeRelCacheModel.companyId = getCompanyId();

		commercePriceListOrderTypeRelCacheModel.userId = getUserId();

		commercePriceListOrderTypeRelCacheModel.userName = getUserName();

		String userName = commercePriceListOrderTypeRelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commercePriceListOrderTypeRelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commercePriceListOrderTypeRelCacheModel.createDate =
				createDate.getTime();
		}
		else {
			commercePriceListOrderTypeRelCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commercePriceListOrderTypeRelCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commercePriceListOrderTypeRelCacheModel.modifiedDate =
				Long.MIN_VALUE;
		}

		commercePriceListOrderTypeRelCacheModel.commercePriceListId =
			getCommercePriceListId();

		commercePriceListOrderTypeRelCacheModel.commerceOrderTypeId =
			getCommerceOrderTypeId();

		commercePriceListOrderTypeRelCacheModel.priority = getPriority();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			commercePriceListOrderTypeRelCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			commercePriceListOrderTypeRelCacheModel.lastPublishDate =
				Long.MIN_VALUE;
		}

		return commercePriceListOrderTypeRelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommercePriceListOrderTypeRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommercePriceListOrderTypeRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommercePriceListOrderTypeRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(CommercePriceListOrderTypeRel)this);

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
			<InvocationHandler, CommercePriceListOrderTypeRel>
				_escapedModelProxyProviderFunction =
					ProxyUtil.getProxyProviderFunction(
						CommercePriceListOrderTypeRel.class,
						ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private long _commercePriceListOrderTypeRelId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commercePriceListId;
	private long _commerceOrderTypeId;
	private int _priority;
	private Date _lastPublishDate;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CommercePriceListOrderTypeRel, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommercePriceListOrderTypeRel)this);
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
			"CPriceListOrderTypeRelId", _commercePriceListOrderTypeRelId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("commercePriceListId", _commercePriceListId);
		_columnOriginalValues.put("commerceOrderTypeId", _commerceOrderTypeId);
		_columnOriginalValues.put("priority", _priority);
		_columnOriginalValues.put("lastPublishDate", _lastPublishDate);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");
		attributeNames.put(
			"CPriceListOrderTypeRelId", "commercePriceListOrderTypeRelId");

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

		columnBitmasks.put("CPriceListOrderTypeRelId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("userId", 32L);

		columnBitmasks.put("userName", 64L);

		columnBitmasks.put("createDate", 128L);

		columnBitmasks.put("modifiedDate", 256L);

		columnBitmasks.put("commercePriceListId", 512L);

		columnBitmasks.put("commerceOrderTypeId", 1024L);

		columnBitmasks.put("priority", 2048L);

		columnBitmasks.put("lastPublishDate", 4096L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommercePriceListOrderTypeRel _escapedModel;

}