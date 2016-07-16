/**
 * 机房信息业务
 */

$(function(){
	
	initElementPermissions();
	var dictionaryData = getDictionaryByType(DICTIONARY_USER_STATE);
	//加载数据
	loadData(dictionaryData);
	//弹出编辑页面
	$("#addUser").click(function(){
		openWin("addUserWin","system/redirect/redirectHomePage.do?path=system/user_add","新增用户账号信息",600,350);
	});
	//查询
	$("#searchUser").click(function(){
		searchUser();
	});
	//重置条件
	$("#resetUser").click(function(){
		 $('#queryForm').form('clear'); 
		 searchUser();
	});
	//批量删除
	$("#delUser").click(function(){
		 var rows = $('#userGrid').datagrid('getSelections'); 
         var ps = "";  
         $.each(rows,function(i,n){  
             if(i==0)   
                 ps += "?ids="+n.id;  
             else  
                 ps += "&ids="+n.id;  
         });  
         if(ps==""){
         	 $.messager.alert('提示',"请选择需要删除的数据！");  
         }else{
        	 $.messager.confirm('提示','确定要删除吗?',function(result){  
 	            if (result){  
 	            	 $.post('system/user/delBatchEntity.do'+ps,function(data){  
 	            	 	var res = $.parseJSON(data);
 	            	 	if(!res.success){
 	                   		 $.messager.alert('提示',res.msg);  
 	            	 	}
 	                    $('#userGrid').datagrid('reload');   
 	                });  
 	            }  
        	 });  
         }
	});
	
	//初始化单位性质
	$('#parentId').combobox({   
	    url:'system/user/getSysUnits.do',   
	    valueField:'id',   
	    textField:'unitName',
	    panelHeight:'auto',
	    width:130
	});
	//初始化用户角色
	$('#roleId').combobox({   
	    url:'system/user/getRoles.do',   
	    valueField:'id',   
	    textField:'roleName',
	    panelHeight:'auto'
	});
});

/**
 * 初始化加载数据,分页
 */
function loadData(dictionaryData){
	//grid数据加载
	$('#userGrid').datagrid({
		fit:true,
		fitColumns:true,
		collapsible:false,
		nowrap: true,
		singleSelect:false,  
		autoRowHeight: false,
		striped: true,
		url:'system/user/getPageModel.do',
		sortName: 'createTime',
		sortOrder: 'desc',
		remoteSort: true,
		idField:'id',
		frozenColumns:[[
            {field:'ck',checkbox:true}
		]],
		columns:[[
			{field:'userName',title:'用户名称',width:80,align:'center',sortable:true},
			{field:'userCode',title:'用户帐号',width:80,align:'center',sortable:true},
			{field:'roleName',title:'角色',width:80,align:'center',sortable:false},
			{field:'tel',title:'电话',width:80,align:'center'},
			{field:'mobile',title:'手机',width:80,align:'center'},
			{field:'unitName',title:'所属单位',width:80,align:'center'},
			{field:'userState',title:'用户状态',width:80,align:'center',sortable:true,
				formatter:function(value,rec,index){
					return '<span style="color:'+(value=='0'?'green':'red')+';">'+getValueByKey(dictionaryData, value);+'</span>';
				}
			},
			{field:'reUserState',title:'启用/停用',width:80,align:'center',
				formatter:function(value,rec,index){
					var userState = rec.userState;
					var userId = rec.id;
					if (userState == 1) 
						return '<a id="reUserState" href="javascript:void(0)" onclick="reUserState(\''+userId+'\',\''+userState+'\');"><img alt="启用" title="启用" style="cursor:hand" src="./resources/images/menu/use-icon.gif" /></a>';
					return '<a id="reUserState" href="javascript:void(0)" onclick="reUserState(\''+userId+'\',\''+userState+'\');"><img alt="停用" title="停用" style="cursor:hand" src="./resources/images/menu/logout-icon.gif" /></a>';
				}
			},
			{field:'opt',title:'操作',width:370,align:'center',
				formatter:function(value,rec,index){
					var edit = '<a id="editUser" href="javascript:void(0)"  class="editUser" onclick="editUser(\''+ rec.id + '\')" iconCls="icon-edit"  plain="true"></a>';
					var detail = '<a id="detailUser" href="javascript:void(0)" class="detailUser" onclick="detailUser(\''+ rec.id + '\')" iconCls="icon-edit"  plain="true"></a>';
					var resetPwd = '<a id="resetPwdUser" href="javascript:void(0)" class="resetPwdUser" onclick="resetPwdUser(\''+ rec.id + '\')" iconCls="icon-edit"  plain="true"></a>';
					var assign = '<a id="assignRolesBtn" href="javascript:void(0)" class="assignRoles" onclick="assignRoles(\''+rec.id+'\',\''+rec.userName+'\')" iconCls="icon-edit" plain="true"></a>'
					return edit+" "+detail +" " + resetPwd + " "+assign;
				}
			}
		]],
		pagination:true,
		pageSize:30,
		pageList:[30,50,100],
		rownumbers:true,
		toolbar:'#tb',
		onLoadSuccess:function(data){
			
			var flag = true;
			flag = isHasElementPerssion('editUser');
			$("a#editUser").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			flag = isHasElementPerssion('detailUser');
			$("a#detailUser").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			flag = isHasElementPerssion('resetPwdUser');
			$("a#resetPwdUser").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			flag = isHasElementPerssion('assignRolesBtn');
			$("a#assignRolesBtn").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			flag = isHasElementPerssion('reUserState');
			$("a#reUserState").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			flag = isHasElementPerssion('msgConfigBtn');
			$("a#msgConfigBtn").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			//启用CA注销
//			$("a#cancelCABtn").each(function(){
//				$(this).css("display","none");
//			});
			
			/**
			 * 数据加载成功后渲染linkbutton
			 */
			$(".editUser").linkbutton({
				text:'编辑', 
				title:'编辑',
				plain:true
			});
			$(".detailUser").linkbutton({
				text:'详细', 
				title:'详细',
				plain:true
			});
			$(".resetPwdUser").linkbutton({
				text:'密码重置', 
				title:'密码重置',
				plain:true
			});
			$(".assignRoles").linkbutton({
				text: '分配角色',
				title:'分配角色',
				plain:true
			});
			$(".msgConfig").linkbutton({
				text: '短信配置',
				title:'短信配置',
				plain:true
			});
			$(".cancelCA").linkbutton({
				text: '注销证书',
				title:'注销证书',
				plain:true
			});
			
		}
	});
	//分页
	var p = $('#userGrid').datagrid('getPager');
	$(p).pagination({
		pageSize: 30,//每页显示的记录条数，默认为10 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        pageList: [30,50,100],//可以设置每页记录条数的列表  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
		onBeforeRefresh:function(){
		}
	});
}

/**
 * 查询机房信息
 */
function searchUser(){
	var params = $('#userGrid').datagrid('options').queryParams; //先取得 datagrid 的查询参数  
    var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象 
    $.each( fields, function(i, field){  
        params[field.name] = field.value; //设置查询参数  
    });   
    $('#userGrid').datagrid('reload'); //设置好查询参数 reload
}

/**
 * 编辑机房
 * @param id 机房id
 */
function editUser(id){
	var params = [{param:[{paramName:"id",value:id}]}];
	openWin("editUserWin","system/redirect/redirectHomePage.do?path=system/user_edit","编辑用户账号信息",650,350,params);
}

/**
 * 查看机房详情
 * @param id 机房id
 */
function detailUser(id){
	var params = [{param:[{paramName:"id",value:id}]}];
	openWin("detailUserWin","system/redirect/redirectHomePage.do?path=system/user_detail","用户账号信息详情",600,350,params);
}

/**
 * 动态渲染出角色分配弹出框中的元素
 * @param {} userId  用户ID
 * @param {} userName 用户姓名
 */
function assignRoles(userId, userName){
	if(userId != null && userName != null){
		
		$('#assignRolesForm-userId').val(userId);
		// 先清空table下的所有节点，根据后台传输的数据动态渲染表格
		$('#assignRolesTable').empty();
		$('#assignRolesTable').append('<tr class="fp-title" align="center"><th>角色名称</th><td>是否分配</td></tr>');
		$.ajax({
			url: 'system/user/getSelectRoleByUserId.do',
			data: {
				'userId': userId
			},
			type: 'POST',
			success: function(data){
				var data = eval(data);
				if(data != null && data.length > 0){
					for(var i=0;i<data.length;i++){
						var template = '<tr><th>'+data[i].roleName+'</th><td><input name="roleId" value="'+data[i].id+'" type="checkbox" '+(data[i].description=='true'?'checked':'')+'/></td></tr>';
						$('#assignRolesTable').append(template);
					}
				}
			}
		});
		// 分配信息的弹出框
		$('#assignRoles').dialog({
			title: userName + '用户角色配置',
			collapsible: false, 
			width:400,
			height:300,   
			buttons: [{ 
				text: '提交', 
				iconCls: 'icon-ok',
				handler: function() { 
					assignRolesFormSubmitFn();
				} 
			},{ 
				text: '返回列表', 
				handler: function() { 
					$('#assignRoles').dialog('close'); 
				} 
			}]
		});
		
		$('#assignRoles').dialog('open');
	}
}

/**
 * 启用/停用用户状态
 * @param {} userId 用户ID
 * @param {} userState 用户当前的状态
 */
function reUserState(userId, userState){
	if(userId != null && userState != null){
		var confirmMsg = (userState=='0')?'确认停用该用户?':'确认启用该用户?';
		$.messager.confirm('信息提示', confirmMsg, function(r){
			if (r){
				$.ajax({
					type: 'POST',
					url: 'system/user/reUserState.do',
					data: {
						'userId': userId,
						'userState': userState
					},
					success: function(data){
						var res = $.parseJSON(data);
						if(!res.success){
							$.messager.alert('信息提示', res.msg);
						}else{
							var msg = (userState=='1')?'启用成功':'停用成功';
							$.messager.alert('信息提示', msg);
						}
						$('#userGrid').datagrid('reload');
			        }
				});
		    }
		});
	}
}

/**
 * 提交角色分配的表单
 */
function assignRolesFormSubmitFn(){
	$("#assignRolesForm").form('submit',{  
			url: 'system/user/authRole.do',
			success: function(data){
				$.messager.alert('信息提示', '角色分配成功');
				$('#userGrid').datagrid('reload');
				$('#assignRoles').dialog('close'); 
			}  
		});  
}

/**
 * 密码重置
 */
function resetPwdUser(userId){
	$.messager.confirm('信息提示', "确认重置该用户密码?", function(r){
		if (r){
			$.post('system/user/resetPwdUser.do',{userId:userId},function(data){
				var res = $.parseJSON(data);
				if(!res.success){
					$.messager.alert("温馨提示","重置密码成功!");
				}else{
					$.messager.show({title:"温馨提示",msg:res.msg})
				}
			})
		}
	});
}

/**
 * 短信配置
 */
function msgConfig(id){
	var params = [{param:[{paramName:"id",value:id}]}];
	openWin("userMsgConfigWin","system/redirect/redirectHomePage.do?path=system/user_msgConfig","用户短信配置",500,330,params);
}

//CA证书注销
function cancelCA(id){
	$.messager.confirm('温馨提示', "您确认要注销用户证书吗?", function(r){
		if (r){
			$.ajax({
				type: 'POST',
				url: 'system/user/cancelCA.do',
				data: {'userId': id},
				success: function(data){
					var res = $.parseJSON(data);
					$.messager.alert('信息提示', res.msg);
					$('#userGrid').datagrid('reload');
		        }
			});
		}
	});
}
