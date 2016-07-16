<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 基本信息 -->
<form id="testEditFrom"  method="post">
<div class="easyui-panel">
	<!-- 添加 -->
	<div class="add-table add-list2">
    	<table  width="100%" border="0" cellspacing="0" cellpadding="0">
    		<tr>
    			<th width="100">名称:</td>
    			<td><input class="easyui-validatebox input-text" type="text"  name="name" data-options="required:true"></input></td>
    		</tr>

    	</table>
    </div>
</div>
<div class="add-save">
   	<a href="javascript:void(0)" id="testEidt"class="easyui-linkbutton" >保存</a>
   	<a href="javascript:void(0)" id="testCancel"class="easyui-linkbutton">取消</a>
   </div>
  <!-- 隐藏域参数 接受通过url直接调转传过来的参数-->
  <input type="hidden" id="windowType" value="<%=request.getParameter("windowType")%>">
  <input type="hidden" id="testId" name="id" value="<%=request.getParameter("id")%>">
</form>
  
<script type="text/javascript" src="js/modules/test/test_edit.js"></script>
