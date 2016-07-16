<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 基本信息 -->
<form id="sysUnitEditFrom"  method="post">
<div class="easyui-panel">
	<!-- 添加 -->
	<div class="add-table add-list2">
    	<table>
    		<tr>
    			<th width="100">单位名称<i class="must"></i>：</th>
    			<td><input class="easyui-validatebox" type="text"  name="unitName" data-options="required:true"></input></td>
    		</tr>
			<tr>
				<th width="100">上级单位<i class="must"></i>：</th>
    			<td><input  type="text"  id="parentId" name="parentId" data-options="required:true"></input></td>
			</tr>
			<tr>
				<th width="100">单位标识号<i class="must"></i>：</th>
    			<td><input type="text"  name="nativeId" ></input></td>
			</tr>
    	</table>
    </div>
</div>
<div style="text-align:center;padding:5px">
   	<a href="javascript:void(0)" id="sysUnitEidt"class="easyui-linkbutton" >保存</a>
   	<a href="javascript:void(0)" id="sysUnitCancel"class="easyui-linkbutton">取消</a>
   </div>
  <!-- 隐藏域参数 接受通过url直接调转传过来的参数-->
  <input type="hidden" id="windowType" value="<%=request.getParameter("windowType")%>"/>
  <input type="hidden" id="sysUnitId" name="id" value="<%=request.getParameter("id")%>"/>
  <input type="hidden" id="parentUnitId" name="parentUnitId" value="<%=request.getParameter("parentUnitId")%>"/>
  <input type="hidden" id="unitLevel" name="unitLevel" value="<%=request.getParameter("unitLevel")%>" />
</form>
  
<script type="text/javascript" src="js/modules/system/sysUnit_edit.js"></script>
