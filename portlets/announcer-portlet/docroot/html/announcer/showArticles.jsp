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

<%@include file="/html/init.jsp"%>

<%
long groupId = themeDisplay.getScopeGroupId();
List<Long> folderIds = new ArrayList<Long>();
folderIds.add(new Long(DLFolderConstants.DEFAULT_PARENT_FOLDER_ID));
JournalFolderLocalServiceUtil.getSubfolderIds(folderIds, groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);

PortletURL iteratorURL = renderResponse.createRenderURL();
iteratorURL.setParameter(AnnouncerPortlet.JSP_PAGE, "/html/announcer/showArticles.jsp");
iteratorURL.setParameter(AnnouncerPortlet.GROUP_ID, String.valueOf(groupId));
%>

<portlet:resourceURL var="closeURL"/>
<portlet:actionURL name="addArticles" var="addArticlesURL"/>
<div id="${pns}container">

    <liferay-ui:success key="added-articles"
        message="added-articles-message" />
    <liferay-ui:success key="selected-articles"
        message="selected-articles-message" />
	
	<liferay-ui:message key="article-autosave-message"/>
	
    <aui:form name="fm_add_articles" action="${addArticlesURL}"
        method="POST">

        <liferay-ui:search-container delta="5"
            iteratorURL="<%= iteratorURL %>"
            emptyResultsMessage="empty-articles-message">

            <liferay-ui:search-container-results
                total="<%=JournalArticleLocalServiceUtil.searchCount(groupId, folderIds,
                		WorkflowConstants.STATUS_APPROVED) %>"
                results="<%=JournalArticleLocalServiceUtil.search(groupId, folderIds, 
                		WorkflowConstants.STATUS_APPROVED, searchContainer.getStart(), searchContainer.getEnd())%>" />

            <liferay-ui:search-container-row modelVar="content"
                keyProperty="articleId"
                className="com.liferay.portlet.journal.model.JournalArticle">

                <liferay-ui:search-container-column-text
                    name="article-id" value="${content.articleId}" />
                <liferay-ui:search-container-column-text
                    name="article-name"
                    value="${content.titleCurrentValue}" />
                <liferay-ui:search-container-column-text
                    name="article-mod-date"
                    value="${content.modifiedDate}" />
                <liferay-ui:search-container-column-text
                    name="article-actions">
			        <c:choose>
			        	<c:when test="${  rf:arrContains( addedArticleIds, content.articleId ) }">
		                    <input type="checkbox" name="selectArticleCheckBox" id="selectArticleCheckBox" checked="checked" 
		                        onchange="MyAnnouncerClass.handleClick('${content.articleId}', '<%= portletId %>', this, '${closeURL}')" />
			        	</c:when>
			        	<c:otherwise>
		                    <input type="checkbox" name="selectArticleCheckBox" id="selectArticleCheckBox"
		                        onchange="MyAnnouncerClass.handleClick('${content.articleId}', '<%= portletId %>', this, '${closeURL}')" />
			        	</c:otherwise>
			        </c:choose>
                </liferay-ui:search-container-column-text>
            </liferay-ui:search-container-row>

            <liferay-ui:search-iterator
                searchContainer="<%= searchContainer %>" />

        </liferay-ui:search-container>

        <aui:fieldset>
            <aui:button-row>
                <aui:button type="cancel" value="close" onClick="window.close()" />
            </aui:button-row>
        </aui:fieldset>
    </aui:form>
</div>