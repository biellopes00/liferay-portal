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

import com.liferay.commerce.price.list.model.CommercePriceListChannelRel;
import com.liferay.commerce.price.list.model.CommercePriceListChannelRelModel;
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
 * The base model implementation for the CommercePriceListChannelRel service. Represents a row in the &quot;CommercePriceListChannelRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommercePriceListChannelRelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommercePriceListChannelRelImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceListChannelRelImpl
 * @generated
 */
@JSON(strict = true)
public class CommercePriceListChannelRelModelImpl
	extends BaseModelImpl<CommercePriceListChannelRel>
	implements CommercePriceListChannelRelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce price list channel rel model instance should use the <code>CommercePriceListChannelRel</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommercePriceListChannelRel";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR},
		{"CommercePriceListChannelRelId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"commerceChannelId", Types.BIGINT},
		{"commercePriceListId", Types.BIGINT}, {"order_", Types.INTEGER},
		{"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CommercePriceListChannelRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commerceChannelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commercePriceListId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("order_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommercePriceListChannelRel (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,CommercePriceListChannelRelId LONG not null,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceChannelId LONG,commercePriceListId LONG,order_ INTEGER,lastPublishDate DATE null,primary key (CommercePriceListChannelRelId, ctCollectionId))";

	public static final String TABLE_SQL_DROP =
		"drop table CommercePriceListChannelRel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commercePriceListChannelRel.order ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommercePriceListChannelRel.order_ ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMMERCECHANNELID_COLUMN_BITMASK = 1L;

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
	public static final long ORDER_COLUMN_BITMASK = 16L;

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

	public CommercePriceListChannelRelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _CommercePriceListChannelRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommercePriceListChannelRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _CommercePriceListChannelRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommercePriceListChannelRel.class;
	}

	@Override
	public String getModelClassName() {
		return CommercePriceListChannelRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommercePriceListChannelRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommercePriceListChannelRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommercePriceListChannelRel, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(CommercePriceListChannelRel)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommercePriceListChannelRel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommercePriceListChannelRel, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommercePriceListChannelRel)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommercePriceListChannelRel, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommercePriceListChannelRel, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map
			<String, Function<CommercePriceListChannelRel, Object>>
				_attributeGetterFunctions;

		static {
			Map<String, Function<CommercePriceListChannelRel, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String,
						 Function<CommercePriceListChannelRel, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", CommercePriceListChannelRel::getMvccVersion);
			attributeGetterFunctions.put(
				"ctCollectionId",
				CommercePriceListChannelRel::getCtCollectionId);
			attributeGetterFunctions.put(
				"uuid", CommercePriceListChannelRel::getUuid);
			attributeGetterFunctions.put(
				"CommercePriceListChannelRelId",
				CommercePriceListChannelRel::getCommercePriceListChannelRelId);
			attributeGetterFunctions.put(
				"companyId", CommercePriceListChannelRel::getCompanyId);
			attributeGetterFunctions.put(
				"userId", CommercePriceListChannelRel::getUserId);
			attributeGetterFunctions.put(
				"userName", CommercePriceListChannelRel::getUserName);
			attributeGetterFunctions.put(
				"createDate", CommercePriceListChannelRel::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", CommercePriceListChannelRel::getModifiedDate);
			attributeGetterFunctions.put(
				"commerceChannelId",
				CommercePriceListChannelRel::getCommerceChannelId);
			attributeGetterFunctions.put(
				"commercePriceListId",
				CommercePriceListChannelRel::getCommercePriceListId);
			attributeGetterFunctions.put(
				"order", CommercePriceListChannelRel::getOrder);
			attributeGetterFunctions.put(
				"lastPublishDate",
				CommercePriceListChannelRel::getLastPublishDate);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<CommercePriceListChannelRel, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<CommercePriceListChannelRel, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<CommercePriceListChannelRel, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<CommercePriceListChannelRel, Long>)
					CommercePriceListChannelRel::setMvccVersion);
			attributeSetterBiConsumers.put(
				"ctCollectionId",
				(BiConsumer<CommercePriceListChannelRel, Long>)
					CommercePriceListChannelRel::setCtCollectionId);
			attributeSetterBiConsumers.put(
				"uuid",
				(BiConsumer<CommercePriceListChannelRel, String>)
					CommercePriceListChannelRel::setUuid);
			attributeSetterBiConsumers.put(
				"CommercePriceListChannelRelId",
				(BiConsumer<CommercePriceListChannelRel, Long>)
					CommercePriceListChannelRel::
						setCommercePriceListChannelRelId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<CommercePriceListChannelRel, Long>)
					CommercePriceListChannelRel::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<CommercePriceListChannelRel, Long>)
					CommercePriceListChannelRel::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<CommercePriceListChannelRel, String>)
					CommercePriceListChannelRel::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<CommercePriceListChannelRel, Date>)
					CommercePriceListChannelRel::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<CommercePriceListChannelRel, Date>)
					CommercePriceListChannelRel::setModifiedDate);
			attributeSetterBiConsumers.put(
				"commerceChannelId",
				(BiConsumer<CommercePriceListChannelRel, Long>)
					CommercePriceListChannelRel::setCommerceChannelId);
			attributeSetterBiConsumers.put(
				"commercePriceListId",
				(BiConsumer<CommercePriceListChannelRel, Long>)
					CommercePriceListChannelRel::setCommercePriceListId);
			attributeSetterBiConsumers.put(
				"order",
				(BiConsumer<CommercePriceListChannelRel, Integer>)
					CommercePriceListChannelRel::setOrder);
			attributeSetterBiConsumers.put(
				"lastPublishDate",
				(BiConsumer<CommercePriceListChannelRel, Date>)
					CommercePriceListChannelRel::setLastPublishDate);

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
	public long getCommercePriceListChannelRelId() {
		return _CommercePriceListChannelRelId;
	}

	@Override
	public void setCommercePriceListChannelRelId(
		long CommercePriceListChannelRelId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_CommercePriceListChannelRelId = CommercePriceListChannelRelId;
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
	public long getCommerceChannelId() {
		return _commerceChannelId;
	}

	@Override
	public void setCommerceChannelId(long commerceChannelId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceChannelId = commerceChannelId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCommerceChannelId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("commerceChannelId"));
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
	public int getOrder() {
		return _order;
	}

	@Override
	public void setOrder(int order) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_order = order;
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
				CommercePriceListChannelRel.class.getName()));
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
			getCompanyId(), CommercePriceListChannelRel.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommercePriceListChannelRel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommercePriceListChannelRel>
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
		CommercePriceListChannelRelImpl commercePriceListChannelRelImpl =
			new CommercePriceListChannelRelImpl();

		commercePriceListChannelRelImpl.setMvccVersion(getMvccVersion());
		commercePriceListChannelRelImpl.setCtCollectionId(getCtCollectionId());
		commercePriceListChannelRelImpl.setUuid(getUuid());
		commercePriceListChannelRelImpl.setCommercePriceListChannelRelId(
			getCommercePriceListChannelRelId());
		commercePriceListChannelRelImpl.setCompanyId(getCompanyId());
		commercePriceListChannelRelImpl.setUserId(getUserId());
		commercePriceListChannelRelImpl.setUserName(getUserName());
		commercePriceListChannelRelImpl.setCreateDate(getCreateDate());
		commercePriceListChannelRelImpl.setModifiedDate(getModifiedDate());
		commercePriceListChannelRelImpl.setCommerceChannelId(
			getCommerceChannelId());
		commercePriceListChannelRelImpl.setCommercePriceListId(
			getCommercePriceListId());
		commercePriceListChannelRelImpl.setOrder(getOrder());
		commercePriceListChannelRelImpl.setLastPublishDate(
			getLastPublishDate());

		commercePriceListChannelRelImpl.resetOriginalValues();

		return commercePriceListChannelRelImpl;
	}

	@Override
	public CommercePriceListChannelRel cloneWithOriginalValues() {
		CommercePriceListChannelRelImpl commercePriceListChannelRelImpl =
			new CommercePriceListChannelRelImpl();

		commercePriceListChannelRelImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		commercePriceListChannelRelImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		commercePriceListChannelRelImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		commercePriceListChannelRelImpl.setCommercePriceListChannelRelId(
			this.<Long>getColumnOriginalValue("CommercePriceListChannelRelId"));
		commercePriceListChannelRelImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		commercePriceListChannelRelImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		commercePriceListChannelRelImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		commercePriceListChannelRelImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		commercePriceListChannelRelImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		commercePriceListChannelRelImpl.setCommerceChannelId(
			this.<Long>getColumnOriginalValue("commerceChannelId"));
		commercePriceListChannelRelImpl.setCommercePriceListId(
			this.<Long>getColumnOriginalValue("commercePriceListId"));
		commercePriceListChannelRelImpl.setOrder(
			this.<Integer>getColumnOriginalValue("order_"));
		commercePriceListChannelRelImpl.setLastPublishDate(
			this.<Date>getColumnOriginalValue("lastPublishDate"));

		return commercePriceListChannelRelImpl;
	}

	@Override
	public int compareTo(
		CommercePriceListChannelRel commercePriceListChannelRel) {

		int value = 0;

		if (getOrder() < commercePriceListChannelRel.getOrder()) {
			value = -1;
		}
		else if (getOrder() > commercePriceListChannelRel.getOrder()) {
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

		if (!(object instanceof CommercePriceListChannelRel)) {
			return false;
		}

		CommercePriceListChannelRel commercePriceListChannelRel =
			(CommercePriceListChannelRel)object;

		long primaryKey = commercePriceListChannelRel.getPrimaryKey();

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
	public CacheModel<CommercePriceListChannelRel> toCacheModel() {
		CommercePriceListChannelRelCacheModel
			commercePriceListChannelRelCacheModel =
				new CommercePriceListChannelRelCacheModel();

		commercePriceListChannelRelCacheModel.mvccVersion = getMvccVersion();

		commercePriceListChannelRelCacheModel.ctCollectionId =
			getCtCollectionId();

		commercePriceListChannelRelCacheModel.uuid = getUuid();

		String uuid = commercePriceListChannelRelCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			commercePriceListChannelRelCacheModel.uuid = null;
		}

		commercePriceListChannelRelCacheModel.CommercePriceListChannelRelId =
			getCommercePriceListChannelRelId();

		commercePriceListChannelRelCacheModel.companyId = getCompanyId();

		commercePriceListChannelRelCacheModel.userId = getUserId();

		commercePriceListChannelRelCacheModel.userName = getUserName();

		String userName = commercePriceListChannelRelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commercePriceListChannelRelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commercePriceListChannelRelCacheModel.createDate =
				createDate.getTime();
		}
		else {
			commercePriceListChannelRelCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commercePriceListChannelRelCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commercePriceListChannelRelCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commercePriceListChannelRelCacheModel.commerceChannelId =
			getCommerceChannelId();

		commercePriceListChannelRelCacheModel.commercePriceListId =
			getCommercePriceListId();

		commercePriceListChannelRelCacheModel.order = getOrder();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			commercePriceListChannelRelCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			commercePriceListChannelRelCacheModel.lastPublishDate =
				Long.MIN_VALUE;
		}

		return commercePriceListChannelRelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommercePriceListChannelRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommercePriceListChannelRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommercePriceListChannelRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(CommercePriceListChannelRel)this);

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
			<InvocationHandler, CommercePriceListChannelRel>
				_escapedModelProxyProviderFunction =
					ProxyUtil.getProxyProviderFunction(
						CommercePriceListChannelRel.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private long _CommercePriceListChannelRelId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceChannelId;
	private long _commercePriceListId;
	private int _order;
	private Date _lastPublishDate;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CommercePriceListChannelRel, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommercePriceListChannelRel)this);
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
			"CommercePriceListChannelRelId", _CommercePriceListChannelRelId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("commerceChannelId", _commerceChannelId);
		_columnOriginalValues.put("commercePriceListId", _commercePriceListId);
		_columnOriginalValues.put("order_", _order);
		_columnOriginalValues.put("lastPublishDate", _lastPublishDate);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");
		attributeNames.put("order_", "order");

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

		columnBitmasks.put("CommercePriceListChannelRelId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("userId", 32L);

		columnBitmasks.put("userName", 64L);

		columnBitmasks.put("createDate", 128L);

		columnBitmasks.put("modifiedDate", 256L);

		columnBitmasks.put("commerceChannelId", 512L);

		columnBitmasks.put("commercePriceListId", 1024L);

		columnBitmasks.put("order_", 2048L);

		columnBitmasks.put("lastPublishDate", 4096L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommercePriceListChannelRel _escapedModel;

}