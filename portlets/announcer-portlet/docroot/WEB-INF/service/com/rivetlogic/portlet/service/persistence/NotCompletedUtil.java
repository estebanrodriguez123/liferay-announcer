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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.rivetlogic.portlet.model.NotCompleted;

import java.util.List;

/**
 * The persistence utility for the not completed service. This utility wraps {@link NotCompletedPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author charles.rodriguez
 * @see NotCompletedPersistence
 * @see NotCompletedPersistenceImpl
 * @generated
 */
public class NotCompletedUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(NotCompleted notCompleted) {
		getPersistence().clearCache(notCompleted);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<NotCompleted> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<NotCompleted> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<NotCompleted> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static NotCompleted update(NotCompleted notCompleted)
		throws SystemException {
		return getPersistence().update(notCompleted);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static NotCompleted update(NotCompleted notCompleted,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(notCompleted, serviceContext);
	}

	/**
	* Returns the not completed where USER_ID = &#63; and LAYOUT_PK = &#63; or throws a {@link com.rivetlogic.portlet.NoSuchNotCompletedException} if it could not be found.
	*
	* @param USER_ID the u s e r_ i d
	* @param LAYOUT_PK the l a y o u t_ p k
	* @return the matching not completed
	* @throws com.rivetlogic.portlet.NoSuchNotCompletedException if a matching not completed could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.rivetlogic.portlet.model.NotCompleted findByUserLayoutKey(
		java.lang.String USER_ID, java.lang.String LAYOUT_PK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.rivetlogic.portlet.NoSuchNotCompletedException {
		return getPersistence().findByUserLayoutKey(USER_ID, LAYOUT_PK);
	}

	/**
	* Returns the not completed where USER_ID = &#63; and LAYOUT_PK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param USER_ID the u s e r_ i d
	* @param LAYOUT_PK the l a y o u t_ p k
	* @return the matching not completed, or <code>null</code> if a matching not completed could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.rivetlogic.portlet.model.NotCompleted fetchByUserLayoutKey(
		java.lang.String USER_ID, java.lang.String LAYOUT_PK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserLayoutKey(USER_ID, LAYOUT_PK);
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
	public static com.rivetlogic.portlet.model.NotCompleted fetchByUserLayoutKey(
		java.lang.String USER_ID, java.lang.String LAYOUT_PK,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUserLayoutKey(USER_ID, LAYOUT_PK, retrieveFromCache);
	}

	/**
	* Removes the not completed where USER_ID = &#63; and LAYOUT_PK = &#63; from the database.
	*
	* @param USER_ID the u s e r_ i d
	* @param LAYOUT_PK the l a y o u t_ p k
	* @return the not completed that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.rivetlogic.portlet.model.NotCompleted removeByUserLayoutKey(
		java.lang.String USER_ID, java.lang.String LAYOUT_PK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.rivetlogic.portlet.NoSuchNotCompletedException {
		return getPersistence().removeByUserLayoutKey(USER_ID, LAYOUT_PK);
	}

	/**
	* Returns the number of not completeds where USER_ID = &#63; and LAYOUT_PK = &#63;.
	*
	* @param USER_ID the u s e r_ i d
	* @param LAYOUT_PK the l a y o u t_ p k
	* @return the number of matching not completeds
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserLayoutKey(java.lang.String USER_ID,
		java.lang.String LAYOUT_PK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserLayoutKey(USER_ID, LAYOUT_PK);
	}

	/**
	* Caches the not completed in the entity cache if it is enabled.
	*
	* @param notCompleted the not completed
	*/
	public static void cacheResult(
		com.rivetlogic.portlet.model.NotCompleted notCompleted) {
		getPersistence().cacheResult(notCompleted);
	}

	/**
	* Caches the not completeds in the entity cache if it is enabled.
	*
	* @param notCompleteds the not completeds
	*/
	public static void cacheResult(
		java.util.List<com.rivetlogic.portlet.model.NotCompleted> notCompleteds) {
		getPersistence().cacheResult(notCompleteds);
	}

	/**
	* Creates a new not completed with the primary key. Does not add the not completed to the database.
	*
	* @param notCompletedPK the primary key for the new not completed
	* @return the new not completed
	*/
	public static com.rivetlogic.portlet.model.NotCompleted create(
		com.rivetlogic.portlet.service.persistence.NotCompletedPK notCompletedPK) {
		return getPersistence().create(notCompletedPK);
	}

	/**
	* Removes the not completed with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param notCompletedPK the primary key of the not completed
	* @return the not completed that was removed
	* @throws com.rivetlogic.portlet.NoSuchNotCompletedException if a not completed with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.rivetlogic.portlet.model.NotCompleted remove(
		com.rivetlogic.portlet.service.persistence.NotCompletedPK notCompletedPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.rivetlogic.portlet.NoSuchNotCompletedException {
		return getPersistence().remove(notCompletedPK);
	}

	public static com.rivetlogic.portlet.model.NotCompleted updateImpl(
		com.rivetlogic.portlet.model.NotCompleted notCompleted)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(notCompleted);
	}

	/**
	* Returns the not completed with the primary key or throws a {@link com.rivetlogic.portlet.NoSuchNotCompletedException} if it could not be found.
	*
	* @param notCompletedPK the primary key of the not completed
	* @return the not completed
	* @throws com.rivetlogic.portlet.NoSuchNotCompletedException if a not completed with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.rivetlogic.portlet.model.NotCompleted findByPrimaryKey(
		com.rivetlogic.portlet.service.persistence.NotCompletedPK notCompletedPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.rivetlogic.portlet.NoSuchNotCompletedException {
		return getPersistence().findByPrimaryKey(notCompletedPK);
	}

	/**
	* Returns the not completed with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param notCompletedPK the primary key of the not completed
	* @return the not completed, or <code>null</code> if a not completed with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.rivetlogic.portlet.model.NotCompleted fetchByPrimaryKey(
		com.rivetlogic.portlet.service.persistence.NotCompletedPK notCompletedPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(notCompletedPK);
	}

	/**
	* Returns all the not completeds.
	*
	* @return the not completeds
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.rivetlogic.portlet.model.NotCompleted> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<com.rivetlogic.portlet.model.NotCompleted> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
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
	public static java.util.List<com.rivetlogic.portlet.model.NotCompleted> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the not completeds from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of not completeds.
	*
	* @return the number of not completeds
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static NotCompletedPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (NotCompletedPersistence)PortletBeanLocatorUtil.locate(com.rivetlogic.portlet.service.ClpSerializer.getServletContextName(),
					NotCompletedPersistence.class.getName());

			ReferenceRegistry.registerReference(NotCompletedUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(NotCompletedPersistence persistence) {
	}

	private static NotCompletedPersistence _persistence;
}