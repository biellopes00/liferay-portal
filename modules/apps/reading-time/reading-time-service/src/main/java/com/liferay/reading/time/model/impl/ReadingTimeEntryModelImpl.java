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

package com.liferay.reading.time.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.reading.time.model.ReadingTimeEntry;
import com.liferay.reading.time.model.ReadingTimeEntryModel;

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
 * The base model implementation for the ReadingTimeEntry service. Represents a row in the &quot;ReadingTimeEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>ReadingTimeEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ReadingTimeEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReadingTimeEntryImpl
 * @generated
 */
@JSON(strict = true)
public class ReadingTimeEntryModelImpl
	extends BaseModelImpl<ReadingTimeEntry> implements ReadingTimeEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a reading time entry model instance should use the <code>ReadingTimeEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "ReadingTimeEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR}, {"readingTimeEntryId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"classNameId", Types.BIGINT}, {"classPK", Types.BIGINT},
		{"readingTime", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("readingTimeEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("readingTime", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table ReadingTimeEntry (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,readingTimeEntryId LONG not null,groupId LONG,companyId LONG,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG,readingTime LONG,primary key (readingTimeEntryId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table ReadingTimeEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY readingTimeEntry.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY ReadingTimeEntry.createDate DESC";

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
	public static final long CLASSPK_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CREATEDATE_COLUMN_BITMASK = 32L;

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

	public ReadingTimeEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _readingTimeEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setReadingTimeEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _readingTimeEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ReadingTimeEntry.class;
	}

	@Override
	public String getModelClassName() {
		return ReadingTimeEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ReadingTimeEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ReadingTimeEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ReadingTimeEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ReadingTimeEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ReadingTimeEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ReadingTimeEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ReadingTimeEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ReadingTimeEntry, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ReadingTimeEntry, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<ReadingTimeEntry, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<ReadingTimeEntry, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<ReadingTimeEntry, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", ReadingTimeEntry::getMvccVersion);
			attributeGetterFunctions.put(
				"ctCollectionId", ReadingTimeEntry::getCtCollectionId);
			attributeGetterFunctions.put("uuid", ReadingTimeEntry::getUuid);
			attributeGetterFunctions.put(
				"readingTimeEntryId", ReadingTimeEntry::getReadingTimeEntryId);
			attributeGetterFunctions.put(
				"groupId", ReadingTimeEntry::getGroupId);
			attributeGetterFunctions.put(
				"companyId", ReadingTimeEntry::getCompanyId);
			attributeGetterFunctions.put(
				"createDate", ReadingTimeEntry::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", ReadingTimeEntry::getModifiedDate);
			attributeGetterFunctions.put(
				"classNameId", ReadingTimeEntry::getClassNameId);
			attributeGetterFunctions.put(
				"classPK", ReadingTimeEntry::getClassPK);
			attributeGetterFunctions.put(
				"readingTime", ReadingTimeEntry::getReadingTime);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<ReadingTimeEntry, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<ReadingTimeEntry, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<ReadingTimeEntry, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<ReadingTimeEntry, Long>)
					ReadingTimeEntry::setMvccVersion);
			attributeSetterBiConsumers.put(
				"ctCollectionId",
				(BiConsumer<ReadingTimeEntry, Long>)
					ReadingTimeEntry::setCtCollectionId);
			attributeSetterBiConsumers.put(
				"uuid",
				(BiConsumer<ReadingTimeEntry, String>)
					ReadingTimeEntry::setUuid);
			attributeSetterBiConsumers.put(
				"readingTimeEntryId",
				(BiConsumer<ReadingTimeEntry, Long>)
					ReadingTimeEntry::setReadingTimeEntryId);
			attributeSetterBiConsumers.put(
				"groupId",
				(BiConsumer<ReadingTimeEntry, Long>)
					ReadingTimeEntry::setGroupId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<ReadingTimeEntry, Long>)
					ReadingTimeEntry::setCompanyId);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<ReadingTimeEntry, Date>)
					ReadingTimeEntry::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<ReadingTimeEntry, Date>)
					ReadingTimeEntry::setModifiedDate);
			attributeSetterBiConsumers.put(
				"classNameId",
				(BiConsumer<ReadingTimeEntry, Long>)
					ReadingTimeEntry::setClassNameId);
			attributeSetterBiConsumers.put(
				"classPK",
				(BiConsumer<ReadingTimeEntry, Long>)
					ReadingTimeEntry::setClassPK);
			attributeSetterBiConsumers.put(
				"readingTime",
				(BiConsumer<ReadingTimeEntry, Long>)
					ReadingTimeEntry::setReadingTime);

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
	public long getReadingTimeEntryId() {
		return _readingTimeEntryId;
	}

	@Override
	public void setReadingTimeEntryId(long readingTimeEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_readingTimeEntryId = readingTimeEntryId;
	}

	@JSON
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

	@JSON
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

	@JSON
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

	@JSON
	@Override
	public long getReadingTime() {
		return _readingTime;
	}

	@Override
	public void setReadingTime(long readingTime) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_readingTime = readingTime;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(ReadingTimeEntry.class.getName()),
			getClassNameId());
	}

	@Override
	public int getStatus() {
		return 0;
	}

	@Override
	public long getTrashEntryClassPK() {
		return getPrimaryKey();
	}

	@Override
	public boolean isInTrash() {
		if (getStatus() == WorkflowConstants.STATUS_IN_TRASH) {
			return true;
		}
		else {
			return false;
		}
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
			getCompanyId(), ReadingTimeEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ReadingTimeEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, ReadingTimeEntry>
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
		ReadingTimeEntryImpl readingTimeEntryImpl = new ReadingTimeEntryImpl();

		readingTimeEntryImpl.setMvccVersion(getMvccVersion());
		readingTimeEntryImpl.setCtCollectionId(getCtCollectionId());
		readingTimeEntryImpl.setUuid(getUuid());
		readingTimeEntryImpl.setReadingTimeEntryId(getReadingTimeEntryId());
		readingTimeEntryImpl.setGroupId(getGroupId());
		readingTimeEntryImpl.setCompanyId(getCompanyId());
		readingTimeEntryImpl.setCreateDate(getCreateDate());
		readingTimeEntryImpl.setModifiedDate(getModifiedDate());
		readingTimeEntryImpl.setClassNameId(getClassNameId());
		readingTimeEntryImpl.setClassPK(getClassPK());
		readingTimeEntryImpl.setReadingTime(getReadingTime());

		readingTimeEntryImpl.resetOriginalValues();

		return readingTimeEntryImpl;
	}

	@Override
	public ReadingTimeEntry cloneWithOriginalValues() {
		ReadingTimeEntryImpl readingTimeEntryImpl = new ReadingTimeEntryImpl();

		readingTimeEntryImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		readingTimeEntryImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		readingTimeEntryImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		readingTimeEntryImpl.setReadingTimeEntryId(
			this.<Long>getColumnOriginalValue("readingTimeEntryId"));
		readingTimeEntryImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		readingTimeEntryImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		readingTimeEntryImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		readingTimeEntryImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		readingTimeEntryImpl.setClassNameId(
			this.<Long>getColumnOriginalValue("classNameId"));
		readingTimeEntryImpl.setClassPK(
			this.<Long>getColumnOriginalValue("classPK"));
		readingTimeEntryImpl.setReadingTime(
			this.<Long>getColumnOriginalValue("readingTime"));

		return readingTimeEntryImpl;
	}

	@Override
	public int compareTo(ReadingTimeEntry readingTimeEntry) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), readingTimeEntry.getCreateDate());

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

		if (!(object instanceof ReadingTimeEntry)) {
			return false;
		}

		ReadingTimeEntry readingTimeEntry = (ReadingTimeEntry)object;

		long primaryKey = readingTimeEntry.getPrimaryKey();

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
	public CacheModel<ReadingTimeEntry> toCacheModel() {
		ReadingTimeEntryCacheModel readingTimeEntryCacheModel =
			new ReadingTimeEntryCacheModel();

		readingTimeEntryCacheModel.mvccVersion = getMvccVersion();

		readingTimeEntryCacheModel.ctCollectionId = getCtCollectionId();

		readingTimeEntryCacheModel.uuid = getUuid();

		String uuid = readingTimeEntryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			readingTimeEntryCacheModel.uuid = null;
		}

		readingTimeEntryCacheModel.readingTimeEntryId = getReadingTimeEntryId();

		readingTimeEntryCacheModel.groupId = getGroupId();

		readingTimeEntryCacheModel.companyId = getCompanyId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			readingTimeEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			readingTimeEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			readingTimeEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			readingTimeEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		readingTimeEntryCacheModel.classNameId = getClassNameId();

		readingTimeEntryCacheModel.classPK = getClassPK();

		readingTimeEntryCacheModel.readingTime = getReadingTime();

		return readingTimeEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ReadingTimeEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ReadingTimeEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ReadingTimeEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(ReadingTimeEntry)this);

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

		private static final Function<InvocationHandler, ReadingTimeEntry>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					ReadingTimeEntry.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private long _readingTimeEntryId;
	private long _groupId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _classNameId;
	private long _classPK;
	private long _readingTime;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<ReadingTimeEntry, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((ReadingTimeEntry)this);
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
		_columnOriginalValues.put("readingTimeEntryId", _readingTimeEntryId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("classPK", _classPK);
		_columnOriginalValues.put("readingTime", _readingTime);
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

		columnBitmasks.put("ctCollectionId", 2L);

		columnBitmasks.put("uuid_", 4L);

		columnBitmasks.put("readingTimeEntryId", 8L);

		columnBitmasks.put("groupId", 16L);

		columnBitmasks.put("companyId", 32L);

		columnBitmasks.put("createDate", 64L);

		columnBitmasks.put("modifiedDate", 128L);

		columnBitmasks.put("classNameId", 256L);

		columnBitmasks.put("classPK", 512L);

		columnBitmasks.put("readingTime", 1024L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private ReadingTimeEntry _escapedModel;

}