$(function() {
	//加载菜单
	loadCodes();
	//加载列表
	load("");
	
	//加载树形菜单效果
	$('.codeTree li:has(ul)').addClass('parent_li').find(' > span').attr('title', '折起');
	$('.codeTree li.parent_li > span').on( 'click', function(e) {
		var children = $(this).parent('li.parent_li') .find(' > ul > li');
		
		$(this).parent('li.parent_li') .find(' > ul > li');
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


/*-------------------------父类操作--------------------------------*/
//新增
function add(){
	//清空表单
	$('#addPForm').form('clear'); 
	//弹框
	$("#addModal").modal("show");
}
// 编辑
function edit() {
	var codeId = $("#pId").val();
	if (codeId != null && codeId != '') {
		//加载数据
		$.ajax({
			type: 'POST',
			url: ctx + 'menu/getEntity.do',
			async : false,
			data: {
				'id': codeId
			},
			success: function(data){
				if( data != '' ){
					var res = $.parseJSON(data);
					$("#editPForm").find("input[name='name']").val(res.result.name);
					$("#editPForm").find("input[name='pageCmpUrl']").val(res.result.pageCmpUrl);
					$("#editPForm").find("input[name='showIndex']").val(res.result.showIndex);
				}
			}
		});
		//弹框
		$("#editModal").modal("show");
	} else {
		$.messager.alert("温馨提示","请选择一个需要修改的菜单");
	}
}
//删除
function del() {
	var codeId = $("#pId").val();
	if (codeId != null && codeId != '') {
		$.messager.confirm('提示信息','您确定要删除该菜单吗?',function(r){   
		    if (r){
		    	$.ajax({
					type: 'POST',
					url: ctx + 'menu/delEntity.do',
					data: {
						'id': codeId
					},
					success: function(data){
						var res = $.parseJSON(data);
						if(!res.success){
							$.messager.alert("信息提示",res.msg);
						}else{
							//加载菜单
							loadCodes();
							//加载列表
							load("");
						}
					}
				});
		    }   
		});
	} else {
		$.messager.alert("温馨提示","请选择一个需要删除的菜单");
	}
}
//新增
function addPSure(){
	var flag = $("#addPForm").form('validate');
	if( flag ){
		var name = $("#addPForm").find("input[name='name']").val();
		var pageCmpUrl = $("#addPForm").find("input[name='pageCmpUrl']").val();
		var showIndex = $("#addPForm").find("input[name='showIndex']").val();
		$.ajax({
			type: 'POST',
			url: ctx + 'menu/addEntity.do',
			data: {
				'pageCmpUrl': pageCmpUrl,
				'name':name,
				'showIndex':showIndex
			},
			success: function(data){
				var res = $.parseJSON(data);
				if(!res.success){
					$.messager.alert("信息提示",res.msg);
				}else{
					$("#addModal").modal("hide");
					//加载菜单
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
		var name = $("#editPForm").find("input[name='name']").val();
		var pageCmpUrl = $("#editPForm").find("input[name='pageCmpUrl']").val();
		var showIndex = $("#editPForm").find("input[name='showIndex']").val();
		$.ajax({
			type: 'POST',
			url: ctx + 'menu/editEntity.do',
			data: {
				'id': id,
				'pageCmpUrl': pageCmpUrl,
				'showIndex':showIndex,
				'name':name
			},
			success: function(data){
				var res = $.parseJSON(data);
				if(!res.success){
					$.messager.alert("信息提示",res.msg);
				}else{
					$("#editModal").modal("hide");
					//加载菜单
					loadCodes();
					//加载列表
					load("");
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
		//弹框
		$("#addCModal").modal("show");
	} else {
		$.messager.alert("温馨提示","请选择所属父类的菜单");
	}
}
// 编辑
function editC(id) {
	//加载combobox
	$('#editCPid').combobox({   
	    url:ctx + 'menu/getParentMenu.do',   
	    valueField:'id',   
	    textField:'name',
	    editable:false,
	    panelHeight:'auto',
	    required : true
	}); 
	//加载数据
	$.ajax({
		type: 'POST',
		url: ctx + 'menu/getEntity.do',
		async : false,
		data: {
			'id': id
		},
		success: function(data){
			if( data != '' ){
				var res = $.parseJSON(data);
				$("#editCForm").find("input[name='id']").val(res.result.id);
				$("#editCPid").combobox('setValue', res.result.parentMenu);
				$("#editCForm").find("input[name='name']").val(res.result.name);
				$("#editCForm").find("input[name='showIndex']").val(res.result.showIndex);
				$("#editCForm").find("input[name='pageCmpUrl']").val(res.result.pageCmpUrl);
			}
		}
	});
	//弹框
	$("#editCModal").modal("show");
}
//删除
function delC(id) {
	var pid = $("#pId").val();
	$.messager.confirm('提示信息','您确定要删除该菜单吗?',function(r){   
	    if (r){
	    	$.ajax({
				type: 'POST',
				url: ctx + 'menu/delEntity.do',
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
		var pageCmpUrl = $("#addCForm").find("input[name='pageCmpUrl']").val();
		var showIndex = $("#addCForm").find("input[name='showIndex']").val();
		var name = $("#addCForm").find("input[name='name']").val();
		$.ajax({
			type: 'POST',
			url: ctx + 'menu/addEntity.do',
			data: {
				'parentMenu':pid,
				'pageCmpUrl':pageCmpUrl,
				'showIndex':showIndex,
				'name':name
			},
			success: function(data){
				var res = $.parseJSON(data);
				if(!res.success){
					$.messager.alert("信息提示",res.msg);
				}else{
					$("#addCModal").modal("hide");
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
		var parentMenu = $("#editCPid").combobox('getValue');
		var pageCmpUrl = $("#editCForm").find("input[name='pageCmpUrl']").val();
		var showIndex = $("#editCForm").find("input[name='showIndex']").val();
		var name = $("#editCForm").find("input[name='name']").val();
		$.ajax({
			type: 'POST',
			url: ctx + 'menu/editEntity.do',
			data: {
				'id': id,
				'parentMenu':parentMenu,
				'pageCmpUrl':pageCmpUrl,
				'showIndex':showIndex,
				'name':name
			},
			success: function(data){
				var res = $.parseJSON(data);
				if(!res.success){
					$.messager.alert("信息提示",res.msg);
				}else{
					$("#editCModal").modal("hide");
					//加载列表
					load(pid);
				}
			}
		});
	}
}

//加载所有的数据菜单
function loadCodes(){
	$.ajax({
		type: 'POST',
		url: ctx + 'menu/getParentMenu.do',
		async : false,
		success: function(data){
			var html = "";
			var res = $.parseJSON(data);
			for( var i = 0 ; i < res.length ; i++ ){
				var temp = res[i];
//				onclick="childClick(\''+temp.id+'\')"
				html += '<li><a href="javascript:void(0)" data-id="'+temp.id+'"><i class="icon-minus-sign"></i>'+temp.name+'</a></li>';
			}
			$("#ulCode").html(html);
			$("#ulCode li a").bind("click",function(e){
				$(e.target).addClass("highred").parent("li").siblings("li").children("a").removeClass("highred");
				var codeId=$(e.target).attr("data-id");
				$("#pId").val(codeId);
				//根据id加载详情form
				$.ajax({
					type: 'POST',
					url: ctx + 'menu/getEntity.do?id='+codeId,
					async : false,
					success: function(data){
						var res = $.parseJSON(data);
						$("#detailPFrom").find("input[name='name']").val(res.result.name);
						$("#detailPFrom").find("input[name='pageCmpUrl']").val(res.result.pageCmpUrl);
						$("#detailPFrom").find("input[name='showIndex']").val(res.result.showIndex);
					}
				});
				//同时加载右侧grid
				load(codeId);
			});
		}
	});
}


//叶子节点被点击
function childClick(codeId) {
	$("#pId").val(codeId);
	//同时加载右侧grid
	load(codeId);
}
//加载右侧数据菜单
function load(parentId){
	$("#codeGrid").datagrid({
		url : ctx + 'menu/getPageModel.do?parentMenu='+parentId,
//		toolbar : toolbar,
		height : 300,
		fit : true,
		singleSelect:false,
//		frozenColumns : [ [ {
//			field : 'ck',
//			checkbox : true
//		} ] ],
		columns : [[
		    {title : '菜单名称',field : 'name',width : 1,align : 'center'},
		    {title : '菜单链接',field : 'pageCmpUrl',width : 2,align : 'center'},
		    {title : '菜单序号',field : 'showIndex',width : 0.5,align : 'center'},
		    {title : '父菜单',field : 'parentMenuName',width : 1,align : 'center'},
		    {title : '操作',field : 'opt',width : 0.5,align : 'left',
		    	formatter:function(value,rec,index){
//		    		var edit = '<button class="btn btn-primary" onclick="editC(\''+ rec.id + '\')">编辑</button> ';
//		    		var del = '<button class="btn btn-primary" onclick="delC(\''+ rec.id + '\')">删除</button> ';
//					return edit + " " + del;
		    		//操作数组
		    		var opt = new Array();
		    		var edit = '<a href="javascript:void(0)" onclick="editC(\''+ rec.id + '\')"><span class="drop-edit"></span>编辑</a> ';
		    		var del = '<a href="javascript:void(0)" onclick="delC(\''+ rec.id + '\')"><span class="drop-del"></span>删除</a> ';	
		    		opt.unshift(del);
		    		opt.unshift(edit);
		    		
		    		return createBtn(rec.id,opt);
				}
		    }
		]],
//		queryParams : me.$form.getFormValues(),
		pagination:true,
		pageNumber:1,
		pageSize:30,
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
