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

package com.liferay.commerce.product.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.exception.NoSuchCPFriendlyUrlEntryException;
import com.liferay.commerce.product.model.CPFriendlyUrlEntry;
import com.liferay.commerce.product.model.impl.CPFriendlyUrlEntryImpl;
import com.liferay.commerce.product.model.impl.CPFriendlyUrlEntryModelImpl;
import com.liferay.commerce.product.service.persistence.CPFriendlyUrlEntryPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the cp friendly url entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see CPFriendlyUrlEntryPersistence
 * @see com.liferay.commerce.product.service.persistence.CPFriendlyUrlEntryUtil
 * @generated
 */
@ProviderType
public class CPFriendlyUrlEntryPersistenceImpl extends BasePersistenceImpl<CPFriendlyUrlEntry>
	implements CPFriendlyUrlEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CPFriendlyUrlEntryUtil} to access the cp friendly url entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CPFriendlyUrlEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CPFriendlyUrlEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPFriendlyUrlEntryModelImpl.FINDER_CACHE_ENABLED,
			CPFriendlyUrlEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CPFriendlyUrlEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPFriendlyUrlEntryModelImpl.FINDER_CACHE_ENABLED,
			CPFriendlyUrlEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CPFriendlyUrlEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPFriendlyUrlEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(CPFriendlyUrlEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPFriendlyUrlEntryModelImpl.FINDER_CACHE_ENABLED,
			CPFriendlyUrlEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(CPFriendlyUrlEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPFriendlyUrlEntryModelImpl.FINDER_CACHE_ENABLED,
			CPFriendlyUrlEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			CPFriendlyUrlEntryModelImpl.UUID_COLUMN_BITMASK |
			CPFriendlyUrlEntryModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			CPFriendlyUrlEntryModelImpl.CLASSPK_COLUMN_BITMASK |
			CPFriendlyUrlEntryModelImpl.URLTITLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(CPFriendlyUrlEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPFriendlyUrlEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the cp friendly url entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cp friendly url entries
	 */
	@Override
	public List<CPFriendlyUrlEntry> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp friendly url entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPFriendlyUrlEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @return the range of matching cp friendly url entries
	 */
	@Override
	public List<CPFriendlyUrlEntry> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp friendly url entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPFriendlyUrlEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp friendly url entries
	 */
	@Override
	public List<CPFriendlyUrlEntry> findByUuid(String uuid, int start, int end,
		OrderByComparator<CPFriendlyUrlEntry> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp friendly url entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPFriendlyUrlEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp friendly url entries
	 */
	@Override
	public List<CPFriendlyUrlEntry> findByUuid(String uuid, int start, int end,
		OrderByComparator<CPFriendlyUrlEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<CPFriendlyUrlEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CPFriendlyUrlEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPFriendlyUrlEntry cpFriendlyUrlEntry : list) {
					if (!Objects.equals(uuid, cpFriendlyUrlEntry.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CPFRIENDLYURLENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CPFriendlyUrlEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<CPFriendlyUrlEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPFriendlyUrlEntry>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first cp friendly url entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp friendly url entry
	 * @throws NoSuchCPFriendlyUrlEntryException if a matching cp friendly url entry could not be found
	 */
	@Override
	public CPFriendlyUrlEntry findByUuid_First(String uuid,
		OrderByComparator<CPFriendlyUrlEntry> orderByComparator)
		throws NoSuchCPFriendlyUrlEntryException {
		CPFriendlyUrlEntry cpFriendlyUrlEntry = fetchByUuid_First(uuid,
				orderByComparator);

		if (cpFriendlyUrlEntry != null) {
			return cpFriendlyUrlEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCPFriendlyUrlEntryException(msg.toString());
	}

	/**
	 * Returns the first cp friendly url entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	@Override
	public CPFriendlyUrlEntry fetchByUuid_First(String uuid,
		OrderByComparator<CPFriendlyUrlEntry> orderByComparator) {
		List<CPFriendlyUrlEntry> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp friendly url entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp friendly url entry
	 * @throws NoSuchCPFriendlyUrlEntryException if a matching cp friendly url entry could not be found
	 */
	@Override
	public CPFriendlyUrlEntry findByUuid_Last(String uuid,
		OrderByComparator<CPFriendlyUrlEntry> orderByComparator)
		throws NoSuchCPFriendlyUrlEntryException {
		CPFriendlyUrlEntry cpFriendlyUrlEntry = fetchByUuid_Last(uuid,
				orderByComparator);

		if (cpFriendlyUrlEntry != null) {
			return cpFriendlyUrlEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCPFriendlyUrlEntryException(msg.toString());
	}

	/**
	 * Returns the last cp friendly url entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	@Override
	public CPFriendlyUrlEntry fetchByUuid_Last(String uuid,
		OrderByComparator<CPFriendlyUrlEntry> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CPFriendlyUrlEntry> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp friendly url entries before and after the current cp friendly url entry in the ordered set where uuid = &#63;.
	 *
	 * @param CPFriendlyUrlEntryId the primary key of the current cp friendly url entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp friendly url entry
	 * @throws NoSuchCPFriendlyUrlEntryException if a cp friendly url entry with the primary key could not be found
	 */
	@Override
	public CPFriendlyUrlEntry[] findByUuid_PrevAndNext(
		long CPFriendlyUrlEntryId, String uuid,
		OrderByComparator<CPFriendlyUrlEntry> orderByComparator)
		throws NoSuchCPFriendlyUrlEntryException {
		CPFriendlyUrlEntry cpFriendlyUrlEntry = findByPrimaryKey(CPFriendlyUrlEntryId);

		Session session = null;

		try {
			session = openSession();

			CPFriendlyUrlEntry[] array = new CPFriendlyUrlEntryImpl[3];

			array[0] = getByUuid_PrevAndNext(session, cpFriendlyUrlEntry, uuid,
					orderByComparator, true);

			array[1] = cpFriendlyUrlEntry;

			array[2] = getByUuid_PrevAndNext(session, cpFriendlyUrlEntry, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPFriendlyUrlEntry getByUuid_PrevAndNext(Session session,
		CPFriendlyUrlEntry cpFriendlyUrlEntry, String uuid,
		OrderByComparator<CPFriendlyUrlEntry> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CPFRIENDLYURLENTRY_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(CPFriendlyUrlEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(cpFriendlyUrlEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CPFriendlyUrlEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp friendly url entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CPFriendlyUrlEntry cpFriendlyUrlEntry : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(cpFriendlyUrlEntry);
		}
	}

	/**
	 * Returns the number of cp friendly url entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cp friendly url entries
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPFRIENDLYURLENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "cpFriendlyUrlEntry.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "cpFriendlyUrlEntry.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(cpFriendlyUrlEntry.uuid IS NULL OR cpFriendlyUrlEntry.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(CPFriendlyUrlEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPFriendlyUrlEntryModelImpl.FINDER_CACHE_ENABLED,
			CPFriendlyUrlEntryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			CPFriendlyUrlEntryModelImpl.UUID_COLUMN_BITMASK |
			CPFriendlyUrlEntryModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(CPFriendlyUrlEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPFriendlyUrlEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the cp friendly url entry where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchCPFriendlyUrlEntryException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching cp friendly url entry
	 * @throws NoSuchCPFriendlyUrlEntryException if a matching cp friendly url entry could not be found
	 */
	@Override
	public CPFriendlyUrlEntry findByUUID_G(String uuid, long groupId)
		throws NoSuchCPFriendlyUrlEntryException {
		CPFriendlyUrlEntry cpFriendlyUrlEntry = fetchByUUID_G(uuid, groupId);

		if (cpFriendlyUrlEntry == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCPFriendlyUrlEntryException(msg.toString());
		}

		return cpFriendlyUrlEntry;
	}

	/**
	 * Returns the cp friendly url entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	@Override
	public CPFriendlyUrlEntry fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the cp friendly url entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	@Override
	public CPFriendlyUrlEntry fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof CPFriendlyUrlEntry) {
			CPFriendlyUrlEntry cpFriendlyUrlEntry = (CPFriendlyUrlEntry)result;

			if (!Objects.equals(uuid, cpFriendlyUrlEntry.getUuid()) ||
					(groupId != cpFriendlyUrlEntry.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CPFRIENDLYURLENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<CPFriendlyUrlEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					CPFriendlyUrlEntry cpFriendlyUrlEntry = list.get(0);

					result = cpFriendlyUrlEntry;

					cacheResult(cpFriendlyUrlEntry);

					if ((cpFriendlyUrlEntry.getUuid() == null) ||
							!cpFriendlyUrlEntry.getUuid().equals(uuid) ||
							(cpFriendlyUrlEntry.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, cpFriendlyUrlEntry);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (CPFriendlyUrlEntry)result;
		}
	}

	/**
	 * Removes the cp friendly url entry where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the cp friendly url entry that was removed
	 */
	@Override
	public CPFriendlyUrlEntry removeByUUID_G(String uuid, long groupId)
		throws NoSuchCPFriendlyUrlEntryException {
		CPFriendlyUrlEntry cpFriendlyUrlEntry = findByUUID_G(uuid, groupId);

		return remove(cpFriendlyUrlEntry);
	}

	/**
	 * Returns the number of cp friendly url entries where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching cp friendly url entries
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPFRIENDLYURLENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "cpFriendlyUrlEntry.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "cpFriendlyUrlEntry.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(cpFriendlyUrlEntry.uuid IS NULL OR cpFriendlyUrlEntry.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "cpFriendlyUrlEntry.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(CPFriendlyUrlEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPFriendlyUrlEntryModelImpl.FINDER_CACHE_ENABLED,
			CPFriendlyUrlEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(CPFriendlyUrlEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPFriendlyUrlEntryModelImpl.FINDER_CACHE_ENABLED,
			CPFriendlyUrlEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			CPFriendlyUrlEntryModelImpl.UUID_COLUMN_BITMASK |
			CPFriendlyUrlEntryModelImpl.COMPANYID_COLUMN_BITMASK |
			CPFriendlyUrlEntryModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			CPFriendlyUrlEntryModelImpl.CLASSPK_COLUMN_BITMASK |
			CPFriendlyUrlEntryModelImpl.URLTITLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(CPFriendlyUrlEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPFriendlyUrlEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the cp friendly url entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching cp friendly url entries
	 */
	@Override
	public List<CPFriendlyUrlEntry> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp friendly url entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPFriendlyUrlEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @return the range of matching cp friendly url entries
	 */
	@Override
	public List<CPFriendlyUrlEntry> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp friendly url entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPFriendlyUrlEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp friendly url entries
	 */
	@Override
	public List<CPFriendlyUrlEntry> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<CPFriendlyUrlEntry> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp friendly url entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPFriendlyUrlEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp friendly url entries
	 */
	@Override
	public List<CPFriendlyUrlEntry> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<CPFriendlyUrlEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<CPFriendlyUrlEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CPFriendlyUrlEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPFriendlyUrlEntry cpFriendlyUrlEntry : list) {
					if (!Objects.equals(uuid, cpFriendlyUrlEntry.getUuid()) ||
							(companyId != cpFriendlyUrlEntry.getCompanyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_CPFRIENDLYURLENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CPFriendlyUrlEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<CPFriendlyUrlEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPFriendlyUrlEntry>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first cp friendly url entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp friendly url entry
	 * @throws NoSuchCPFriendlyUrlEntryException if a matching cp friendly url entry could not be found
	 */
	@Override
	public CPFriendlyUrlEntry findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<CPFriendlyUrlEntry> orderByComparator)
		throws NoSuchCPFriendlyUrlEntryException {
		CPFriendlyUrlEntry cpFriendlyUrlEntry = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (cpFriendlyUrlEntry != null) {
			return cpFriendlyUrlEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCPFriendlyUrlEntryException(msg.toString());
	}

	/**
	 * Returns the first cp friendly url entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	@Override
	public CPFriendlyUrlEntry fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<CPFriendlyUrlEntry> orderByComparator) {
		List<CPFriendlyUrlEntry> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp friendly url entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp friendly url entry
	 * @throws NoSuchCPFriendlyUrlEntryException if a matching cp friendly url entry could not be found
	 */
	@Override
	public CPFriendlyUrlEntry findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<CPFriendlyUrlEntry> orderByComparator)
		throws NoSuchCPFriendlyUrlEntryException {
		CPFriendlyUrlEntry cpFriendlyUrlEntry = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (cpFriendlyUrlEntry != null) {
			return cpFriendlyUrlEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCPFriendlyUrlEntryException(msg.toString());
	}

	/**
	 * Returns the last cp friendly url entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	@Override
	public CPFriendlyUrlEntry fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<CPFriendlyUrlEntry> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CPFriendlyUrlEntry> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp friendly url entries before and after the current cp friendly url entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param CPFriendlyUrlEntryId the primary key of the current cp friendly url entry
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp friendly url entry
	 * @throws NoSuchCPFriendlyUrlEntryException if a cp friendly url entry with the primary key could not be found
	 */
	@Override
	public CPFriendlyUrlEntry[] findByUuid_C_PrevAndNext(
		long CPFriendlyUrlEntryId, String uuid, long companyId,
		OrderByComparator<CPFriendlyUrlEntry> orderByComparator)
		throws NoSuchCPFriendlyUrlEntryException {
		CPFriendlyUrlEntry cpFriendlyUrlEntry = findByPrimaryKey(CPFriendlyUrlEntryId);

		Session session = null;

		try {
			session = openSession();

			CPFriendlyUrlEntry[] array = new CPFriendlyUrlEntryImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, cpFriendlyUrlEntry,
					uuid, companyId, orderByComparator, true);

			array[1] = cpFriendlyUrlEntry;

			array[2] = getByUuid_C_PrevAndNext(session, cpFriendlyUrlEntry,
					uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPFriendlyUrlEntry getByUuid_C_PrevAndNext(Session session,
		CPFriendlyUrlEntry cpFriendlyUrlEntry, String uuid, long companyId,
		OrderByComparator<CPFriendlyUrlEntry> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_CPFRIENDLYURLENTRY_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(CPFriendlyUrlEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(cpFriendlyUrlEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CPFriendlyUrlEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp friendly url entries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CPFriendlyUrlEntry cpFriendlyUrlEntry : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(cpFriendlyUrlEntry);
		}
	}

	/**
	 * Returns the number of cp friendly url entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching cp friendly url entries
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPFRIENDLYURLENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "cpFriendlyUrlEntry.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "cpFriendlyUrlEntry.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(cpFriendlyUrlEntry.uuid IS NULL OR cpFriendlyUrlEntry.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "cpFriendlyUrlEntry.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_U_L = new FinderPath(CPFriendlyUrlEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPFriendlyUrlEntryModelImpl.FINDER_CACHE_ENABLED,
			CPFriendlyUrlEntryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByG_U_L",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			CPFriendlyUrlEntryModelImpl.GROUPID_COLUMN_BITMASK |
			CPFriendlyUrlEntryModelImpl.URLTITLE_COLUMN_BITMASK |
			CPFriendlyUrlEntryModelImpl.LANGUAGEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_U_L = new FinderPath(CPFriendlyUrlEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPFriendlyUrlEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_U_L",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the cp friendly url entry where groupId = &#63; and urlTitle = &#63; and languageId = &#63; or throws a {@link NoSuchCPFriendlyUrlEntryException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param urlTitle the url title
	 * @param languageId the language ID
	 * @return the matching cp friendly url entry
	 * @throws NoSuchCPFriendlyUrlEntryException if a matching cp friendly url entry could not be found
	 */
	@Override
	public CPFriendlyUrlEntry findByG_U_L(long groupId, String urlTitle,
		String languageId) throws NoSuchCPFriendlyUrlEntryException {
		CPFriendlyUrlEntry cpFriendlyUrlEntry = fetchByG_U_L(groupId, urlTitle,
				languageId);

		if (cpFriendlyUrlEntry == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", urlTitle=");
			msg.append(urlTitle);

			msg.append(", languageId=");
			msg.append(languageId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCPFriendlyUrlEntryException(msg.toString());
		}

		return cpFriendlyUrlEntry;
	}

	/**
	 * Returns the cp friendly url entry where groupId = &#63; and urlTitle = &#63; and languageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param urlTitle the url title
	 * @param languageId the language ID
	 * @return the matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	@Override
	public CPFriendlyUrlEntry fetchByG_U_L(long groupId, String urlTitle,
		String languageId) {
		return fetchByG_U_L(groupId, urlTitle, languageId, true);
	}

	/**
	 * Returns the cp friendly url entry where groupId = &#63; and urlTitle = &#63; and languageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param urlTitle the url title
	 * @param languageId the language ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching cp friendly url entry, or <code>null</code> if a matching cp friendly url entry could not be found
	 */
	@Override
	public CPFriendlyUrlEntry fetchByG_U_L(long groupId, String urlTitle,
		String languageId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, urlTitle, languageId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_U_L,
					finderArgs, this);
		}

		if (result instanceof CPFriendlyUrlEntry) {
			CPFriendlyUrlEntry cpFriendlyUrlEntry = (CPFriendlyUrlEntry)result;

			if ((groupId != cpFriendlyUrlEntry.getGroupId()) ||
					!Objects.equals(urlTitle, cpFriendlyUrlEntry.getUrlTitle()) ||
					!Objects.equals(languageId,
						cpFriendlyUrlEntry.getLanguageId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_CPFRIENDLYURLENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_U_L_GROUPID_2);

			boolean bindUrlTitle = false;

			if (urlTitle == null) {
				query.append(_FINDER_COLUMN_G_U_L_URLTITLE_1);
			}
			else if (urlTitle.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_U_L_URLTITLE_3);
			}
			else {
				bindUrlTitle = true;

				query.append(_FINDER_COLUMN_G_U_L_URLTITLE_2);
			}

			boolean bindLanguageId = false;

			if (languageId == null) {
				query.append(_FINDER_COLUMN_G_U_L_LANGUAGEID_1);
			}
			else if (languageId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_U_L_LANGUAGEID_3);
			}
			else {
				bindLanguageId = true;

				query.append(_FINDER_COLUMN_G_U_L_LANGUAGEID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindUrlTitle) {
					qPos.add(urlTitle);
				}

				if (bindLanguageId) {
					qPos.add(languageId);
				}

				List<CPFriendlyUrlEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_U_L,
						finderArgs, list);
				}
				else {
					CPFriendlyUrlEntry cpFriendlyUrlEntry = list.get(0);

					result = cpFriendlyUrlEntry;

					cacheResult(cpFriendlyUrlEntry);

					if ((cpFriendlyUrlEntry.getGroupId() != groupId) ||
							(cpFriendlyUrlEntry.getUrlTitle() == null) ||
							!cpFriendlyUrlEntry.getUrlTitle().equals(urlTitle) ||
							(cpFriendlyUrlEntry.getLanguageId() == null) ||
							!cpFriendlyUrlEntry.getLanguageId()
												   .equals(languageId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_G_U_L,
							finderArgs, cpFriendlyUrlEntry);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_U_L, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (CPFriendlyUrlEntry)result;
		}
	}

	/**
	 * Removes the cp friendly url entry where groupId = &#63; and urlTitle = &#63; and languageId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param urlTitle the url title
	 * @param languageId the language ID
	 * @return the cp friendly url entry that was removed
	 */
	@Override
	public CPFriendlyUrlEntry removeByG_U_L(long groupId, String urlTitle,
		String languageId) throws NoSuchCPFriendlyUrlEntryException {
		CPFriendlyUrlEntry cpFriendlyUrlEntry = findByG_U_L(groupId, urlTitle,
				languageId);

		return remove(cpFriendlyUrlEntry);
	}

	/**
	 * Returns the number of cp friendly url entries where groupId = &#63; and urlTitle = &#63; and languageId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param urlTitle the url title
	 * @param languageId the language ID
	 * @return the number of matching cp friendly url entries
	 */
	@Override
	public int countByG_U_L(long groupId, String urlTitle, String languageId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_U_L;

		Object[] finderArgs = new Object[] { groupId, urlTitle, languageId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CPFRIENDLYURLENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_U_L_GROUPID_2);

			boolean bindUrlTitle = false;

			if (urlTitle == null) {
				query.append(_FINDER_COLUMN_G_U_L_URLTITLE_1);
			}
			else if (urlTitle.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_U_L_URLTITLE_3);
			}
			else {
				bindUrlTitle = true;

				query.append(_FINDER_COLUMN_G_U_L_URLTITLE_2);
			}

			boolean bindLanguageId = false;

			if (languageId == null) {
				query.append(_FINDER_COLUMN_G_U_L_LANGUAGEID_1);
			}
			else if (languageId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_U_L_LANGUAGEID_3);
			}
			else {
				bindLanguageId = true;

				query.append(_FINDER_COLUMN_G_U_L_LANGUAGEID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindUrlTitle) {
					qPos.add(urlTitle);
				}

				if (bindLanguageId) {
					qPos.add(languageId);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_U_L_GROUPID_2 = "cpFriendlyUrlEntry.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_L_URLTITLE_1 = "cpFriendlyUrlEntry.urlTitle IS NULL AND ";
	private static final String _FINDER_COLUMN_G_U_L_URLTITLE_2 = "cpFriendlyUrlEntry.urlTitle = ? AND ";
	private static final String _FINDER_COLUMN_G_U_L_URLTITLE_3 = "(cpFriendlyUrlEntry.urlTitle IS NULL OR cpFriendlyUrlEntry.urlTitle = '') AND ";
	private static final String _FINDER_COLUMN_G_U_L_LANGUAGEID_1 = "cpFriendlyUrlEntry.languageId IS NULL";
	private static final String _FINDER_COLUMN_G_U_L_LANGUAGEID_2 = "cpFriendlyUrlEntry.languageId = ?";
	private static final String _FINDER_COLUMN_G_U_L_LANGUAGEID_3 = "(cpFriendlyUrlEntry.languageId IS NULL OR cpFriendlyUrlEntry.languageId = '')";

	public CPFriendlyUrlEntryPersistenceImpl() {
		setModelClass(CPFriendlyUrlEntry.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the cp friendly url entry in the entity cache if it is enabled.
	 *
	 * @param cpFriendlyUrlEntry the cp friendly url entry
	 */
	@Override
	public void cacheResult(CPFriendlyUrlEntry cpFriendlyUrlEntry) {
		entityCache.putResult(CPFriendlyUrlEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPFriendlyUrlEntryImpl.class, cpFriendlyUrlEntry.getPrimaryKey(),
			cpFriendlyUrlEntry);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				cpFriendlyUrlEntry.getUuid(), cpFriendlyUrlEntry.getGroupId()
			}, cpFriendlyUrlEntry);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_U_L,
			new Object[] {
				cpFriendlyUrlEntry.getGroupId(),
				cpFriendlyUrlEntry.getUrlTitle(),
				cpFriendlyUrlEntry.getLanguageId()
			}, cpFriendlyUrlEntry);

		cpFriendlyUrlEntry.resetOriginalValues();
	}

	/**
	 * Caches the cp friendly url entries in the entity cache if it is enabled.
	 *
	 * @param cpFriendlyUrlEntries the cp friendly url entries
	 */
	@Override
	public void cacheResult(List<CPFriendlyUrlEntry> cpFriendlyUrlEntries) {
		for (CPFriendlyUrlEntry cpFriendlyUrlEntry : cpFriendlyUrlEntries) {
			if (entityCache.getResult(
						CPFriendlyUrlEntryModelImpl.ENTITY_CACHE_ENABLED,
						CPFriendlyUrlEntryImpl.class,
						cpFriendlyUrlEntry.getPrimaryKey()) == null) {
				cacheResult(cpFriendlyUrlEntry);
			}
			else {
				cpFriendlyUrlEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cp friendly url entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CPFriendlyUrlEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cp friendly url entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CPFriendlyUrlEntry cpFriendlyUrlEntry) {
		entityCache.removeResult(CPFriendlyUrlEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPFriendlyUrlEntryImpl.class, cpFriendlyUrlEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CPFriendlyUrlEntryModelImpl)cpFriendlyUrlEntry,
			true);
	}

	@Override
	public void clearCache(List<CPFriendlyUrlEntry> cpFriendlyUrlEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CPFriendlyUrlEntry cpFriendlyUrlEntry : cpFriendlyUrlEntries) {
			entityCache.removeResult(CPFriendlyUrlEntryModelImpl.ENTITY_CACHE_ENABLED,
				CPFriendlyUrlEntryImpl.class, cpFriendlyUrlEntry.getPrimaryKey());

			clearUniqueFindersCache((CPFriendlyUrlEntryModelImpl)cpFriendlyUrlEntry,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		CPFriendlyUrlEntryModelImpl cpFriendlyUrlEntryModelImpl) {
		Object[] args = new Object[] {
				cpFriendlyUrlEntryModelImpl.getUuid(),
				cpFriendlyUrlEntryModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			cpFriendlyUrlEntryModelImpl, false);

		args = new Object[] {
				cpFriendlyUrlEntryModelImpl.getGroupId(),
				cpFriendlyUrlEntryModelImpl.getUrlTitle(),
				cpFriendlyUrlEntryModelImpl.getLanguageId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_U_L, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_U_L, args,
			cpFriendlyUrlEntryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CPFriendlyUrlEntryModelImpl cpFriendlyUrlEntryModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					cpFriendlyUrlEntryModelImpl.getUuid(),
					cpFriendlyUrlEntryModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((cpFriendlyUrlEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					cpFriendlyUrlEntryModelImpl.getOriginalUuid(),
					cpFriendlyUrlEntryModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					cpFriendlyUrlEntryModelImpl.getGroupId(),
					cpFriendlyUrlEntryModelImpl.getUrlTitle(),
					cpFriendlyUrlEntryModelImpl.getLanguageId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_U_L, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_U_L, args);
		}

		if ((cpFriendlyUrlEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_U_L.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					cpFriendlyUrlEntryModelImpl.getOriginalGroupId(),
					cpFriendlyUrlEntryModelImpl.getOriginalUrlTitle(),
					cpFriendlyUrlEntryModelImpl.getOriginalLanguageId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_U_L, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_U_L, args);
		}
	}

	/**
	 * Creates a new cp friendly url entry with the primary key. Does not add the cp friendly url entry to the database.
	 *
	 * @param CPFriendlyUrlEntryId the primary key for the new cp friendly url entry
	 * @return the new cp friendly url entry
	 */
	@Override
	public CPFriendlyUrlEntry create(long CPFriendlyUrlEntryId) {
		CPFriendlyUrlEntry cpFriendlyUrlEntry = new CPFriendlyUrlEntryImpl();

		cpFriendlyUrlEntry.setNew(true);
		cpFriendlyUrlEntry.setPrimaryKey(CPFriendlyUrlEntryId);

		String uuid = PortalUUIDUtil.generate();

		cpFriendlyUrlEntry.setUuid(uuid);

		cpFriendlyUrlEntry.setCompanyId(companyProvider.getCompanyId());

		return cpFriendlyUrlEntry;
	}

	/**
	 * Removes the cp friendly url entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPFriendlyUrlEntryId the primary key of the cp friendly url entry
	 * @return the cp friendly url entry that was removed
	 * @throws NoSuchCPFriendlyUrlEntryException if a cp friendly url entry with the primary key could not be found
	 */
	@Override
	public CPFriendlyUrlEntry remove(long CPFriendlyUrlEntryId)
		throws NoSuchCPFriendlyUrlEntryException {
		return remove((Serializable)CPFriendlyUrlEntryId);
	}

	/**
	 * Removes the cp friendly url entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cp friendly url entry
	 * @return the cp friendly url entry that was removed
	 * @throws NoSuchCPFriendlyUrlEntryException if a cp friendly url entry with the primary key could not be found
	 */
	@Override
	public CPFriendlyUrlEntry remove(Serializable primaryKey)
		throws NoSuchCPFriendlyUrlEntryException {
		Session session = null;

		try {
			session = openSession();

			CPFriendlyUrlEntry cpFriendlyUrlEntry = (CPFriendlyUrlEntry)session.get(CPFriendlyUrlEntryImpl.class,
					primaryKey);

			if (cpFriendlyUrlEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCPFriendlyUrlEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(cpFriendlyUrlEntry);
		}
		catch (NoSuchCPFriendlyUrlEntryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected CPFriendlyUrlEntry removeImpl(
		CPFriendlyUrlEntry cpFriendlyUrlEntry) {
		cpFriendlyUrlEntry = toUnwrappedModel(cpFriendlyUrlEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cpFriendlyUrlEntry)) {
				cpFriendlyUrlEntry = (CPFriendlyUrlEntry)session.get(CPFriendlyUrlEntryImpl.class,
						cpFriendlyUrlEntry.getPrimaryKeyObj());
			}

			if (cpFriendlyUrlEntry != null) {
				session.delete(cpFriendlyUrlEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cpFriendlyUrlEntry != null) {
			clearCache(cpFriendlyUrlEntry);
		}

		return cpFriendlyUrlEntry;
	}

	@Override
	public CPFriendlyUrlEntry updateImpl(CPFriendlyUrlEntry cpFriendlyUrlEntry) {
		cpFriendlyUrlEntry = toUnwrappedModel(cpFriendlyUrlEntry);

		boolean isNew = cpFriendlyUrlEntry.isNew();

		CPFriendlyUrlEntryModelImpl cpFriendlyUrlEntryModelImpl = (CPFriendlyUrlEntryModelImpl)cpFriendlyUrlEntry;

		if (Validator.isNull(cpFriendlyUrlEntry.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			cpFriendlyUrlEntry.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (cpFriendlyUrlEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				cpFriendlyUrlEntry.setCreateDate(now);
			}
			else {
				cpFriendlyUrlEntry.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!cpFriendlyUrlEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				cpFriendlyUrlEntry.setModifiedDate(now);
			}
			else {
				cpFriendlyUrlEntry.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (cpFriendlyUrlEntry.isNew()) {
				session.save(cpFriendlyUrlEntry);

				cpFriendlyUrlEntry.setNew(false);
			}
			else {
				cpFriendlyUrlEntry = (CPFriendlyUrlEntry)session.merge(cpFriendlyUrlEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CPFriendlyUrlEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { cpFriendlyUrlEntryModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					cpFriendlyUrlEntryModelImpl.getUuid(),
					cpFriendlyUrlEntryModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((cpFriendlyUrlEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cpFriendlyUrlEntryModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { cpFriendlyUrlEntryModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((cpFriendlyUrlEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cpFriendlyUrlEntryModelImpl.getOriginalUuid(),
						cpFriendlyUrlEntryModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						cpFriendlyUrlEntryModelImpl.getUuid(),
						cpFriendlyUrlEntryModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		entityCache.putResult(CPFriendlyUrlEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPFriendlyUrlEntryImpl.class, cpFriendlyUrlEntry.getPrimaryKey(),
			cpFriendlyUrlEntry, false);

		clearUniqueFindersCache(cpFriendlyUrlEntryModelImpl, false);
		cacheUniqueFindersCache(cpFriendlyUrlEntryModelImpl);

		cpFriendlyUrlEntry.resetOriginalValues();

		return cpFriendlyUrlEntry;
	}

	protected CPFriendlyUrlEntry toUnwrappedModel(
		CPFriendlyUrlEntry cpFriendlyUrlEntry) {
		if (cpFriendlyUrlEntry instanceof CPFriendlyUrlEntryImpl) {
			return cpFriendlyUrlEntry;
		}

		CPFriendlyUrlEntryImpl cpFriendlyUrlEntryImpl = new CPFriendlyUrlEntryImpl();

		cpFriendlyUrlEntryImpl.setNew(cpFriendlyUrlEntry.isNew());
		cpFriendlyUrlEntryImpl.setPrimaryKey(cpFriendlyUrlEntry.getPrimaryKey());

		cpFriendlyUrlEntryImpl.setUuid(cpFriendlyUrlEntry.getUuid());
		cpFriendlyUrlEntryImpl.setCPFriendlyUrlEntryId(cpFriendlyUrlEntry.getCPFriendlyUrlEntryId());
		cpFriendlyUrlEntryImpl.setGroupId(cpFriendlyUrlEntry.getGroupId());
		cpFriendlyUrlEntryImpl.setCompanyId(cpFriendlyUrlEntry.getCompanyId());
		cpFriendlyUrlEntryImpl.setUserId(cpFriendlyUrlEntry.getUserId());
		cpFriendlyUrlEntryImpl.setUserName(cpFriendlyUrlEntry.getUserName());
		cpFriendlyUrlEntryImpl.setCreateDate(cpFriendlyUrlEntry.getCreateDate());
		cpFriendlyUrlEntryImpl.setModifiedDate(cpFriendlyUrlEntry.getModifiedDate());
		cpFriendlyUrlEntryImpl.setClassNameId(cpFriendlyUrlEntry.getClassNameId());
		cpFriendlyUrlEntryImpl.setClassPK(cpFriendlyUrlEntry.getClassPK());
		cpFriendlyUrlEntryImpl.setLanguageId(cpFriendlyUrlEntry.getLanguageId());
		cpFriendlyUrlEntryImpl.setUrlTitle(cpFriendlyUrlEntry.getUrlTitle());
		cpFriendlyUrlEntryImpl.setMain(cpFriendlyUrlEntry.isMain());

		return cpFriendlyUrlEntryImpl;
	}

	/**
	 * Returns the cp friendly url entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp friendly url entry
	 * @return the cp friendly url entry
	 * @throws NoSuchCPFriendlyUrlEntryException if a cp friendly url entry with the primary key could not be found
	 */
	@Override
	public CPFriendlyUrlEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCPFriendlyUrlEntryException {
		CPFriendlyUrlEntry cpFriendlyUrlEntry = fetchByPrimaryKey(primaryKey);

		if (cpFriendlyUrlEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCPFriendlyUrlEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return cpFriendlyUrlEntry;
	}

	/**
	 * Returns the cp friendly url entry with the primary key or throws a {@link NoSuchCPFriendlyUrlEntryException} if it could not be found.
	 *
	 * @param CPFriendlyUrlEntryId the primary key of the cp friendly url entry
	 * @return the cp friendly url entry
	 * @throws NoSuchCPFriendlyUrlEntryException if a cp friendly url entry with the primary key could not be found
	 */
	@Override
	public CPFriendlyUrlEntry findByPrimaryKey(long CPFriendlyUrlEntryId)
		throws NoSuchCPFriendlyUrlEntryException {
		return findByPrimaryKey((Serializable)CPFriendlyUrlEntryId);
	}

	/**
	 * Returns the cp friendly url entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp friendly url entry
	 * @return the cp friendly url entry, or <code>null</code> if a cp friendly url entry with the primary key could not be found
	 */
	@Override
	public CPFriendlyUrlEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CPFriendlyUrlEntryModelImpl.ENTITY_CACHE_ENABLED,
				CPFriendlyUrlEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CPFriendlyUrlEntry cpFriendlyUrlEntry = (CPFriendlyUrlEntry)serializable;

		if (cpFriendlyUrlEntry == null) {
			Session session = null;

			try {
				session = openSession();

				cpFriendlyUrlEntry = (CPFriendlyUrlEntry)session.get(CPFriendlyUrlEntryImpl.class,
						primaryKey);

				if (cpFriendlyUrlEntry != null) {
					cacheResult(cpFriendlyUrlEntry);
				}
				else {
					entityCache.putResult(CPFriendlyUrlEntryModelImpl.ENTITY_CACHE_ENABLED,
						CPFriendlyUrlEntryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CPFriendlyUrlEntryModelImpl.ENTITY_CACHE_ENABLED,
					CPFriendlyUrlEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cpFriendlyUrlEntry;
	}

	/**
	 * Returns the cp friendly url entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param CPFriendlyUrlEntryId the primary key of the cp friendly url entry
	 * @return the cp friendly url entry, or <code>null</code> if a cp friendly url entry with the primary key could not be found
	 */
	@Override
	public CPFriendlyUrlEntry fetchByPrimaryKey(long CPFriendlyUrlEntryId) {
		return fetchByPrimaryKey((Serializable)CPFriendlyUrlEntryId);
	}

	@Override
	public Map<Serializable, CPFriendlyUrlEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CPFriendlyUrlEntry> map = new HashMap<Serializable, CPFriendlyUrlEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CPFriendlyUrlEntry cpFriendlyUrlEntry = fetchByPrimaryKey(primaryKey);

			if (cpFriendlyUrlEntry != null) {
				map.put(primaryKey, cpFriendlyUrlEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CPFriendlyUrlEntryModelImpl.ENTITY_CACHE_ENABLED,
					CPFriendlyUrlEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CPFriendlyUrlEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CPFRIENDLYURLENTRY_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (CPFriendlyUrlEntry cpFriendlyUrlEntry : (List<CPFriendlyUrlEntry>)q.list()) {
				map.put(cpFriendlyUrlEntry.getPrimaryKeyObj(),
					cpFriendlyUrlEntry);

				cacheResult(cpFriendlyUrlEntry);

				uncachedPrimaryKeys.remove(cpFriendlyUrlEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CPFriendlyUrlEntryModelImpl.ENTITY_CACHE_ENABLED,
					CPFriendlyUrlEntryImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the cp friendly url entries.
	 *
	 * @return the cp friendly url entries
	 */
	@Override
	public List<CPFriendlyUrlEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp friendly url entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPFriendlyUrlEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @return the range of cp friendly url entries
	 */
	@Override
	public List<CPFriendlyUrlEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp friendly url entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPFriendlyUrlEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cp friendly url entries
	 */
	@Override
	public List<CPFriendlyUrlEntry> findAll(int start, int end,
		OrderByComparator<CPFriendlyUrlEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp friendly url entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPFriendlyUrlEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp friendly url entries
	 * @param end the upper bound of the range of cp friendly url entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cp friendly url entries
	 */
	@Override
	public List<CPFriendlyUrlEntry> findAll(int start, int end,
		OrderByComparator<CPFriendlyUrlEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<CPFriendlyUrlEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CPFriendlyUrlEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CPFRIENDLYURLENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CPFRIENDLYURLENTRY;

				if (pagination) {
					sql = sql.concat(CPFriendlyUrlEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CPFriendlyUrlEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPFriendlyUrlEntry>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the cp friendly url entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CPFriendlyUrlEntry cpFriendlyUrlEntry : findAll()) {
			remove(cpFriendlyUrlEntry);
		}
	}

	/**
	 * Returns the number of cp friendly url entries.
	 *
	 * @return the number of cp friendly url entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CPFRIENDLYURLENTRY);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CPFriendlyUrlEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cp friendly url entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CPFriendlyUrlEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CPFRIENDLYURLENTRY = "SELECT cpFriendlyUrlEntry FROM CPFriendlyUrlEntry cpFriendlyUrlEntry";
	private static final String _SQL_SELECT_CPFRIENDLYURLENTRY_WHERE_PKS_IN = "SELECT cpFriendlyUrlEntry FROM CPFriendlyUrlEntry cpFriendlyUrlEntry WHERE CPFriendlyUrlEntryId IN (";
	private static final String _SQL_SELECT_CPFRIENDLYURLENTRY_WHERE = "SELECT cpFriendlyUrlEntry FROM CPFriendlyUrlEntry cpFriendlyUrlEntry WHERE ";
	private static final String _SQL_COUNT_CPFRIENDLYURLENTRY = "SELECT COUNT(cpFriendlyUrlEntry) FROM CPFriendlyUrlEntry cpFriendlyUrlEntry";
	private static final String _SQL_COUNT_CPFRIENDLYURLENTRY_WHERE = "SELECT COUNT(cpFriendlyUrlEntry) FROM CPFriendlyUrlEntry cpFriendlyUrlEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "cpFriendlyUrlEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CPFriendlyUrlEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CPFriendlyUrlEntry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CPFriendlyUrlEntryPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}