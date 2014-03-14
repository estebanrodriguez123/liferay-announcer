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

package com.rivetlogic.portlet.model;

import com.rivetlogic.portlet.service.persistence.NotCompletedPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.rivetlogic.portlet.service.http.NotCompletedServiceSoap}.
 *
 * @author charles.rodriguez
 * @see com.rivetlogic.portlet.service.http.NotCompletedServiceSoap
 * @generated
 */
public class NotCompletedSoap implements Serializable {
	public static NotCompletedSoap toSoapModel(NotCompleted model) {
		NotCompletedSoap soapModel = new NotCompletedSoap();

		soapModel.setUSER_ID(model.getUSER_ID());
		soapModel.setLAYOUT_PK(model.getLAYOUT_PK());
		soapModel.setPANEL_CLOSE_DATE(model.getPANEL_CLOSE_DATE());

		return soapModel;
	}

	public static NotCompletedSoap[] toSoapModels(NotCompleted[] models) {
		NotCompletedSoap[] soapModels = new NotCompletedSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NotCompletedSoap[][] toSoapModels(NotCompleted[][] models) {
		NotCompletedSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NotCompletedSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NotCompletedSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NotCompletedSoap[] toSoapModels(List<NotCompleted> models) {
		List<NotCompletedSoap> soapModels = new ArrayList<NotCompletedSoap>(models.size());

		for (NotCompleted model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NotCompletedSoap[soapModels.size()]);
	}

	public NotCompletedSoap() {
	}

	public NotCompletedPK getPrimaryKey() {
		return new NotCompletedPK(_USER_ID, _LAYOUT_PK);
	}

	public void setPrimaryKey(NotCompletedPK pk) {
		setUSER_ID(pk.USER_ID);
		setLAYOUT_PK(pk.LAYOUT_PK);
	}

	public String getUSER_ID() {
		return _USER_ID;
	}

	public void setUSER_ID(String USER_ID) {
		_USER_ID = USER_ID;
	}

	public String getLAYOUT_PK() {
		return _LAYOUT_PK;
	}

	public void setLAYOUT_PK(String LAYOUT_PK) {
		_LAYOUT_PK = LAYOUT_PK;
	}

	public Date getPANEL_CLOSE_DATE() {
		return _PANEL_CLOSE_DATE;
	}

	public void setPANEL_CLOSE_DATE(Date PANEL_CLOSE_DATE) {
		_PANEL_CLOSE_DATE = PANEL_CLOSE_DATE;
	}

	private String _USER_ID;
	private String _LAYOUT_PK;
	private Date _PANEL_CLOSE_DATE;
}