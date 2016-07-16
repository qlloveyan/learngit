<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 基本信息 -->
<form id="elementDetailFrom"  method="post">
<div class="easyui-panel">
	<!-- 添加 -->
	<div class="add-table add-list2">
    	<table>
    		<tr>
    			<th width="100">页面元素编码：</th>
    			<td><input class="easyui-validatebox" type="text"  name="elementCode"></input></td>
    		</tr>
    		<tr>
    			<th width="100">所属功能：</th>
    			<td><input class="easyui-validatebox" type="text"  name="funcName"></input></td>
    		</tr>
			<tr>
				<th width="100">元素名称：</th>
    			<td><input class="easyui-validatebox" type="text"  name="elementName"></input></td>
			</tr>
    	</table>
    </div>
</div>
<!-- 隐藏域参数 接受通过url直接调转传过来的参数-->
<input type="hidden" id="elementId" name="id" value="<%=request.getParameter("id")%>">
</form>
  
<script type="text/javascript" src="js/modules/system/element_detail.js"></script>
