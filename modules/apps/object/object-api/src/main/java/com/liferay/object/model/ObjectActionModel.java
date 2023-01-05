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

package com.liferay.object.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the ObjectAction service. Represents a row in the &quot;ObjectAction&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.object.model.impl.ObjectActionModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.object.model.impl.ObjectActionImpl</code>.
 * </p>
 *
 * @author Marco Leo
 * @see ObjectAction
 * @generated
 */
@ProviderType
public interface ObjectActionModel
	extends BaseModel<ObjectAction>, LocalizedModel, MVCCModel, ShardedModel,
			StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a object action model instance should use the {@link ObjectAction} interface instead.
	 */

	/**
	 * Returns the primary key of this object action.
	 *
	 * @return the primary key of this object action
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this object action.
	 *
	 * @param primaryKey the primary key of this object action
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this object action.
	 *
	 * @return the mvcc version of this object action
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this object action.
	 *
	 * @param mvccVersion the mvcc version of this object action
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this object action.
	 *
	 * @return the uuid of this object action
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this object action.
	 *
	 * @param uuid the uuid of this object action
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the external reference code of this object action.
	 *
	 * @return the external reference code of this object action
	 */
	@AutoEscape
	public String getExternalReferenceCode();

	/**
	 * Sets the external reference code of this object action.
	 *
	 * @param externalReferenceCode the external reference code of this object action
	 */
	public void setExternalReferenceCode(String externalReferenceCode);

	/**
	 * Returns the object action ID of this object action.
	 *
	 * @return the object action ID of this object action
	 */
	public long getObjectActionId();

	/**
	 * Sets the object action ID of this object action.
	 *
	 * @param objectActionId the object action ID of this object action
	 */
	public void setObjectActionId(long objectActionId);

	/**
	 * Returns the company ID of this object action.
	 *
	 * @return the company ID of this object action
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this object action.
	 *
	 * @param companyId the company ID of this object action
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this object action.
	 *
	 * @return the user ID of this object action
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this object action.
	 *
	 * @param userId the user ID of this object action
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this object action.
	 *
	 * @return the user uuid of this object action
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this object action.
	 *
	 * @param userUuid the user uuid of this object action
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this object action.
	 *
	 * @return the user name of this object action
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this object action.
	 *
	 * @param userName the user name of this object action
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this object action.
	 *
	 * @return the create date of this object action
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this object action.
	 *
	 * @param createDate the create date of this object action
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this object action.
	 *
	 * @return the modified date of this object action
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this object action.
	 *
	 * @param modifiedDate the modified date of this object action
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the object definition ID of this object action.
	 *
	 * @return the object definition ID of this object action
	 */
	public long getObjectDefinitionId();

	/**
	 * Sets the object definition ID of this object action.
	 *
	 * @param objectDefinitionId the object definition ID of this object action
	 */
	public void setObjectDefinitionId(long objectDefinitionId);

	/**
	 * Returns the active of this object action.
	 *
	 * @return the active of this object action
	 */
	public boolean getActive();

	/**
	 * Returns <code>true</code> if this object action is active.
	 *
	 * @return <code>true</code> if this object action is active; <code>false</code> otherwise
	 */
	public boolean isActive();

	/**
	 * Sets whether this object action is active.
	 *
	 * @param active the active of this object action
	 */
	public void setActive(boolean active);

	/**
	 * Returns the condition expression of this object action.
	 *
	 * @return the condition expression of this object action
	 */
	@AutoEscape
	public String getConditionExpression();

	/**
	 * Sets the condition expression of this object action.
	 *
	 * @param conditionExpression the condition expression of this object action
	 */
	public void setConditionExpression(String conditionExpression);

	/**
	 * Returns the description of this object action.
	 *
	 * @return the description of this object action
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this object action.
	 *
	 * @param description the description of this object action
	 */
	public void setDescription(String description);

	/**
	 * Returns the error message of this object action.
	 *
	 * @return the error message of this object action
	 */
	public String getErrorMessage();

	/**
	 * Returns the localized error message of this object action in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized error message of this object action
	 */
	@AutoEscape
	public String getErrorMessage(Locale locale);

	/**
	 * Returns the localized error message of this object action in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized error message of this object action. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getErrorMessage(Locale locale, boolean useDefault);

	/**
	 * Returns the localized error message of this object action in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized error message of this object action
	 */
	@AutoEscape
	public String getErrorMessage(String languageId);

	/**
	 * Returns the localized error message of this object action in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized error message of this object action
	 */
	@AutoEscape
	public String getErrorMessage(String languageId, boolean useDefault);

	@AutoEscape
	public String getErrorMessageCurrentLanguageId();

	@AutoEscape
	public String getErrorMessageCurrentValue();

	/**
	 * Returns a map of the locales and localized error messages of this object action.
	 *
	 * @return the locales and localized error messages of this object action
	 */
	public Map<Locale, String> getErrorMessageMap();

	/**
	 * Sets the error message of this object action.
	 *
	 * @param errorMessage the error message of this object action
	 */
	public void setErrorMessage(String errorMessage);

	/**
	 * Sets the localized error message of this object action in the language.
	 *
	 * @param errorMessage the localized error message of this object action
	 * @param locale the locale of the language
	 */
	public void setErrorMessage(String errorMessage, Locale locale);

	/**
	 * Sets the localized error message of this object action in the language, and sets the default locale.
	 *
	 * @param errorMessage the localized error message of this object action
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setErrorMessage(
		String errorMessage, Locale locale, Locale defaultLocale);

	public void setErrorMessageCurrentLanguageId(String languageId);

	/**
	 * Sets the localized error messages of this object action from the map of locales and localized error messages.
	 *
	 * @param errorMessageMap the locales and localized error messages of this object action
	 */
	public void setErrorMessageMap(Map<Locale, String> errorMessageMap);

	/**
	 * Sets the localized error messages of this object action from the map of locales and localized error messages, and sets the default locale.
	 *
	 * @param errorMessageMap the locales and localized error messages of this object action
	 * @param defaultLocale the default locale
	 */
	public void setErrorMessageMap(
		Map<Locale, String> errorMessageMap, Locale defaultLocale);

	/**
	 * Returns the label of this object action.
	 *
	 * @return the label of this object action
	 */
	public String getLabel();

	/**
	 * Returns the localized label of this object action in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized label of this object action
	 */
	@AutoEscape
	public String getLabel(Locale locale);

	/**
	 * Returns the localized label of this object action in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized label of this object action. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getLabel(Locale locale, boolean useDefault);

	/**
	 * Returns the localized label of this object action in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized label of this object action
	 */
	@AutoEscape
	public String getLabel(String languageId);

	/**
	 * Returns the localized label of this object action in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized label of this object action
	 */
	@AutoEscape
	public String getLabel(String languageId, boolean useDefault);

	@AutoEscape
	public String getLabelCurrentLanguageId();

	@AutoEscape
	public String getLabelCurrentValue();

	/**
	 * Returns a map of the locales and localized labels of this object action.
	 *
	 * @return the locales and localized labels of this object action
	 */
	public Map<Locale, String> getLabelMap();

	/**
	 * Sets the label of this object action.
	 *
	 * @param label the label of this object action
	 */
	public void setLabel(String label);

	/**
	 * Sets the localized label of this object action in the language.
	 *
	 * @param label the localized label of this object action
	 * @param locale the locale of the language
	 */
	public void setLabel(String label, Locale locale);

	/**
	 * Sets the localized label of this object action in the language, and sets the default locale.
	 *
	 * @param label the localized label of this object action
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setLabel(String label, Locale locale, Locale defaultLocale);

	public void setLabelCurrentLanguageId(String languageId);

	/**
	 * Sets the localized labels of this object action from the map of locales and localized labels.
	 *
	 * @param labelMap the locales and localized labels of this object action
	 */
	public void setLabelMap(Map<Locale, String> labelMap);

	/**
	 * Sets the localized labels of this object action from the map of locales and localized labels, and sets the default locale.
	 *
	 * @param labelMap the locales and localized labels of this object action
	 * @param defaultLocale the default locale
	 */
	public void setLabelMap(Map<Locale, String> labelMap, Locale defaultLocale);

	/**
	 * Returns the name of this object action.
	 *
	 * @return the name of this object action
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this object action.
	 *
	 * @param name the name of this object action
	 */
	public void setName(String name);

	/**
	 * Returns the object action executor key of this object action.
	 *
	 * @return the object action executor key of this object action
	 */
	@AutoEscape
	public String getObjectActionExecutorKey();

	/**
	 * Sets the object action executor key of this object action.
	 *
	 * @param objectActionExecutorKey the object action executor key of this object action
	 */
	public void setObjectActionExecutorKey(String objectActionExecutorKey);

	/**
	 * Returns the object action trigger key of this object action.
	 *
	 * @return the object action trigger key of this object action
	 */
	@AutoEscape
	public String getObjectActionTriggerKey();

	/**
	 * Sets the object action trigger key of this object action.
	 *
	 * @param objectActionTriggerKey the object action trigger key of this object action
	 */
	public void setObjectActionTriggerKey(String objectActionTriggerKey);

	/**
	 * Returns the parameters of this object action.
	 *
	 * @return the parameters of this object action
	 */
	@AutoEscape
	public String getParameters();

	/**
	 * Sets the parameters of this object action.
	 *
	 * @param parameters the parameters of this object action
	 */
	public void setParameters(String parameters);

	/**
	 * Returns the status of this object action.
	 *
	 * @return the status of this object action
	 */
	public int getStatus();

	/**
	 * Sets the status of this object action.
	 *
	 * @param status the status of this object action
	 */
	public void setStatus(int status);

	@Override
	public String[] getAvailableLanguageIds();

	@Override
	public String getDefaultLanguageId();

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException;

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

	@Override
	public ObjectAction cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}