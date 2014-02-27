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
    String articles = renderRequest.getPreferences().getValue("articleId", "0");
%>

<div id="<portlet:namespace />articles" class="announcer-articles">
    <%
        for (String article : StringUtil.split(articles, ",")) {
    %>
    <div class="aui-carousel-item">
        <div class="aui-carousel-item-content">
        	<liferay-ui:journal-article 
        		groupId="<%=themeDisplay.getScopeGroupId()%>" 
        		articleId="<%=article%>" />
        </div>
    </div>
    <%
        }
    %>
</div>

<script type="text/javascript">
AUI().ready('aui-carousel','node','event', function(A) {
   var carousel=new A.Carousel({
        contentBox : '#<portlet:namespace />articles',
        height : 450,
        playing: false
    }).render();
    
    var carouselNode = A.one('#<portlet:namespace />articles');
    var newPrev = A.Node.create('<button type="button" class="prev">Previous</button>');
    var newNext = A.Node.create('<button type="button" class="next">Next</button>');
    var portletId = '<%=portletId%>';
    window.parent.setPortletId(portletId);
    carouselNode.prepend(newNext);
    carouselNode.prepend(newPrev);
    newNext.on('click',function(e){
	    if(A.one('.carousel-menu-active').get("text")!=(A.all('.carousel-menu-index').size() - 1).toString()){
	    	carousel.next();
	    }
	    if(A.one('.carousel-menu-active').get("text")==(A.all('.carousel-menu-index').size() - 1).toString()){
	    	window.parent.MyAnnouncerClass.showAnnouncerCloseBtn('${pns}');
	    }
    });
    newPrev.on('click',function(e){
        if(A.one('.carousel-menu-active').get("text")!='0'){
    		carousel.prev();
        }
	});
});
</script>
