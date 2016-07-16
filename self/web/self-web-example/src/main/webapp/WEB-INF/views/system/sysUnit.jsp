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
	<title>单位信息</title>
    <jsp:include page="../../../public/common2.jsp" />
	<script type="text/javascript" src="js/modules/system/sysUnit.js"></script>
   	
</head>
<body class="easyui-layout" fit="true">
	<div region="west" class="listLeft-max">
    	<h1 class="tree-h1"><b class="list-tree-ico"></b>单位列表</h1>
		<ul id="sysUnitTree" class="easyui-tree jstree" ></ul>
	</div>
	 <!--  面包屑 -->
	<div data-options="region:'center'">
		<div class="easyui-layout" fit="true">
			<div region="north">
				<div class="search-left">
					<!-- <h1 class="search-title easyui-panel" title="单位基本信息"></h1> -->
					<form id="sysUnitFrom"method="post" >
						<input type="hidden" name="id">
						<div class="search-form">
							<span><label class="label4">单位名称:</label><input class="input-small" name="unitName" /></span>
							<span><label class="label4">单位标识号: </label><input class="input-small" name="nativeId"/></span>
						</div>
					</form>
				</div>
			</div>
			<div region="center">
				<table id="sysUnitGrid"></table>
			</div>
		</div>
	</div>
	<div id="mm" class="easyui-menu">  
    	<div iconCls="icon-add" onclick="addSysUnit()"><i class="add"></i>新增</div>  
    	<div iconCls="icon-edit" onclick="editSysUnitTree()"><i class="edit"></i>修改</div> 
    	<div iconCls="icon-remove" onclick="delSysUnit()"><i class="del"></i>删除</div>
    	<div class="menu-sep"></div>
    	<div onclick="collapse()"><i class="flex"></i>收缩</div> 
    	<div onclick="expand()"><i class="unfold"></i>展开</div> 
    	<div ><i class="exit"></i>退出</div> 
	</div>  
</body>
</html>
