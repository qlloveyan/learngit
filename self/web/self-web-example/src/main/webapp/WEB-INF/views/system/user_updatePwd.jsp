<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 基本信息 -->
<form id="userPwdEditFrom"  method="post">
<div class="easyui-panel">
	<!-- 添加 -->
	<div class="add-table add-list2">
    	<table>
    		<tr>
    			<th width="100">当前密码:</th>
    			<td><input id="oldPw" type="password" name="oldPw" class="easyui-validatebox" required="true"/></input></td>
    		</tr>
			<tr>
				<th width="100">更新密码:</th>
    			<td><input id="newPw" type="password" name="newPw" class="easyui-validatebox" required="true" validType="comlpePass"/></td>
			</tr>
			<tr>
				<th width="100">确认密码:</th>
    			<td><input id="pwdAgain" type="password" name="pwdAgain" class="easyui-validatebox" required="true"/></td>
			</tr>

    	</table>
    </div>
</div>
<div style="text-align:center;padding:5px">
   	<a href="javascript:void(0)" id="userPwdEidt"class="easyui-linkbutton" >保存</a>
   	<a href="javascript:void(0)" id="userPwdCancel"class="easyui-linkbutton">取消</a>
   </div>
  <!-- 隐藏域参数 接受通过url直接调转传过来的参数-->
  <input type="hidden" id="windowType" value="<%=request.getParameter("windowType")%>">
  
</form>
<input type="hidden" id="pasUpdate" value="${pasUpdate}"/>
<script type="text/javascript" src="js/modules/system/user_updatePwd.js"></script>
