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
	<title>数据字典</title>
    <jsp:include page="../../../public/common2.jsp" />
	<script type="text/javascript" src="js/modules/system/dictionary.js"></script>
   	
</head>
<body class="easyui-layout">


	<div data-options="region:'west',title:'字典类型',split:true" style="width:330px;">
		<table id="dictionaryTypeGrid"></table>
		<div id="typeTb" style="padding:0px;height:auto;">
			<div class="list-add list-add-float">
				<a href="javascript:void(0)"  id="addTypeDictionary" plain="true">新增</a>
				<a href="javascript:void(0)"  id="editTypeDictionary" plain="true">编辑</a>
				<a href="javascript:void(0)"  id="delTypeDictionary" plain="true">删除</a>
			</div>
		</div>
	</div>  
	<div data-options="region:'center',title: '字典详情'">
	 	<table id="dictionaryGrid"></table>
	 	<div id="tb" style="padding:0px;height:auto;">
			<div class="list-add list-add-float">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add"  id="addDictionary" plain="true">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" id="editDictionary" plain="true">编辑</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" id="delDictionary" plain="true">删除</a>
			</div>
		</div>
	</div>
	
	<div id="typeAddWin" class="easyui-window" title="字典类型" style="width:500px;height:180px;padding:5px;" closed="true" data-options="iconCls:'icon-save',modal:true,minimizable:false,maximizable:false,collapsible:false">  
		<form id="typeAddForm" method="post">
			<input id="dictionary-type-id" type="hidden" name="id"/>
			<input id="dictionary-type-parent-id" type="hidden" name="parentId"/>
			<fieldset>
				<table style="width: 100%">
					<tr>
						<td>类型名称<span style="color:red;">*</span>：<input id="code-value-id" style="width:200px;" class="easyui-validatebox" type="text" name="codeValue" data-options="required:true" validType="length[0,20]"/></td>
						<td style="color: gray;">类型名称不超过10个字</td>
					</tr>
					<tr>
						<td>字典类型<span style="color:red;">*</span>：<input id="code-type-id" style="width:200px;" class="easyui-validatebox" type="text" name="codeType" data-options="required:true" validType="length[0,100]"/></td>
						<td style="color: gray;">字典类型不能重复</td>
					</tr>
	 			</table>
			</fieldset>
		</form>
		<div style="text-align:center;padding:5px">
		   	<a href="javascript:void(0)" id="typeAddWinSave"class="easyui-linkbutton" >保存</a>
		   	<a href="javascript:void(0)" id="typeAddWinClosed"class="easyui-linkbutton">取消</a>
		</div>
	</div>
	
	
	<div id="addWin" class="easyui-window" title="字典" closed="true" style="width: 600px;height: 180px;" data-options="zIndex:1000,modal:true,minimizable:false,maximizable:false,collapsible:false">
		<form id="dictionaryAddFrom"  method="post">
		<input id="dictionary-id" type="hidden" name="id"/>
		<input id="dictionary-code-type" type="hidden" name="codeType"/>
		<input id="dictionary-parent-id" type="hidden" name="parentId"/>
			<!-- 添加 -->
			<div style="padding:5px 0 5px 60px">
	    		<table>
	    			<tr>
		    			<td align="right">字典编码:</td>
		    			<td><input class="easyui-validatebox input-big" type="text"  name="codeKey" data-options="required:true,validType:['length[0,50]']"></input></td>
		    			<td align="right">字典名称:</td>
		    			<td><input class="easyui-validatebox input-big" type="text"  name="codeValue" data-options="required:true,validType:['length[0,100]']"></input></td>
		    		</tr>
		    		<tr>
		    			<td align="right">字典排序:</td>
		    			<td><input class="easyui-numberbox input-big" type="text"  name="codeSort" data-options="required:true"></input></td>
		    			<td align="right">描述:</td>
		    			<td><input class="easyui-validatebox input-big" type="text"  name="description" data-options="required:true,validType:['length[0,100]']"></input></td>
		    		</tr>
		    		<tr>
		    			<td align="right">是否可操作:</td>
		    			<td><input  type="text" id="isOperate" name="isOperate" class="input-big" data-options="required:true"></input></td>
		    			<td align="right"></td>
		    			<td></td>
		    		</tr>
		    	</table>
		    </div>
		</form>
		<div style="text-align:center;padding:5px">
		   	<a href="javascript:void(0)" id="dictionaryAdd" class="easyui-linkbutton" >保存</a>
		   	<a href="javascript:void(0)" id="dictionaryCancel" class="easyui-linkbutton">取消</a>
		</div>
	</div>
</body>
</html>
