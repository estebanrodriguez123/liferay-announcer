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

import com.liferay.portal.service.persistence.BasePersistence;

import com.rivetlogic.portlet.model.Completed;

/**
 * The persistence interface for the completed service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author charles.rodriguez
 * @see CompletedPersistenceImpl
 * @see CompletedUtil
 * @generated
 */
public interface CompletedPersistence extends BasePersistence<Completed> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CompletedUtil} to access the completed persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the completed where USER_ID = &#63; and LAYOUT_PK = &#63; or throws a {@link com.rivetlogic.portlet.NoSuchCompletedException} if it could not be found.
	*
	* @param USER_ID the u s e r_ i d
	* @param LAYOUT_PK the l a y o u t_ p k
	* @return the matching completed
	* @throws com.rivetlogic.portlet.NoSuchCompletedException if a matching completed could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.rivetlogic.portlet.model.Completed findByUserLayoutKey(
		java.lang.String USER_ID, java.lang.String LAYOUT_PK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.rivetlogic.portlet.NoSuchCompletedException;

	/**
	* Returns the completed where USER_ID = &#63; and LAYOUT_PK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param USER_ID the u s e r_ i d
	* @param LAYOUT_PK the l a y o u t_ p k
	* @return the matching completed, or <code>null</code> if a matching completed could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.rivetlogic.portlet.model.Completed fetchByUserLayoutKey(
		java.lang.String USER_ID, java.lang.String LAYOUT_PK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the completed where USER_ID = &#63; and LAYOUT_PK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param USER_ID the u s e r_ i d
	* @param LAYOUT_PK the l a y o u t_ p k
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching completed, or <code>null</code> if a matching completed could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.rivetlogic.portlet.model.Completed fetchByUserLayoutKey(
		java.lang.String USER_ID, java.lang.String LAYOUT_PK,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the completed where USER_ID = &#63; and LAYOUT_PK = &#63; from the database.
	*
	* @param USER_ID the u s e r_ i d
	* @param LAYOUT_PK the l a y o u t_ p k
	* @return the completed that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.rivetlogic.portlet.model.Completed removeByUserLayoutKey(
		java.lang.String USER_ID, java.lang.String LAYOUT_PK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.rivetlogic.portlet.NoSuchCompletedException;

	/**
	* Returns the number of completeds where USER_ID = &#63; and LAYOUT_PK = &#63;.
	*
	* @param USER_ID the u s e r_ i d
	* @param LAYOUT_PK the l a y o u t_ p k
	* @return the number of matching completeds
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserLayoutKey(java.lang.String USER_ID,
		java.lang.String LAYOUT_PK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the completed in the entity cache if it is enabled.
	*
	* @param completed the completed
	*/
	public void cacheResult(com.rivetlogic.portlet.model.Completed completed);

	/**
	* Caches the completeds in the entity cache if it is enabled.
	*
	* @param completeds the completeds
	*/
	public void cacheResult(
		java.util.List<com.rivetlogic.portlet.model.Completed> completeds);

	/**
	* Creates a new completed with the primary key. Does not add the completed to the database.
	*
	* @param completedPK the primary key for the new completed
	* @return the new completed
	*/
	public com.rivetlogic.portlet.model.Completed create(
		com.rivetlogic.portlet.service.persistence.CompletedPK completedPK);

	/**
	* Removes the completed with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param completedPK the primary key of the completed
	* @return the completed that was removed
	* @throws com.rivetlogic.portlet.NoSuchCompletedException if a completed with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.rivetlogic.portlet.model.Completed remove(
		com.rivetlogic.portlet.service.persistence.CompletedPK completedPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.rivetlogic.portlet.NoSuchCompletedException;

	public com.rivetlogic.portlet.model.Completed updateImpl(
		com.rivetlogic.portlet.model.Completed completed)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the completed with the primary key or throws a {@link com.rivetlogic.portlet.NoSuchCompletedException} if it could not be found.
	*
	* @param completedPK the primary key of the completed
	* @return the completed
	* @throws com.rivetlogic.portlet.NoSuchCompletedException if a completed with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.rivetlogic.portlet.model.Completed findByPrimaryKey(
		com.rivetlogic.portlet.service.persistence.CompletedPK completedPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.rivetlogic.portlet.NoSuchCompletedException;

	/**
	* Returns the completed with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param completedPK the primary key of the completed
	* @return the completed, or <code>null</code> if a completed with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.rivetlogic.portlet.model.Completed fetchByPrimaryKey(
		com.rivetlogic.portlet.service.persistence.CompletedPK completedPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the completeds.
	*
	* @return the completeds
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.rivetlogic.portlet.model.Completed> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.rivetlogic.portlet.model.Completed> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.rivetlogic.portlet.model.Completed> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the completeds from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of completeds.
	*
	* @return the number of completeds
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}