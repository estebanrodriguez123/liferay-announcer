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

package com.rivetlogic.portlet.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.rivetlogic.portlet.model.NotCompleted;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing NotCompleted in entity cache.
 *
 * @author charles.rodriguez
 * @see NotCompleted
 * @generated
 */
public class NotCompletedCacheModel implements CacheModel<NotCompleted>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{USER_ID=");
		sb.append(USER_ID);
		sb.append(", LAYOUT_PK=");
		sb.append(LAYOUT_PK);
		sb.append(", PANEL_CLOSE_DATE=");
		sb.append(PANEL_CLOSE_DATE);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public NotCompleted toEntityModel() {
		NotCompletedImpl notCompletedImpl = new NotCompletedImpl();

		if (USER_ID == null) {
			notCompletedImpl.setUSER_ID(StringPool.BLANK);
		}
		else {
			notCompletedImpl.setUSER_ID(USER_ID);
		}

		if (LAYOUT_PK == null) {
			notCompletedImpl.setLAYOUT_PK(StringPool.BLANK);
		}
		else {
			notCompletedImpl.setLAYOUT_PK(LAYOUT_PK);
		}

		if (PANEL_CLOSE_DATE == Long.MIN_VALUE) {
			notCompletedImpl.setPANEL_CLOSE_DATE(null);
		}
		else {
			notCompletedImpl.setPANEL_CLOSE_DATE(new Date(PANEL_CLOSE_DATE));
		}

		notCompletedImpl.resetOriginalValues();

		return notCompletedImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		USER_ID = objectInput.readUTF();
		LAYOUT_PK = objectInput.readUTF();
		PANEL_CLOSE_DATE = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (USER_ID == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(USER_ID);
		}

		if (LAYOUT_PK == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(LAYOUT_PK);
		}

		objectOutput.writeLong(PANEL_CLOSE_DATE);
	}

	public String USER_ID;
	public String LAYOUT_PK;
	public long PANEL_CLOSE_DATE;
}