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

<div id="${pns}Tree"></div>
<aui:script use="rl-journal-tree-view">
var ${pns}TREE_CONTAINER = "treeDiv";
var ${pns}treeView;
${pns}treeView = new A.Rivet.JournalTreeView(
        	{
        		namespace: '${pns}',
        		resourceUrl: '${resourceUrl}',
        		treeBox: ${pns}TREE_CONTAINER,
        		groupId: <%=themeDisplay.getScopeGroupId() %>,
        		rootFolderId:'<%=DLFolderConstants.DEFAULT_PARENT_FOLDER_ID%>',
        		rootFolderLabel: 'Home'
        	}
    );
</aui:script>
<aui:fieldset>
	<aui:button-row>
		<aui:button type="cancel" value="close" onClick="window.close()" />
	</aui:button-row>
</aui:fieldset>