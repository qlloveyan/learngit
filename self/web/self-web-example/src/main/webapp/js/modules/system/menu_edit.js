/**
 * 机房编辑页面
 */
$(function(){

	init();
	/**
	 * 根据窗口类型初始化页面
	 */
	var menuId = $.trim($("#menuId").val());
	var parentId = $.trim($("#parentId").val());
	
	console.log("parentId:" + parentId);
	//加载机房信息
	if (menuId != "null" && menuId != "" && typeof(menuId)!="undefined") {
		loadMenu();
	}
	//取消
	$("#menuCancel").click(function() {
		closeWin('editMenuWin');
	});
	//编辑，新增机房信息，或修改机房信息
	$("#menuEidt").click(function() {
		$("#menuEditFrom").form('submit',{  
			url:'system/funcModule/editEntity.do',
			onSubmit:function(){  
				return $(this).form('validate'); 
			},  
			success:function(data){  
				
				var result = $.parseJSON(data);
				if(!result.success){
					$.messager.alert('提示',result.msg);  
				}
				getMainFrameWindow().loadMenu(menuId); 
				
				if (parentId != "null" && parentId != "" && typeof(parentId)!="undefined") {
					menuId = parentId;
				}
				getMainFrameWindow().reloadMenu(menuId); 
				
				closeWin('editMenuWin');
			}  
		});  
			
	});
	
	/**
	 * 加载机房信息
	 */
	function loadMenu(){
		$.getJSON("system/funcModule/getEntity.do",{'id':menuId}, function(data){
			$('#menuEditFrom').form('load',data.result);
		});
	}
})


/**
 * 初始化页面数据
 */
function init(){
	//初始化模块上级
	$("input[name='parentId']").combotree({
		// url:'system/funcModule/listAllModuleToCombox.do'
		url:'system/funcModule/listModuleTree.do'
		
	});
}