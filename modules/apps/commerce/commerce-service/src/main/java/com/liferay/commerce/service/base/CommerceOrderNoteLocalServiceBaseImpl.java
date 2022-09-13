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

package com.liferay.commerce.service.base;

import com.liferay.commerce.model.CommerceOrderNote;
import com.liferay.commerce.service.CommerceOrderNoteLocalService;
import com.liferay.commerce.service.CommerceOrderNoteLocalServiceUtil;
import com.liferay.commerce.service.persistence.CommerceOrderNotePersistence;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
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
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
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
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the commerce order note local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.service.impl.CommerceOrderNoteLocalServiceImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see com.liferay.commerce.service.impl.CommerceOrderNoteLocalServiceImpl
 * @generated
 */
public abstract class CommerceOrderNoteLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements CommerceOrderNoteLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CommerceOrderNoteLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CommerceOrderNoteLocalServiceUtil</code>.
	 */

	/**
	 * Adds the commerce order note to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceOrderNoteLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceOrderNote the commerce order note
	 * @return the commerce order note that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceOrderNote addCommerceOrderNote(
		CommerceOrderNote commerceOrderNote) {

		commerceOrderNote.setNew(true);

		return commerceOrderNotePersistence.update(commerceOrderNote);
	}

	/**
	 * Creates a new commerce order note with the primary key. Does not add the commerce order note to the database.
	 *
	 * @param commerceOrderNoteId the primary key for the new commerce order note
	 * @return the new commerce order note
	 */
	@Override
	@Transactional(enabled = false)
	public CommerceOrderNote createCommerceOrderNote(long commerceOrderNoteId) {
		return commerceOrderNotePersistence.create(commerceOrderNoteId);
	}

	/**
	 * Deletes the commerce order note with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceOrderNoteLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceOrderNoteId the primary key of the commerce order note
	 * @return the commerce order note that was removed
	 * @throws PortalException if a commerce order note with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceOrderNote deleteCommerceOrderNote(long commerceOrderNoteId)
		throws PortalException {

		return commerceOrderNotePersistence.remove(commerceOrderNoteId);
	}

	/**
	 * Deletes the commerce order note from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceOrderNoteLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceOrderNote the commerce order note
	 * @return the commerce order note that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceOrderNote deleteCommerceOrderNote(
		CommerceOrderNote commerceOrderNote) {

		return commerceOrderNotePersistence.remove(commerceOrderNote);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return commerceOrderNotePersistence.dslQuery(dslQuery);
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
			CommerceOrderNote.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return commerceOrderNotePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceOrderNoteModelImpl</code>.
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

		return commerceOrderNotePersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceOrderNoteModelImpl</code>.
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

		return commerceOrderNotePersistence.findWithDynamicQuery(
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
		return commerceOrderNotePersistence.countWithDynamicQuery(dynamicQuery);
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

		return commerceOrderNotePersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public CommerceOrderNote fetchCommerceOrderNote(long commerceOrderNoteId) {
		return commerceOrderNotePersistence.fetchByPrimaryKey(
			commerceOrderNoteId);
	}

	/**
	 * Returns the commerce order note matching the UUID and group.
	 *
	 * @param uuid the commerce order note's UUID
	 * @param groupId the primary key of the group
	 * @return the matching commerce order note, or <code>null</code> if a matching commerce order note could not be found
	 */
	@Override
	public CommerceOrderNote fetchCommerceOrderNoteByUuidAndGroupId(
		String uuid, long groupId) {

		return commerceOrderNotePersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the commerce order note with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the commerce order note's external reference code
	 * @return the matching commerce order note, or <code>null</code> if a matching commerce order note could not be found
	 */
	@Override
	public CommerceOrderNote fetchCommerceOrderNoteByExternalReferenceCode(
		long companyId, String externalReferenceCode) {

		return commerceOrderNotePersistence.fetchByC_ERC(
			companyId, externalReferenceCode);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link #fetchCommerceOrderNoteByExternalReferenceCode(long, String)}
	 */
	@Deprecated
	@Override
	public CommerceOrderNote fetchCommerceOrderNoteByReferenceCode(
		long companyId, String externalReferenceCode) {

		return fetchCommerceOrderNoteByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	/**
	 * Returns the commerce order note with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the commerce order note's external reference code
	 * @return the matching commerce order note
	 * @throws PortalException if a matching commerce order note could not be found
	 */
	@Override
	public CommerceOrderNote getCommerceOrderNoteByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException {

		return commerceOrderNotePersistence.findByC_ERC(
			companyId, externalReferenceCode);
	}

	/**
	 * Returns the commerce order note with the primary key.
	 *
	 * @param commerceOrderNoteId the primary key of the commerce order note
	 * @return the commerce order note
	 * @throws PortalException if a commerce order note with the primary key could not be found
	 */
	@Override
	public CommerceOrderNote getCommerceOrderNote(long commerceOrderNoteId)
		throws PortalException {

		return commerceOrderNotePersistence.findByPrimaryKey(
			commerceOrderNoteId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			commerceOrderNoteLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CommerceOrderNote.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("commerceOrderNoteId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			commerceOrderNoteLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(CommerceOrderNote.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceOrderNoteId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			commerceOrderNoteLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CommerceOrderNote.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("commerceOrderNoteId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<CommerceOrderNote>() {

				@Override
				public void performAction(CommerceOrderNote commerceOrderNote)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, commerceOrderNote);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(CommerceOrderNote.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return commerceOrderNotePersistence.create(
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
				"Implement CommerceOrderNoteLocalServiceImpl#deleteCommerceOrderNote(CommerceOrderNote) to avoid orphaned data");
		}

		return commerceOrderNoteLocalService.deleteCommerceOrderNote(
			(CommerceOrderNote)persistedModel);
	}

	@Override
	public BasePersistence<CommerceOrderNote> getBasePersistence() {
		return commerceOrderNotePersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return commerceOrderNotePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the commerce order notes matching the UUID and company.
	 *
	 * @param uuid the UUID of the commerce order notes
	 * @param companyId the primary key of the company
	 * @return the matching commerce order notes, or an empty list if no matches were found
	 */
	@Override
	public List<CommerceOrderNote> getCommerceOrderNotesByUuidAndCompanyId(
		String uuid, long companyId) {

		return commerceOrderNotePersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of commerce order notes matching the UUID and company.
	 *
	 * @param uuid the UUID of the commerce order notes
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of commerce order notes
	 * @param end the upper bound of the range of commerce order notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching commerce order notes, or an empty list if no matches were found
	 */
	@Override
	public List<CommerceOrderNote> getCommerceOrderNotesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommerceOrderNote> orderByComparator) {

		return commerceOrderNotePersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the commerce order note matching the UUID and group.
	 *
	 * @param uuid the commerce order note's UUID
	 * @param groupId the primary key of the group
	 * @return the matching commerce order note
	 * @throws PortalException if a matching commerce order note could not be found
	 */
	@Override
	public CommerceOrderNote getCommerceOrderNoteByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return commerceOrderNotePersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the commerce order notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceOrderNoteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce order notes
	 * @param end the upper bound of the range of commerce order notes (not inclusive)
	 * @return the range of commerce order notes
	 */
	@Override
	public List<CommerceOrderNote> getCommerceOrderNotes(int start, int end) {
		return commerceOrderNotePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of commerce order notes.
	 *
	 * @return the number of commerce order notes
	 */
	@Override
	public int getCommerceOrderNotesCount() {
		return commerceOrderNotePersistence.countAll();
	}

	/**
	 * Updates the commerce order note in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceOrderNoteLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceOrderNote the commerce order note
	 * @return the commerce order note that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceOrderNote updateCommerceOrderNote(
		CommerceOrderNote commerceOrderNote) {

		return commerceOrderNotePersistence.update(commerceOrderNote);
	}

	/**
	 * Returns the commerce order note local service.
	 *
	 * @return the commerce order note local service
	 */
	public CommerceOrderNoteLocalService getCommerceOrderNoteLocalService() {
		return commerceOrderNoteLocalService;
	}

	/**
	 * Sets the commerce order note local service.
	 *
	 * @param commerceOrderNoteLocalService the commerce order note local service
	 */
	public void setCommerceOrderNoteLocalService(
		CommerceOrderNoteLocalService commerceOrderNoteLocalService) {

		this.commerceOrderNoteLocalService = commerceOrderNoteLocalService;
	}

	/**
	 * Returns the commerce order note persistence.
	 *
	 * @return the commerce order note persistence
	 */
	public CommerceOrderNotePersistence getCommerceOrderNotePersistence() {
		return commerceOrderNotePersistence;
	}

	/**
	 * Sets the commerce order note persistence.
	 *
	 * @param commerceOrderNotePersistence the commerce order note persistence
	 */
	public void setCommerceOrderNotePersistence(
		CommerceOrderNotePersistence commerceOrderNotePersistence) {

		this.commerceOrderNotePersistence = commerceOrderNotePersistence;
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
			"com.liferay.commerce.model.CommerceOrderNote",
			commerceOrderNoteLocalService);

		_setLocalServiceUtilService(commerceOrderNoteLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.commerce.model.CommerceOrderNote");

		_setLocalServiceUtilService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CommerceOrderNoteLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CommerceOrderNote.class;
	}

	protected String getModelClassName() {
		return CommerceOrderNote.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				commerceOrderNotePersistence.getDataSource();

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
		CommerceOrderNoteLocalService commerceOrderNoteLocalService) {

		try {
			Field field =
				CommerceOrderNoteLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, commerceOrderNoteLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(type = CommerceOrderNoteLocalService.class)
	protected CommerceOrderNoteLocalService commerceOrderNoteLocalService;

	@BeanReference(type = CommerceOrderNotePersistence.class)
	protected CommerceOrderNotePersistence commerceOrderNotePersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceOrderNoteLocalServiceBaseImpl.class);

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}