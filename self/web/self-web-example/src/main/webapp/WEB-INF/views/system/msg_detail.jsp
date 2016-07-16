<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
input{
	width:150px;
/* 	background-color: white; */
}
</style>
<!-- 基本信息 -->
<form id="msgDetailFrom"  method="post">
<div class="easyui-panel">
	<!-- 添加 -->
		<div class="add-table add-list2">
    	<table>
    		<tr>
    			<th width="100">发送者:</th>
    			<td><input class="easyui-validatebox" type="text"  name="createUser" id="createUser"></input></td>
    			<th width="100">发送时间:</th>
    			<td><input class="easyui-validatebox" type="text"  name="createTime"></input></td>
    		</tr>
    		<tr>
    			<th width="100">标题:</th>
    			<td colspan="3"><input class="easyui-validatebox" style="width: 405px;" type="text"  name="msgTitle"></input></td>
    		</tr>
    		<tr>
    			<th width="100">内容:</th>
    			<td colspan="3">
<!--     				<input class="easyui-validatebox" type="text"  name="msgContent"></input> -->
	    			<textarea class="easyui-validatebox" name="msgContent" style="width: 405px;height: 100px;font-size: 13px;" readonly="readonly"></textarea>
    			</td>
    		</tr>
    	</table>
    </div>
</div>
<!-- 隐藏域参数 接受通过url直接调转传过来的参数-->
<input type="hidden" id="msgId" name="id" value="<%=request.getParameter("id")%>">
</form>
  
<script type="text/javascript" src="js/modules/system/msg_detail.js"></script>
