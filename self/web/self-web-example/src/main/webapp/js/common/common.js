var btnsCount=0;
var winzIndex = window.top.$("#winzIndex");
if(winzIndex.length <= 0){
	winzIndex = window.top.$("<input type='hidden' value='3000' id='winzIndex'  />").appendTo(window.top.document.body);
}
//提交数据是清楚缓存 针对IE提交缓存问题
$.ajaxSetup({cache:false});

var elementPermissions;

var allElements;

var isSuperAdmin;
/**
 * 全弹出模态窗口，覆盖父窗口
 * @param id 弹出框id 唯一
 * @param url 窗口需要显示页面地址
 * @param title 窗口标题
 * @param width 窗口宽度
 * @param height 窗口高度
 * @param parameters 参数集合
 * @author wangguohong
 * 
 */
function openWin(winId,url,title,width,height,parameters){
	
	var addWindow = window.top.$(winId);
	if(addWindow.length <= 0) {
		addWindow = window.self.$("<div id='"+winId+"'/>").appendTo(window.self.document.body);
	}
	
	//封装参数
	var ps = "";
	if(parameters!=null){
		
		$.each(parameters,function(idx,item){
			$.each(item.param,function(i,n){
//				if(i==0){
//					ps += "?"+n.paramName+"="+n.value;  
//				}else{
					ps += "&"+n.paramName+"="+n.value;  
//				}
			});
		});
	}
	var zIndexTemp = parseInt(window.top.$("#winzIndex").val());
	addWindow.window({
             title: title,
             href: location,
             width: width,
             height: height,
             modal:true,
             closed: false,
             closable:true,
             minimizable:false,
             maximizable:false,
             collapsible:false,
             resizable:false,
             zIndex:zIndexTemp,
             onClose : function(){ 
            	 addWindow.window("destroy"); 
             }
     });
	window.top.$("#winzIndex").val(zIndexTemp+4);
}

function openWin(winId,url,title,width,height,parameters,callback){
	
	var addWindow = window.top.$(winId);
	if(addWindow.length <= 0) {
		addWindow = window.top.$("<div id='"+winId+"'/>").appendTo(window.top.document.body);
	}
	
	//封装参数
	var ps = "";
	if(parameters!=null){
		
		$.each(parameters,function(idx,item){
			$.each(item.param,function(i,n){
				ps += "&"+n.paramName+"="+n.value;  
			});
		});
	}
	var zindex = parseInt(window.top.$("#winzIndex").val());
	if(!zindex){
		zindex="3000";
	}
	var location = encodeURI(encodeURI(url+ps));
	addWindow.window({
             title: title,
             href:location,
             width: width,
             height: height,
             modal:true,
             closed: false,
             closable:true,
             minimizable:false,
             maximizable:false,
             collapsible:false,
             resizable:false,
             zIndex:zindex,
             onClose : function(){ 
            	 addWindow.window("destroy"); 
            	 if(callback){
            		 callback();
            	 }
             }
     });
	 window.top.$("#winzIndex").val(zindex+4);
}
/**
 * 关闭窗口
 * @param winId 弹出窗口时设置的窗口ID
 */
function closeWin(winId){
	win=$('#'+winId+'');
	win.window('close');
}

/**
 * 创建上传窗口 公共方法
 * @param chunk 是否分割大文件
 * @param callBack 上传成功之后的回调
 */
function Uploader(chunk,callBack){
	var addWin = $('<div style="overflow: hidden;"/>');
	var upladoer = $('<iframe/>');
	upladoer.attr({'src':'framework/core/plugs/plupload/uploader.jsp?chunk='+chunk,width:'100%',height:'100%',frameborder:'0',scrolling:'no'});
	addWin.window({
		title:"上传文件",
		height:350,
		width:550,
		minimizable:false,
		modal:true,
		collapsible:false,
		maximizable:false,
		resizable:false,
		content:upladoer,
		onClose:function(){
			var fw = GetFrameWindow(upladoer[0]);
			var files = fw.files;
			$(this).window('destroy');
			callBack.call(this,files);
		},
		onOpen:function(){
			var target = $(this);
			setTimeout(function(){
				var fw = GetFrameWindow(upladoer[0]);
				fw.target = target;
			},100);
		}
	});
}

/**
 * 根据iframe对象获取iframe的window对象
 * @param frame
 * @returns {Boolean}
 */
function GetFrameWindow(frame){
	return frame && typeof(frame)=='object' && frame.tagName == 'IFRAME' && frame.contentWindow;
}

/**
 * 应急协查中获取应急的iframe
 * @returns {Boolean}
 */
function getEventFrameWinow2(){
	var monitorFrame = $('#eventIframe2' ,getMainFrameWindow().document)[0];
	return monitorFrame.contentWindow || monitorFrame.window;
}

/**
 * 获取内容iframe的window对象
 * @return {}
 */
function getMainFrameWindow(){
 	var iframe = $('#mainPanel > iframe' ,window.top.document)[0];
	return iframe.contentWindow || iframe.window;
}

/**
 * 应急协查中获取应急的iframe
 * @returns {Boolean}
 */
function getEventFrameWinow(){
	var monitorFrame = $('#eventIframe' ,getMainFrameWindow().document)[0];
	return monitorFrame.contentWindow || monitorFrame.window;
}

/**
 * 应急协查中获取应急的iframe
 * @returns {Boolean}
 */
function getHandleFrameWinow(){
	var handleIframe = $('#handleIframe' ,getMainFrameWindow().document)[0];
	return handleIframe.contentWindow || handleIframe.window;
}

/**
 * 上传文件
 * @param chunk 是否分割大文件
 */
function makerUpload(chunk){
	 Uploader(chunk,function(files){
		 alert(files);
//		 if(files && files.length>0){
////			 $("#res").text("成功上传："+files.join(","));
//			 alert("上传成功！");
//		 }
	 });
}

/**
 * map
 * @returns
 */
function Map() {
	 var struct = function(key, value) {
	  this.key = key;
	  this.value = value;
	 }
	 
	 var put = function(key, value){
	  for (var i = 0; i < this.arr.length; i++) {
	   if ( this.arr[i].key === key ) {
	    this.arr[i].value = value;
	    return;
	   }
	  }
	   this.arr[this.arr.length] = new struct(key, value);
	 }
	 
	 var get = function(key) {
	  for (var i = 0; i < this.arr.length; i++) {
	   if ( this.arr[i].key === key ) {
	     return this.arr[i].value;
	   }
	  }
	  return null;
	 }
	 
	 var remove = function(key) {
	  var v;
	  for (var i = 0; i < this.arr.length; i++) {
	   v = this.arr.pop();
	   if ( v.key === key ) {
	    continue;
	   }
	   this.arr.unshift(v);
	  }
	 }
	 
	 var size = function() {
	  return this.arr.length;
	 }
	 
	 var isEmpty = function() {
	  return this.arr.length <= 0;
	 }

	 this.arr = new Array();
	 this.get = get;
	 this.put = put;
	 this.remove = remove;
	 this.size = size;
	 this.isEmpty = isEmpty;
}


function initElementPermissions(){
	
	var startTime = new Date().getTime();
	isSuperAdminJudge();
	if(isSuperAdmin){
		//超级管理员
		//initSuperDataGrid();
		return;
	}
	//getAllElements();
	
	//过滤datagrid的操作按钮
	$.ajax({	
			url:"system/element/getElementPermissions.do",
			async:false,
			type:'post',
			dataType:'json',
			success: function(data){
				elementPermissions = data;
				$("a.easyui-linkbutton,a[type='export'],a.linkbutton,a").each(
					function(){
						elementId =  $(this).attr("id");
//						//console.log("页面的按钮：===" + elementId);
//						var flag = isRegister(elementId);
//						if(flag){
//							//未注册元素
//							$(this).css("display","none");
//						}else{
//							//已经注册
//							flag = getElementPermission(elementId,elementPermissions);
//							if(flag){
//								$(this).css("display","none");
//							}
//						}
						
						var flag = getElementPermission(elementId,elementPermissions);
						if(flag){
							$(this).css("display","none");
						}
					}
				);
				
			}
	})
	
	//遍历datagrid
	//initDataGrid();
	var endTime = new Date().getTime();
	//console.log("判断元素权限消耗时间毫秒数====" + (endTime-startTime));
}

function isSuperAdminJudge(){
	
	$.ajax({	
			url:"system/element/isSuperAdmin.do",
			async:false,
			type:'post',
			dataType:'json',
			success: function(data){
				isSuperAdmin = data.result;
			}
	})
}

function getAllElements(){
	
	$.ajax({	
			url:"system/element/getAllElementCode.do",
			async:false,
			type:'post',
			dataType:'json',
			success: function(data){
				allElements = data;
			}
	})
}

//判断按钮是否有权限
function getElementPermission(elementId,elementPermissions){
	
	var flag = false;
	//判断按钮是否被禁止
	$.each(elementPermissions,function(index,value){
		if($.trim(elementId+"") == $.trim(value + "")){
			//被禁止
			flag = true;
			return flag;
		}
	});
	return flag;
}

//判断按钮是否被禁用
function isRegister(elementId){
	if (elementId == undefined) {
		return false;
	}
	var flag = true;
	//判断按钮是否注册
	$.each(allElements,function(index,value){
		
		if($.trim(elementId+"") == $.trim(value + "")){
			flag = false;
			return flag;
		}
	});
	//未注册
	return flag;
}

//function initDataGrid(){
//	
//	$("[id$='Grid']").each(function(){
//	
//		$(this).datagrid({
//			onLoadSuccess:function(data){
//				var editClass;
//				var detailClass;
//				$("a[id^='edit']").each(function(){
//					
//					elementId =  $(this).attr("id");
//					editClass = $(this).attr("class");
//					console.log("页面的链接：===" + elementId);
//					var flag = isRegister(elementId);
//					if(!flag){
//						$(this).css("display","none");
//					}
//					flag = getElementPermission(elementId,elementPermissions);
//					if(!flag){
//						$(this).css("display","none");
//					}
//				});
//				
//				$("a[id^='detail']").each(function(){
//					
//					elementId =  $(this).attr("id");
//					detailClass = $(this).attr("class");
//					console.log("页面的链接：===" + elementId);
//					var flag = isRegister(elementId);
//					if(!flag){
//						$(this).css("display","none");
//					}
//					flag = getElementPermission(elementId,elementPermissions);
//					if(!flag){
//						$(this).css("display","none");
//					}
//				});
//				
//				$("." + editClass ).linkbutton({
//					text:'编辑', 
//					title:'编辑',
//					plain:true
//				});
//				$("." + detailClass).linkbutton({
//					text:'详细', 
//					title:'详细',
//					plain:true
//				});
//					
//			}
//		});
//	})	
//}


//function initSuperDataGrid(){
//	
//	$("[id$='Grid']").each(function(){
//	
//		$(this).datagrid({
//			onLoadSuccess:function(data){
//				var editClass;
//				var detailClass;
//				$("a[id^='edit']").each(function(){
//					
//					elementId =  $(this).attr("id");
//					editClass = $(this).attr("class");
//					console.log("页面的链接：===" + elementId);
//				});
//				
//				$("a[id^='detail']").each(function(){
//					
//					elementId =  $(this).attr("id");
//					detailClass = $(this).attr("class");
//					console.log("页面的链接：===" + elementId);
//				});
//				
//				$("." + editClass ).linkbutton({
//					text:'编辑', 
//					title:'编辑',
//					plain:true
//				});
//				$("." + detailClass).linkbutton({
//					text:'详细', 
//					title:'详细',
//					plain:true
//				});
//					
//			}
//		});
//	})	
//} 

//判断元素是否有权限
function isHasElementPerssion(elementId){
	
	var startTime = new Date().getTime();
	
	var isHasElementPerssion = true;
	isSuperAdminJudge();
	if(isSuperAdmin){
		//超级管理员
		return isHasElementPerssion;
	}
	getAllElements();
	
	//过滤datagrid的操作按钮
	$.ajax({	
			url:"system/element/getElementPermissions.do",
			async:false,
			type:'post',
			dataType:'json',
			success: function(data){
				elementPermissions = data;
				
				//console.log("页面的链接：===" + elementId);
//				var flag = isRegister(elementId);
//				if(!flag){
//					isHasElementPerssion = true;
//					return isHasElementPerssion;
//				}
				flag = getElementPermission(elementId,elementPermissions);
				if(!flag){
					isHasElementPerssion = true;
					return isHasElementPerssion;
				}
			}
	})
	//遍历datagrid
	var endTime = new Date().getTime();
	//console.log("判断超链接权限消耗时间毫秒数====" + (endTime-startTime));
	return isHasElementPerssion;
}

//判断元素是否有权限
//function isHasElementPerssionByArray(elementIds){
//	
//	//$("#editSysParam").css("display","none");
//	var startTime = new Date().getTime();
//	
//	isSuperAdminJudge();
//	var map = new Map();
//	
//	for (var i = 0; i < elementIds.length; i++) {
//		var elementId = elementIds[i];
//		if(isSuperAdmin){
//			//超级管理员
//			map.put(elementId,true);
//		}else{
//			getAllElements();
//	
//			//过滤datagrid的操作按钮
//			$.ajax({	
//					url:"system/element/getElementPermissions.do",
//					async:false,
//					type:'post',
//					dataType:'json',
//					success: function(data){
//						elementPermissions = data;
//						
//						console.log("页面的元素：===" + elementId);
//						var flag = isRegister(elementId);
//						if(!flag){
//							map.put(elementId,false);
//						}else{
//							flag = getElementPermission(elementId,elementPermissions);
//							if(!flag){
//								map.put(elementId,false);
//							}else{
//								map.put(elementId,true);
//							}
//						}
//					}
//			})
//		}
//		
//		
//	}
//		
//	//遍历datagrid
//	var endTime = new Date().getTime();
//	console.log("判断元素权限消耗时间毫秒数====" + (endTime-startTime));
//	return map;
//}
/**
 * @author dengqw
 * @returns {String}	返回根路径
 */

function getBasePath(){
	var hrefPath=window.document.location.href; 
	var pathName=window.document.location.pathname;
	var pos=hrefPath.indexOf(pathName); 
	var path=hrefPath.substring(0,pos);
	var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	var basePath=path+projectName+"/";
	return basePath;
}

/**
 * 表格内操作按钮，鼠标移上去之后，操作按钮下拉
 * @param {} id  为了构建层的id做唯一标示
 * @param {} btns 按钮数据，需要显示的下拉按钮
 * @return {}
 */
function createBtn(id, btns){
	btnsCount = btns.length;
	var html = '';
	if(btns != null && btns.length>0){
		html = '<span id="selectBtn'+id+'" class="list-drop"><a href="javascript:void(0);">选择</a><b class="list-drop-ico"></b></span>';
		html += '<div id="detailBtn'+id+'" class="float2-style float-drop"><ul>';
						
		$.each(btns, function(index, btn){
			html += '<li>'+btn+'</li>';
		});
		html +=	'</ul></div>';
	}
	return html;
}

/**
 * 改变详情表格的交互方式，主要表现在：1，用每次只能单机一行显示详情；2，每次选择只有一行会变色
 * @param {} grid
 * @param {} rowIndex
 */
function detailGridClickFn(grid, rowIndex){
	
	// 去掉其余列的所选样式
	grid.datagrid('clearSelections');
	grid.datagrid('selectRow', rowIndex);
	
	var rowExpanders = $('span.datagrid-row-expander');
	if(rowExpanders){
		var length = rowExpanders.length;
		for(var i=0;i<length;i++){
			grid.datagrid('collapseRow', i);
		if(i-rowIndex != 0){
				var ddv = grid.datagrid('getRowDetail',i).find('div.ddv');
				$(ddv).attr('id', 'false');
			}
		}
	}
	
	var ddv = grid.datagrid('getRowDetail',rowIndex).find('div.ddv');
	var isExpand = $(ddv).attr('id');
 	if(isExpand == 'false'){
		grid.datagrid('expandRow', rowIndex);
	 	$(ddv).attr('id', 'true');
 	}else{
 		grid.datagrid('collapseRow', rowIndex);
		$(ddv).attr('id', 'false');
 	}
 	
 	if($(ddv).attr('id') == 'false'){
 		grid.datagrid('unselectRow', rowIndex);
 	}
}

/**
 * 给表格后面的选择按钮鼠标移上去的后显示操作按钮的事件
 * @param {} rows 表格当前页的数据集
 */
function onSelectBtnEvent(rows){
	if(rows!=null && rows.length>0){
		$.each(rows, function(index, rowData){
			//列表下拉选择插件
			var selectBtn = null;
			var detailBtn = null;
			
			if(rowData.id == 'undefined' || rowData.id == undefined){
				if(rowData.serialId){
					selectBtn = $('#selectBtn'+rowData.serialId);
					detailBtn = $('#detailBtn'+rowData.serialId);
				}else if(rowData.applyId) {
					selectBtn = $('#selectBtn'+rowData.applyId);
					detailBtn = $('#detailBtn'+rowData.applyId);
				}else if(rowData.wzid){
					selectBtn = $('#selectBtn'+rowData.wzid);
					detailBtn = $('#detailBtn'+rowData.wzid);
				}else if(rowData.ztid){
					selectBtn = $('#selectBtn'+rowData.ztid);
					detailBtn = $('#detailBtn'+rowData.ztid);
				}else if(rowData.cityName){
					selectBtn = $('#selectBtn'+rowData.cityName);
					detailBtn = $('#detailBtn'+rowData.cityName);
				}else{
					selectBtn = $('#selectBtn'+rowData.serialId);
					detailBtn = $('#detailBtn'+rowData.serialId);
				}
				
			}else{
				selectBtn = $('#selectBtn'+rowData.id);
				detailBtn = $('#detailBtn'+rowData.id);
			}
			
			if(!selectBtn || !detailBtn){
				alert('请确保传入的是rec.id或者rec.serialId');
			}
			var classname= "";
			if(btnsCount>5){
				classname = "tip-yellowsimplemore";
			}else{
				classname = "tip-yellowsimple";
			}
				
			selectBtn.poshytip({
			 		className: classname,
				    content: detailBtn.html(),
				    alignTo: 'target',
				    alignX: 'center',
				    offsetY: 5,
					alignY: 'bottom',
					showTimeout:10
					//hideTimeout:1000000
				});
		});
	}
}

/**
 * 高级查询
 * @param addHeight
 */
function createaSuperSearch(addHeight){
	//高级搜索按钮位置改变
	var $br = $("<br>");
	$(".search-more>span:eq(3),.search-more>span:eq(7)").after($br); //第四个查询条件换行
	$(".search-more").hide();  
	
	$(".search-gj a").live("click",function(){
	  $(".search-button").prependTo("#search-buttom-alter");
	  $(this).html("简易搜索");
	  $(".search-more").show();
	  initHeight=$(".search").height();
	  dataGirdHeight= $("#userDailyInspectionGrid").parent().css("height");
	  var newHeight=parseInt(initHeight+addHeight);
	  var initTop=$(".xa-left").parent().css("top");
	  var initTopNum=$.trim(initTop.replace(/[^0-9]/ig,""));
	   newTopHeiht=parseInt(parseInt(initTopNum)+parseInt(addHeight));
	  $(".search").attr("style","height:"+newHeight+"px");
	  
	  
	  $(".xa-left").parent().attr("style","left: 0px; top: "+newTopHeiht+"px; width: 150px;")
	  $(".xa-right").parent().attr("style","left: 89%; top: "+newTopHeiht+"px; width: 150px;")
	  $(".layout-panel-center").attr("style","left: 150px; top: "+newTopHeiht+"px;")
	  $("#userDailyInspectionGrid").parent().attr("style","height:"+(parseInt(dataGirdHeight)-parseInt(addHeight))+"px")
	})
	
	$("#search-buttom-alter .search-gj a").live("click",function(){
	  var $button =$(".search-one>span:eq(3)");
	  var $button2 =$(".search-one>span:eq(2)");
	 $(".search-button").insertAfter($button);
	 $(".search-button").insertAfter($button2);
	 $(this).html("高级搜索");
	  var easyheight=parseInt(newTopHeiht)-parseInt(addHeight)*2;
	  $(".search").attr("style","height:"+(parseInt(initHeight)-parseInt(addHeight))+"px");
	  $(".xa-left").parent().attr("style","left: 0px; top: "+easyheight+"px; width: 150px;")
	  $(".xa-right").parent().attr("style","left:  89%; top: "+easyheight+"px; width: 150px;")
	  $(".layout-panel-center").attr("style","left: 150px; top: "+easyheight+"px;")
	  $("#userDailyInspectionGrid").parent().attr("style","height:"+(parseInt(dataGirdHeight)+parseInt(addHeight))+"px")
	 $(".search-more").hide();
	})
	
}


/**
 * 高级查询(针对普通列表)
 * @param addHeight girdId
 */
function createaCommonSuperSearch(addHeight,girdId){
	//高级搜索按钮位置改变
	var $br = $("<br>");
	$(".search-more>span:eq(3),.search-more>span:eq(7)").after($br); //第四个查询条件换行
	$(".search-more").hide();  
	
	$(".search-gj a").live("click",function(){
	  $(".search-button").prependTo("#search-buttom-alter");
	  $(this).html("简易搜索");
	  $(".search-more").show();
	  initHeight=$(".search").height();
	  dataGirdHeight= $("#provinceTLDGrid").parent().css("height");
	  var newHeight=parseInt(initHeight+addHeight);
	  var initTop=$(".layout-panel-center").css("top");
	  var initTopNum=$.trim(initTop.replace(/[^0-9]/ig,""));
	  newTopHeiht=parseInt(parseInt(initTopNum)+parseInt(addHeight));
	  $(".search").attr("style","height:"+newHeight+"px");
	  
	  
	  $(".layout-panel-center").attr("style","left: 0px; top: "+newTopHeiht+"px;")
	  $("#"+girdId).parent().attr("style","height:"+(parseInt(dataGirdHeight)-parseInt(addHeight))+"px")
	})
	
	$("#search-buttom-alter .search-gj a").live("click",function(){
	  var $button =$(".search-one>span:eq(3)");
	  var $button1 =$(".search-one>span:eq(1)");
	 $(".search-button").insertAfter($button);
	 $(".search-button").insertAfter($button1);
	 $(this).html("高级搜索");
	  var easyheight=parseInt(newTopHeiht)-parseInt(addHeight)*2;
	  $(".search").attr("style","height:"+(parseInt(initHeight)-parseInt(addHeight))+"px");
	  $(".layout-panel-center").attr("style","left: 0px; top: "+easyheight+"px;")
	  $("#"+girdId).parent().attr("style","height:"+(parseInt(dataGirdHeight)+parseInt(addHeight))+"px")
	 $(".search-more").hide();
	})
	
}

/**
 * 阻止事件传递
 * @param {} e
 */
function stopBubble() {
	var e = e || window.event;
	if(window.event) { // IE
		e.cancelBubble = true; 
	}else{ // FF
		e = getEvent(stopBubble.caller);
		if(e != null){
			e.stopPropagation(); 
		}
	}
}

/**
 * IE FF Chrome浏览器获取兼容的event对象
 * @param {} caller
 * @return {}
 */
function getEvent(caller){ 
	if($.browser.msie) 
		return window.event; //For IE. 
	if(caller == null || typeof(caller) != "function") 
		return null; 
	while(caller.caller != null){ // for FF
		caller = caller.caller; 
	} 
	return caller.arguments[0]; 
} 

/**
 * 主内容面板页面跳转
 * @param {} url  页面跳转路径
 * @param {} id 页面的唯一ID， 数据库中的FUNC_CODE字段
 * @param {} name 和id相同
 */
function redirectPage(url, id, name){
	var iframe = $('#mainPanel > iframe' ,window.top.document)[0];
	if(iframe){
		if(url){
			iframe.src = PROJECT_NAME + url;
		}
		if(id){
			$(iframe).attr('id', 'f_'+id);
		}
		if(name){
			$(iframe).attr('name', 'f_'+name);
		}else{
			$(iframe).attr('name', 'f_'+id);
		}
	}
}

/**
 * 弹出策略运行状态详情窗口
 * @param commandFileId
 */
function openStateDetailWin(commandFileId,commandType){
	var params = [{param:[{paramName:"commandFileId",value:commandFileId},{paramName:"commandType",value:commandType}]}];
	openWin("strategyStateDetailWin","system/redirect/redirectHomePage.do?path=is/strategyState_detail","状态详情",800,450,params);
	stopBubble();
}

/**
 * 是否为直辖市
 * @returns {Boolean}
 */
function isMunicipality(){
	if(PROVINCE_AREA_CODE_LIKE == '11'){
		return true;
	}else if(PROVINCE_AREA_CODE_LIKE == '12'){
		return true;
	}else if(PROVINCE_AREA_CODE_LIKE == '31'){
		return true;
	}else if(PROVINCE_AREA_CODE_LIKE == '50'){
		return true;
	}
	return false;
}

/**数组删除
 * val  		删除的元素
 * destArray  	目标数组
 */
function arrayRemove(val,destArray){
	for (var i = 0; i < destArray.length; i++) {
	    if( val == destArray[i] ){
	    	destArray.splice(i, 1);
	    }
    }
}