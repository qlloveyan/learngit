/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.42
 * Generated at: 2016-07-16 11:34:25 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.public_;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class uploader_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');

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

      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("<title></title>\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("\thref=\"js/plugs/plupload/queue/css/jquery.plupload.queue.css\"\r\n");
      out.write("\ttype=\"text/css\"></link>\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/easyui/jquery-1.8.0.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/plugs/plupload/plupload.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/plugs/plupload/plupload.html4.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/plugs/plupload/plupload.html5.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/plugs/plupload/plupload.flash.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/plugs/plupload/zh_CN.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/plugs/plupload/queue/jquery.plupload.queue.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("$(function() {\r\n");
      out.write("\tvar files = [];\r\n");
      out.write("\tvar errors = [];\r\n");
      out.write("\tvar type = 'file';\r\n");
      out.write("\tvar chunk = true;\r\n");
      out.write("\tvar resouse =\"");
      out.print(resouse);
      out.write("\";\r\n");
      out.write("\tvar resouseId =\"");
      out.print(resouseId);
      out.write("\";\r\n");
      out.write("\tvar max_file_size = \"");
      out.print(maxSize);
      out.write("\";\r\n");
      out.write("\tvar filters=");
      out.print(filters);
      out.write(";\r\n");
      out.write("\tif(!filters.extensions){\r\n");
      out.write("\t\tfilters=[];\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\tfilters=[];\r\n");
      out.write("\t\tfilters[0]=");
      out.print(filters);
      out.write(";\r\n");
      out.write("\t}\r\n");
      out.write("\tvar maxCount=");
      out.print(maxCount);
      out.write(";\r\n");
      out.write("\tvar isDataImport=\"");
      out.print(isDataImport);
      out.write("\";\r\n");
      out.write("\tvar callback = \"");
      out.print(callback);
      out.write("\";\r\n");
      out.write("\tvar importurl = \"");
      out.print(importurl);
      out.write("\";\r\n");
      out.write("\t\r\n");
      out.write("\tvar startbutton = null;\r\n");
      out.write("\tvar des = \"<div class='upload-sm'>\";\r\n");
      out.write("\tdes += \"单个文件最大不超过\"+max_file_size+\"; &nbsp;&nbsp;\";\r\n");
      out.write("\tdes += \"批量上传文件个数不超过\"+maxCount+\"个; &nbsp;&nbsp;\";\r\n");
      out.write("\tif(filters.length>0){\r\n");
      out.write("\t\tdes += \"上传文件格式为:\"+filters[0].extensions+\"\";\r\n");
      out.write("\t}\r\n");
      out.write("\tdes += \"</div>\";\r\n");
      out.write("\t//var filters = {title : \"文档\", extensions : \"zip,doc,docx,xls,xlsx,ppt,pptx\"};\r\n");
      out.write("\t\r\n");
      out.write("\t$(\"#uploader\").pluploadQueue($.extend({\r\n");
      out.write("\t\truntimes : 'flash,html4,html5',\r\n");
      out.write("\t\turl : '");
      out.print(basePath);
      out.write("framework/filehandle/fileHandle/upload.do?resouseCode='+resouse+'&resouseId='+resouseId,\r\n");
      out.write("\t\tmax_file_size : max_file_size,\r\n");
      out.write("\t\tfile_data_name:'file',\r\n");
      out.write("\t\tfilters : filters,\r\n");
      out.write("\t\tdragdrop : false,\r\n");
      out.write("\t\tflash_swf_url : 'js/plugs/plupload/plupload.flash.swf',\r\n");
      out.write("\t\t//设置说明文字\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tinit:{\r\n");
      out.write("\t\t\tInit:function(uploader){\r\n");
      out.write("\t\t\t\tstartbutton = $(\".plupload_start\");\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t$(\".plupload_header\").html(des);\r\n");
      out.write("\t\t\t\t//清理session中的上传路径\r\n");
      out.write("\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tQueueChanged:function(uploader){\r\n");
      out.write("\t\t\t\tif(uploader.files.length>maxCount){\r\n");
      out.write("\t\t\t\t\tvar error = \"<div class='upload-sm'>\";\r\n");
      out.write("\t\t\t\t\terror += \"<font color=red>最多只能上传\"+maxCount+\"个</font>\";\r\n");
      out.write("\t\t\t\t\terror += \"</div>\";\r\n");
      out.write("\t\t\t\t\t$(\".plupload_header\").html(error);\r\n");
      out.write("\t\t\t\t\tstartbutton.hide();\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t$(\".plupload_header\").html(des);\r\n");
      out.write("\t\t\t\t\tstartbutton.show();\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tFileUploaded:function(uploader,file,response){\r\n");
      out.write("\t\t\t\tif(response.response){\r\n");
      out.write("\t\t\t\t\tvar rs = $.parseJSON(response.response);\r\n");
      out.write("\t\t\t\t\tconsole.log(rs);\r\n");
      out.write("\t\t\t\t\tif(rs.status){\r\n");
      out.write("\t\t\t\t\t\tfiles.push(file.name);\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\terrors.push(file.name);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tUploadProgress:function(uploader,fs){\r\n");
      out.write("\t\t\t\tvar begin = \"<div class='upload-sm'>\";\r\n");
      out.write("\t\t\t\tbegin +=\"开始上传文件<img  src='js/plugs/plupload/queue/img/uploading.gif'>\";\r\n");
      out.write("\t\t\t\tbegin +=\"</div>\";\r\n");
      out.write("\t\t\t\t$(\".plupload_header\").html(begin);\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t//上传完毕后触发\r\n");
      out.write("\t\t\tUploadComplete:function(uploader,fs){\r\n");
      out.write("\t\t\t\tvar e= errors.length ? \",失败\"+errors.length+\"个(\"+errors.join(\"、\")+\")。\" : \"。\";\r\n");
      out.write("\t\t\t\tvar begin = \"<div class='upload-sm'>\";\r\n");
      out.write("\t\t\t\tvar value=parseInt(fs.length)-parseInt(errors.length);\r\n");
      out.write("\t\t\t\tbegin +=\"上传完成！共\"+fs.length+\"个。成功\"+value+\"个\";\r\n");
      out.write("\t\t\t\tbegin +=\"</div>\";\r\n");
      out.write("\t\t\t\t$(\".plupload_header\").html(begin);\r\n");
      out.write("\t\t\t\t//var e= errors.length ? \",失败\"+errors.length+\"个(\"+errors.join(\"、\")+\")。\" : \"。\";\r\n");
      out.write("\t\t\t\t//alert(\"上传完成！共\"+fs.length+\"个。成功\"+files.length+e);\r\n");
      out.write("\t\t\t\t//target.window(\"close\");\r\n");
      out.write("\t\t\t\t//console.log(request);\r\n");
      out.write("\t\t\t\t//console.log(uploader);\r\n");
      out.write("\t\t\t\tif(isDataImport==\"true\"){\r\n");
      out.write("\t\t\t\t\tif(callback!=\"\"){\r\n");
      out.write("\t\t\t\t\t\timportdata(importurl,callback);\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\tvar noback = \"<div class='upload-sm'>\";\r\n");
      out.write("\t\t\t\t\t\tnoback += \"<font color=red>找不到回调函数</font>\";\r\n");
      out.write("\t\t\t\t\t\tnoback += \"</div>\";\r\n");
      out.write("\t\t\t\t\t\t$(\".plupload_header\").html(error);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\tif(callback==\"showimg\"){\r\n");
      out.write("\t\t\t\t\t\tuploadcallback(callback);\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t\tuploadfilecallback(callback);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t},(chunk ? {chunk_size:'1mb'} : {})));\r\n");
      out.write("\t\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("function uploadfilecallback(callback){\r\n");
      out.write("\twindow.parent.callback.call(this);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function uploadcallback(callback){\r\n");
      out.write("\t\r\n");
      out.write("\t//console.log(getMainFrameWindow().showimg());\r\n");
      out.write("\tgetMainFrameWindow().showimg();\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function getMainFrameWindow(){\r\n");
      out.write("\tvar iframe = $('#mainPanel > iframe' ,window.top.document)[0];\r\n");
      out.write("\treturn iframe.contentWindow || iframe.window;\r\n");
      out.write("}\r\n");
      out.write("function importdata(importurl,callback){\r\n");
      out.write("\t//alert(callback);\r\n");
      out.write("\tvar des = \"<div class='upload-sm'>\";\r\n");
      out.write("\tdes +=\"正在解析入库<img  src='js/plugs/plupload/queue/img/uploading.gif'>\";\r\n");
      out.write("\tdes +=\"</div>\";\r\n");
      out.write("\t$(\".plupload_header\").html(des);\r\n");
      out.write("\t//alert(justpath);\r\n");
      out.write("\t\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("\t\turl:importurl,\r\n");
      out.write("\t\ttype:\"POST\",\r\n");
      out.write("\t\tdataType: \"json\",\r\n");
      out.write("\t\tsuccess: function (data) {\r\n");
      out.write("\t\t\tvar msg = \"<div class='upload-sm'>\";\r\n");
      out.write("\t\t\tmsg +=data.msg;\r\n");
      out.write("\t\t\tmsg +=\"</div>\";\r\n");
      out.write("\t\t\t$(\".plupload_header\").html(msg);\r\n");
      out.write("\t\t\t//$(\".plupload_header\").html(data.message);\r\n");
      out.write("\t\t\t//target.window(\"close\");\r\n");
      out.write("\t\t\t//f_TrustedSite\r\n");
      out.write("\t\t\t/* var frames = $(window.document).find(\"iframe\");\r\n");
      out.write("\t\t\tconsole.log(frames); */\r\n");
      out.write("\t\t\tif(window.parent.callback){\r\n");
      out.write("\t\t\t\t//窗口\r\n");
      out.write("\t\t\t\twindow.parent.callback.call(this);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<body style=\"padding: 0; margin: 0;\">\r\n");
      out.write("<form id=\"form\">\r\n");
      out.write("<div id=\"uploader\">\r\n");
      out.write("</div>\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
