/**
 * 机房编辑页面
 */
$(function(){
	//编辑，新增机房信息，或修改机房信息
	$("#accessLogAdd").click(function() {
		var url = "";
		var winId= "";
		$("#accessLogAddFrom").form('submit',{  
			url:'system/accessLog/addEntity.do',
			onSubmit:function(){  
				return $(this).form('validate'); 
			},  
			success:function(data){ 
			
				var result = $.parseJSON(data);
				if(!result.success){
					$.messager.alert('提示',result.msg);  
				}else{
					getMainFrameWindow().searchAccessLog(); 
					closeWin("addAccessLogWin");
				}
			}  
		});  
	});
	
	//编辑，新增机房信息，或修改机房信息
	$("#accessLogCancel").click(function() {
		closeWin("addAccessLogWin");
	});
})
