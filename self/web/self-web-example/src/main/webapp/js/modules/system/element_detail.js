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
	/**
	 * 加载机房信息
	 */
	function loadElement(){
		$.getJSON("system/element/getEntity.do",{'id':elementId}, function(data){
			$('#elementDetailFrom').form('load',data.result);
			
			if(data.success){
				var funcType = getDictionaryByValue("FUNC_TYPE",data.result.funcType);
				$('#funcType').val(funcType);
			}
		});
		//把页面元素设置为不可编辑
		$("#elementDetailFrom").find("input").each(function(){
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
		 url:'system/element/listAllModuleToCombox.do'
	});
}
