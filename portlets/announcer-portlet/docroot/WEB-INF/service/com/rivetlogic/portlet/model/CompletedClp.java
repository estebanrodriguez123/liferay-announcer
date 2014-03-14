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
import com.rivetlogic.portlet.service.CompletedLocalServiceUtil;
import com.rivetlogic.portlet.service.persistence.CompletedPK;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author charles.rodriguez
 */
public class CompletedClp extends BaseModelImpl<Completed> implements Completed {
	public CompletedClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return Completed.class;
	}

	@Override
	public String getModelClassName() {
		return Completed.class.getName();
	}

	@Override
	public CompletedPK getPrimaryKey() {
		return new CompletedPK(_USER_ID, _LAYOUT_PK);
	}

	@Override
	public void setPrimaryKey(CompletedPK primaryKey) {
		setUSER_ID(primaryKey.USER_ID);
		setLAYOUT_PK(primaryKey.LAYOUT_PK);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new CompletedPK(_USER_ID, _LAYOUT_PK);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((CompletedPK)primaryKeyObj);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("USER_ID", getUSER_ID());
		attributes.put("LAYOUT_PK", getLAYOUT_PK());
		attributes.put("ARTICLES_SET_ID", getARTICLES_SET_ID());

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

		String ARTICLES_SET_ID = (String)attributes.get("ARTICLES_SET_ID");

		if (ARTICLES_SET_ID != null) {
			setARTICLES_SET_ID(ARTICLES_SET_ID);
		}
	}

	@Override
	public String getUSER_ID() {
		return _USER_ID;
	}

	@Override
	public void setUSER_ID(String USER_ID) {
		_USER_ID = USER_ID;

		if (_completedRemoteModel != null) {
			try {
				Class<?> clazz = _completedRemoteModel.getClass();

				Method method = clazz.getMethod("setUSER_ID", String.class);

				method.invoke(_completedRemoteModel, USER_ID);
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

		if (_completedRemoteModel != null) {
			try {
				Class<?> clazz = _completedRemoteModel.getClass();

				Method method = clazz.getMethod("setLAYOUT_PK", String.class);

				method.invoke(_completedRemoteModel, LAYOUT_PK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getARTICLES_SET_ID() {
		return _ARTICLES_SET_ID;
	}

	@Override
	public void setARTICLES_SET_ID(String ARTICLES_SET_ID) {
		_ARTICLES_SET_ID = ARTICLES_SET_ID;

		if (_completedRemoteModel != null) {
			try {
				Class<?> clazz = _completedRemoteModel.getClass();

				Method method = clazz.getMethod("setARTICLES_SET_ID",
						String.class);

				method.invoke(_completedRemoteModel, ARTICLES_SET_ID);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getCompletedRemoteModel() {
		return _completedRemoteModel;
	}

	public void setCompletedRemoteModel(BaseModel<?> completedRemoteModel) {
		_completedRemoteModel = completedRemoteModel;
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

		Class<?> remoteModelClass = _completedRemoteModel.getClass();

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

		Object returnValue = method.invoke(_completedRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			CompletedLocalServiceUtil.addCompleted(this);
		}
		else {
			CompletedLocalServiceUtil.updateCompleted(this);
		}
	}

	@Override
	public Completed toEscapedModel() {
		return (Completed)ProxyUtil.newProxyInstance(Completed.class.getClassLoader(),
			new Class[] { Completed.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		CompletedClp clone = new CompletedClp();

		clone.setUSER_ID(getUSER_ID());
		clone.setLAYOUT_PK(getLAYOUT_PK());
		clone.setARTICLES_SET_ID(getARTICLES_SET_ID());

		return clone;
	}

	@Override
	public int compareTo(Completed completed) {
		CompletedPK primaryKey = completed.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CompletedClp)) {
			return false;
		}

		CompletedClp completed = (CompletedClp)obj;

		CompletedPK primaryKey = completed.getPrimaryKey();

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
		sb.append(", ARTICLES_SET_ID=");
		sb.append(getARTICLES_SET_ID());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.rivetlogic.portlet.model.Completed");
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
			"<column><column-name>ARTICLES_SET_ID</column-name><column-value><![CDATA[");
		sb.append(getARTICLES_SET_ID());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _USER_ID;
	private String _LAYOUT_PK;
	private String _ARTICLES_SET_ID;
	private BaseModel<?> _completedRemoteModel;
}