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

import com.rivetlogic.portlet.model.Completed;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Completed in entity cache.
 *
 * @author charles.rodriguez
 * @see Completed
 * @generated
 */
public class CompletedCacheModel implements CacheModel<Completed>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{USER_ID=");
		sb.append(USER_ID);
		sb.append(", LAYOUT_PK=");
		sb.append(LAYOUT_PK);
		sb.append(", ARTICLES_SET_ID=");
		sb.append(ARTICLES_SET_ID);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Completed toEntityModel() {
		CompletedImpl completedImpl = new CompletedImpl();

		if (USER_ID == null) {
			completedImpl.setUSER_ID(StringPool.BLANK);
		}
		else {
			completedImpl.setUSER_ID(USER_ID);
		}

		if (LAYOUT_PK == null) {
			completedImpl.setLAYOUT_PK(StringPool.BLANK);
		}
		else {
			completedImpl.setLAYOUT_PK(LAYOUT_PK);
		}

		if (ARTICLES_SET_ID == null) {
			completedImpl.setARTICLES_SET_ID(StringPool.BLANK);
		}
		else {
			completedImpl.setARTICLES_SET_ID(ARTICLES_SET_ID);
		}

		completedImpl.resetOriginalValues();

		return completedImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		USER_ID = objectInput.readUTF();
		LAYOUT_PK = objectInput.readUTF();
		ARTICLES_SET_ID = objectInput.readUTF();
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

		if (ARTICLES_SET_ID == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(ARTICLES_SET_ID);
		}
	}

	public String USER_ID;
	public String LAYOUT_PK;
	public String ARTICLES_SET_ID;
}