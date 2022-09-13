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

package com.liferay.html.preview.service.base;

import com.liferay.html.preview.model.HtmlPreviewEntry;
import com.liferay.html.preview.service.HtmlPreviewEntryLocalService;
import com.liferay.html.preview.service.HtmlPreviewEntryLocalServiceUtil;
import com.liferay.html.preview.service.persistence.HtmlPreviewEntryPersistence;
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

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the html preview entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.html.preview.service.impl.HtmlPreviewEntryLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.html.preview.service.impl.HtmlPreviewEntryLocalServiceImpl
 * @generated
 */
public abstract class HtmlPreviewEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, HtmlPreviewEntryLocalService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>HtmlPreviewEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>HtmlPreviewEntryLocalServiceUtil</code>.
	 */

	/**
	 * Adds the html preview entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HtmlPreviewEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param htmlPreviewEntry the html preview entry
	 * @return the html preview entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public HtmlPreviewEntry addHtmlPreviewEntry(
		HtmlPreviewEntry htmlPreviewEntry) {

		htmlPreviewEntry.setNew(true);

		return htmlPreviewEntryPersistence.update(htmlPreviewEntry);
	}

	/**
	 * Creates a new html preview entry with the primary key. Does not add the html preview entry to the database.
	 *
	 * @param htmlPreviewEntryId the primary key for the new html preview entry
	 * @return the new html preview entry
	 */
	@Override
	@Transactional(enabled = false)
	public HtmlPreviewEntry createHtmlPreviewEntry(long htmlPreviewEntryId) {
		return htmlPreviewEntryPersistence.create(htmlPreviewEntryId);
	}

	/**
	 * Deletes the html preview entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HtmlPreviewEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param htmlPreviewEntryId the primary key of the html preview entry
	 * @return the html preview entry that was removed
	 * @throws PortalException if a html preview entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public HtmlPreviewEntry deleteHtmlPreviewEntry(long htmlPreviewEntryId)
		throws PortalException {

		return htmlPreviewEntryPersistence.remove(htmlPreviewEntryId);
	}

	/**
	 * Deletes the html preview entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HtmlPreviewEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param htmlPreviewEntry the html preview entry
	 * @return the html preview entry that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public HtmlPreviewEntry deleteHtmlPreviewEntry(
			HtmlPreviewEntry htmlPreviewEntry)
		throws PortalException {

		return htmlPreviewEntryPersistence.remove(htmlPreviewEntry);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return htmlPreviewEntryPersistence.dslQuery(dslQuery);
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
			HtmlPreviewEntry.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return htmlPreviewEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.html.preview.model.impl.HtmlPreviewEntryModelImpl</code>.
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

		return htmlPreviewEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.html.preview.model.impl.HtmlPreviewEntryModelImpl</code>.
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

		return htmlPreviewEntryPersistence.findWithDynamicQuery(
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
		return htmlPreviewEntryPersistence.countWithDynamicQuery(dynamicQuery);
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

		return htmlPreviewEntryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public HtmlPreviewEntry fetchHtmlPreviewEntry(long htmlPreviewEntryId) {
		return htmlPreviewEntryPersistence.fetchByPrimaryKey(
			htmlPreviewEntryId);
	}

	/**
	 * Returns the html preview entry with the primary key.
	 *
	 * @param htmlPreviewEntryId the primary key of the html preview entry
	 * @return the html preview entry
	 * @throws PortalException if a html preview entry with the primary key could not be found
	 */
	@Override
	public HtmlPreviewEntry getHtmlPreviewEntry(long htmlPreviewEntryId)
		throws PortalException {

		return htmlPreviewEntryPersistence.findByPrimaryKey(htmlPreviewEntryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			htmlPreviewEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(HtmlPreviewEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("htmlPreviewEntryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			htmlPreviewEntryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(HtmlPreviewEntry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"htmlPreviewEntryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			htmlPreviewEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(HtmlPreviewEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("htmlPreviewEntryId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return htmlPreviewEntryPersistence.create(
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
				"Implement HtmlPreviewEntryLocalServiceImpl#deleteHtmlPreviewEntry(HtmlPreviewEntry) to avoid orphaned data");
		}

		return htmlPreviewEntryLocalService.deleteHtmlPreviewEntry(
			(HtmlPreviewEntry)persistedModel);
	}

	@Override
	public BasePersistence<HtmlPreviewEntry> getBasePersistence() {
		return htmlPreviewEntryPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return htmlPreviewEntryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the html preview entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.html.preview.model.impl.HtmlPreviewEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of html preview entries
	 * @param end the upper bound of the range of html preview entries (not inclusive)
	 * @return the range of html preview entries
	 */
	@Override
	public List<HtmlPreviewEntry> getHtmlPreviewEntries(int start, int end) {
		return htmlPreviewEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of html preview entries.
	 *
	 * @return the number of html preview entries
	 */
	@Override
	public int getHtmlPreviewEntriesCount() {
		return htmlPreviewEntryPersistence.countAll();
	}

	/**
	 * Updates the html preview entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HtmlPreviewEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param htmlPreviewEntry the html preview entry
	 * @return the html preview entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public HtmlPreviewEntry updateHtmlPreviewEntry(
		HtmlPreviewEntry htmlPreviewEntry) {

		return htmlPreviewEntryPersistence.update(htmlPreviewEntry);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			HtmlPreviewEntryLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		htmlPreviewEntryLocalService = (HtmlPreviewEntryLocalService)aopProxy;

		_setLocalServiceUtilService(htmlPreviewEntryLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return HtmlPreviewEntryLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return HtmlPreviewEntry.class;
	}

	protected String getModelClassName() {
		return HtmlPreviewEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = htmlPreviewEntryPersistence.getDataSource();

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
		HtmlPreviewEntryLocalService htmlPreviewEntryLocalService) {

		try {
			Field field =
				HtmlPreviewEntryLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, htmlPreviewEntryLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	protected HtmlPreviewEntryLocalService htmlPreviewEntryLocalService;

	@Reference
	protected HtmlPreviewEntryPersistence htmlPreviewEntryPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		HtmlPreviewEntryLocalServiceBaseImpl.class);

}