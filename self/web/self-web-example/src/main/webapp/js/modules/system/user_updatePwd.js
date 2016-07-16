/**
 * 机房编辑页面
 */
$(function(){
	
	//隐藏右上角关闭图标
	//$(".panel-tool").hide();
	
	$.extend($.fn.validatebox.defaults.rules, {
		//数字、字母和特殊字符
		comlpePass : {
			validator : function(value, param) {
				var flag = false;
				var re = new RegExp("[a-zA-Z]");
				var len=re.test(value);
				if( len ){
					re = new RegExp("[0-9]");
					len=re.test(value);
					if(len){
						re = new RegExp("((?=[x21-x7e]+)[^A-Za-z0-9])");
						len=re.test(value);
						if(len && value.length >= 8 && value.length <= 30)
							flag = true;
					}
				}
				return flag;
			},
			message : '必须输入由数字、字母和特殊字符(@ ^ _ = < >)组成的8-30位字符!'
		},
	});

	//取消
	$("#userPwdCancel").click(function() {
		var pasUpdate = $("#pasUpdate").val();
		if( pasUpdate == 1 ){
			closeWin('editUserPwdWin');
		}else if( pasUpdate == 2 ){
			$.messager.alert('友情提示','请更新初始密码');
		}else if( pasUpdate == 3 ){
			$.messager.alert('友情提示','密码已过期,请更新密码');
		}
	});
	//编辑，新增机房信息，或修改机房信息
	$("#userPwdEidt").click(function() {
		
		var new_pwd = $('#newPw').val();
		var pwd_again = $('#pwdAgain').val();
		
		if(new_pwd!=pwd_again){
			$.messager.alert('友情提示','更新密码应与确认密码一致');
			return;
		}
		$("#userPwdEditFrom").form('submit',{  
			url:'system/user/updatePassWord.do',
			onSubmit:function(){  
				return $(this).form('validate'); 
			},  
			success:function(data){ 
				var res = $.parseJSON(data);
				if(!res.success){
					$.messager.alert("温馨提示",res.msg);
				}else{
					$.messager.show({title:"温馨提示",msg:"密码修改成功!"});
				}
				closeWin('editUserPwdWin');
			}  
		});  
			
	});
	
})


