/**
 * 机房编辑页面
 */
$(function(){

	init();
	/**
	 * 根据窗口类型初始化页面
	 */
	var elementId = $.trim($("#elementId").val());
	//加载机房信息
	if (elementId != "null" && elementId != "" && typeof(elementId)!="undefined") {
		loadElement();
	}
	//取消
	$("#elementCancel").click(function() {
		closeWin('editElementWin');
	});
	//编辑，新增机房信息，或修改机房信息
	$("#elementEidt").click(function() {
		$("#elementEditFrom").form('submit',{  
			url:'system/element/editEntity.do',
			onSubmit:function(){  
				return $(this).form('validate'); 
			},  
			success:function(data){  
				
				var result = $.parseJSON(data);
				if(!result.success){
					$.messager.alert('提示',result.msg);  
				}
				getMainFrameWindow().searchElement(); 
				closeWin('editElementWin');
			}  
		});  
			
	});
	
	/**
	 * 加载机房信息
	 */
	function loadElement(){
		$.getJSON("system/element/getEntity.do",{'id':elementId}, function(data){
			$('#elementEditFrom').form('load',data.result);
		});
	}
})


/**
 * 初始化页面数据
 */
function init(){
	//初始化模块上级
	$("input[name='funcId']").combotree({
		 url:'system/funcModule/listAllModuleToCombox.do'
	});
}