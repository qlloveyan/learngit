/**
 * 机房编辑页面
 */
$(function(){

	/**
	 * 根据窗口类型初始化页面
	 */
	var accessLogId = $.trim($("#accessLogId").val());
	//加载机房信息
	if (accessLogId != "null" && accessLogId != "" && typeof(accessLogId)!="undefined") {
		loadAccessLog();
	}
	//取消
	$("#accessLogCancel").click(function() {
		closeWin('editAccessLogWin');
	});
	//编辑，新增机房信息，或修改机房信息
	$("#accessLogEidt").click(function() {
		$("#accessLogEditFrom").form('submit',{  
			url:'system/accessLog/editEntity.do',
			onSubmit:function(){  
				return $(this).form('validate'); 
			},  
			success:function(data){  
			
				var result = $.parseJSON(data);
				if(!result.success){
					$.messager.alert('提示',result.msg);  
				}else{
					getMainFrameWindow().searchAccessLog(); 
					closeWin('editAccessLogWin');
				}
			}  
		});  
			
	});
	
	/**
	 * 加载机房信息
	 */
	function loadAccessLog(){
		$.getJSON("system/accessLog/getEntity.do",{'id':accessLogId}, function(data){
			$('#accessLogEditFrom').form('load',data.result);
		});
	}
})
