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

package com.liferay.portal.security.wedeploy.auth.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the WeDeployAuthToken service. Represents a row in the &quot;WeDeployAuth_WeDeployAuthToken&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.security.wedeploy.auth.model.impl.WeDeployAuthTokenModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.security.wedeploy.auth.model.impl.WeDeployAuthTokenImpl</code>.
 * </p>
 *
 * @author Supritha Sundaram
 * @see WeDeployAuthToken
 * @generated
 */
@ProviderType
public interface WeDeployAuthTokenModel
	extends AuditedModel, BaseModel<WeDeployAuthToken>, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a we deploy auth token model instance should use the {@link WeDeployAuthToken} interface instead.
	 */

	/**
	 * Returns the primary key of this we deploy auth token.
	 *
	 * @return the primary key of this we deploy auth token
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this we deploy auth token.
	 *
	 * @param primaryKey the primary key of this we deploy auth token
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the we deploy auth token ID of this we deploy auth token.
	 *
	 * @return the we deploy auth token ID of this we deploy auth token
	 */
	public long getWeDeployAuthTokenId();

	/**
	 * Sets the we deploy auth token ID of this we deploy auth token.
	 *
	 * @param weDeployAuthTokenId the we deploy auth token ID of this we deploy auth token
	 */
	public void setWeDeployAuthTokenId(long weDeployAuthTokenId);

	/**
	 * Returns the company ID of this we deploy auth token.
	 *
	 * @return the company ID of this we deploy auth token
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this we deploy auth token.
	 *
	 * @param companyId the company ID of this we deploy auth token
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this we deploy auth token.
	 *
	 * @return the user ID of this we deploy auth token
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this we deploy auth token.
	 *
	 * @param userId the user ID of this we deploy auth token
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this we deploy auth token.
	 *
	 * @return the user uuid of this we deploy auth token
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this we deploy auth token.
	 *
	 * @param userUuid the user uuid of this we deploy auth token
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this we deploy auth token.
	 *
	 * @return the user name of this we deploy auth token
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this we deploy auth token.
	 *
	 * @param userName the user name of this we deploy auth token
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this we deploy auth token.
	 *
	 * @return the create date of this we deploy auth token
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this we deploy auth token.
	 *
	 * @param createDate the create date of this we deploy auth token
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this we deploy auth token.
	 *
	 * @return the modified date of this we deploy auth token
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this we deploy auth token.
	 *
	 * @param modifiedDate the modified date of this we deploy auth token
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the client ID of this we deploy auth token.
	 *
	 * @return the client ID of this we deploy auth token
	 */
	@AutoEscape
	public String getClientId();

	/**
	 * Sets the client ID of this we deploy auth token.
	 *
	 * @param clientId the client ID of this we deploy auth token
	 */
	public void setClientId(String clientId);

	/**
	 * Returns the token of this we deploy auth token.
	 *
	 * @return the token of this we deploy auth token
	 */
	@AutoEscape
	public String getToken();

	/**
	 * Sets the token of this we deploy auth token.
	 *
	 * @param token the token of this we deploy auth token
	 */
	public void setToken(String token);

	/**
	 * Returns the type of this we deploy auth token.
	 *
	 * @return the type of this we deploy auth token
	 */
	public int getType();

	/**
	 * Sets the type of this we deploy auth token.
	 *
	 * @param type the type of this we deploy auth token
	 */
	public void setType(int type);

	@Override
	public WeDeployAuthToken cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}