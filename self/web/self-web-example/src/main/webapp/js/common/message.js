/**
 * 消息服务
 */
$(function() {
//	msgTimer();
});

/**
 * 获取当前用户的消息
 */
function msgTimer(){
	setInterval(checkMsgByCurrentUser,3000);
}
function checkMsgByCurrentUser(){
	//清空数据
	$.ajax({
		url:"system/msgRel/getMsgRelByUser.do",
		type:"POST",
		dataType:"JSON",
		success:function(data){
			var msgNewCount = data.result.length;
			//改变图标
			//console.log(msgNewCount);
			
			changeImg(msgNewCount);
		}
	});
}

/**
 * 根据消息数量改变图标
 * @param msgNewCount
 */
function changeImg(msgNewCount){
	$("b[type=msgnum]").html(msgNewCount);
	if(parseInt(msgNewCount)>0){
		$("b[type=msgnum]").attr("class","tool-flicker");
	}else{
		$("b[type=msgnum]").attr("class","");
	}
}

