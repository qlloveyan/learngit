$(function() {
	
	initElementPermissions();
	loadMenuTree();
	loadData();
	init();
	//查询
	$("#searchMenu").click(function(){
		searchMenu();
	})
	
});

function loadMenuTree(){
	
	$('#menuTree').tree({   
         checkbox: false,   
         animate:true,//是否支持动画
         dnd:false,//是否支持拖放
         url: 'system/funcModule/listModuleTree.do',   
         onBeforeExpand:function(node,param){
             $('#menuTree').tree('options').url = "system/funcModule/listModuleTree.do";                  
         },               
         onClick:function(node){     
             if(node.attributes.isLeaf){
	     		var title = node.text;
	     		var id = node.id;
	     		var parentId = node.attributes.parentId;
	     		loadMenu(id);
	     		initMenuDg(id);
             }
         },
         onLoadSuccess: function(node, data){
        	 var node = $('#menuTree').tree('find','-1');
        	 $('#menuTree').tree('select',node.target);
         },
         onContextMenu: function(e,node){ 
         	
            e.preventDefault();  
            $(this).tree('select',node.target);  
            $('#mm').menu('show',{  
                left: e.pageX,  
                top: e.pageY  
            });
            
         } 
     });   
}


/**
 * 初始化加载数据,分页
 */
function loadData(){
	//grid数据加载
	$('#menuGrid').datagrid({
		title:'下属菜单信息',
		fit:true,
		fitColumns:true,
		collapsible:false,
		nowrap: true,
		singleSelect:false,  
		autoRowHeight: false,
		striped: true,
		url:'system/funcModule/getPageModel.do',
		queryParams:{parentId:'-1',funcType:'0'},
		sortName: 'id',
		sortOrder: 'desc',
		remoteSort: true,
		idField:'id',
		//frozenColumns:[[
           // {field:'ck',checkbox:true}
		//]],
		columns:[[
			{field:'funcName',title:'菜单名称',width:60,align:'center',sortable:true},
			{field:'funcCode',title:'菜单编码',width:60,align:'center',sortable:true},
			{field:'funcSort',title:'序号',width:40,align:'center',sortable:true},
			{field:'url',title:'菜单URL',width:110,align:'center',sortable:true},
			{field:'opt',title:'操作',width:80,align:'center',
				formatter:function(value,rec,index){
						var edit = '<a id="editMenu" href="javascript:void(0)"  class="editMenu" onclick="editMenuDg(\''+ rec.id + '\')" iconCls="icon-edit"  plain="true"></a>';
						var detail = '<a id="detailMenu" href="javascript:void(0)" class="detailMenu" onclick="detailMenu(\''+ rec.id + '\')" iconCls="icon-edit"  plain="true"></a>';
						var del = '<a id="delMenu" href="javascript:void(0)" class="delMenu" onclick="delMenuDg(\''+ rec.id + '\')" iconCls="icon-edit"  plain="true"></a>';
						return edit+" "+detail + " " + del;
					
				}
			}
		]],
		pagination:true,
		rownumbers:true,
		//toolbar:'#tb-unit',
		onLoadSuccess:function(data){
			
			var flag = true;
			flag = isHasElementPerssion('editMenu');
			$("a#editMenu").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			flag = isHasElementPerssion('detailMenu');
			$("a#detailMenu").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			flag = isHasElementPerssion('delMenu');
			$("a#delMenu").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			/**
			 * 数据加载成功后渲染linkbutton
			 */
			$(".editMenu").linkbutton({
				text:'编辑', 
				title:'编辑',
				plain:true
			});
			$(".detailMenu").linkbutton({
				text:'详细', 
				title:'详细',
				plain:true
			});
			$(".delMenu").linkbutton({
				text:'删除', 
				title:'删除',
				plain:true
			});
		}
	});
	//分页
	var p = $('#menuGrid').datagrid('getPager');
	$(p).pagination({
		pageSize: 30,//每页显示的记录条数，默认为10 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        pageList: [30,50,100],//可以设置每页记录条数的列表  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
		onBeforeRefresh:function(){
		}
	});
}


	
	/**
 * 编辑机房
 * @param id 机房id
 */
function editMenu(id){
	var params = [{param:[{paramName:"id",value:id}]}];
	openWin("editMenuWin","system/redirect/redirectHomePage.do?path=system/menu_edit","编辑菜单信息",560,220,params);
}

/**
 * 查看机房详情
 * @param id 机房id
 */
function detailMenu(id){
	var params = [{param:[{paramName:"id",value:id}]}];
	openWin("detailMenuWin","system/redirect/redirectHomePage.do?path=system/menu_detail","菜单详细信息",580,215,params);
}

//新增菜单
function addMenu(){
	
	var node = $('#menuTree').tree('getSelected');
	var title = "添加菜单（上级模块:" + node.text + "）";
	var params = [{param:[{paramName:"parentId",value:node.id}]}];
	openWin("addMenuWin","system/redirect/redirectHomePage.do?path=system/menu_add",title,560,220,params);
}

//重新加载
function reloadMenu(parentId) {
	$('#menuTree').tree('reload');
	$('#menuGrid').datagrid('reload',{
		parentId:parentId,
		funcType:'0'
	});
} 

//删除菜单
function delMenu(){
	//删除菜单
	var node = $('#menuTree').tree('getSelected');
	
	if(node){
		
		var children = $('#menuTree').tree('getChildren', node.target);
		
		//如果还有孩子节点
		if(children!=''){
			$.messager.show({
						title: '友情提示',
						msg: '请先删除子节点'
					});
			return;
		}
		
		$.messager.confirm('确认!','你确定要删除该用节点吗?',function(r){
			if(r){
				$.post("system/funcModule/delEntity.do",{'id':node.id}, function(result){
					if (result.success){
						$.messager.show({
							title: '友情提示',
							msg: '操作成功'
						});
						reloadMenu(node.id);
					} else {
						$.messager.show({
							title: 'Error',
							msg: result.msg
						});
					}
				},'json');
			}
		});
	}else{
		$.messager.show({
			title: '友情提示',
			msg: '请选择节点'
		});
	}
}

//删除资源
function delMenuDg(id){
		//批量删除
	 $.messager.confirm('提示','确定要删除吗?',function(result){ 
	 	if (result){  
        	 $.post('system/funcModule/delEntity.do',{id:id},function(data){  
                $('#menuGrid').datagrid('reload');   
                $('#menuTree').tree('reload');   
         	 }); 
        }
	 });
}

//编辑菜单
function editMenu(){
	var node = $('#menuTree').tree('getSelected');
	if(node.id==-1 || node.id==-2){
		$.messager.show({
			title: '警告',
			msg: '该节点不能编辑'
		});
		return;
	}
	var title = "修改菜单信息（当前模块:" + node.text + "）";
	var params = [{param:[{paramName:"id",value:node.id}]}];
	openWin("editMenuWin","system/redirect/redirectHomePage.do?path=system/menu_edit",title,560,220,params);
}

//编辑菜单
function editMenuDg(muneid){
	var node = $('#menuTree').tree('getSelected');
	var params = [{param:[{paramName:"id",value:muneid},{paramName:"parentId",value:node.id}]}];
	openWin("editMenuWin","system/redirect/redirectHomePage.do?path=system/menu_edit","修改菜单信息",560,220,params);
}

//收缩
function collapse(){
    var node = $('#menuTree').tree('getSelected');
    $('#menuTree').tree('collapse',node.target);
}

//展开
function expand(){
    var node = $('#menuTree').tree('getSelected');
    $('#menuTree').tree('expand',node.target);
}


/**
	 * 加载单位信息
	 */
function loadMenu(id){
	
	$.getJSON("system/funcModule/getEntity.do",{'id':id}, function(data){
		$('#menuFrom').form('clear');
		$('#menuFrom').form('load',data.result);
	});
	
	//把页面元素设置为不可编辑
	$("#menuFrom").find("input").each(function(){
		$(this).attr("readonly","readonly");
	})
}

//按条件查询支付订单
function initMenuDg(parentId) {
	$('#menuGrid').datagrid('load',{
		parentId:parentId,
		funcType:'0'
	});
}

function init(){
	//把页面元素设置为不可编辑
	$("#menuFrom").find("input").each(function(){
		$(this).attr("readonly","readonly");
	})
}