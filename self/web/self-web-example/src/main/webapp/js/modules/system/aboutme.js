$(function(){

		$.ajax({
			type: "POST",
			url: "system/aboutMe/getaboutMeMsg.do",
			success: function(data){
				var json = eval('(' + data + ')');
				$("#version").html(json.version);
				$("#buildid").html(json.buildid);
			}
		});
})
