/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.42
 * Generated at: 2016-07-15 12:43:41 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.system;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class dictionary_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("\t<base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("\t<meta http-equiv=\"Cache-Control\" CONTENT=\"no-cache\">\r\n");
      out.write("\t<title>数据字典</title>\r\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../../public/common2.jsp", out, false);
      out.write("\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/modules/system/dictionary.js\"></script>\r\n");
      out.write("   \t\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"easyui-layout\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t<div data-options=\"region:'west',title:'字典类型',split:true\" style=\"width:330px;\">\r\n");
      out.write("\t\t<table id=\"dictionaryTypeGrid\"></table>\r\n");
      out.write("\t\t<div id=\"typeTb\" style=\"padding:0px;height:auto;\">\r\n");
      out.write("\t\t\t<div class=\"list-add list-add-float\">\r\n");
      out.write("\t\t\t\t<a href=\"javascript:void(0)\"  id=\"addTypeDictionary\" plain=\"true\">新增</a>\r\n");
      out.write("\t\t\t\t<a href=\"javascript:void(0)\"  id=\"editTypeDictionary\" plain=\"true\">编辑</a>\r\n");
      out.write("\t\t\t\t<a href=\"javascript:void(0)\"  id=\"delTypeDictionary\" plain=\"true\">删除</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>  \r\n");
      out.write("\t<div data-options=\"region:'center',title: '字典详情'\">\r\n");
      out.write("\t \t<table id=\"dictionaryGrid\"></table>\r\n");
      out.write("\t \t<div id=\"tb\" style=\"padding:0px;height:auto;\">\r\n");
      out.write("\t\t\t<div class=\"list-add list-add-float\">\r\n");
      out.write("\t\t\t\t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconCls=\"icon-add\"  id=\"addDictionary\" plain=\"true\">新增</a>\r\n");
      out.write("\t\t\t\t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconCls=\"icon-edit\" id=\"editDictionary\" plain=\"true\">编辑</a>\r\n");
      out.write("\t\t\t\t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconCls=\"icon-remove\" id=\"delDictionary\" plain=\"true\">删除</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<div id=\"typeAddWin\" class=\"easyui-window\" title=\"字典类型\" style=\"width:500px;height:180px;padding:5px;\" closed=\"true\" data-options=\"iconCls:'icon-save',modal:true,minimizable:false,maximizable:false,collapsible:false\">  \r\n");
      out.write("\t\t<form id=\"typeAddForm\" method=\"post\">\r\n");
      out.write("\t\t\t<input id=\"dictionary-type-id\" type=\"hidden\" name=\"id\"/>\r\n");
      out.write("\t\t\t<input id=\"dictionary-type-parent-id\" type=\"hidden\" name=\"parentId\"/>\r\n");
      out.write("\t\t\t<fieldset>\r\n");
      out.write("\t\t\t\t<table style=\"width: 100%\">\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>类型名称<span style=\"color:red;\">*</span>：<input id=\"code-value-id\" style=\"width:200px;\" class=\"easyui-validatebox\" type=\"text\" name=\"codeValue\" data-options=\"required:true\" validType=\"length[0,20]\"/></td>\r\n");
      out.write("\t\t\t\t\t\t<td style=\"color: gray;\">类型名称不超过10个字</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>字典类型<span style=\"color:red;\">*</span>：<input id=\"code-type-id\" style=\"width:200px;\" class=\"easyui-validatebox\" type=\"text\" name=\"codeType\" data-options=\"required:true\" validType=\"length[0,100]\"/></td>\r\n");
      out.write("\t\t\t\t\t\t<td style=\"color: gray;\">字典类型不能重复</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t \t\t\t</table>\r\n");
      out.write("\t\t\t</fieldset>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t\t<div style=\"text-align:center;padding:5px\">\r\n");
      out.write("\t\t   \t<a href=\"javascript:void(0)\" id=\"typeAddWinSave\"class=\"easyui-linkbutton\" >保存</a>\r\n");
      out.write("\t\t   \t<a href=\"javascript:void(0)\" id=\"typeAddWinClosed\"class=\"easyui-linkbutton\">取消</a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t<div id=\"addWin\" class=\"easyui-window\" title=\"字典\" closed=\"true\" style=\"width: 600px;height: 180px;\" data-options=\"zIndex:1000,modal:true,minimizable:false,maximizable:false,collapsible:false\">\r\n");
      out.write("\t\t<form id=\"dictionaryAddFrom\"  method=\"post\">\r\n");
      out.write("\t\t<input id=\"dictionary-id\" type=\"hidden\" name=\"id\"/>\r\n");
      out.write("\t\t<input id=\"dictionary-code-type\" type=\"hidden\" name=\"codeType\"/>\r\n");
      out.write("\t\t<input id=\"dictionary-parent-id\" type=\"hidden\" name=\"parentId\"/>\r\n");
      out.write("\t\t\t<!-- 添加 -->\r\n");
      out.write("\t\t\t<div style=\"padding:5px 0 5px 60px\">\r\n");
      out.write("\t    \t\t<table>\r\n");
      out.write("\t    \t\t\t<tr>\r\n");
      out.write("\t\t    \t\t\t<td align=\"right\">字典编码:</td>\r\n");
      out.write("\t\t    \t\t\t<td><input class=\"easyui-validatebox input-big\" type=\"text\"  name=\"codeKey\" data-options=\"required:true,validType:['length[0,50]']\"></input></td>\r\n");
      out.write("\t\t    \t\t\t<td align=\"right\">字典名称:</td>\r\n");
      out.write("\t\t    \t\t\t<td><input class=\"easyui-validatebox input-big\" type=\"text\"  name=\"codeValue\" data-options=\"required:true,validType:['length[0,100]']\"></input></td>\r\n");
      out.write("\t\t    \t\t</tr>\r\n");
      out.write("\t\t    \t\t<tr>\r\n");
      out.write("\t\t    \t\t\t<td align=\"right\">字典排序:</td>\r\n");
      out.write("\t\t    \t\t\t<td><input class=\"easyui-numberbox input-big\" type=\"text\"  name=\"codeSort\" data-options=\"required:true\"></input></td>\r\n");
      out.write("\t\t    \t\t\t<td align=\"right\">描述:</td>\r\n");
      out.write("\t\t    \t\t\t<td><input class=\"easyui-validatebox input-big\" type=\"text\"  name=\"description\" data-options=\"required:true,validType:['length[0,100]']\"></input></td>\r\n");
      out.write("\t\t    \t\t</tr>\r\n");
      out.write("\t\t    \t\t<tr>\r\n");
      out.write("\t\t    \t\t\t<td align=\"right\">是否可操作:</td>\r\n");
      out.write("\t\t    \t\t\t<td><input  type=\"text\" id=\"isOperate\" name=\"isOperate\" class=\"input-big\" data-options=\"required:true\"></input></td>\r\n");
      out.write("\t\t    \t\t\t<td align=\"right\"></td>\r\n");
      out.write("\t\t    \t\t\t<td></td>\r\n");
      out.write("\t\t    \t\t</tr>\r\n");
      out.write("\t\t    \t</table>\r\n");
      out.write("\t\t    </div>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t\t<div style=\"text-align:center;padding:5px\">\r\n");
      out.write("\t\t   \t<a href=\"javascript:void(0)\" id=\"dictionaryAdd\" class=\"easyui-linkbutton\" >保存</a>\r\n");
      out.write("\t\t   \t<a href=\"javascript:void(0)\" id=\"dictionaryCancel\" class=\"easyui-linkbutton\">取消</a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
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