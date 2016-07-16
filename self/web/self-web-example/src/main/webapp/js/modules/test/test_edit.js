/**
 * 机房编辑页面
 */
$(function(){

	/**
	 * 根据窗口类型初始化页面
	 */
	var testId = $.trim($("#testId").val());
	//加载机房信息
	if (testId != "null" && testId != "" && typeof(testId)!="undefined") {
		loadTest();
	}
	//取消
	$("#testCancel").click(function() {
		closeWin('editTestWin');
	});
	//编辑，新增机房信息，或修改机房信息
	$("#testEidt").click(function() {
		$("#testEditFrom").form('submit',{  
			url:'test/test/editEntity.do',
			onSubmit:function(){  
				return $(this).form('validate'); 
			},  
			success:function(data){  
			
				var result = $.parseJSON(data);
				if(!result.success){
					$.messager.alert('提示',result.msg);  
				}else{
					getMainFrameWindow().searchTest(); 
					closeWin('editTestWin');
				}
			}  
		});  
			
	});
	
	/**
	 * 加载机房信息
	 */
	function loadTest(){
		$.getJSON("test/test/getEntity.do",{'id':testId}, function(data){
			$('#testEditFrom').form('load',data.result);
		});
	}
})
