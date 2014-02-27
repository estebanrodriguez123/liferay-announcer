/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.rivetlogic.portlet.service.persistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.rivetlogic.portlet.NoSuchNotCompletedException;
import com.rivetlogic.portlet.model.NotCompleted;
import com.rivetlogic.portlet.model.impl.NotCompletedImpl;
import com.rivetlogic.portlet.model.impl.NotCompletedModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the not completed service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author charles.rodriguez
 * @see NotCompletedPersistence
 * @see NotCompletedUtil
 * @generated
 */
public class NotCompletedPersistenceImpl extends BasePersistenceImpl<NotCompleted>
	implements NotCompletedPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link NotCompletedUtil} to access the not completed persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = NotCompletedImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NotCompletedModelImpl.ENTITY_CACHE_ENABLED,
			NotCompletedModelImpl.FINDER_CACHE_ENABLED, NotCompletedImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NotCompletedModelImpl.ENTITY_CACHE_ENABLED,
			NotCompletedModelImpl.FINDER_CACHE_ENABLED, NotCompletedImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NotCompletedModelImpl.ENTITY_CACHE_ENABLED,
			NotCompletedModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_USERLAYOUTKEY = new FinderPath(NotCompletedModelImpl.ENTITY_CACHE_ENABLED,
			NotCompletedModelImpl.FINDER_CACHE_ENABLED, NotCompletedImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUserLayoutKey",
			new String[] { String.class.getName(), String.class.getName() },
			NotCompletedModelImpl.USER_ID_COLUMN_BITMASK |
			NotCompletedModelImpl.LAYOUT_PK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERLAYOUTKEY = new FinderPath(NotCompletedModelImpl.ENTITY_CACHE_ENABLED,
			NotCompletedModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserLayoutKey",
			new String[] { String.class.getName(), String.class.getName() });

	/**
	 * Returns the not completed where USER_ID = &#63; and LAYOUT_PK = &#63; or throws a {@link com.rivetlogic.portlet.NoSuchNotCompletedException} if it could not be found.
	 *
	 * @param USER_ID the u s e r_ i d
	 * @param LAYOUT_PK the l a y o u t_ p k
	 * @return the matching not completed
	 * @throws com.rivetlogic.portlet.NoSuchNotCompletedException if a matching not completed could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NotCompleted findByUserLayoutKey(String USER_ID, String LAYOUT_PK)
		throws NoSuchNotCompletedException, SystemException {
		NotCompleted notCompleted = fetchByUserLayoutKey(USER_ID, LAYOUT_PK);

		if (notCompleted == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("USER_ID=");
			msg.append(USER_ID);

			msg.append(", LAYOUT_PK=");
			msg.append(LAYOUT_PK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchNotCompletedException(msg.toString());
		}

		return notCompleted;
	}

	/**
	 * Returns the not completed where USER_ID = &#63; and LAYOUT_PK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param USER_ID the u s e r_ i d
	 * @param LAYOUT_PK the l a y o u t_ p k
	 * @return the matching not completed, or <code>null</code> if a matching not completed could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NotCompleted fetchByUserLayoutKey(String USER_ID, String LAYOUT_PK)
		throws SystemException {
		return fetchByUserLayoutKey(USER_ID, LAYOUT_PK, true);
	}

	/**
	 * Returns the not completed where USER_ID = &#63; and LAYOUT_PK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param USER_ID the u s e r_ i d
	 * @param LAYOUT_PK the l a y o u t_ p k
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching not completed, or <code>null</code> if a matching not completed could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NotCompleted fetchByUserLayoutKey(String USER_ID, String LAYOUT_PK,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { USER_ID, LAYOUT_PK };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_USERLAYOUTKEY,
					finderArgs, this);
		}

		if (result instanceof NotCompleted) {
			NotCompleted notCompleted = (NotCompleted)result;

			if (!Validator.equals(USER_ID, notCompleted.getUSER_ID()) ||
					!Validator.equals(LAYOUT_PK, notCompleted.getLAYOUT_PK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_NOTCOMPLETED_WHERE);

			boolean bindUSER_ID = false;

			if (USER_ID == null) {
				query.append(_FINDER_COLUMN_USERLAYOUTKEY_USER_ID_1);
			}
			else if (USER_ID.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_USERLAYOUTKEY_USER_ID_3);
			}
			else {
				bindUSER_ID = true;

				query.append(_FINDER_COLUMN_USERLAYOUTKEY_USER_ID_2);
			}

			boolean bindLAYOUT_PK = false;

			if (LAYOUT_PK == null) {
				query.append(_FINDER_COLUMN_USERLAYOUTKEY_LAYOUT_PK_1);
			}
			else if (LAYOUT_PK.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_USERLAYOUTKEY_LAYOUT_PK_3);
			}
			else {
				bindLAYOUT_PK = true;

				query.append(_FINDER_COLUMN_USERLAYOUTKEY_LAYOUT_PK_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUSER_ID) {
					qPos.add(USER_ID);
				}

				if (bindLAYOUT_PK) {
					qPos.add(LAYOUT_PK);
				}

				List<NotCompleted> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERLAYOUTKEY,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"NotCompletedPersistenceImpl.fetchByUserLayoutKey(String, String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					NotCompleted notCompleted = list.get(0);

					result = notCompleted;

					cacheResult(notCompleted);

					if ((notCompleted.getUSER_ID() == null) ||
							!notCompleted.getUSER_ID().equals(USER_ID) ||
							(notCompleted.getLAYOUT_PK() == null) ||
							!notCompleted.getLAYOUT_PK().equals(LAYOUT_PK)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERLAYOUTKEY,
							finderArgs, notCompleted);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERLAYOUTKEY,
					finderArgs);

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
			return (NotCompleted)result;
		}
	}

	/**
	 * Removes the not completed where USER_ID = &#63; and LAYOUT_PK = &#63; from the database.
	 *
	 * @param USER_ID the u s e r_ i d
	 * @param LAYOUT_PK the l a y o u t_ p k
	 * @return the not completed that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NotCompleted removeByUserLayoutKey(String USER_ID, String LAYOUT_PK)
		throws NoSuchNotCompletedException, SystemException {
		NotCompleted notCompleted = findByUserLayoutKey(USER_ID, LAYOUT_PK);

		return remove(notCompleted);
	}

	/**
	 * Returns the number of not completeds where USER_ID = &#63; and LAYOUT_PK = &#63;.
	 *
	 * @param USER_ID the u s e r_ i d
	 * @param LAYOUT_PK the l a y o u t_ p k
	 * @return the number of matching not completeds
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUserLayoutKey(String USER_ID, String LAYOUT_PK)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERLAYOUTKEY;

		Object[] finderArgs = new Object[] { USER_ID, LAYOUT_PK };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_NOTCOMPLETED_WHERE);

			boolean bindUSER_ID = false;

			if (USER_ID == null) {
				query.append(_FINDER_COLUMN_USERLAYOUTKEY_USER_ID_1);
			}
			else if (USER_ID.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_USERLAYOUTKEY_USER_ID_3);
			}
			else {
				bindUSER_ID = true;

				query.append(_FINDER_COLUMN_USERLAYOUTKEY_USER_ID_2);
			}

			boolean bindLAYOUT_PK = false;

			if (LAYOUT_PK == null) {
				query.append(_FINDER_COLUMN_USERLAYOUTKEY_LAYOUT_PK_1);
			}
			else if (LAYOUT_PK.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_USERLAYOUTKEY_LAYOUT_PK_3);
			}
			else {
				bindLAYOUT_PK = true;

				query.append(_FINDER_COLUMN_USERLAYOUTKEY_LAYOUT_PK_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUSER_ID) {
					qPos.add(USER_ID);
				}

				if (bindLAYOUT_PK) {
					qPos.add(LAYOUT_PK);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_USERLAYOUTKEY_USER_ID_1 = "notCompleted.id.USER_ID IS NULL AND ";
	private static final String _FINDER_COLUMN_USERLAYOUTKEY_USER_ID_2 = "notCompleted.id.USER_ID = ? AND ";
	private static final String _FINDER_COLUMN_USERLAYOUTKEY_USER_ID_3 = "(notCompleted.id.USER_ID IS NULL OR notCompleted.id.USER_ID = '') AND ";
	private static final String _FINDER_COLUMN_USERLAYOUTKEY_LAYOUT_PK_1 = "notCompleted.id.LAYOUT_PK IS NULL";
	private static final String _FINDER_COLUMN_USERLAYOUTKEY_LAYOUT_PK_2 = "notCompleted.id.LAYOUT_PK = ?";
	private static final String _FINDER_COLUMN_USERLAYOUTKEY_LAYOUT_PK_3 = "(notCompleted.id.LAYOUT_PK IS NULL OR notCompleted.id.LAYOUT_PK = '')";

	public NotCompletedPersistenceImpl() {
		setModelClass(NotCompleted.class);
	}

	/**
	 * Caches the not completed in the entity cache if it is enabled.
	 *
	 * @param notCompleted the not completed
	 */
	@Override
	public void cacheResult(NotCompleted notCompleted) {
		EntityCacheUtil.putResult(NotCompletedModelImpl.ENTITY_CACHE_ENABLED,
			NotCompletedImpl.class, notCompleted.getPrimaryKey(), notCompleted);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERLAYOUTKEY,
			new Object[] { notCompleted.getUSER_ID(), notCompleted.getLAYOUT_PK() },
			notCompleted);

		notCompleted.resetOriginalValues();
	}

	/**
	 * Caches the not completeds in the entity cache if it is enabled.
	 *
	 * @param notCompleteds the not completeds
	 */
	@Override
	public void cacheResult(List<NotCompleted> notCompleteds) {
		for (NotCompleted notCompleted : notCompleteds) {
			if (EntityCacheUtil.getResult(
						NotCompletedModelImpl.ENTITY_CACHE_ENABLED,
						NotCompletedImpl.class, notCompleted.getPrimaryKey()) == null) {
				cacheResult(notCompleted);
			}
			else {
				notCompleted.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all not completeds.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(NotCompletedImpl.class.getName());
		}

		EntityCacheUtil.clearCache(NotCompletedImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the not completed.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NotCompleted notCompleted) {
		EntityCacheUtil.removeResult(NotCompletedModelImpl.ENTITY_CACHE_ENABLED,
			NotCompletedImpl.class, notCompleted.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(notCompleted);
	}

	@Override
	public void clearCache(List<NotCompleted> notCompleteds) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (NotCompleted notCompleted : notCompleteds) {
			EntityCacheUtil.removeResult(NotCompletedModelImpl.ENTITY_CACHE_ENABLED,
				NotCompletedImpl.class, notCompleted.getPrimaryKey());

			clearUniqueFindersCache(notCompleted);
		}
	}

	protected void cacheUniqueFindersCache(NotCompleted notCompleted) {
		if (notCompleted.isNew()) {
			Object[] args = new Object[] {
					notCompleted.getUSER_ID(), notCompleted.getLAYOUT_PK()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERLAYOUTKEY, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERLAYOUTKEY, args,
				notCompleted);
		}
		else {
			NotCompletedModelImpl notCompletedModelImpl = (NotCompletedModelImpl)notCompleted;

			if ((notCompletedModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_USERLAYOUTKEY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						notCompleted.getUSER_ID(), notCompleted.getLAYOUT_PK()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERLAYOUTKEY,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERLAYOUTKEY,
					args, notCompleted);
			}
		}
	}

	protected void clearUniqueFindersCache(NotCompleted notCompleted) {
		NotCompletedModelImpl notCompletedModelImpl = (NotCompletedModelImpl)notCompleted;

		Object[] args = new Object[] {
				notCompleted.getUSER_ID(), notCompleted.getLAYOUT_PK()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERLAYOUTKEY, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERLAYOUTKEY, args);

		if ((notCompletedModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_USERLAYOUTKEY.getColumnBitmask()) != 0) {
			args = new Object[] {
					notCompletedModelImpl.getOriginalUSER_ID(),
					notCompletedModelImpl.getOriginalLAYOUT_PK()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERLAYOUTKEY,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERLAYOUTKEY,
				args);
		}
	}

	/**
	 * Creates a new not completed with the primary key. Does not add the not completed to the database.
	 *
	 * @param notCompletedPK the primary key for the new not completed
	 * @return the new not completed
	 */
	@Override
	public NotCompleted create(NotCompletedPK notCompletedPK) {
		NotCompleted notCompleted = new NotCompletedImpl();

		notCompleted.setNew(true);
		notCompleted.setPrimaryKey(notCompletedPK);

		return notCompleted;
	}

	/**
	 * Removes the not completed with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param notCompletedPK the primary key of the not completed
	 * @return the not completed that was removed
	 * @throws com.rivetlogic.portlet.NoSuchNotCompletedException if a not completed with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NotCompleted remove(NotCompletedPK notCompletedPK)
		throws NoSuchNotCompletedException, SystemException {
		return remove((Serializable)notCompletedPK);
	}

	/**
	 * Removes the not completed with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the not completed
	 * @return the not completed that was removed
	 * @throws com.rivetlogic.portlet.NoSuchNotCompletedException if a not completed with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NotCompleted remove(Serializable primaryKey)
		throws NoSuchNotCompletedException, SystemException {
		Session session = null;

		try {
			session = openSession();

			NotCompleted notCompleted = (NotCompleted)session.get(NotCompletedImpl.class,
					primaryKey);

			if (notCompleted == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNotCompletedException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(notCompleted);
		}
		catch (NoSuchNotCompletedException nsee) {
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
	protected NotCompleted removeImpl(NotCompleted notCompleted)
		throws SystemException {
		notCompleted = toUnwrappedModel(notCompleted);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(notCompleted)) {
				notCompleted = (NotCompleted)session.get(NotCompletedImpl.class,
						notCompleted.getPrimaryKeyObj());
			}

			if (notCompleted != null) {
				session.delete(notCompleted);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (notCompleted != null) {
			clearCache(notCompleted);
		}

		return notCompleted;
	}

	@Override
	public NotCompleted updateImpl(
		com.rivetlogic.portlet.model.NotCompleted notCompleted)
		throws SystemException {
		notCompleted = toUnwrappedModel(notCompleted);

		boolean isNew = notCompleted.isNew();

		Session session = null;

		try {
			session = openSession();

			if (notCompleted.isNew()) {
				session.save(notCompleted);

				notCompleted.setNew(false);
			}
			else {
				session.merge(notCompleted);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !NotCompletedModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(NotCompletedModelImpl.ENTITY_CACHE_ENABLED,
			NotCompletedImpl.class, notCompleted.getPrimaryKey(), notCompleted);

		clearUniqueFindersCache(notCompleted);
		cacheUniqueFindersCache(notCompleted);

		return notCompleted;
	}

	protected NotCompleted toUnwrappedModel(NotCompleted notCompleted) {
		if (notCompleted instanceof NotCompletedImpl) {
			return notCompleted;
		}

		NotCompletedImpl notCompletedImpl = new NotCompletedImpl();

		notCompletedImpl.setNew(notCompleted.isNew());
		notCompletedImpl.setPrimaryKey(notCompleted.getPrimaryKey());

		notCompletedImpl.setUSER_ID(notCompleted.getUSER_ID());
		notCompletedImpl.setLAYOUT_PK(notCompleted.getLAYOUT_PK());
		notCompletedImpl.setPANEL_CLOSE_DATE(notCompleted.getPANEL_CLOSE_DATE());

		return notCompletedImpl;
	}

	/**
	 * Returns the not completed with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the not completed
	 * @return the not completed
	 * @throws com.rivetlogic.portlet.NoSuchNotCompletedException if a not completed with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NotCompleted findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNotCompletedException, SystemException {
		NotCompleted notCompleted = fetchByPrimaryKey(primaryKey);

		if (notCompleted == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNotCompletedException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return notCompleted;
	}

	/**
	 * Returns the not completed with the primary key or throws a {@link com.rivetlogic.portlet.NoSuchNotCompletedException} if it could not be found.
	 *
	 * @param notCompletedPK the primary key of the not completed
	 * @return the not completed
	 * @throws com.rivetlogic.portlet.NoSuchNotCompletedException if a not completed with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NotCompleted findByPrimaryKey(NotCompletedPK notCompletedPK)
		throws NoSuchNotCompletedException, SystemException {
		return findByPrimaryKey((Serializable)notCompletedPK);
	}

	/**
	 * Returns the not completed with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the not completed
	 * @return the not completed, or <code>null</code> if a not completed with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NotCompleted fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		NotCompleted notCompleted = (NotCompleted)EntityCacheUtil.getResult(NotCompletedModelImpl.ENTITY_CACHE_ENABLED,
				NotCompletedImpl.class, primaryKey);

		if (notCompleted == _nullNotCompleted) {
			return null;
		}

		if (notCompleted == null) {
			Session session = null;

			try {
				session = openSession();

				notCompleted = (NotCompleted)session.get(NotCompletedImpl.class,
						primaryKey);

				if (notCompleted != null) {
					cacheResult(notCompleted);
				}
				else {
					EntityCacheUtil.putResult(NotCompletedModelImpl.ENTITY_CACHE_ENABLED,
						NotCompletedImpl.class, primaryKey, _nullNotCompleted);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(NotCompletedModelImpl.ENTITY_CACHE_ENABLED,
					NotCompletedImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return notCompleted;
	}

	/**
	 * Returns the not completed with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param notCompletedPK the primary key of the not completed
	 * @return the not completed, or <code>null</code> if a not completed with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NotCompleted fetchByPrimaryKey(NotCompletedPK notCompletedPK)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)notCompletedPK);
	}

	/**
	 * Returns all the not completeds.
	 *
	 * @return the not completeds
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<NotCompleted> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the not completeds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.rivetlogic.portlet.model.impl.NotCompletedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of not completeds
	 * @param end the upper bound of the range of not completeds (not inclusive)
	 * @return the range of not completeds
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<NotCompleted> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the not completeds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.rivetlogic.portlet.model.impl.NotCompletedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of not completeds
	 * @param end the upper bound of the range of not completeds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of not completeds
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<NotCompleted> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
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

		List<NotCompleted> list = (List<NotCompleted>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_NOTCOMPLETED);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NOTCOMPLETED;

				if (pagination) {
					sql = sql.concat(NotCompletedModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<NotCompleted>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<NotCompleted>(list);
				}
				else {
					list = (List<NotCompleted>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the not completeds from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (NotCompleted notCompleted : findAll()) {
			remove(notCompleted);
		}
	}

	/**
	 * Returns the number of not completeds.
	 *
	 * @return the number of not completeds
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_NOTCOMPLETED);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the not completed persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.rivetlogic.portlet.model.NotCompleted")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<NotCompleted>> listenersList = new ArrayList<ModelListener<NotCompleted>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<NotCompleted>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(NotCompletedImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_NOTCOMPLETED = "SELECT notCompleted FROM NotCompleted notCompleted";
	private static final String _SQL_SELECT_NOTCOMPLETED_WHERE = "SELECT notCompleted FROM NotCompleted notCompleted WHERE ";
	private static final String _SQL_COUNT_NOTCOMPLETED = "SELECT COUNT(notCompleted) FROM NotCompleted notCompleted";
	private static final String _SQL_COUNT_NOTCOMPLETED_WHERE = "SELECT COUNT(notCompleted) FROM NotCompleted notCompleted WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "notCompleted.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NotCompleted exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No NotCompleted exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(NotCompletedPersistenceImpl.class);
	private static NotCompleted _nullNotCompleted = new NotCompletedImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<NotCompleted> toCacheModel() {
				return _nullNotCompletedCacheModel;
			}
		};

	private static CacheModel<NotCompleted> _nullNotCompletedCacheModel = new CacheModel<NotCompleted>() {
			@Override
			public NotCompleted toEntityModel() {
				return _nullNotCompleted;
			}
		};
}