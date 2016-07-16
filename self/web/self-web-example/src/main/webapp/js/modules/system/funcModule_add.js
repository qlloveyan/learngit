/**
 * 机房编辑页面
 */
$(function(){
	
	var parentId = $.trim($("#parentId").val());
	//编辑，新增机房信息，或修改机房信息
	$("#funcModuleAdd").click(function() {
		var url = "";
		var winId= "";
		$("#funcModuleAddFrom").form('submit',{  
			url:'system/funcModule/addEntity.do',
			onSubmit:function(){  
				return $(this).form('validate'); 
			},  
			success:function(data){ 
				var result = $.parseJSON(data);
				if(!result.success){
					$.messager.alert('提示',result.msg);  
				}
				getMainFrameWindow().loadFuncModuleDg(parentId); 
				closeWin("addFuncModuleWin");
			}  
		});  
	});
	
	//编辑，新增机房信息，或修改机房信息
	$("#funcModuleCancel").click(function() {
		closeWin("addFuncModuleWin");
	});
	
	
})

