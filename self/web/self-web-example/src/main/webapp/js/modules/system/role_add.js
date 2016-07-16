/**
 * 机房编辑页面
 */
$(function(){
	loadroles();
	//编辑，新增机房信息，或修改机房信息
	$("#roleAdd").click(function() {
		var url = "";
		var winId= "";
		$("#roleAddFrom").form('submit',{  
			url:'system/role/addEntity.do',
			onSubmit:function(){  
				return $(this).form('validate'); 
			},  
			success:function(data){  
				getMainFrameWindow().searchRole(); 
				closeWin("addRoleWin");
			}  
		});  
	});
	
	//编辑，新增机房信息，或修改机房信息
	$("#roleCancel").click(function() {
		closeWin("addRoleWin");
	});
})

//function loadroles(){
//	$('#parentIds').combobox({  
//	    url:'system/user/getRolesByUser.do',  
//	    valueField:'id',  
//	    textField:'roleName',
//	    separator:'|',
//	    multiple:true
//	}); 
//}


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
			}
		}
	});
}
