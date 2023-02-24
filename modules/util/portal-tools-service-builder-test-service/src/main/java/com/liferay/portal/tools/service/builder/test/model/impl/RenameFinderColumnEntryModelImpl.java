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

package com.liferay.portal.tools.service.builder.test.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.tools.service.builder.test.model.RenameFinderColumnEntry;
import com.liferay.portal.tools.service.builder.test.model.RenameFinderColumnEntryModel;

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
 * The base model implementation for the RenameFinderColumnEntry service. Represents a row in the &quot;RenameFinderColumnEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>RenameFinderColumnEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link RenameFinderColumnEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RenameFinderColumnEntryImpl
 * @generated
 */
public class RenameFinderColumnEntryModelImpl
	extends BaseModelImpl<RenameFinderColumnEntry>
	implements RenameFinderColumnEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a rename finder column entry model instance should use the <code>RenameFinderColumnEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "RenameFinderColumnEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"renameFinderColumnEntryId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"columnToRename", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("renameFinderColumnEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("columnToRename", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table RenameFinderColumnEntry (renameFinderColumnEntryId LONG not null primary key,groupId LONG,columnToRename VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table RenameFinderColumnEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY renameFinderColumnEntry.renameFinderColumnEntryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY RenameFinderColumnEntry.renameFinderColumnEntryId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean ENTITY_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean FINDER_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean COLUMN_BITMASK_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COLUMNTORENAME_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long RENAMEFINDERCOLUMNENTRYID_COLUMN_BITMASK = 2L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.tools.service.builder.test.service.util.ServiceProps.
			get(
				"lock.expiration.time.com.liferay.portal.tools.service.builder.test.model.RenameFinderColumnEntry"));

	public RenameFinderColumnEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _renameFinderColumnEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setRenameFinderColumnEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _renameFinderColumnEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return RenameFinderColumnEntry.class;
	}

	@Override
	public String getModelClassName() {
		return RenameFinderColumnEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<RenameFinderColumnEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<RenameFinderColumnEntry, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<RenameFinderColumnEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((RenameFinderColumnEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<RenameFinderColumnEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<RenameFinderColumnEntry, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(RenameFinderColumnEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<RenameFinderColumnEntry, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<RenameFinderColumnEntry, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map
			<String, Function<RenameFinderColumnEntry, Object>>
				_attributeGetterFunctions;

		static {
			Map<String, Function<RenameFinderColumnEntry, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<RenameFinderColumnEntry, Object>>();

			attributeGetterFunctions.put(
				"renameFinderColumnEntryId",
				RenameFinderColumnEntry::getRenameFinderColumnEntryId);
			attributeGetterFunctions.put(
				"groupId", RenameFinderColumnEntry::getGroupId);
			attributeGetterFunctions.put(
				"columnToRename", RenameFinderColumnEntry::getColumnToRename);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<RenameFinderColumnEntry, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<RenameFinderColumnEntry, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<RenameFinderColumnEntry, ?>>();

			attributeSetterBiConsumers.put(
				"renameFinderColumnEntryId",
				(BiConsumer<RenameFinderColumnEntry, Long>)
					RenameFinderColumnEntry::setRenameFinderColumnEntryId);
			attributeSetterBiConsumers.put(
				"groupId",
				(BiConsumer<RenameFinderColumnEntry, Long>)
					RenameFinderColumnEntry::setGroupId);
			attributeSetterBiConsumers.put(
				"columnToRename",
				(BiConsumer<RenameFinderColumnEntry, String>)
					RenameFinderColumnEntry::setColumnToRename);

			_attributeSetterBiConsumers = Collections.unmodifiableMap(
				(Map)attributeSetterBiConsumers);
		}

	}

	@Override
	public long getRenameFinderColumnEntryId() {
		return _renameFinderColumnEntryId;
	}

	@Override
	public void setRenameFinderColumnEntryId(long renameFinderColumnEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_renameFinderColumnEntryId = renameFinderColumnEntryId;
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

	@Override
	public String getColumnToRename() {
		if (_columnToRename == null) {
			return "";
		}
		else {
			return _columnToRename;
		}
	}

	@Override
	public void setColumnToRename(String columnToRename) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_columnToRename = columnToRename;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalColumnToRename() {
		return getColumnOriginalValue("columnToRename");
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
			0, RenameFinderColumnEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public RenameFinderColumnEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, RenameFinderColumnEntry>
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
		RenameFinderColumnEntryImpl renameFinderColumnEntryImpl =
			new RenameFinderColumnEntryImpl();

		renameFinderColumnEntryImpl.setRenameFinderColumnEntryId(
			getRenameFinderColumnEntryId());
		renameFinderColumnEntryImpl.setGroupId(getGroupId());
		renameFinderColumnEntryImpl.setColumnToRename(getColumnToRename());

		renameFinderColumnEntryImpl.resetOriginalValues();

		return renameFinderColumnEntryImpl;
	}

	@Override
	public RenameFinderColumnEntry cloneWithOriginalValues() {
		RenameFinderColumnEntryImpl renameFinderColumnEntryImpl =
			new RenameFinderColumnEntryImpl();

		renameFinderColumnEntryImpl.setRenameFinderColumnEntryId(
			this.<Long>getColumnOriginalValue("renameFinderColumnEntryId"));
		renameFinderColumnEntryImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		renameFinderColumnEntryImpl.setColumnToRename(
			this.<String>getColumnOriginalValue("columnToRename"));

		return renameFinderColumnEntryImpl;
	}

	@Override
	public int compareTo(RenameFinderColumnEntry renameFinderColumnEntry) {
		long primaryKey = renameFinderColumnEntry.getPrimaryKey();

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

		if (!(object instanceof RenameFinderColumnEntry)) {
			return false;
		}

		RenameFinderColumnEntry renameFinderColumnEntry =
			(RenameFinderColumnEntry)object;

		long primaryKey = renameFinderColumnEntry.getPrimaryKey();

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
		return ENTITY_CACHE_ENABLED;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<RenameFinderColumnEntry> toCacheModel() {
		RenameFinderColumnEntryCacheModel renameFinderColumnEntryCacheModel =
			new RenameFinderColumnEntryCacheModel();

		renameFinderColumnEntryCacheModel.renameFinderColumnEntryId =
			getRenameFinderColumnEntryId();

		renameFinderColumnEntryCacheModel.groupId = getGroupId();

		renameFinderColumnEntryCacheModel.columnToRename = getColumnToRename();

		String columnToRename =
			renameFinderColumnEntryCacheModel.columnToRename;

		if ((columnToRename != null) && (columnToRename.length() == 0)) {
			renameFinderColumnEntryCacheModel.columnToRename = null;
		}

		return renameFinderColumnEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<RenameFinderColumnEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<RenameFinderColumnEntry, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<RenameFinderColumnEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(RenameFinderColumnEntry)this);

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
			<InvocationHandler, RenameFinderColumnEntry>
				_escapedModelProxyProviderFunction =
					ProxyUtil.getProxyProviderFunction(
						RenameFinderColumnEntry.class, ModelWrapper.class);

	}

	private long _renameFinderColumnEntryId;
	private long _groupId;
	private String _columnToRename;

	public <T> T getColumnValue(String columnName) {
		Function<RenameFinderColumnEntry, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((RenameFinderColumnEntry)this);
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

		_columnOriginalValues.put(
			"renameFinderColumnEntryId", _renameFinderColumnEntryId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("columnToRename", _columnToRename);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("renameFinderColumnEntryId", 1L);

		columnBitmasks.put("groupId", 2L);

		columnBitmasks.put("columnToRename", 4L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private RenameFinderColumnEntry _escapedModel;

}