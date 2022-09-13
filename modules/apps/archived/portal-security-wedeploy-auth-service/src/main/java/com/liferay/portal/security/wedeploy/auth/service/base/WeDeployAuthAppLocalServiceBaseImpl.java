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

package com.liferay.portal.security.wedeploy.auth.service.base;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.security.wedeploy.auth.model.WeDeployAuthApp;
import com.liferay.portal.security.wedeploy.auth.service.WeDeployAuthAppLocalService;
import com.liferay.portal.security.wedeploy.auth.service.WeDeployAuthAppLocalServiceUtil;
import com.liferay.portal.security.wedeploy.auth.service.persistence.WeDeployAuthAppPersistence;
import com.liferay.portal.security.wedeploy.auth.service.persistence.WeDeployAuthTokenPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the we deploy auth app local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.security.wedeploy.auth.service.impl.WeDeployAuthAppLocalServiceImpl}.
 * </p>
 *
 * @author Supritha Sundaram
 * @see com.liferay.portal.security.wedeploy.auth.service.impl.WeDeployAuthAppLocalServiceImpl
 * @generated
 */
public abstract class WeDeployAuthAppLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService,
			   WeDeployAuthAppLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>WeDeployAuthAppLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>WeDeployAuthAppLocalServiceUtil</code>.
	 */

	/**
	 * Adds the we deploy auth app to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WeDeployAuthAppLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param weDeployAuthApp the we deploy auth app
	 * @return the we deploy auth app that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WeDeployAuthApp addWeDeployAuthApp(WeDeployAuthApp weDeployAuthApp) {
		weDeployAuthApp.setNew(true);

		return weDeployAuthAppPersistence.update(weDeployAuthApp);
	}

	/**
	 * Creates a new we deploy auth app with the primary key. Does not add the we deploy auth app to the database.
	 *
	 * @param weDeployAuthAppId the primary key for the new we deploy auth app
	 * @return the new we deploy auth app
	 */
	@Override
	@Transactional(enabled = false)
	public WeDeployAuthApp createWeDeployAuthApp(long weDeployAuthAppId) {
		return weDeployAuthAppPersistence.create(weDeployAuthAppId);
	}

	/**
	 * Deletes the we deploy auth app with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WeDeployAuthAppLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param weDeployAuthAppId the primary key of the we deploy auth app
	 * @return the we deploy auth app that was removed
	 * @throws PortalException if a we deploy auth app with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public WeDeployAuthApp deleteWeDeployAuthApp(long weDeployAuthAppId)
		throws PortalException {

		return weDeployAuthAppPersistence.remove(weDeployAuthAppId);
	}

	/**
	 * Deletes the we deploy auth app from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WeDeployAuthAppLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param weDeployAuthApp the we deploy auth app
	 * @return the we deploy auth app that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public WeDeployAuthApp deleteWeDeployAuthApp(
		WeDeployAuthApp weDeployAuthApp) {

		return weDeployAuthAppPersistence.remove(weDeployAuthApp);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return weDeployAuthAppPersistence.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(DSLQuery dslQuery) {
		Long count = dslQuery(dslQuery);

		return count.intValue();
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			WeDeployAuthApp.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return weDeployAuthAppPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.security.wedeploy.auth.model.impl.WeDeployAuthAppModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return weDeployAuthAppPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.security.wedeploy.auth.model.impl.WeDeployAuthAppModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return weDeployAuthAppPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return weDeployAuthAppPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return weDeployAuthAppPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public WeDeployAuthApp fetchWeDeployAuthApp(long weDeployAuthAppId) {
		return weDeployAuthAppPersistence.fetchByPrimaryKey(weDeployAuthAppId);
	}

	/**
	 * Returns the we deploy auth app with the primary key.
	 *
	 * @param weDeployAuthAppId the primary key of the we deploy auth app
	 * @return the we deploy auth app
	 * @throws PortalException if a we deploy auth app with the primary key could not be found
	 */
	@Override
	public WeDeployAuthApp getWeDeployAuthApp(long weDeployAuthAppId)
		throws PortalException {

		return weDeployAuthAppPersistence.findByPrimaryKey(weDeployAuthAppId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(weDeployAuthAppLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(WeDeployAuthApp.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("weDeployAuthAppId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			weDeployAuthAppLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(WeDeployAuthApp.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"weDeployAuthAppId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(weDeployAuthAppLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(WeDeployAuthApp.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("weDeployAuthAppId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return weDeployAuthAppPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		if (_log.isWarnEnabled()) {
			_log.warn(
				"Implement WeDeployAuthAppLocalServiceImpl#deleteWeDeployAuthApp(WeDeployAuthApp) to avoid orphaned data");
		}

		return weDeployAuthAppLocalService.deleteWeDeployAuthApp(
			(WeDeployAuthApp)persistedModel);
	}

	@Override
	public BasePersistence<WeDeployAuthApp> getBasePersistence() {
		return weDeployAuthAppPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return weDeployAuthAppPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the we deploy auth apps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.security.wedeploy.auth.model.impl.WeDeployAuthAppModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of we deploy auth apps
	 * @param end the upper bound of the range of we deploy auth apps (not inclusive)
	 * @return the range of we deploy auth apps
	 */
	@Override
	public List<WeDeployAuthApp> getWeDeployAuthApps(int start, int end) {
		return weDeployAuthAppPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of we deploy auth apps.
	 *
	 * @return the number of we deploy auth apps
	 */
	@Override
	public int getWeDeployAuthAppsCount() {
		return weDeployAuthAppPersistence.countAll();
	}

	/**
	 * Updates the we deploy auth app in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WeDeployAuthAppLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param weDeployAuthApp the we deploy auth app
	 * @return the we deploy auth app that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WeDeployAuthApp updateWeDeployAuthApp(
		WeDeployAuthApp weDeployAuthApp) {

		return weDeployAuthAppPersistence.update(weDeployAuthApp);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			WeDeployAuthAppLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		weDeployAuthAppLocalService = (WeDeployAuthAppLocalService)aopProxy;

		_setLocalServiceUtilService(weDeployAuthAppLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return WeDeployAuthAppLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return WeDeployAuthApp.class;
	}

	protected String getModelClassName() {
		return WeDeployAuthApp.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = weDeployAuthAppPersistence.getDataSource();

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

	private void _setLocalServiceUtilService(
		WeDeployAuthAppLocalService weDeployAuthAppLocalService) {

		try {
			Field field =
				WeDeployAuthAppLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, weDeployAuthAppLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	protected WeDeployAuthAppLocalService weDeployAuthAppLocalService;

	@Reference
	protected WeDeployAuthAppPersistence weDeployAuthAppPersistence;

	@Reference
	protected WeDeployAuthTokenPersistence weDeployAuthTokenPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		WeDeployAuthAppLocalServiceBaseImpl.class);

}