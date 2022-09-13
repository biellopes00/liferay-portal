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

package com.liferay.notification.service.base;

import com.liferay.notification.model.NotificationTemplateAttachment;
import com.liferay.notification.service.NotificationTemplateAttachmentLocalService;
import com.liferay.notification.service.NotificationTemplateAttachmentLocalServiceUtil;
import com.liferay.notification.service.persistence.NotificationTemplateAttachmentPersistence;
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
 * Provides the base implementation for the notification template attachment local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.notification.service.impl.NotificationTemplateAttachmentLocalServiceImpl}.
 * </p>
 *
 * @author Gabriel Albuquerque
 * @see com.liferay.notification.service.impl.NotificationTemplateAttachmentLocalServiceImpl
 * @generated
 */
public abstract class NotificationTemplateAttachmentLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService,
			   NotificationTemplateAttachmentLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>NotificationTemplateAttachmentLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>NotificationTemplateAttachmentLocalServiceUtil</code>.
	 */

	/**
	 * Adds the notification template attachment to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NotificationTemplateAttachmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param notificationTemplateAttachment the notification template attachment
	 * @return the notification template attachment that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public NotificationTemplateAttachment addNotificationTemplateAttachment(
		NotificationTemplateAttachment notificationTemplateAttachment) {

		notificationTemplateAttachment.setNew(true);

		return notificationTemplateAttachmentPersistence.update(
			notificationTemplateAttachment);
	}

	/**
	 * Creates a new notification template attachment with the primary key. Does not add the notification template attachment to the database.
	 *
	 * @param notificationTemplateAttachmentId the primary key for the new notification template attachment
	 * @return the new notification template attachment
	 */
	@Override
	@Transactional(enabled = false)
	public NotificationTemplateAttachment createNotificationTemplateAttachment(
		long notificationTemplateAttachmentId) {

		return notificationTemplateAttachmentPersistence.create(
			notificationTemplateAttachmentId);
	}

	/**
	 * Deletes the notification template attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NotificationTemplateAttachmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param notificationTemplateAttachmentId the primary key of the notification template attachment
	 * @return the notification template attachment that was removed
	 * @throws PortalException if a notification template attachment with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public NotificationTemplateAttachment deleteNotificationTemplateAttachment(
			long notificationTemplateAttachmentId)
		throws PortalException {

		return notificationTemplateAttachmentPersistence.remove(
			notificationTemplateAttachmentId);
	}

	/**
	 * Deletes the notification template attachment from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NotificationTemplateAttachmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param notificationTemplateAttachment the notification template attachment
	 * @return the notification template attachment that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public NotificationTemplateAttachment deleteNotificationTemplateAttachment(
		NotificationTemplateAttachment notificationTemplateAttachment) {

		return notificationTemplateAttachmentPersistence.remove(
			notificationTemplateAttachment);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return notificationTemplateAttachmentPersistence.dslQuery(dslQuery);
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
			NotificationTemplateAttachment.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return notificationTemplateAttachmentPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.notification.model.impl.NotificationTemplateAttachmentModelImpl</code>.
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

		return notificationTemplateAttachmentPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.notification.model.impl.NotificationTemplateAttachmentModelImpl</code>.
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

		return notificationTemplateAttachmentPersistence.findWithDynamicQuery(
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
		return notificationTemplateAttachmentPersistence.countWithDynamicQuery(
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

		return notificationTemplateAttachmentPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public NotificationTemplateAttachment fetchNotificationTemplateAttachment(
		long notificationTemplateAttachmentId) {

		return notificationTemplateAttachmentPersistence.fetchByPrimaryKey(
			notificationTemplateAttachmentId);
	}

	/**
	 * Returns the notification template attachment with the primary key.
	 *
	 * @param notificationTemplateAttachmentId the primary key of the notification template attachment
	 * @return the notification template attachment
	 * @throws PortalException if a notification template attachment with the primary key could not be found
	 */
	@Override
	public NotificationTemplateAttachment getNotificationTemplateAttachment(
			long notificationTemplateAttachmentId)
		throws PortalException {

		return notificationTemplateAttachmentPersistence.findByPrimaryKey(
			notificationTemplateAttachmentId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			notificationTemplateAttachmentLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(
			NotificationTemplateAttachment.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"notificationTemplateAttachmentId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			notificationTemplateAttachmentLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			NotificationTemplateAttachment.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"notificationTemplateAttachmentId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			notificationTemplateAttachmentLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(
			NotificationTemplateAttachment.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"notificationTemplateAttachmentId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return notificationTemplateAttachmentPersistence.create(
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
				"Implement NotificationTemplateAttachmentLocalServiceImpl#deleteNotificationTemplateAttachment(NotificationTemplateAttachment) to avoid orphaned data");
		}

		return notificationTemplateAttachmentLocalService.
			deleteNotificationTemplateAttachment(
				(NotificationTemplateAttachment)persistedModel);
	}

	@Override
	public BasePersistence<NotificationTemplateAttachment>
		getBasePersistence() {

		return notificationTemplateAttachmentPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return notificationTemplateAttachmentPersistence.findByPrimaryKey(
			primaryKeyObj);
	}

	/**
	 * Returns a range of all the notification template attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.notification.model.impl.NotificationTemplateAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notification template attachments
	 * @param end the upper bound of the range of notification template attachments (not inclusive)
	 * @return the range of notification template attachments
	 */
	@Override
	public List<NotificationTemplateAttachment>
		getNotificationTemplateAttachments(int start, int end) {

		return notificationTemplateAttachmentPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of notification template attachments.
	 *
	 * @return the number of notification template attachments
	 */
	@Override
	public int getNotificationTemplateAttachmentsCount() {
		return notificationTemplateAttachmentPersistence.countAll();
	}

	/**
	 * Updates the notification template attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NotificationTemplateAttachmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param notificationTemplateAttachment the notification template attachment
	 * @return the notification template attachment that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public NotificationTemplateAttachment updateNotificationTemplateAttachment(
		NotificationTemplateAttachment notificationTemplateAttachment) {

		return notificationTemplateAttachmentPersistence.update(
			notificationTemplateAttachment);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			NotificationTemplateAttachmentLocalService.class,
			IdentifiableOSGiService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		notificationTemplateAttachmentLocalService =
			(NotificationTemplateAttachmentLocalService)aopProxy;

		_setLocalServiceUtilService(notificationTemplateAttachmentLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return NotificationTemplateAttachmentLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return NotificationTemplateAttachment.class;
	}

	protected String getModelClassName() {
		return NotificationTemplateAttachment.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				notificationTemplateAttachmentPersistence.getDataSource();

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
		NotificationTemplateAttachmentLocalService
			notificationTemplateAttachmentLocalService) {

		try {
			Field field =
				NotificationTemplateAttachmentLocalServiceUtil.class.
					getDeclaredField("_service");

			field.setAccessible(true);

			field.set(null, notificationTemplateAttachmentLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	protected NotificationTemplateAttachmentLocalService
		notificationTemplateAttachmentLocalService;

	@Reference
	protected NotificationTemplateAttachmentPersistence
		notificationTemplateAttachmentPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		NotificationTemplateAttachmentLocalServiceBaseImpl.class);

}