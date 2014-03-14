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

import com.rivetlogic.portlet.service.base.CompletedLocalServiceBaseImpl;
import com.rivetlogic.portlet.service.persistence.CompletedPK;
import com.rivetlogic.portlet.model.Completed;
import com.rivetlogic.portlet.service.CompletedLocalServiceUtil;

/**
 * The implementation of the completed local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.rivetlogic.portlet.service.CompletedLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author charles.rodriguez
 * @see com.rivetlogic.portlet.service.base.CompletedLocalServiceBaseImpl
 * @see com.rivetlogic.portlet.service.CompletedLocalServiceUtil
 */
public class CompletedLocalServiceImpl extends CompletedLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.rivetlogic.portlet.service.CompletedLocalServiceUtil} to access the completed local service.
	 */
	
	private static Log LOG = 
			LogFactoryUtil.getLog(CompletedLocalServiceImpl.class);
	
	@Override
	public Completed addCompleted(Completed newUser) 
			throws SystemException {
		CompletedPK onbCPK = new CompletedPK();
		
		Completed completed = 
				completedPersistence.create(onbCPK);
		
		completed.setUSER_ID(newUser.getUSER_ID());
		completed.setARTICLES_SET_ID(newUser.getARTICLES_SET_ID());		
		completed.setLAYOUT_PK(newUser.getLAYOUT_PK());

		completedPersistence.update(completed);
		
		return completed;
	}
	
	public Completed updateCompleted(Completed completed) 
			throws SystemException {
		
		completedPersistence.update(completed);
		
		return completed;
	}
	
	public Completed deleteCompleted(Completed completed) 
			throws SystemException {
		
		completedPersistence.remove(completed);
		
		return completed;
	}
	
	public Completed deleteCompleted(CompletedPK user) 
			throws SystemException, PortalException{
		return deleteCompleted(getCompleted(user));
	}
	
	//Returns all elements in the Completed table
	public List<Completed> CompletedAllUsers(){		
		List<Completed> allUsers = 
				new ArrayList<Completed>();
		int usersCount;
		
		try {
			usersCount = 
					CompletedLocalServiceUtil.getCompletedsCount();
			allUsers = 
					CompletedLocalServiceUtil.getCompleteds(0, usersCount);
		} catch (SystemException e) {
			LOG.error(e);
		}
	
		return allUsers;
	}
	
	//Returns true if the userId was found on the table
	public boolean userExist(String userId, String layoutPK) {
				
		try {
			if(completedPersistence.fetchByUserLayoutKey(userId,layoutPK) != null){
				return true;
			}
		} catch (SystemException e) {
			LOG.error(e);
		}
		return false;
	}
}