<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path+"/";
%>
<!-- import -->
<%@page import="java.util.*"%>
<%@page import="com.ql.basepro.system.model.User"%>
<!-- jsp标签 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 作用域变量 -->
<c:set var="ctx" value="<%=basePath %>" />
<c:set var="resourceRoot" value="<%=basePath %>" />
<!-- 以下为HTML document -->
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="content-script-type" content="text/javascript">  

<!--****************************************第三方资源引入 start，请注意引入顺序****************************************-->
<!-- jQuery -->
<script type="text/javascript" src="${ctx }/js/static/jquery/jquery-1.9.1.min.js"></script>
<!-- bootstrap -->
<script type="text/javascript" src="${ctx}/js/static/bootstrap/2.3.2/js/bootstrap.min.js" ></script>
<!-- jQuery easyUI -->
<script type="text/javascript" src="${ctx }/js/static/jquery/easyui/jquery.easyui.min.js"></script>
<link href="${ctx }/js/static/jquery/easyui/easyui.css" rel="stylesheet" type="text/css" />
<!-- jQuery easyUI datagrid datailview-->
<script type="text/javascript" src="${ctx }/js/static/jquery/easyui/jquery.easyui.datagrid-detailview.js"></script>
<!-- jQuery easyUI datagrid groupview-->
<script type="text/javascript" src="${ctx }/js/static/jquery/easyui/jquery.easyui.datagrid-groupview.js"></script>
<script type="text/javascript" src="${ctx }/js/static/jquery/easyui/easyui-lang-zh_CN.js"></script>
<!-- 系统css -->
<link type="text/css" media="screen"  href="${ctx}/js/static/bootstrap/2.3.2/css/bootstrap.min.css" rel="stylesheet"/>
<link type="text/css" media="screen"  href="${ctx}/js/static/bootstrap/2.3.2/css/bootstrap-responsive.min.css" rel="stylesheet"/>
<link type="text/css" media="screen"  href="${ctx}/js/static/bootstrap/2.3.2/css/style.css" rel="stylesheet"/>
<!-- easyui css -->
<!--****************************************第三方资源引入 end，请注意引入顺序****************************************-->
<!-- 系统图片 -->
<link type="image/x-icon" href="${ctx}/js/static/images/favicon.ico" rel="shortcut icon">
<!--****************************************JavaScript引入 start，请注意引入顺序****************************************-->
<!-- Surfilter.js库文件 -->
<script type="text/javascript" src="${ctx }/js/static/common/Surfilter.js"></script>
<!-- 自定义jQuery扩展 -->
<script type="text/javascript" src="${resourceRoot }/js/static/common/Surfilter.jquery.ex.js"></script>
<!-- 自定义jQuery easyui扩展 -->
<script type="text/javascript" src="${resourceRoot }/js/static/common/Surfilter.easyui.ex.js"></script>
<!-- JavaScript原型扩展 -->
<script type="text/javascript" src="${resourceRoot }/js/static/common/Surfilter.javascript.ex.js"></script>
<script type="text/javascript" src="${ctx }/js/static/common/listModules.js"></script>
<!------------------------入口模块------------------------->
<script type="text/javascript" src="${ctx }/js/static/common/main.js"></script>

<!-- 引入公共文件 -->
<script type="text/javascript" src="${ctx }/js/common/validate.js"></script>
<script type="text/javascript" src="${ctx }/js/common/constants.js"></script>
<script type="text/javascript" src="${ctx }/js/common/utils.js"></script>
<link type="text/css" media="screen"  href="${ctx}/css/style.css" rel="stylesheet"/>

<link rel="stylesheet" type="text/css" href="${ctx }/js/plugs/poshytip/tip-yellowsimple/tip-yellowsimple.css"/>
<script type="text/javascript" src="${ctx }/js/plugs/poshytip/jquery.poshytip.min.js"></script>
<!--****************************************JavaScript引入 end****************************************-->
<!-- 引日messageBox.jsp -->
<%@include file="/js/static/common/messageBox.jsp" %>
<!--系统初始化javascript代码块start-->
<script type="text/javascript">
	var ctx = '${ctx }';
	var resourceRoot = '${resourceRoot }';
	var nowServerTime = <%=new Date().getTime() %>;
	
	//encache userInfo
	<%User currentUser = (User)session.getAttribute("UserSession");if(currentUser == null){currentUser = new User();}%>
	/* Surfilter.currentUser =  */
<%-- 	{
		userId : <%= currentUser.getId()%>,
		isAdmin : '<%=UserAction.isAdmin(currentUser)%>',
		loginName : '<%=currentUser.getLoginName()%>',
		provinceId : <%=currentUser.getProvinceId()%>,
		cityId : <%=currentUser.getCityId()%>,
	}; --%>
</script>
