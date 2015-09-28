$(function() {
	//加载字典
	loadCodes();
	//加载列表
	load("");
	
	//加载树形字典效果
	$('.codeTree li:has(ul)').addClass('parent_li').find(' > span').attr('title', '折起');
	$('.codeTree li.parent_li > span').on( 'click', function(e) {
		var children = $(this).parent('li.parent_li') .find(' > ul > li');
		if (children.is(":visible")) {
			children.hide('fast');
			$(this).attr('title', 'Expand this branch').find(' > i') .addClass('icon-plus-sign').removeClass( 'icon-minus-sign');
		} else {
			children.show('fast');
			$(this).attr('title', 'Collapse this branch').find(' > i') .addClass('icon-minus-sign').removeClass( 'icon-plus-sign');
		}
		e.stopPropagation();
	});
	
});

// 叶子节点被点击
function childClick(codeId) {
	$("#codeId").val(codeId);
	//同时加载右侧grid
	load(codeId);
}
/*-------------------------父类操作--------------------------------*/
//新增
function add(){
	//清空表单
	$('#addPForm').form('clear');
	//设置div层级
	$("#addModal").css("z-index","1050");
	//弹框
	$("#addModal").modal("show");
}
//编辑
function edit() {
	var codeId = $("#pId").val();
	if (codeId != null && codeId != '') {
		//加载数据
		$.ajax({
			type: 'POST',
			url: ctx + 'code/getEntity.do',
			async : false,
			data: {
				'id': codeId
			},
			success: function(data){
				if( data != '' ){
					var res = $.parseJSON(data);
					$("#editPForm").find("input[name='id']").val(res.result.id);
					$("#editPForm").find("input[name='codeName']").val(res.result.codeName);
					$("#editPForm").find("input[name='codeType']").val(res.result.codeType);
//					$("#editPForm").find("input[name='codeSort']").val(res.result.codeSort);
				}
			}
		});
		//设置div层级
		$("#editModal").css("z-index","1050");
		//弹框
		$("#editModal").modal("show");
	} else {
		$.messager.alert("温馨提示","请选择一个需要修改的字典");
	}
}
//删除
function del() {
	var codeId = $("#pId").val();
	if (codeId != null && codeId != '') {
		$.messager.confirm('提示信息','您确定要删除该字典吗?',function(r){   
		    if (r){
		    	$.ajax({
					type: 'POST',
					url: ctx + 'code/delEntity.do',
					data: {
						'id': codeId
					},
					success: function(data){
						var res = $.parseJSON(data);
						if(!res.success){
							$.messager.alert("信息提示",res.msg);
						}else{
							//加载字典
							loadCodes();
							//加载列表
							load("");
						}
					}
				});
		    }   
		});
	} else {
		$.messager.alert("温馨提示","请选择一个需要删除的字典");
	}
}
//新增
function addPSure(){
	var flag = $("#addPForm").form('validate');
	if( flag ){
		var codeName = $("#addPForm").find("input[name='codeName']").val();
		var codeType = $("#addPForm").find("input[name='codeType']").val();
//		var codeSort = $("#addPForm").find("input[name='codeSort']").val();
		$.ajax({
			type: 'POST',
			url: ctx + 'code/addEntity.do',
			data: {
				'codeName': codeName,
				'codeType':codeType
//				,
//				'codeSort':codeSort
			},
			success: function(data){
				var res = $.parseJSON(data);
				if(!res.success){
					$.messager.alert("信息提示",res.msg);
				}else{
					$("#addModal").modal("hide");
					//设置div层级
					$("#addModal").css("z-index","-1");
					//加载字典
					loadCodes();
					//加载列表
					load("");
				}
			}
		});
	}
}
//编辑
function editPSure(){
	var flag = $("#editPForm").form('validate');
	if( flag ){
		var id = $("#editPForm").find("input[name='id']").val();
		var codeName = $("#editPForm").find("input[name='codeName']").val();
		var codeType = $("#editPForm").find("input[name='codeType']").val();
//		var codeSort = $("#editPForm").find("input[name='codeSort']").val();
		$.ajax({
			type: 'POST',
			url: ctx + 'code/editEntity.do',
			data: {
				'id': id,
				'codeName': codeName,
				'codeType':codeType
//				,
//				'codeSort':codeSort
			},
			success: function(data){
				var res = $.parseJSON(data);
				if(!res.success){
					$.messager.alert("信息提示",res.msg);
				}else{
					$("#editModal").modal("hide");
					//设置div层级
					$("#editModal").css("z-index","-1");
					//加载字典
					loadCodes();
					//加载列表
					load(id);
				}
			}
		});
	}
}
/*--------------------------子类操作------------------------------*/
//新增
function addChild(){
	var codeId = $("#pId").val();
	if (codeId != null && codeId != '') {
		//清空表单
		$('#addCForm').form('clear');
		//codeType赋值
		$("#addCForm").find("input[name='codeType']").val($("#codeType").val());
		//设置div层级
		$("#addCModal").css("z-index","1050");
		//弹框
		$("#addCModal").modal("show");
	} else {
		$.messager.alert("温馨提示","请选择所属父类的字典");
	}
}
//编辑
function editC(id) {
	//加载combobox
	$('#editCPid').combobox({   
	    url:ctx + 'code/getParentCode.do',   
	    valueField:'codeKey',   
	    textField:'codeName',
	    editable:false,
	    panelHeight:'auto',
	    required : true
	}); 
	//加载数据
	$.ajax({
		type: 'POST',
		url: ctx + 'code/getEntity.do',
		async : false,
		data: {
			'id': id
		},
		success: function(data){
			if( data != '' ){
				var res = $.parseJSON(data);
				$("#editCForm").find("input[name='id']").val(res.result.id);
				$("#editCPid").combobox('setValue', res.result.pid);
				$("#editCForm").find("input[name='codeName']").val(res.result.codeName);
				$("#editCForm").find("input[name='codeType']").val(res.result.codeType);
				$("#editCForm").find("input[name='codeKey']").val(res.result.codeKey);
				$("#editCForm").find("input[name='codeSort']").val(res.result.codeSort);
			}
		}
	});
	//设置div层级
	$("#editCModal").css("z-index","1050");
	//弹框
	$("#editCModal").modal("show");
}
//删除
function delC(id) {
	var pid = $("#pId").val();
	$.messager.confirm('提示信息','您确定要删除该字典吗?',function(r){   
	    if (r){
	    	$.ajax({
				type: 'POST',
				url: ctx + 'code/delEntity.do',
				data: {
					'id': id
				},
				success: function(data){
					var res = $.parseJSON(data);
					if(!res.success){
						$.messager.alert("信息提示",res.msg);
					}else{
						//加载列表
						load(pid);
					}
				}
			});
	    }   
	});
}
//新增
function addCSure(){
	var flag = $("#addCForm").form('validate');
	if( flag ){
		var pid = $("#pId").val();
		var codeName = $("#addCForm").find("input[name='codeName']").val();
		var codeType = $("#addCForm").find("input[name='codeType']").val();
		var codeKey = $("#addCForm").find("input[name='codeKey']").val();
		var codeSort = $("#addCForm").find("input[name='codeSort']").val();
		$.ajax({
			type: 'POST',
			url: ctx + 'code/addEntity.do',
			data: {
				'pid':pid,
				'codeName':codeName,
				'codeType':codeType,
				'codeKey':codeKey,
				'codeSort':codeSort
			},
			success: function(data){
				var res = $.parseJSON(data);
				if(!res.success){
					$.messager.alert("信息提示",res.msg);
				}else{
					$("#addCModal").modal("hide");
					//设置div层级
					$("#addCodal").css("z-index","-1");
					//加载列表
					load(pid);
				}
			}
		});
	}
}
//编辑
function editCSure(){
	var flag = $("#editCForm").form('validate');
	if( flag ){
		var id = $("#editCForm").find("input[name='id']").val();
		var pid = $("#editCPid").combobox('getValue');
		var codeName = $("#editCForm").find("input[name='codeName']").val();
		var codeType = $("#editCForm").find("input[name='codeType']").val();
		var codeKey = $("#editCForm").find("input[name='codeKey']").val();
		var codeSort = $("#editCForm").find("input[name='codeSort']").val();
		$.ajax({
			type: 'POST',
			url: ctx + 'code/editEntity.do',
			data: {
				'id': id,
				'pid':pid,
				'codeName':codeName,
				'codeType':codeType,
				'codeKey':codeKey,
				'codeSort':codeSort
			},
			success: function(data){
				var res = $.parseJSON(data);
				if(!res.success){
					$.messager.alert("信息提示",res.msg);
				}else{
					$("#editCModal").modal("hide");
					//设置div层级
					$("#editCModal").css("z-index","-1");
					//加载列表
					load(pid);
				}
			}
		});
	}
}

//加载所有的数据字典
function loadCodes(){
	$.ajax({
		type: 'POST',
		url: ctx + 'code/getParentCode.do',
		async : false,
		success: function(data){
			var html = "";
			var res = $.parseJSON(data);
			for( var i = 0 ; i < res.length ; i++ ){
				var temp = res[i];
				html += '<li><a href="javascript:void()" data-id="'+temp.id+'" data-type="'+temp.codeType+'"><i class="icon-minus-sign"></i>'+temp.codeName+'</a></li>';
			}
			$("#ulCode").html(html);
			$("#ulCode li a").bind("click",function(e){
				$(e.target).addClass("highred").parent("li").siblings("li").children("a").removeClass("highred");
				var codeId = $(e.target).attr("data-id");
				$("#pId").val(codeId);
				var codeType = $(e.target).attr("data-type");
				$("#codeType").val(codeType);
				//根据id加载详情form
				$.ajax({
					type: 'POST',
					url: ctx + 'code/getEntity.do?id='+codeId,
					async : false,
					success: function(data){
						var res = $.parseJSON(data);
						$("#detailPFrom").find("input[name='codeType']").val(res.result.codeType);
						$("#detailPFrom").find("input[name='codeName']").val(res.result.codeName);
						$("#detailPFrom").find("input[name='codeSort']").val(res.result.codeSort);
					}
				});
				//同时加载右侧grid
				load(codeId);
			});
		}
	});
}
//加载右侧数据字典
function load(parentId){
	$("#codeGrid").datagrid({
		url : ctx + 'code/getPageModel.do?pid='+parentId,
//		toolbar : toolbar,
		height : 300,
		fit : true,
		singleSelect:false,
//		frozenColumns : [ [ {
//			field : 'ck',
//			checkbox : true
//		} ] ],
		columns : [[
		    {title : '字典类型',field : 'codeType',width : 1,align : 'center'},
		    {title : '字典名称',field : 'codeName',width : 1,align : 'center'},
		    {title : '字典编码',field : 'codeKey',width : 1,align : 'center'},
		    {title : '所属分类',field : 'pName',width : 1,align : 'center'},
		    {title : '字典序号',field : 'codeSort',width : 1,align : 'center'},
		    {title : '状态',field : 'isUsing',width : 0.5,align : 'center',
		    	formatter:function(value,rec,index){
		    		return value == 1?"启用":"停用";
		    	}
		    },
		    {title : '操作',field : 'opt',width : 0.5,align : 'left',
		    	formatter:function(value,rec,index){
		    		//操作数组
		    		var opt = new Array();
		    		var edit = '<a href="javascript:void(0)" onclick="editC(\''+ rec.id + '\')"><span class="drop-edit"></span>编辑</a> ';
		    		var del = '<a href="javascript:void(0)" onclick="delC(\''+ rec.id + '\')"><span class="drop-del"></span>删除</a> ';
		    		if( rec.codeType == 'PAYMENT_RULE' ){
		    			var startOrEnd = rec.isUsing == 1?'<a href="javascript:void(0)" onclick="changeUsing(\''+ rec.id + '\',\''+ rec.isUsing + '\')"><span class="drop-stop"></span>停用</a> ':'<a href="javascript:void(0)" onclick="changeUsing(\''+ rec.id + '\',\''+ rec.isUsing + '\')"><span class="drop-start"></span>启用</a> ';
    					opt.unshift(startOrEnd);
		    		}
		    		opt.unshift(del);
		    		opt.unshift(edit);
		    		return createBtn(rec.id,opt);
				}
		    }
		]],
//		queryParams : me.$form.getFormValues(),
		pagination:true,
		onLoadSuccess:function(data){
			onSelectBtnEvent(data.rows);
		}
	});
	//分页
	var p = $("#codeGrid").datagrid('getPager');
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
//更改劳务规则
function changeUsing(id,status){
	var confirmMsg = "";
	if( status == 1 ){
		confirmMsg = "您确认停用该规则吗?";
	}else{
		confirmMsg = "您确认启用该规则吗?";
	}
	
	$.messager.confirm('提示信息',confirmMsg,function(r){   
	    if (r){
	    	$.ajax({
				type: 'POST',
				url: ctx + 'code/changeUsing.do',
				data: {
					'id':id,
					'status': status
				},
				success: function(data){
					var res = $.parseJSON(data);
					if(!res.success){
						$.messager.alert("信息提示",res.msg);
					}else{
						$("#codeGrid").datagrid('reload');
					}
				}
			});
	    }
	});
}