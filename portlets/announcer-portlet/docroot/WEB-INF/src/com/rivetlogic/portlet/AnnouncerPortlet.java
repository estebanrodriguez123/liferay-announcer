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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ValidatorException;
import javax.servlet.http.HttpServletRequest;

/**
 * The Class AnnouncerPortlet.
 */
public class AnnouncerPortlet extends MVCPortlet {
    
    /** The Constant LOG. */
    private static final Log LOG = LogFactoryUtil
            .getLog(AnnouncerPortlet.class);
    
    /** Counter */
    public static BigInteger COUNTER = null;
    /** The Constant ARTICLE_SELECTION_DELIMITER. */
    private static final String ARTICLE_SELECTION_DELIMITER = ",";
    
    /** The Constant LR_EMPTY_VALUE. */
    public static final String LR_EMPTY_VALUE = "0";
    
    /** Constants */
    public static final String JSP_PAGE = "jspPage";
    public static final String EDIT_TEMPLATE = "edit-template";
    public static final String DEFAULT_ARTICLE= "defaultArticle";
    public static final String ARTICLE_ID = "articleId";
    public static final String ADDED_ARTICLE_IDS = "addedArticleIds";
    public static final String ARTICLES_RAW = "articlesRaw";
    public static final String ARTICLE_ID_CONSECUTIVE = "articleIdConsecutive";
    public static final String ARTICLE_ID_WITH_VERSION = "articleIdWithVersion";
    public static final String ARTICLE_VERSION_ID = "articleVersionId";
    public static final String GROUP_ID = "groupId";
    public static final String SHOW_ANNOUNCER = "showAnnouncer";
    public static final String SIGNED_IN = "signedIn";
    public static final String IDS = "ids";
    public static final String ARTICLE_DEAFULT = "article-default";
    public static final String ARTICLE_UP_DOWN = "article-up-down";
    public static final String CMD = "cmd";
    public static final String USER_ID = "userId";
    public static final String COMPLETED = "COMPLETED";
    public static final String NOTCOMPLETED = "NOTCOMPLETED";
    public static final String ARTICLE_RAW = "articleRaw";
    public static final String ARTICLE_SERIAL = "articleSerial";
    public static final String ARTICLES = "articles";
    public static final String UPDATE_ARTICLE_SELECTION = "UPDATE-ARTICLE-SELECTION";
    public static String EDIT_URL = StringPool.BLANK;
    
   @Override
   public void init() throws PortletException {
	   COUNTER  = BigInteger.ZERO;
	   EDIT_URL = getInitParameter(EDIT_TEMPLATE);
	   super.init();
   }
    
    @Override
    public void render(RenderRequest request, RenderResponse response) throws PortletException, IOException{
    	
    	PortletPreferences preferences = request.getPreferences();
        String defaultArticle = preferences.getValue(DEFAULT_ARTICLE, LR_EMPTY_VALUE);
        String[] addedArticleIds = preferences.getValue(ARTICLE_ID, LR_EMPTY_VALUE).split(ARTICLE_SELECTION_DELIMITER);
        request.setAttribute(DEFAULT_ARTICLE, defaultArticle);
        request.setAttribute(ADDED_ARTICLE_IDS, addedArticleIds);
    	super.render(request, response);
    }
    
    @Override
    public void doView(RenderRequest request, RenderResponse response)
            throws IOException, PortletException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);
        long groupId = themeDisplay.getScopeGroupId();

        PortletPreferences preferences = request.getPreferences();
        boolean showAnnouncer = false;
        if (themeDisplay.isSignedIn()) {
            String articleVersionId = preferences.getValue(ARTICLE_ID_CONSECUTIVE, LR_EMPTY_VALUE);
            String articleIds = preferences.getValue(ARTICLE_ID, LR_EMPTY_VALUE);
            if (!articleIds.equals(LR_EMPTY_VALUE)) {
                for (String articleId : articleIds.split(ARTICLE_SELECTION_DELIMITER)) {
                	if(!AnnouncerTools.isArticle(groupId, articleId)) {
                		AnnouncerTools.removeArticle(preferences, themeDisplay, articleId);    
                	}
                	try {
                		String layoutPK = String.valueOf(themeDisplay.getLayout()
                				.getPrimaryKey());
                		showAnnouncer = AnnouncerTools.showAnnouncer(themeDisplay
                				.getRealUser().getUuid(), layoutPK, articleVersionId);
                		request.setAttribute(ARTICLE_VERSION_ID, articleVersionId);
                	}catch (SystemException e) {
                		LOG.error(e);
                	}
                }
            }
        }
        request.setAttribute(GROUP_ID, groupId);
        request.setAttribute(SHOW_ANNOUNCER, showAnnouncer);
        request.setAttribute(SIGNED_IN, themeDisplay.isSignedIn());
        String defaultArticle = preferences.getValue(DEFAULT_ARTICLE, LR_EMPTY_VALUE);
        request.setAttribute(DEFAULT_ARTICLE, defaultArticle);

        super.doView(request, response);
    }

    @Override
    public void doEdit(RenderRequest request, RenderResponse response)
            throws IOException, PortletException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);
        long groupId = themeDisplay.getScopeGroupId();
        request.setAttribute(GROUP_ID, groupId);

        PortletPreferences preferences = request.getPreferences();
        String[] ids = preferences.getValue(ARTICLE_ID, LR_EMPTY_VALUE).split(ARTICLE_SELECTION_DELIMITER);
        String defaultArticle = preferences.getValue(DEFAULT_ARTICLE, LR_EMPTY_VALUE);
        request.setAttribute(DEFAULT_ARTICLE, defaultArticle);
        request.setAttribute(IDS, ids);

        super.doEdit(request, response);
    }

    /**
     * Default article.
     *
     * @param request the request
     * @param response the response
     */
    public void defaultArticle(ActionRequest request, ActionResponse response) {
        String defaultArticle = ParamUtil.getString(request, ARTICLE_ID);
        PortletPreferences preferences = request.getPreferences();
        try {
            preferences.setValue(DEFAULT_ARTICLE, defaultArticle);
            preferences.store();
        } catch (ReadOnlyException e) {
            LOG.error(e);
        } catch (ValidatorException e) {
            LOG.error(e);
        } catch (IOException e) {
            LOG.error(e);
        }
        SessionMessages.add(request, ARTICLE_DEAFULT);
    }

    /**
     * Up article.
     *
     * @param request the request
     * @param response the response
     * @throws PortletException the portlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public void upArticle(ActionRequest request, ActionResponse response)
            throws PortletException, IOException, PortalException,
            SystemException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);
        long groupId = themeDisplay.getScopeGroupId();
        request.setAttribute(GROUP_ID, groupId);

        String articleId = ParamUtil.getString(request, ARTICLE_ID);
        PortletPreferences preferences = request.getPreferences();

        String[] ids = preferences.getValue(ARTICLE_ID, LR_EMPTY_VALUE).split(ARTICLE_SELECTION_DELIMITER);
        List<String> currentArticles = new ArrayList<String>();
        for (int i = 0; i < ids.length; i++) {
            currentArticles.add(ids[i]);
        }

        int index = currentArticles.indexOf(articleId);
        if (index != 0) {
            Collections.swap(currentArticles, index, index - 1);
        }

        String articlesRaw = StringPool.BLANK;
        for (int i = 0; i < currentArticles.size(); i++) {
            articlesRaw += currentArticles.get(i) + StringPool.SPACE;
        }

        updatePreferences(request, response, articlesRaw);
        ids = preferences.getValue(ARTICLE_ID, LR_EMPTY_VALUE).split(ARTICLE_SELECTION_DELIMITER);
        request.setAttribute(IDS, ids);
        
        String defaultArticle = preferences.getValue(DEFAULT_ARTICLE, LR_EMPTY_VALUE);
        request.setAttribute(DEFAULT_ARTICLE, defaultArticle);

        SessionMessages.add(request, ARTICLE_UP_DOWN);

        response.setRenderParameter(JSP_PAGE, EDIT_URL);
    }

    /**
     * Down article.
     *
     * @param request the request
     * @param response the response
     * @throws PortletException the portlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public void downArticle(ActionRequest request, ActionResponse response)
            throws PortletException, IOException, PortalException,
            SystemException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);
        long groupId = themeDisplay.getScopeGroupId();
        request.setAttribute(GROUP_ID, groupId);

        String articleId = ParamUtil.getString(request, ARTICLE_ID);
        PortletPreferences preferences = request.getPreferences();

        String[] ids = preferences.getValue(ARTICLE_ID, LR_EMPTY_VALUE).split(ARTICLE_SELECTION_DELIMITER);
        List<String> currentArticles = new ArrayList<String>();
        for (int i = 0; i < ids.length; i++) {
            currentArticles.add(ids[i]);
        }

        int index = currentArticles.indexOf(articleId);
        if (index != (currentArticles.size() - 1)) {
            Collections.swap(currentArticles, index, index + 1);
        }

        String articlesRaw = StringPool.BLANK;
        for (int i = 0; i < currentArticles.size(); i++) {
            articlesRaw += currentArticles.get(i) + StringPool.SPACE;
        }

        updatePreferences(request, response, articlesRaw);
        ids = preferences.getValue(ARTICLE_ID, LR_EMPTY_VALUE).split(ARTICLE_SELECTION_DELIMITER);
        request.setAttribute(IDS, ids);

        String defaultArticle = preferences.getValue(DEFAULT_ARTICLE, LR_EMPTY_VALUE);
        request.setAttribute(DEFAULT_ARTICLE, defaultArticle);

        SessionMessages.add(request, ARTICLE_UP_DOWN);

        response.setRenderParameter(JSP_PAGE, EDIT_URL);
    }

    /**
     * Save preferences.
     *
     * @param request the request
     * @param response the response
     * @throws PortletException the portlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public void savePreferences(ActionRequest request, ActionResponse response)
            throws PortletException, IOException, PortalException,
            SystemException {

        String articles = ParamUtil.getString(request, ARTICLES);
        updatePreferences(request, response, articles);
    }

    @Override
    public void serveResource(ResourceRequest request, ResourceResponse response)
            throws IOException, PortletException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);
        String layoutPK = String.valueOf(themeDisplay.getLayout()
                .getPrimaryKey());

        String action = ParamUtil.getString(request, CMD);
        
        if (action.equals(NOTCOMPLETED)) {
            String userId = ParamUtil.getString(request, USER_ID);// servletRequest.getParameter(USER_ID);
            Date currentDate = new Date();

            AnnouncerTools.addToNotCompleted(userId, layoutPK, currentDate);
            
        } else if (action.equals(COMPLETED)) {
            String userId = ParamUtil.getString(request, USER_ID);
            String articleSerial = ParamUtil.getString(request, ARTICLE_SERIAL);// servletRequest.getParameter(ARTICLE_SERIAL);

            AnnouncerTools.addToCompleted(userId, layoutPK, articleSerial);
            
        } else if (UPDATE_ARTICLE_SELECTION.equalsIgnoreCase(action)){
        	
        	String articleId = ParamUtil.getString(request, ARTICLE_ID);//servletRequest.getParameter(ARTICLE_ID);
        	updateArticleSelection(request, response, articleId);
        } 

        super.serveResource(request, response);
    }
    
    /**
     * Update article selection.
     *
     * @param request the request
     * @param response the response
     * @param articleId the article id
     * @throws PortletException the portlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void updateArticleSelection(PortletRequest request, PortletResponse response, String articleId)
            throws PortletException, IOException {

        PortletPreferences preferences = request.getPreferences();
        String articlesStr = preferences.getValue(ARTICLE_ID, LR_EMPTY_VALUE);
        String[] articlesSelected; 
        if(LR_EMPTY_VALUE.equalsIgnoreCase(articlesStr)){
        	articlesSelected = new String[1];
        	articlesSelected[0] = StringPool.BLANK;
        } else {
        	articlesSelected = articlesStr.split(ARTICLE_SELECTION_DELIMITER);
        }
        String defaultArticle = preferences.getValue(DEFAULT_ARTICLE, LR_EMPTY_VALUE);
        StringBuilder updatedArticleSelection = new StringBuilder();

    	boolean isDeleted = false;
    	for (int i = 0; i < articlesSelected.length; i++) {
    		 if(articlesSelected[i].equalsIgnoreCase(articleId)){
    			 //Since the articleId already exists, let us remove it. Also check if this is default article
    			 articlesSelected[i] = LR_EMPTY_VALUE;
    			 isDeleted = true;
    			 if (defaultArticle.equalsIgnoreCase(articleId)) {
    				 preferences.setValue(DEFAULT_ARTICLE, LR_EMPTY_VALUE);
    				 preferences.store();
    			 }
    			 break;
    		 }
    	 }
    	 
    	//Now iterate over the array to form the string
    	for (int j = 0; j < articlesSelected.length; j++) {
    		if((articlesSelected[j] != LR_EMPTY_VALUE) && !(StringPool.BLANK.equalsIgnoreCase(articlesSelected[j]))){
    		    updatedArticleSelection.append(articlesSelected[j]);
    			updatedArticleSelection.append(StringPool.SPACE);
    		}
    	}
    	 //If the articleId doesn't exist, then let us add it to selected ids.
    	 if(!isDeleted){
    		 updatedArticleSelection.append(articleId);
    	 }
        updatePreferences(request, response, updatedArticleSelection.toString());
    }
    
    /**
     * Update preferences.
     *
     * @param request the request
     * @param response the response
     * @param articles the articles
     * @throws PortletException the portlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void updatePreferences(PortletRequest request,
            PortletResponse response, String articles) throws PortletException,
            IOException {
        PortletPreferences preferences = request.getPreferences();
        String articleIds = articles.trim().replaceAll("\\s", ARTICLE_SELECTION_DELIMITER);
        if (articleIds.equals(StringPool.BLANK)) {
            articleIds = LR_EMPTY_VALUE;
        }

        String articleWithVersionPref = LR_EMPTY_VALUE;
        String articleConsecutive = preferences.getValue(ARTICLE_ID_CONSECUTIVE, LR_EMPTY_VALUE); 
        String articleWithVersion = LR_EMPTY_VALUE;
        if (!articleIds.equals(LR_EMPTY_VALUE)) {
            articleWithVersionPref = preferences.getValue(
                    ARTICLE_ID_WITH_VERSION, LR_EMPTY_VALUE); // articleId1:version ...
            articleConsecutive = preferences.getValue(ARTICLE_ID_CONSECUTIVE,
                    LR_EMPTY_VALUE);

            boolean updatedArticleIds = false, updatedArticleVersions = false;

            updatedArticleIds = !preferences.getValue(ARTICLE_ID, LR_EMPTY_VALUE).equals(
                    articleIds);

            ThemeDisplay themeDisplay = (ThemeDisplay) request
                    .getAttribute(WebKeys.THEME_DISPLAY);
            long groupId = themeDisplay.getScopeGroupId();

            articleWithVersion = AnnouncerTools.getArticleIdsWithVersion(
                    groupId, articleIds);
            // Check if the articles version is still the same
            updatedArticleVersions = !articleWithVersion
                    .equals(articleWithVersionPref);
            if (updatedArticleIds || updatedArticleVersions) {
            	COUNTER = COUNTER.add(BigInteger.ONE);
                articleConsecutive = COUNTER.toString();
            }
        }
        
        preferences.setValue(ARTICLE_RAW, articles);
        preferences.setValue(ARTICLE_ID, articleIds);
        preferences.setValue(ARTICLE_ID_WITH_VERSION, articleWithVersion);
        preferences.setValue(ARTICLE_ID_CONSECUTIVE, articleConsecutive);
        preferences.store();
    }
}
