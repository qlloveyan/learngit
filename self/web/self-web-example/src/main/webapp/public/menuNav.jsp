<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单导航路径</title>
<script type="text/javascript">
$(function() {
	
	if(PROVINCE_AREA_CODE != '110000'){
		// 获取当前的菜单路径，只显示的是配置到数据库中的菜单路径
		var iframes = $('iframe', parent.document);
		
		if(iframes){
			var iframe = iframes[0];
			var iframeName = $(iframe).attr('id');
			if(iframeName){
				var iframeNametemp = iframeName.substring(2);
				$.ajax({
					url: 'system/funcModule/getCurrentPath.do',
					data: {
						'funcModule': iframeNametemp
					},
					type: 'POST',
					dataType: 'json',
					success: function(data){
						$('#currentPath').empty();
						if(data && data.length>0){
							var currentPath = '';
							for(var i=data.length-1;i>=0;i--){
								if(data[i].url !=null && data[i].url!=""){
									//'+data[i].url+','+'f_'+data[i].funcCode+'
									currentPath += "/ " +"<a style='cursor: pointer;' type='atopage' url='"+data[i].url+"' frameName='"+iframeName+"' >"+data[i].funcName+"<a/>";
								}else{
									currentPath += '/ ' +data[i].funcName;
								}
							}
							currentPath = currentPath.substring(currentPath.indexOf("/")+1);
							currentPath = currentPath.substring(currentPath.indexOf("/")+1);
							$('#currentPath').html(currentPath);
						}
						
						$("a[type='atopage']").click(function(){
							window.parent.toPage($(this).attr("url"), $(this).attr("frameName"));
						});
						
					}
				});
			}
			
		}
	}
});

</script>
</head>
<body>
	<div id="currentPath"></div>
</body>
</html>
