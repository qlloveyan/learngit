//用户状态
var stateValue = getValueByType(DICTIONARY_USER_STATE);
//用户类型
var statusValue = getValueByType(DICTIONARY_USER_STATUS);

$('#queryStatus').combobox({
	data:statusValue,
	valueField:'codeKey',
	textField:'codeName',
	editable:false,
	panelHeight:'auto'
});
$('#queryActive').combobox({
	data:stateValue,
	valueField:'codeKey',
	textField:'codeName',
	editable:false,
	panelHeight:'auto'
});

//定义全局变量
var me;
//批量审核变量
var idTemp;

Surfilter.define("user.userList",{
	
	
	/**
	 * 模块名称
	 */
	moduleName : '用户管理模块',
	
	/**
	 * 继承公共列表模块
	 */
	extend : Surfilter.modules.common.listModules,
	
	/**
	 * 初始化列表
	 */
	initGrid : function() {
//		var me = this;
		me = this;
		
		var toolbar = [{
			text : "批量审批",
			iconCls : 'btn btn-info',
			handler : function(){
				var sels = $("[UI-Module=listGrid]").datagrid("getChecked");
				if (sels.length < 1) {
					$.messager.alert("提示","请选择一条记录进行操作！");
					return;
				}
				var ps = "";
				$.each(sels,function(i,n){  
	                ps += n.id + ",";  
		        });
				me.showEditWindow(ps.substring(0, ps.length-1));
			}
		}];
		
		me.$grid.datagrid({
			url : ctx + 'user/getPageModel.do',
			toolbar : toolbar,
			height : 300,
			fit : true,
			singleSelect:false,
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			} ] ],
			columns : [[
//			    {title : '头像',field : 'smallAvatar',width : 1,align : 'center'},
			    {title : '账号',field : 'phone',width : 1,align : 'center'},
			    {title : '用户名',field : 'name',width : 1,align : 'center'},
			    {title : '注册日期',field : 'registerTime',width : 1,align : 'center',
			    	formatter:function(value,rec,index){
			    		return int2Date(value);
			    	}
			    },
			    {title : '用户类型',field : 'status',width : 1,align : 'center',
			    	formatter:function(value,rec,index){
			    		return '<span style="color:'+(value=='100'?'green':'#333')+';">'+getValueByKey(statusValue, value);+'</span>';
			    	}
			    },
			    {title : '用户状态',field : 'active',width : 1,align : 'center',
			    	formatter:function(value,rec,index){
						return '<span style="color:'+(value=='1'?'green':'red')+';">'+getValueByKey(stateValue, value);+'</span>';
					}
			    },
			    {title : '启用/停用',field : 'reUserState',width : 1,align : 'center',
			    	formatter:function(value,rec,index){
			    		var userState = rec.active;
						var userId = rec.id;
						if (userState == 3) {//禁用
							return '<a id="reUserState" href="javascript:void(0)" onclick="reUserState(\''+userId+'\',\''+userState+'\');"><img alt="启用" title="启用" style="cursor:hand" src="images/use-icon.gif" /></a>';
						}else if( userState == 1 ){//启用
							return '<a id="reUserState" href="javascript:void(0)" onclick="reUserState(\''+userId+'\',\''+userState+'\');"><img alt="停用" title="停用" style="cursor:hand" src="images/logout-icon.gif" /></a>';
						}else{//审核
							return "";
						}
					}
			    },
			    {title : '操作',field : 'opt',width : 0.5,align : 'left',
			    	formatter:function(value,rec,index){
			    		//操作数组
			    		var opt = new Array();
			    		
			    		//获取用户状态和类型
			    		var userStatus = rec.status;
			    		var userActive = rec.active;
			    		
			    		if( userStatus != 100 && userActive == 1){
			    			var toAdmin ='<a href="javascript:void(0)" onclick="toAdmin(\''+ rec.id+'\')"><span class="drop-edit"></span>设为管理员</a> '; 
			    			opt.unshift(toAdmin);
			    		}
			    		if( userActive == 2 ){
			    			var showEditWindow2 ='<a href="javascript:void(0)" onclick="showEditWindow2(\''+ rec.id + '\')"><span class="drop-edit"></span>审核</a> '; 
			    			opt.unshift(showEditWindow2);
			    		}
			    		var detail = '<a href="javascript:void(0)" onclick="detail(\''+ rec.id + '\')"><span class="drop-detail"></span>查看详情</a> ';
			    		opt.unshift(detail);
						return createBtn(rec.id,opt);
					}
			    }
			]],
			queryParams : me.$form.getFormValues(),
			pagination:true,
			onLoadSuccess:function(data){
				onSelectBtnEvent(data.rows);
			}
		});
		
		//分页
		var p = me.$grid.datagrid('getPager');
		$(p).pagination({
			pageSize: 30,//每页显示的记录条数，默认为10 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        pageList: [30,50,100],//可以设置每页记录条数的列表  
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
			onBeforeRefresh:function(){
			}
		});
	},
	
	/**
	 * 初始化编辑窗口
	 */
	showEditWindow : function(ids){
//		var me = this;
		var data = getUserActives(false);
		//加载用户状态
		$('#active').combobox({
			data:data,
			valueField:'codeKey',
			textField:'codeName',
			editable:false,
			panelHeight:'auto',
			required : true,
			onLoadSuccess: function () { //加载完成后,设置选中第一项
				var val = $(this).combobox('getData');
				for (var item in val[0]) {
					if (item == 'codeKey') {
						$(this).combobox('select', val[0][item]);
					}
				}
			}
		});
		idTemp = ids;
//		$("#add_submit").click(function(){
//			
//		});
		//弹框
		$("#addTitle").html("批量审核用户");
		$("#addModal").css("z-index","1050");
		$("#addModal").modal("show");
	}
});

//批量审批操作
function shenpi(){
	//获取token
	var active = $("#active").combobox("getValue");
	$.ajax({
		url : ctx + "user/checkUser.do",
		type: 'POST',
		data: {
			'userId': idTemp,
			'active': active
		},
		async : false,
		datatype:'json',
		success : function(data){
			data = eval("("+ data +")");
			if(data.success){
				$.messager.alert("提示",data.msg);
			}
			me.query();
		}
	});
}

/**
 * 启用/停用用户状态
 * @param {} userId 用户ID
 * @param {} userState 用户当前的状态
 */
function reUserState(userId, userState){
	if(userId != null && userState != null){
		var confirmMsg = (userState=='1')?'确认停用该用户?':'确认启用该用户?';
		$.messager.confirm('提示信息',confirmMsg,function(r){   
		    if (r){
		    	$.ajax({
					type: 'POST',
					url: ctx + 'user/reUserState.do',
					data: {
						'userId': userId,
						'userState': userState
					},
					success: function(data){
						var res = $.parseJSON(data);
						if(!res.success){
							$.messager.alert("信息提示",res.msg);
						}else{
							var msg = (userState=='2')?'启用成功':'停用成功';
							$.messager.alert("信息提示",msg);
							me.$grid.datagrid('reload');
						}
					}
				});
		    }   
		});
	}
}

//将用户设为管理员
function toAdmin(userId){
	var confirmMsg = '确认要将该用户设置为管理员吗?';
	$.messager.confirm('提示信息',confirmMsg,function(r){   
	    if (r){
	    	$.ajax({
				type: 'POST',
				url: ctx + 'user/toAdmin.do',
				data: {
					'userId': userId
				},
				success: function(data){
					var res = $.parseJSON(data);
					if(!res.success){
						$.messager.alert("信息提示",res.msg);
					}else{
						me.$grid.datagrid('reload');
					}
				}
			});
	    }
	});
}

function detail(userId){
	$.ajax({
		type: 'POST',
		url: ctx + 'user/getEntity.do',
		async : false,
		data: {
			'id': userId
		},
		success: function(data){
			if( data != '' ){
				var res = $.parseJSON(data);
//				$("#detName").html(res.name);
				$("#detId").val(res.id);
				$("#detName").val(res.name);
				$("#detIdentityCard").val(res.identityCard);
				$("#detPhone").html(res.phone);
				$("#detEmail").html(res.email);
				$("#detProvince").html(res.provinceName);
				$("#detCity").html(res.cityName);
//				$("#detSrcAddr").html(res.addr);
				$("#detSrcAddr").val(res.addr);
				$("#detRegisterTime").html(int2Date(res.registerTime));
				$("#detStatus").html(getValueByKey(statusValue, res.status));
				$("#detActive").html(getValueByKey(stateValue, res.active));
			}
		}
	});
	//设置z-index
	$("#detailModal").css("z-index","1050");
	//弹框
	$("#detailModal").modal("show");
}
//弹出层消失
function detailDlgDisplay(){
	$('#detailModal').modal('hidden');
	$("#detailModal").css("z-index","-1");
}

//单独审核的方法
function showEditWindow2(userId){
	//调用审核的公共方法
	me.showEditWindow(userId);
}

//查询
$("#querySubmit").click(function(){
	searchUser();
});
//重置条件
$("#queryReset").click(function(){
	 $('#queryForm').form('clear'); 
	 searchUser();
});
/**
 * 查询用户信息
 */
function searchUser(){
	var params = $("[UI-Module=listGrid]").datagrid('options').queryParams; //先取得 datagrid 的查询参数  
    var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象 
    $.each( fields, function(i, field){  
        params[field.name] = field.value; //设置查询参数  
    });
    me.$grid.datagrid('reload');//设置好查询参数 reload
}

/**
 * 编辑用户
 */
function changeSure(){
	var flag= $("#detailForm").form('validate');
	if(flag){
		var id = $("#detId").val();
		var name = $("#detName").val();
		var identityCard = $("#detIdentityCard").val();
		var addr = $("#detSrcAddr").val();
		
		$.ajax({
			type: 'POST',
			url: ctx + 'user/editEntity.do',
			async : false,
			data: {
				'id': id,
				'name': name,
				'identityCard': identityCard,
				'addr': addr
			},
			success: function(data){
				if( data != '' ){
					var res = $.parseJSON(data);
					if(!res.success){
						$.messager.alert("信息提示",res.msg);
					}else{
						//设置z-index
						$("#detailModal").css("z-index","-1");
						//弹框
						$("#detailModal").modal("hide");
						me.$grid.datagrid('reload');
					}
				}
			}
		});
	}
}