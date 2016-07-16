/**
 * 机房编辑页面
 */
$(function(){
	//编辑，新增机房信息，或修改机房信息
	$("#testAdd").click(function() {
		var url = "";
		var winId= "";
		$("#testAddFrom").form('submit',{  
			url:'test/test/addEntity.do',
			onSubmit:function(){  
				return $(this).form('validate'); 
			},  
			success:function(data){ 
			
				var result = $.parseJSON(data);
				if(!result.success){
					$.messager.alert('提示',result.msg);  
				}else{
					getMainFrameWindow().searchTest(); 
					closeWin("addTestWin");
				}
			}  
		});  
	});
	
	//编辑，新增机房信息，或修改机房信息
	$("#testCancel").click(function() {
		closeWin("addTestWin");
	});
})
