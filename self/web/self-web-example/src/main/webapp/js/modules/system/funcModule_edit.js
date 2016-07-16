/**
 * 机房编辑页面
 */
$(function(){

	init();
	/**
	 * 根据窗口类型初始化页面
	 */
	var funcModuleId = $.trim($("#funcModuleId").val());
	//加载机房信息
	if (funcModuleId != "null" && funcModuleId != "" && typeof(funcModuleId)!="undefined") {
		loadFuncModule();
	}
	//取消
	$("#funcModuleCancel").click(function() {
		closeWin('editFuncModuleWin');
	});
	//编辑，新增机房信息，或修改机房信息
	$("#funcModuleEidt").click(function() {
		$("#funcModuleEditFrom").form('submit',{  
			url:'system/funcModule/editEntity.do',
			onSubmit:function(){  
				return $(this).form('validate'); 
			},  
			success:function(data){  
				
				var result = $.parseJSON(data);
				if(!result.success){
					$.messager.alert('提示',result.msg);  
				}
				getMainFrameWindow().searchFuncModule(); 
				closeWin('editFuncModuleWin');
			}  
		});  
			
	});
	
	/**
	 * 加载机房信息
	 */
	function loadFuncModule(){
		$.getJSON("system/funcModule/getEntity.do",{'id':funcModuleId}, function(data){
			$('#funcModuleEditFrom').form('load',data.result);
		});
	}
})


/**
 * 初始化页面数据
 */
function init(){
	//初始化模块上级
	$("input[name='parentId']").combotree({
		 url:'system/funcModule/listAllModuleToCombox.do'
	});
}