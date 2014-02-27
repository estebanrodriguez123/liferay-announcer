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

package com.rivetlogic.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NotCompletedLocalService}.
 *
 * @author charles.rodriguez
 * @see NotCompletedLocalService
 * @generated
 */
public class NotCompletedLocalServiceWrapper implements NotCompletedLocalService,
	ServiceWrapper<NotCompletedLocalService> {
	public NotCompletedLocalServiceWrapper(
		NotCompletedLocalService notCompletedLocalService) {
		_notCompletedLocalService = notCompletedLocalService;
	}

	/**
	* Adds the not completed to the database. Also notifies the appropriate model listeners.
	*
	* @param notCompleted the not completed
	* @return the not completed that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.rivetlogic.portlet.model.NotCompleted addNotCompleted(
		com.rivetlogic.portlet.model.NotCompleted notCompleted)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _notCompletedLocalService.addNotCompleted(notCompleted);
	}

	/**
	* Creates a new not completed with the primary key. Does not add the not completed to the database.
	*
	* @param notCompletedPK the primary key for the new not completed
	* @return the new not completed
	*/
	@Override
	public com.rivetlogic.portlet.model.NotCompleted createNotCompleted(
		com.rivetlogic.portlet.service.persistence.NotCompletedPK notCompletedPK) {
		return _notCompletedLocalService.createNotCompleted(notCompletedPK);
	}

	/**
	* Deletes the not completed with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param notCompletedPK the primary key of the not completed
	* @return the not completed that was removed
	* @throws PortalException if a not completed with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.rivetlogic.portlet.model.NotCompleted deleteNotCompleted(
		com.rivetlogic.portlet.service.persistence.NotCompletedPK notCompletedPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _notCompletedLocalService.deleteNotCompleted(notCompletedPK);
	}

	/**
	* Deletes the not completed from the database. Also notifies the appropriate model listeners.
	*
	* @param notCompleted the not completed
	* @return the not completed that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.rivetlogic.portlet.model.NotCompleted deleteNotCompleted(
		com.rivetlogic.portlet.model.NotCompleted notCompleted)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _notCompletedLocalService.deleteNotCompleted(notCompleted);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _notCompletedLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _notCompletedLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.rivetlogic.portlet.model.impl.NotCompletedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _notCompletedLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.rivetlogic.portlet.model.impl.NotCompletedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _notCompletedLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _notCompletedLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _notCompletedLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.rivetlogic.portlet.model.NotCompleted fetchNotCompleted(
		com.rivetlogic.portlet.service.persistence.NotCompletedPK notCompletedPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _notCompletedLocalService.fetchNotCompleted(notCompletedPK);
	}

	/**
	* Returns the not completed with the primary key.
	*
	* @param notCompletedPK the primary key of the not completed
	* @return the not completed
	* @throws PortalException if a not completed with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.rivetlogic.portlet.model.NotCompleted getNotCompleted(
		com.rivetlogic.portlet.service.persistence.NotCompletedPK notCompletedPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _notCompletedLocalService.getNotCompleted(notCompletedPK);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _notCompletedLocalService.getPersistedModel(primaryKeyObj);
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
	public java.util.List<com.rivetlogic.portlet.model.NotCompleted> getNotCompleteds(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _notCompletedLocalService.getNotCompleteds(start, end);
	}

	/**
	* Returns the number of not completeds.
	*
	* @return the number of not completeds
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getNotCompletedsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _notCompletedLocalService.getNotCompletedsCount();
	}

	/**
	* Updates the not completed in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param notCompleted the not completed
	* @return the not completed that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.rivetlogic.portlet.model.NotCompleted updateNotCompleted(
		com.rivetlogic.portlet.model.NotCompleted notCompleted)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _notCompletedLocalService.updateNotCompleted(notCompleted);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _notCompletedLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_notCompletedLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _notCompletedLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public java.util.List<com.rivetlogic.portlet.model.NotCompleted> NotCompletedAllUsers() {
		return _notCompletedLocalService.NotCompletedAllUsers();
	}

	@Override
	public boolean userExist(java.lang.String userId, java.lang.String layoutPK) {
		return _notCompletedLocalService.userExist(userId, layoutPK);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public NotCompletedLocalService getWrappedNotCompletedLocalService() {
		return _notCompletedLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedNotCompletedLocalService(
		NotCompletedLocalService notCompletedLocalService) {
		_notCompletedLocalService = notCompletedLocalService;
	}

	@Override
	public NotCompletedLocalService getWrappedService() {
		return _notCompletedLocalService;
	}

	@Override
	public void setWrappedService(
		NotCompletedLocalService notCompletedLocalService) {
		_notCompletedLocalService = notCompletedLocalService;
	}

	private NotCompletedLocalService _notCompletedLocalService;
}