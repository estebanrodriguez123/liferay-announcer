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

import com.rivetlogic.portlet.NoSuchCompletedException;
import com.rivetlogic.portlet.model.Completed;
import com.rivetlogic.portlet.model.impl.CompletedImpl;
import com.rivetlogic.portlet.model.impl.CompletedModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the completed service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author charles.rodriguez
 * @see CompletedPersistence
 * @see CompletedUtil
 * @generated
 */
public class CompletedPersistenceImpl extends BasePersistenceImpl<Completed>
	implements CompletedPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CompletedUtil} to access the completed persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CompletedImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CompletedModelImpl.ENTITY_CACHE_ENABLED,
			CompletedModelImpl.FINDER_CACHE_ENABLED, CompletedImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CompletedModelImpl.ENTITY_CACHE_ENABLED,
			CompletedModelImpl.FINDER_CACHE_ENABLED, CompletedImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CompletedModelImpl.ENTITY_CACHE_ENABLED,
			CompletedModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_USERLAYOUTKEY = new FinderPath(CompletedModelImpl.ENTITY_CACHE_ENABLED,
			CompletedModelImpl.FINDER_CACHE_ENABLED, CompletedImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUserLayoutKey",
			new String[] { String.class.getName(), String.class.getName() },
			CompletedModelImpl.USER_ID_COLUMN_BITMASK |
			CompletedModelImpl.LAYOUT_PK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERLAYOUTKEY = new FinderPath(CompletedModelImpl.ENTITY_CACHE_ENABLED,
			CompletedModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserLayoutKey",
			new String[] { String.class.getName(), String.class.getName() });

	/**
	 * Returns the completed where USER_ID = &#63; and LAYOUT_PK = &#63; or throws a {@link com.rivetlogic.portlet.NoSuchCompletedException} if it could not be found.
	 *
	 * @param USER_ID the u s e r_ i d
	 * @param LAYOUT_PK the l a y o u t_ p k
	 * @return the matching completed
	 * @throws com.rivetlogic.portlet.NoSuchCompletedException if a matching completed could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Completed findByUserLayoutKey(String USER_ID, String LAYOUT_PK)
		throws NoSuchCompletedException, SystemException {
		Completed completed = fetchByUserLayoutKey(USER_ID, LAYOUT_PK);

		if (completed == null) {
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

			throw new NoSuchCompletedException(msg.toString());
		}

		return completed;
	}

	/**
	 * Returns the completed where USER_ID = &#63; and LAYOUT_PK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param USER_ID the u s e r_ i d
	 * @param LAYOUT_PK the l a y o u t_ p k
	 * @return the matching completed, or <code>null</code> if a matching completed could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Completed fetchByUserLayoutKey(String USER_ID, String LAYOUT_PK)
		throws SystemException {
		return fetchByUserLayoutKey(USER_ID, LAYOUT_PK, true);
	}

	/**
	 * Returns the completed where USER_ID = &#63; and LAYOUT_PK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param USER_ID the u s e r_ i d
	 * @param LAYOUT_PK the l a y o u t_ p k
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching completed, or <code>null</code> if a matching completed could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Completed fetchByUserLayoutKey(String USER_ID, String LAYOUT_PK,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { USER_ID, LAYOUT_PK };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_USERLAYOUTKEY,
					finderArgs, this);
		}

		if (result instanceof Completed) {
			Completed completed = (Completed)result;

			if (!Validator.equals(USER_ID, completed.getUSER_ID()) ||
					!Validator.equals(LAYOUT_PK, completed.getLAYOUT_PK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_COMPLETED_WHERE);

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

				List<Completed> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERLAYOUTKEY,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"CompletedPersistenceImpl.fetchByUserLayoutKey(String, String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					Completed completed = list.get(0);

					result = completed;

					cacheResult(completed);

					if ((completed.getUSER_ID() == null) ||
							!completed.getUSER_ID().equals(USER_ID) ||
							(completed.getLAYOUT_PK() == null) ||
							!completed.getLAYOUT_PK().equals(LAYOUT_PK)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERLAYOUTKEY,
							finderArgs, completed);
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
			return (Completed)result;
		}
	}

	/**
	 * Removes the completed where USER_ID = &#63; and LAYOUT_PK = &#63; from the database.
	 *
	 * @param USER_ID the u s e r_ i d
	 * @param LAYOUT_PK the l a y o u t_ p k
	 * @return the completed that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Completed removeByUserLayoutKey(String USER_ID, String LAYOUT_PK)
		throws NoSuchCompletedException, SystemException {
		Completed completed = findByUserLayoutKey(USER_ID, LAYOUT_PK);

		return remove(completed);
	}

	/**
	 * Returns the number of completeds where USER_ID = &#63; and LAYOUT_PK = &#63;.
	 *
	 * @param USER_ID the u s e r_ i d
	 * @param LAYOUT_PK the l a y o u t_ p k
	 * @return the number of matching completeds
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

			query.append(_SQL_COUNT_COMPLETED_WHERE);

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

	private static final String _FINDER_COLUMN_USERLAYOUTKEY_USER_ID_1 = "completed.id.USER_ID IS NULL AND ";
	private static final String _FINDER_COLUMN_USERLAYOUTKEY_USER_ID_2 = "completed.id.USER_ID = ? AND ";
	private static final String _FINDER_COLUMN_USERLAYOUTKEY_USER_ID_3 = "(completed.id.USER_ID IS NULL OR completed.id.USER_ID = '') AND ";
	private static final String _FINDER_COLUMN_USERLAYOUTKEY_LAYOUT_PK_1 = "completed.id.LAYOUT_PK IS NULL";
	private static final String _FINDER_COLUMN_USERLAYOUTKEY_LAYOUT_PK_2 = "completed.id.LAYOUT_PK = ?";
	private static final String _FINDER_COLUMN_USERLAYOUTKEY_LAYOUT_PK_3 = "(completed.id.LAYOUT_PK IS NULL OR completed.id.LAYOUT_PK = '')";

	public CompletedPersistenceImpl() {
		setModelClass(Completed.class);
	}

	/**
	 * Caches the completed in the entity cache if it is enabled.
	 *
	 * @param completed the completed
	 */
	@Override
	public void cacheResult(Completed completed) {
		EntityCacheUtil.putResult(CompletedModelImpl.ENTITY_CACHE_ENABLED,
			CompletedImpl.class, completed.getPrimaryKey(), completed);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERLAYOUTKEY,
			new Object[] { completed.getUSER_ID(), completed.getLAYOUT_PK() },
			completed);

		completed.resetOriginalValues();
	}

	/**
	 * Caches the completeds in the entity cache if it is enabled.
	 *
	 * @param completeds the completeds
	 */
	@Override
	public void cacheResult(List<Completed> completeds) {
		for (Completed completed : completeds) {
			if (EntityCacheUtil.getResult(
						CompletedModelImpl.ENTITY_CACHE_ENABLED,
						CompletedImpl.class, completed.getPrimaryKey()) == null) {
				cacheResult(completed);
			}
			else {
				completed.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all completeds.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(CompletedImpl.class.getName());
		}

		EntityCacheUtil.clearCache(CompletedImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the completed.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Completed completed) {
		EntityCacheUtil.removeResult(CompletedModelImpl.ENTITY_CACHE_ENABLED,
			CompletedImpl.class, completed.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(completed);
	}

	@Override
	public void clearCache(List<Completed> completeds) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Completed completed : completeds) {
			EntityCacheUtil.removeResult(CompletedModelImpl.ENTITY_CACHE_ENABLED,
				CompletedImpl.class, completed.getPrimaryKey());

			clearUniqueFindersCache(completed);
		}
	}

	protected void cacheUniqueFindersCache(Completed completed) {
		if (completed.isNew()) {
			Object[] args = new Object[] {
					completed.getUSER_ID(), completed.getLAYOUT_PK()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERLAYOUTKEY, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERLAYOUTKEY, args,
				completed);
		}
		else {
			CompletedModelImpl completedModelImpl = (CompletedModelImpl)completed;

			if ((completedModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_USERLAYOUTKEY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						completed.getUSER_ID(), completed.getLAYOUT_PK()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERLAYOUTKEY,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERLAYOUTKEY,
					args, completed);
			}
		}
	}

	protected void clearUniqueFindersCache(Completed completed) {
		CompletedModelImpl completedModelImpl = (CompletedModelImpl)completed;

		Object[] args = new Object[] {
				completed.getUSER_ID(), completed.getLAYOUT_PK()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERLAYOUTKEY, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERLAYOUTKEY, args);

		if ((completedModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_USERLAYOUTKEY.getColumnBitmask()) != 0) {
			args = new Object[] {
					completedModelImpl.getOriginalUSER_ID(),
					completedModelImpl.getOriginalLAYOUT_PK()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERLAYOUTKEY,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERLAYOUTKEY,
				args);
		}
	}

	/**
	 * Creates a new completed with the primary key. Does not add the completed to the database.
	 *
	 * @param completedPK the primary key for the new completed
	 * @return the new completed
	 */
	@Override
	public Completed create(CompletedPK completedPK) {
		Completed completed = new CompletedImpl();

		completed.setNew(true);
		completed.setPrimaryKey(completedPK);

		return completed;
	}

	/**
	 * Removes the completed with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param completedPK the primary key of the completed
	 * @return the completed that was removed
	 * @throws com.rivetlogic.portlet.NoSuchCompletedException if a completed with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Completed remove(CompletedPK completedPK)
		throws NoSuchCompletedException, SystemException {
		return remove((Serializable)completedPK);
	}

	/**
	 * Removes the completed with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the completed
	 * @return the completed that was removed
	 * @throws com.rivetlogic.portlet.NoSuchCompletedException if a completed with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Completed remove(Serializable primaryKey)
		throws NoSuchCompletedException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Completed completed = (Completed)session.get(CompletedImpl.class,
					primaryKey);

			if (completed == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCompletedException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(completed);
		}
		catch (NoSuchCompletedException nsee) {
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
	protected Completed removeImpl(Completed completed)
		throws SystemException {
		completed = toUnwrappedModel(completed);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(completed)) {
				completed = (Completed)session.get(CompletedImpl.class,
						completed.getPrimaryKeyObj());
			}

			if (completed != null) {
				session.delete(completed);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (completed != null) {
			clearCache(completed);
		}

		return completed;
	}

	@Override
	public Completed updateImpl(
		com.rivetlogic.portlet.model.Completed completed)
		throws SystemException {
		completed = toUnwrappedModel(completed);

		boolean isNew = completed.isNew();

		Session session = null;

		try {
			session = openSession();

			if (completed.isNew()) {
				session.save(completed);

				completed.setNew(false);
			}
			else {
				session.merge(completed);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !CompletedModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(CompletedModelImpl.ENTITY_CACHE_ENABLED,
			CompletedImpl.class, completed.getPrimaryKey(), completed);

		clearUniqueFindersCache(completed);
		cacheUniqueFindersCache(completed);

		return completed;
	}

	protected Completed toUnwrappedModel(Completed completed) {
		if (completed instanceof CompletedImpl) {
			return completed;
		}

		CompletedImpl completedImpl = new CompletedImpl();

		completedImpl.setNew(completed.isNew());
		completedImpl.setPrimaryKey(completed.getPrimaryKey());

		completedImpl.setUSER_ID(completed.getUSER_ID());
		completedImpl.setLAYOUT_PK(completed.getLAYOUT_PK());
		completedImpl.setARTICLES_SET_ID(completed.getARTICLES_SET_ID());

		return completedImpl;
	}

	/**
	 * Returns the completed with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the completed
	 * @return the completed
	 * @throws com.rivetlogic.portlet.NoSuchCompletedException if a completed with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Completed findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCompletedException, SystemException {
		Completed completed = fetchByPrimaryKey(primaryKey);

		if (completed == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCompletedException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return completed;
	}

	/**
	 * Returns the completed with the primary key or throws a {@link com.rivetlogic.portlet.NoSuchCompletedException} if it could not be found.
	 *
	 * @param completedPK the primary key of the completed
	 * @return the completed
	 * @throws com.rivetlogic.portlet.NoSuchCompletedException if a completed with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Completed findByPrimaryKey(CompletedPK completedPK)
		throws NoSuchCompletedException, SystemException {
		return findByPrimaryKey((Serializable)completedPK);
	}

	/**
	 * Returns the completed with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the completed
	 * @return the completed, or <code>null</code> if a completed with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Completed fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Completed completed = (Completed)EntityCacheUtil.getResult(CompletedModelImpl.ENTITY_CACHE_ENABLED,
				CompletedImpl.class, primaryKey);

		if (completed == _nullCompleted) {
			return null;
		}

		if (completed == null) {
			Session session = null;

			try {
				session = openSession();

				completed = (Completed)session.get(CompletedImpl.class,
						primaryKey);

				if (completed != null) {
					cacheResult(completed);
				}
				else {
					EntityCacheUtil.putResult(CompletedModelImpl.ENTITY_CACHE_ENABLED,
						CompletedImpl.class, primaryKey, _nullCompleted);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(CompletedModelImpl.ENTITY_CACHE_ENABLED,
					CompletedImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return completed;
	}

	/**
	 * Returns the completed with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param completedPK the primary key of the completed
	 * @return the completed, or <code>null</code> if a completed with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Completed fetchByPrimaryKey(CompletedPK completedPK)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)completedPK);
	}

	/**
	 * Returns all the completeds.
	 *
	 * @return the completeds
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Completed> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the completeds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.rivetlogic.portlet.model.impl.CompletedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of completeds
	 * @param end the upper bound of the range of completeds (not inclusive)
	 * @return the range of completeds
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Completed> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the completeds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.rivetlogic.portlet.model.impl.CompletedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of completeds
	 * @param end the upper bound of the range of completeds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of completeds
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Completed> findAll(int start, int end,
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

		List<Completed> list = (List<Completed>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_COMPLETED);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMPLETED;

				if (pagination) {
					sql = sql.concat(CompletedModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Completed>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Completed>(list);
				}
				else {
					list = (List<Completed>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the completeds from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Completed completed : findAll()) {
			remove(completed);
		}
	}

	/**
	 * Returns the number of completeds.
	 *
	 * @return the number of completeds
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

				Query q = session.createQuery(_SQL_COUNT_COMPLETED);

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
	 * Initializes the completed persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.rivetlogic.portlet.model.Completed")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Completed>> listenersList = new ArrayList<ModelListener<Completed>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Completed>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(CompletedImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_COMPLETED = "SELECT completed FROM Completed completed";
	private static final String _SQL_SELECT_COMPLETED_WHERE = "SELECT completed FROM Completed completed WHERE ";
	private static final String _SQL_COUNT_COMPLETED = "SELECT COUNT(completed) FROM Completed completed";
	private static final String _SQL_COUNT_COMPLETED_WHERE = "SELECT COUNT(completed) FROM Completed completed WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "completed.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Completed exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Completed exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(CompletedPersistenceImpl.class);
	private static Completed _nullCompleted = new CompletedImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Completed> toCacheModel() {
				return _nullCompletedCacheModel;
			}
		};

	private static CacheModel<Completed> _nullCompletedCacheModel = new CacheModel<Completed>() {
			@Override
			public Completed toEntityModel() {
				return _nullCompleted;
			}
		};
}