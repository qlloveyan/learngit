/**
 * 机房编辑页面
 */
$(function(){
	//验证同一模块同一类下是否存在同一个方法
	$.extend($.fn.validatebox.defaults.rules, {
		//不为空
		equalMethod : {
			validator : function(value, param) {
				var module = $("#module").val();
				var className = $("#className").val();
				var flag = true;
				if( module != "" && className != "" ){
					$.ajax({
						type: "POST",
						url: "system/ctrlMethod/equalMethod.do",
						data: "module="+module +"&className="+className+"&methodName="+value,
						async: false,
						success: function(msg){
							msg = eval("("+msg+")");
							if( msg.success ){
								flag = false;
							}
						}
					});
				}
				
				return flag;
			},
			message : '同一模块的同一控制类中,不能存在同样的方法!'
		},
		//方法说明必须在存在方法的情况下填写
		hasParam : {
			validator : function(value, param) {
				if( value != "" && $(param[0]).val() == "" ){
					return false;
				}
				return true;
			},
			message : '必须填写参数之后才能填写参数说明!'
		}
	});
	
	//编辑，新增机房信息，或修改机房信息
	$("#ctrlMethodAdd").click(function() {
		var url = "";
		var winId= "";
		$("#ctrlMethodAddFrom").form('submit',{  
			url:'system/ctrlMethod/addEntity.do',
			onSubmit:function(){  
				return $(this).form('validate'); 
			},  
			success:function(data){ 
			
				var result = $.parseJSON(data);
				if(!result.success){
					$.messager.alert('提示',result.msg);  
				}else{
					getMainFrameWindow().searchCtrlMethod(); 
					closeWin("addCtrlMethodWin");
				}
			}  
		});  
	});
	
	//编辑，新增机房信息，或修改机房信息
	$("#ctrlMethodCancel").click(function() {
		closeWin("addCtrlMethodWin");
	});
})
