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

package com.liferay.oauth2.provider.service.base;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.oauth2.provider.model.OAuth2Application;
import com.liferay.oauth2.provider.service.OAuth2ApplicationLocalService;
import com.liferay.oauth2.provider.service.OAuth2ApplicationLocalServiceUtil;
import com.liferay.oauth2.provider.service.persistence.OAuth2ApplicationPersistence;
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
 * Provides the base implementation for the o auth2 application local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.oauth2.provider.service.impl.OAuth2ApplicationLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.oauth2.provider.service.impl.OAuth2ApplicationLocalServiceImpl
 * @generated
 */
public abstract class OAuth2ApplicationLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService,
			   OAuth2ApplicationLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>OAuth2ApplicationLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>OAuth2ApplicationLocalServiceUtil</code>.
	 */

	/**
	 * Adds the o auth2 application to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OAuth2ApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param oAuth2Application the o auth2 application
	 * @return the o auth2 application that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public OAuth2Application addOAuth2Application(
		OAuth2Application oAuth2Application) {

		oAuth2Application.setNew(true);

		return oAuth2ApplicationPersistence.update(oAuth2Application);
	}

	/**
	 * Creates a new o auth2 application with the primary key. Does not add the o auth2 application to the database.
	 *
	 * @param oAuth2ApplicationId the primary key for the new o auth2 application
	 * @return the new o auth2 application
	 */
	@Override
	@Transactional(enabled = false)
	public OAuth2Application createOAuth2Application(long oAuth2ApplicationId) {
		return oAuth2ApplicationPersistence.create(oAuth2ApplicationId);
	}

	/**
	 * Deletes the o auth2 application with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OAuth2ApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param oAuth2ApplicationId the primary key of the o auth2 application
	 * @return the o auth2 application that was removed
	 * @throws PortalException if a o auth2 application with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public OAuth2Application deleteOAuth2Application(long oAuth2ApplicationId)
		throws PortalException {

		return oAuth2ApplicationPersistence.remove(oAuth2ApplicationId);
	}

	/**
	 * Deletes the o auth2 application from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OAuth2ApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param oAuth2Application the o auth2 application
	 * @return the o auth2 application that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public OAuth2Application deleteOAuth2Application(
			OAuth2Application oAuth2Application)
		throws PortalException {

		return oAuth2ApplicationPersistence.remove(oAuth2Application);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return oAuth2ApplicationPersistence.dslQuery(dslQuery);
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
			OAuth2Application.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return oAuth2ApplicationPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.oauth2.provider.model.impl.OAuth2ApplicationModelImpl</code>.
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

		return oAuth2ApplicationPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.oauth2.provider.model.impl.OAuth2ApplicationModelImpl</code>.
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

		return oAuth2ApplicationPersistence.findWithDynamicQuery(
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
		return oAuth2ApplicationPersistence.countWithDynamicQuery(dynamicQuery);
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

		return oAuth2ApplicationPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public OAuth2Application fetchOAuth2Application(long oAuth2ApplicationId) {
		return oAuth2ApplicationPersistence.fetchByPrimaryKey(
			oAuth2ApplicationId);
	}

	/**
	 * Returns the o auth2 application with the matching UUID and company.
	 *
	 * @param uuid the o auth2 application's UUID
	 * @param companyId the primary key of the company
	 * @return the matching o auth2 application, or <code>null</code> if a matching o auth2 application could not be found
	 */
	@Override
	public OAuth2Application fetchOAuth2ApplicationByUuidAndCompanyId(
		String uuid, long companyId) {

		return oAuth2ApplicationPersistence.fetchByUuid_C_First(
			uuid, companyId, null);
	}

	@Override
	public OAuth2Application fetchOAuth2ApplicationByExternalReferenceCode(
		String externalReferenceCode, long companyId) {

		return oAuth2ApplicationPersistence.fetchByERC_C(
			externalReferenceCode, companyId);
	}

	@Override
	public OAuth2Application getOAuth2ApplicationByExternalReferenceCode(
			String externalReferenceCode, long companyId)
		throws PortalException {

		return oAuth2ApplicationPersistence.findByERC_C(
			externalReferenceCode, companyId);
	}

	/**
	 * Returns the o auth2 application with the primary key.
	 *
	 * @param oAuth2ApplicationId the primary key of the o auth2 application
	 * @return the o auth2 application
	 * @throws PortalException if a o auth2 application with the primary key could not be found
	 */
	@Override
	public OAuth2Application getOAuth2Application(long oAuth2ApplicationId)
		throws PortalException {

		return oAuth2ApplicationPersistence.findByPrimaryKey(
			oAuth2ApplicationId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			oAuth2ApplicationLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(OAuth2Application.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("oAuth2ApplicationId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			oAuth2ApplicationLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(OAuth2Application.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"oAuth2ApplicationId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			oAuth2ApplicationLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(OAuth2Application.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("oAuth2ApplicationId");
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
				<OAuth2Application>() {

				@Override
				public void performAction(OAuth2Application oAuth2Application)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, oAuth2Application);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(OAuth2Application.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return oAuth2ApplicationPersistence.create(
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
				"Implement OAuth2ApplicationLocalServiceImpl#deleteOAuth2Application(OAuth2Application) to avoid orphaned data");
		}

		return oAuth2ApplicationLocalService.deleteOAuth2Application(
			(OAuth2Application)persistedModel);
	}

	@Override
	public BasePersistence<OAuth2Application> getBasePersistence() {
		return oAuth2ApplicationPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return oAuth2ApplicationPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the o auth2 application with the matching UUID and company.
	 *
	 * @param uuid the o auth2 application's UUID
	 * @param companyId the primary key of the company
	 * @return the matching o auth2 application
	 * @throws PortalException if a matching o auth2 application could not be found
	 */
	@Override
	public OAuth2Application getOAuth2ApplicationByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return oAuth2ApplicationPersistence.findByUuid_C_First(
			uuid, companyId, null);
	}

	/**
	 * Returns a range of all the o auth2 applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.oauth2.provider.model.impl.OAuth2ApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of o auth2 applications
	 * @param end the upper bound of the range of o auth2 applications (not inclusive)
	 * @return the range of o auth2 applications
	 */
	@Override
	public List<OAuth2Application> getOAuth2Applications(int start, int end) {
		return oAuth2ApplicationPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of o auth2 applications.
	 *
	 * @return the number of o auth2 applications
	 */
	@Override
	public int getOAuth2ApplicationsCount() {
		return oAuth2ApplicationPersistence.countAll();
	}

	/**
	 * Updates the o auth2 application in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OAuth2ApplicationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param oAuth2Application the o auth2 application
	 * @return the o auth2 application that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public OAuth2Application updateOAuth2Application(
		OAuth2Application oAuth2Application) {

		return oAuth2ApplicationPersistence.update(oAuth2Application);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			OAuth2ApplicationLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		oAuth2ApplicationLocalService = (OAuth2ApplicationLocalService)aopProxy;

		_setLocalServiceUtilService(oAuth2ApplicationLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return OAuth2ApplicationLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return OAuth2Application.class;
	}

	protected String getModelClassName() {
		return OAuth2Application.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				oAuth2ApplicationPersistence.getDataSource();

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
		OAuth2ApplicationLocalService oAuth2ApplicationLocalService) {

		try {
			Field field =
				OAuth2ApplicationLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, oAuth2ApplicationLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	protected OAuth2ApplicationLocalService oAuth2ApplicationLocalService;

	@Reference
	protected OAuth2ApplicationPersistence oAuth2ApplicationPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		OAuth2ApplicationLocalServiceBaseImpl.class);

}