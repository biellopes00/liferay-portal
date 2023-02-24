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

package com.liferay.frontend.view.state.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.frontend.view.state.model.FVSActiveEntry;
import com.liferay.frontend.view.state.model.FVSActiveEntryModel;
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
 * The base model implementation for the FVSActiveEntry service. Represents a row in the &quot;FVSActiveEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>FVSActiveEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link FVSActiveEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FVSActiveEntryImpl
 * @generated
 */
public class FVSActiveEntryModelImpl
	extends BaseModelImpl<FVSActiveEntry> implements FVSActiveEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a fvs active entry model instance should use the <code>FVSActiveEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "FVSActiveEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"fvsActiveEntryId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"fvsEntryId", Types.BIGINT}, {"clayDataSetDisplayId", Types.VARCHAR},
		{"plid", Types.BIGINT}, {"portletId", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fvsActiveEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("fvsEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("clayDataSetDisplayId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("plid", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("portletId", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table FVSActiveEntry (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,fvsActiveEntryId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,fvsEntryId LONG,clayDataSetDisplayId VARCHAR(75) null,plid LONG,portletId VARCHAR(200) null)";

	public static final String TABLE_SQL_DROP = "drop table FVSActiveEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY fvsActiveEntry.fvsActiveEntryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY FVSActiveEntry.fvsActiveEntryId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CLAYDATASETDISPLAYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long PLID_COLUMN_BITMASK = 4L;

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
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long FVSACTIVEENTRYID_COLUMN_BITMASK = 64L;

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

	public FVSActiveEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _fvsActiveEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setFvsActiveEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _fvsActiveEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return FVSActiveEntry.class;
	}

	@Override
	public String getModelClassName() {
		return FVSActiveEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<FVSActiveEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<FVSActiveEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FVSActiveEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((FVSActiveEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<FVSActiveEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<FVSActiveEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(FVSActiveEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<FVSActiveEntry, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<FVSActiveEntry, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<FVSActiveEntry, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<FVSActiveEntry, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<FVSActiveEntry, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", FVSActiveEntry::getMvccVersion);
			attributeGetterFunctions.put("uuid", FVSActiveEntry::getUuid);
			attributeGetterFunctions.put(
				"fvsActiveEntryId", FVSActiveEntry::getFvsActiveEntryId);
			attributeGetterFunctions.put(
				"companyId", FVSActiveEntry::getCompanyId);
			attributeGetterFunctions.put("userId", FVSActiveEntry::getUserId);
			attributeGetterFunctions.put(
				"userName", FVSActiveEntry::getUserName);
			attributeGetterFunctions.put(
				"createDate", FVSActiveEntry::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", FVSActiveEntry::getModifiedDate);
			attributeGetterFunctions.put(
				"fvsEntryId", FVSActiveEntry::getFvsEntryId);
			attributeGetterFunctions.put(
				"clayDataSetDisplayId",
				FVSActiveEntry::getClayDataSetDisplayId);
			attributeGetterFunctions.put("plid", FVSActiveEntry::getPlid);
			attributeGetterFunctions.put(
				"portletId", FVSActiveEntry::getPortletId);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<FVSActiveEntry, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<FVSActiveEntry, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap<String, BiConsumer<FVSActiveEntry, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<FVSActiveEntry, Long>)
					FVSActiveEntry::setMvccVersion);
			attributeSetterBiConsumers.put(
				"uuid",
				(BiConsumer<FVSActiveEntry, String>)FVSActiveEntry::setUuid);
			attributeSetterBiConsumers.put(
				"fvsActiveEntryId",
				(BiConsumer<FVSActiveEntry, Long>)
					FVSActiveEntry::setFvsActiveEntryId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<FVSActiveEntry, Long>)FVSActiveEntry::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<FVSActiveEntry, Long>)FVSActiveEntry::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<FVSActiveEntry, String>)
					FVSActiveEntry::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<FVSActiveEntry, Date>)
					FVSActiveEntry::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<FVSActiveEntry, Date>)
					FVSActiveEntry::setModifiedDate);
			attributeSetterBiConsumers.put(
				"fvsEntryId",
				(BiConsumer<FVSActiveEntry, Long>)
					FVSActiveEntry::setFvsEntryId);
			attributeSetterBiConsumers.put(
				"clayDataSetDisplayId",
				(BiConsumer<FVSActiveEntry, String>)
					FVSActiveEntry::setClayDataSetDisplayId);
			attributeSetterBiConsumers.put(
				"plid",
				(BiConsumer<FVSActiveEntry, Long>)FVSActiveEntry::setPlid);
			attributeSetterBiConsumers.put(
				"portletId",
				(BiConsumer<FVSActiveEntry, String>)
					FVSActiveEntry::setPortletId);

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
	public long getFvsActiveEntryId() {
		return _fvsActiveEntryId;
	}

	@Override
	public void setFvsActiveEntryId(long fvsActiveEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_fvsActiveEntryId = fvsActiveEntryId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalUserId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("userId"));
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
	public long getFvsEntryId() {
		return _fvsEntryId;
	}

	@Override
	public void setFvsEntryId(long fvsEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_fvsEntryId = fvsEntryId;
	}

	@Override
	public String getClayDataSetDisplayId() {
		if (_clayDataSetDisplayId == null) {
			return "";
		}
		else {
			return _clayDataSetDisplayId;
		}
	}

	@Override
	public void setClayDataSetDisplayId(String clayDataSetDisplayId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_clayDataSetDisplayId = clayDataSetDisplayId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalClayDataSetDisplayId() {
		return getColumnOriginalValue("clayDataSetDisplayId");
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
	public long getContainerModelId() {
		return getFvsActiveEntryId();
	}

	@Override
	public void setContainerModelId(long containerModelId) {
		_fvsActiveEntryId = containerModelId;
	}

	@Override
	public String getContainerModelName() {
		return String.valueOf(getContainerModelId());
	}

	@Override
	public long getParentContainerModelId() {
		return 0;
	}

	@Override
	public void setParentContainerModelId(long parentContainerModelId) {
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(FVSActiveEntry.class.getName()));
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
			getCompanyId(), FVSActiveEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public FVSActiveEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, FVSActiveEntry>
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
		FVSActiveEntryImpl fvsActiveEntryImpl = new FVSActiveEntryImpl();

		fvsActiveEntryImpl.setMvccVersion(getMvccVersion());
		fvsActiveEntryImpl.setUuid(getUuid());
		fvsActiveEntryImpl.setFvsActiveEntryId(getFvsActiveEntryId());
		fvsActiveEntryImpl.setCompanyId(getCompanyId());
		fvsActiveEntryImpl.setUserId(getUserId());
		fvsActiveEntryImpl.setUserName(getUserName());
		fvsActiveEntryImpl.setCreateDate(getCreateDate());
		fvsActiveEntryImpl.setModifiedDate(getModifiedDate());
		fvsActiveEntryImpl.setFvsEntryId(getFvsEntryId());
		fvsActiveEntryImpl.setClayDataSetDisplayId(getClayDataSetDisplayId());
		fvsActiveEntryImpl.setPlid(getPlid());
		fvsActiveEntryImpl.setPortletId(getPortletId());

		fvsActiveEntryImpl.resetOriginalValues();

		return fvsActiveEntryImpl;
	}

	@Override
	public FVSActiveEntry cloneWithOriginalValues() {
		FVSActiveEntryImpl fvsActiveEntryImpl = new FVSActiveEntryImpl();

		fvsActiveEntryImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		fvsActiveEntryImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		fvsActiveEntryImpl.setFvsActiveEntryId(
			this.<Long>getColumnOriginalValue("fvsActiveEntryId"));
		fvsActiveEntryImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		fvsActiveEntryImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		fvsActiveEntryImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		fvsActiveEntryImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		fvsActiveEntryImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		fvsActiveEntryImpl.setFvsEntryId(
			this.<Long>getColumnOriginalValue("fvsEntryId"));
		fvsActiveEntryImpl.setClayDataSetDisplayId(
			this.<String>getColumnOriginalValue("clayDataSetDisplayId"));
		fvsActiveEntryImpl.setPlid(this.<Long>getColumnOriginalValue("plid"));
		fvsActiveEntryImpl.setPortletId(
			this.<String>getColumnOriginalValue("portletId"));

		return fvsActiveEntryImpl;
	}

	@Override
	public int compareTo(FVSActiveEntry fvsActiveEntry) {
		long primaryKey = fvsActiveEntry.getPrimaryKey();

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

		if (!(object instanceof FVSActiveEntry)) {
			return false;
		}

		FVSActiveEntry fvsActiveEntry = (FVSActiveEntry)object;

		long primaryKey = fvsActiveEntry.getPrimaryKey();

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
	public CacheModel<FVSActiveEntry> toCacheModel() {
		FVSActiveEntryCacheModel fvsActiveEntryCacheModel =
			new FVSActiveEntryCacheModel();

		fvsActiveEntryCacheModel.mvccVersion = getMvccVersion();

		fvsActiveEntryCacheModel.uuid = getUuid();

		String uuid = fvsActiveEntryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			fvsActiveEntryCacheModel.uuid = null;
		}

		fvsActiveEntryCacheModel.fvsActiveEntryId = getFvsActiveEntryId();

		fvsActiveEntryCacheModel.companyId = getCompanyId();

		fvsActiveEntryCacheModel.userId = getUserId();

		fvsActiveEntryCacheModel.userName = getUserName();

		String userName = fvsActiveEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			fvsActiveEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			fvsActiveEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			fvsActiveEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			fvsActiveEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			fvsActiveEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		fvsActiveEntryCacheModel.fvsEntryId = getFvsEntryId();

		fvsActiveEntryCacheModel.clayDataSetDisplayId =
			getClayDataSetDisplayId();

		String clayDataSetDisplayId =
			fvsActiveEntryCacheModel.clayDataSetDisplayId;

		if ((clayDataSetDisplayId != null) &&
			(clayDataSetDisplayId.length() == 0)) {

			fvsActiveEntryCacheModel.clayDataSetDisplayId = null;
		}

		fvsActiveEntryCacheModel.plid = getPlid();

		fvsActiveEntryCacheModel.portletId = getPortletId();

		String portletId = fvsActiveEntryCacheModel.portletId;

		if ((portletId != null) && (portletId.length() == 0)) {
			fvsActiveEntryCacheModel.portletId = null;
		}

		return fvsActiveEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<FVSActiveEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<FVSActiveEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FVSActiveEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((FVSActiveEntry)this);

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

		private static final Function<InvocationHandler, FVSActiveEntry>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					FVSActiveEntry.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private String _uuid;
	private long _fvsActiveEntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _fvsEntryId;
	private String _clayDataSetDisplayId;
	private long _plid;
	private String _portletId;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<FVSActiveEntry, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((FVSActiveEntry)this);
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
		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put("fvsActiveEntryId", _fvsActiveEntryId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("fvsEntryId", _fvsEntryId);
		_columnOriginalValues.put(
			"clayDataSetDisplayId", _clayDataSetDisplayId);
		_columnOriginalValues.put("plid", _plid);
		_columnOriginalValues.put("portletId", _portletId);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");

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

		columnBitmasks.put("uuid_", 2L);

		columnBitmasks.put("fvsActiveEntryId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("userId", 16L);

		columnBitmasks.put("userName", 32L);

		columnBitmasks.put("createDate", 64L);

		columnBitmasks.put("modifiedDate", 128L);

		columnBitmasks.put("fvsEntryId", 256L);

		columnBitmasks.put("clayDataSetDisplayId", 512L);

		columnBitmasks.put("plid", 1024L);

		columnBitmasks.put("portletId", 2048L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private FVSActiveEntry _escapedModel;

}