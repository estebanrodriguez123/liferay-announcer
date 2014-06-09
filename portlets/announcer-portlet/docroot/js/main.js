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

AUI.add('my-announcer', function (A, NAME) { 
    
    function MyAnnouncerClass() {
        this.portletNamespace;
        this.failureMessage = new A.Modal({
            bodyContent: Liferay.Language.get('announcer-request-fail-error'),
            centered: true,
            headerContent: Liferay.Language.get('announcer-title'),
            visible: false,
            zIndex: Liferay.zIndex.TOOLTIP,
            modal: true
        }).render();   
    }

    MyAnnouncerClass.prototype = {
        
        failureMessage: null,    
        
        displayContent: function (groupId, uuid, articleVersionId, url, pns, closeURL,articlesArrayIds) {
            var instance = this,
            	title = Liferay.Language.get('announcer-title'),
            	tipModal = null,
            	currentId = 0,
            	articlesIdLength = articlesArrayIds.length,
            	currentUrl;
            Liferay.Util.openWindow({
                dialog: {
                    align: Liferay.Util.Window.ALIGN_CENTER,
                    cache: false,
                    width: 850,
                    height: 600,
                    bodyContent: Liferay.Language.get('announcer-loading-content'),
                    modal: true,
                },
                id: 'announcer-iframe',
                title: title,
                uri: url + "&" + pns + "articleId=" + articlesArrayIds[currentId]
            }, function (modal) {
                this.portletNamespace = pns;
                tipModal = modal;
                tipModal.addToolbar([
                    {
                        label: Liferay.Language.get('Previous'),
                        id: this.portletNamespace + 'previous-announcer',
                        cssClass: 'previous-announcer',
                        on: {
                            click: function () {
                            	if(currentId > 0) {
                            		currentId--;
                            		currentUrl = url + "&" + pns + "articleId=" + articlesArrayIds[currentId];
                            		tipModal.get('boundingBox').one('iframe').setAttribute('src', currentUrl);
                            	}
                            	if(currentId == 0) {
                            		tipModal.get('boundingBox').one('.previous-announcer').set('disabled','disabled');
                            	}
                            	if(currentId < (articlesIdLength-1)) {
                            		tipModal.get('boundingBox').one('.next-announcer').set('disabled', false);
                            	}
                            }
                        }
                    },
                    {
                        label: Liferay.Language.get('next'),
                        id: this.portletNamespace + 'next-announcer',
                        cssClass: 'next-announcer',
                        on: {
                            click: function () {
                            	if(currentId < (articlesIdLength-1)) {
                            		currentId++;
                            		currentUrl = url + "&" + pns + "articleId=" + articlesArrayIds[currentId];
                            		tipModal.get('boundingBox').one('iframe').setAttribute('src', currentUrl);
                            	}
                            	if(currentId == (articlesIdLength-1)) {
                            		tipModal.get('boundingBox').one('.next-announcer').set('disabled','disabled');
                            		tipModal.get('boundingBox').one('.close-announcer').set('disabled', false);
                            	}
                            	if(currentId > 0) {
                            		tipModal.get('boundingBox').one('.previous-announcer').set('disabled', false);
                            	}
                            }
                        }
                    },
                    {
                        label: Liferay.Language.get('close'),
                        id: this.portletNamespace + 'close-announcer',
                        cssClass: 'close-announcer',
                        on: {
                            click: function () {
                                /*Ajax call to change user preference about displaying the pop up*/
                                A.io.request(closeURL, {
                                    method: 'GET',
                                    data: Liferay.Util.ns(pns, {
                                    	cmd: 'COMPLETED',
                                    	userId: uuid,
                                    	articleSerial: articleVersionId
                                    }),
                                    on: {
                                        failure: function () {
                                            instance.failureMessage.show();
                                        },
                                        success: function() {
                                        	modal.destroy();
                                        }
                                    }
                                });
                            }
                        },
                        primary: true
                    }
                ]);
                if(currentId == 0) {
                	tipModal.get('boundingBox').one('.previous-announcer').set('disabled','disabled');
                }
                if(articlesIdLength == 1) {
                	tipModal.get('boundingBox').one('.next-announcer').set('disabled', 'disabled');
                	tipModal.get('boundingBox').one('.close-announcer').set('disabled',false);
                } else {
                	tipModal.get('boundingBox').one('.close-announcer').set('disabled','disabled');
                }
                
                tipModal.on(
                    'visibleChange', function () {
                    /*Ajax call to change user preference about displaying the pop up*/
                    A.io.request(closeURL, {
                        method: 'GET',
                        data: Liferay.Util.ns(pns,{
                        	cmd: 'NOTCOMPLETED',
                        	userId: uuid
                        }),
                        on: {
                            failure: function () {
                                instance.failureMessage.show();
                            }
                        }
                    });
                    modal.destroy();
                });
            });

            Liferay.provide(
                window,
                'setPortletId', function (id) {
                portletId = id;
            }, ['aui-base', 'aui-dialog', 'aui-dialog-iframe']);

        },

        displayArticles: function (url) {
            var tipModal = null;
            Liferay.Util.openWindow({
                dialog: {
                    align: Liferay.Util.Window.ALIGN_CENTER,
                    cache: false,
                    width: 800,
                    height: 600,
                    bodyContent: Liferay.Language.get('announcer-loading-content'),
                    modal: true
                },
                id: 'articles-iframe',
                title: Liferay.Language.get('articles-select'),
                uri: url
            }, function (modal) {
                tipModal = modal;
                tipModal.on(
                    'visibleChange', function () {
                    window.parent.location = window.location.pathname;
                    modal.destroy();
                });
            });
        },

        handleClick: function (articleId, pns, cb, closeURL) {
            var instance = this;
            A.io.request(closeURL, {
                method: 'GET',
                data:Liferay.Util.ns(pns,{
                	cmd: 'UPDATE-ARTICLE-SELECTION',
                	articleId: articleId
                }),
                on: {
                    failure: function () {
                        cb.checked= (cb.checked)? false : true;
                        instance.failureMessage.show();
                    }
                }
            });
        }
    };
    
    A.MyAnnouncerClass = new MyAnnouncerClass();
    
}, '@VERSION@', {
    "requires": ['aui-dialog', 'aui-io', 'aui-modal']
});

AUI().use('my-announcer', function(A) {
    window.MyAnnouncerClass = A.MyAnnouncerClass;
});