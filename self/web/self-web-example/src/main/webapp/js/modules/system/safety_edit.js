/**
 * 机房编辑页面
 */
$(function(){

	/**
	 * 根据窗口类型初始化页面
	 */
	var safetyId = $.trim($("#safetyId").val());
	//加载机房信息
	if (safetyId != "null" && safetyId != "" && typeof(safetyId)!="undefined") {
		loadSafety();
	}
	//取消
	$("#safetyCancel").click(function() {
		alert("成功执行!");
		closeWin('editSafetyWin');
		alert("成功执行!");
	});
	//编辑，新增机房信息，或修改机房信息
	$("#safetyEidt").click(function() {
		$("#safetyEditFrom").form('submit',{  
			url:'system/safety/editEntity.do',
			onSubmit:function(){  
				return $(this).form('validate'); 
			},  
			success:function(data){  
			
				var result = $.parseJSON(data);
				if(!result.success){
					$.messager.alert('提示',result.msg);  
				} 
				window.frames["f_Safety"].window.searchSafety(); 
				closeWin('editSafetyWin');
			}  
		});  
			
	});
	
	/**
	 * 加载机房信息
	 */
	function loadSafety(){
		$.getJSON("system/safety/getEntityByUserId.do",{'id':safetyId}, function(data){
			$('#safetyEditFrom').form('load',data.result);
		});
	}
})
