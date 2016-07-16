/**
 * 机房信息业务
 */

$(function(){
	
	initElementPermissions();
	//加载数据
	loadData();
	//弹出编辑页面
	$("#addRole").click(function(){
		openWin("addRoleWin","system/redirect/redirectHomePage.do?path=system/role_add","新增角色信息",350,260);
	});
	//查询
	$("#search").click(function(){
		searchRole();
	});
	
	$("#selectAllBtn").click(function(){
		var roots = [];
		roots = $('#modulesTree').tree('getRoots');
		for (var i = 0; i < roots.length; i++) {
			var childrenNode =  $('#modulesTree').tree('getChildren',roots[i].target);
			for ( var j = 0; j < childrenNode.length; j++) {
				$('#modulesTree').tree("check",childrenNode[j].target);
			}
		}
	});
	//重置条件
	$("#resetRole").click(function(){
		$.messager.progress({
			text:"权限加载中..."
		});
		$('#modulesTree').tree('reload');
	});
	
	
	$('#modulesTree').tree({
		lines:true,
		animate:true,
		checkbox:true,
	    onClick:function(node){
	    
	    	var isRoot =  $('#modulesTree').tree('getChildren', node.target);
			//if(isRoot==''){
				var funcId = node.id;
				var roleId = $("#roleId").val();
				$("#funcId").val(funcId);
				$("#btnListPanel").css("display","block");
				$.messager.progress({
					text:"操作按钮列表数据加载中..."
				});
				$('#btnsTree').tree({
				    url:'system/element/getElementByRoleIdAndFuncId.do?funcId=' + funcId +'&roleId='+roleId,
				    checkbox:true,
				    animate:true,
				    lines:true,
				    onLoadSuccess : function(node) {
						$.messager.progress('close');
					}
				});
			//}else {
			///}
	    },
	    onBeforeLoad:function(){
//	    	$.messager.progress({
//				text:"权限加载中..."
//			});
	    },
	    onLoadSuccess:function(){
	    	$.messager.progress('close');
	    }
	});
	
	//批量删除
	$("#delRole").click(function(){
		 var rows = $('#roleGrid').datagrid('getSelections'); 
         var ps = "";  
         $.each(rows,function(i,n){  
             if(i==0)   
                 ps += "?ids="+n.id;  
             else  
                 ps += "&ids="+n.id;  
         });  
         if(ps==""){
         	 $.messager.alert('提示',"请选择需要删除的数据！");  
         }else{
        	 $.messager.confirm('提示','确定要删除吗?',function(result){  
 	            if (result){  
 	            	$.ajax({
 	            		url: 'system/role/delBatchEntity.do'+ps,
 	            		type: 'post',
 	            		beforeSend: function(){
 	            			var flag = false;
 	            			$.ajax({
 	            				url: 'system/role/isRoleIdsUsed.do'+ps,
 	            				type: 'post',
 	            				async: false, // 同步请求
 	            				success: function(data){
 	            					var res = $.parseJSON(data);
 	            					if(res.result)
 	            						flag=false;
 	            					else
 	            						flag=true;
 	            				}
 	            			});
 	            			if(!flag){
 	            				$.messager.alert('信息提示', '正在使用的角色无法删除!');
 	            			}
 	            			return flag;
 	            		},
 	            		success: function(data){
 	            			  $('#roleGrid').datagrid('reload'); 
 	            		}
 	            	});
 	            }  
        	 });  
         }
	});
})

/**
 * 初始化加载数据,分页
 */
function loadData(){
	//grid数据加载
	$('#roleGrid').datagrid({
		fit:true,
		fitColumns:true,
		collapsible:false,
		nowrap: true,
		singleSelect:false,  
		autoRowHeight: false,
		striped: true,
		url:'system/role/getPageModel.do',
		sortName: 'id',
		sortOrder: 'desc',
		remoteSort: true,
		idField:'id',
		checkOnSelect:true,
		frozenColumns:[[
            {field:'ck',checkbox:true}
		]],
		columns:[[
			{field:'roleName',title:'角色名称',width:150,align:'center',sortable:true},
			{field:'description',title:'角色描述',width:150,align:'center'},
			{field:'opt',title:'操作',width:150,align:'center',
				formatter:function(value,rec,index){
						var edit = '<a  id="editRole" href="javascript:void(0)"  class="btn" onclick="editRole(\''+ rec.id + '\')" >编辑</a>';
						var assignModules = '<a id="assignModules" href="javascript:void(0)" class="btn" onclick="assignModulesFn(\''+ rec.id + '\',\'' + rec.roleName + '\')" >权限设置</a>';
						return edit+" "+assignModules;
					
				}
			}
		]],
		pagination:true,
		rownumbers:true,
		toolbar:'#tb',
		onLoadSuccess:function(data){
			
			/*var flag = true;
			flag = isHasElementPerssion('editRole');
			$("a#editRole").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			flag = isHasElementPerssion('assignModules');
			$("a#assignModules").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});*/
			/**
			 * 数据加载成功后渲染linkbutton
			 */
			/*$(".editRole").linkbutton({
				text:'编辑', 
				title:'编辑',
				plain:true
			});
			$(".assignModules").linkbutton({
				text:'权限设置', 
				title:'权限设置',
				plain:true
			});*/
		}
	});
	//分页
	var p = $('#roleGrid').datagrid('getPager');
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
 * 查询机房信息
 */
function searchRole(){
	var params = $('#roleGrid').datagrid('options').queryParams; //先取得 datagrid 的查询参数  
    var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象 
    $.each( fields, function(i, field){  
        params[field.name] = field.value; //设置查询参数  
    });   
    $('#roleGrid').datagrid('reload'); //设置好查询参数 reload
}

/**
 * 编辑机房
 * @param id 机房id
 */
function editRole(id){
	var params = [{param:[{paramName:"id",value:id}]}];
	openWin("editRoleWin","system/redirect/redirectHomePage.do?path=system/role_edit","编辑角色信息",350,260,params);
}

/**
 * 权限分配选择树弹出框
 */
function assignModulesFn(roleId,roleName){
	$(".tree-h1").html("权限设置【"+roleName+"】");
	$("#functionListPanel").css("display","block");
	$("#function-panel").panel(
		{
			title :roleName+":当前权限"
		}
	);
	$('#function-panel').panel("refresh" );
	
	initModulesTree(roleId);
	$("#btnListPanel").css("display","none");
	
}

/**
 * 分配权限之后的数据保存，提交数据
 * @param {} roleId 角色ID
 */
function submitAssignModulesFn(){
	var roleId = $("#roleId").val();
	var selectedNode = $('#modulesTree').tree('getChecked');
	var pnode = null; //保存上一步所选父节点
	var moduleIds = [];
	for(var i=0;i<selectedNode.length;i++){
		
		//if($('#modulesTree').tree('isLeaf', selectedNode[i].target)){
			
			moduleIds.push(selectedNode[i].id); //保存叶子节点
			//pnode = $('#modulesTree').tree('getParent', selectedNode[i].target); //获取当前节点的父节点
			//console.log(pnode);
//			while (pnode!=null) {//添加全部父节点
//				moduleIds.push(pnode.id); //保存叶子节点
//				pnode = $('#modulesTree').tree('getParent', pnode.target); 
//			}
		//}
	}
	
//	console.error(moduleIds.length);
	$.messager.progress({
			text:"权限分配中..."
	});
	$.ajax({
		url: 'system/role/addRoleModule.do',
		type:'POST',
		data: {
			'roleId':roleId, 
			'moduleIds': distinctArray(moduleIds)
		},
		traditional: true,
		success: function(data){
			$.messager.progress('close');
			$.messager.alert('信息提示', '权限分配成功');
		}
	});
}

/**
 * 初始化树形面板
 */
function initModulesTree(roleId){
	$.messager.progress({
		text:"功能模块列表数据加载中..."
	});
	$("#roleId").val(roleId);
	$('#modulesTree').tree({
	    url:'system/funcModule/getRoleModuleByRoleKey.do?roleId='+roleId+"&opflag=role",
	    checkbox:true,
	   // animate:true,
	    onLoadSuccess : function(node) {
//	    	console.log(node);
//			expandAll();
	    	collapseAll();
			$.messager.progress('close');
		}
	});
}

function collapseAll(){
	var node = $('#modulesTree').tree('getSelected');
	if (node) {
		$('#modulesTree').tree('collapseAll', node.target);
	} else {
		$('#modulesTree').tree('collapseAll');
	}
}

function expandAll() {
//	var node = $('#modulesTree').tree('getSelected');
//	if (node) {
//		$('#modulesTree').tree('expandAll', node.target);
//	} else {
//	}
	$('#modulesTree').tree('expandAll');
}
	
function doSearch(){
	var value = $("#roleName").val();
	//按条件查询收单信息
	$('#roleGrid').datagrid('load',{
		roleName:value
	});
}



function submitAssignElementsFn() {
	
	var roleId = $("#roleId").val();
	var funcId = $("#funcId").val();
	var selectedNode = $('#btnsTree').tree('getChecked');
		var elementIds = [];
		for(var i=0;i<selectedNode.length;i++){
			elementIds.push(selectedNode[i].id);
		}
		console.error(elementIds);
		$.messager.progress({
			text:"元素权限分配中..."
		});
		$.ajax({
			url: 'system/role/addRoleElement.do',
			type:'POST',
			data: {
				'roleId':roleId, 
				'funcId':funcId, 
				'elementIds': distinctArray(elementIds)
			},
			traditional: true,
			success: function(data){
				$('#assignBtns').dialog('close'); 
				$.messager.progress('close');
				$.messager.alert('信息提示', '元素权限分配成功');
				$('#btnsTree').tree("reload");
			}
		});
}

function distinctArray(arr){
	var obj={},temp=[];
	for(var i=0;i<arr.length;i++){
		if(!obj[arr[i]]){
			temp.push(arr[i]);
			obj[arr[i]] =true;
		}
	}
	return temp;
}