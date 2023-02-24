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

package com.liferay.document.library.model.impl;

import com.liferay.document.library.model.DLFileVersionPreview;
import com.liferay.document.library.model.DLFileVersionPreviewModel;
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
 * The base model implementation for the DLFileVersionPreview service. Represents a row in the &quot;DLFileVersionPreview&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>DLFileVersionPreviewModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DLFileVersionPreviewImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLFileVersionPreviewImpl
 * @generated
 */
public class DLFileVersionPreviewModelImpl
	extends BaseModelImpl<DLFileVersionPreview>
	implements DLFileVersionPreviewModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a dl file version preview model instance should use the <code>DLFileVersionPreview</code> interface instead.
	 */
	public static final String TABLE_NAME = "DLFileVersionPreview";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"dlFileVersionPreviewId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"fileEntryId", Types.BIGINT},
		{"fileVersionId", Types.BIGINT}, {"previewStatus", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("dlFileVersionPreviewId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fileVersionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("previewStatus", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table DLFileVersionPreview (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,dlFileVersionPreviewId LONG not null,groupId LONG,companyId LONG,fileEntryId LONG,fileVersionId LONG,previewStatus INTEGER,primary key (dlFileVersionPreviewId, ctCollectionId))";

	public static final String TABLE_SQL_DROP =
		"drop table DLFileVersionPreview";

	public static final String ORDER_BY_JPQL =
		" ORDER BY dlFileVersionPreview.dlFileVersionPreviewId DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY DLFileVersionPreview.dlFileVersionPreviewId DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long FILEENTRYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long FILEVERSIONID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long PREVIEWSTATUS_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long DLFILEVERSIONPREVIEWID_COLUMN_BITMASK = 8L;

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

	public DLFileVersionPreviewModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _dlFileVersionPreviewId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setDlFileVersionPreviewId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dlFileVersionPreviewId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DLFileVersionPreview.class;
	}

	@Override
	public String getModelClassName() {
		return DLFileVersionPreview.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<DLFileVersionPreview, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<DLFileVersionPreview, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DLFileVersionPreview, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((DLFileVersionPreview)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<DLFileVersionPreview, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<DLFileVersionPreview, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(DLFileVersionPreview)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<DLFileVersionPreview, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<DLFileVersionPreview, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<DLFileVersionPreview, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<DLFileVersionPreview, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<DLFileVersionPreview, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", DLFileVersionPreview::getMvccVersion);
			attributeGetterFunctions.put(
				"ctCollectionId", DLFileVersionPreview::getCtCollectionId);
			attributeGetterFunctions.put(
				"dlFileVersionPreviewId",
				DLFileVersionPreview::getDlFileVersionPreviewId);
			attributeGetterFunctions.put(
				"groupId", DLFileVersionPreview::getGroupId);
			attributeGetterFunctions.put(
				"companyId", DLFileVersionPreview::getCompanyId);
			attributeGetterFunctions.put(
				"fileEntryId", DLFileVersionPreview::getFileEntryId);
			attributeGetterFunctions.put(
				"fileVersionId", DLFileVersionPreview::getFileVersionId);
			attributeGetterFunctions.put(
				"previewStatus", DLFileVersionPreview::getPreviewStatus);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<DLFileVersionPreview, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<DLFileVersionPreview, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<DLFileVersionPreview, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<DLFileVersionPreview, Long>)
					DLFileVersionPreview::setMvccVersion);
			attributeSetterBiConsumers.put(
				"ctCollectionId",
				(BiConsumer<DLFileVersionPreview, Long>)
					DLFileVersionPreview::setCtCollectionId);
			attributeSetterBiConsumers.put(
				"dlFileVersionPreviewId",
				(BiConsumer<DLFileVersionPreview, Long>)
					DLFileVersionPreview::setDlFileVersionPreviewId);
			attributeSetterBiConsumers.put(
				"groupId",
				(BiConsumer<DLFileVersionPreview, Long>)
					DLFileVersionPreview::setGroupId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<DLFileVersionPreview, Long>)
					DLFileVersionPreview::setCompanyId);
			attributeSetterBiConsumers.put(
				"fileEntryId",
				(BiConsumer<DLFileVersionPreview, Long>)
					DLFileVersionPreview::setFileEntryId);
			attributeSetterBiConsumers.put(
				"fileVersionId",
				(BiConsumer<DLFileVersionPreview, Long>)
					DLFileVersionPreview::setFileVersionId);
			attributeSetterBiConsumers.put(
				"previewStatus",
				(BiConsumer<DLFileVersionPreview, Integer>)
					DLFileVersionPreview::setPreviewStatus);

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
	public long getDlFileVersionPreviewId() {
		return _dlFileVersionPreviewId;
	}

	@Override
	public void setDlFileVersionPreviewId(long dlFileVersionPreviewId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_dlFileVersionPreviewId = dlFileVersionPreviewId;
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
	public long getFileEntryId() {
		return _fileEntryId;
	}

	@Override
	public void setFileEntryId(long fileEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_fileEntryId = fileEntryId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalFileEntryId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("fileEntryId"));
	}

	@Override
	public long getFileVersionId() {
		return _fileVersionId;
	}

	@Override
	public void setFileVersionId(long fileVersionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_fileVersionId = fileVersionId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalFileVersionId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("fileVersionId"));
	}

	@Override
	public int getPreviewStatus() {
		return _previewStatus;
	}

	@Override
	public void setPreviewStatus(int previewStatus) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_previewStatus = previewStatus;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalPreviewStatus() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("previewStatus"));
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
			getCompanyId(), DLFileVersionPreview.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public DLFileVersionPreview toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, DLFileVersionPreview>
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
		DLFileVersionPreviewImpl dlFileVersionPreviewImpl =
			new DLFileVersionPreviewImpl();

		dlFileVersionPreviewImpl.setMvccVersion(getMvccVersion());
		dlFileVersionPreviewImpl.setCtCollectionId(getCtCollectionId());
		dlFileVersionPreviewImpl.setDlFileVersionPreviewId(
			getDlFileVersionPreviewId());
		dlFileVersionPreviewImpl.setGroupId(getGroupId());
		dlFileVersionPreviewImpl.setCompanyId(getCompanyId());
		dlFileVersionPreviewImpl.setFileEntryId(getFileEntryId());
		dlFileVersionPreviewImpl.setFileVersionId(getFileVersionId());
		dlFileVersionPreviewImpl.setPreviewStatus(getPreviewStatus());

		dlFileVersionPreviewImpl.resetOriginalValues();

		return dlFileVersionPreviewImpl;
	}

	@Override
	public DLFileVersionPreview cloneWithOriginalValues() {
		DLFileVersionPreviewImpl dlFileVersionPreviewImpl =
			new DLFileVersionPreviewImpl();

		dlFileVersionPreviewImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		dlFileVersionPreviewImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		dlFileVersionPreviewImpl.setDlFileVersionPreviewId(
			this.<Long>getColumnOriginalValue("dlFileVersionPreviewId"));
		dlFileVersionPreviewImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		dlFileVersionPreviewImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		dlFileVersionPreviewImpl.setFileEntryId(
			this.<Long>getColumnOriginalValue("fileEntryId"));
		dlFileVersionPreviewImpl.setFileVersionId(
			this.<Long>getColumnOriginalValue("fileVersionId"));
		dlFileVersionPreviewImpl.setPreviewStatus(
			this.<Integer>getColumnOriginalValue("previewStatus"));

		return dlFileVersionPreviewImpl;
	}

	@Override
	public int compareTo(DLFileVersionPreview dlFileVersionPreview) {
		int value = 0;

		if (getDlFileVersionPreviewId() <
				dlFileVersionPreview.getDlFileVersionPreviewId()) {

			value = -1;
		}
		else if (getDlFileVersionPreviewId() >
					dlFileVersionPreview.getDlFileVersionPreviewId()) {

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

		if (!(object instanceof DLFileVersionPreview)) {
			return false;
		}

		DLFileVersionPreview dlFileVersionPreview =
			(DLFileVersionPreview)object;

		long primaryKey = dlFileVersionPreview.getPrimaryKey();

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

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<DLFileVersionPreview> toCacheModel() {
		DLFileVersionPreviewCacheModel dlFileVersionPreviewCacheModel =
			new DLFileVersionPreviewCacheModel();

		dlFileVersionPreviewCacheModel.mvccVersion = getMvccVersion();

		dlFileVersionPreviewCacheModel.ctCollectionId = getCtCollectionId();

		dlFileVersionPreviewCacheModel.dlFileVersionPreviewId =
			getDlFileVersionPreviewId();

		dlFileVersionPreviewCacheModel.groupId = getGroupId();

		dlFileVersionPreviewCacheModel.companyId = getCompanyId();

		dlFileVersionPreviewCacheModel.fileEntryId = getFileEntryId();

		dlFileVersionPreviewCacheModel.fileVersionId = getFileVersionId();

		dlFileVersionPreviewCacheModel.previewStatus = getPreviewStatus();

		return dlFileVersionPreviewCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<DLFileVersionPreview, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<DLFileVersionPreview, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DLFileVersionPreview, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(DLFileVersionPreview)this);

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

		private static final Function<InvocationHandler, DLFileVersionPreview>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					DLFileVersionPreview.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _dlFileVersionPreviewId;
	private long _groupId;
	private long _companyId;
	private long _fileEntryId;
	private long _fileVersionId;
	private int _previewStatus;

	public <T> T getColumnValue(String columnName) {
		Function<DLFileVersionPreview, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((DLFileVersionPreview)this);
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
		_columnOriginalValues.put(
			"dlFileVersionPreviewId", _dlFileVersionPreviewId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("fileEntryId", _fileEntryId);
		_columnOriginalValues.put("fileVersionId", _fileVersionId);
		_columnOriginalValues.put("previewStatus", _previewStatus);
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

		columnBitmasks.put("dlFileVersionPreviewId", 4L);

		columnBitmasks.put("groupId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("fileEntryId", 32L);

		columnBitmasks.put("fileVersionId", 64L);

		columnBitmasks.put("previewStatus", 128L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private DLFileVersionPreview _escapedModel;

}