/**
 * 机房信息业务
 */
//定义表格中是否有数据的标志位
var dataFlag = false;
//接收查询表单id
var queryFormId = "queryForm";
$(function(){
	
	//验证查询时间
	$.extend($.fn.validatebox.defaults.rules, {
		//方法说明必须在存在方法的情况下填写
		after : {
			validator : function(value, param) {
				var timeBefore = $('#timeBefore').datebox('getValue');
				var timeAfter = $('#timeAfter').datebox('getValue');
				if( timeBefore > timeAfter){
					return false;
				}
				return true;
			},
			message : '查询截止时间必须大于查询开始时间!'
		},
		
		afterAdvance : {
			validator : function(value, param) {
				var timeBefore = $('#timeBeforeAdvance').datebox('getValue');
				var timeAfter = $('#timeAfterAdvance').datebox('getValue');
				if( timeBefore > timeAfter){
					return false;
				}
				return true;
			},
			message : '查询截止时间必须大于查询开始时间!'
		}
	});
	
	//初始化页面元素
//	initElementPermissions();
	//加载数据
	loadData();
	
	//加载查询条件
	loadQuery();
	
	//弹出编辑页面
	$("#addAccessLog").click(function(){
		openWin("addAccessLogWin","system/redirect/redirectHomePage.do?path=system/accessLog_add","添加机房",650,450);
	});
	
	//查询
	$("#searchAccessLog").click(function(){
		queryFormId = "queryForm";
		searchAccessLog();
	});
	
	//重置条件
	$("#resetAccessLog").click(function(){
		 $('#queryForm').form('clear'); 
		 searchAccessLog();
	});
	
	//弹出高级查询页面
	$('#advanceSearch-dlg').hide();
	$('#searchAccessLogAdvance').click(function(){
		
		$('#advanceSearch-dlg,#advancedQueryForm').show();
		$('#advanceSearch-dlg').dialog({
		    title: '高级查询',
		    width: 650,
		    height: 300,
		    closed: false,
		    cache: false,
		    zIndex:3000,
		    modal: true
		});
	});
	
	//高级查询
	$("#advanceSearchSubmit").click(function(){
		queryFormId = "advancedQueryForm";
		searchAdvance();
	});
	
	//高级查询重置
	$("#advanceSearchReset").click(function(){
		$('#advancedQueryForm').form('clear'); 
		searchAdvance();
	});
	
	//批量删除
	$("#delAccessLog").click(function(){
		 var rows = $('#accessLogGrid').datagrid('getChecked'); 
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
 	            	 $.post('system/accessLog/delBatchEntity.do'+ps,function(data){  
 	                    $('#accessLogGrid').datagrid('reload');   
 	                });  
 	            }  
        	 });  
         }
	});
	
	
	$('#exportAccessLog').click(function(){
		if( dataFlag ){
			$("#"+queryFormId).form('submit',{
		 		url : 'system/accessLog/exportExcel.do',
		 		onSubmit : function() {
		 			return $(this).form('validate');
		 		},
		 		success : function(data) {				
		 		}
		    });
		}else{
			 $.messager.alert('提示',"表格中不存在导出数据!");  
		}
	});
	
});

/**
 * 初始化加载数据,分页
 */
function loadData(){
	//grid数据加载
	$('#accessLogGrid').datagrid({
		view:detailview,
		fit:true,
		fitColumns:true,
		collapsible:false,
		nowrap: true,
		singleSelect:false,  
		autoRowHeight: false,
		striped: true,
		url:'system/accessLog/getPageModel.do',
		sortName: 'id',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'id',
		checkOnSelect:false,
		frozenColumns:[[
            {field:'ck',checkbox:true}
		]],
		columns:[[
			{field:'logMsg',title:'日志信息',width:200,align:'left',sortable:true},
			{field:'ip',title:'访问端IP',width:40,align:'center',sortable:true},
			{field:'userName',title:'操作用户',width:40,align:'center',sortable:true},
			{field:'time',title:'时间',width:60,align:'center',sortable:true}
//			,
//			{field:'opt',title:'操作',width:20,align:'center',
//				formatter:function(value,rec,index){
////						var edit = '<a id="editAccessLogDg" href="javascript:void(0)"  onclick="editAccessLogDg(\''+ rec.id + '\')"><span class="drop-edit"></span>编辑</a>';
//						var del = '<a id="delAccessLogDg" href="javascript:void(0)" onclick="delAccessLogDg(\''+ rec.id + '\')"><span class="drop-del"></span>删除</a>';
//						var btns = new Array();
////						btns.push(edit);
//						btns.push(del);
//						return createBtn(rec.id, btns);
//				}
//			}
		]],
		pagination:true,
		rownumbers:true,
		toolbar:'#tb',
		detailFormatter: function(rowIndex, rowData){
			return '<div class="ddv" id="false"></div>';
		},
		onClickRow: function(rowIndex,rowData){
			detailGridClickFn($(this), rowIndex);
		},
		onExpandRow: function(index,rowData){
			/**
			 * 展示详情信息；
			 * 1. 先清空原有的层下的数据和样式
			 * 2. 获取数据，将模板表单克隆一份，修改克隆后的id
			 * 3. 将数据load到克隆后的模板，append到该显示的地方
			 * 4. 重新定位详情的高度
			 */
            var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
            ddv.empty();
			var cloneDetailForm = $('#accessLogDetailForm').clone(); 
			cloneDetailForm.attr('id', 'accessLogDetailForm'+rowData.id);
			ddv.append(cloneDetailForm);
			
			$.getJSON('system/accessLog/getEntity.do',{'id':rowData.id},function(data){
				$(cloneDetailForm).form('load',data.result);
			});
            $('#accessLogGrid').datagrid('fixDetailRowHeight',index);
		},
		onLoadSuccess:function(data){
			//判断表格数据是否存在
			dataFlag = data.total > 0;
			
			var flag = true;
			flag = isHasElementPerssion('editAccessLog');
			$("a#editAccessLog").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			flag = isHasElementPerssion('detailAccessLog');
			$("a#detailAccessLog").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			onSelectBtnEvent(data.rows);
		}
	});
	//分页
	var p = $('#accessLogGrid').datagrid('getPager');
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
function searchAccessLog(){
	
	if( $("#queryForm").form('validate') ){
		var formId = $("a[type='export']").attr("formId","queryForm");
		var params = []; //先取得 datagrid 的查询参数  
		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象 
		$.each( fields, function(i, field){  
			params[field.name] = field.value; //设置查询参数  
		});   
		$('#accessLogGrid').datagrid('load',params); //设置好查询参数 reload
	}
}

/**
 * 高级查询信息
 */
function searchAdvance(){
	var formId = $("a[type='export']").attr("formId","advancedQueryForm");
	var params = []; //先取得 datagrid 的查询参数  
    var fields =$('#advancedQueryForm').serializeArray(); //自动序列化表单元素为JSON对象 
    $.each( fields, function(i, field){  
        params[field.name] = field.value; //设置查询参数  
    });  
    $('#advanceSearch-dlg').dialog('close');
    $('#accessLogGrid').datagrid('load',params); //设置好查询参数 reload
}

/**
 * 编辑机房
 * @param id 机房id
 */
function editAccessLogDg(id){
	var params = [{param:[{paramName:"id",value:id}]}];
	openWin("editAccessLogWin","system/redirect/redirectHomePage.do?path=system/accessLog_edit","编辑机房信息",650,450,params);
}

/**
 * 单条删除
 */
function delAccessLogDg(id){
	 $.messager.confirm('提示','确定要删除吗?',function(result){  
        if (result){  
        	 $.post('system/accessLog/delEntity.do',{id:id},function(data){  
                $('#accessLogGrid').datagrid('reload');   
            });  
        }  
     });  
}

function loadQuery(){
	//加载系统平台
	$("#plateform").combobox({
		url:'system/accessLog/getPlateFormList.do?funcId=-1,-2',
		valueField:'id',
		textField:'funcName',
		editable:false,
		zIndex:6000,
		panelHeight:'auto',
		onSelect: function(rec){//被选中,加载所属平台的模块
			var p_id = rec.id;
			//加载平台所属功能模块
			$("#funcModuleAdvance").combobox({
				url:'system/accessLog/getFuncMenuList.do?parentId='+p_id,
				valueField:'funcName',
				textField:'funcName',
				editable:false,
				panelHeight:'auto',
				onLoadSuccess: function () { //加载完成后,设置选中第一项
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'funcName') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
				onSelect: function(rec){//被选中,加载所属模块的一级菜单
					var p_id = rec.id;
					//加载模块一级菜单
					$("#fiMenuAdvance").combobox({
						url:'system/accessLog/getFuncMenuList.do?parentId='+p_id,
						valueField:'funcName',
						textField:'funcName',
						editable:false,
						panelHeight:'auto',
						onSelect: function(rec){//被选中,加载所属一级菜单的二级菜单
							var p_id = rec.id;
							//加载模块二级菜单
							$("#seMenuAdvance").combobox({
								url:'system/accessLog/getFuncMenuList.do?parentId='+p_id,
								valueField:'funcName',
								textField:'funcName',
								editable:false,
								panelHeight:'auto'
							});
						}
					});
				}
			});
		}
	});
	
	//加载方法类型
	$("#methodTypeAdvance").combobox({
		url:'system/accessLog/getMethodType.do',
		valueField:'id',
		textField:'value',
		editable:false,
		panelHeight:'auto'
	})
}
