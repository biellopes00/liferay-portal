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

package com.liferay.portal.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.PasswordPolicy;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.PasswordPolicyService;
import com.liferay.portal.kernel.service.PasswordPolicyServiceUtil;
import com.liferay.portal.kernel.service.persistence.PasswordPolicyFinder;
import com.liferay.portal.kernel.service.persistence.PasswordPolicyPersistence;
import com.liferay.portal.kernel.util.PortalUtil;

import java.lang.reflect.Field;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the password policy remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.PasswordPolicyServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.PasswordPolicyServiceImpl
 * @generated
 */
public abstract class PasswordPolicyServiceBaseImpl
	extends BaseServiceImpl
	implements IdentifiableOSGiService, PasswordPolicyService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>PasswordPolicyService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>PasswordPolicyServiceUtil</code>.
	 */

	/**
	 * Returns the password policy local service.
	 *
	 * @return the password policy local service
	 */
	public com.liferay.portal.kernel.service.PasswordPolicyLocalService
		getPasswordPolicyLocalService() {

		return passwordPolicyLocalService;
	}

	/**
	 * Sets the password policy local service.
	 *
	 * @param passwordPolicyLocalService the password policy local service
	 */
	public void setPasswordPolicyLocalService(
		com.liferay.portal.kernel.service.PasswordPolicyLocalService
			passwordPolicyLocalService) {

		this.passwordPolicyLocalService = passwordPolicyLocalService;
	}

	/**
	 * Returns the password policy remote service.
	 *
	 * @return the password policy remote service
	 */
	public PasswordPolicyService getPasswordPolicyService() {
		return passwordPolicyService;
	}

	/**
	 * Sets the password policy remote service.
	 *
	 * @param passwordPolicyService the password policy remote service
	 */
	public void setPasswordPolicyService(
		PasswordPolicyService passwordPolicyService) {

		this.passwordPolicyService = passwordPolicyService;
	}

	/**
	 * Returns the password policy persistence.
	 *
	 * @return the password policy persistence
	 */
	public PasswordPolicyPersistence getPasswordPolicyPersistence() {
		return passwordPolicyPersistence;
	}

	/**
	 * Sets the password policy persistence.
	 *
	 * @param passwordPolicyPersistence the password policy persistence
	 */
	public void setPasswordPolicyPersistence(
		PasswordPolicyPersistence passwordPolicyPersistence) {

		this.passwordPolicyPersistence = passwordPolicyPersistence;
	}

	/**
	 * Returns the password policy finder.
	 *
	 * @return the password policy finder
	 */
	public PasswordPolicyFinder getPasswordPolicyFinder() {
		return passwordPolicyFinder;
	}

	/**
	 * Sets the password policy finder.
	 *
	 * @param passwordPolicyFinder the password policy finder
	 */
	public void setPasswordPolicyFinder(
		PasswordPolicyFinder passwordPolicyFinder) {

		this.passwordPolicyFinder = passwordPolicyFinder;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	public void afterPropertiesSet() {
		_setServiceUtilService(passwordPolicyService);
	}

	public void destroy() {
		_setServiceUtilService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return PasswordPolicyService.class.getName();
	}

	protected Class<?> getModelClass() {
		return PasswordPolicy.class;
	}

	protected String getModelClassName() {
		return PasswordPolicy.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = passwordPolicyPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	private void _setServiceUtilService(
		PasswordPolicyService passwordPolicyService) {

		try {
			Field field = PasswordPolicyServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, passwordPolicyService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(
		type = com.liferay.portal.kernel.service.PasswordPolicyLocalService.class
	)
	protected com.liferay.portal.kernel.service.PasswordPolicyLocalService
		passwordPolicyLocalService;

	@BeanReference(type = PasswordPolicyService.class)
	protected PasswordPolicyService passwordPolicyService;

	@BeanReference(type = PasswordPolicyPersistence.class)
	protected PasswordPolicyPersistence passwordPolicyPersistence;

	@BeanReference(type = PasswordPolicyFinder.class)
	protected PasswordPolicyFinder passwordPolicyFinder;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		PasswordPolicyLocalServiceBaseImpl.class);

}