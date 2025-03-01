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

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.kernel.model.RecentLayoutRevision;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.RecentLayoutRevisionLocalService;
import com.liferay.portal.kernel.service.RecentLayoutRevisionLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.RecentLayoutRevisionPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the recent layout revision local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.RecentLayoutRevisionLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.RecentLayoutRevisionLocalServiceImpl
 * @generated
 */
public abstract class RecentLayoutRevisionLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements IdentifiableOSGiService, RecentLayoutRevisionLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>RecentLayoutRevisionLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>RecentLayoutRevisionLocalServiceUtil</code>.
	 */

	/**
	 * Adds the recent layout revision to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RecentLayoutRevisionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param recentLayoutRevision the recent layout revision
	 * @return the recent layout revision that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public RecentLayoutRevision addRecentLayoutRevision(
		RecentLayoutRevision recentLayoutRevision) {

		recentLayoutRevision.setNew(true);

		return recentLayoutRevisionPersistence.update(recentLayoutRevision);
	}

	/**
	 * Creates a new recent layout revision with the primary key. Does not add the recent layout revision to the database.
	 *
	 * @param recentLayoutRevisionId the primary key for the new recent layout revision
	 * @return the new recent layout revision
	 */
	@Override
	@Transactional(enabled = false)
	public RecentLayoutRevision createRecentLayoutRevision(
		long recentLayoutRevisionId) {

		return recentLayoutRevisionPersistence.create(recentLayoutRevisionId);
	}

	/**
	 * Deletes the recent layout revision with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RecentLayoutRevisionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param recentLayoutRevisionId the primary key of the recent layout revision
	 * @return the recent layout revision that was removed
	 * @throws PortalException if a recent layout revision with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public RecentLayoutRevision deleteRecentLayoutRevision(
			long recentLayoutRevisionId)
		throws PortalException {

		return recentLayoutRevisionPersistence.remove(recentLayoutRevisionId);
	}

	/**
	 * Deletes the recent layout revision from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RecentLayoutRevisionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param recentLayoutRevision the recent layout revision
	 * @return the recent layout revision that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public RecentLayoutRevision deleteRecentLayoutRevision(
		RecentLayoutRevision recentLayoutRevision) {

		return recentLayoutRevisionPersistence.remove(recentLayoutRevision);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return recentLayoutRevisionPersistence.dslQuery(dslQuery);
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
			RecentLayoutRevision.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return recentLayoutRevisionPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.RecentLayoutRevisionModelImpl</code>.
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

		return recentLayoutRevisionPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.RecentLayoutRevisionModelImpl</code>.
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

		return recentLayoutRevisionPersistence.findWithDynamicQuery(
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
		return recentLayoutRevisionPersistence.countWithDynamicQuery(
			dynamicQuery);
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

		return recentLayoutRevisionPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public RecentLayoutRevision fetchRecentLayoutRevision(
		long recentLayoutRevisionId) {

		return recentLayoutRevisionPersistence.fetchByPrimaryKey(
			recentLayoutRevisionId);
	}

	/**
	 * Returns the recent layout revision with the primary key.
	 *
	 * @param recentLayoutRevisionId the primary key of the recent layout revision
	 * @return the recent layout revision
	 * @throws PortalException if a recent layout revision with the primary key could not be found
	 */
	@Override
	public RecentLayoutRevision getRecentLayoutRevision(
			long recentLayoutRevisionId)
		throws PortalException {

		return recentLayoutRevisionPersistence.findByPrimaryKey(
			recentLayoutRevisionId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			recentLayoutRevisionLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(RecentLayoutRevision.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"recentLayoutRevisionId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			recentLayoutRevisionLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			RecentLayoutRevision.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"recentLayoutRevisionId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			recentLayoutRevisionLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(RecentLayoutRevision.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"recentLayoutRevisionId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return recentLayoutRevisionPersistence.create(
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
				"Implement RecentLayoutRevisionLocalServiceImpl#deleteRecentLayoutRevision(RecentLayoutRevision) to avoid orphaned data");
		}

		return recentLayoutRevisionLocalService.deleteRecentLayoutRevision(
			(RecentLayoutRevision)persistedModel);
	}

	@Override
	public BasePersistence<RecentLayoutRevision> getBasePersistence() {
		return recentLayoutRevisionPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return recentLayoutRevisionPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the recent layout revisions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.RecentLayoutRevisionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of recent layout revisions
	 * @param end the upper bound of the range of recent layout revisions (not inclusive)
	 * @return the range of recent layout revisions
	 */
	@Override
	public List<RecentLayoutRevision> getRecentLayoutRevisions(
		int start, int end) {

		return recentLayoutRevisionPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of recent layout revisions.
	 *
	 * @return the number of recent layout revisions
	 */
	@Override
	public int getRecentLayoutRevisionsCount() {
		return recentLayoutRevisionPersistence.countAll();
	}

	/**
	 * Updates the recent layout revision in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RecentLayoutRevisionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param recentLayoutRevision the recent layout revision
	 * @return the recent layout revision that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public RecentLayoutRevision updateRecentLayoutRevision(
		RecentLayoutRevision recentLayoutRevision) {

		return recentLayoutRevisionPersistence.update(recentLayoutRevision);
	}

	/**
	 * Returns the recent layout revision local service.
	 *
	 * @return the recent layout revision local service
	 */
	public RecentLayoutRevisionLocalService
		getRecentLayoutRevisionLocalService() {

		return recentLayoutRevisionLocalService;
	}

	/**
	 * Sets the recent layout revision local service.
	 *
	 * @param recentLayoutRevisionLocalService the recent layout revision local service
	 */
	public void setRecentLayoutRevisionLocalService(
		RecentLayoutRevisionLocalService recentLayoutRevisionLocalService) {

		this.recentLayoutRevisionLocalService =
			recentLayoutRevisionLocalService;
	}

	/**
	 * Returns the recent layout revision persistence.
	 *
	 * @return the recent layout revision persistence
	 */
	public RecentLayoutRevisionPersistence
		getRecentLayoutRevisionPersistence() {

		return recentLayoutRevisionPersistence;
	}

	/**
	 * Sets the recent layout revision persistence.
	 *
	 * @param recentLayoutRevisionPersistence the recent layout revision persistence
	 */
	public void setRecentLayoutRevisionPersistence(
		RecentLayoutRevisionPersistence recentLayoutRevisionPersistence) {

		this.recentLayoutRevisionPersistence = recentLayoutRevisionPersistence;
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
		persistedModelLocalServiceRegistry.register(
			"com.liferay.portal.kernel.model.RecentLayoutRevision",
			recentLayoutRevisionLocalService);

		_setLocalServiceUtilService(recentLayoutRevisionLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.kernel.model.RecentLayoutRevision");

		_setLocalServiceUtilService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return RecentLayoutRevisionLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return RecentLayoutRevision.class;
	}

	protected String getModelClassName() {
		return RecentLayoutRevision.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				recentLayoutRevisionPersistence.getDataSource();

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
		RecentLayoutRevisionLocalService recentLayoutRevisionLocalService) {

		try {
			Field field =
				RecentLayoutRevisionLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, recentLayoutRevisionLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(type = RecentLayoutRevisionLocalService.class)
	protected RecentLayoutRevisionLocalService recentLayoutRevisionLocalService;

	@BeanReference(type = RecentLayoutRevisionPersistence.class)
	protected RecentLayoutRevisionPersistence recentLayoutRevisionPersistence;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		RecentLayoutRevisionLocalServiceBaseImpl.class);

	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}