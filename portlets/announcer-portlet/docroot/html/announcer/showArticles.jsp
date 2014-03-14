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
long groupId = themeDisplay.getScopeGroupId();

PortletURL iteratorURL = renderResponse.createRenderURL();
iteratorURL.setParameter("jspPage", "/html/announcer/showArticles.jsp");
iteratorURL.setParameter("groupId", String.valueOf(groupId));
%>

<portlet:actionURL name="addArticles" var="addArticlesURL" >
</portlet:actionURL>
<div id="<portlet:namespace />container">

<liferay-ui:success key="added-articles" message="added-articles-message" />
<liferay-ui:success key="selected-articles" message="selected-articles-message" />

<aui:form name="fm_add_articles" action="${addArticlesURL}" method="POST">

<liferay-ui:search-container delta="5" iteratorURL="<%= iteratorURL %>"
	emptyResultsMessage="empty-articles-message">
	
	<liferay-ui:search-container-results
		total="<%=JournalArticleLocalServiceUtil.searchCount(groupId, 0L, 0) %>"
		results="<%=JournalArticleLocalServiceUtil.search(groupId, 0L, 0,
				searchContainer.getStart(), searchContainer.getEnd())%>"
	/>
 
	<liferay-ui:search-container-row modelVar="content" keyProperty="articleId"
		className="com.liferay.portlet.journal.model.JournalArticle">
 	
		<liferay-ui:search-container-column-text 
			name="article-id" value="${content.articleId}" />	
		<liferay-ui:search-container-column-text 
			name="article-name" value="${content.titleCurrentValue}" />	
		<liferay-ui:search-container-column-text 
			name="article-mod-date" value="${content.modifiedDate}" />	
		<liferay-ui:search-container-column-text name="article-actions">
			<input type="checkbox" name="selectArticleCheckBox" 
				onClick="MyAnnouncerClass.handleClick('${content.articleId}', this,'${pns}')" />       
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>
	
	<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />

</liferay-ui:search-container>

<aui:input name="selectedIds" id="selectedIds" type="hidden"/>

<aui:fieldset>
	<aui:button-row>
		<aui:button name="saveButton" type="submit"
			value="article-add" onclick="window.close()" />
		<aui:button type="cancel" value="Close" onClick="window.close()" />
    </aui:button-row>
</aui:fieldset>
</aui:form>
</div>