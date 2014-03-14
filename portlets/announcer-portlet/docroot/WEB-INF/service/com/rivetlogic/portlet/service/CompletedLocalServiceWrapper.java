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
 * Provides a wrapper for {@link CompletedLocalService}.
 *
 * @author charles.rodriguez
 * @see CompletedLocalService
 * @generated
 */
public class CompletedLocalServiceWrapper implements CompletedLocalService,
	ServiceWrapper<CompletedLocalService> {
	public CompletedLocalServiceWrapper(
		CompletedLocalService completedLocalService) {
		_completedLocalService = completedLocalService;
	}

	/**
	* Adds the completed to the database. Also notifies the appropriate model listeners.
	*
	* @param completed the completed
	* @return the completed that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.rivetlogic.portlet.model.Completed addCompleted(
		com.rivetlogic.portlet.model.Completed completed)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _completedLocalService.addCompleted(completed);
	}

	/**
	* Creates a new completed with the primary key. Does not add the completed to the database.
	*
	* @param completedPK the primary key for the new completed
	* @return the new completed
	*/
	@Override
	public com.rivetlogic.portlet.model.Completed createCompleted(
		com.rivetlogic.portlet.service.persistence.CompletedPK completedPK) {
		return _completedLocalService.createCompleted(completedPK);
	}

	/**
	* Deletes the completed with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param completedPK the primary key of the completed
	* @return the completed that was removed
	* @throws PortalException if a completed with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.rivetlogic.portlet.model.Completed deleteCompleted(
		com.rivetlogic.portlet.service.persistence.CompletedPK completedPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _completedLocalService.deleteCompleted(completedPK);
	}

	/**
	* Deletes the completed from the database. Also notifies the appropriate model listeners.
	*
	* @param completed the completed
	* @return the completed that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.rivetlogic.portlet.model.Completed deleteCompleted(
		com.rivetlogic.portlet.model.Completed completed)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _completedLocalService.deleteCompleted(completed);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _completedLocalService.dynamicQuery();
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
		return _completedLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.rivetlogic.portlet.model.impl.CompletedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _completedLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.rivetlogic.portlet.model.impl.CompletedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _completedLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _completedLocalService.dynamicQueryCount(dynamicQuery);
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
		return _completedLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.rivetlogic.portlet.model.Completed fetchCompleted(
		com.rivetlogic.portlet.service.persistence.CompletedPK completedPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _completedLocalService.fetchCompleted(completedPK);
	}

	/**
	* Returns the completed with the primary key.
	*
	* @param completedPK the primary key of the completed
	* @return the completed
	* @throws PortalException if a completed with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.rivetlogic.portlet.model.Completed getCompleted(
		com.rivetlogic.portlet.service.persistence.CompletedPK completedPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _completedLocalService.getCompleted(completedPK);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _completedLocalService.getPersistedModel(primaryKeyObj);
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
	public java.util.List<com.rivetlogic.portlet.model.Completed> getCompleteds(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _completedLocalService.getCompleteds(start, end);
	}

	/**
	* Returns the number of completeds.
	*
	* @return the number of completeds
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getCompletedsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _completedLocalService.getCompletedsCount();
	}

	/**
	* Updates the completed in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param completed the completed
	* @return the completed that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.rivetlogic.portlet.model.Completed updateCompleted(
		com.rivetlogic.portlet.model.Completed completed)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _completedLocalService.updateCompleted(completed);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _completedLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_completedLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _completedLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public java.util.List<com.rivetlogic.portlet.model.Completed> CompletedAllUsers() {
		return _completedLocalService.CompletedAllUsers();
	}

	@Override
	public boolean userExist(java.lang.String userId, java.lang.String layoutPK) {
		return _completedLocalService.userExist(userId, layoutPK);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public CompletedLocalService getWrappedCompletedLocalService() {
		return _completedLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedCompletedLocalService(
		CompletedLocalService completedLocalService) {
		_completedLocalService = completedLocalService;
	}

	@Override
	public CompletedLocalService getWrappedService() {
		return _completedLocalService;
	}

	@Override
	public void setWrappedService(CompletedLocalService completedLocalService) {
		_completedLocalService = completedLocalService;
	}

	private CompletedLocalService _completedLocalService;
}