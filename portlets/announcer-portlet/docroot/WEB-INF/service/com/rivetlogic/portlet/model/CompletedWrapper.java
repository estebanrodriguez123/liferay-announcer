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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Completed}.
 * </p>
 *
 * @author charles.rodriguez
 * @see Completed
 * @generated
 */
public class CompletedWrapper implements Completed, ModelWrapper<Completed> {
	public CompletedWrapper(Completed completed) {
		_completed = completed;
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

	/**
	* Returns the primary key of this completed.
	*
	* @return the primary key of this completed
	*/
	@Override
	public com.rivetlogic.portlet.service.persistence.CompletedPK getPrimaryKey() {
		return _completed.getPrimaryKey();
	}

	/**
	* Sets the primary key of this completed.
	*
	* @param primaryKey the primary key of this completed
	*/
	@Override
	public void setPrimaryKey(
		com.rivetlogic.portlet.service.persistence.CompletedPK primaryKey) {
		_completed.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the u s e r_ i d of this completed.
	*
	* @return the u s e r_ i d of this completed
	*/
	@Override
	public java.lang.String getUSER_ID() {
		return _completed.getUSER_ID();
	}

	/**
	* Sets the u s e r_ i d of this completed.
	*
	* @param USER_ID the u s e r_ i d of this completed
	*/
	@Override
	public void setUSER_ID(java.lang.String USER_ID) {
		_completed.setUSER_ID(USER_ID);
	}

	/**
	* Returns the l a y o u t_ p k of this completed.
	*
	* @return the l a y o u t_ p k of this completed
	*/
	@Override
	public java.lang.String getLAYOUT_PK() {
		return _completed.getLAYOUT_PK();
	}

	/**
	* Sets the l a y o u t_ p k of this completed.
	*
	* @param LAYOUT_PK the l a y o u t_ p k of this completed
	*/
	@Override
	public void setLAYOUT_PK(java.lang.String LAYOUT_PK) {
		_completed.setLAYOUT_PK(LAYOUT_PK);
	}

	/**
	* Returns the a r t i c l e s_ s e t_ i d of this completed.
	*
	* @return the a r t i c l e s_ s e t_ i d of this completed
	*/
	@Override
	public java.lang.String getARTICLES_SET_ID() {
		return _completed.getARTICLES_SET_ID();
	}

	/**
	* Sets the a r t i c l e s_ s e t_ i d of this completed.
	*
	* @param ARTICLES_SET_ID the a r t i c l e s_ s e t_ i d of this completed
	*/
	@Override
	public void setARTICLES_SET_ID(java.lang.String ARTICLES_SET_ID) {
		_completed.setARTICLES_SET_ID(ARTICLES_SET_ID);
	}

	@Override
	public boolean isNew() {
		return _completed.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_completed.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _completed.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_completed.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _completed.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _completed.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_completed.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _completed.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_completed.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_completed.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_completed.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new CompletedWrapper((Completed)_completed.clone());
	}

	@Override
	public int compareTo(com.rivetlogic.portlet.model.Completed completed) {
		return _completed.compareTo(completed);
	}

	@Override
	public int hashCode() {
		return _completed.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.rivetlogic.portlet.model.Completed> toCacheModel() {
		return _completed.toCacheModel();
	}

	@Override
	public com.rivetlogic.portlet.model.Completed toEscapedModel() {
		return new CompletedWrapper(_completed.toEscapedModel());
	}

	@Override
	public com.rivetlogic.portlet.model.Completed toUnescapedModel() {
		return new CompletedWrapper(_completed.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _completed.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _completed.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_completed.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CompletedWrapper)) {
			return false;
		}

		CompletedWrapper completedWrapper = (CompletedWrapper)obj;

		if (Validator.equals(_completed, completedWrapper._completed)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Completed getWrappedCompleted() {
		return _completed;
	}

	@Override
	public Completed getWrappedModel() {
		return _completed;
	}

	@Override
	public void resetOriginalValues() {
		_completed.resetOriginalValues();
	}

	private Completed _completed;
}