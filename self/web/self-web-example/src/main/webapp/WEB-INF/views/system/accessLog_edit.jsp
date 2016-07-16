<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 基本信息 -->
<form id="accessLogEditFrom"  method="post">
<div class="easyui-panel">
	<!-- 添加 -->
	<div class="add-table add-list2">
    	<table  width="100%" border="0" cellspacing="0" cellpadding="0">
    		<tr>
    			<th width="100">日志信息:</td>
    			<td><input class="easyui-validatebox input-text" type="text"  name="logMsg" data-options="required:true"></input></td>
    			<th width="100">访问端IP:</td>
    			<td><input class="easyui-validatebox input-text" type="text"  name="ip" data-options="required:true"></input></td>
    		</tr>
    		<tr>
    			<th width="100">访问用户:</td>
    			<td><input class="easyui-validatebox input-text" type="text"  name="userName" data-options="required:true"></input></td>
    			<th width="100">时间:</td>
    			<td><input class="easyui-validatebox input-text" type="text"  name="time" data-options="required:true"></input></td>
    		</tr>

    	</table>
    </div>
</div>
<div class="add-save">
   	<a href="javascript:void(0)" id="accessLogEidt"class="easyui-linkbutton" >保存</a>
   	<a href="javascript:void(0)" id="accessLogCancel"class="easyui-linkbutton">取消</a>
   </div>
  <!-- 隐藏域参数 接受通过url直接调转传过来的参数-->
  <input type="hidden" id="windowType" value="<%=request.getParameter("windowType")%>">
  <input type="hidden" id="accessLogId" name="id" value="<%=request.getParameter("id")%>">
</form>
  
<script type="text/javascript" src="js/modules/system/accessLog_edit.js"></script>
