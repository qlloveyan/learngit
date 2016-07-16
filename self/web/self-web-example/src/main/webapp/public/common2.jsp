<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!--
<link rel="stylesheet" id="linktheme" type="text/css" href="js/easyui/themes/rzx-ui/easyui.css">
 -->
<link rel="stylesheet" id="linktheme" type="text/css" href="js/sysui/css/<%=request.getSession().getAttribute("layout") %>/<%=request.getSession().getAttribute("skin") %>/easyui.css"> 
<link rel="stylesheet" id="linkskin" type="text/css" href="js/sysui/css/<%=request.getSession().getAttribute("layout") %>/<%=request.getSession().getAttribute("skin") %>/comm.css">
<link type="text/css" rel="stylesheet" href="js/sysui/css/<%=request.getSession().getAttribute("layout") %>/<%=request.getSession().getAttribute("skin") %>/style.css"> 
<link rel="stylesheet" id="linkskin" type="text/css" href="js/easyui/themes/rzx-ui/scrollbar.css">
<!-- 皮肤设置 -->
<link rel="stylesheet" type="text/css" href="resources/css/global-style.css">
<link rel="stylesheet" type="text/css" href="resources/css/secord.css">
<link rel="stylesheet" type="text/css" href="resources/css/index.css">
<link rel="stylesheet" type="text/css" href="resources/css/isotope.css">
<link rel="stylesheet" type="text/css" href="js/plugs/poshytip/tip-yellowsimple/tip-yellowsimple.css"/>

<script type="text/javascript" src="js/easyui/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/plugs/rzx-js/scrollbar.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.json-2.4.min.js"></script> 
<script type="text/javascript" src="js/plugs/rzx-js/jquery-rzx.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/plugs/My97DatePicker/WdatePicker.js"></script>


<script type="text/javascript" src="js/plugs/poshytip/jquery.poshytip.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/common/constants.js"></script>
<script type="text/javascript" src="js/common/common.js"></script>
<script type="text/javascript" src="js/common/utils.js"></script>
<script type="text/javascript" src="js/common/validate.js"></script>
<script type="text/javascript" src="js/easyui/myload.js"></script>
<script type="text/javascript" src="js/easyui/jquery.window.js"></script>

<script type="text/javascript" src="js/common/property.js"></script>
<script type="text/javascript" src="js/common/mask.js"></script>
