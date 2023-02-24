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
import com.liferay.portal.tools.service.builder.test.model.NullConvertibleEntry;
import com.liferay.portal.tools.service.builder.test.model.NullConvertibleEntryModel;

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
 * The base model implementation for the NullConvertibleEntry service. Represents a row in the &quot;NullConvertibleEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>NullConvertibleEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link NullConvertibleEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NullConvertibleEntryImpl
 * @generated
 */
public class NullConvertibleEntryModelImpl
	extends BaseModelImpl<NullConvertibleEntry>
	implements NullConvertibleEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a null convertible entry model instance should use the <code>NullConvertibleEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "NullConvertibleEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"nullConvertibleEntryId", Types.BIGINT}, {"name", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("nullConvertibleEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table NullConvertibleEntry (nullConvertibleEntryId LONG not null primary key,name VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table NullConvertibleEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY nullConvertibleEntry.nullConvertibleEntryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY NullConvertibleEntry.nullConvertibleEntryId ASC";

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
	public static final long NAME_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long NULLCONVERTIBLEENTRYID_COLUMN_BITMASK = 2L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.tools.service.builder.test.service.util.ServiceProps.
			get(
				"lock.expiration.time.com.liferay.portal.tools.service.builder.test.model.NullConvertibleEntry"));

	public NullConvertibleEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _nullConvertibleEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setNullConvertibleEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _nullConvertibleEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return NullConvertibleEntry.class;
	}

	@Override
	public String getModelClassName() {
		return NullConvertibleEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<NullConvertibleEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<NullConvertibleEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<NullConvertibleEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((NullConvertibleEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<NullConvertibleEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<NullConvertibleEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(NullConvertibleEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<NullConvertibleEntry, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<NullConvertibleEntry, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<NullConvertibleEntry, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<NullConvertibleEntry, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<NullConvertibleEntry, Object>>();

			attributeGetterFunctions.put(
				"nullConvertibleEntryId",
				NullConvertibleEntry::getNullConvertibleEntryId);
			attributeGetterFunctions.put("name", NullConvertibleEntry::getName);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<NullConvertibleEntry, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<NullConvertibleEntry, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<NullConvertibleEntry, ?>>();

			attributeSetterBiConsumers.put(
				"nullConvertibleEntryId",
				(BiConsumer<NullConvertibleEntry, Long>)
					NullConvertibleEntry::setNullConvertibleEntryId);
			attributeSetterBiConsumers.put(
				"name",
				(BiConsumer<NullConvertibleEntry, String>)
					NullConvertibleEntry::setName);

			_attributeSetterBiConsumers = Collections.unmodifiableMap(
				(Map)attributeSetterBiConsumers);
		}

	}

	@Override
	public long getNullConvertibleEntryId() {
		return _nullConvertibleEntryId;
	}

	@Override
	public void setNullConvertibleEntryId(long nullConvertibleEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_nullConvertibleEntryId = nullConvertibleEntryId;
	}

	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_name = name;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalName() {
		return getColumnOriginalValue("name");
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
			0, NullConvertibleEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public NullConvertibleEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, NullConvertibleEntry>
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
		NullConvertibleEntryImpl nullConvertibleEntryImpl =
			new NullConvertibleEntryImpl();

		nullConvertibleEntryImpl.setNullConvertibleEntryId(
			getNullConvertibleEntryId());
		nullConvertibleEntryImpl.setName(getName());

		nullConvertibleEntryImpl.resetOriginalValues();

		return nullConvertibleEntryImpl;
	}

	@Override
	public NullConvertibleEntry cloneWithOriginalValues() {
		NullConvertibleEntryImpl nullConvertibleEntryImpl =
			new NullConvertibleEntryImpl();

		nullConvertibleEntryImpl.setNullConvertibleEntryId(
			this.<Long>getColumnOriginalValue("nullConvertibleEntryId"));
		nullConvertibleEntryImpl.setName(
			this.<String>getColumnOriginalValue("name"));

		return nullConvertibleEntryImpl;
	}

	@Override
	public int compareTo(NullConvertibleEntry nullConvertibleEntry) {
		long primaryKey = nullConvertibleEntry.getPrimaryKey();

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

		if (!(object instanceof NullConvertibleEntry)) {
			return false;
		}

		NullConvertibleEntry nullConvertibleEntry =
			(NullConvertibleEntry)object;

		long primaryKey = nullConvertibleEntry.getPrimaryKey();

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
	public CacheModel<NullConvertibleEntry> toCacheModel() {
		NullConvertibleEntryCacheModel nullConvertibleEntryCacheModel =
			new NullConvertibleEntryCacheModel();

		nullConvertibleEntryCacheModel.nullConvertibleEntryId =
			getNullConvertibleEntryId();

		nullConvertibleEntryCacheModel.name = getName();

		String name = nullConvertibleEntryCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			nullConvertibleEntryCacheModel.name = null;
		}

		return nullConvertibleEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<NullConvertibleEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<NullConvertibleEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<NullConvertibleEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(NullConvertibleEntry)this);

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

		private static final Function<InvocationHandler, NullConvertibleEntry>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					NullConvertibleEntry.class, ModelWrapper.class);

	}

	private long _nullConvertibleEntryId;
	private String _name;

	public <T> T getColumnValue(String columnName) {
		Function<NullConvertibleEntry, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((NullConvertibleEntry)this);
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
			"nullConvertibleEntryId", _nullConvertibleEntryId);
		_columnOriginalValues.put("name", _name);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("nullConvertibleEntryId", 1L);

		columnBitmasks.put("name", 2L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private NullConvertibleEntry _escapedModel;

}