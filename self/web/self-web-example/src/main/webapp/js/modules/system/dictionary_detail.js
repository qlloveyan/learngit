/**
 * 机房编辑页面
 */
$(function(){
	/**
	 * 根据窗口类型初始化页面
	 */
	var dictionaryId = $("#dictionaryId").val().trim();

	//加载机房信息
	if (dictionaryId != "null" && dictionaryId != "" && typeof(dictionaryId)!="undefined") {
		loadDictionary();
	}
	/**
	 * 加载机房信息
	 */
	function loadDictionary(){
		$.getJSON("system/dictionary/getEntity.do",{'id':dictionaryId}, function(data){
			$('#dictionaryDetailFrom').form('load',data.result);
			var description = getDictionaryByValue("IS_OPERATE",data.result.isOperate);
			$('#isOperate').val(description);
		});
		//把页面元素设置为不可编辑
		//$(".easyui-linkbutton").remove();
		$("#dictionaryDetailFrom").find("input").each(function(){
			$(this).attr("disabled","disabled");
		})
	}
	
})
