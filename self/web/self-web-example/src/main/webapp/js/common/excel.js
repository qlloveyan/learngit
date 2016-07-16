//定义formid
var staticformId ="";
var staticgirdId="";

//定义form map
var queryFormMap =new Map();
/****************************************
 * 定义导出对象
 * @param grid 页面数据显示grid
 * @param queryform 页面查询from
 * @param totalCount grid记录数
 ****************************************/
function exportObj(oldurl,grid,queryform,totalCount){
	var obj= new Object();
	obj.oldurl = oldurl;
	obj.grid = grid;
	obj.queryform = queryform;
	obj.totalCount = totalCount;
	return obj;
}

$(function() {
	//initFormData();
	if(typeof(selfAction) == "undefined"){
		initExportMenu();
	}else{
		initExportMenu(selfAction);
	}
	
});

/************************************
 * @author wangguohong
 * @deprecated 创建导出方式选择框
 * @returns {String} 
 ***********************************/
function initexportwayStr(formId){
	var exportwayStr ='<table id="exportwayTable" style="width: 100%;" border="0px" cellspacing="0" class="excel-table" >'+
						'<tr>'+
							'<th>格式选择:</th>'+
							'<td>'+
								'<input type="radio" name="rdexportType"  value="1" checked="true"></input>excel&nbsp;&nbsp;'+
								
								'<input type="radio" name="rdexportType" value="2"></input>csv'+
							'</td>'+
						'</tr>'+
						'<tr>'+
							'<th>数据选择:</th>'+
							'<td>'+
								'<input type="radio" name="data" value="1" checked="true"></input>当前页&nbsp'+
								'<input type="radio" name="data" value="2"></input>全部&nbsp;'+
								'<input type="radio" name="data" value="3"></input>查询结果&nbsp;'+
								'<input type="radio" name="data" value="4"></input>选择数据&nbsp;'+
							'</td>'+
						'</tr>'+
						'<tr>'+
							'<th valign="top">字段选择:</th>'+
							'<td><div id="proertiesTable" class="excel" ></div></td>'+
						'</tr>'+
					'</table>';
	var propertiesStr = '<div id="dchproerties" class="easyui-dialog" title="选择字段"  closed="false" modal="true" >'+
						'<form id="exportForm">'+
							exportwayStr+
						'</form>'+
						'</div>';
	
	return propertiesStr;
}

/**************************************
 * 添加导出字段
 * @param formId
 *************************************/
function addexportProperty(formId){
	//定义选择字段内容
	var checkboxs = "";
	var exprotObj = queryFormMap.get(formId);
	//获取gird列集合
	var columns = exprotObj.grid.datagrid('options').columns;
	var index=0;
	$.each( columns[0], function(i, c){
		//去除不需要导出的 和 隐藏的列 
		if(c.isExport!=false && c.hidden!=true){
			var template = '<span class="excel-title"><i><input name="ckPropert" value="'+c.field+'" title="'+c.title+'" type="checkbox" checked="checked"/></i>'+c.title+'</span>';
			checkboxs = checkboxs+template;
			index++;
		}
    });
	//添加选择导出字段内容
	$('#proertiesTable').html(checkboxs);
}

/****************************************
 * 修改queryfrom 的action属性赋值为导出数据地址
 * @param formId
 **************************************/
function changeQuryformAction(selfAction,formId){
	var exprotObj = queryFormMap.get(formId);
	//提取gird提交地址变为导出地址
	var url = exprotObj.grid.datagrid('options').url;
	oldurl = url;
	var suffix =url.split("/")[url.split("/").length-1];
	var newurl;
	if(typeof(selfAction) != "undefined"){
		newurl =url.replace(suffix,selfAction); 
	}else{
		newurl = url.replace(suffix,"exportExcel.do");
	}
	$(exprotObj.queryform).attr("action",newurl);
	$(exprotObj.queryform).attr("method","post");
}



/**
 * 还原form
 * @param formId
 */
function restformAction(formId){
//	var exprotObj = queryFormMap.get(formId);
////	$("#"+formId).attr("action",exprotObj.oldurl);
////	$("#"+formId).attr("method","post");
//	$(exprotObj.queryform).attr("action",exprotObj.oldurl);
//	$(exprotObj.queryform).attr("method","post");
//	console.error(queryFormMap);
	queryFormMap =  new Map();
}

/**
 * 导出当前页
 * @param formId
 */
function exprotCurrent(selfAction , formId){
	//添加导出字段
	addexportProperty(formId);
	//获取导出组件对象
	var exprotObj = queryFormMap.get(formId);
	//提取gird提交地址变为导出地址
	changeQuryformAction(selfAction,formId);
	
	//获取导出数据的gridid
	var options = exprotObj.grid.datagrid('getPager').data("pagination").options;
	//获取排序字段
	//定义默认排序字段
	var sort="id";
	//定义默认排序方式
	var order="desc";
	sort = exprotObj.grid.datagrid('options').sortName;
	//获取排序方式
	order = exprotObj.grid.datagrid('options').sortOrder;
	//当前页数
	var pageNumber = getpageProperty(exprotObj.grid).pageNumber;
	//分页大小
	var pageSize = getpageProperty(exprotObj.grid).pageSize;
	
	$(exprotObj.queryform).find("input[name='isExport']").val("false");
	
	
	var columns = exprotObj.grid.datagrid('options').columns;
	//获取选择字段 与字段名称
	var choosearrPropert = new Array();
	var choosearrTitle = new Array();
	$.each( columns[0], function(i, c){
		
		if(c.isExport!=false && c.hidden!=true){
			choosearrPropert[i]=c.field;
			choosearrTitle[i]=c.title;
		}
    });
//	console.log(columns);
	$(exprotObj.queryform).find("input[name='properties']").val(eval(choosearrPropert));
	$(exprotObj.queryform).find("input[name='titles']").val(eval(choosearrTitle));
	$(exprotObj.queryform).find("input[name='sort']").val(sort);
	$(exprotObj.queryform).find("input[name='order']").val(order);
	$(exprotObj.queryform).find("input[name='isExport']").val("true");
	$(exprotObj.queryform).find("input[name='exportType']").val(1);
	///////////////////////////////////////
	var ids = new Array();
	var idFieldName=exprotObj.grid.datagrid('options').idField;
	var columnschoonse = exprotObj.grid.datagrid('getChecked');
	//获取选中数据 的id集合
	$.each( columnschoonse, function(i, c){
		ids[i] = c[idFieldName]; 
	});
	
	if(ids.length>0){
		
		$(exprotObj.queryform).find("input[name='isExport']").val("true");
		//console.log(eval(ids));
		$(exprotObj.queryform).find("input[name='ids']").val(eval(ids));
		submit(formId,false,exprotObj.totalCount,1);
	}else{
	
		//当前页数
		var pageNumber = getpageProperty(exprotObj.grid).pageNumber;
		//分页大小
		var pageSize = getpageProperty(exprotObj.grid).pageSize;
		$(exprotObj.queryform).find("input[name='isExport']").val("false");
		submit(formId,false,pageSize,pageNumber); //直接导出不用于后台导出
	}
	initExportMenu(selfAction);
	clean(formId);
	var gridtemp = $("#"+staticgirdId);
	deleteObjectPerperty(gridtemp.datagrid('options').queryParams);
}

/**
 * 导出全部
 * @param formId
 */
function exprotAll(selfAction,formId){
	
	var exprotObj = queryFormMap.get(formId);
	//提取gird提交地址变为导出地址
	changeQuryformAction(selfAction,formId);
	var sort="id";
	//定义默认排序方式
	var order="desc";
	sort = exprotObj.grid.datagrid('options').sortName;
	//获取排序方式
	order = exprotObj.grid.datagrid('options').sortOrder;
	var columns = exprotObj.grid.datagrid('options').columns;
	//获取选择字段 与字段名称
	var choosearrPropert = new Array();
	var choosearrTitle = new Array();
	$.each( columns[0], function(i, c){
		
		if(c.isExport!=false && c.hidden!=true){
			choosearrPropert[i]=c.field;
			choosearrTitle[i]=c.title;
		}
    });
	$(exprotObj.queryform).find("input[name='properties']").val(eval(choosearrPropert));
	$(exprotObj.queryform).find("input[name='titles']").val(eval(choosearrTitle));
	$(exprotObj.queryform).find("input[name='sort']").val(sort);
	$(exprotObj.queryform).find("input[name='order']").val(order);
	$(exprotObj.queryform).find("input[name='isExport']").val("true");
	$(exprotObj.queryform).find("input[name='exportType']").val(2);
	////////////////////
	
	//先根据页面查询条件查询数据 目的在于获取有多少数据
	inittotalCount(staticformId,staticgirdId,function(count){
		if(count<LIMIT_BACK){//直接导出不用于后台导出 LIMIT_BACK=100
			$(exprotObj.queryform).find("input[name='isExport']").val("false");
			$(exprotObj.queryform).find("input[name='exportType']").val(1);
			submit(formId,false,exprotObj.totalCount,1,function(){
				//alert("清理");
				initExportMenu(selfAction);
				clean(formId);
				var gridtemp = $("#"+staticgirdId);
				deleteObjectPerperty(gridtemp.datagrid('options').queryParams);
			}); 
		}else{
			
			$.messager.confirm("提示","共<font color=red>"+count+"</font>条记录！确定需要全部导出吗？",function(data){
				if(data){
					$("#" + formId+" input[name='isExport']").val("false");
					$("#" + formId+" input[name='isback']").val(eval(true));
					submit(formId,true,exprotObj.totalCount,null,function(){
						
						//restformAction(staticformId);
						initExportMenu(selfAction);
						clean(formId);
						var gridtemp = $("#"+staticgirdId);
						deleteObjectPerperty(gridtemp.datagrid('options').queryParams);
					});//数据量大用于后台导出
				}else{
					initExportMenu(selfAction);
					clean(formId);
					var gridtemp = $("#"+staticgirdId);
					deleteObjectPerperty(gridtemp.datagrid('options').queryParams);
					return;
				}
			});
			$(".panel-tool-close").click(function(){
				initExportMenu(selfAction);
				clean(formId);
				var gridtemp = $("#"+staticgirdId);
				deleteObjectPerperty(gridtemp.datagrid('options').queryParams);
			});
		}
	});
}
/**
 * 导出选择字段
 */
function exportExcelChooseProperties(selfAction,formId){
	//添加导出方式选择框
	var value = $("#dchproerties").length;
	//判断选择窗口div是否已经存在
	if(value>0){
	}else{
		$("body").append(initexportwayStr(formId));
	}
	//添加导出字段
	addexportProperty(formId);
	//获取导出组件对象
	var exprotObj = queryFormMap.get(formId);
	//提取gird提交地址变为导出地址
	changeQuryformAction(selfAction,formId);
	
	//获取导出数据的gridid
	var options = exprotObj.grid.datagrid('getPager').data("pagination").options;
	
	
	//定义默认排序字段
	var sort="id";
	//定义默认排序方式
	var order="desc";
	//获取排序字段
	sort = exprotObj.grid.datagrid('options').sortName;
	//获取排序方式
	order = exprotObj.grid.datagrid('options').sortOrder;
	//定义选择中的数据集合
	var ids = new Array();
	
    //弹出选择导出方式窗口
    $('#dchproerties').dialog({
    	width:600,
    	height:350,
		title: '导出方式',
		onClose: function(){
			//关闭窗口后还原form中的属性
			initExportMenu(selfAction);
			clean(formId);
			var gridtemp = $("#"+staticgirdId);
			deleteObjectPerperty(gridtemp.datagrid('options').queryParams);
		},
		onOpen : function(){
			alert(2);
			//弹出导出框的时候初始化导出条件
			//initFormData();
		},
		buttons: [{ 
			text: '导出', 
			iconCls: 'icon-ok',
			handler: function() {
				
				//获取选择字段 与字段名称
				var choosearrPropert = new Array();
				var choosearrTitle = new Array();
				$("input[name='ckPropert']:checked").each(function(i){
					choosearrPropert[i]=$(this).attr("value");
					choosearrTitle[i]=$(this).attr("title");
				});
				//$(exprotObj.queryform).find("input[name='isExport']")
				$(exprotObj.queryform).find("input[name='properties']").val(eval(choosearrPropert));
				$(exprotObj.queryform).find("input[name='titles']").val(eval(choosearrTitle));
				$(exprotObj.queryform).find("input[name='sort']").val(sort);
				$(exprotObj.queryform).find("input[name='order']").val(order);
				$(exprotObj.queryform).find("input[name='isExport']").val("true");
				var exportTypechecked = parseInt($("input[name='rdexportType']:checked").val());
				
				$(exprotObj.queryform).find("input[name='exportType']").val(exportTypechecked);
				
				//判断导出方式
				var datachecked = parseInt($("input[name='data']:checked").val());
				switch(datachecked){
					case 1: //导出当1前页数据
						//当前页数
						var pageNumber = getpageProperty(exprotObj.grid).pageNumber;
						//分页大小
						var pageSize = getpageProperty(exprotObj.grid).pageSize;
						if(exportTypechecked==2){
							$.messager.alert("提示","数据比较少请选择excel导出方式",function(data){
								return;
							});
							return;
						}
						$(exprotObj.queryform).find("input[name='isExport']").val("false");
						submit(formId,false,pageSize,pageNumber); //直接导出不用于后台导出
						break;
					case 2: //导出全部数据
						if(exportTypechecked==1){
							$.messager.alert("提示","全部数据导出请选择csv导出方式,在后台导出",function(data){
								return;
							});
							return;
						}
						$.messager.confirm("提示","所有数据总共<font color=red>"+exprotObj.totalCount+"</font>条记录,全部导出需要较长的时间！确定需要全部导出吗？",function(data){
							if(data){
								
								$("#" + formId+" input[name='isExport']").val("false");
								$("#" + formId+" input[name='isback']").val(eval(true));
								submit(formId,true,exprotObj.totalCount,null);//数据量大用于后台导出
							}else{
								return;
							}
						});
						break;
					case 3: //导出当前查询结果数据
						if(exportTypechecked==1){
							$.messager.alert("提示","查询结果数据导出请选择csv导出方式,在后台导出",function(data){
								return;
							});
							return;
						}
						//先根据页面查询条件查询数据 目的在于获取有多少数据
						inittotalCount(staticformId,staticgirdId,function(count){
							$.messager.confirm("提示","所有数据总共<font color=red>"+count+"</font>条记录！确定需要全部导出吗？",function(data){
								if(data){
									$("#" + formId+" input[name='isExport']").val("false");
									$("#" + formId+" input[name='isback']").val(eval(true));
									submit(formId,true,exprotObj.totalCount,null);//数据量大用于后台导出
								}else{
									return;
								}
							});
						});
//						console.log(exprotObj);
						
						break;
					case 4://导出选择的数据
						//获取gird制定的选择数据的id
						var idFieldName=exprotObj.grid.datagrid('options').idField;
						var columnschoonse = exprotObj.grid.datagrid('getChecked');
						//获取选中数据 的id集合
						$.each( columnschoonse, function(i, c){
							ids[i] = c[idFieldName]; 
						});
						if(ids.length==0){
							$.messager.alert("提示","请选中需要导出的数据！");
							return;
						}else{
							if(exportTypechecked==2){
								$.messager.alert("提示","数据比较少请选择excel导出方式",function(data){
									return;
								});
								return;
							}
							$(exprotObj.queryform).find("input[name='isExport']").val("true");
							//console.log(eval(ids));
							$(exprotObj.queryform).find("input[name='ids']").val(eval(ids));
							submit(formId,false,exprotObj.totalCount,1);
						}
						break;
				}
				
			} 
		},{ 
			text: '取消', 
			handler: function() { 
				//关闭窗口，还原是否选择字段导出 默认false
//				
//				initExportMenu();
//				clean(formId);
//				var gridtemp = $("#"+staticgirdId);
//				deleteObjectPerperty(gridtemp.datagrid('options').queryParams);
				coloseWin();
			} 
		}]
	});
}

/**
 * 计算请求批次
 * @param count 记录总数
 * @param batchlimit 每笔记录数
 */
function calculateBatch(count,batchlimit){
	var countTemp = parseInt(count);
	var batchlimitTemp = parseInt(batchlimit);
	var batchCount = 0;
	if(countTemp%batchlimitTemp==0){
		batchCount = countTemp/batchlimitTemp;
	}else{
		batchCount = Math.floor(countTemp/batchlimitTemp)+1;
	}
	return batchCount;
}

/**
 * 提交导出,
 * @param formId
 */
function submit(formId,isback,count,pagenum,callback){
	//console.error("queryFormMap的值")
		//console.log(queryFormMap);
	
	//alert(isback);
	var exprotObj = queryFormMap.get(formId);
	if(exprotObj==null){
		return;
	}
//	console.error(exprotObj.queryform);
	if(isback){
		//获取下载文件名称
		var fileName = $(exprotObj.queryform).find("input[name=fileName]").val();
		//修改文件名称
		var timestamp= new Date().getTime();
		fileName = fileName+timestamp;
		
		$(exprotObj.queryform).find("input[name=exportType]").val(2);
		$(exprotObj.queryform).find("input[name=fileName]").val(fileName);
		$(exprotObj.queryform).find("input[name='batchnum']").val(eval(parseInt(pagenum)));
		$(exprotObj.queryform).find("input[name='recordsum']").val(eval(parseInt(count)));
		$(exprotObj.queryform).find("input[name='batchsize']").val(eval(parseInt(BATCH_COUNT)));
		$(exprotObj.queryform).find("input[name='isback']").val(eval(true));
		var params = getparams(exprotObj.grid,exprotObj.queryform);
		//console.log(params);
		
//		var result = $.ajax({
//			type:"POST",
//			url:$(exprotObj.queryform).attr("action"),
//			data:params,
//			dataType:"json",
//			async: false
//		}).responseText;
		$.ajax({
			type:"POST",
			url:$(exprotObj.queryform).attr("action"),
			data:params,
			dataType:"json",
			error:function(result){
				$.messager.alert("操作提示", "已开始后台下载，稍后会有下载消息通知您<br/>下载文件为：<font color=red>"+$("#fileName").val()+"</font>", "info", function () {
					coloseWin();
				});
				if(callback!=null){
					callback();
	    		}
			}
		});
//		if(result!=null){
//			
//			$.messager.alert("操作提示", "已开始后台下载，稍后会有下载消息通知您<br/>下载文件为：<font color=red>"+$("#fileName").val()+"</font>", "info", function () {
//				coloseWin();
//			});
//			if(callback!=null){
//				callback();
//    		}
//		}
	
	}else{
		$(exprotObj.queryform).find("input[name='batchsize']").val(eval(parseInt(count)));
		$(exprotObj.queryform).find("input[name='batchnum']").val(eval(pagenum));
		$(exprotObj.queryform).find("input[name='isback']").val(eval(false));
		$(exprotObj.queryform).attr("method","post");
		$(exprotObj.queryform).submit();
		if(callback!=null){
			callback();
		}
		coloseWin();
		
	}
}

/**
 * 关闭窗口
 */
function coloseWin(){
	$('#dchproerties').dialog('close'); 
}



/*************************************
 * 获取参数
 * @param gridid
 * @param formId
 * @returns
 *************************************/
function getparams(grid,queryForm){
	//获取grid查询参数
//	var params = grid.datagrid('options').queryParams;
	var params = new Object();
	//自动序列化表单元素为JSON对象
	var fields =$(queryForm).serializeArray();
	//console.log(fields);
    $.each( fields, function(i, field){  
        params[field.name] = field.value; //设置查询参数  
    });
    //console.log(params);
   return params;
    //return fields;
}

/*******************************************
 * @author wangguohong
 * @deprecated 清除导出条件
 * 
 ******************************************/
function clean(formId){
	var form = $("#"+formId);
	$(form).find("input[name=titles]")
	$(form).find("input[name=properties]").remove();
	$(form).find("input[name=isExport]").remove();
	$(form).find("input[name=ids]").remove();
	$(form).find("input[name=sort]").remove();
	$(form).find("input[name=order]").remove();
	$(form).find("input[name=isback]").remove();
	$(form).find("input[name=batchsize]").remove();
	$(form).find("input[name=batchnum]").remove();
	$(form).find("input[name=recordsum]").remove();
	$(form).find("input[name=batchEnd]").remove();
	$(form).find("input[name=exportType]").remove();
	$(form).find("input[name=fileName]").remove();
	$(form).find("input[name=titles]").remove();
	$(form).removeAttr("action");
	$(form).removeAttr("method");
	queryFormMap.remove(formId);
}

/****************************************
 * 检测组件正确使用
 * @param exportmenu
 * <a href="javascript:void(0)"  exportExcel="houseGrid" 
 * fileName="house" type="export"  id="exportidcIspUnit" 
 * formId="queryForm"><span class="export"></span>导出</a>
 ***************************************/
function checkexport(exportmenu){
	var flag = true;
	if($(exportmenu).attr("fileName")==undefined){
		$.messager.alert("警告,请正确使用导出组件","组件缺少fileName属性");
		flag =  false;
	}
	if($(exportmenu).attr("exportExcel")==undefined){
		$.messager.alert("警告,请正确使用导出组件","组件缺少exportExcel属性");
		flag = false;
	}
	if($(exportmenu).attr("formId")==undefined){
		$.messager.alert("警告,请正确使用导出组件","组件缺少formId属性");
		flag = false;
	}
	return flag;
}

/*******************************************
 * @author wangguohong
 * @deprecated 为queryfrom 添加导出属性
 * 
 ******************************************/
function appendExprotPropertyToQueryFrom(form,fileName){
	//alert(1);
	var formstr = '<input  name="properties" id="iproperties" type="hidden"/>'+
	  '<input  name="isExport" id="isExport" type="hidden"/>'+
	  '<input  name="ids" id="ids" type="hidden"/>'+
	  '<input  name="sort" id="sort" type="hidden"/>'+
	  '<input  name="order" id="order" type="hidden"/>'+
	  '<input  name="isback" id="isback" type="hidden"/>'+
	  '<input  name="batchsize" id="batchsize" type="hidden"/>'+
	  '<input  name="batchnum" id="batchnum" type="hidden"/>'+
	  '<input  name="recordsum" id="recordsum" type="hidden"/>'+
	  '<input  name="batchEnd" id="batchEnd" value=false type="hidden"/>'+
	  '<input  name="exportType" value="1" id="exportType" type="hidden"/>'+
	  '<input  name="fileName" value="'+fileName+'" id="fileName" type="hidden"/>'+
	  '<input  name="titles" id="titles" type="hidden"/>';
	form.append(formstr);
}

/*******************************************
 * @author wangguohong
 * @deprecated 获取分页参数
 ******************************************/
function getpageProperty(grid){
	var options = grid.datagrid('getPager').data("pagination").options;
	return options;
}

/*******************************************
 * @author wangguohong
 * @deprecated 初始化导出菜单
 ******************************************/
function initExportMenu(selfAction){
	var exportsubmenu = '<div id="exportMenu_sub" style="width:50px;">'+
	'<div id="export_crrent" style="padding-left:5px"><span class="exportcurrent"></span>导出当前页</div>'+
	'<h1 class="excel-line"></h1>'+
	'<div id="export_all" style="padding-left:5px"><span class="exportall"></span>导出全部</div>'+
	//'<h1 class="excel-line"></h1>'+
	//'<div id="export_choose"><span class="exportSelect"></span>选择导出方式</div>'+
	//'<div id="export_choose"></div>'+
	'</div>';
	$("body").append(exportsubmenu);
	var exports = $("a[type='export']");
	
	$.each(exports,function(data){
		//检查正确性
		if(!checkexport($(this))){
			return;
		}
		//判断导出组件类型  1 全部显示  2只显示导出当前页 3只显示导出全部
		var type = $(this).attr("exportType");
		if(type){
			if(type=="2"){
				$("#export_all").hide();
			}
			if(type=="3"){
				$("#export_crrent").hide();
			}
		}
		
		var formId = $(this).attr("formId");
		var gridId = $(this).attr("exportExcel");
		var exportMenu = $(this).menubutton({ 
			menu: "#exportMenu_sub",
			iconCls:"icon-export"
			
		}); 
		$(exportMenu.menubutton('options').menu).menu({
			onHide:function(){
				//取消事件
			},
			onClick: function (item) {
			staticformId = formId;
			staticgirdId = gridId;
			initFormData();
			if(item.id=="export_crrent"){
//				formId = $('a[type=export]').attr('formId');
//				staticformId = formId;
				exprotCurrent(selfAction,formId);
			}
			if(item.id=="export_all"){
//				formId = $('a[type=export]').attr('formId');
//				staticformId = formId;
				exprotAll(selfAction,formId);
			}
			if(item.id=="export_choose"){
					//console.log(exportobj);
					exportExcelChooseProperties(selfAction,formId);
				}
			}
		});
	});
}

/*******************************************
 * @author wangguohong
 * @deprecated 初始化queryform
 * 根据页面中的导出组件，获取queryfrom放入map中
 ******************************************/
function initFormData(){
	//获取导出组件集合，一个页面可能有多个导出组件
	var exports = $("a[type='export']");
	//遍历组件集合，存储map，以formId作为key，组件作为value
	$.each(exports,function(data){
		var formId = $(this).attr("formId");
		var gridId = $(this).attr("exportExcel");
		//alert(formId);
		var fileName = $(this).attr("fileName");
		var from = $("#"+formId);
		var grid = $("#"+gridId);
		//if(queryFormMap.get(formId) == null || queryFormMap.get(formId)==""){
			appendExprotPropertyToQueryFrom(from,fileName);
			
			//提取gird提交地址变为导出地址
			var oldurl = grid.datagrid('options').url;
			var exportobj = new exportObj(oldurl,grid,from,getpageProperty(grid).total);
			queryFormMap.put(formId,exportobj);
		//}
		
	});
}


/**
 * 判断字符是否存在一个集合中
 * @param temp 查找的字符串
 * @param arr 字符串集合
 */
function isexits(temp,arr){
	var flag = false;
	for(i=0;i<arr.length;i++){
		if(arr[i]==temp){
			flag = true;
			break;
		}
	}
	return flag;
}

/**
 * 删除多余的属性
 * @param obj
 */
function deleteObjectPerperty(obj){
	var cleanProper = ["titles","properties","isExport","ids","sort"
                       ,"order","isback","batchsize","batchnum",
                       "batchEnd","recordsum","exportType","fileName","titles"];
	for(i=0;i<cleanProper.length;i++){
		obj[cleanProper[i]]=undefined;
	}
}
/**
 * 更新总数
 * @param staticformId
 * @param staticgirdId
 */
function inittotalCount(staticformId,staticgirdId,callback){
	var exportobj = queryFormMap.get(staticformId);
	//alert(formId);
//	var from = $("#"+staticformId);
//	var grid = $("#"+staticgirdId);
	
	var from = exportobj.queryform;
	var grid = exportobj.grid;
//	obj.grid = grid;
//	obj.queryform = queryform;
	
	
	//alert(staticformId+"   "+staticgirdId);
	var params = grid.datagrid('options').queryParams; //先取得 datagrid 的查询参数  
    var fields =from.serializeArray(); //自动序列化表单元素为JSON对象
  //"isback",
    var cleanProper = ["titles","properties","isExport","ids","sort"
                       ,"order","batchsize","batchnum",
                       "batchEnd","recordsum","exportType","fileName","titles"];
    $.each( fields, function(i, field){
    	if(!isexits(field.value,cleanProper)){
    		params[field.name] = field.value; //设置查询参数  
    	}
    });  
    deleteObjectPerperty(grid.datagrid('options').queryParams);
//    grid.datagrid('load',{
//    	onLoadSuccess:function(data){
//    		alert(data.total);
//    	}
//    });
    grid.datagrid({
    	
    	onLoadSuccess:function(data){
			try{
				if(typeof (eval("onSelectBtnEvent")) == "function"){
					onSelectBtnEvent(data.rows);
				}
				if(typeof (eval("bindDropBtnEvent")) == "function"){
					bindDropBtnEvent(data.rows,grid.datagrid("options").idField);
				}
			}catch(e){
				
			} 
    	//	onSelectBtnEvent(data.rows);
    		if(callback!=null){
    			var exportobjtemp = queryFormMap.get(staticformId);
    			if(exportobjtemp!=null){
    				callback(data.total);
    			}
    		}
    	}
    });
  //  console.log(grid);
	
}