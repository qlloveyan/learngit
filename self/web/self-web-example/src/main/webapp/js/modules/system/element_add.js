/**
 * 机房编辑页面
 */
$(function(){
	
	var funcId = $.trim($("#funcId").val());
	//编辑，新增机房信息，或修改机房信息
	$("#elementAdd").click(function() {
		var url = "";
		var winId= "";
		$("#elementAddFrom").form('submit',{  
			url:'system/element/addEntity.do',
			onSubmit:function(){  
				return $(this).form('validate'); 
			},  
			success:function(data){ 
				var result = $.parseJSON(data);
				if(!result.success){
					$.messager.alert('提示',result.msg);  
				}
				getMainFrameWindow().loadElementDg(funcId); 
				closeWin("addElementWin");
			}  
		});  
	});
	
	//编辑，新增机房信息，或修改机房信息
	$("#elementCancel").click(function() {
		closeWin("addElementWin");
	});
	
	
})

