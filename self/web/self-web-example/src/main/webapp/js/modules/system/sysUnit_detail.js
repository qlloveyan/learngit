/**
 * 机房编辑页面
 */
$(function(){
	
	init();
	/**
	 * 根据窗口类型初始化页面
	 */
	var sysUnitId = $("#sysUnitId").val().trim();

	//加载机房信息
	if (sysUnitId != "null" && sysUnitId != "" && typeof(sysUnitId)!="undefined") {
		loadSysUnit();
	}
	/**
	 * 加载机房信息
	 */
	function loadSysUnit(){
		$.getJSON("system/sysUnit/getEntity.do",{'id':sysUnitId}, function(data){
			$('#sysUnitDetailFrom').form('load',data.result);
		});
		//把页面元素设置为不可编辑
		$("#sysUnitDetailFrom").find("input").each(function(){
			$(this).attr("disabled","disabled");
		})
	}
})


function init(){
	
	// loadComboboxData('unitType',DICTIONARY_UNIT_TYPE);
	// loadComboboxData('unitLevel',DICTIONARY_UNIT_LEVEL);
	//初始化模块上级
	$("input[name='parentId']").combotree({
		 url:'system/sysUnit/listAllSysUnit.do'
	});
}