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
    		
PortletPreferences preferences = renderRequest.getPreferences();
String[] ids = preferences.getValue("articleId", "0").split(",");
    
List<JournalArticle> currentArticles = new ArrayList<JournalArticle>();
if(ids != null){
	if(!ids[0].equals("0")){		//The ids atributte has no values
		for(int i = 0; i<ids.length; i++){
			if(JournalArticleLocalServiceUtil.hasArticle(groupId, ids[i])){
				double version =
					JournalArticleLocalServiceUtil.getLatestVersion(groupId, ids[i]);
				currentArticles.add(JournalArticleLocalServiceUtil.getArticle(groupId,ids[i],version));
			}
			else{	//If article no longer exist, remove it from the list
				AnnouncerTools.removeArticle(preferences, themeDisplay, ids[i]);
			}
		}
	}
}
PortletURL iteratorURL = renderResponse.createRenderURL();
iteratorURL.setParameter("jspPage", "/html/announcer/edit.jsp");
%>

<liferay-ui:success key="article-delete" message="article-deleted-message" />
<liferay-ui:success key="article-up-down" message="article-up-down-message" />
<liferay-ui:success key="article-default" message="article-default-message" />

<div class="portet-announcer-edit">
	<portlet:actionURL var="actionURL" name="savePreferences" /> 
	
	<portlet:renderURL var="contentURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
        <portlet:param name="jspPage" value="/html/announcer/showArticles.jsp"/>
    	<portlet:param name="groupId" value="${groupId}"/>
    </portlet:renderURL>
	
	<aui:form method="POST" action="${actionURL}" name="fm">
	    <liferay-ui:search-container delta="10" iteratorURL="<%= iteratorURL %>" 
	    	emptyResultsMessage="empty-articles-message">
			
			<liferay-ui:search-container-results 
				total="<%=currentArticles.size() %>"
				results="<%=ListUtil.subList(currentArticles, searchContainer.getStart(), searchContainer.getEnd())%>" 
			/>
			
			<liferay-ui:search-container-row modelVar="article" keyProperty="articleId"
				className="com.liferay.portlet.journal.model.JournalArticle" >

				<c:set var="defaultValue" value="No" />
				<c:if test="${ article.articleId eq defaultArticle }">
					<c:set var="defaultValue" value="Yes" />
				</c:if>
				
				<liferay-ui:search-container-column-text 
					name="article-default" value="${defaultValue}" />	
				<liferay-ui:search-container-column-text 
					name="article-id" value="${article.articleId}" />	
				<liferay-ui:search-container-column-text 
					name="article-name" value="${article.titleCurrentValue}" />	
				<liferay-ui:search-container-column-jsp 
					path="/html/announcer/include/announcer_actions.jsp" />
			
			</liferay-ui:search-container-row>
			
			<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />	
		</liferay-ui:search-container> 
	    
	    <aui:fieldset>
			<aui:button-row>
			<aui:button type="button" cssClass="btn-primary" value="article-add"
	    		onClick="MyAnnouncerClass.displayArticles('${contentURL}')" />
    		</aui:button-row>
		</aui:fieldset>
	</aui:form>
</div>
