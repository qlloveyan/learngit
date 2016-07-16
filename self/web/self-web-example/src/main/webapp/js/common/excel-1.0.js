//定义formid
var formId ="";
//定义form map
var map =new Map();
$(function() {
	//定义菜单
	var exportsubmenu = '<div id="exportMenu_sub" style="width:100px;">'+
						'<div id="export_choose">选择导出方式</div>'+
						'</div>';
	$("body").append(exportsubmenu);
	//初始化导出菜单
	var exportMenu = $("a[type='export']").menubutton({ 
		menu: "#exportMenu_sub",
		iconCls:"icon-export"
	}); 
	$(exportMenu.menubutton('options').menu).menu({
        onClick: function (item) {
        	if(item.id=="export_choose"){
        		exportExcelChooseProperties();
        	}
        }
	});
});

/**
 * 导出选择字段
 */
function exportExcelChooseProperties(){
	initFormData();
	//定义选择导出方式
	var exportwayStr ='<table id="exportwayTable" style="width: 100%;" border="0px" cellspacing="0" >'+
		'<tr>'+
			'<td>导出格式选择:</td>'+
			'<td>'+
				'<input type="radio" name="format" value="1" checked="true"></input>excel'+
				//暂时不做csv格式导出
				//'<input type="radio" name="format" value="2"></input>csv&nbsp;'+
			'</td>'+
		'</tr>'+
		'<tr>'+
			'<td>导出数据选择:</td>'+
			'<td>'+
				'<input type="radio" name="data" value="1" checked="true"></input>当前页'+
				'<input type="radio" name="data" value="2"></input>全部'+
				'<input type="radio" name="data" value="3"></input>查询结果'+
				'<input type="radio" name="data" value="4"></input>选择数据'+
			'</td>'+
		'</tr>'+
		'<tr>'+
			'<td>导出字段选择:</td>'+
		'</tr>'+
	'</table>';
	//定义弹出选择窗口
	var propertiesStr = '<div id="dchproerties" style="width:450;height:350;padding:10px" class="easyui-dialog" title="选择字段"  closed="false" modal="true" >'+
						'<form id="exportForm">'+
							exportwayStr+
							'<table id="proertiesTable" style="width: 100%;" border="1px" cellspacing="0" >'+
						'</table>'+
						'</form>'+
						'</div>';
	//获取导出数据的gridid
	var gridid = $("a[type='export']").attr("exportExcel");
	//获取数据总数
	var options = $('#'+gridid+'').datagrid('getPager').data("pagination").options;
	var totalCount = options.total;
	
	//获取选中元素的ID
	var columnschoonse = $('#'+gridid+'').datagrid('getChecked');
	//获取gird制定的选择数据的id
	var idFieldName=$('#'+gridid+'').datagrid('options').idField;
	//定义默认排序字段
	var sort="id";
	//定义默认排序方式
	var order="desc";
	//获取排序字段
	sort = $('#'+gridid+'').datagrid('options').sortName;
	//获取排序方式
	order = $('#'+gridid+'').datagrid('options').sortOrder;
	//定义选择中的数据集合
	var ids = new Array();
	//判断选择窗口div是否已经存在
	var value = $("#dchproerties").length;
	if(value>0){
	}else{
		//在grid层添加弹出层内容
		$('#'+gridid+'').append(propertiesStr);
	}
	
	
	//定义
//	var arrPropert = new Array();
//	var arrTitle = new Array();
	//定义选择字段内容
	var checkboxs = "";
	//获取gird列集合
	var columns = $('#'+gridid+'').datagrid('options').columns;
	var index=0;
	$.each( columns[0], function(i, c){
		//去除不需要导出的 和 隐藏的列 
		if(c.isExport!=false && c.hidden!=true){
//			arrPropert[index]=c.field;
//			arrTitle[index]=c.title;
			var template = '<tr align="center"><td>'+c.title+'</td><td><input name="ckPropert" value="'+c.field+'" title="'+c.title+'" type="checkbox" checked="checked"/></td></tr>';
			checkboxs = checkboxs+template;
			index++;
		}
    });
	//添加选择导出字段内容
	$('#proertiesTable').html(checkboxs);
    
    //提取gird提交地址变为导出地址
	var url = $('#'+gridid+'').datagrid('options').url;
	var suffix =url.split("/")[url.split("/").length-1];
	var newurl = url.replace(suffix,"exportExcel.do"); 
    $('#'+formId).attr("action",newurl);
    $('#'+formId).attr("method","post");
    
    //弹出选择导出方式窗口
    $('#dchproerties').dialog({
		title: '导出方式',
		buttons: [{ 
			text: '导出', 
			iconCls: 'icon-ok',
			handler: function() {
				//获取grid查询参数
//				var params = $('#'+gridid+'').datagrid('options').queryParams;
//				//自动序列化表单元素为JSON对象
//				var fields =$('#'+formId).serializeArray();
//				console.log(fields);
//			    $.each( fields, function(i, field){  
//			        params[field.name] = field.value; //设置查询参数  
//			    });
//			    console.log(params);
				
				
				//获取选择字段 与字段名称
				var choosearrPropert = new Array();
				var choosearrTitle = new Array();
				$("input[name='ckPropert']:checked").each(function(i){
					choosearrPropert[i]=$(this).attr("value");
					choosearrTitle[i]=$(this).attr("title");
				});
				$("#" + formId+" input[name='properties']").val(eval(choosearrPropert));
				$("#" + formId+" input[name='titles']").val(eval(choosearrTitle));
				$("#" + formId+" input[name='sort']").val(sort);
				$("#" + formId+" input[name='order']").val(order);
				$("#" + formId+" input[name='isExport']").val("true");
				
				//判断导出方式
				var datachecked = parseInt($("input[name='data']:checked").val());
				switch(datachecked){
					case 1: //导出当前页数据
						var currentrows = $('#'+gridid+'').datagrid('getRows')
						$.each( currentrows, function(i, c){
							ids[i] = c[idFieldName]; 
						});
						//isExport 表示传入后台的 用于判断 是否导出页面选中的数据集 用于判断 sql语句的写法
						$("#" + formId+" input[name='isExport']").val("true");
						$("#" + formId+" input[name='ids']").val(eval(ids));
						
						submit(gridid,formId,false); //直接导出不用于后台导出
						break;
					case 2: //导出全部数据
						
						if(parseInt(totalCount)>LIMIT_EXPORT){
							$.messager.confirm("提示","所有数据总共<font color=red>"+totalCount+"</font>条记录,全部导出需要较长的时间！确定需要全部导出吗？",function(data){
								if(data){
									$("#" + formId+" input[name='isExport']").val("false");
									$("#" + formId+" input[name='isback']").val(eval(true));
									submit(gridid,formId,true);//数据量大用于后台导出
								}else{
									return;
								}
							});
						}else{
							$("#" + formId+" input[name='isExport']").val("false");
							submit(gridid,formId,false); //直接理解导出
						}
						break;
					case 3: //导出当前查询结果数据
						//先根据页面查询条件查询数据 目的在于获取有多少数据
						$('#'+gridid+'').datagrid('reload');
						var options1 = $('#'+gridid+'').datagrid('getPager').data("pagination").options;
						var totalCount1 = options1.total; 
						if(parseInt(totalCount1)>LIMIT_EXPORT){
							$.messager.confirm("提示","查询记录总共<font color=red>"+totalCount1+"</font>条记录,全部导出需要较长的时间！确定需要全部导出吗？",function(data){
								if(data){
									$("#" + formId+" input[name='isExport']").val("false");
									$("#" + formId+" input[name='isback']").val(eval(true));
									submit(gridid,formId,true);//数据量比较大，后台导出
								}else{
									return;
								}
							});
						}else{
							$("#" + formId+" input[name='isExport']").val("false");
							submit(gridid,formId,false); //直接导出
						}
						break;
					case 4://导出选择的数据
						//获取选中数据 的id集合
						$.each( columnschoonse, function(i, c){
							ids[i] = c[idFieldName]; 
						});
						if(ids.length==0){
							$.messager.alert("提示","请选中需要导出的数据！");
							return;
						}else{
							$("#" + formId+" input[name='isExport']").val("true");
							$("#" + formId+" input[name='ids']").val(eval(ids));
							if(ids.length>1){
								$.messager.confirm("提示","选中记录总共<font color=red>"+ids.length+"</font>条记录,全部导出需要较长的时间！确定需要全部导出吗？",function(data){
									if(data){
										$("#" + formId+" input[name='isback']").val(eval(true));
										submit(gridid,formId,true); //数据量比较大 后台导出
									}else{
										return;
									}
								});
								
							}else{
								submit(gridid,formId,false); //数据量比较小直接导出
							}
						}
						break;
				}
				
			} 
		},{ 
			text: '取消', 
			handler: function() { 
				//关闭窗口，还原是否选择字段导出 默认false
				coloseWin();
			} 
		}]
	});
}

/**
 * 提交导出,
 * @param formId
 */
function submit(gridid,formId,isback){
	var params = getparams(gridid,formId);
	if(isback){
		var result = $.ajax({
			type:"POST",
			url:$('#'+formId).attr("action"),
			data:params,
			dataType:"json",
			async: false
//			success:function(data){
//				alert(data);
//			}
		}).responseText;
		$.messager.alert("操作提示", "已开始后台下载，稍后会有下载消息通知您<br/>下载文件为：<font color=red>"+result+"</font>", "info", function () {
	         coloseWin();
	    });
	}else{
		$("#" + formId+" input[name='isback']").val(eval(false));
		$('#'+formId).attr("method","post");
		$('#'+formId).submit();
		coloseWin();
	}
}

/**
 * 关闭窗口
 */
function coloseWin(){
	//关闭窗口，还原是否选择字段导出 默认false
	$('#dchproerties').dialog('close'); 
	$("#" + formId+" input[name='isExport']").val("false");
}

function test(formId){
	$('#dchproerties').dialog('close'); 
	$("#" + formId+" input[name='isExport']").val("false");
	return $('#'+formId).submit();
}

/**
 * 获取参数
 * @param gridid
 * @param formId
 * @returns
 */
function getparams(gridid,formId){
	//获取grid查询参数
	var params = $('#'+gridid+'').datagrid('options').queryParams;
	//自动序列化表单元素为JSON对象
	var fields =$('#'+formId).serializeArray();
	console.log(fields);
    $.each( fields, function(i, field){  
        params[field.name] = field.value; //设置查询参数  
    });
    console.log(params);
    return params;
}
function initFormData(){
	formId = $("a[type='export']").attr("formId");
	//获取下载文件名称
	var fileName = $("a[type='export']").attr("fileName");
	//修改文件名称
	var timestamp= new Date().getTime();
	fileName = fileName+timestamp;
//	alert(fileName);
	if(map.get(formId) ==null || map.get(formId)==""){
		var formstr = '<input  name="properties" id="iproperties" type="hidden"/>'+
					  '<input  name="isExport" id="isExport" type="hidden"/>'+
					  '<input  name="ids" id="ids" type="hidden"/>'+
					  '<input  name="sort" id="sort" type="hidden"/>'+
					  '<input  name="order" id="order" type="hidden"/>'+
					  '<input  name="isback" id="isback" type="hidden"/>'+
					  '<input  name="fileName" value="'+fileName+'" id="fileName" type="hidden"/>'+
					  '<input  name="titles" id="titles" type="hidden"/>';
		
		$('#'+formId).append(formstr);
		map.put(formId,formstr);
	}
}