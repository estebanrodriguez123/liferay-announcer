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

<portlet:renderURL var="contentURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="jspPage" value="/html/announcer/content.jsp"/>
</portlet:renderURL>

<c:if test="${ defaultArticle != '0' }">
	<div class="default-article">
		<liferay-ui:journal-article 
        	groupId="${groupId}" 
        	articleId="${defaultArticle}" />
	</div>
	
	<c:if test="${signedIn}">
		<aui:fieldset>
			<aui:button-row>
				<aui:button type="button" cssClass="btn-primary" value="announcer-launch"
		    		onClick="MyAnnouncerClass.displayContent('${user.uuid}','${articleVersionId}','${contentURL}','${pns}')" />
	    	</aui:button-row>
		</aui:fieldset>
	</c:if>
</c:if>

<c:if test="${showAnnouncer}"> 
<script type="text/javascript">
AUI().ready(function(){
	MyAnnouncerClass.displayContent('${user.uuid}','${articleVersionId}','${contentURL}','${pns}');
});
</script>
</c:if>
