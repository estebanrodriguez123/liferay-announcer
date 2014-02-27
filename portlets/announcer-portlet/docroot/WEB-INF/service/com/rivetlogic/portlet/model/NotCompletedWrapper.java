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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link NotCompleted}.
 * </p>
 *
 * @author charles.rodriguez
 * @see NotCompleted
 * @generated
 */
public class NotCompletedWrapper implements NotCompleted,
	ModelWrapper<NotCompleted> {
	public NotCompletedWrapper(NotCompleted notCompleted) {
		_notCompleted = notCompleted;
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

	/**
	* Returns the primary key of this not completed.
	*
	* @return the primary key of this not completed
	*/
	@Override
	public com.rivetlogic.portlet.service.persistence.NotCompletedPK getPrimaryKey() {
		return _notCompleted.getPrimaryKey();
	}

	/**
	* Sets the primary key of this not completed.
	*
	* @param primaryKey the primary key of this not completed
	*/
	@Override
	public void setPrimaryKey(
		com.rivetlogic.portlet.service.persistence.NotCompletedPK primaryKey) {
		_notCompleted.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the u s e r_ i d of this not completed.
	*
	* @return the u s e r_ i d of this not completed
	*/
	@Override
	public java.lang.String getUSER_ID() {
		return _notCompleted.getUSER_ID();
	}

	/**
	* Sets the u s e r_ i d of this not completed.
	*
	* @param USER_ID the u s e r_ i d of this not completed
	*/
	@Override
	public void setUSER_ID(java.lang.String USER_ID) {
		_notCompleted.setUSER_ID(USER_ID);
	}

	/**
	* Returns the l a y o u t_ p k of this not completed.
	*
	* @return the l a y o u t_ p k of this not completed
	*/
	@Override
	public java.lang.String getLAYOUT_PK() {
		return _notCompleted.getLAYOUT_PK();
	}

	/**
	* Sets the l a y o u t_ p k of this not completed.
	*
	* @param LAYOUT_PK the l a y o u t_ p k of this not completed
	*/
	@Override
	public void setLAYOUT_PK(java.lang.String LAYOUT_PK) {
		_notCompleted.setLAYOUT_PK(LAYOUT_PK);
	}

	/**
	* Returns the p a n e l_ c l o s e_ d a t e of this not completed.
	*
	* @return the p a n e l_ c l o s e_ d a t e of this not completed
	*/
	@Override
	public java.util.Date getPANEL_CLOSE_DATE() {
		return _notCompleted.getPANEL_CLOSE_DATE();
	}

	/**
	* Sets the p a n e l_ c l o s e_ d a t e of this not completed.
	*
	* @param PANEL_CLOSE_DATE the p a n e l_ c l o s e_ d a t e of this not completed
	*/
	@Override
	public void setPANEL_CLOSE_DATE(java.util.Date PANEL_CLOSE_DATE) {
		_notCompleted.setPANEL_CLOSE_DATE(PANEL_CLOSE_DATE);
	}

	@Override
	public boolean isNew() {
		return _notCompleted.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_notCompleted.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _notCompleted.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_notCompleted.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _notCompleted.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _notCompleted.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_notCompleted.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _notCompleted.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_notCompleted.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_notCompleted.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_notCompleted.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new NotCompletedWrapper((NotCompleted)_notCompleted.clone());
	}

	@Override
	public int compareTo(com.rivetlogic.portlet.model.NotCompleted notCompleted) {
		return _notCompleted.compareTo(notCompleted);
	}

	@Override
	public int hashCode() {
		return _notCompleted.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.rivetlogic.portlet.model.NotCompleted> toCacheModel() {
		return _notCompleted.toCacheModel();
	}

	@Override
	public com.rivetlogic.portlet.model.NotCompleted toEscapedModel() {
		return new NotCompletedWrapper(_notCompleted.toEscapedModel());
	}

	@Override
	public com.rivetlogic.portlet.model.NotCompleted toUnescapedModel() {
		return new NotCompletedWrapper(_notCompleted.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _notCompleted.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _notCompleted.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_notCompleted.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NotCompletedWrapper)) {
			return false;
		}

		NotCompletedWrapper notCompletedWrapper = (NotCompletedWrapper)obj;

		if (Validator.equals(_notCompleted, notCompletedWrapper._notCompleted)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public NotCompleted getWrappedNotCompleted() {
		return _notCompleted;
	}

	@Override
	public NotCompleted getWrappedModel() {
		return _notCompleted;
	}

	@Override
	public void resetOriginalValues() {
		_notCompleted.resetOriginalValues();
	}

	private NotCompleted _notCompleted;
}