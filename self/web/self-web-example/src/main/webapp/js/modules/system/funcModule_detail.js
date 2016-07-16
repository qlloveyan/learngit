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
	/**
	 * 加载机房信息
	 */
	function loadFuncModule(){
		$.getJSON("system/funcModule/getEntity.do",{'id':funcModuleId}, function(data){
			$('#funcModuleDetailFrom').form('load',data.result);
			
			if(data.success){
				var funcType = getDictionaryByValue("FUNC_TYPE",data.result.funcType);
				$('#funcType').val(funcType);
			}
		});
		//把页面元素设置为不可编辑
		$("#funcModuleDetailFrom").find("input").each(function(){
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
		 url:'system/funcModule/listAllModuleToCombox.do'
	});
}
