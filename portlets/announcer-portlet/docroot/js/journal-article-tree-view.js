AUI.add('rl-journal-tree-view', function (A) {
	
	A.namespace('Rivet');
	var NS = 'namespace',
		GROUP_ID = 'groupId',
		RESOURCE_URL = 'resourceUrl',
		TREE_BOX = 'treeBox',
		TREE = 'Tree',
		ROOT_FOLDER_ID = 'rootFolderId',
		ROOT_FOLDER_LABEL = 'rootFolderLabel',
		BOUNDING_BOX = 'boundingBox',
		NODE_SELECTOR = '.tree-node',
		NODE_ATTR_IS_FOLDER = 'isFolder',
		NODE_ATTR_IS_CHECKED = 'checked',
		NODE_ATTR_FULL_LOADED = 'fullLoaded',
		IS_CHECKED = 'IS_CHECKED',
		CLICK_STR = 'click',
		CMD_UPDATE_ARTICLE = 'UPDATE-ARTICLE-SELECTION',
		SERVICE_URL_GET_CHILDREN = '/journalfolder/get-folders',
		SERVICE_URL_GET_ARTICLES = '/journalarticle/get-articles';
	 
    A.Rivet.JournalTreeView = A.Base.create('rl-journal-tree-view',A.Base, [], {

    	ns : null,
    	groupId: null,
    	contentTree: null,
    	contentRoot : null,

        initializer: function () {
        
        	this.ns = this.get(NS);
        	this.groupId = this.get(GROUP_ID);
        	this.resourceUrl = this.get(RESOURCE_URL);
        	var instance = this;
        	var boundingBoxId = this.ns + this.get(TREE_BOX);
        	var folderId = this.get(ROOT_FOLDER_ID);
        	var folderLabel = this.get(ROOT_FOLDER_LABEL);
        	
        	A.one('#'+this.ns+ TREE).append('<div id="'+boundingBoxId+'"></div>');
        	
        	this.contentTree = new A.TreeView(
        		      {
        		        boundingBox: '#'+boundingBoxId,
        		        children: [
        		        	{
        		        		id: folderId,
        		        		label: Liferay.Language.get(folderLabel),
        		        		alwaysShowHitArea: true,
        		        		leaf:false,
        		        		expanded: false
        		        	}
        		       	]
        		      }
        		    ).render();
        	
        	this.contentRoot = this.contentTree.getNodeById(folderId);
        	this.contentRoot.set(NODE_ATTR_IS_FOLDER, true);
        	this.contentRoot.set(NODE_ATTR_FULL_LOADED, false);
        	
        	// Adding this event on this way because the click event seems on creations seems to be on tree level
        	var boundingBox = this.contentTree.get(BOUNDING_BOX);        	
        	boundingBox.delegate(CLICK_STR, A.bind(instance._clickRivetHandler,this), NODE_SELECTOR);
        	A.one('#'+this.ns+TREE + ' ' + NODE_SELECTOR).simulate(CLICK_STR);
        },
        
        addContentFolder: function(newNodeConfig, parentNode){
        	
        	this._addContentNode(newNodeConfig, parentNode, true, false);
        },
        
        addContentEntry: function(newNodeConfig, parentNode){
        	
        	this._addContentNode(newNodeConfig, parentNode, false, true);
        },
        
        _clickRivetHandler: function(event){
        	var id = event.currentTarget.attr('id');
        	event.stopPropagation();

        	var treeNode = this.contentTree.getNodeById(id);
        	if (treeNode) {
        		if (!(this._isFullLoaded(treeNode))){
        			this._getChildren(treeNode, this);
        		}
        		else{
        			//if is loading children, it will be expanded anyway
        			treeNode.toggle();
        		}
        		if(treeNode.isLeaf()){
        			this._clickLeafHandle(id);	
        		}
            }
        },
        _clickLeafHandle : function(articleId){
        	A.io.request(this.resourceUrl, {method: 'GET',
        		data:Liferay.Util.ns(this.ns,{
        			cmd: CMD_UPDATE_ARTICLE,
        			articleId: articleId
        		})
             });
        }
        ,
       _addContentNode: function(newNodeConfig, parentNode, isFolder, fullLoaded){       	  
        	var newNode = this.contentRoot.createNode(
			  {
			    id: newNodeConfig.id,
			    label: newNodeConfig.label,
			    draggable: true,
        		alwaysShowHitArea: true,
			    leaf:!isFolder,
			    type: newNodeConfig.type,
        		expanded: false
			  }
			);
        	
        	newNode.set(NODE_ATTR_IS_FOLDER, isFolder);
        	newNode.set(NODE_ATTR_IS_CHECKED, newNodeConfig.isChecked);
        	newNode.set(NODE_ATTR_FULL_LOADED, fullLoaded);
        	
        	var forceBindUI = true;
        	if (parentNode === undefined){
        		parentNode = this.contentRoot;
        		forceBindUI = false;
        	}
        	      	
        	parentNode.appendChild(newNode);
        	
        	if (forceBindUI){
        		this.contentTree.bindUI();
        	}
        },
        
        _getChildren: function(treeNode, instance) {
        	treeNode.collapse();
        	// Get folders children of this folder
        	Liferay.Service(SERVICE_URL_GET_CHILDREN,
           			{
           				groupId: instance.groupId,
           				parentFolderId: treeNode.get('id')
           			},
           			function(folders) {
           				A.each(folders, function(item, index, collection){    

           					instance.addContentFolder({
           						id : item.folderId.toString(),
           						label: item.name,
           						type: 'file'
           					},treeNode);
           				});
           			}
           		);
        	
        	// Get entries children of this folder
        	Liferay.Service(SERVICE_URL_GET_ARTICLES,
        			{
        				groupId: instance.groupId,
        				folderId: treeNode.get('id')
        			},
        			function(entries) {
        				
        				A.each(entries, function(item, index, collection){
        					
        					var isChecked = instance._isChecked(item.articleId.toString());
        					instance.addContentEntry({
        						id : item.articleId.toString(),
        						label: item.titleCurrentValue,
        						isChecked: isChecked,
        						type: 'check'
        					},treeNode);
        				});
        			}
        	);
        	   
        	treeNode.set(NODE_ATTR_FULL_LOADED, true);
        	treeNode.expand();
        },
        _isChecked : function(articleId){
        	var result = false;
        	A.io.request(this.resourceUrl, {
        		method: 'GET', 
        		sync: true,
        		dataType: 'json',
        		data: Liferay.Util.ns(this.ns,{
        			cmd: IS_CHECKED,
        			articleId: articleId
        		}),
        		on: {
                     success: function (e) {
                         var data = this.get('responseData');
                         result = data[IS_CHECKED];
                     }
                 }
             });
        	return result;
        }, 
        _isFolder: function(treeNode){
        	result = false;
        	if (treeNode){
        		result = treeNode.get(NODE_ATTR_IS_FOLDER);
        	}
        	return result;
        },
        
        _isFullLoaded: function(treeNode){
        	result = false;
        	if (treeNode){
        		result = treeNode.get(NODE_ATTR_FULL_LOADED);
        	}
        	return result;
        }
    
    }, {
        ATTRS: {

        	namespace:{
        		value: null
        	},
        	resourceUrl:{
        		value: null
        	},
        	treeBox:{
        		value: null
        	},
        	groupId:{
        		value: null
        	},
            rootFolderId: {
                value: null
            },
            rootFolderLabel:{
            	value: null
            }
        }
    });
 
}, '1.0.0', {
    requires: ['aui-tree-view','json','liferay-portlet-url', 'node-event-simulate']
});