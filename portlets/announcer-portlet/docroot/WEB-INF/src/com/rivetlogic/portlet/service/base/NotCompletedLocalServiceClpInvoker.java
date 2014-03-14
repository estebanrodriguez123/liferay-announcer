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

package com.rivetlogic.portlet.service.base;

import com.rivetlogic.portlet.service.NotCompletedLocalServiceUtil;

import java.util.Arrays;

/**
 * @author charles.rodriguez
 * @generated
 */
public class NotCompletedLocalServiceClpInvoker {
	public NotCompletedLocalServiceClpInvoker() {
		_methodName0 = "addNotCompleted";

		_methodParameterTypes0 = new String[] {
				"com.rivetlogic.portlet.model.NotCompleted"
			};

		_methodName1 = "createNotCompleted";

		_methodParameterTypes1 = new String[] {
				"com.rivetlogic.portlet.service.persistence.NotCompletedPK"
			};

		_methodName2 = "deleteNotCompleted";

		_methodParameterTypes2 = new String[] {
				"com.rivetlogic.portlet.service.persistence.NotCompletedPK"
			};

		_methodName3 = "deleteNotCompleted";

		_methodParameterTypes3 = new String[] {
				"com.rivetlogic.portlet.model.NotCompleted"
			};

		_methodName4 = "dynamicQuery";

		_methodParameterTypes4 = new String[] {  };

		_methodName5 = "dynamicQuery";

		_methodParameterTypes5 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName6 = "dynamicQuery";

		_methodParameterTypes6 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
			};

		_methodName7 = "dynamicQuery";

		_methodParameterTypes7 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName8 = "dynamicQueryCount";

		_methodParameterTypes8 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName9 = "dynamicQueryCount";

		_methodParameterTypes9 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery",
				"com.liferay.portal.kernel.dao.orm.Projection"
			};

		_methodName10 = "fetchNotCompleted";

		_methodParameterTypes10 = new String[] {
				"com.rivetlogic.portlet.service.persistence.NotCompletedPK"
			};

		_methodName11 = "getNotCompleted";

		_methodParameterTypes11 = new String[] {
				"com.rivetlogic.portlet.service.persistence.NotCompletedPK"
			};

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getNotCompleteds";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getNotCompletedsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateNotCompleted";

		_methodParameterTypes15 = new String[] {
				"com.rivetlogic.portlet.model.NotCompleted"
			};

		_methodName40 = "getBeanIdentifier";

		_methodParameterTypes40 = new String[] {  };

		_methodName41 = "setBeanIdentifier";

		_methodParameterTypes41 = new String[] { "java.lang.String" };

		_methodName46 = "addNotCompleted";

		_methodParameterTypes46 = new String[] {
				"com.rivetlogic.portlet.model.NotCompleted"
			};

		_methodName47 = "updateNotCompleted";

		_methodParameterTypes47 = new String[] {
				"com.rivetlogic.portlet.model.NotCompleted"
			};

		_methodName48 = "deleteNotCompleted";

		_methodParameterTypes48 = new String[] {
				"com.rivetlogic.portlet.model.NotCompleted"
			};

		_methodName49 = "deleteNotCompleted";

		_methodParameterTypes49 = new String[] {
				"com.rivetlogic.portlet.service.persistence.NotCompletedPK"
			};

		_methodName50 = "NotCompletedAllUsers";

		_methodParameterTypes50 = new String[] {  };

		_methodName51 = "userExist";

		_methodParameterTypes51 = new String[] {
				"java.lang.String", "java.lang.String"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return NotCompletedLocalServiceUtil.addNotCompleted((com.rivetlogic.portlet.model.NotCompleted)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return NotCompletedLocalServiceUtil.createNotCompleted((com.rivetlogic.portlet.service.persistence.NotCompletedPK)arguments[0]);
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return NotCompletedLocalServiceUtil.deleteNotCompleted((com.rivetlogic.portlet.service.persistence.NotCompletedPK)arguments[0]);
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return NotCompletedLocalServiceUtil.deleteNotCompleted((com.rivetlogic.portlet.model.NotCompleted)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return NotCompletedLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return NotCompletedLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return NotCompletedLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return NotCompletedLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return NotCompletedLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return NotCompletedLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return NotCompletedLocalServiceUtil.fetchNotCompleted((com.rivetlogic.portlet.service.persistence.NotCompletedPK)arguments[0]);
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return NotCompletedLocalServiceUtil.getNotCompleted((com.rivetlogic.portlet.service.persistence.NotCompletedPK)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return NotCompletedLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return NotCompletedLocalServiceUtil.getNotCompleteds(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return NotCompletedLocalServiceUtil.getNotCompletedsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return NotCompletedLocalServiceUtil.updateNotCompleted((com.rivetlogic.portlet.model.NotCompleted)arguments[0]);
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			return NotCompletedLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName41.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
			NotCompletedLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName46.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes46, parameterTypes)) {
			return NotCompletedLocalServiceUtil.addNotCompleted((com.rivetlogic.portlet.model.NotCompleted)arguments[0]);
		}

		if (_methodName47.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes47, parameterTypes)) {
			return NotCompletedLocalServiceUtil.updateNotCompleted((com.rivetlogic.portlet.model.NotCompleted)arguments[0]);
		}

		if (_methodName48.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes48, parameterTypes)) {
			return NotCompletedLocalServiceUtil.deleteNotCompleted((com.rivetlogic.portlet.model.NotCompleted)arguments[0]);
		}

		if (_methodName49.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes49, parameterTypes)) {
			return NotCompletedLocalServiceUtil.deleteNotCompleted((com.rivetlogic.portlet.service.persistence.NotCompletedPK)arguments[0]);
		}

		if (_methodName50.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes50, parameterTypes)) {
			return NotCompletedLocalServiceUtil.NotCompletedAllUsers();
		}

		if (_methodName51.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes51, parameterTypes)) {
			return NotCompletedLocalServiceUtil.userExist((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName0;
	private String[] _methodParameterTypes0;
	private String _methodName1;
	private String[] _methodParameterTypes1;
	private String _methodName2;
	private String[] _methodParameterTypes2;
	private String _methodName3;
	private String[] _methodParameterTypes3;
	private String _methodName4;
	private String[] _methodParameterTypes4;
	private String _methodName5;
	private String[] _methodParameterTypes5;
	private String _methodName6;
	private String[] _methodParameterTypes6;
	private String _methodName7;
	private String[] _methodParameterTypes7;
	private String _methodName8;
	private String[] _methodParameterTypes8;
	private String _methodName9;
	private String[] _methodParameterTypes9;
	private String _methodName10;
	private String[] _methodParameterTypes10;
	private String _methodName11;
	private String[] _methodParameterTypes11;
	private String _methodName12;
	private String[] _methodParameterTypes12;
	private String _methodName13;
	private String[] _methodParameterTypes13;
	private String _methodName14;
	private String[] _methodParameterTypes14;
	private String _methodName15;
	private String[] _methodParameterTypes15;
	private String _methodName40;
	private String[] _methodParameterTypes40;
	private String _methodName41;
	private String[] _methodParameterTypes41;
	private String _methodName46;
	private String[] _methodParameterTypes46;
	private String _methodName47;
	private String[] _methodParameterTypes47;
	private String _methodName48;
	private String[] _methodParameterTypes48;
	private String _methodName49;
	private String[] _methodParameterTypes49;
	private String _methodName50;
	private String[] _methodParameterTypes50;
	private String _methodName51;
	private String[] _methodParameterTypes51;
}