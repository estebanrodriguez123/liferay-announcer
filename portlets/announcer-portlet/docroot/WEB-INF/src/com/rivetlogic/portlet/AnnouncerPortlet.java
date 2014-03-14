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

package com.rivetlogic.portlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ValidatorException;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

public class AnnouncerPortlet extends MVCPortlet {
    private static final Log LOG = 
    	LogFactoryUtil.getLog(AnnouncerPortlet.class);

    @Override
	public void doView(RenderRequest request, RenderResponse response) 
			throws IOException, PortletException {
		
    	ThemeDisplay themeDisplay = 
    			(ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    	long groupId = themeDisplay.getScopeGroupId();
    	
    	PortletPreferences preferences = request.getPreferences();
    	boolean showAnnouncer = false;
    	if(themeDisplay.isSignedIn()){
    		String articleVersionId = 
    				preferences.getValue("articleIdConsecutive", "0");
    	    String articleIds =
    	    		preferences.getValue("articleId", "0");
    	    String articleIdsWithVersion =
    	    		preferences.getValue("articleIdWithVersion", "0");
    	    
    	    StringBuilder articleWithVersionBuilder = new StringBuilder();
    	    if(!articleIds.equals("0")){
    		    for (String articleId : articleIds.split(",")) {
    		        double version;
					try {
						version = 
							JournalArticleLocalServiceUtil.getLatestVersion(groupId, articleId);
		    		    articleWithVersionBuilder.append(articleId);
		    		    articleWithVersionBuilder.append(":");
		    		    articleWithVersionBuilder.append(version);
		    		    if(!articleWithVersionBuilder.toString().equals(articleIdsWithVersion)){
		    		        articleVersionId=String.valueOf(Integer.valueOf(articleVersionId)+1);
		    		    }
					} catch (PortalException e) {
						LOG.error(e);
					} catch (SystemException e) {
						LOG.error(e);
					}
    		    }
    		    
    	    	try {
    	    		String layoutPK = String.valueOf(themeDisplay.getLayout().getPrimaryKey());
    	    		showAnnouncer =
    	    			AnnouncerTools.showAnnouncer(themeDisplay.getRealUser().getUuid(),layoutPK,articleVersionId);
    	    		request.setAttribute("articleVersionId", articleVersionId);
				} catch (SystemException e) {
					LOG.error(e);
				}
    	    }
    	}
    	request.setAttribute("groupId", groupId);
    	request.setAttribute("showAnnouncer", showAnnouncer);
    	request.setAttribute("signedIn", themeDisplay.isSignedIn());
    	String defaultArticle = preferences.getValue("defaultArticle", "0");
    	request.setAttribute("defaultArticle", defaultArticle);
    	
		super.doView(request, response);
	}
    
    @Override
	public void doEdit(RenderRequest request, RenderResponse response) 
			throws IOException, PortletException {
		
    	ThemeDisplay themeDisplay = 
    			(ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    	long groupId = themeDisplay.getScopeGroupId();
    	request.setAttribute("groupId", groupId);
    	
    	PortletPreferences preferences = request.getPreferences();
    	String[] ids = preferences.getValue("articleId", "0").split(","); 
    	String defaultArticle = preferences.getValue("defaultArticle", "0");
    	request.setAttribute("defaultArticle", defaultArticle);
    	request.setAttribute("ids", ids);
    	
		super.doEdit(request, response);
	}
    
    public void addArticles(ActionRequest request, ActionResponse response)
    		throws PortletException, IOException, 
		PortalException, SystemException {

    	PortletPreferences preferences = request.getPreferences();
    	String articles = preferences.getValue("articlesRaw", "0");
    	
    	String newIds = "";
    	String addedIds[] = ParamUtil.getString(request, "selectedIds").split(" ");
    	
    	for(int i = 0; i < addedIds.length;i++){
    		if(!articles.contains(addedIds[i])){	//Don't add if it already exist
    			newIds += addedIds[i] + " ";
    		}
    	}

    	if(!articles.equals("0")){	//There is no articles
    		articles += newIds; 
    	}else{
    		articles = newIds;
    	}
    	articles = articles.replaceAll("\\s+"," ");   
    	updatePreferences(request,response,articles);
    	
    	if(newIds.equals("")){
    		SessionMessages.add(request, "selected-articles");
    	}
    	else{
    		SessionMessages.add(request, "added-articles");
    	}
    	response.setRenderParameter(
    		"jspPage", "/html/announcer/showArticles.jsp");
	}
    
    public void deleteArticle(ActionRequest request, ActionResponse response)
    		throws PortletException, IOException, 
    	PortalException, SystemException {
                		        	
        String articleId = ParamUtil.getString(request, "articleId");
        PortletPreferences preferences = request.getPreferences();
        String articles= preferences.getValue("articlesRaw", "0");
        String defaultArticle = preferences.getValue("defaultArticle", "0");
        
        if(defaultArticle.equals(articleId)){
        	preferences.setValue("defaultArticle", "0");
			preferences.store();
        }
    		
        articles = articles.replace(String.valueOf(articleId), "");  //remove id
        articles = articles.replaceAll("\\s+"," ");
        updatePreferences(request,response,articles);
        
        SessionMessages.add(request, "article-delete");
    }
    
    public void defaultArticle(ActionRequest request, ActionResponse response){
    	String defaultArticle = ParamUtil.getString(request, "articleId");
    	PortletPreferences preferences = request.getPreferences();
    	try {
			preferences.setValue("defaultArticle", defaultArticle);
			preferences.store();
		} catch (ReadOnlyException e) {
			LOG.error(e);
		} catch (ValidatorException e) {
			LOG.error(e);
		} catch (IOException e) {
			LOG.error(e);
		}
    	SessionMessages.add(request, "article-default");
    }
    
    public void upArticle(ActionRequest request, ActionResponse response)
    		throws PortletException, IOException, 
    	PortalException, SystemException {
        	
        ThemeDisplay themeDisplay = 
        	(ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long groupId = themeDisplay.getScopeGroupId();
        request.setAttribute("groupId", groupId);
        		        	
        String articleId = ParamUtil.getString(request, "articleId");
        PortletPreferences preferences = request.getPreferences();
        
        String[] ids = preferences.getValue("articleId", "0").split(",");
        List<String> currentArticles = new ArrayList<String>();
        for(int i = 0; i < ids.length; i++){
        	currentArticles.add(ids[i]);
        }
        
        int index = currentArticles.indexOf(articleId);
        if(index != 0){
        	Collections.swap(currentArticles, index, index-1);
        }
        
        String articlesRaw = "";
        for(int i = 0; i < currentArticles.size(); i++){
        	articlesRaw += currentArticles.get(i) + " ";
        }
       
        updatePreferences(request,response,articlesRaw);
        ids = preferences.getValue("articleId", "0").split(",");
        request.setAttribute("ids", ids);
        	
        SessionMessages.add(request, "article-up-down");
        
        response.setRenderParameter(
        	"jspPage", "/html/announcer/edit.jsp");
    }
    
    public void downArticle(ActionRequest request, ActionResponse response)
    		throws PortletException, IOException, 
    	PortalException, SystemException {
        	
    	ThemeDisplay themeDisplay = 
    		(ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long groupId = themeDisplay.getScopeGroupId();
        request.setAttribute("groupId", groupId);
        		        	
        String articleId = ParamUtil.getString(request, "articleId");
        PortletPreferences preferences = request.getPreferences();
            
        String[] ids = preferences.getValue("articleId", "0").split(",");
        List<String> currentArticles = new ArrayList<String>();
        for(int i = 0; i < ids.length; i++){
        	currentArticles.add(ids[i]);
        }
            
        int index = currentArticles.indexOf(articleId);
        if(index != (currentArticles.size()-1)){
        	Collections.swap(currentArticles, index, index+1);
        }
            
        String articlesRaw = "";
        for(int i = 0; i < currentArticles.size(); i++){
        	articlesRaw += currentArticles.get(i) + " ";
        }
           
        updatePreferences(request,response,articlesRaw);
        ids = preferences.getValue("articleId", "0").split(",");
        request.setAttribute("ids", ids);
        	
        SessionMessages.add(request, "article-up-down");
        
        response.setRenderParameter(
        	"jspPage", "/html/announcer/edit.jsp");
    }
    
    public void savePreferences(ActionRequest request, ActionResponse response) 
    		throws PortletException, IOException, 
    		PortalException, SystemException {
    	
		String articles = ParamUtil.getString(request, "articles");
		updatePreferences(request,response,articles);
    }
     
    public void updatePreferences(ActionRequest request, ActionResponse response, String articles) 
    		throws PortletException, IOException, 
    		PortalException, SystemException {
		PortletPreferences preferences = request.getPreferences();
		String articleIds = articles.trim().replaceAll("\\s", ",");
		if(articleIds.equals("")){
			articleIds = "0";
		}
		
		String articleWithVersionPref = "0";
		String articleConsecutive = "0";
		String articleWithVersion = "0";
		if(!articleIds.equals("0")){
			articleWithVersionPref = 
					preferences.getValue("articleIdWithVersion", "0"); //articleId1:version ...
			articleConsecutive = 
					preferences.getValue("articleIdConsecutive", "0");
			
			boolean updatedArticleIds = false, updatedArticleVersions = false;
		
			updatedArticleIds = 
					!preferences.getValue("articleId", "0").equals(articleIds);
		
			ThemeDisplay themeDisplay = 
					(ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			long groupId = themeDisplay.getScopeGroupId();
			
			articleWithVersion = AnnouncerTools.getArticleIdsWithVersion(groupId, articleIds);
			//Check if the articles version is still the same
			updatedArticleVersions = 
					!articleWithVersion.equals(articleWithVersionPref);
			if (updatedArticleIds || updatedArticleVersions) {
			    articleConsecutive = 
			    		String.valueOf((int)(Double.valueOf(articleConsecutive) + 1));
			}
		}
		preferences.setValue("articlesRaw", articles);
		preferences.setValue("articleId", articleIds);
		preferences.setValue("articleIdWithVersion", articleWithVersion);
		preferences.setValue("articleIdConsecutive", articleConsecutive);
		preferences.store();
    }
    
    @Override
	public void serveResource(
			ResourceRequest request, ResourceResponse response) 
		throws IOException,PortletException {
		
    	ThemeDisplay themeDisplay = 
    			(ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    	String layoutPK = String.valueOf(themeDisplay.getLayout().getPrimaryKey());
    	
    	String action = ParamUtil.getString(request, "cmd");
		if (action.equals("NOTCOMPLETED")) {
			String userId = ParamUtil.getString(request, "userId");
			Date currentDate = new Date();
			
			AnnouncerTools.addToNotCompleted(userId,layoutPK,currentDate);
		}
		else if(action.equals("COMPLETED")){
			String userId = ParamUtil.getString(request, "userId");
			String articleSerial = ParamUtil.getString(request, "articleSerial");
			
			AnnouncerTools.addToCompleted(userId,layoutPK,articleSerial);
		}
		
		super.serveResource(request, response);
	}
}
