/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.42
 * Generated at: 2016-07-16 12:02:58 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.test;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class test_005fedit_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- 基本信息 -->\r\n");
      out.write("<form id=\"testEditFrom\"  method=\"post\">\r\n");
      out.write("<div class=\"easyui-panel\">\r\n");
      out.write("\t<!-- 添加 -->\r\n");
      out.write("\t<div class=\"add-table add-list2\">\r\n");
      out.write("    \t<table  width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("    \t\t<tr>\r\n");
      out.write("    \t\t\t<th width=\"100\">名称:</td>\r\n");
      out.write("    \t\t\t<td><input class=\"easyui-validatebox input-text\" type=\"text\"  name=\"name\" data-options=\"required:true\"></input></td>\r\n");
      out.write("    \t\t</tr>\r\n");
      out.write("\r\n");
      out.write("    \t</table>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"add-save\">\r\n");
      out.write("   \t<a href=\"javascript:void(0)\" id=\"testEidt\"class=\"easyui-linkbutton\" >保存</a>\r\n");
      out.write("   \t<a href=\"javascript:void(0)\" id=\"testCancel\"class=\"easyui-linkbutton\">取消</a>\r\n");
      out.write("   </div>\r\n");
      out.write("  <!-- 隐藏域参数 接受通过url直接调转传过来的参数-->\r\n");
      out.write("  <input type=\"hidden\" id=\"windowType\" value=\"");
      out.print(request.getParameter("windowType"));
      out.write("\">\r\n");
      out.write("  <input type=\"hidden\" id=\"testId\" name=\"id\" value=\"");
      out.print(request.getParameter("id"));
      out.write("\">\r\n");
      out.write("</form>\r\n");
      out.write("  \r\n");
      out.write("<script type=\"text/javascript\" src=\"js/modules/test/test_edit.js\"></script>\r\n");
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
