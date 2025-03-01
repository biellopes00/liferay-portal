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

package com.liferay.portal.workflow.kaleo.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.change.tracking.CTColumnResolutionType;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.change.tracking.helper.CTPersistenceHelper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.workflow.kaleo.exception.NoSuchDefinitionVersionException;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinitionVersion;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinitionVersionTable;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionVersionImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionVersionModelImpl;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoDefinitionVersionPersistence;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoDefinitionVersionUtil;
import com.liferay.portal.workflow.kaleo.service.persistence.impl.constants.KaleoPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the kaleo definition version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = KaleoDefinitionVersionPersistence.class)
public class KaleoDefinitionVersionPersistenceImpl
	extends BasePersistenceImpl<KaleoDefinitionVersion>
	implements KaleoDefinitionVersionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>KaleoDefinitionVersionUtil</code> to access the kaleo definition version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		KaleoDefinitionVersionImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the kaleo definition versions where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching kaleo definition versions
	 */
	@Override
	public List<KaleoDefinitionVersion> findByCompanyId(long companyId) {
		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo definition versions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoDefinitionVersionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo definition versions
	 * @param end the upper bound of the range of kaleo definition versions (not inclusive)
	 * @return the range of matching kaleo definition versions
	 */
	@Override
	public List<KaleoDefinitionVersion> findByCompanyId(
		long companyId, int start, int end) {

		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo definition versions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoDefinitionVersionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo definition versions
	 * @param end the upper bound of the range of kaleo definition versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo definition versions
	 */
	@Override
	public List<KaleoDefinitionVersion> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<KaleoDefinitionVersion> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the kaleo definition versions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoDefinitionVersionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo definition versions
	 * @param end the upper bound of the range of kaleo definition versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching kaleo definition versions
	 */
	@Override
	public List<KaleoDefinitionVersion> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<KaleoDefinitionVersion> orderByComparator,
		boolean useFinderCache) {

		boolean productionMode = ctPersistenceHelper.isProductionMode(
			KaleoDefinitionVersion.class);

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache && productionMode) {
				finderPath = _finderPathWithoutPaginationFindByCompanyId;
				finderArgs = new Object[] {companyId};
			}
		}
		else if (useFinderCache && productionMode) {
			finderPath = _finderPathWithPaginationFindByCompanyId;
			finderArgs = new Object[] {
				companyId, start, end, orderByComparator
			};
		}

		List<KaleoDefinitionVersion> list = null;

		if (useFinderCache && productionMode) {
			list = (List<KaleoDefinitionVersion>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (KaleoDefinitionVersion kaleoDefinitionVersion : list) {
					if (companyId != kaleoDefinitionVersion.getCompanyId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_KALEODEFINITIONVERSION_WHERE);

			sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(KaleoDefinitionVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				list = (List<KaleoDefinitionVersion>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache && productionMode) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first kaleo definition version in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo definition version
	 * @throws NoSuchDefinitionVersionException if a matching kaleo definition version could not be found
	 */
	@Override
	public KaleoDefinitionVersion findByCompanyId_First(
			long companyId,
			OrderByComparator<KaleoDefinitionVersion> orderByComparator)
		throws NoSuchDefinitionVersionException {

		KaleoDefinitionVersion kaleoDefinitionVersion = fetchByCompanyId_First(
			companyId, orderByComparator);

		if (kaleoDefinitionVersion != null) {
			return kaleoDefinitionVersion;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchDefinitionVersionException(sb.toString());
	}

	/**
	 * Returns the first kaleo definition version in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo definition version, or <code>null</code> if a matching kaleo definition version could not be found
	 */
	@Override
	public KaleoDefinitionVersion fetchByCompanyId_First(
		long companyId,
		OrderByComparator<KaleoDefinitionVersion> orderByComparator) {

		List<KaleoDefinitionVersion> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo definition version in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo definition version
	 * @throws NoSuchDefinitionVersionException if a matching kaleo definition version could not be found
	 */
	@Override
	public KaleoDefinitionVersion findByCompanyId_Last(
			long companyId,
			OrderByComparator<KaleoDefinitionVersion> orderByComparator)
		throws NoSuchDefinitionVersionException {

		KaleoDefinitionVersion kaleoDefinitionVersion = fetchByCompanyId_Last(
			companyId, orderByComparator);

		if (kaleoDefinitionVersion != null) {
			return kaleoDefinitionVersion;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchDefinitionVersionException(sb.toString());
	}

	/**
	 * Returns the last kaleo definition version in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo definition version, or <code>null</code> if a matching kaleo definition version could not be found
	 */
	@Override
	public KaleoDefinitionVersion fetchByCompanyId_Last(
		long companyId,
		OrderByComparator<KaleoDefinitionVersion> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<KaleoDefinitionVersion> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo definition versions before and after the current kaleo definition version in the ordered set where companyId = &#63;.
	 *
	 * @param kaleoDefinitionVersionId the primary key of the current kaleo definition version
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo definition version
	 * @throws NoSuchDefinitionVersionException if a kaleo definition version with the primary key could not be found
	 */
	@Override
	public KaleoDefinitionVersion[] findByCompanyId_PrevAndNext(
			long kaleoDefinitionVersionId, long companyId,
			OrderByComparator<KaleoDefinitionVersion> orderByComparator)
		throws NoSuchDefinitionVersionException {

		KaleoDefinitionVersion kaleoDefinitionVersion = findByPrimaryKey(
			kaleoDefinitionVersionId);

		Session session = null;

		try {
			session = openSession();

			KaleoDefinitionVersion[] array = new KaleoDefinitionVersionImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, kaleoDefinitionVersion, companyId, orderByComparator,
				true);

			array[1] = kaleoDefinitionVersion;

			array[2] = getByCompanyId_PrevAndNext(
				session, kaleoDefinitionVersion, companyId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoDefinitionVersion getByCompanyId_PrevAndNext(
		Session session, KaleoDefinitionVersion kaleoDefinitionVersion,
		long companyId,
		OrderByComparator<KaleoDefinitionVersion> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_KALEODEFINITIONVERSION_WHERE);

		sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(KaleoDefinitionVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						kaleoDefinitionVersion)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<KaleoDefinitionVersion> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo definition versions where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (KaleoDefinitionVersion kaleoDefinitionVersion :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(kaleoDefinitionVersion);
		}
	}

	/**
	 * Returns the number of kaleo definition versions where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching kaleo definition versions
	 */
	@Override
	public int countByCompanyId(long companyId) {
		boolean productionMode = ctPersistenceHelper.isProductionMode(
			KaleoDefinitionVersion.class);

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		Long count = null;

		if (productionMode) {
			finderPath = _finderPathCountByCompanyId;

			finderArgs = new Object[] {companyId};

			count = (Long)finderCache.getResult(finderPath, finderArgs, this);
		}

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_KALEODEFINITIONVERSION_WHERE);

			sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				count = (Long)query.uniqueResult();

				if (productionMode) {
					finderCache.putResult(finderPath, finderArgs, count);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 =
		"kaleoDefinitionVersion.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByC_N;
	private FinderPath _finderPathWithoutPaginationFindByC_N;
	private FinderPath _finderPathCountByC_N;

	/**
	 * Returns all the kaleo definition versions where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching kaleo definition versions
	 */
	@Override
	public List<KaleoDefinitionVersion> findByC_N(long companyId, String name) {
		return findByC_N(
			companyId, name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo definition versions where companyId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoDefinitionVersionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param start the lower bound of the range of kaleo definition versions
	 * @param end the upper bound of the range of kaleo definition versions (not inclusive)
	 * @return the range of matching kaleo definition versions
	 */
	@Override
	public List<KaleoDefinitionVersion> findByC_N(
		long companyId, String name, int start, int end) {

		return findByC_N(companyId, name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo definition versions where companyId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoDefinitionVersionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param start the lower bound of the range of kaleo definition versions
	 * @param end the upper bound of the range of kaleo definition versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo definition versions
	 */
	@Override
	public List<KaleoDefinitionVersion> findByC_N(
		long companyId, String name, int start, int end,
		OrderByComparator<KaleoDefinitionVersion> orderByComparator) {

		return findByC_N(companyId, name, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the kaleo definition versions where companyId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoDefinitionVersionModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param start the lower bound of the range of kaleo definition versions
	 * @param end the upper bound of the range of kaleo definition versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching kaleo definition versions
	 */
	@Override
	public List<KaleoDefinitionVersion> findByC_N(
		long companyId, String name, int start, int end,
		OrderByComparator<KaleoDefinitionVersion> orderByComparator,
		boolean useFinderCache) {

		name = Objects.toString(name, "");

		boolean productionMode = ctPersistenceHelper.isProductionMode(
			KaleoDefinitionVersion.class);

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache && productionMode) {
				finderPath = _finderPathWithoutPaginationFindByC_N;
				finderArgs = new Object[] {companyId, name};
			}
		}
		else if (useFinderCache && productionMode) {
			finderPath = _finderPathWithPaginationFindByC_N;
			finderArgs = new Object[] {
				companyId, name, start, end, orderByComparator
			};
		}

		List<KaleoDefinitionVersion> list = null;

		if (useFinderCache && productionMode) {
			list = (List<KaleoDefinitionVersion>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (KaleoDefinitionVersion kaleoDefinitionVersion : list) {
					if ((companyId != kaleoDefinitionVersion.getCompanyId()) ||
						!name.equals(kaleoDefinitionVersion.getName())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_KALEODEFINITIONVERSION_WHERE);

			sb.append(_FINDER_COLUMN_C_N_COMPANYID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_N_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_C_N_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(KaleoDefinitionVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindName) {
					queryPos.add(name);
				}

				list = (List<KaleoDefinitionVersion>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache && productionMode) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first kaleo definition version in the ordered set where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo definition version
	 * @throws NoSuchDefinitionVersionException if a matching kaleo definition version could not be found
	 */
	@Override
	public KaleoDefinitionVersion findByC_N_First(
			long companyId, String name,
			OrderByComparator<KaleoDefinitionVersion> orderByComparator)
		throws NoSuchDefinitionVersionException {

		KaleoDefinitionVersion kaleoDefinitionVersion = fetchByC_N_First(
			companyId, name, orderByComparator);

		if (kaleoDefinitionVersion != null) {
			return kaleoDefinitionVersion;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append(", name=");
		sb.append(name);

		sb.append("}");

		throw new NoSuchDefinitionVersionException(sb.toString());
	}

	/**
	 * Returns the first kaleo definition version in the ordered set where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo definition version, or <code>null</code> if a matching kaleo definition version could not be found
	 */
	@Override
	public KaleoDefinitionVersion fetchByC_N_First(
		long companyId, String name,
		OrderByComparator<KaleoDefinitionVersion> orderByComparator) {

		List<KaleoDefinitionVersion> list = findByC_N(
			companyId, name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo definition version in the ordered set where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo definition version
	 * @throws NoSuchDefinitionVersionException if a matching kaleo definition version could not be found
	 */
	@Override
	public KaleoDefinitionVersion findByC_N_Last(
			long companyId, String name,
			OrderByComparator<KaleoDefinitionVersion> orderByComparator)
		throws NoSuchDefinitionVersionException {

		KaleoDefinitionVersion kaleoDefinitionVersion = fetchByC_N_Last(
			companyId, name, orderByComparator);

		if (kaleoDefinitionVersion != null) {
			return kaleoDefinitionVersion;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append(", name=");
		sb.append(name);

		sb.append("}");

		throw new NoSuchDefinitionVersionException(sb.toString());
	}

	/**
	 * Returns the last kaleo definition version in the ordered set where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo definition version, or <code>null</code> if a matching kaleo definition version could not be found
	 */
	@Override
	public KaleoDefinitionVersion fetchByC_N_Last(
		long companyId, String name,
		OrderByComparator<KaleoDefinitionVersion> orderByComparator) {

		int count = countByC_N(companyId, name);

		if (count == 0) {
			return null;
		}

		List<KaleoDefinitionVersion> list = findByC_N(
			companyId, name, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo definition versions before and after the current kaleo definition version in the ordered set where companyId = &#63; and name = &#63;.
	 *
	 * @param kaleoDefinitionVersionId the primary key of the current kaleo definition version
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo definition version
	 * @throws NoSuchDefinitionVersionException if a kaleo definition version with the primary key could not be found
	 */
	@Override
	public KaleoDefinitionVersion[] findByC_N_PrevAndNext(
			long kaleoDefinitionVersionId, long companyId, String name,
			OrderByComparator<KaleoDefinitionVersion> orderByComparator)
		throws NoSuchDefinitionVersionException {

		name = Objects.toString(name, "");

		KaleoDefinitionVersion kaleoDefinitionVersion = findByPrimaryKey(
			kaleoDefinitionVersionId);

		Session session = null;

		try {
			session = openSession();

			KaleoDefinitionVersion[] array = new KaleoDefinitionVersionImpl[3];

			array[0] = getByC_N_PrevAndNext(
				session, kaleoDefinitionVersion, companyId, name,
				orderByComparator, true);

			array[1] = kaleoDefinitionVersion;

			array[2] = getByC_N_PrevAndNext(
				session, kaleoDefinitionVersion, companyId, name,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoDefinitionVersion getByC_N_PrevAndNext(
		Session session, KaleoDefinitionVersion kaleoDefinitionVersion,
		long companyId, String name,
		OrderByComparator<KaleoDefinitionVersion> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_KALEODEFINITIONVERSION_WHERE);

		sb.append(_FINDER_COLUMN_C_N_COMPANYID_2);

		boolean bindName = false;

		if (name.isEmpty()) {
			sb.append(_FINDER_COLUMN_C_N_NAME_3);
		}
		else {
			bindName = true;

			sb.append(_FINDER_COLUMN_C_N_NAME_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(KaleoDefinitionVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(companyId);

		if (bindName) {
			queryPos.add(name);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						kaleoDefinitionVersion)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<KaleoDefinitionVersion> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo definition versions where companyId = &#63; and name = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 */
	@Override
	public void removeByC_N(long companyId, String name) {
		for (KaleoDefinitionVersion kaleoDefinitionVersion :
				findByC_N(
					companyId, name, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(kaleoDefinitionVersion);
		}
	}

	/**
	 * Returns the number of kaleo definition versions where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the number of matching kaleo definition versions
	 */
	@Override
	public int countByC_N(long companyId, String name) {
		name = Objects.toString(name, "");

		boolean productionMode = ctPersistenceHelper.isProductionMode(
			KaleoDefinitionVersion.class);

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		Long count = null;

		if (productionMode) {
			finderPath = _finderPathCountByC_N;

			finderArgs = new Object[] {companyId, name};

			count = (Long)finderCache.getResult(finderPath, finderArgs, this);
		}

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_KALEODEFINITIONVERSION_WHERE);

			sb.append(_FINDER_COLUMN_C_N_COMPANYID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_N_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_C_N_NAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindName) {
					queryPos.add(name);
				}

				count = (Long)query.uniqueResult();

				if (productionMode) {
					finderCache.putResult(finderPath, finderArgs, count);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_N_COMPANYID_2 =
		"kaleoDefinitionVersion.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_N_NAME_2 =
		"kaleoDefinitionVersion.name = ?";

	private static final String _FINDER_COLUMN_C_N_NAME_3 =
		"(kaleoDefinitionVersion.name IS NULL OR kaleoDefinitionVersion.name = '')";

	private FinderPath _finderPathFetchByC_N_V;
	private FinderPath _finderPathCountByC_N_V;

	/**
	 * Returns the kaleo definition version where companyId = &#63; and name = &#63; and version = &#63; or throws a <code>NoSuchDefinitionVersionException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @return the matching kaleo definition version
	 * @throws NoSuchDefinitionVersionException if a matching kaleo definition version could not be found
	 */
	@Override
	public KaleoDefinitionVersion findByC_N_V(
			long companyId, String name, String version)
		throws NoSuchDefinitionVersionException {

		KaleoDefinitionVersion kaleoDefinitionVersion = fetchByC_N_V(
			companyId, name, version);

		if (kaleoDefinitionVersion == null) {
			StringBundler sb = new StringBundler(8);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("companyId=");
			sb.append(companyId);

			sb.append(", name=");
			sb.append(name);

			sb.append(", version=");
			sb.append(version);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchDefinitionVersionException(sb.toString());
		}

		return kaleoDefinitionVersion;
	}

	/**
	 * Returns the kaleo definition version where companyId = &#63; and name = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @return the matching kaleo definition version, or <code>null</code> if a matching kaleo definition version could not be found
	 */
	@Override
	public KaleoDefinitionVersion fetchByC_N_V(
		long companyId, String name, String version) {

		return fetchByC_N_V(companyId, name, version, true);
	}

	/**
	 * Returns the kaleo definition version where companyId = &#63; and name = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching kaleo definition version, or <code>null</code> if a matching kaleo definition version could not be found
	 */
	@Override
	public KaleoDefinitionVersion fetchByC_N_V(
		long companyId, String name, String version, boolean useFinderCache) {

		name = Objects.toString(name, "");
		version = Objects.toString(version, "");

		boolean productionMode = ctPersistenceHelper.isProductionMode(
			KaleoDefinitionVersion.class);

		Object[] finderArgs = null;

		if (useFinderCache && productionMode) {
			finderArgs = new Object[] {companyId, name, version};
		}

		Object result = null;

		if (useFinderCache && productionMode) {
			result = finderCache.getResult(
				_finderPathFetchByC_N_V, finderArgs, this);
		}

		if (result instanceof KaleoDefinitionVersion) {
			KaleoDefinitionVersion kaleoDefinitionVersion =
				(KaleoDefinitionVersion)result;

			if ((companyId != kaleoDefinitionVersion.getCompanyId()) ||
				!Objects.equals(name, kaleoDefinitionVersion.getName()) ||
				!Objects.equals(version, kaleoDefinitionVersion.getVersion())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(5);

			sb.append(_SQL_SELECT_KALEODEFINITIONVERSION_WHERE);

			sb.append(_FINDER_COLUMN_C_N_V_COMPANYID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_N_V_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_C_N_V_NAME_2);
			}

			boolean bindVersion = false;

			if (version.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_N_V_VERSION_3);
			}
			else {
				bindVersion = true;

				sb.append(_FINDER_COLUMN_C_N_V_VERSION_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindName) {
					queryPos.add(name);
				}

				if (bindVersion) {
					queryPos.add(version);
				}

				List<KaleoDefinitionVersion> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache && productionMode) {
						finderCache.putResult(
							_finderPathFetchByC_N_V, finderArgs, list);
					}
				}
				else {
					KaleoDefinitionVersion kaleoDefinitionVersion = list.get(0);

					result = kaleoDefinitionVersion;

					cacheResult(kaleoDefinitionVersion);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (KaleoDefinitionVersion)result;
		}
	}

	/**
	 * Removes the kaleo definition version where companyId = &#63; and name = &#63; and version = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @return the kaleo definition version that was removed
	 */
	@Override
	public KaleoDefinitionVersion removeByC_N_V(
			long companyId, String name, String version)
		throws NoSuchDefinitionVersionException {

		KaleoDefinitionVersion kaleoDefinitionVersion = findByC_N_V(
			companyId, name, version);

		return remove(kaleoDefinitionVersion);
	}

	/**
	 * Returns the number of kaleo definition versions where companyId = &#63; and name = &#63; and version = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @return the number of matching kaleo definition versions
	 */
	@Override
	public int countByC_N_V(long companyId, String name, String version) {
		name = Objects.toString(name, "");
		version = Objects.toString(version, "");

		boolean productionMode = ctPersistenceHelper.isProductionMode(
			KaleoDefinitionVersion.class);

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		Long count = null;

		if (productionMode) {
			finderPath = _finderPathCountByC_N_V;

			finderArgs = new Object[] {companyId, name, version};

			count = (Long)finderCache.getResult(finderPath, finderArgs, this);
		}

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_KALEODEFINITIONVERSION_WHERE);

			sb.append(_FINDER_COLUMN_C_N_V_COMPANYID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_N_V_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_C_N_V_NAME_2);
			}

			boolean bindVersion = false;

			if (version.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_N_V_VERSION_3);
			}
			else {
				bindVersion = true;

				sb.append(_FINDER_COLUMN_C_N_V_VERSION_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindName) {
					queryPos.add(name);
				}

				if (bindVersion) {
					queryPos.add(version);
				}

				count = (Long)query.uniqueResult();

				if (productionMode) {
					finderCache.putResult(finderPath, finderArgs, count);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_N_V_COMPANYID_2 =
		"kaleoDefinitionVersion.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_N_V_NAME_2 =
		"kaleoDefinitionVersion.name = ? AND ";

	private static final String _FINDER_COLUMN_C_N_V_NAME_3 =
		"(kaleoDefinitionVersion.name IS NULL OR kaleoDefinitionVersion.name = '') AND ";

	private static final String _FINDER_COLUMN_C_N_V_VERSION_2 =
		"kaleoDefinitionVersion.version = ?";

	private static final String _FINDER_COLUMN_C_N_V_VERSION_3 =
		"(kaleoDefinitionVersion.version IS NULL OR kaleoDefinitionVersion.version = '')";

	public KaleoDefinitionVersionPersistenceImpl() {
		setModelClass(KaleoDefinitionVersion.class);

		setModelImplClass(KaleoDefinitionVersionImpl.class);
		setModelPKClass(long.class);

		setTable(KaleoDefinitionVersionTable.INSTANCE);
	}

	/**
	 * Caches the kaleo definition version in the entity cache if it is enabled.
	 *
	 * @param kaleoDefinitionVersion the kaleo definition version
	 */
	@Override
	public void cacheResult(KaleoDefinitionVersion kaleoDefinitionVersion) {
		if (kaleoDefinitionVersion.getCtCollectionId() != 0) {
			return;
		}

		entityCache.putResult(
			KaleoDefinitionVersionImpl.class,
			kaleoDefinitionVersion.getPrimaryKey(), kaleoDefinitionVersion);

		finderCache.putResult(
			_finderPathFetchByC_N_V,
			new Object[] {
				kaleoDefinitionVersion.getCompanyId(),
				kaleoDefinitionVersion.getName(),
				kaleoDefinitionVersion.getVersion()
			},
			kaleoDefinitionVersion);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the kaleo definition versions in the entity cache if it is enabled.
	 *
	 * @param kaleoDefinitionVersions the kaleo definition versions
	 */
	@Override
	public void cacheResult(
		List<KaleoDefinitionVersion> kaleoDefinitionVersions) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (kaleoDefinitionVersions.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (KaleoDefinitionVersion kaleoDefinitionVersion :
				kaleoDefinitionVersions) {

			if (kaleoDefinitionVersion.getCtCollectionId() != 0) {
				continue;
			}

			if (entityCache.getResult(
					KaleoDefinitionVersionImpl.class,
					kaleoDefinitionVersion.getPrimaryKey()) == null) {

				cacheResult(kaleoDefinitionVersion);
			}
		}
	}

	/**
	 * Clears the cache for all kaleo definition versions.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(KaleoDefinitionVersionImpl.class);

		finderCache.clearCache(KaleoDefinitionVersionImpl.class);
	}

	/**
	 * Clears the cache for the kaleo definition version.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KaleoDefinitionVersion kaleoDefinitionVersion) {
		entityCache.removeResult(
			KaleoDefinitionVersionImpl.class, kaleoDefinitionVersion);
	}

	@Override
	public void clearCache(
		List<KaleoDefinitionVersion> kaleoDefinitionVersions) {

		for (KaleoDefinitionVersion kaleoDefinitionVersion :
				kaleoDefinitionVersions) {

			entityCache.removeResult(
				KaleoDefinitionVersionImpl.class, kaleoDefinitionVersion);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(KaleoDefinitionVersionImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				KaleoDefinitionVersionImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		KaleoDefinitionVersionModelImpl kaleoDefinitionVersionModelImpl) {

		Object[] args = new Object[] {
			kaleoDefinitionVersionModelImpl.getCompanyId(),
			kaleoDefinitionVersionModelImpl.getName(),
			kaleoDefinitionVersionModelImpl.getVersion()
		};

		finderCache.putResult(_finderPathCountByC_N_V, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByC_N_V, args, kaleoDefinitionVersionModelImpl);
	}

	/**
	 * Creates a new kaleo definition version with the primary key. Does not add the kaleo definition version to the database.
	 *
	 * @param kaleoDefinitionVersionId the primary key for the new kaleo definition version
	 * @return the new kaleo definition version
	 */
	@Override
	public KaleoDefinitionVersion create(long kaleoDefinitionVersionId) {
		KaleoDefinitionVersion kaleoDefinitionVersion =
			new KaleoDefinitionVersionImpl();

		kaleoDefinitionVersion.setNew(true);
		kaleoDefinitionVersion.setPrimaryKey(kaleoDefinitionVersionId);

		kaleoDefinitionVersion.setCompanyId(CompanyThreadLocal.getCompanyId());

		return kaleoDefinitionVersion;
	}

	/**
	 * Removes the kaleo definition version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoDefinitionVersionId the primary key of the kaleo definition version
	 * @return the kaleo definition version that was removed
	 * @throws NoSuchDefinitionVersionException if a kaleo definition version with the primary key could not be found
	 */
	@Override
	public KaleoDefinitionVersion remove(long kaleoDefinitionVersionId)
		throws NoSuchDefinitionVersionException {

		return remove((Serializable)kaleoDefinitionVersionId);
	}

	/**
	 * Removes the kaleo definition version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo definition version
	 * @return the kaleo definition version that was removed
	 * @throws NoSuchDefinitionVersionException if a kaleo definition version with the primary key could not be found
	 */
	@Override
	public KaleoDefinitionVersion remove(Serializable primaryKey)
		throws NoSuchDefinitionVersionException {

		Session session = null;

		try {
			session = openSession();

			KaleoDefinitionVersion kaleoDefinitionVersion =
				(KaleoDefinitionVersion)session.get(
					KaleoDefinitionVersionImpl.class, primaryKey);

			if (kaleoDefinitionVersion == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDefinitionVersionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(kaleoDefinitionVersion);
		}
		catch (NoSuchDefinitionVersionException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected KaleoDefinitionVersion removeImpl(
		KaleoDefinitionVersion kaleoDefinitionVersion) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(kaleoDefinitionVersion)) {
				kaleoDefinitionVersion = (KaleoDefinitionVersion)session.get(
					KaleoDefinitionVersionImpl.class,
					kaleoDefinitionVersion.getPrimaryKeyObj());
			}

			if ((kaleoDefinitionVersion != null) &&
				ctPersistenceHelper.isRemove(kaleoDefinitionVersion)) {

				session.delete(kaleoDefinitionVersion);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (kaleoDefinitionVersion != null) {
			clearCache(kaleoDefinitionVersion);
		}

		return kaleoDefinitionVersion;
	}

	@Override
	public KaleoDefinitionVersion updateImpl(
		KaleoDefinitionVersion kaleoDefinitionVersion) {

		boolean isNew = kaleoDefinitionVersion.isNew();

		if (!(kaleoDefinitionVersion instanceof
				KaleoDefinitionVersionModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(kaleoDefinitionVersion.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					kaleoDefinitionVersion);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in kaleoDefinitionVersion proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom KaleoDefinitionVersion implementation " +
					kaleoDefinitionVersion.getClass());
		}

		KaleoDefinitionVersionModelImpl kaleoDefinitionVersionModelImpl =
			(KaleoDefinitionVersionModelImpl)kaleoDefinitionVersion;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (kaleoDefinitionVersion.getCreateDate() == null)) {
			if (serviceContext == null) {
				kaleoDefinitionVersion.setCreateDate(date);
			}
			else {
				kaleoDefinitionVersion.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!kaleoDefinitionVersionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				kaleoDefinitionVersion.setModifiedDate(date);
			}
			else {
				kaleoDefinitionVersion.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (ctPersistenceHelper.isInsert(kaleoDefinitionVersion)) {
				if (!isNew) {
					session.evict(
						KaleoDefinitionVersionImpl.class,
						kaleoDefinitionVersion.getPrimaryKeyObj());
				}

				session.save(kaleoDefinitionVersion);
			}
			else {
				kaleoDefinitionVersion = (KaleoDefinitionVersion)session.merge(
					kaleoDefinitionVersion);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (kaleoDefinitionVersion.getCtCollectionId() != 0) {
			if (isNew) {
				kaleoDefinitionVersion.setNew(false);
			}

			kaleoDefinitionVersion.resetOriginalValues();

			return kaleoDefinitionVersion;
		}

		entityCache.putResult(
			KaleoDefinitionVersionImpl.class, kaleoDefinitionVersionModelImpl,
			false, true);

		cacheUniqueFindersCache(kaleoDefinitionVersionModelImpl);

		if (isNew) {
			kaleoDefinitionVersion.setNew(false);
		}

		kaleoDefinitionVersion.resetOriginalValues();

		return kaleoDefinitionVersion;
	}

	/**
	 * Returns the kaleo definition version with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo definition version
	 * @return the kaleo definition version
	 * @throws NoSuchDefinitionVersionException if a kaleo definition version with the primary key could not be found
	 */
	@Override
	public KaleoDefinitionVersion findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDefinitionVersionException {

		KaleoDefinitionVersion kaleoDefinitionVersion = fetchByPrimaryKey(
			primaryKey);

		if (kaleoDefinitionVersion == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDefinitionVersionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return kaleoDefinitionVersion;
	}

	/**
	 * Returns the kaleo definition version with the primary key or throws a <code>NoSuchDefinitionVersionException</code> if it could not be found.
	 *
	 * @param kaleoDefinitionVersionId the primary key of the kaleo definition version
	 * @return the kaleo definition version
	 * @throws NoSuchDefinitionVersionException if a kaleo definition version with the primary key could not be found
	 */
	@Override
	public KaleoDefinitionVersion findByPrimaryKey(
			long kaleoDefinitionVersionId)
		throws NoSuchDefinitionVersionException {

		return findByPrimaryKey((Serializable)kaleoDefinitionVersionId);
	}

	/**
	 * Returns the kaleo definition version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo definition version
	 * @return the kaleo definition version, or <code>null</code> if a kaleo definition version with the primary key could not be found
	 */
	@Override
	public KaleoDefinitionVersion fetchByPrimaryKey(Serializable primaryKey) {
		if (ctPersistenceHelper.isProductionMode(
				KaleoDefinitionVersion.class, primaryKey)) {

			return super.fetchByPrimaryKey(primaryKey);
		}

		KaleoDefinitionVersion kaleoDefinitionVersion = null;

		Session session = null;

		try {
			session = openSession();

			kaleoDefinitionVersion = (KaleoDefinitionVersion)session.get(
				KaleoDefinitionVersionImpl.class, primaryKey);

			if (kaleoDefinitionVersion != null) {
				cacheResult(kaleoDefinitionVersion);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		return kaleoDefinitionVersion;
	}

	/**
	 * Returns the kaleo definition version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoDefinitionVersionId the primary key of the kaleo definition version
	 * @return the kaleo definition version, or <code>null</code> if a kaleo definition version with the primary key could not be found
	 */
	@Override
	public KaleoDefinitionVersion fetchByPrimaryKey(
		long kaleoDefinitionVersionId) {

		return fetchByPrimaryKey((Serializable)kaleoDefinitionVersionId);
	}

	@Override
	public Map<Serializable, KaleoDefinitionVersion> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (ctPersistenceHelper.isProductionMode(
				KaleoDefinitionVersion.class)) {

			return super.fetchByPrimaryKeys(primaryKeys);
		}

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, KaleoDefinitionVersion> map =
			new HashMap<Serializable, KaleoDefinitionVersion>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			KaleoDefinitionVersion kaleoDefinitionVersion = fetchByPrimaryKey(
				primaryKey);

			if (kaleoDefinitionVersion != null) {
				map.put(primaryKey, kaleoDefinitionVersion);
			}

			return map;
		}

		if ((databaseInMaxParameters > 0) &&
			(primaryKeys.size() > databaseInMaxParameters)) {

			Iterator<Serializable> iterator = primaryKeys.iterator();

			while (iterator.hasNext()) {
				Set<Serializable> page = new HashSet<>();

				for (int i = 0;
					 (i < databaseInMaxParameters) && iterator.hasNext(); i++) {

					page.add(iterator.next());
				}

				map.putAll(fetchByPrimaryKeys(page));
			}

			return map;
		}

		StringBundler sb = new StringBundler((primaryKeys.size() * 2) + 1);

		sb.append(getSelectSQL());
		sb.append(" WHERE ");
		sb.append(getPKDBName());
		sb.append(" IN (");

		for (Serializable primaryKey : primaryKeys) {
			sb.append((long)primaryKey);

			sb.append(",");
		}

		sb.setIndex(sb.index() - 1);

		sb.append(")");

		String sql = sb.toString();

		Session session = null;

		try {
			session = openSession();

			Query query = session.createQuery(sql);

			for (KaleoDefinitionVersion kaleoDefinitionVersion :
					(List<KaleoDefinitionVersion>)query.list()) {

				map.put(
					kaleoDefinitionVersion.getPrimaryKeyObj(),
					kaleoDefinitionVersion);

				cacheResult(kaleoDefinitionVersion);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the kaleo definition versions.
	 *
	 * @return the kaleo definition versions
	 */
	@Override
	public List<KaleoDefinitionVersion> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo definition versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoDefinitionVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo definition versions
	 * @param end the upper bound of the range of kaleo definition versions (not inclusive)
	 * @return the range of kaleo definition versions
	 */
	@Override
	public List<KaleoDefinitionVersion> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo definition versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoDefinitionVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo definition versions
	 * @param end the upper bound of the range of kaleo definition versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo definition versions
	 */
	@Override
	public List<KaleoDefinitionVersion> findAll(
		int start, int end,
		OrderByComparator<KaleoDefinitionVersion> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the kaleo definition versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>KaleoDefinitionVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo definition versions
	 * @param end the upper bound of the range of kaleo definition versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of kaleo definition versions
	 */
	@Override
	public List<KaleoDefinitionVersion> findAll(
		int start, int end,
		OrderByComparator<KaleoDefinitionVersion> orderByComparator,
		boolean useFinderCache) {

		boolean productionMode = ctPersistenceHelper.isProductionMode(
			KaleoDefinitionVersion.class);

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache && productionMode) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache && productionMode) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<KaleoDefinitionVersion> list = null;

		if (useFinderCache && productionMode) {
			list = (List<KaleoDefinitionVersion>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_KALEODEFINITIONVERSION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_KALEODEFINITIONVERSION;

				sql = sql.concat(KaleoDefinitionVersionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<KaleoDefinitionVersion>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache && productionMode) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the kaleo definition versions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (KaleoDefinitionVersion kaleoDefinitionVersion : findAll()) {
			remove(kaleoDefinitionVersion);
		}
	}

	/**
	 * Returns the number of kaleo definition versions.
	 *
	 * @return the number of kaleo definition versions
	 */
	@Override
	public int countAll() {
		boolean productionMode = ctPersistenceHelper.isProductionMode(
			KaleoDefinitionVersion.class);

		Long count = null;

		if (productionMode) {
			count = (Long)finderCache.getResult(
				_finderPathCountAll, FINDER_ARGS_EMPTY, this);
		}

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_KALEODEFINITIONVERSION);

				count = (Long)query.uniqueResult();

				if (productionMode) {
					finderCache.putResult(
						_finderPathCountAll, FINDER_ARGS_EMPTY, count);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "kaleoDefinitionVersionId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_KALEODEFINITIONVERSION;
	}

	@Override
	public Set<String> getCTColumnNames(
		CTColumnResolutionType ctColumnResolutionType) {

		return _ctColumnNamesMap.getOrDefault(
			ctColumnResolutionType, Collections.emptySet());
	}

	@Override
	public List<String> getMappingTableNames() {
		return _mappingTableNames;
	}

	@Override
	public Map<String, Integer> getTableColumnsMap() {
		return KaleoDefinitionVersionModelImpl.TABLE_COLUMNS_MAP;
	}

	@Override
	public String getTableName() {
		return "KaleoDefinitionVersion";
	}

	@Override
	public List<String[]> getUniqueIndexColumnNames() {
		return _uniqueIndexColumnNames;
	}

	private static final Map<CTColumnResolutionType, Set<String>>
		_ctColumnNamesMap = new EnumMap<CTColumnResolutionType, Set<String>>(
			CTColumnResolutionType.class);
	private static final List<String> _mappingTableNames =
		new ArrayList<String>();
	private static final List<String[]> _uniqueIndexColumnNames =
		new ArrayList<String[]>();

	static {
		Set<String> ctControlColumnNames = new HashSet<String>();
		Set<String> ctIgnoreColumnNames = new HashSet<String>();
		Set<String> ctStrictColumnNames = new HashSet<String>();

		ctControlColumnNames.add("mvccVersion");
		ctControlColumnNames.add("ctCollectionId");
		ctStrictColumnNames.add("groupId");
		ctStrictColumnNames.add("companyId");
		ctStrictColumnNames.add("userId");
		ctStrictColumnNames.add("userName");
		ctStrictColumnNames.add("createDate");
		ctIgnoreColumnNames.add("modifiedDate");
		ctStrictColumnNames.add("kaleoDefinitionId");
		ctStrictColumnNames.add("name");
		ctStrictColumnNames.add("title");
		ctStrictColumnNames.add("description");
		ctStrictColumnNames.add("content");
		ctStrictColumnNames.add("version");
		ctStrictColumnNames.add("startKaleoNodeId");
		ctStrictColumnNames.add("status");
		ctStrictColumnNames.add("statusByUserId");
		ctStrictColumnNames.add("statusByUserName");
		ctStrictColumnNames.add("statusDate");

		_ctColumnNamesMap.put(
			CTColumnResolutionType.CONTROL, ctControlColumnNames);
		_ctColumnNamesMap.put(
			CTColumnResolutionType.IGNORE, ctIgnoreColumnNames);
		_ctColumnNamesMap.put(
			CTColumnResolutionType.PK,
			Collections.singleton("kaleoDefinitionVersionId"));
		_ctColumnNamesMap.put(
			CTColumnResolutionType.STRICT, ctStrictColumnNames);

		_uniqueIndexColumnNames.add(
			new String[] {"companyId", "name", "version"});
	}

	/**
	 * Initializes the kaleo definition version persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByCompanyId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"companyId"}, true);

		_finderPathWithoutPaginationFindByCompanyId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] {Long.class.getName()}, new String[] {"companyId"},
			true);

		_finderPathCountByCompanyId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] {Long.class.getName()}, new String[] {"companyId"},
			false);

		_finderPathWithPaginationFindByC_N = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_N",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"companyId", "name"}, true);

		_finderPathWithoutPaginationFindByC_N = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_N",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"companyId", "name"}, true);

		_finderPathCountByC_N = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_N",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"companyId", "name"}, false);

		_finderPathFetchByC_N_V = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByC_N_V",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			new String[] {"companyId", "name", "version"}, true);

		_finderPathCountByC_N_V = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_N_V",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			new String[] {"companyId", "name", "version"}, false);

		_setKaleoDefinitionVersionUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setKaleoDefinitionVersionUtilPersistence(null);

		entityCache.removeCache(KaleoDefinitionVersionImpl.class.getName());
	}

	private void _setKaleoDefinitionVersionUtilPersistence(
		KaleoDefinitionVersionPersistence kaleoDefinitionVersionPersistence) {

		try {
			Field field = KaleoDefinitionVersionUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, kaleoDefinitionVersionPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = KaleoPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = KaleoPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = KaleoPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected CTPersistenceHelper ctPersistenceHelper;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_KALEODEFINITIONVERSION =
		"SELECT kaleoDefinitionVersion FROM KaleoDefinitionVersion kaleoDefinitionVersion";

	private static final String _SQL_SELECT_KALEODEFINITIONVERSION_WHERE =
		"SELECT kaleoDefinitionVersion FROM KaleoDefinitionVersion kaleoDefinitionVersion WHERE ";

	private static final String _SQL_COUNT_KALEODEFINITIONVERSION =
		"SELECT COUNT(kaleoDefinitionVersion) FROM KaleoDefinitionVersion kaleoDefinitionVersion";

	private static final String _SQL_COUNT_KALEODEFINITIONVERSION_WHERE =
		"SELECT COUNT(kaleoDefinitionVersion) FROM KaleoDefinitionVersion kaleoDefinitionVersion WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"kaleoDefinitionVersion.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No KaleoDefinitionVersion exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No KaleoDefinitionVersion exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		KaleoDefinitionVersionPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}