/**
 * 机房编辑页面
 */
$(function(){
	
	init();
	//编辑，新增机房信息，或修改机房信息
	$("#dictionaryAdd").click(function() {
		var url = "";
		var winId= "";
		$("#dictionaryAddFrom").form('submit',{  
			url:'system/dictionary/addEntity.do',
			onSubmit:function(){  
				return $(this).form('validate'); 
			},  
			success:function(data){  
				window.frames["f_Dictionary"].window.searchDictionary(); 
				closeWin("addDictionaryWin");
			}  
		});  
	});
	
	//编辑，新增机房信息，或修改机房信息
	$("#dictionaryCancel").click(function() {
		closeWin("addDictionaryWin");
	});
	
})


/**
 * 初始化页面数据
 */
function init(){
	
	loadComboboxData('isOperate',DICTIONARY_IS_OPERATE);
}