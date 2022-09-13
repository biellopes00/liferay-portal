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

package com.liferay.portal.tools.service.builder.test.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.portal.tools.service.builder.test.model.EagerBlobEntry;
import com.liferay.portal.tools.service.builder.test.service.EagerBlobEntryService;
import com.liferay.portal.tools.service.builder.test.service.EagerBlobEntryServiceUtil;
import com.liferay.portal.tools.service.builder.test.service.persistence.EagerBlobEntryPersistence;

import java.lang.reflect.Field;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the eager blob entry remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.tools.service.builder.test.service.impl.EagerBlobEntryServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.tools.service.builder.test.service.impl.EagerBlobEntryServiceImpl
 * @generated
 */
public abstract class EagerBlobEntryServiceBaseImpl
	extends BaseServiceImpl
	implements EagerBlobEntryService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>EagerBlobEntryService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>EagerBlobEntryServiceUtil</code>.
	 */

	/**
	 * Returns the eager blob entry local service.
	 *
	 * @return the eager blob entry local service
	 */
	public com.liferay.portal.tools.service.builder.test.service.
		EagerBlobEntryLocalService getEagerBlobEntryLocalService() {

		return eagerBlobEntryLocalService;
	}

	/**
	 * Sets the eager blob entry local service.
	 *
	 * @param eagerBlobEntryLocalService the eager blob entry local service
	 */
	public void setEagerBlobEntryLocalService(
		com.liferay.portal.tools.service.builder.test.service.
			EagerBlobEntryLocalService eagerBlobEntryLocalService) {

		this.eagerBlobEntryLocalService = eagerBlobEntryLocalService;
	}

	/**
	 * Returns the eager blob entry remote service.
	 *
	 * @return the eager blob entry remote service
	 */
	public EagerBlobEntryService getEagerBlobEntryService() {
		return eagerBlobEntryService;
	}

	/**
	 * Sets the eager blob entry remote service.
	 *
	 * @param eagerBlobEntryService the eager blob entry remote service
	 */
	public void setEagerBlobEntryService(
		EagerBlobEntryService eagerBlobEntryService) {

		this.eagerBlobEntryService = eagerBlobEntryService;
	}

	/**
	 * Returns the eager blob entry persistence.
	 *
	 * @return the eager blob entry persistence
	 */
	public EagerBlobEntryPersistence getEagerBlobEntryPersistence() {
		return eagerBlobEntryPersistence;
	}

	/**
	 * Sets the eager blob entry persistence.
	 *
	 * @param eagerBlobEntryPersistence the eager blob entry persistence
	 */
	public void setEagerBlobEntryPersistence(
		EagerBlobEntryPersistence eagerBlobEntryPersistence) {

		this.eagerBlobEntryPersistence = eagerBlobEntryPersistence;
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
		_setServiceUtilService(eagerBlobEntryService);
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
		return EagerBlobEntryService.class.getName();
	}

	protected Class<?> getModelClass() {
		return EagerBlobEntry.class;
	}

	protected String getModelClassName() {
		return EagerBlobEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = eagerBlobEntryPersistence.getDataSource();

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
		EagerBlobEntryService eagerBlobEntryService) {

		try {
			Field field = EagerBlobEntryServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, eagerBlobEntryService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(
		type = com.liferay.portal.tools.service.builder.test.service.EagerBlobEntryLocalService.class
	)
	protected com.liferay.portal.tools.service.builder.test.service.
		EagerBlobEntryLocalService eagerBlobEntryLocalService;

	@BeanReference(type = EagerBlobEntryService.class)
	protected EagerBlobEntryService eagerBlobEntryService;

	@BeanReference(type = EagerBlobEntryPersistence.class)
	protected EagerBlobEntryPersistence eagerBlobEntryPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		EagerBlobEntryLocalServiceBaseImpl.class);

}