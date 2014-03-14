/**
 * Copyright (c) 2014 Rivet Logic Corporation. All rights reserved.
 */

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

package com.rivetlogic.portlet.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.rivetlogic.portlet.service.base.NotCompletedLocalServiceBaseImpl;
import com.rivetlogic.portlet.service.persistence.NotCompletedPK;
import com.rivetlogic.portlet.model.NotCompleted;
import com.rivetlogic.portlet.service.NotCompletedLocalServiceUtil;

/**
 * The implementation of the not completed local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.rivetlogic.portlet.service.NotCompletedLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author charles.rodriguez
 * @see com.rivetlogic.portlet.service.base.NotCompletedLocalServiceBaseImpl
 * @see com.rivetlogic.portlet.service.NotCompletedLocalServiceUtil
 */
public class NotCompletedLocalServiceImpl
	extends NotCompletedLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.rivetlogic.portlet.service.NotCompletedLocalServiceUtil} to access the not completed local service.
	 */
	
	private static Log LOG = 
			LogFactoryUtil.getLog(NotCompletedLocalServiceImpl.class);
	
	@Override
	public NotCompleted addNotCompleted(NotCompleted newUser) 
			throws SystemException {
		NotCompletedPK onbNCPK = new NotCompletedPK();

		NotCompleted NotCompleted = 
				notCompletedPersistence.create(onbNCPK);
		
		NotCompleted.setUSER_ID(newUser.getUSER_ID());
		NotCompleted.setLAYOUT_PK(newUser.getLAYOUT_PK());
		NotCompleted.setPANEL_CLOSE_DATE(newUser.getPANEL_CLOSE_DATE());		
		
		notCompletedPersistence.update(NotCompleted);
		
		return NotCompleted;
	}
	
	public NotCompleted updateNotCompleted(NotCompleted NotCompleted) 
			throws SystemException {
		
		notCompletedPersistence.update(NotCompleted);
		
		return NotCompleted;
	}
	
	public NotCompleted deleteNotCompleted(NotCompleted NotCompleted) 
			throws SystemException {
		
		notCompletedPersistence.remove(NotCompleted);
		
		return NotCompleted;
	}
	
	public NotCompleted deleteNotCompleted(NotCompletedPK user) 
			throws SystemException, PortalException{
		
		 return deleteNotCompleted(getNotCompleted(user));
	}
	
	//Returns all elements in the NotCompleted table
	public List<NotCompleted> NotCompletedAllUsers(){		
		List<NotCompleted> allUsers = 
				new ArrayList<NotCompleted>();
		int usersCount;
		
		try {
			usersCount = 
					NotCompletedLocalServiceUtil.getNotCompletedsCount();
			allUsers = 
					NotCompletedLocalServiceUtil.getNotCompleteds(0, usersCount);
		} catch (SystemException e) {
			LOG.error(e);
		}
	
		return allUsers;
	}
	
	//Returns true if the userId was found on the table
	public boolean userExist(String userId, String layoutPK) {
				
		try {
			if(notCompletedPersistence.fetchByUserLayoutKey(userId, layoutPK) != null){
				return true;
			}
		} catch (SystemException e) {
			LOG.error(e);
		}
		return false;
	}
}