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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.rivetlogic.portlet.service.ClpSerializer;
import com.rivetlogic.portlet.service.NotCompletedLocalServiceUtil;
import com.rivetlogic.portlet.service.persistence.NotCompletedPK;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author charles.rodriguez
 */
public class NotCompletedClp extends BaseModelImpl<NotCompleted>
	implements NotCompleted {
	public NotCompletedClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return NotCompleted.class;
	}

	@Override
	public String getModelClassName() {
		return NotCompleted.class.getName();
	}

	@Override
	public NotCompletedPK getPrimaryKey() {
		return new NotCompletedPK(_USER_ID, _LAYOUT_PK);
	}

	@Override
	public void setPrimaryKey(NotCompletedPK primaryKey) {
		setUSER_ID(primaryKey.USER_ID);
		setLAYOUT_PK(primaryKey.LAYOUT_PK);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new NotCompletedPK(_USER_ID, _LAYOUT_PK);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((NotCompletedPK)primaryKeyObj);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("USER_ID", getUSER_ID());
		attributes.put("LAYOUT_PK", getLAYOUT_PK());
		attributes.put("PANEL_CLOSE_DATE", getPANEL_CLOSE_DATE());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String USER_ID = (String)attributes.get("USER_ID");

		if (USER_ID != null) {
			setUSER_ID(USER_ID);
		}

		String LAYOUT_PK = (String)attributes.get("LAYOUT_PK");

		if (LAYOUT_PK != null) {
			setLAYOUT_PK(LAYOUT_PK);
		}

		Date PANEL_CLOSE_DATE = (Date)attributes.get("PANEL_CLOSE_DATE");

		if (PANEL_CLOSE_DATE != null) {
			setPANEL_CLOSE_DATE(PANEL_CLOSE_DATE);
		}
	}

	@Override
	public String getUSER_ID() {
		return _USER_ID;
	}

	@Override
	public void setUSER_ID(String USER_ID) {
		_USER_ID = USER_ID;

		if (_notCompletedRemoteModel != null) {
			try {
				Class<?> clazz = _notCompletedRemoteModel.getClass();

				Method method = clazz.getMethod("setUSER_ID", String.class);

				method.invoke(_notCompletedRemoteModel, USER_ID);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getLAYOUT_PK() {
		return _LAYOUT_PK;
	}

	@Override
	public void setLAYOUT_PK(String LAYOUT_PK) {
		_LAYOUT_PK = LAYOUT_PK;

		if (_notCompletedRemoteModel != null) {
			try {
				Class<?> clazz = _notCompletedRemoteModel.getClass();

				Method method = clazz.getMethod("setLAYOUT_PK", String.class);

				method.invoke(_notCompletedRemoteModel, LAYOUT_PK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getPANEL_CLOSE_DATE() {
		return _PANEL_CLOSE_DATE;
	}

	@Override
	public void setPANEL_CLOSE_DATE(Date PANEL_CLOSE_DATE) {
		_PANEL_CLOSE_DATE = PANEL_CLOSE_DATE;

		if (_notCompletedRemoteModel != null) {
			try {
				Class<?> clazz = _notCompletedRemoteModel.getClass();

				Method method = clazz.getMethod("setPANEL_CLOSE_DATE",
						Date.class);

				method.invoke(_notCompletedRemoteModel, PANEL_CLOSE_DATE);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getNotCompletedRemoteModel() {
		return _notCompletedRemoteModel;
	}

	public void setNotCompletedRemoteModel(BaseModel<?> notCompletedRemoteModel) {
		_notCompletedRemoteModel = notCompletedRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _notCompletedRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_notCompletedRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			NotCompletedLocalServiceUtil.addNotCompleted(this);
		}
		else {
			NotCompletedLocalServiceUtil.updateNotCompleted(this);
		}
	}

	@Override
	public NotCompleted toEscapedModel() {
		return (NotCompleted)ProxyUtil.newProxyInstance(NotCompleted.class.getClassLoader(),
			new Class[] { NotCompleted.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		NotCompletedClp clone = new NotCompletedClp();

		clone.setUSER_ID(getUSER_ID());
		clone.setLAYOUT_PK(getLAYOUT_PK());
		clone.setPANEL_CLOSE_DATE(getPANEL_CLOSE_DATE());

		return clone;
	}

	@Override
	public int compareTo(NotCompleted notCompleted) {
		NotCompletedPK primaryKey = notCompleted.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NotCompletedClp)) {
			return false;
		}

		NotCompletedClp notCompleted = (NotCompletedClp)obj;

		NotCompletedPK primaryKey = notCompleted.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey().hashCode();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{USER_ID=");
		sb.append(getUSER_ID());
		sb.append(", LAYOUT_PK=");
		sb.append(getLAYOUT_PK());
		sb.append(", PANEL_CLOSE_DATE=");
		sb.append(getPANEL_CLOSE_DATE());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.rivetlogic.portlet.model.NotCompleted");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>USER_ID</column-name><column-value><![CDATA[");
		sb.append(getUSER_ID());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>LAYOUT_PK</column-name><column-value><![CDATA[");
		sb.append(getLAYOUT_PK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>PANEL_CLOSE_DATE</column-name><column-value><![CDATA[");
		sb.append(getPANEL_CLOSE_DATE());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _USER_ID;
	private String _LAYOUT_PK;
	private Date _PANEL_CLOSE_DATE;
	private BaseModel<?> _notCompletedRemoteModel;
}