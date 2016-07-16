$(function() {
	initElementPermissions();
	loadSysUnitTree();
	loadData();
	init();
	
	//编辑，新增机房信息，或修改机房信息
	$("#editSysUnit").click(function() {
		$("#sysUnitFrom").form('submit',{  
			url:'system/sysUnit/editEntity.do',
			onSubmit:function(){  
				return $(this).form('validate'); 
			},  
			success:function(data){  
				searchSysUnit(); 
				loadSysUnitTree(); 
			}  
		});  
			
	});
	
	//批量删除
	$("#delSysUnit").click(function(){
		 var rows = $('#sysUnitGrid').datagrid('getSelections'); 
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
 	            	 $.post('system/sysUnit/delBatchEntity.do'+ps,function(data){  
 	                    $('#sysUnitGrid').datagrid('reload');  
 	                    loadSysUnitTree(); 
 	                });  
 	            }  
        	 });  
            
         }
		
	});
	
});


//收缩
function collapse(){
    var node = $('#sysUnitTree').tree('getSelected');
    $('#sysUnitTree').tree('collapse',node.target);
}

//展开
function expand(){
    var node = $('#sysUnitTree').tree('getSelected');
    $('#sysUnitTree').tree('expand',node.target);
}


function loadSysUnitTree(){
	
	$('#sysUnitTree').tree({   
         checkbox: false,   
         animate:true,//是否支持动画
         dnd:false,//是否支持拖放
         url: 'system/sysUnit/listAllSysUnit.do',   
         onBeforeExpand:function(node,param){
             $('#sysUnitTree').tree('options').url = "system/sysUnit/listAllSysUnit.do";                  
         },               
         onClick:function(node){     
             if(node.attributes.isLeaf){
	     		var title = node.text;
	     		var id = node.attributes.id;
	     		var parentId = node.attributes.parentId;
	     		loadSysUnit(id);
	     		initSysUnitDg(id);
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
	$('#sysUnitGrid').datagrid({
		title:'下属信息',
		fit:true,
		fitColumns:true,
		collapsible:false,
		nowrap: true,
		singleSelect:false,  
		autoRowHeight: false,
		striped: true,
		url:'system/sysUnit/getPageModel.do',
		queryParams:{parentId:'###'},
		sortName: 'id',
		sortOrder: 'desc',
		remoteSort: true,
		idField:'id',
		columns:[[
			{field:'unitName',title:'单位名称',width:100,align:'center',sortable:true},
			{field:'nativeId',title:'单位标识号',width:100,align:'center'},
			{field:'unitLevel',title:'单位级别',width:100,align:'center'},
			{field:'opt',title:'操作',width:80,align:'left',
				formatter:function(value,rec,index){
					var edit = '<a id="editsysUnit" href="javascript:void(0)"  class="editsysUnit" onclick="editSysUnit(\''+ rec.id + '\',\''+ rec.parentId + '\')" iconCls="icon-edit"  plain="true"></a>';
					var detail = '<a id="detailsysUnit" href="javascript:void(0)" class="detailsysUnit" onclick="detailSysUnit(\''+ rec.id + '\')" iconCls="icon-edit"  plain="true"></a>';
					var del = '<a id="delsysUnit" href="javascript:void(0)" class="delsysUnit" onclick="delSysUnitDg(\''+ rec.id + '\')" iconCls="icon-edit"  plain="true"></a>';
					return edit+" "+detail + " " + del;
				}
			}
		]],
		pagination:true,
		rownumbers:true,
		toolbar:'#tb-unit',
		onLoadSuccess:function(data){
			
			var flag = true;
			flag = isHasElementPerssion('editsysUnit');
			$("a#editsysUnit").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			flag = isHasElementPerssion('detailsysUnit');
			$("a#detailsysUnit").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			flag = isHasElementPerssion('delsysUnit');
			$("a#delsysUnit").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			/**
			 * 数据加载成功后渲染linkbutton
			 */
			$(".editsysUnit").linkbutton({
				text:'编辑', 
				title:'编辑',
				plain:true
			});
			$(".detailsysUnit").linkbutton({
				text:'详细', 
				title:'详细',
				plain:true
			});
			$(".delsysUnit").linkbutton({
				text:'删除', 
				title:'删除',
				plain:true
			});
		}
	});
	//分页
	var p = $('#sysUnitGrid').datagrid('getPager');
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
	 * 加载单位信息
	 */
function loadSysUnit(id){
	$.getJSON("system/sysUnit/getEntity.do",{'id':id}, function(data){
		$('#sysUnitFrom').form('load',data.result);
	});
}

//按条件查询支付订单
function initSysUnitDg(parentId) {
	$('#sysUnitGrid').datagrid('load',{
		parentId:parentId
	});
}
	
/**
 * 查询机房信息
 */
function searchSysUnit(){
	var params = $('#sysUnitGrid').datagrid('options').queryParams; //先取得 datagrid 的查询参数  
    $('#sysUnitGrid').datagrid('reload'); //设置好查询参数 reload
}
	
/**
 * 编辑单位
 * @param id 
 */
function editSysUnit(id,parentId){
	var params = [{param:[{paramName:"id",value:id},{paramName:"parentUnitId",value:parentId}]}];
	openWin("editSysUnitWin","system/redirect/redirectHomePage.do?path=system/sysUnit_edit","编辑单位信息",300,220,params);
}

//单位信息
function editSysUnitTree(){
	
	var node = $('#sysUnitTree').tree('getSelected');
	var pnode =  $('#sysUnitTree').tree('getParent',node.target);
	if(node.attributes.unitLevel=="0"){
		 $.messager.alert('提示',"当前节点不能修改");  
		 return;
	}
	var title = "修改单位信息（当前单位:" + node.text + "）";
	var params = "";
	if(pnode==null){
		params = [{param:[{paramName:"id",value:node.id},{paramName:"parentUnitId",value:node.id}]}];
	}else{
		params = [{param:[{paramName:"id",value:node.id},{paramName:"parentUnitId",value:node.id},{paramName:"unitLevel",value:node.attributes.unitLevel}]}];
	}
	openWin("editSysUnitWin","system/redirect/redirectHomePage.do?path=system/sysUnit_edit",title,300,250,params);
}

/**
 * 查看机房详情
 * @param id 机房id
 */
function detailSysUnit(id){
	var params = [{param:[{paramName:"id",value:id}]}];
	openWin("detailSysUnitWin","system/redirect/redirectHomePage.do?path=system/sysUnit_detail","单位详细信息",300,220,params);
}

//初始化
function init(){
	
	//把页面元素设置为不可编辑
	$("#sysUnitFrom").find("input").each(function(){
		$(this).attr("readonly","readonly");
	})
	
}

//新增单位
function addSysUnit(){
	
	var node = $('#sysUnitTree').tree('getSelected');
	var pnode =  $('#sysUnitTree').tree('getParent',node.target);
	var flag = false;
	var crrentUnitlevel = node.attributes.unitLevel;
	var title = "";
	if(crrentUnitlevel=="0"){
		title = "添加单位（上级分组:" + node.text + "）";
	}else if(crrentUnitlevel=="1"){
		title = "新增单位信息（" + node.text + "）";
	}else if(crrentUnitlevel=="2"){
		 $.messager.alert('提示',"当前节点不能继续添加单位");  
		return;
	}
	var level = parseInt(crrentUnitlevel)+1;
	if(pnode==null){
		var params = [{param:[{paramName:"parentId",value:node.id},{paramName:"unitLevel",value:level}]}];
	}else{
		var params = [{param:[{paramName:"unitText",value:node.text},{paramName:"parentId",value:node.id},{paramName:"unitLevel",value:level}]}];
	}
	openWin("addSysUnitWin","system/redirect/redirectHomePage.do?path=system/sysUnit_add",title,300,220,params);
	
}


//删除单位
function delSysUnit(){
	//删除单位
	var node = $('#sysUnitTree').tree('getSelected');
	//console.log(node);
	$.post("system/sysUnit/checkUserByUnit.do",{'unitid':node.id}, function(result){
		//alert(result.length);
		if (result.length>0){
			$.messager.show({
				title: '友情提示',
				msg: '该单位下存在用户，请先删除单位下的用户再删除该单位'
			});
			return;
		} else {
			if(node.attributes.parentId=="0"){
				$.messager.show({
					title: '友情提示',
					msg: '不能删除该节点'
				});
				return;
			}
			if(node){
				var children = $('#sysUnitTree').tree('getChildren', node.target);
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
						$.post("system/sysUnit/delEntity.do",{'id':node.id}, function(result){
							if (result.success){
								$.messager.show({
									title: '友情提示',
									msg: '操作成功'
								});
								reloadSysUnit();
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
	},'json');
	//system/sysUnit/checkUserByUnit.do
	//
	//console.log(node.attributes.parentId);
	
}
//删除单位
function delSysUnitDg(id){
		//批量删除
	 $.messager.confirm('提示','确定要删除吗?',function(result){ 
	 	if (result){  
        	 $.post('system/sysUnit/delEntity.do',{id:id},function(data){  
                 $('#sysUnitGrid').datagrid('reload');   
                 $('#sysUnitTree').tree('reload');
         	 }); 
        }
	 });
}

//重新加载
function reloadSysUnit(id) {
	loadSysUnitTree();
	$('#sysUnitGrid').datagrid('reload');
} 

