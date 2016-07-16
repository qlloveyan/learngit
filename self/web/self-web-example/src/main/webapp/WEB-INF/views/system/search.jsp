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
	<title>机房信息</title>
    <jsp:include page="../../../public/common2.jsp" />
	<script type="text/javascript" src="js/modules/system/search.js"></script>
   	
</head>
<body class="easyui-layout" fit="true">

<!-- 	<div region="west" border="false"  title="页面元素管理" class="cs-west" style="width: 270px;"> -->
<!-- 		<ul id="elementTree" class="easyui-tree" ></ul> -->
<!-- 	</div> -->
	<div region="west" class="listLeft-max">
    	<h1 class="tree-h1"><b class="list-tree-ico"></b>页面元素管理</h1>
		<ul id="elementTree" class="easyui-tree jstree" ></ul>
	</div>
	<div data-options="region:'center'">
		<div class="easyui-layout" fit="true">
			<div region="north"  style="height: 116px;">
				<div class="search-left" >
					<h1 class="search-title easyui-panel" title="菜单基本信息"></h1>
					<form id="elementFrom" method="post" >
						<input type="hidden" name="id">
						<div class="search-form">
							<table>
					    		<tr>
									<td style="width: 200px;text-align: right;"><span><label class="label4">菜单名称:</label><input class="input-small" name="funcName" /></span></td>
									<td style="width: 260px;text-align: right;"><span><label class="label4">菜单编码: </label><input class="input-small" name="funcCode"/></span></td>
									<td style="padding-left: 105px;"><span><label class="label4">菜单URL: </label><input class="input-small" name="url" style="width: 400px;"/></span></td>
								</tr>
								<tr>
									<td style="width: 200px;text-align: right;"><span><label class="label4">图标: </label><input class="input-small" name="icon"/></span></td>
									<td style="width: 260px;text-align: right;"><span><label class="label4">序号: </label><input class="input-small" name="funcSort"/></span></td>
								</tr>
							</table>
						</div>
					</form>
				</div>
			</div>
			<div region="center">
				<table id="elementGrid"></table>
			</div>
		</div>
	</div>
	<div id="mm" class="easyui-menu" style="width:120px;">  
     
    	<div iconCls="icon-add" onclick="addElement()">新增页面元素</div>  
    	<div class="menu-sep"></div>
    	<div onclick="collapse()">收缩</div> 
    	<div onclick="expand()">展开</div> 
    	<div >退出</div> 
	</div>  
	
</body>
</html>
