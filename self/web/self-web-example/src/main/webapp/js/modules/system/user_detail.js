/**
 * 机房编辑页面
 */
$(function(){
	/**
	 * 根据窗口类型初始化页面
	 */
	var userId = $.trim($("#userId").val());

	//加载用户信息
	if (userId != "null" && userId != "" && typeof(userId)!="undefined") {
		loadUser();
	}
	/**
	 * 加载机房信息
	 */
	function loadUser(){
		
		$.getJSON("system/user/getEntity.do",{'id':userId}, function(data){
			$('#userDetailFrom').form('myLoad',data.result);
			console.log(data.result);
			var userType =  data.result.userType;
			if(userType!="2"){
				$("#isMangertrue").attr("checked","checked");
			}else{
				$("#isMangerfalse").attr("checked","checked");
			}
			
		});
		
		//把页面元素设置为不可编辑
		$("#userDetailFrom").find("input").each(function(){
			$(this).attr("disabled","disabled");
		});
		$("#userDetailFrom").find("textarea").each(function(){
			$(this).attr("disabled","disabled");
		});
	}
})
