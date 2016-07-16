/**
 * 机房编辑页面
 */
$(function(){
	/**
	 * 根据窗口类型初始化页面
	 */
	var msgId = $("#msgId").val().trim();

	//加载机房信息
	if (msgId != "null" && msgId != "" && typeof(msgId)!="undefined") {
		loadMsg();
	}
	/**
	 * 加载机房信息
	 */
	function loadMsg(){
		$.getJSON("system/msg/getEntity.do",{'id':msgId}, function(data){
			$('#msgDetailFrom').form('load',data.result);
//			if(data.success){
//				var description = getDictionaryByValue("PARAM_GROUP_TYPE",data.result.paramGroupType);
//				$('#paramGroupType').val(description);
//			}
			//将发送者信息进行初始化
			$.getJSON("system/user/getEntity.do",{'id':data.result.createUser}, function(user){
				$("#createUser").val(user.result.userName);
			});
		});
//		//把页面元素设置为不可编辑
		$("#msgDetailFrom").find("input").each(function(){
			$(this).attr("disabled","disabled");
		})
	}
})


