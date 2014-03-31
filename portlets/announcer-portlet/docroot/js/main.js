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
            bodyContent: 'Request failed, please try again.',
            centered: true,
            headerContent: 'Announcer',
            visible: false,
            zIndex: Liferay.zIndex.TOOLTIP,
            modal: true
        }).render();   
    }

    MyAnnouncerClass.prototype = {
        
        failureMessage: null,    
        
        displayContent: function (uuid, articleVersionId, url, pns) {
            var instance = this;
            var title = "Announcer";
            var tipModal = null;
            var portletId = null;

            Liferay.Util.openWindow({
                dialog: {
                    align: Liferay.Util.Window.ALIGN_CENTER,
                    cache: false,
                    width: 850,
                    height: 600,
                    bodyContent: 'Loading content...',
                    modal: true,
                },
                id: 'announcer-iframe',
                title: title,
                uri: url
            }, function (modal) {
                this.portletNamespace = pns;
                tipModal = modal;
                tipModal.addToolbar([
                    {
                        label: 'Close',
                        id: this.portletNamespace + 'close-announcer',
                        cssClass: 'close-announcer',
                        on: {
                            click: function () {
                                /*Ajax call to change user preference about displaying the pop up*/
                                var resourceURL = Liferay.PortletURL.createResourceURL();
                                resourceURL.setPortletId(portletId);
                                resourceURL.setParameter('cmd', 'COMPLETED');
                                resourceURL.setParameter('userId', uuid);
                                resourceURL.setParameter('articleSerial', articleVersionId);
                                A.io(resourceURL.toString(), {
                                    method: 'POST',
                                    on: {
                                        failure: function () {
                                            instance.failureMessage.show();
                                        }
                                    }
                                });
                                modal.destroy();
                            }
                        },
                        primary: true
                    }
                ]);
                tipModal.on(
                    'visibleChange', function () {
                    /*Ajax call to change user preference about displaying the pop up*/
                    var resourceURL = Liferay.PortletURL.createResourceURL();
                    resourceURL.setPortletId(portletId);
                    resourceURL.setParameter('cmd', 'NOTCOMPLETED');
                    resourceURL.setParameter('userId', uuid);
                    A.io(resourceURL.toString(), {
                        method: 'POST',
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
            var title = "Select articles";
            Liferay.Util.openWindow({
                dialog: {
                    align: Liferay.Util.Window.ALIGN_CENTER,
                    cache: false,
                    width: 800,
                    height: 600,
                    bodyContent: 'Loading content...',
                    modal: true
                },
                id: 'articles-iframe',
                title: title,
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

        showAnnouncerCloseBtn: function (pns) {
            this.portletNamespace = pns;
            var nodeObject = A.one('#' + this.portletNamespace + 'close-announcer');
            nodeObject.setStyle('visibility', 'visible');
        },

        handleClick: function (articleId, portletId, cb) {
            var instance = this;
            var articleSelectionURL = Liferay.PortletURL.createResourceURL();
            articleSelectionURL.setPortletId(portletId);
            articleSelectionURL.setParameter('cmd', 'UPDATE-ARTICLE-SELECTION');
            articleSelectionURL.setParameter('articleId', articleId);
            A.io(articleSelectionURL.toString(), {
                method: 'POST',
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
})