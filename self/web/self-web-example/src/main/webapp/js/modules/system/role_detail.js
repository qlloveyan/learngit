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
		loadRole();
	}
	/**
	 * 加载机房信息
	 */
	function loadRole(){
		$.getJSON("system/role/getEntity.do",{'id':roleId}, function(data){
			$('#roleDetailFrom').form('load',data.result);
			loadroles();
		});
		//把页面元素设置为不可编辑
		$("#roleDetailFrom").find("input").each(function(){
			$(this).attr("disabled","disabled");
		})
	}
})

function loadroles(){
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