var riskSuffix = "exe,com,cmd,bat,tmp,dll,sys,vbs,js,reg,scr";
/**
 * 封装上传组件
 * 提供上传下载功能
 * framework/filehandle/fileHandle/upload.do
 */
$(function() {
	var uploadsubmenu = '<div id="upload_sub" style="width:100px;">'+
	'<div id="file_updload">上传文件</div>'+
	'<div id="download_templ"><span class="exportDownload"></span>模板下载</div>'+
	'<h1 class="excel-line" id="line1"></h1>'+
	'<div id="import_data"><span class="exportData"></span>导入数据</div>'+
	//'<h1 class="excel-line"></h1>'+
	'</div>';
	if($("#upload_sub").length<1){
		$("body").append(uploadsubmenu);
	}
	$("#upload_sub").hide();
	var isDataImport = $('#import').attr("isDataImport");
	var fileupload = $('#import').attr("fileupload");
	var isTempDown = $('#import').attr("isTempDown");
	var isOnlyUpload=false;
	if(fileupload=="false"){
		$("#file_updload").hide();
		isOnlyUpload=false;
	}else{
		isOnlyUpload=true;
	}
	//判断是否提供模板下载
	if(isTempDown=="false"){
		$("#download_templ").hide();
		$("#line1").attr("style","display:none");
		$("#file_updload").hide();
	}
	
	//判断是否需要导入功能
	if(isDataImport!="true"){
		$("#download_templ").hide();
		$("#import_data").hide();
		
	}
	//初始化文件处理菜单
	if(!isOnlyUpload){
		
		var importMenu = $('#import').menubutton({ 
			menu: "#upload_sub",
			iconCls:"icon-import"
		}); 
	}else{
		$('#import').addClass("btn");
		$('#import').click(function(){
			onlayUpload();
		});
	}
	
	try{
		$(importMenu.menubutton('options').menu).menu({
	        onClick: function (item) {
	        	var $import = $('#import');
	        	var resouseCode = $import.attr("resouseCode");
	        	var resouseId = $import.attr("resouseId");
	        	var chooseTitle = $import.attr("chooseTitle");
	        	var suffix = $import.attr("suffix");
	        	//封装过滤对象
	        	var filters = [];
	        	if(chooseTitle!="" && suffix!=""){
	        		filters = new Object();
	        		filters.title=chooseTitle;
	        		filters.extensions = suffix;
	        	}
	        	var maxSize = $import.attr("maxSize");
	        	var maxCount = $import.attr("maxCount");
	        	var importurl = $import.attr("importurl");
	        //	alert(importurl);
	        	
	        	
	        	if(item.id=="file_updload"){
	        		var callback = $import.attr("callback");//获取回调函数
	        		//alert(callback);
	        		makerUpload(true,resouseCode, resouseId,json2str(filters),maxSize,maxCount,"false",importurl,callback);
	        	}
	        	if(item.id=="download_templ"){
	        		loadTemplate(resouseCode);
	        	}
	        	if(item.id=="import_data"){
	        		var callback = $import.attr("callback");//获取回调函数
	        		makerUpload(true,resouseCode, resouseId,json2str(filters),maxSize,maxCount,"true",importurl,callback);
	        	}
	        }
		});
	}catch(err){
	
	}
	
});

function onlayUpload(){
	var $import = $('#import');
	var resouseCode = $import.attr("resouseCode");
	var resouseId = $import.attr("resouseId");
	var chooseTitle = $import.attr("chooseTitle");
	var suffix = $import.attr("suffix");
	//封装过滤对象
	var filters = [];
	if(chooseTitle!="" && suffix!=""){
		filters = new Object();
		filters.title=chooseTitle;
		filters.extensions = suffix;
	}
//	filters.title=chooseTitle;
//	filters.extensions = suffix;
	var maxSize = $import.attr("maxSize");
	var maxCount = $import.attr("maxCount");
	var importurl = $import.attr("importurl");
	var callback = $import.attr("callback");//获取回调函数
	//alert(callback);
	makerUpload(true,resouseCode, resouseId,json2str(filters),maxSize,maxCount,"false",importurl,callback);
}

/**
 * 根据资源编号获取输入导入模板
 * @param resouseCode
 */
function loadTemplate(resouseCode){
	
	$.ajax({
		url:"framework/filehandle/fileHandle/loadDataModel.do",
		data:{resourceCode:resouseCode},
		dataType: "json",
		success: function (data) {
		   var templatediv = '<div id="templatediv" style="width:400px;height:200px;">'+
							 '</div>';
		   $("body").append(templatediv);
		   var templistspan = "";
		   templistspan +='<div class="download-list">'
		   $.each(data.result,function(index,value){
			   templistspan += '<span><a href="framework/filehandle/fileHandle/downloadtemplate.do?path='+encodeURI(encodeURI(value.templagePath))+'&filename='+encodeURI(encodeURI( value.templateFileName))+'">'+value.templateFileName+'</a></span>'
//			   console.log(value.templagePath);
//			   alert(value.templagePath);
		   });
		   templistspan +='</div>';
		   $("#templatediv").html(templistspan);
		   //弹出下载模板
		   $("#templatediv").dialog({
			   title:"下载模板",
			   modal:true  
		   });
		}
	});
}
/**
 * 创建上传窗口 公共方法
 * @param chunk 是否分割大文件
 * @param callBack 上传成功之后的回调
 */
function Uploader(chunk,resouseCode,resouseId,filters,maxSize,maxCount,isDataImport,importurl,callback,callBackselft){
	//这里为div添加一个id，那么在文件上传成功后，在回调函数里可以关闭窗口。
	var addWin = $('<div  id="uploaderWin" style="overflow: hidden;"/>');
	var upladoer = $('<iframe/>');
	upladoer.attr({
				src:'public/uploader.jsp?chunk='+chunk+
				'&resouse='+resouseCode+
				'&resouseId='+resouseId+
				'&filters='+filters+
				'&maxSize='+maxSize+
				'&isDataImport='+isDataImport+
				'&importurl='+importurl+
				'&callback='+callback+
				'&maxCount='+maxCount,
				width:'100%',
				height:'100%',
				frameborder:'0',
				scrolling:'no'
			});
	addWin.window({
		title:"上传文件",
		height:360,
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
//			console.log(fw);
//			alert(fw);
			$(this).window('destroy');
			callBackselft.call(this,files);
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
 
function makerUpload(chunk,resouseCode,resouseId,filters,maxSize,maxCount,isDataImport,importurl,callback){
 Uploader(chunk,resouseCode,resouseId,filters,maxSize,maxCount,isDataImport,importurl,callback,function(files){
	 if(files && files.length>0){
		//这里以后可以添加成功上传了哪些文件
		//回调函数处理上传成功后的任务
		// $("#res").text("成功上传："+files.join(","));
		// console.log(files);
		//alert(files)
	 }
 });
}

/**
 * json对象转换成字符串
 * @param obj
 * @returns {String}
 */
function json2str(obj){
	  var S = [];
	  for(var i in obj){
	    obj[i] = typeof obj[i] == 'string'?'"'+obj[i]+'"':(typeof obj[i] == 'object'?json2str(obj[i]):obj[i]);
	    S.push(i+':'+obj[i]); 
	  }
	 // alert('{'+S.join(',')+'}');
	  return '{'+S.join(',')+'}';
}

/**
 * 下载文件
 * @param filePath 文件路径
 * @param fileName 文件名称
 */
function downFile(filePath, fileName){
	 $.messager.confirm('提示','确定要下载文件吗?',function(result){  
         if (result){
        	 var url = 'framework/filehandle/fileHandle/downOpenFile.do?filePath='+filePath+'&fileName=';
        	 url = encodeURI(encodeURI(url)+encodeURI(fileName).replace(/\(/g,"%28").replace(/\)/g,"%29").replace(/\+/g,"%2B").replace(/\#/g,"%23").replace(/\&/g,"%26"));
        	 window.open(url);
         }  
	 });  
}
