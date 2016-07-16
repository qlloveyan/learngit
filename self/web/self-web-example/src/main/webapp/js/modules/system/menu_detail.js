/**
 * 机房编辑页面
 */
$(function(){
	
	init();
	/**
	 * 根据窗口类型初始化页面
	 */
	var menuId = $.trim($("#menuId").val());

	//加载机房信息
	if (menuId != "null" && menuId != "" && typeof(menuId)!="undefined") {
		loadMenu();
	}
	/**
	 * 加载机房信息
	 */
	function loadMenu(){
		$.getJSON("system/funcModule/getEntity.do",{'id':menuId}, function(data){
			$('#menuDetailFrom').form('load',data.result);
			
			if(data.success){
				var funcType = getDictionaryByValue("FUNC_TYPE",data.result.funcType);
				$('#funcType').val(funcType);
			}
		});
		//把页面元素设置为不可编辑
		$("#menuDetailFrom").find("input").each(function(){
			$(this).attr("disabled","disabled");
		})
	}
})


/**
 * 初始化页面数据
 */
function init(){
	//初始化模块上级
	$("input[name='parentId']").combotree({
		 //url:'system/funcModule/listAllModuleToCombox.do'
		url:'system/funcModule/listModuleTree.do'
	});
}
