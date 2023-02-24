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
import com.liferay.portal.tools.service.builder.test.model.LVEntryLocalization;
import com.liferay.portal.tools.service.builder.test.model.LVEntryLocalizationVersion;
import com.liferay.portal.tools.service.builder.test.model.LVEntryLocalizationVersionModel;

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
 * The base model implementation for the LVEntryLocalizationVersion service. Represents a row in the &quot;LVEntryLocalizationVersion&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>LVEntryLocalizationVersionModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LVEntryLocalizationVersionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LVEntryLocalizationVersionImpl
 * @generated
 */
public class LVEntryLocalizationVersionModelImpl
	extends BaseModelImpl<LVEntryLocalizationVersion>
	implements LVEntryLocalizationVersionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a lv entry localization version model instance should use the <code>LVEntryLocalizationVersion</code> interface instead.
	 */
	public static final String TABLE_NAME = "LVEntryLocalizationVersion";

	public static final Object[][] TABLE_COLUMNS = {
		{"lvEntryLocalizationVersionId", Types.BIGINT},
		{"version", Types.INTEGER}, {"lvEntryLocalizationId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"lvEntryId", Types.BIGINT},
		{"languageId", Types.VARCHAR}, {"title", Types.VARCHAR},
		{"content", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("lvEntryLocalizationVersionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("version", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("lvEntryLocalizationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("lvEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("languageId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("content", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table LVEntryLocalizationVersion (lvEntryLocalizationVersionId LONG not null primary key,version INTEGER,lvEntryLocalizationId LONG,companyId LONG,lvEntryId LONG,languageId VARCHAR(75) null,title VARCHAR(75) null,content VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table LVEntryLocalizationVersion";

	public static final String ORDER_BY_JPQL =
		" ORDER BY lvEntryLocalizationVersion.version DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY LVEntryLocalizationVersion.version DESC";

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
	public static final long LANGUAGEID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long LVENTRYID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long LVENTRYLOCALIZATIONID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long VERSION_COLUMN_BITMASK = 8L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.tools.service.builder.test.service.util.ServiceProps.
			get(
				"lock.expiration.time.com.liferay.portal.tools.service.builder.test.model.LVEntryLocalizationVersion"));

	public LVEntryLocalizationVersionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _lvEntryLocalizationVersionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLvEntryLocalizationVersionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _lvEntryLocalizationVersionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return LVEntryLocalizationVersion.class;
	}

	@Override
	public String getModelClassName() {
		return LVEntryLocalizationVersion.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<LVEntryLocalizationVersion, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<LVEntryLocalizationVersion, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LVEntryLocalizationVersion, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(LVEntryLocalizationVersion)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<LVEntryLocalizationVersion, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<LVEntryLocalizationVersion, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(LVEntryLocalizationVersion)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<LVEntryLocalizationVersion, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<LVEntryLocalizationVersion, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map
			<String, Function<LVEntryLocalizationVersion, Object>>
				_attributeGetterFunctions;

		static {
			Map<String, Function<LVEntryLocalizationVersion, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String,
						 Function<LVEntryLocalizationVersion, Object>>();

			attributeGetterFunctions.put(
				"lvEntryLocalizationVersionId",
				LVEntryLocalizationVersion::getLvEntryLocalizationVersionId);
			attributeGetterFunctions.put(
				"version", LVEntryLocalizationVersion::getVersion);
			attributeGetterFunctions.put(
				"lvEntryLocalizationId",
				LVEntryLocalizationVersion::getLvEntryLocalizationId);
			attributeGetterFunctions.put(
				"companyId", LVEntryLocalizationVersion::getCompanyId);
			attributeGetterFunctions.put(
				"lvEntryId", LVEntryLocalizationVersion::getLvEntryId);
			attributeGetterFunctions.put(
				"languageId", LVEntryLocalizationVersion::getLanguageId);
			attributeGetterFunctions.put(
				"title", LVEntryLocalizationVersion::getTitle);
			attributeGetterFunctions.put(
				"content", LVEntryLocalizationVersion::getContent);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<LVEntryLocalizationVersion, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<LVEntryLocalizationVersion, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<LVEntryLocalizationVersion, ?>>();

			attributeSetterBiConsumers.put(
				"lvEntryLocalizationVersionId",
				(BiConsumer<LVEntryLocalizationVersion, Long>)
					LVEntryLocalizationVersion::
						setLvEntryLocalizationVersionId);
			attributeSetterBiConsumers.put(
				"version",
				(BiConsumer<LVEntryLocalizationVersion, Integer>)
					LVEntryLocalizationVersion::setVersion);
			attributeSetterBiConsumers.put(
				"lvEntryLocalizationId",
				(BiConsumer<LVEntryLocalizationVersion, Long>)
					LVEntryLocalizationVersion::setLvEntryLocalizationId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<LVEntryLocalizationVersion, Long>)
					LVEntryLocalizationVersion::setCompanyId);
			attributeSetterBiConsumers.put(
				"lvEntryId",
				(BiConsumer<LVEntryLocalizationVersion, Long>)
					LVEntryLocalizationVersion::setLvEntryId);
			attributeSetterBiConsumers.put(
				"languageId",
				(BiConsumer<LVEntryLocalizationVersion, String>)
					LVEntryLocalizationVersion::setLanguageId);
			attributeSetterBiConsumers.put(
				"title",
				(BiConsumer<LVEntryLocalizationVersion, String>)
					LVEntryLocalizationVersion::setTitle);
			attributeSetterBiConsumers.put(
				"content",
				(BiConsumer<LVEntryLocalizationVersion, String>)
					LVEntryLocalizationVersion::setContent);

			_attributeSetterBiConsumers = Collections.unmodifiableMap(
				(Map)attributeSetterBiConsumers);
		}

	}

	@Override
	public long getVersionedModelId() {
		return getLvEntryLocalizationId();
	}

	@Override
	public void populateVersionedModel(
		LVEntryLocalization lvEntryLocalization) {

		lvEntryLocalization.setCompanyId(getCompanyId());
		lvEntryLocalization.setLvEntryId(getLvEntryId());
		lvEntryLocalization.setLanguageId(getLanguageId());
		lvEntryLocalization.setTitle(getTitle());
		lvEntryLocalization.setContent(getContent());
	}

	@Override
	public void setVersionedModelId(long lvEntryLocalizationId) {
		setLvEntryLocalizationId(lvEntryLocalizationId);
	}

	@Override
	public LVEntryLocalization toVersionedModel() {
		LVEntryLocalization lvEntryLocalization = new LVEntryLocalizationImpl();

		lvEntryLocalization.setPrimaryKey(getVersionedModelId());
		lvEntryLocalization.setHeadId(-getVersionedModelId());

		populateVersionedModel(lvEntryLocalization);

		return lvEntryLocalization;
	}

	@Override
	public long getLvEntryLocalizationVersionId() {
		return _lvEntryLocalizationVersionId;
	}

	@Override
	public void setLvEntryLocalizationVersionId(
		long lvEntryLocalizationVersionId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_lvEntryLocalizationVersionId = lvEntryLocalizationVersionId;
	}

	@Override
	public int getVersion() {
		return _version;
	}

	@Override
	public void setVersion(int version) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_version = version;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalVersion() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("version"));
	}

	@Override
	public long getLvEntryLocalizationId() {
		return _lvEntryLocalizationId;
	}

	@Override
	public void setLvEntryLocalizationId(long lvEntryLocalizationId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_lvEntryLocalizationId = lvEntryLocalizationId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalLvEntryLocalizationId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("lvEntryLocalizationId"));
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

	@Override
	public long getLvEntryId() {
		return _lvEntryId;
	}

	@Override
	public void setLvEntryId(long lvEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_lvEntryId = lvEntryId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalLvEntryId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("lvEntryId"));
	}

	@Override
	public String getLanguageId() {
		if (_languageId == null) {
			return "";
		}
		else {
			return _languageId;
		}
	}

	@Override
	public void setLanguageId(String languageId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_languageId = languageId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalLanguageId() {
		return getColumnOriginalValue("languageId");
	}

	@Override
	public String getTitle() {
		if (_title == null) {
			return "";
		}
		else {
			return _title;
		}
	}

	@Override
	public void setTitle(String title) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_title = title;
	}

	@Override
	public String getContent() {
		if (_content == null) {
			return "";
		}
		else {
			return _content;
		}
	}

	@Override
	public void setContent(String content) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_content = content;
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
			getCompanyId(), LVEntryLocalizationVersion.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public LVEntryLocalizationVersion toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, LVEntryLocalizationVersion>
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
		LVEntryLocalizationVersionImpl lvEntryLocalizationVersionImpl =
			new LVEntryLocalizationVersionImpl();

		lvEntryLocalizationVersionImpl.setLvEntryLocalizationVersionId(
			getLvEntryLocalizationVersionId());
		lvEntryLocalizationVersionImpl.setVersion(getVersion());
		lvEntryLocalizationVersionImpl.setLvEntryLocalizationId(
			getLvEntryLocalizationId());
		lvEntryLocalizationVersionImpl.setCompanyId(getCompanyId());
		lvEntryLocalizationVersionImpl.setLvEntryId(getLvEntryId());
		lvEntryLocalizationVersionImpl.setLanguageId(getLanguageId());
		lvEntryLocalizationVersionImpl.setTitle(getTitle());
		lvEntryLocalizationVersionImpl.setContent(getContent());

		lvEntryLocalizationVersionImpl.resetOriginalValues();

		return lvEntryLocalizationVersionImpl;
	}

	@Override
	public LVEntryLocalizationVersion cloneWithOriginalValues() {
		LVEntryLocalizationVersionImpl lvEntryLocalizationVersionImpl =
			new LVEntryLocalizationVersionImpl();

		lvEntryLocalizationVersionImpl.setLvEntryLocalizationVersionId(
			this.<Long>getColumnOriginalValue("lvEntryLocalizationVersionId"));
		lvEntryLocalizationVersionImpl.setVersion(
			this.<Integer>getColumnOriginalValue("version"));
		lvEntryLocalizationVersionImpl.setLvEntryLocalizationId(
			this.<Long>getColumnOriginalValue("lvEntryLocalizationId"));
		lvEntryLocalizationVersionImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		lvEntryLocalizationVersionImpl.setLvEntryId(
			this.<Long>getColumnOriginalValue("lvEntryId"));
		lvEntryLocalizationVersionImpl.setLanguageId(
			this.<String>getColumnOriginalValue("languageId"));
		lvEntryLocalizationVersionImpl.setTitle(
			this.<String>getColumnOriginalValue("title"));
		lvEntryLocalizationVersionImpl.setContent(
			this.<String>getColumnOriginalValue("content"));

		return lvEntryLocalizationVersionImpl;
	}

	@Override
	public int compareTo(
		LVEntryLocalizationVersion lvEntryLocalizationVersion) {

		int value = 0;

		if (getVersion() < lvEntryLocalizationVersion.getVersion()) {
			value = -1;
		}
		else if (getVersion() > lvEntryLocalizationVersion.getVersion()) {
			value = 1;
		}
		else {
			value = 0;
		}

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

		if (!(object instanceof LVEntryLocalizationVersion)) {
			return false;
		}

		LVEntryLocalizationVersion lvEntryLocalizationVersion =
			(LVEntryLocalizationVersion)object;

		long primaryKey = lvEntryLocalizationVersion.getPrimaryKey();

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
	public CacheModel<LVEntryLocalizationVersion> toCacheModel() {
		LVEntryLocalizationVersionCacheModel
			lvEntryLocalizationVersionCacheModel =
				new LVEntryLocalizationVersionCacheModel();

		lvEntryLocalizationVersionCacheModel.lvEntryLocalizationVersionId =
			getLvEntryLocalizationVersionId();

		lvEntryLocalizationVersionCacheModel.version = getVersion();

		lvEntryLocalizationVersionCacheModel.lvEntryLocalizationId =
			getLvEntryLocalizationId();

		lvEntryLocalizationVersionCacheModel.companyId = getCompanyId();

		lvEntryLocalizationVersionCacheModel.lvEntryId = getLvEntryId();

		lvEntryLocalizationVersionCacheModel.languageId = getLanguageId();

		String languageId = lvEntryLocalizationVersionCacheModel.languageId;

		if ((languageId != null) && (languageId.length() == 0)) {
			lvEntryLocalizationVersionCacheModel.languageId = null;
		}

		lvEntryLocalizationVersionCacheModel.title = getTitle();

		String title = lvEntryLocalizationVersionCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			lvEntryLocalizationVersionCacheModel.title = null;
		}

		lvEntryLocalizationVersionCacheModel.content = getContent();

		String content = lvEntryLocalizationVersionCacheModel.content;

		if ((content != null) && (content.length() == 0)) {
			lvEntryLocalizationVersionCacheModel.content = null;
		}

		return lvEntryLocalizationVersionCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<LVEntryLocalizationVersion, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<LVEntryLocalizationVersion, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LVEntryLocalizationVersion, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(LVEntryLocalizationVersion)this);

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
			<InvocationHandler, LVEntryLocalizationVersion>
				_escapedModelProxyProviderFunction =
					ProxyUtil.getProxyProviderFunction(
						LVEntryLocalizationVersion.class, ModelWrapper.class);

	}

	private long _lvEntryLocalizationVersionId;
	private int _version;
	private long _lvEntryLocalizationId;
	private long _companyId;
	private long _lvEntryId;
	private String _languageId;
	private String _title;
	private String _content;

	public <T> T getColumnValue(String columnName) {
		Function<LVEntryLocalizationVersion, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((LVEntryLocalizationVersion)this);
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
			"lvEntryLocalizationVersionId", _lvEntryLocalizationVersionId);
		_columnOriginalValues.put("version", _version);
		_columnOriginalValues.put(
			"lvEntryLocalizationId", _lvEntryLocalizationId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("lvEntryId", _lvEntryId);
		_columnOriginalValues.put("languageId", _languageId);
		_columnOriginalValues.put("title", _title);
		_columnOriginalValues.put("content", _content);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("lvEntryLocalizationVersionId", 1L);

		columnBitmasks.put("version", 2L);

		columnBitmasks.put("lvEntryLocalizationId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("lvEntryId", 16L);

		columnBitmasks.put("languageId", 32L);

		columnBitmasks.put("title", 64L);

		columnBitmasks.put("content", 128L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private LVEntryLocalizationVersion _escapedModel;

}