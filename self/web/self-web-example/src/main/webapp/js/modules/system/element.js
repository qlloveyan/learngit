$(function() {
	
	initElementPermissions();
	loadElementTree();
	loadData();
	init();
	//查询
	$("#searchElement").click(function(){
		searchElement();
	})
	
	
});

function loadElementTree(){
	
	$('#elementTree').tree({   
         checkbox: false,   
         animate:true,//是否支持动画
         dnd:false,//是否支持拖放
         url: 'system/funcModule/listModuleTree.do',   
         onBeforeExpand:function(node,param){
             $('#elementTree').tree('options').url = "system/funcModule/listModuleTree.do";                  
         },               
         onClick:function(node){     
             if(node.attributes.isLeaf){
	     		var title = node.text;
	     		var id = node.id;
	     		var parentId = node.attributes.parentId;
	     		loadElement(id);
	     		loadElementDg(id);
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
	$('#elementGrid').datagrid({
		title:'下属页面元素信息',
		fit:true,
		fitColumns:true,
		collapsible:false,
		nowrap: true,
		singleSelect:false,  
		autoRowHeight: false,
		striped: true,
		url:'system/element/getPageModel.do',
		queryParams:{funcId:'-1'},
		sortName: 'id',
		sortOrder: 'desc',
		remoteSort: true,
		idField:'id',
		columns:[[
			{field:'elementCode',title:'页面元素编码',align:'center',sortable:true},
			{field:'elementName',title:'元素名称',align:'center',sortable:true},
			{field:'funcName',title:'所属菜单',align:'center',sortable:true},
			{field:'opt',title:'操作',align:'center',
				formatter:function(value,rec,index){
						var edit = '<a id="editElement" href="javascript:void(0)"  class="editElement" onclick="editElement(\''+ rec.id + '\')" iconCls="icon-edit"  plain="true"></a>';
						var detail = '<a id="detailElement" href="javascript:void(0)" class="detailElement" onclick="detailElement(\''+ rec.id + '\')" iconCls="icon-edit"  plain="true"></a>';
						var del = '<a id="delElement" href="javascript:void(0)"  class="delElement" onclick="delElement(\''+ rec.id + '\')" iconCls="icon-edit"  plain="true"></a>';
						return edit+" "+detail + " " + del;
					
				}
			}
		]],
		pagination:true,
		rownumbers:true,
		onLoadSuccess:function(data){
			
			var flag = true;
			flag = isHasElementPerssion('editElement');
			$("a#editElement").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			flag = isHasElementPerssion('detailElement');
			$("a#detailElement").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			flag = isHasElementPerssion('delElement');
			$("a#delElement").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			/**
			 * 数据加载成功后渲染linkbutton
			 */
			$(".editElement").linkbutton({
				text:'编辑', 
				title:'编辑',
				plain:true
			});
			$(".detailElement").linkbutton({
				text:'详细', 
				title:'详细',
				plain:true
			});
			$(".delElement").linkbutton({
				text:'删除', 
				title:'删除',
				plain:true
			});
		}
	});
	//分页
	var p = $('#elementGrid').datagrid('getPager');
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
function editElement(id){
	var params = [{param:[{paramName:"id",value:id}]}];
	openWin("editElementWin","system/redirect/redirectHomePage.do?path=system/element_edit","编辑页面元素信息",350,220,params);
}

/**
 * 查看机房详情
 * @param id 机房id
 */
function detailElement(id){
	var params = [{param:[{paramName:"id",value:id}]}];
	openWin("detailElementWin","system/redirect/redirectHomePage.do?path=system/element_detail","页面元素详细信息",350,200,params);
}

//新增资源
function addElement(){
	
	var node = $('#elementTree').tree('getSelected');
	var title = "添加页面元素（当前模块:" + node.text + "）";
	var params = [{param:[{paramName:"funcId",value:node.id}]}];
	openWin("addElementWin","system/redirect/redirectHomePage.do?path=system/element_add",title,350,170,params);
}


//删除资源
function delElement(id){
		//批量删除
	 $.messager.confirm('提示','确定要删除吗?',function(result){ 
	 	if (result){  
        	 $.post('system/element/delEntity.do',{id:id},function(data){  
                $('#elementGrid').datagrid('reload');   
         	 }); 
        }
	 });
        
}
//收缩
function collapse(){
    var node = $('#elementTree').tree('getSelected');
    $('#elementTree').tree('collapse',node.target);
}

//展开
function expand(){
    var node = $('#elementTree').tree('getSelected');
    $('#elementTree').tree('expand',node.target);
}

/**
 * 查询菜单信息
 */
function searchElement(){
	var params = $('#elementGrid').datagrid('options').queryParams; //先取得 datagrid 的查询参数  
    var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象 
    $.each( fields, function(i, field){  
        params[field.name] = field.value; //设置查询参数  
    });   
    $('#elementGrid').datagrid('reload'); //设置好查询参数 reload
}

/**
	 * 加载单位信息
	 */
function loadElement(id){
	$.getJSON("system/funcModule/getEntity.do",{'id':id}, function(data){
		$('#elementFrom').form('load',data.result);
	});
	
	//把页面元素设置为不可编辑
	$("#elementFrom").find("input").each(function(){
		$(this).attr("readonly","readonly");
	})
}

//按条件查询支付订单
function loadElementDg(funcId) {
	$('#elementGrid').datagrid('reload',{
		funcId:funcId
	});
}

//初始化
function init(){
	//把页面元素设置为不可编辑
	$("#elementFrom").find("input").each(function(){
		$(this).attr("readonly","readonly");
	})
}