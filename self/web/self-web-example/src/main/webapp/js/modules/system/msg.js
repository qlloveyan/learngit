/**
 * 机房信息业务
 */

$(function(){
	//初始化页面元素
	initElementPermissions();
	//加载数据
	loadData();
	
	
	
	
	
	
	
});

/**
 * 初始化加载数据,分页
 */
function loadData(){
	//grid数据加载
	$('#msgGrid').datagrid({
		title:'',
		fit:true,
		fitColumns:true,
		collapsible:false,
		nowrap: true,
		singleSelect:true,  
		autoRowHeight: false,
		striped: true,
		url:'system/msgRel/getMyMsg.do',
		sortName: 'id',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'id',
		checkOnSelect:false,
//		frozenColumns:[[
//            {field:'ck',checkbox:false}
//		]],
		columns:[[
			{field:'msgType',title:'消息类型',align:'center',width:80,sortable:true,formatter:function(value,rec,index){
				if(rec.msg.msgType=="1"){
					return "业务提醒";
				}else if(rec.msg.msgType=="2"){
					return "站内消息";
				}else{
					return rec.msg.msgType;
				}
			}},
			{field:'businessType',title:'业务类别 ',align:'center',width:80,sortable:true,formatter:function(value,rec,index){
				if(rec.msg.businessType=="1"){
					return "数据导出";
				}else if(rec.msg.businessType=="2"){
					return "消息";
				}else{
					
					return rec.msg.businessType;
				}
			}},
			{field:'msgTitle',title:'消息标题',align:'center',width:200,formatter:function(value,rec,index){
				if(rec.msg.msgType==1){
					var download = '<a id="downloadExcel" '+
						'href="framework/filehandle/fileHandle/downloadtemplate.do?path='+rec.msg.msgUrl+'&filename='+rec.msg.msgTitle+'&msgId='+rec.id+'"'+
						'title="点击下载此文件" style="font-style: italic;cursor: pointer;color: red" onclick="updateState(\''+ rec.id + '\')" >'+rec.msg.msgTitle+'</a>';
					return download;
				}else if(rec.msg.msgType==2){
					//弹出消息
					var detail = '<a href="javascript:void(0)"'+
					'title="查看详情" style="font-style: italic;cursor: pointer;color: red" onclick="detailMsg(\''+ rec.msgId + '\',\''+rec.id+'\')" >'+rec.msg.msgTitle+'</a>';
					return detail;
				}else {
				}
				return rec.msg.msgTitle;
			}},
			//{field:'msgContent',title:'消息内容',width:80,align:'center',sortable:true},
			//{field:'msgUrl',title:'消息地址',width:80,align:'center',sortable:true},
//			{field:'msgLevel',title:'消息级别',align:'center',sortable:true,formatter:function(value,rec,index){
//				return rec.msg.msgLevel;
//			}},
//			{field:'createUser',title:'消息创建人',align:'center',sortable:true,formatter:function(value,rec,index){
//				
//				return rec.msg.createUser;
//			}},
			{field:'createTime',title:'消息创建时间',width:100,align:'center',sortable:true,formatter:function(value,rec,index){
				return rec.msg.createTime;
			}}

//			{field:'opt',title:'操作',width:100,align:'center',
//				formatter:function(value,rec,index){
//						var edit = '<a id="editMsg" href="javascript:void(0)"  class="editMsg" onclick="editMsg(\''+ rec.id + '\')" iconCls="icon-edit"  plain="true"></a>';
//						var detail = '<a id="detailMsg" href="javascript:void(0)" class="detailMsg" onclick="detailMsg(\''+ rec.id + '\')" iconCls="icon-edit"  plain="true"></a>';
//						return edit+" | "+detail;
//					
//				}
//			}
		]],
		pagination:true,
		rownumbers:true
		
//		onLoadSuccess:function(data){
//		
//			var flag = true;
//			flag = isHasElementPerssion('editMsg');
//			$("a#editMsg").each(function(){
//				if(!flag){
//					$(this).css("display","none");
//				}
//			});
//			
//			flag = isHasElementPerssion('detailMsg');
//			$("a#detailMsg").each(function(){
//				if(!flag){
//					$(this).css("display","none");
//				}
//			});
//			
//			/**
//			 * 数据加载成功后渲染linkbutton
//			 */
//			$(".editMsg").linkbutton({
//				text:'编辑', 
//				title:'编辑',
//				plain:true
//			});
//			$(".detailMsg").linkbutton({
//				text:'详细', 
//				title:'详细',
//				plain:true
//			});
//		}
	});
	//分页
	var p = $('#msgGrid').datagrid('getPager');
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
function searchMsg(){
	var params = $('#msgGrid').datagrid('options').queryParams; //先取得 datagrid 的查询参数  
    var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象 
    $.each( fields, function(i, field){  
        params[field.name] = field.value; //设置查询参数  
    });   
    $('#msgGrid').datagrid('reload'); //设置好查询参数 reload
}

/**
 * 编辑机房
 * @param id 机房id
 */
function editMsg(id){
	var params = [{param:[{paramName:"id",value:id}]}];
	openWin("editMsgWin","system/redirect/redirectHomePage.do?path=m/msg_edit","编辑机房信息",650,300,params);
}

/**
 * 
 * @param id 机房id
 */
function detailMsg(id,relid){
	var params = [{param:[{paramName:"id",value:id}]}];
	updateState(relid);
	openWin("detailMsgWin","system/redirect/redirectHomePage.do?path=system/msg_detail","消息详情",510,250,params);
}

function updateState(msgrelid){
	$.ajax({
		url: 'system/msgRel/upateState.do', 
		type: 'post',
		//async: false,
		dataType: 'json',
		data:{id:msgrelid,state:1},
		success: function(data){
			
		}
	});
}




