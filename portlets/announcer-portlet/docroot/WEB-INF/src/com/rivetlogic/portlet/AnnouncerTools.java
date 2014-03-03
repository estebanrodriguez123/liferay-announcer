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
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.rivetlogic.portlet.model.Completed;
import com.rivetlogic.portlet.model.NotCompleted;
import com.rivetlogic.portlet.service.CompletedLocalServiceUtil;
import com.rivetlogic.portlet.service.NotCompletedLocalServiceUtil;
import com.rivetlogic.portlet.service.persistence.CompletedPK;
import com.rivetlogic.portlet.service.persistence.NotCompletedPK;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

import java.io.IOException;
import java.util.Date;

public class AnnouncerTools {
	private static Log LOG = 
			LogFactoryUtil.getLog(AnnouncerTools.class);
	private static final long MILLIS_PER_DAY=86400000;
	private static CompletedPK userLayoutPKC = 
			new CompletedPK();
	private static NotCompletedPK userLayoutPKNC = 
			new NotCompletedPK();
	
	private static boolean isCompleted(String userId, String layoutPK) 
			throws SystemException{
		if(CompletedLocalServiceUtil.userExist(userId,layoutPK)){
			return true;
		}
		
		return false;
	}
	
	private static boolean isNotCompleted(String userId, String layoutPK) 
			throws SystemException{
		if(NotCompletedLocalServiceUtil.userExist(userId,layoutPK)){
			return true;
		}

		return false;
	}
	
	private static boolean isSameArticlesSetId(String userId, String layoutPK, String articlesSetId){
		try {
			userLayoutPKC.setLAYOUT_PK(layoutPK);
			userLayoutPKC.setUSER_ID(userId);
			
			Completed user = 
					CompletedLocalServiceUtil.getCompleted(userLayoutPKC);
			
			if(!user.getARTICLES_SET_ID().equals(articlesSetId)){
				return false;
			}
		} catch (PortalException e) {
			LOG.error(e);
		} catch (SystemException e) {
			LOG.error(e);
		}
		
		return true;
	}
	
	private static boolean showNotCompleted(String userId, String layoutPK){
		Date currentDate = null;
		Date notCompletedDate = null;
		
		userLayoutPKNC.setLAYOUT_PK(layoutPK);
		userLayoutPKNC.setUSER_ID(userId);
				
		try {
			NotCompleted user = 
					NotCompletedLocalServiceUtil.getNotCompleted(userLayoutPKNC);
			notCompletedDate = user.getPANEL_CLOSE_DATE();
			
			currentDate = new Date();
			long diff = currentDate.getTime() - notCompletedDate.getTime();
			
			if(diff > MILLIS_PER_DAY){
				return true;
			}
		} catch (PortalException e) {
			LOG.error(e);
		} catch (SystemException e) {
			LOG.error(e);
		} 
		
		return false;
	}
	
	/*
	 * This method decides if it's necessary to show the  portlet
	 *  will not be shown if: userId is in Completed table, userId is 
	 * in NonCompleted table but it has been no longer than a day, since it was 
	 * last shown to the user
	 */
	public static boolean showAnnouncer(String userId, String layoutPK, String articlesSetId) 
			throws SystemException{
		if(isCompleted(userId,layoutPK)){
			if(isSameArticlesSetId(userId,layoutPK,articlesSetId)){
				return false;
			}else{
				return true;
			}
		}
		else{
			if(isNotCompleted(userId,layoutPK)){
				if(showNotCompleted(userId,layoutPK)){
					return true;
				}
				else{
					return false;
				}
			}
			else{			//userId is not on Completed or NotCompleted tables
				return true;
			}
		}
	}
	
	public static void addToCompleted(String userId, String layoutPK, String articlesSetId){
		userLayoutPKC.setUSER_ID(userId);
		userLayoutPKC.setLAYOUT_PK(layoutPK);
		
		Completed newCompleted = 
				CompletedLocalServiceUtil.createCompleted(userLayoutPKC);
		newCompleted.setARTICLES_SET_ID(articlesSetId);
		
		try {
			if(isCompleted(userId,layoutPK)){		//Delete to update new completed
				deleteCompleted(userId,layoutPK);
			}
			CompletedLocalServiceUtil.addCompleted(newCompleted);
			
			if(isNotCompleted(userId,layoutPK)){	//If userId exist on NotCompleted table, delete it
				deleteNotCompleted(userId,layoutPK);
			}
		} catch (SystemException e) {
			LOG.error(e);
		}
	}
	
	public static void addToNotCompleted(String userId, String layoutPK, Date panelCloseDate){
		userLayoutPKNC.setUSER_ID(userId);
		userLayoutPKNC.setLAYOUT_PK(layoutPK);
		
		NotCompleted newNotCompleted = 
				NotCompletedLocalServiceUtil.createNotCompleted(userLayoutPKNC);
		newNotCompleted.setPANEL_CLOSE_DATE(panelCloseDate);

		try {
			if(isNotCompleted(userId,layoutPK)){	//Delete to update new not completed
				deleteNotCompleted(userId,layoutPK);
			}
			NotCompletedLocalServiceUtil.addNotCompleted(newNotCompleted);
			
			if(isCompleted(userId,layoutPK)){		//If userId exist on Completed table, delete it
				deleteCompleted(userId,layoutPK);	//Can happen if article set id has change
			}										//and user does not complete the new offer
		} catch (SystemException e) {
			LOG.error(e);
		}
	}
	
	private static void deleteCompleted(String userId, String layoutPK){
		userLayoutPKC.setUSER_ID(userId);
		userLayoutPKC.setLAYOUT_PK(layoutPK);
		
		try {
			CompletedLocalServiceUtil.deleteCompleted(userLayoutPKC);
		} catch (PortalException e) {
			LOG.error(e);
		} catch (SystemException e) {
			LOG.error(e);
		}
	}
	
	private static void deleteNotCompleted(String userId, String layoutPK){
		userLayoutPKNC.setUSER_ID(userId);
		userLayoutPKNC.setLAYOUT_PK(layoutPK);
		
		try {
			NotCompletedLocalServiceUtil.deleteNotCompleted(userLayoutPKNC);
		} catch (PortalException e) {
			LOG.error(e);
		} catch (SystemException e) {
			LOG.error(e);
		}
	}
	
	public static void removeArticle(PortletPreferences pref, ThemeDisplay themeDisplay, String articleId){
		String articles= pref.getValue("articlesRaw", "0");
		System.out.println(articles);
		
        articles = articles.replace(String.valueOf(articleId), "");  //remove id
        articles = articles.replaceAll("\\s+"," ");
        
        String articleIds = articles.trim().replaceAll("\\s", ",");
        System.out.println(articleIds);
        
        String articleWithVersionPref = "0";
		String articleConsecutive = "0";
		String articleWithVersion = "0";
		
		articleWithVersionPref = 
				pref.getValue("articleIdWithVersion", "0"); //articleId1:version ...
		articleConsecutive = 
				pref.getValue("articleIdConsecutive", "0");
			
		boolean updatedArticleIds = false, updatedArticleVersions = false;
		
		updatedArticleIds = 
				!pref.getValue("articleId", "0").equals(articleIds);

		long groupId = themeDisplay.getScopeGroupId();
			
		articleWithVersion = getArticleIdsWithVersion(groupId, articleIds);
		//Check if the articles version is still the same
		updatedArticleVersions = 
				!articleWithVersion.equals(articleWithVersionPref);
		if (updatedArticleIds || updatedArticleVersions) {
			articleConsecutive = 
			    	String.valueOf((int)(Double.valueOf(articleConsecutive) + 1));
		}

		try {
			pref.setValue("articlesRaw", articles);
			pref.setValue("articleId", articleIds);
			pref.setValue("articleIdWithVersion", articleWithVersion);
			pref.setValue("articleIdConsecutive", articleConsecutive);
			pref.store();
		} catch (ReadOnlyException e) {
			LOG.error(e);
		} catch (ValidatorException e) {
			LOG.error(e);
		} catch (IOException e) {
			LOG.error(e);
		}
	}
	
	/**
     * Gets a string in the form of articleId1:version,articleId2:version ...
     */
    public static String getArticleIdsWithVersion(long groupId, String articleIds) {
		StringBuilder articleWithVersionBuilder = new StringBuilder();
		for (String articleId : articleIds.split(",")) { //StringUtil.split
		    double version;
			try {
				version = JournalArticleLocalServiceUtil.getLatestVersion(groupId, articleId);
				articleWithVersionBuilder.append(",");
			    articleWithVersionBuilder.append(articleId);
			    articleWithVersionBuilder.append(":");
			    articleWithVersionBuilder.append(version);
			} catch (PortalException e) {
				LOG.error(e);
			} catch (SystemException e) {
				LOG.error(e);
			}
		    
		}
		articleWithVersionBuilder.deleteCharAt(0); // remove the initial comma
		return articleWithVersionBuilder.toString();
    }
}
