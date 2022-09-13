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

import com.liferay.petra.io.AutoDeleteFileInputStream;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.db.DBType;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
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
import com.liferay.portal.kernel.util.File;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.portal.tools.service.builder.test.model.LazyBlobEntry;
import com.liferay.portal.tools.service.builder.test.model.LazyBlobEntryBlob1BlobModel;
import com.liferay.portal.tools.service.builder.test.model.LazyBlobEntryBlob2BlobModel;
import com.liferay.portal.tools.service.builder.test.service.LazyBlobEntryLocalService;
import com.liferay.portal.tools.service.builder.test.service.LazyBlobEntryLocalServiceUtil;
import com.liferay.portal.tools.service.builder.test.service.persistence.LazyBlobEntryPersistence;

import java.io.InputStream;
import java.io.Serializable;

import java.lang.reflect.Field;

import java.sql.Blob;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the lazy blob entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.tools.service.builder.test.service.impl.LazyBlobEntryLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.tools.service.builder.test.service.impl.LazyBlobEntryLocalServiceImpl
 * @generated
 */
public abstract class LazyBlobEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements IdentifiableOSGiService, LazyBlobEntryLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>LazyBlobEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>LazyBlobEntryLocalServiceUtil</code>.
	 */

	/**
	 * Adds the lazy blob entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LazyBlobEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param lazyBlobEntry the lazy blob entry
	 * @return the lazy blob entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public LazyBlobEntry addLazyBlobEntry(LazyBlobEntry lazyBlobEntry) {
		lazyBlobEntry.setNew(true);

		return lazyBlobEntryPersistence.update(lazyBlobEntry);
	}

	/**
	 * Creates a new lazy blob entry with the primary key. Does not add the lazy blob entry to the database.
	 *
	 * @param lazyBlobEntryId the primary key for the new lazy blob entry
	 * @return the new lazy blob entry
	 */
	@Override
	@Transactional(enabled = false)
	public LazyBlobEntry createLazyBlobEntry(long lazyBlobEntryId) {
		return lazyBlobEntryPersistence.create(lazyBlobEntryId);
	}

	/**
	 * Deletes the lazy blob entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LazyBlobEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param lazyBlobEntryId the primary key of the lazy blob entry
	 * @return the lazy blob entry that was removed
	 * @throws PortalException if a lazy blob entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public LazyBlobEntry deleteLazyBlobEntry(long lazyBlobEntryId)
		throws PortalException {

		return lazyBlobEntryPersistence.remove(lazyBlobEntryId);
	}

	/**
	 * Deletes the lazy blob entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LazyBlobEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param lazyBlobEntry the lazy blob entry
	 * @return the lazy blob entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public LazyBlobEntry deleteLazyBlobEntry(LazyBlobEntry lazyBlobEntry) {
		return lazyBlobEntryPersistence.remove(lazyBlobEntry);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return lazyBlobEntryPersistence.dslQuery(dslQuery);
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
			LazyBlobEntry.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return lazyBlobEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.LazyBlobEntryModelImpl</code>.
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

		return lazyBlobEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.LazyBlobEntryModelImpl</code>.
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

		return lazyBlobEntryPersistence.findWithDynamicQuery(
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
		return lazyBlobEntryPersistence.countWithDynamicQuery(dynamicQuery);
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

		return lazyBlobEntryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public LazyBlobEntry fetchLazyBlobEntry(long lazyBlobEntryId) {
		return lazyBlobEntryPersistence.fetchByPrimaryKey(lazyBlobEntryId);
	}

	/**
	 * Returns the lazy blob entry matching the UUID and group.
	 *
	 * @param uuid the lazy blob entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching lazy blob entry, or <code>null</code> if a matching lazy blob entry could not be found
	 */
	@Override
	public LazyBlobEntry fetchLazyBlobEntryByUuidAndGroupId(
		String uuid, long groupId) {

		return lazyBlobEntryPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the lazy blob entry with the primary key.
	 *
	 * @param lazyBlobEntryId the primary key of the lazy blob entry
	 * @return the lazy blob entry
	 * @throws PortalException if a lazy blob entry with the primary key could not be found
	 */
	@Override
	public LazyBlobEntry getLazyBlobEntry(long lazyBlobEntryId)
		throws PortalException {

		return lazyBlobEntryPersistence.findByPrimaryKey(lazyBlobEntryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(lazyBlobEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(LazyBlobEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("lazyBlobEntryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			lazyBlobEntryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(LazyBlobEntry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"lazyBlobEntryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(lazyBlobEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(LazyBlobEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("lazyBlobEntryId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return lazyBlobEntryPersistence.create(
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
				"Implement LazyBlobEntryLocalServiceImpl#deleteLazyBlobEntry(LazyBlobEntry) to avoid orphaned data");
		}

		return lazyBlobEntryLocalService.deleteLazyBlobEntry(
			(LazyBlobEntry)persistedModel);
	}

	@Override
	public BasePersistence<LazyBlobEntry> getBasePersistence() {
		return lazyBlobEntryPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return lazyBlobEntryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the lazy blob entry matching the UUID and group.
	 *
	 * @param uuid the lazy blob entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching lazy blob entry
	 * @throws PortalException if a matching lazy blob entry could not be found
	 */
	@Override
	public LazyBlobEntry getLazyBlobEntryByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return lazyBlobEntryPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the lazy blob entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.LazyBlobEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of lazy blob entries
	 * @param end the upper bound of the range of lazy blob entries (not inclusive)
	 * @return the range of lazy blob entries
	 */
	@Override
	public List<LazyBlobEntry> getLazyBlobEntries(int start, int end) {
		return lazyBlobEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of lazy blob entries.
	 *
	 * @return the number of lazy blob entries
	 */
	@Override
	public int getLazyBlobEntriesCount() {
		return lazyBlobEntryPersistence.countAll();
	}

	/**
	 * Updates the lazy blob entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LazyBlobEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param lazyBlobEntry the lazy blob entry
	 * @return the lazy blob entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public LazyBlobEntry updateLazyBlobEntry(LazyBlobEntry lazyBlobEntry) {
		return lazyBlobEntryPersistence.update(lazyBlobEntry);
	}

	/**
	 * Returns the lazy blob entry local service.
	 *
	 * @return the lazy blob entry local service
	 */
	public LazyBlobEntryLocalService getLazyBlobEntryLocalService() {
		return lazyBlobEntryLocalService;
	}

	/**
	 * Sets the lazy blob entry local service.
	 *
	 * @param lazyBlobEntryLocalService the lazy blob entry local service
	 */
	public void setLazyBlobEntryLocalService(
		LazyBlobEntryLocalService lazyBlobEntryLocalService) {

		this.lazyBlobEntryLocalService = lazyBlobEntryLocalService;
	}

	/**
	 * Returns the lazy blob entry persistence.
	 *
	 * @return the lazy blob entry persistence
	 */
	public LazyBlobEntryPersistence getLazyBlobEntryPersistence() {
		return lazyBlobEntryPersistence;
	}

	/**
	 * Sets the lazy blob entry persistence.
	 *
	 * @param lazyBlobEntryPersistence the lazy blob entry persistence
	 */
	public void setLazyBlobEntryPersistence(
		LazyBlobEntryPersistence lazyBlobEntryPersistence) {

		this.lazyBlobEntryPersistence = lazyBlobEntryPersistence;
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

	@Override
	public LazyBlobEntryBlob1BlobModel getBlob1BlobModel(
		Serializable primaryKey) {

		Session session = null;

		try {
			session = lazyBlobEntryPersistence.openSession();

			return (LazyBlobEntryBlob1BlobModel)session.get(
				LazyBlobEntryBlob1BlobModel.class, primaryKey);
		}
		catch (Exception exception) {
			throw lazyBlobEntryPersistence.processException(exception);
		}
		finally {
			lazyBlobEntryPersistence.closeSession(session);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public InputStream openBlob1InputStream(long lazyBlobEntryId) {
		try {
			LazyBlobEntryBlob1BlobModel LazyBlobEntryBlob1BlobModel =
				getBlob1BlobModel(lazyBlobEntryId);

			Blob blob = LazyBlobEntryBlob1BlobModel.getBlob1Blob();

			if (blob == null) {
				return _EMPTY_INPUT_STREAM;
			}

			InputStream inputStream = blob.getBinaryStream();

			if (_useTempFile) {
				inputStream = new AutoDeleteFileInputStream(
					_file.createTempFile(inputStream));
			}

			return inputStream;
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	@Override
	public LazyBlobEntryBlob2BlobModel getBlob2BlobModel(
		Serializable primaryKey) {

		Session session = null;

		try {
			session = lazyBlobEntryPersistence.openSession();

			return (LazyBlobEntryBlob2BlobModel)session.get(
				LazyBlobEntryBlob2BlobModel.class, primaryKey);
		}
		catch (Exception exception) {
			throw lazyBlobEntryPersistence.processException(exception);
		}
		finally {
			lazyBlobEntryPersistence.closeSession(session);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public InputStream openBlob2InputStream(long lazyBlobEntryId) {
		try {
			LazyBlobEntryBlob2BlobModel LazyBlobEntryBlob2BlobModel =
				getBlob2BlobModel(lazyBlobEntryId);

			Blob blob = LazyBlobEntryBlob2BlobModel.getBlob2Blob();

			if (blob == null) {
				return _EMPTY_INPUT_STREAM;
			}

			InputStream inputStream = blob.getBinaryStream();

			if (_useTempFile) {
				inputStream = new AutoDeleteFileInputStream(
					_file.createTempFile(inputStream));
			}

			return inputStream;
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.portal.tools.service.builder.test.model.LazyBlobEntry",
			lazyBlobEntryLocalService);

		DB db = DBManagerUtil.getDB();

		if ((db.getDBType() != DBType.DB2) &&
			(db.getDBType() != DBType.MYSQL) &&
			(db.getDBType() != DBType.MARIADB) &&
			(db.getDBType() != DBType.SYBASE)) {

			_useTempFile = true;
		}

		_setLocalServiceUtilService(lazyBlobEntryLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.tools.service.builder.test.model.LazyBlobEntry");

		_setLocalServiceUtilService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return LazyBlobEntryLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return LazyBlobEntry.class;
	}

	protected String getModelClassName() {
		return LazyBlobEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = lazyBlobEntryPersistence.getDataSource();

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
		LazyBlobEntryLocalService lazyBlobEntryLocalService) {

		try {
			Field field = LazyBlobEntryLocalServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, lazyBlobEntryLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(type = LazyBlobEntryLocalService.class)
	protected LazyBlobEntryLocalService lazyBlobEntryLocalService;

	@BeanReference(type = LazyBlobEntryPersistence.class)
	protected LazyBlobEntryPersistence lazyBlobEntryPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		LazyBlobEntryLocalServiceBaseImpl.class);

	@BeanReference(type = File.class)
	protected File _file;

	private static final InputStream _EMPTY_INPUT_STREAM =
		new UnsyncByteArrayInputStream(new byte[0]);

	private boolean _useTempFile;

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}