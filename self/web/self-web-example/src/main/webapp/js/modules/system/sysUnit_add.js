/**
 * 机房编辑页面
 */
$(function(){
	
	var unitLevel = $("#unitLevel").val();
	var parentId = $.trim($("#parentId").val());
	
	//编辑，新增机房信息，或修改机房信息
	$("#sysUnitCancel").click(function() {
		closeWin("addSysUnitWin");
	});
	
	//编辑，新增机房信息，或修改机房信息
	$("#sysUnitAdd").click(function() {
		var url = "";
		var winId= "";
		//验证nativeid是否重复
		var nativeId = $("#sysUnitAddFrom").find("input[name='nativeId']").val();
		var parentId = $("input[name='parentId']").val();
		if(nativeId==""){
			 $.messager.alert('提示',"单位标识不能为空！");  
			return;
		}
		$.ajax({
			type:"POST",
			url:'system/sysUnit/checkNativeId.do',
			data:{nativeId:nativeId,parentId:parentId},
			dataType:"json",
			success:function(data){
				if(data.length>0){
					$.messager.alert('提示',"该类型单位标识号重复！");  
				}else{
					$("#sysUnitAddFrom").form('submit',{  
						url:'system/sysUnit/addEntity.do',
						onSubmit:function(){  
							return $(this).form('validate'); 
						},  
						success:function(data){  
							var pId = $.trim($("#parentId").val());
							getMainFrameWindow().reloadSysUnit(pId);
							closeWin("addSysUnitWin");
						}  
					});  
				}
				
			}
		});
	});
});