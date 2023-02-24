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

package com.liferay.portal.workflow.kaleo.model.impl;

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
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoNotification;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationModel;

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
 * The base model implementation for the KaleoNotification service. Represents a row in the &quot;KaleoNotification&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>KaleoNotificationModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link KaleoNotificationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoNotificationImpl
 * @generated
 */
public class KaleoNotificationModelImpl
	extends BaseModelImpl<KaleoNotification> implements KaleoNotificationModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a kaleo notification model instance should use the <code>KaleoNotification</code> interface instead.
	 */
	public static final String TABLE_NAME = "KaleoNotification";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"kaleoNotificationId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"kaleoClassName", Types.VARCHAR},
		{"kaleoClassPK", Types.BIGINT}, {"kaleoDefinitionId", Types.BIGINT},
		{"kaleoDefinitionVersionId", Types.BIGINT},
		{"kaleoNodeName", Types.VARCHAR}, {"name", Types.VARCHAR},
		{"description", Types.VARCHAR}, {"executionType", Types.VARCHAR},
		{"template", Types.CLOB}, {"templateLanguage", Types.VARCHAR},
		{"notificationTypes", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoNotificationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("kaleoClassName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("kaleoClassPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoDefinitionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoDefinitionVersionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoNodeName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("executionType", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("template", Types.CLOB);
		TABLE_COLUMNS_MAP.put("templateLanguage", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("notificationTypes", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table KaleoNotification (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,kaleoNotificationId LONG not null,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(200) null,createDate DATE null,modifiedDate DATE null,kaleoClassName VARCHAR(200) null,kaleoClassPK LONG,kaleoDefinitionId LONG,kaleoDefinitionVersionId LONG,kaleoNodeName VARCHAR(200) null,name VARCHAR(200) null,description STRING null,executionType VARCHAR(20) null,template TEXT null,templateLanguage VARCHAR(75) null,notificationTypes VARCHAR(255) null,primary key (kaleoNotificationId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table KaleoNotification";

	public static final String ORDER_BY_JPQL =
		" ORDER BY kaleoNotification.kaleoNotificationId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY KaleoNotification.kaleoNotificationId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long EXECUTIONTYPE_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long KALEOCLASSNAME_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long KALEOCLASSPK_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long KALEODEFINITIONVERSIONID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long KALEONOTIFICATIONID_COLUMN_BITMASK = 32L;

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

	public KaleoNotificationModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _kaleoNotificationId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKaleoNotificationId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kaleoNotificationId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoNotification.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoNotification.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<KaleoNotification, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<KaleoNotification, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KaleoNotification, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((KaleoNotification)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<KaleoNotification, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<KaleoNotification, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(KaleoNotification)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<KaleoNotification, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<KaleoNotification, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<KaleoNotification, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<KaleoNotification, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<KaleoNotification, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", KaleoNotification::getMvccVersion);
			attributeGetterFunctions.put(
				"ctCollectionId", KaleoNotification::getCtCollectionId);
			attributeGetterFunctions.put(
				"kaleoNotificationId",
				KaleoNotification::getKaleoNotificationId);
			attributeGetterFunctions.put(
				"groupId", KaleoNotification::getGroupId);
			attributeGetterFunctions.put(
				"companyId", KaleoNotification::getCompanyId);
			attributeGetterFunctions.put(
				"userId", KaleoNotification::getUserId);
			attributeGetterFunctions.put(
				"userName", KaleoNotification::getUserName);
			attributeGetterFunctions.put(
				"createDate", KaleoNotification::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", KaleoNotification::getModifiedDate);
			attributeGetterFunctions.put(
				"kaleoClassName", KaleoNotification::getKaleoClassName);
			attributeGetterFunctions.put(
				"kaleoClassPK", KaleoNotification::getKaleoClassPK);
			attributeGetterFunctions.put(
				"kaleoDefinitionId", KaleoNotification::getKaleoDefinitionId);
			attributeGetterFunctions.put(
				"kaleoDefinitionVersionId",
				KaleoNotification::getKaleoDefinitionVersionId);
			attributeGetterFunctions.put(
				"kaleoNodeName", KaleoNotification::getKaleoNodeName);
			attributeGetterFunctions.put("name", KaleoNotification::getName);
			attributeGetterFunctions.put(
				"description", KaleoNotification::getDescription);
			attributeGetterFunctions.put(
				"executionType", KaleoNotification::getExecutionType);
			attributeGetterFunctions.put(
				"template", KaleoNotification::getTemplate);
			attributeGetterFunctions.put(
				"templateLanguage", KaleoNotification::getTemplateLanguage);
			attributeGetterFunctions.put(
				"notificationTypes", KaleoNotification::getNotificationTypes);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<KaleoNotification, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<KaleoNotification, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<KaleoNotification, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<KaleoNotification, Long>)
					KaleoNotification::setMvccVersion);
			attributeSetterBiConsumers.put(
				"ctCollectionId",
				(BiConsumer<KaleoNotification, Long>)
					KaleoNotification::setCtCollectionId);
			attributeSetterBiConsumers.put(
				"kaleoNotificationId",
				(BiConsumer<KaleoNotification, Long>)
					KaleoNotification::setKaleoNotificationId);
			attributeSetterBiConsumers.put(
				"groupId",
				(BiConsumer<KaleoNotification, Long>)
					KaleoNotification::setGroupId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<KaleoNotification, Long>)
					KaleoNotification::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<KaleoNotification, Long>)
					KaleoNotification::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<KaleoNotification, String>)
					KaleoNotification::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<KaleoNotification, Date>)
					KaleoNotification::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<KaleoNotification, Date>)
					KaleoNotification::setModifiedDate);
			attributeSetterBiConsumers.put(
				"kaleoClassName",
				(BiConsumer<KaleoNotification, String>)
					KaleoNotification::setKaleoClassName);
			attributeSetterBiConsumers.put(
				"kaleoClassPK",
				(BiConsumer<KaleoNotification, Long>)
					KaleoNotification::setKaleoClassPK);
			attributeSetterBiConsumers.put(
				"kaleoDefinitionId",
				(BiConsumer<KaleoNotification, Long>)
					KaleoNotification::setKaleoDefinitionId);
			attributeSetterBiConsumers.put(
				"kaleoDefinitionVersionId",
				(BiConsumer<KaleoNotification, Long>)
					KaleoNotification::setKaleoDefinitionVersionId);
			attributeSetterBiConsumers.put(
				"kaleoNodeName",
				(BiConsumer<KaleoNotification, String>)
					KaleoNotification::setKaleoNodeName);
			attributeSetterBiConsumers.put(
				"name",
				(BiConsumer<KaleoNotification, String>)
					KaleoNotification::setName);
			attributeSetterBiConsumers.put(
				"description",
				(BiConsumer<KaleoNotification, String>)
					KaleoNotification::setDescription);
			attributeSetterBiConsumers.put(
				"executionType",
				(BiConsumer<KaleoNotification, String>)
					KaleoNotification::setExecutionType);
			attributeSetterBiConsumers.put(
				"template",
				(BiConsumer<KaleoNotification, String>)
					KaleoNotification::setTemplate);
			attributeSetterBiConsumers.put(
				"templateLanguage",
				(BiConsumer<KaleoNotification, String>)
					KaleoNotification::setTemplateLanguage);
			attributeSetterBiConsumers.put(
				"notificationTypes",
				(BiConsumer<KaleoNotification, String>)
					KaleoNotification::setNotificationTypes);

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
	public long getKaleoNotificationId() {
		return _kaleoNotificationId;
	}

	@Override
	public void setKaleoNotificationId(long kaleoNotificationId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_kaleoNotificationId = kaleoNotificationId;
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
	public String getKaleoClassName() {
		if (_kaleoClassName == null) {
			return "";
		}
		else {
			return _kaleoClassName;
		}
	}

	@Override
	public void setKaleoClassName(String kaleoClassName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_kaleoClassName = kaleoClassName;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalKaleoClassName() {
		return getColumnOriginalValue("kaleoClassName");
	}

	@Override
	public long getKaleoClassPK() {
		return _kaleoClassPK;
	}

	@Override
	public void setKaleoClassPK(long kaleoClassPK) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_kaleoClassPK = kaleoClassPK;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalKaleoClassPK() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("kaleoClassPK"));
	}

	@Override
	public long getKaleoDefinitionId() {
		return _kaleoDefinitionId;
	}

	@Override
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_kaleoDefinitionId = kaleoDefinitionId;
	}

	@Override
	public long getKaleoDefinitionVersionId() {
		return _kaleoDefinitionVersionId;
	}

	@Override
	public void setKaleoDefinitionVersionId(long kaleoDefinitionVersionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_kaleoDefinitionVersionId = kaleoDefinitionVersionId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalKaleoDefinitionVersionId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("kaleoDefinitionVersionId"));
	}

	@Override
	public String getKaleoNodeName() {
		if (_kaleoNodeName == null) {
			return "";
		}
		else {
			return _kaleoNodeName;
		}
	}

	@Override
	public void setKaleoNodeName(String kaleoNodeName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_kaleoNodeName = kaleoNodeName;
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

	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_description = description;
	}

	@Override
	public String getExecutionType() {
		if (_executionType == null) {
			return "";
		}
		else {
			return _executionType;
		}
	}

	@Override
	public void setExecutionType(String executionType) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_executionType = executionType;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalExecutionType() {
		return getColumnOriginalValue("executionType");
	}

	@Override
	public String getTemplate() {
		if (_template == null) {
			return "";
		}
		else {
			return _template;
		}
	}

	@Override
	public void setTemplate(String template) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_template = template;
	}

	@Override
	public String getTemplateLanguage() {
		if (_templateLanguage == null) {
			return "";
		}
		else {
			return _templateLanguage;
		}
	}

	@Override
	public void setTemplateLanguage(String templateLanguage) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_templateLanguage = templateLanguage;
	}

	@Override
	public String getNotificationTypes() {
		if (_notificationTypes == null) {
			return "";
		}
		else {
			return _notificationTypes;
		}
	}

	@Override
	public void setNotificationTypes(String notificationTypes) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_notificationTypes = notificationTypes;
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
			getCompanyId(), KaleoNotification.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public KaleoNotification toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, KaleoNotification>
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
		KaleoNotificationImpl kaleoNotificationImpl =
			new KaleoNotificationImpl();

		kaleoNotificationImpl.setMvccVersion(getMvccVersion());
		kaleoNotificationImpl.setCtCollectionId(getCtCollectionId());
		kaleoNotificationImpl.setKaleoNotificationId(getKaleoNotificationId());
		kaleoNotificationImpl.setGroupId(getGroupId());
		kaleoNotificationImpl.setCompanyId(getCompanyId());
		kaleoNotificationImpl.setUserId(getUserId());
		kaleoNotificationImpl.setUserName(getUserName());
		kaleoNotificationImpl.setCreateDate(getCreateDate());
		kaleoNotificationImpl.setModifiedDate(getModifiedDate());
		kaleoNotificationImpl.setKaleoClassName(getKaleoClassName());
		kaleoNotificationImpl.setKaleoClassPK(getKaleoClassPK());
		kaleoNotificationImpl.setKaleoDefinitionId(getKaleoDefinitionId());
		kaleoNotificationImpl.setKaleoDefinitionVersionId(
			getKaleoDefinitionVersionId());
		kaleoNotificationImpl.setKaleoNodeName(getKaleoNodeName());
		kaleoNotificationImpl.setName(getName());
		kaleoNotificationImpl.setDescription(getDescription());
		kaleoNotificationImpl.setExecutionType(getExecutionType());
		kaleoNotificationImpl.setTemplate(getTemplate());
		kaleoNotificationImpl.setTemplateLanguage(getTemplateLanguage());
		kaleoNotificationImpl.setNotificationTypes(getNotificationTypes());

		kaleoNotificationImpl.resetOriginalValues();

		return kaleoNotificationImpl;
	}

	@Override
	public KaleoNotification cloneWithOriginalValues() {
		KaleoNotificationImpl kaleoNotificationImpl =
			new KaleoNotificationImpl();

		kaleoNotificationImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		kaleoNotificationImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		kaleoNotificationImpl.setKaleoNotificationId(
			this.<Long>getColumnOriginalValue("kaleoNotificationId"));
		kaleoNotificationImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		kaleoNotificationImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		kaleoNotificationImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		kaleoNotificationImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		kaleoNotificationImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		kaleoNotificationImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		kaleoNotificationImpl.setKaleoClassName(
			this.<String>getColumnOriginalValue("kaleoClassName"));
		kaleoNotificationImpl.setKaleoClassPK(
			this.<Long>getColumnOriginalValue("kaleoClassPK"));
		kaleoNotificationImpl.setKaleoDefinitionId(
			this.<Long>getColumnOriginalValue("kaleoDefinitionId"));
		kaleoNotificationImpl.setKaleoDefinitionVersionId(
			this.<Long>getColumnOriginalValue("kaleoDefinitionVersionId"));
		kaleoNotificationImpl.setKaleoNodeName(
			this.<String>getColumnOriginalValue("kaleoNodeName"));
		kaleoNotificationImpl.setName(
			this.<String>getColumnOriginalValue("name"));
		kaleoNotificationImpl.setDescription(
			this.<String>getColumnOriginalValue("description"));
		kaleoNotificationImpl.setExecutionType(
			this.<String>getColumnOriginalValue("executionType"));
		kaleoNotificationImpl.setTemplate(
			this.<String>getColumnOriginalValue("template"));
		kaleoNotificationImpl.setTemplateLanguage(
			this.<String>getColumnOriginalValue("templateLanguage"));
		kaleoNotificationImpl.setNotificationTypes(
			this.<String>getColumnOriginalValue("notificationTypes"));

		return kaleoNotificationImpl;
	}

	@Override
	public int compareTo(KaleoNotification kaleoNotification) {
		int value = 0;

		if (getKaleoNotificationId() <
				kaleoNotification.getKaleoNotificationId()) {

			value = -1;
		}
		else if (getKaleoNotificationId() >
					kaleoNotification.getKaleoNotificationId()) {

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

		if (!(object instanceof KaleoNotification)) {
			return false;
		}

		KaleoNotification kaleoNotification = (KaleoNotification)object;

		long primaryKey = kaleoNotification.getPrimaryKey();

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
	public CacheModel<KaleoNotification> toCacheModel() {
		KaleoNotificationCacheModel kaleoNotificationCacheModel =
			new KaleoNotificationCacheModel();

		kaleoNotificationCacheModel.mvccVersion = getMvccVersion();

		kaleoNotificationCacheModel.ctCollectionId = getCtCollectionId();

		kaleoNotificationCacheModel.kaleoNotificationId =
			getKaleoNotificationId();

		kaleoNotificationCacheModel.groupId = getGroupId();

		kaleoNotificationCacheModel.companyId = getCompanyId();

		kaleoNotificationCacheModel.userId = getUserId();

		kaleoNotificationCacheModel.userName = getUserName();

		String userName = kaleoNotificationCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			kaleoNotificationCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			kaleoNotificationCacheModel.createDate = createDate.getTime();
		}
		else {
			kaleoNotificationCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			kaleoNotificationCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			kaleoNotificationCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		kaleoNotificationCacheModel.kaleoClassName = getKaleoClassName();

		String kaleoClassName = kaleoNotificationCacheModel.kaleoClassName;

		if ((kaleoClassName != null) && (kaleoClassName.length() == 0)) {
			kaleoNotificationCacheModel.kaleoClassName = null;
		}

		kaleoNotificationCacheModel.kaleoClassPK = getKaleoClassPK();

		kaleoNotificationCacheModel.kaleoDefinitionId = getKaleoDefinitionId();

		kaleoNotificationCacheModel.kaleoDefinitionVersionId =
			getKaleoDefinitionVersionId();

		kaleoNotificationCacheModel.kaleoNodeName = getKaleoNodeName();

		String kaleoNodeName = kaleoNotificationCacheModel.kaleoNodeName;

		if ((kaleoNodeName != null) && (kaleoNodeName.length() == 0)) {
			kaleoNotificationCacheModel.kaleoNodeName = null;
		}

		kaleoNotificationCacheModel.name = getName();

		String name = kaleoNotificationCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			kaleoNotificationCacheModel.name = null;
		}

		kaleoNotificationCacheModel.description = getDescription();

		String description = kaleoNotificationCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			kaleoNotificationCacheModel.description = null;
		}

		kaleoNotificationCacheModel.executionType = getExecutionType();

		String executionType = kaleoNotificationCacheModel.executionType;

		if ((executionType != null) && (executionType.length() == 0)) {
			kaleoNotificationCacheModel.executionType = null;
		}

		kaleoNotificationCacheModel.template = getTemplate();

		String template = kaleoNotificationCacheModel.template;

		if ((template != null) && (template.length() == 0)) {
			kaleoNotificationCacheModel.template = null;
		}

		kaleoNotificationCacheModel.templateLanguage = getTemplateLanguage();

		String templateLanguage = kaleoNotificationCacheModel.templateLanguage;

		if ((templateLanguage != null) && (templateLanguage.length() == 0)) {
			kaleoNotificationCacheModel.templateLanguage = null;
		}

		kaleoNotificationCacheModel.notificationTypes = getNotificationTypes();

		String notificationTypes =
			kaleoNotificationCacheModel.notificationTypes;

		if ((notificationTypes != null) && (notificationTypes.length() == 0)) {
			kaleoNotificationCacheModel.notificationTypes = null;
		}

		return kaleoNotificationCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<KaleoNotification, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<KaleoNotification, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KaleoNotification, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(KaleoNotification)this);

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

		private static final Function<InvocationHandler, KaleoNotification>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					KaleoNotification.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _kaleoNotificationId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _kaleoClassName;
	private long _kaleoClassPK;
	private long _kaleoDefinitionId;
	private long _kaleoDefinitionVersionId;
	private String _kaleoNodeName;
	private String _name;
	private String _description;
	private String _executionType;
	private String _template;
	private String _templateLanguage;
	private String _notificationTypes;

	public <T> T getColumnValue(String columnName) {
		Function<KaleoNotification, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((KaleoNotification)this);
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
		_columnOriginalValues.put("kaleoNotificationId", _kaleoNotificationId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("kaleoClassName", _kaleoClassName);
		_columnOriginalValues.put("kaleoClassPK", _kaleoClassPK);
		_columnOriginalValues.put("kaleoDefinitionId", _kaleoDefinitionId);
		_columnOriginalValues.put(
			"kaleoDefinitionVersionId", _kaleoDefinitionVersionId);
		_columnOriginalValues.put("kaleoNodeName", _kaleoNodeName);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("description", _description);
		_columnOriginalValues.put("executionType", _executionType);
		_columnOriginalValues.put("template", _template);
		_columnOriginalValues.put("templateLanguage", _templateLanguage);
		_columnOriginalValues.put("notificationTypes", _notificationTypes);
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

		columnBitmasks.put("kaleoNotificationId", 4L);

		columnBitmasks.put("groupId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("userId", 32L);

		columnBitmasks.put("userName", 64L);

		columnBitmasks.put("createDate", 128L);

		columnBitmasks.put("modifiedDate", 256L);

		columnBitmasks.put("kaleoClassName", 512L);

		columnBitmasks.put("kaleoClassPK", 1024L);

		columnBitmasks.put("kaleoDefinitionId", 2048L);

		columnBitmasks.put("kaleoDefinitionVersionId", 4096L);

		columnBitmasks.put("kaleoNodeName", 8192L);

		columnBitmasks.put("name", 16384L);

		columnBitmasks.put("description", 32768L);

		columnBitmasks.put("executionType", 65536L);

		columnBitmasks.put("template", 131072L);

		columnBitmasks.put("templateLanguage", 262144L);

		columnBitmasks.put("notificationTypes", 524288L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private KaleoNotification _escapedModel;

}