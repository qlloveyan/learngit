/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.42
 * Generated at: 2016-07-15 12:02:47 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.system;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class element_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t<title>机房信息</title>\r\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../../public/common2.jsp", out, false);
      out.write("\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/modules/system/element.js\"></script>\r\n");
      out.write("   \t\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"easyui-layout\" fit=\"true\">\r\n");
      out.write("\r\n");
      out.write("<!-- \t<div region=\"west\" border=\"false\"  title=\"页面元素管理\" class=\"cs-west\" style=\"width: 270px;\"> -->\r\n");
      out.write("<!-- \t\t<ul id=\"elementTree\" class=\"easyui-tree\" ></ul> -->\r\n");
      out.write("<!-- \t</div> -->\r\n");
      out.write("\t<div region=\"west\" class=\"listLeft-max\">\r\n");
      out.write("    \t<h1 class=\"tree-h1\"><b class=\"list-tree-ico\"></b>页面元素管理</h1>\r\n");
      out.write("\t\t<ul id=\"elementTree\" class=\"easyui-tree jstree\" ></ul>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div data-options=\"region:'center'\">\r\n");
      out.write("\t\t<div class=\"easyui-layout\" fit=\"true\">\r\n");
      out.write("\t\t\t<div region=\"north\">\r\n");
      out.write("\t\t\t\t<div class=\"search-left\" >\r\n");
      out.write("\t\t\t\t\t<!-- <h1 class=\"search-title easyui-panel\" title=\"菜单基本信息\"></h1> -->\r\n");
      out.write("\t\t\t\t\t<form id=\"elementFrom\" method=\"post\" >\r\n");
      out.write("\t\t\t\t\t\t<input type=\"hidden\" name=\"id\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"search-form\">\r\n");
      out.write("\t\t\t\t\t\t\t<table>\r\n");
      out.write("\t\t\t\t\t    \t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td style=\"width: 300px;text-align: right;\"><span><label class=\"label4\">菜单名称:</label><input class=\"input-small\" name=\"funcName\" /></span></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td style=\"width: 300px;text-align: right;\"><span><label class=\"label4\">菜单编码: </label><input class=\"input-small\" name=\"funcCode\"/></span></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td style=\"width: 300px;text-align: right;\"><span><label class=\"label4\">菜单URL: </label><input class=\"input-small\" name=\"url\" style=\"width: 150px;\"/></span></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td style=\"width: 200px;text-align: right;\"><span><label class=\"label4\">图标: </label><input class=\"input-small\" name=\"icon\"/></span></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td style=\"width: 260px;text-align: right;\"><span><label class=\"label4\">序号: </label><input class=\"input-small\" name=\"funcSort\"/></span></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div region=\"center\">\r\n");
      out.write("\t\t\t\t<table id=\"elementGrid\"></table>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"mm\" class=\"easyui-menu\" >  \r\n");
      out.write("    \t<div iconCls=\"icon-add\" onclick=\"addElement()\"><i class=\"add\"></i>新增页面元素</div>  \r\n");
      out.write("    \t<div class=\"menu-sep\"></div>\r\n");
      out.write("    \t<div onclick=\"collapse()\"><i class=\"flex\"></i>收缩</div> \r\n");
      out.write("    \t<div onclick=\"expand()\"><i class=\"unfold\"></i>展开</div> \r\n");
      out.write("    \t<div ><i class=\"exit\"></i>退出</div> \r\n");
      out.write("\t</div>  \r\n");
      out.write("\t\r\n");
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
