/**
 * 机房编辑页面
 */
$(function(){

	/**
	 * 根据窗口类型初始化页面
	 */
	var roleId = $.trim($("#roleId").val());
	//加载机房信息
	if (roleId != "null" && roleId != "" && typeof(roleId)!="undefined") {
		loadRole(roleId);
	}
	
	//取消
	$("#roleCancel").click(function() {
		closeWin('editRoleWin');
	});
	//编辑，新增机房信息，或修改机房信息
	$("#roleEidt").click(function() {
		$("#roleEditFrom").form('submit',{  
			url:'system/role/editEntity.do',
			onSubmit:function(){  
				return $(this).form('validate'); 
			},  
			success:function(data){  
				console.log(data);
				getMainFrameWindow().searchRole(); 
				closeWin('editRoleWin');
			}  
		}); 
	});
	
});
/**
 * 加载机房信息
 */
function loadRole(roleId){
	$.getJSON("system/role/getEntity.do",{'id':roleId}, function(data){
		$('#roleEditFrom').form('load',data.result);
		$('#hparentIds').val(data.result.parentIds);
		loadroles(roleId);
	});
}


function loadroles(roleId){
	$("#parentIds").combobox({
	    valueField:'id',  
	    textField:'roleName',
	    separator:',',
	    multiple:true,
		editable : false
	});
	$.ajax({
		type:"POST",
		url:"system/user/getRolesByUser.do",
		data:{"id":roleId},
		async:false,
		dataType:'json',
		success:function(result){
			if(result!=null){
				var empty = {'id':"",'roleName':'空'}
				result.unshift(empty);
				$("#parentIds").combobox('loadData',result);
				$("#parentIds").combobox('setValues',$('#hparentIds').val().split(","));
			}
		}
	});
	
	
}
