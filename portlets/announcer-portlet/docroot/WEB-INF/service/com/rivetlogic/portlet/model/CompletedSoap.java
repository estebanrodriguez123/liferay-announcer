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

import com.rivetlogic.portlet.service.persistence.CompletedPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.rivetlogic.portlet.service.http.CompletedServiceSoap}.
 *
 * @author charles.rodriguez
 * @see com.rivetlogic.portlet.service.http.CompletedServiceSoap
 * @generated
 */
public class CompletedSoap implements Serializable {
	public static CompletedSoap toSoapModel(Completed model) {
		CompletedSoap soapModel = new CompletedSoap();

		soapModel.setUSER_ID(model.getUSER_ID());
		soapModel.setLAYOUT_PK(model.getLAYOUT_PK());
		soapModel.setARTICLES_SET_ID(model.getARTICLES_SET_ID());

		return soapModel;
	}

	public static CompletedSoap[] toSoapModels(Completed[] models) {
		CompletedSoap[] soapModels = new CompletedSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CompletedSoap[][] toSoapModels(Completed[][] models) {
		CompletedSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CompletedSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CompletedSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CompletedSoap[] toSoapModels(List<Completed> models) {
		List<CompletedSoap> soapModels = new ArrayList<CompletedSoap>(models.size());

		for (Completed model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CompletedSoap[soapModels.size()]);
	}

	public CompletedSoap() {
	}

	public CompletedPK getPrimaryKey() {
		return new CompletedPK(_USER_ID, _LAYOUT_PK);
	}

	public void setPrimaryKey(CompletedPK pk) {
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

	public String getARTICLES_SET_ID() {
		return _ARTICLES_SET_ID;
	}

	public void setARTICLES_SET_ID(String ARTICLES_SET_ID) {
		_ARTICLES_SET_ID = ARTICLES_SET_ID;
	}

	private String _USER_ID;
	private String _LAYOUT_PK;
	private String _ARTICLES_SET_ID;
}