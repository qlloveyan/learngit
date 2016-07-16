/**
 * 机房编辑页面
 */
$(function(){

	init();
	
	/**
	 * 根据窗口类型初始化页面
	 */
	var dictionaryId = $("#dictionaryId").val().trim();
	//加载机房信息
	if (dictionaryId != "null" && dictionaryId != "" && typeof(dictionaryId)!="undefined") {
		loadDictionary();
	}
	//取消
	$("#dictionaryCancel").click(function() {
		closeWin('editDictionaryWin');
	});
	//编辑，新增机房信息，或修改机房信息
	$("#dictionaryEdit").click(function() {
		$("#dictionaryEditFrom").form('submit',{  
			url:'system/dictionary/editEntity.do',
			onSubmit:function(){  
				return $(this).form('validate'); 
			},  
			success:function(data){  
				window.frames["f_Dictionary"].window.searchDictionary(); 
				closeWin('editDictionaryWin');
			}  
		});  
			
	});
	
	/**
	 * 加载机房信息
	 */
	function loadDictionary(){
		$.getJSON("system/dictionary/getEntity.do",{'id':dictionaryId}, function(data){
			$('#dictionaryEditFrom').form('load',data.result);
		});
	}
})

/**
 * 初始化页面数据
 */
function init(){
	
	loadComboboxData('isOperate',DICTIONARY_IS_OPERATE);
}
