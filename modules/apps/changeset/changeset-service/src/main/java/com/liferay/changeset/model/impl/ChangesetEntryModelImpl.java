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

package com.liferay.changeset.model.impl;

import com.liferay.changeset.model.ChangesetEntry;
import com.liferay.changeset.model.ChangesetEntryModel;
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
 * The base model implementation for the ChangesetEntry service. Represents a row in the &quot;ChangesetEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>ChangesetEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ChangesetEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ChangesetEntryImpl
 * @generated
 */
public class ChangesetEntryModelImpl
	extends BaseModelImpl<ChangesetEntry> implements ChangesetEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a changeset entry model instance should use the <code>ChangesetEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "ChangesetEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"changesetEntryId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"changesetCollectionId", Types.BIGINT}, {"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("changesetEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("changesetCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table ChangesetEntry (changesetEntryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,changesetCollectionId LONG,classNameId LONG,classPK LONG)";

	public static final String TABLE_SQL_DROP = "drop table ChangesetEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY changesetEntry.changesetEntryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY ChangesetEntry.changesetEntryId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CHANGESETCOLLECTIONID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CLASSNAMEID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CLASSPK_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CHANGESETENTRYID_COLUMN_BITMASK = 32L;

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

	public ChangesetEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _changesetEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setChangesetEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _changesetEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ChangesetEntry.class;
	}

	@Override
	public String getModelClassName() {
		return ChangesetEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ChangesetEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ChangesetEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ChangesetEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ChangesetEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ChangesetEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ChangesetEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ChangesetEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ChangesetEntry, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ChangesetEntry, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<ChangesetEntry, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<ChangesetEntry, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<ChangesetEntry, Object>>();

			attributeGetterFunctions.put(
				"changesetEntryId", ChangesetEntry::getChangesetEntryId);
			attributeGetterFunctions.put("groupId", ChangesetEntry::getGroupId);
			attributeGetterFunctions.put(
				"companyId", ChangesetEntry::getCompanyId);
			attributeGetterFunctions.put("userId", ChangesetEntry::getUserId);
			attributeGetterFunctions.put(
				"userName", ChangesetEntry::getUserName);
			attributeGetterFunctions.put(
				"createDate", ChangesetEntry::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", ChangesetEntry::getModifiedDate);
			attributeGetterFunctions.put(
				"changesetCollectionId",
				ChangesetEntry::getChangesetCollectionId);
			attributeGetterFunctions.put(
				"classNameId", ChangesetEntry::getClassNameId);
			attributeGetterFunctions.put("classPK", ChangesetEntry::getClassPK);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<ChangesetEntry, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<ChangesetEntry, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap<String, BiConsumer<ChangesetEntry, ?>>();

			attributeSetterBiConsumers.put(
				"changesetEntryId",
				(BiConsumer<ChangesetEntry, Long>)
					ChangesetEntry::setChangesetEntryId);
			attributeSetterBiConsumers.put(
				"groupId",
				(BiConsumer<ChangesetEntry, Long>)ChangesetEntry::setGroupId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<ChangesetEntry, Long>)ChangesetEntry::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<ChangesetEntry, Long>)ChangesetEntry::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<ChangesetEntry, String>)
					ChangesetEntry::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<ChangesetEntry, Date>)
					ChangesetEntry::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<ChangesetEntry, Date>)
					ChangesetEntry::setModifiedDate);
			attributeSetterBiConsumers.put(
				"changesetCollectionId",
				(BiConsumer<ChangesetEntry, Long>)
					ChangesetEntry::setChangesetCollectionId);
			attributeSetterBiConsumers.put(
				"classNameId",
				(BiConsumer<ChangesetEntry, Long>)
					ChangesetEntry::setClassNameId);
			attributeSetterBiConsumers.put(
				"classPK",
				(BiConsumer<ChangesetEntry, Long>)ChangesetEntry::setClassPK);

			_attributeSetterBiConsumers = Collections.unmodifiableMap(
				(Map)attributeSetterBiConsumers);
		}

	}

	@Override
	public long getChangesetEntryId() {
		return _changesetEntryId;
	}

	@Override
	public void setChangesetEntryId(long changesetEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_changesetEntryId = changesetEntryId;
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
	public long getChangesetCollectionId() {
		return _changesetCollectionId;
	}

	@Override
	public void setChangesetCollectionId(long changesetCollectionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_changesetCollectionId = changesetCollectionId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalChangesetCollectionId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("changesetCollectionId"));
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
			getCompanyId(), ChangesetEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ChangesetEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, ChangesetEntry>
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
		ChangesetEntryImpl changesetEntryImpl = new ChangesetEntryImpl();

		changesetEntryImpl.setChangesetEntryId(getChangesetEntryId());
		changesetEntryImpl.setGroupId(getGroupId());
		changesetEntryImpl.setCompanyId(getCompanyId());
		changesetEntryImpl.setUserId(getUserId());
		changesetEntryImpl.setUserName(getUserName());
		changesetEntryImpl.setCreateDate(getCreateDate());
		changesetEntryImpl.setModifiedDate(getModifiedDate());
		changesetEntryImpl.setChangesetCollectionId(getChangesetCollectionId());
		changesetEntryImpl.setClassNameId(getClassNameId());
		changesetEntryImpl.setClassPK(getClassPK());

		changesetEntryImpl.resetOriginalValues();

		return changesetEntryImpl;
	}

	@Override
	public ChangesetEntry cloneWithOriginalValues() {
		ChangesetEntryImpl changesetEntryImpl = new ChangesetEntryImpl();

		changesetEntryImpl.setChangesetEntryId(
			this.<Long>getColumnOriginalValue("changesetEntryId"));
		changesetEntryImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		changesetEntryImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		changesetEntryImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		changesetEntryImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		changesetEntryImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		changesetEntryImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		changesetEntryImpl.setChangesetCollectionId(
			this.<Long>getColumnOriginalValue("changesetCollectionId"));
		changesetEntryImpl.setClassNameId(
			this.<Long>getColumnOriginalValue("classNameId"));
		changesetEntryImpl.setClassPK(
			this.<Long>getColumnOriginalValue("classPK"));

		return changesetEntryImpl;
	}

	@Override
	public int compareTo(ChangesetEntry changesetEntry) {
		long primaryKey = changesetEntry.getPrimaryKey();

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

		if (!(object instanceof ChangesetEntry)) {
			return false;
		}

		ChangesetEntry changesetEntry = (ChangesetEntry)object;

		long primaryKey = changesetEntry.getPrimaryKey();

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
	public CacheModel<ChangesetEntry> toCacheModel() {
		ChangesetEntryCacheModel changesetEntryCacheModel =
			new ChangesetEntryCacheModel();

		changesetEntryCacheModel.changesetEntryId = getChangesetEntryId();

		changesetEntryCacheModel.groupId = getGroupId();

		changesetEntryCacheModel.companyId = getCompanyId();

		changesetEntryCacheModel.userId = getUserId();

		changesetEntryCacheModel.userName = getUserName();

		String userName = changesetEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			changesetEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			changesetEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			changesetEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			changesetEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			changesetEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		changesetEntryCacheModel.changesetCollectionId =
			getChangesetCollectionId();

		changesetEntryCacheModel.classNameId = getClassNameId();

		changesetEntryCacheModel.classPK = getClassPK();

		return changesetEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ChangesetEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ChangesetEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ChangesetEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((ChangesetEntry)this);

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

		private static final Function<InvocationHandler, ChangesetEntry>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					ChangesetEntry.class, ModelWrapper.class);

	}

	private long _changesetEntryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _changesetCollectionId;
	private long _classNameId;
	private long _classPK;

	public <T> T getColumnValue(String columnName) {
		Function<ChangesetEntry, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((ChangesetEntry)this);
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

		_columnOriginalValues.put("changesetEntryId", _changesetEntryId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put(
			"changesetCollectionId", _changesetCollectionId);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("classPK", _classPK);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("changesetEntryId", 1L);

		columnBitmasks.put("groupId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("userId", 8L);

		columnBitmasks.put("userName", 16L);

		columnBitmasks.put("createDate", 32L);

		columnBitmasks.put("modifiedDate", 64L);

		columnBitmasks.put("changesetCollectionId", 128L);

		columnBitmasks.put("classNameId", 256L);

		columnBitmasks.put("classPK", 512L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private ChangesetEntry _escapedModel;

}