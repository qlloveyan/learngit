/**
 * 机房编辑页面
 */
$(function(){

	//验证同一模块同一类下是否存在同一个方法
	$.extend($.fn.validatebox.defaults.rules, {
		//方法说明必须在存在方法的情况下填写
		hasParam : {
			validator : function(value, param) {
				if( value != "" && $("#paramName").val() == "" ){
					return false;
				}
				return true;
			},
			message : '必须填写参数之后才能填写参数说明!'
		}
	});
	
	/**
	 * 根据窗口类型初始化页面
	 */
	var ctrlMethodId = $.trim($("#ctrlMethodId").val());
	//加载机房信息
	if (ctrlMethodId != "null" && ctrlMethodId != "" && typeof(ctrlMethodId)!="undefined") {
		loadCtrlMethod();
	}
	//取消
	$("#ctrlMethodCancel").click(function() {
		closeWin('editCtrlMethodWin');
	});
	//编辑，新增机房信息，或修改机房信息
	$("#ctrlMethodEidt").click(function() {
		$("#ctrlMethodEditFrom").form('submit',{  
			url:'system/ctrlMethod/editEntity.do',
			onSubmit:function(){  
				return $(this).form('validate'); 
			},  
			success:function(data){  
			
				var result = $.parseJSON(data);
				if(!result.success){
					$.messager.alert('提示',result.msg);  
				}else{
					getMainFrameWindow().searchCtrlMethod(); 
					closeWin('editCtrlMethodWin');
				}
			}  
		});  
			
	});
	
	/**
	 * 加载机房信息
	 */
	function loadCtrlMethod(){
		$.getJSON("system/ctrlMethod/getEntity.do",{'id':ctrlMethodId}, function(data){
			$('#ctrlMethodEditFrom').form('load',data.result);
		});
	}
})
