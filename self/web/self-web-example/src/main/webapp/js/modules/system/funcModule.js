$(function() {
	
	initElementPermissions();
	loadFuncModuleTree();
	loadData();
	init();
	//查询
	$("#searchFuncModule").click(function(){
		searchFuncModule();
	})
	
	
});

function loadFuncModuleTree(){
	
	$('#funcModuleTree').tree({   
         checkbox: false,   
         animate:true,//是否支持动画
         dnd:false,//是否支持拖放
         url: 'system/funcModule/listModuleTree.do',   
         onBeforeExpand:function(node,param){
             $('#funcModuleTree').tree('options').url = "system/funcModule/listModuleTree.do";                  
         },               
         onClick:function(node){     
             if(node.attributes.isLeaf){
	     		var title = node.text;
	     		var id = node.id;
	     		var parentId = node.attributes.parentId;
	     		loadFuncModule(id);
	     		loadFuncModuleDg(id);
             }
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
	$('#funcModuleGrid').datagrid({
		title:'下属资源信息',
		fit:true,
		fitColumns:true,
		collapsible:false,
		nowrap: true,
		singleSelect:false,  
		autoRowHeight: false,
		striped: true,
		url:'system/funcModule/getPageModel.do',
		queryParams:{parentId:'-1',funcType:'1'},
		sortName: 'id',
		sortOrder: 'desc',
		remoteSort: true,
		idField:'id',
		//frozenColumns:[[
           // {field:'ck',checkbox:true}
		//]],
		columns:[[
			{field:'funcName',title:'资源名称',width:80,align:'center',sortable:true},
			{field:'funcCode',title:'资源编码',width:80,align:'center',sortable:true},
			{field:'url',title:'资源URL前缀',width:110,align:'center',sortable:true},
			{field:'params',title:'授权参数',width:80,align:'center'},
			{field:'parentModuleName',title:'所属菜单',width:80,align:'center',sortable:true},
			{field:'opt',title:'操作',width:120,align:'center',
				formatter:function(value,rec,index){
						var edit = '<a id="editFuncModule" href="javascript:void(0)"  class="editFuncModule" onclick="editFuncModule(\''+ rec.id + '\')" iconCls="icon-edit"  plain="true"></a>';
						var detail = '<a id="detailFuncModule" href="javascript:void(0)" class="detailFuncModule" onclick="detailFuncModule(\''+ rec.id + '\')" iconCls="icon-edit"  plain="true"></a>';
						var del = '<a id="delFuncModule" href="javascript:void(0)"  class="delFuncModule" onclick="delFuncModule(\''+ rec.id + '\')" iconCls="icon-edit"  plain="true"></a>';
						return edit+" "+detail + " " + del;
					
				}
			}
		]],
		pagination:true,
		rownumbers:true,
		//toolbar:'#tb-unit',
		onLoadSuccess:function(data){
			
			var flag = true;
			flag = isHasElementPerssion('editFuncModule');
			$("a#editFuncModule").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			flag = isHasElementPerssion('detailFuncModule');
			$("a#detailFuncModule").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			flag = isHasElementPerssion('delFuncModule');
			$("a#delFuncModule").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			/**
			 * 数据加载成功后渲染linkbutton
			 */
			$(".editFuncModule").linkbutton({
				text:'编辑', 
				title:'编辑',
				plain:true
			});
			$(".detailFuncModule").linkbutton({
				text:'详细', 
				title:'详细',
				plain:true
			});
			$(".delFuncModule").linkbutton({
				text:'删除', 
				title:'删除',
				plain:true
			});
		}
	});
	//分页
	var p = $('#funcModuleGrid').datagrid('getPager');
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
function editFuncModule(id){
	var params = [{param:[{paramName:"id",value:id}]}];
	openWin("editFuncModuleWin","system/redirect/redirectHomePage.do?path=system/funcModule_edit","编辑资源信息",350,300,params);
}

/**
 * 查看机房详情
 * @param id 机房id
 */
function detailFuncModule(id){
	var params = [{param:[{paramName:"id",value:id}]}];
	openWin("detailFuncModuleWin","system/redirect/redirectHomePage.do?path=system/funcModule_detail","资源详细信息",350,260,params);
}

//新增资源
function addFuncModule(){
	
	var node = $('#funcModuleTree').tree('getSelected');
	var title = "添加资源（当前模块:" + node.text + "）";
	var params = [{param:[{paramName:"parentId",value:node.id}]}];
	openWin("addFuncModuleWin","system/redirect/redirectHomePage.do?path=system/funcModule_add",title,350,250,params);
}


//删除资源
function delFuncModule(id){
		//批量删除
	 $.messager.confirm('提示','确定要删除吗?',function(result){ 
	 	if (result){  
        	 $.post('system/funcModule/delEntity.do',{id:id},function(data){  
                $('#funcModuleGrid').datagrid('reload');   
         	 }); 
        }
	 });
        
}
//收缩
function collapse(){
    var node = $('#funcModuleTree').tree('getSelected');
    $('#funcModuleTree').tree('collapse',node.target);
}

//展开
function expand(){
    var node = $('#funcModuleTree').tree('getSelected');
    $('#funcModuleTree').tree('expand',node.target);
}

/**
 * 查询菜单信息
 */
function searchFuncModule(){
	var params = $('#funcModuleGrid').datagrid('options').queryParams; //先取得 datagrid 的查询参数  
    var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象 
    $.each( fields, function(i, field){  
        params[field.name] = field.value; //设置查询参数  
    });   
    $('#funcModuleGrid').datagrid('reload'); //设置好查询参数 reload
}

/**
	 * 加载单位信息
	 */
function loadFuncModule(id){
	$.getJSON("system/funcModule/getEntity.do",{'id':id}, function(data){
		$('#funcModuleFrom').form('load',data.result);
	});
	
	//把页面元素设置为不可编辑
	$("#funcModuleFrom").find("input").each(function(){
		$(this).attr("readonly","readonly");
	})
}

//按条件查询支付订单
function loadFuncModuleDg(parentId) {
	$('#funcModuleGrid').datagrid('reload',{
		parentId:parentId,
		funcType:'1'
	});
}

//初始化
function init(){
	//把页面元素设置为不可编辑
	$("#funcModuleFrom").find("input").each(function(){
		$(this).attr("readonly","readonly");
	})
}