AUI().use('aui-dialog', 'aui-io', function(A) {
	
	function MyAnnouncerClass() {
		this.portletNamespace;
	}
	
	MyAnnouncerClass.prototype = {
		displayContent: function(uuid, articleVersionId, url, pns) {
			var title = "Announcer";
			var tipModal = null;
			var portletId=null;
			Liferay.Util.openWindow({
				dialog: {
					align: Liferay.Util.Window.ALIGN_CENTER,
					cache: false,
					width:850,
					height: 600,
					bodyContent : 'Loading content...',
					modal: true
				},
		    	id:'announcer-iframe',              
				title : title,
				uri: url
		    	}, 
		    	function(modal) {
		    		this.portletNamespace=pns;
		    		tipModal = modal;
		    		tipModal.addToolbar([
		    		{
		    			label: 'Close',
		    			id: this.portletNamespace+'close-announcer',
		    			cssClass: 'close-announcer',
		    			on: {
		    				click: function() {
		    					/*Ajax call to change user preference about displaying the pop up*/
		    					var resourceURL= Liferay.PortletURL.createResourceURL();
		    					resourceURL.setPortletId(portletId);
		    					resourceURL.setParameter('cmd', 'COMPLETED');
		    					resourceURL.setParameter('userId', uuid);
		    					resourceURL.setParameter('articleSerial', articleVersionId);
		    					A.io(resourceURL.toString(), {
		    						method: 'POST',
		    						on: {
		    							failure: function () {
		    								if (console) { 
		    									console.error('failure on ajax call');
		    								}
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
		    			'visibleChange', 
		            	function() { 
		            		window.parent.closeAnnouncer();
		            	});
		    });
			Liferay.provide(
				window,
				'setPortletId',
				function(id) {
					portletId=id;
				},
				['aui-base','aui-dialog','aui-dialog-iframe']
			);
			Liferay.provide(
				window,
				'closeAnnouncer',
				function() {
					/*Ajax call to change user preference about displaying the pop up*/
					var resourceURL= Liferay.PortletURL.createResourceURL();
					resourceURL.setPortletId(portletId);
					resourceURL.setParameter('cmd', 'NOTCOMPLETED');
					resourceURL.setParameter('userId', uuid);
					A.io(resourceURL.toString(), {
						method: 'POST',
						on: {
							failure: function () {
								if (console) { 
									console.error('failure on ajax call');
								}
							}
						}
					});
				},
				['aui-base','aui-dialog','aui-dialog-iframe','liferay-portlet-url','aui-io-plugin']
			); 
		},
		displayArticles: function(url) {
			var tipModal = null;
			var title = "Select articles";
			Liferay.Util.openWindow({
			    dialog: {
			        align: Liferay.Util.Window.ALIGN_CENTER,
			        cache: false,
			        width:800,
					height: 600,
					bodyContent : 'Loading content...',
			        modal: true
			    },
			    id:'articles-iframe',              
			    title : title,
			    uri: url
			    }, 
			    function(modal) {
			        tipModal = modal;
			        tipModal.on(
			            'visibleChange', 
			            function() { 
			            	window.parent.location = window.location.pathname;
			            	modal.destroy();
			            });
			    });
		},
		showAnnouncerCloseBtn: function(pns) {
			this.portletNamespace=pns;
			var nodeObject = A.one('#'+this.portletNamespace+'close-announcer');
		    nodeObject.setStyle('visibility','visible');
		},
		handleClick: function(articleId,cb,pns) {
			this.portletNamespace=pns;
			var node = A.one('#'+this.portletNamespace+'selectedIds');
			var currentValue = node.val();
			var newValue = "";
			if(cb.checked){	
				newValue = currentValue + " " + articleId;
			}
			else{
				newValue = currentValue.replace(articleId,"");
			}
			node.val(newValue);
		}
	};
	window.MyAnnouncerClass = new MyAnnouncerClass();
});
