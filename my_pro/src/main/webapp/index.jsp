<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
	<base href="<%=basePath%>">
	<title>个人小工具界面</title>
	<script type="text/javascript">
		function shutdown(){
			if( window.confirm("您确定要关机吗?") ){
				window.location.href="ShutDown";
			}else{
				return false;
			}
		}
		
		function shutdown(){
			if( window.confirm("您确定要重启吗?") ){
				window.location.href="Restart";
			}else{
				return false;
			}
		}
	</script>
</head>
<body>
	<a href="javascript:void(0)" onclick="shutdown()">立即关机</a></br>
	<a href="javascript:void(0)" onclick="restart()">重启</a></br></br>
	<a href="angularJs_base.html" target="_blank">angularJs基础</a>
	<a href="angularJs_obj.html" target="_blank">angularJs对象</a>
	<a href="angularJs_exp.html" target="_blank">angularJs示例</a>
</body>
</html>
