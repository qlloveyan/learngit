<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 基本信息  -->
<!-- <form id="userAddFrom"  method="post"> -->
<input type="hidden" name="userId" id="userId" value="<%= request.getParameter("id") %>"/>
<div class="easyui-panel zjip">
	<!-- 添加 -->
	<fieldset style="border:1px solid #0088cc; margin-top: 5px;width: 85%;">
	   <legend>领导审批</legend>

		<table>
    		<tr>
    			<td id="leader" style="font-size:12px;">
    			</td>
    		</tr>
    	</table>

	</fieldset>
	<fieldset style="border:1px solid #0088cc; margin-top: 5px;width: 85%;">
	   <legend>下发处置</legend>

		<table>
			<tr>
    			<td id="issued" style="font-size:12px;">
    			</td>
			</tr>
    	</table>

	</fieldset>
	
</div>
<div style="text-align:center;padding:5px;margin-top:10px;">
   	<a href="javascript:void(0)" id="userAdd" class="easyui-linkbutton" >确定</a>
   	<a href="javascript:void(0)" id="userCancel" class="easyui-linkbutton">取消</a>
</div>
<!-- </form> -->
  
<script type="text/javascript" src="js/modules/system/user_msgConfig.js"></script>
<script type="text/javascript" src="js/common/validate.js"></script>
