<%--
    /**
    * Copyright (C) 2005-2014 Rivet Logic Corporation.
    *
    * This program is free software; you can redistribute it and/or
    * modify it under the terms of the GNU General Public License
    * as published by the Free Software Foundation; version 2
    * of the License.
    *
    * This program is distributed in the hope that it will be useful,
    * but WITHOUT ANY WARRANTY; without even the implied warranty of
    * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    * GNU General Public License for more details.
    *
    * You should have received a copy of the GNU General Public License
    * along with this program; if not, write to the Free Software
    * Foundation, Inc., 51 Franklin Street, Fifth Floor,
    * Boston, MA 02110-1301, USA.
    */
--%>

<%@include file="/html/init.jsp" %>

<% 
ResultRow row = (ResultRow)request.getAttribute(com.liferay.portal.kernel.util.WebKeys.SEARCH_CONTAINER_RESULT_ROW);
JournalArticle article = (JournalArticle)row.getObject();
%>

<portlet:actionURL name="deleteArticle" var="deleteArticleUrl">
	<portlet:param name="articleId" value="<%=article.getArticleId()%>"/>
</portlet:actionURL>
			
<portlet:actionURL name="upArticle" var="upArticleUrl">
	<portlet:param name="articleId" value="<%=article.getArticleId()%>"/>
</portlet:actionURL>
			
<portlet:actionURL name="downArticle" var="downArticleUrl">
	<portlet:param name="articleId" value="<%=article.getArticleId()%>"/>
</portlet:actionURL>

<portlet:actionURL name="defaultArticle" var="defaultArticleUrl">
	<portlet:param name="articleId" value="<%=article.getArticleId()%>"/>
</portlet:actionURL>

<liferay-ui:icon-menu>
	<liferay-ui:icon-delete url="${deleteArticleUrl}" />
	<liferay-ui:icon image="top" message="Up" url="${upArticleUrl}" />
	<liferay-ui:icon image="bottom" message="Down" url="${downArticleUrl}" />
	<liferay-ui:icon image="activate" message="Default Article" url="${defaultArticleUrl}" />
</liferay-ui:icon-menu>
				