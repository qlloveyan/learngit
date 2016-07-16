<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Cache-Control" CONTENT="no-cache">
	<title>互联网资源首页</title>
    <jsp:include page="../../public/common2.jsp" />
   	
</head>
<body class="easyui-layout layout welcome">
<!-- 
<div class="welcome-home" region="center">
  <div class="welcome-logo">
    <span></span>
  </div>
  
  <div class="welcome-bg">
  </div>
</div>
 -->
 <div class="welcome2" style="background: url(js/easyui/themes/rzx-ui/images/welcome2_<%=request.getSession().getServletContext().getInitParameter("smcsArea") %>.jpg) no-repeat center;" >
 
 </div>
</body>
</html>
