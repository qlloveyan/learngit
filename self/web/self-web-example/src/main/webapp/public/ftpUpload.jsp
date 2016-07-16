<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String resouse = request.getParameter("resouse");
    String resouseId = request.getParameter("resouseId");
    String filters = request.getParameter("filters");
    String maxSize = request.getParameter("maxSize");
    String maxCount = request.getParameter("maxCount");
    String isDataImport = request.getParameter("isDataImport");
    String callback = request.getParameter("callback");
    String importurl = request.getParameter("importurl");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title></title>
<link rel="stylesheet"
	href="js/plugs/plupload/queue/css/jquery.plupload.queue.css"
	type="text/css"></link>
<script type="text/javascript" src="js/easyui/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/plugs/plupload/plupload.js"></script>
<script type="text/javascript" src="js/plugs/plupload/plupload.html4.js"></script>
<script type="text/javascript" src="js/plugs/plupload/plupload.html5.js"></script>
<script type="text/javascript" src="js/plugs/plupload/plupload.flash.js"></script>
<script type="text/javascript" src="js/plugs/plupload/zh_CN.js"></script>
<script type="text/javascript" src="js/plugs/plupload/queue/jquery.plupload.queue.js"></script>
<script type="text/javascript">
$(function() {
	var files = [];
	var errors = [];
	var type = 'file';
	var chunk = true;
	var resouse ="<%=resouse%>";
	var resouseId ="<%=resouseId%>";
	var max_file_size = "<%=maxSize%>";
	var filters=<%=filters%>;
	if(!filters.extensions){
		filters=[];
	}else{
		filters=[];
		filters[0]=<%=filters%>;
	}
	var maxCount=<%=maxCount%>;
	var isDataImport="<%=isDataImport%>";
	var callback = "<%=callback%>";
	var importurl = "<%=importurl%>";
	
	var startbutton = null;
	var des = "<div class='upload-sm'>";
	des += "单个文件最大不超过"+max_file_size+"; &nbsp;&nbsp;";
	des += "批量上传文件个数不超过"+maxCount+"个; &nbsp;&nbsp;";
	if(filters.length>0){
		des += "上传文件格式为:"+filters[0].extensions+"";
	}
	des += "</div>";
	//var filters = {title : "文档", extensions : "zip,doc,docx,xls,xlsx,ppt,pptx"};
	
	$("#uploader").pluploadQueue($.extend({
		runtimes : 'flash,html4,html5',
		url : '<%=basePath%>oa/documentManage/upload.do?resouseCode='+resouse+'&resouseId='+resouseId,
		max_file_size : max_file_size,
		file_data_name:'file',
		filters : filters,
		dragdrop : false,
		flash_swf_url : 'js/plugs/plupload/plupload.flash.swf',
		//设置说明文字
		
		init:{
			Init:function(uploader){
				startbutton = $(".plupload_start");
				
				$(".plupload_header").html(des);
				//清理session中的上传路径
				<%-- <%request.getSession().setAttribute("justpath", "");%> --%>
			},
			QueueChanged:function(uploader){
				if(uploader.files.length>maxCount){
					var error = "<div class='upload-sm'>";
					error += "<font color=red>最多只能上传"+maxCount+"个</font>";
					error += "</div>";
					$(".plupload_header").html(error);
					startbutton.hide();
				}else{
					$(".plupload_header").html(des);
					startbutton.show();
				}
			},
			FileUploaded:function(uploader,file,response){
				if(response.response){
					var rs = $.parseJSON(response.response);
					console.log(rs);
					if(rs.status){
						files.push(file.name);
					}else{
						errors.push(file.name);
					}
				}
			},
			UploadProgress:function(uploader,fs){
				var begin = "<div class='upload-sm'>";
				begin +="开始上传文件<img  src='js/plugs/plupload/queue/img/uploading.gif'>";
				begin +="</div>";
				$(".plupload_header").html(begin);
			},
			//上传完毕后触发
			UploadComplete:function(uploader,fs){
				var e= errors.length ? ",失败"+errors.length+"个("+errors.join("、")+")。" : "。";
				var begin = "<div class='upload-sm'>";
				var value=parseInt(fs.length)-parseInt(errors.length);
				begin +="上传完成！共"+fs.length+"个。成功"+value+"个";
				begin +="</div>";
				$(".plupload_header").html(begin);
				//var e= errors.length ? ",失败"+errors.length+"个("+errors.join("、")+")。" : "。";
				//alert("上传完成！共"+fs.length+"个。成功"+files.length+e);
				//target.window("close");
				//console.log(request);
				//console.log(uploader);
				if(isDataImport=="true"){
					if(callback!=""){
						importdata(importurl,callback);
					}else{
						var noback = "<div class='upload-sm'>";
						noback += "<font color=red>找不到回调函数</font>";
						noback += "</div>";
						$(".plupload_header").html(error);
					}
				}else{
					if(callback=="showimg"){
						uploadcallback(callback);
					}else{
							uploadfilecallback(callback);
					}
				}
				
			}
		}
	},(chunk ? {chunk_size:'1mb'} : {})));
	
});

function uploadfilecallback(callback){
	window.parent.callback.call(this);
}

function uploadcallback(callback){
	
	//console.log(getMainFrameWindow().showimg());
	getMainFrameWindow().showimg();
}

function getMainFrameWindow(){
	var iframe = $('#mainPanel > iframe' ,window.top.document)[0];
	return iframe.contentWindow || iframe.window;
}
function importdata(importurl,callback){
	//alert(callback);
	var des = "<div class='upload-sm'>";
	des +="正在解析入库<img  src='js/plugs/plupload/queue/img/uploading.gif'>";
	des +="</div>";
	$(".plupload_header").html(des);
	//alert(justpath);
	
	$.ajax({
		url:importurl,
		type:"POST",
		dataType: "json",
		success: function (data) {
			var msg = "<div class='upload-sm'>";
			msg +=data.msg;
			msg +="</div>";
			$(".plupload_header").html(msg);
			//$(".plupload_header").html(data.message);
			//target.window("close");
			//f_TrustedSite
			/* var frames = $(window.document).find("iframe");
			console.log(frames); */
			if(window.parent.callback){
				//窗口
				window.parent.callback.call(this);
			}
		}
	});
}
</script>
<body style="padding: 0; margin: 0;">
<form id="form">
<div id="uploader">
</div>
</form>

</body>
</html>
