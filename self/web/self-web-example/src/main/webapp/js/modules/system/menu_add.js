/**
 * 机房编辑页面
 */
$(function(){
	
	var parentId = $.trim($("#parentId").val());
	//编辑，新增机房信息，或修改机房信息
	$("#menuAdd").click(function() {
		var url = "";
		var winId= "";
		$("#menuAddFrom").form('submit',{  
			url:'system/funcModule/addEntity.do',
			onSubmit:function(){  
				return $(this).form('validate'); 
			},  
			success:function(data){ 
				var result = $.parseJSON(data);
				if(!result.success){
					$.messager.alert('提示',result.msg);  
				}
				getMainFrameWindow().reloadMenu(parentId); 
				closeWin("addMenuWin");
			}  
		});  
	});
	
	//编辑，新增机房信息，或修改机房信息
	$("#menuCancel").click(function() {
		closeWin("addMenuWin");
	});
	
	
})

