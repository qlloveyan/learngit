/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.42
 * Generated at: 2016-07-16 10:01:37 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.system;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class user_005fadd_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!-- 基本信息  -->\r\n");
      out.write("<form id=\"userAddFrom\"  method=\"post\">\r\n");
      out.write("<div class=\"easyui-panel\">\r\n");
      out.write("\t<!-- 添加 -->\r\n");
      out.write("\t<div class=\"add-table add-list2\">\r\n");
      out.write("    \t<table>\r\n");
      out.write("    \t\t<tr>\r\n");
      out.write("    \t\t\t<th width=\"100\">用户名称:<i class=\"must\"></i>：</th>\r\n");
      out.write("    \t\t\t<td><input class=\"easyui-validatebox input-big\" type=\"text\"  name=\"userName\" data-options=\"required:true\" validType=\"length[0,20]\"></input></td>\r\n");
      out.write("    \t\t\t<th width=\"100\">用户帐号:<i class=\"must\"></i>：</th>\r\n");
      out.write("    \t\t\t<td><input id=\"userAddFrom-userCode\" class=\"easyui-validatebox input-big\" type=\"text\" name=\"userCode\" data-options=\"required:true\" validType=\"length[0,20]\"></input></td>\r\n");
      out.write("    \t\t</tr>\r\n");
      out.write("    \t\t<tr>\r\n");
      out.write("    \t\t\t<th width=\"100\">用户序列号<i class=\"must\"></i>:</th>\r\n");
      out.write("    \t\t\t<td><input type=\"text\" class=\"easyui-validatebox input-big\" name=\"ukeynum\" data-options=\"required:true\" validType=\"length[0,150]\"></input></td>\r\n");
      out.write("    \t\t\t<th width=\"100\">所属单位/部门:</th>\r\n");
      out.write("    \t\t\t<td><input type=\"text\" name=\"unitId\" id=\"unitId\" class=\"input-big\" data-options=\"required:true\"></input></td>\r\n");
      out.write("    \t\t</tr>\r\n");
      out.write("    \t\t<tr>\r\n");
      out.write("    \t\t\t<th width=\"100\">电话:</th>\r\n");
      out.write("    \t\t\t<td><input class=\"easyui-validatebox input-big\" type=\"text\" name=\"tel\" validType=\"length[0,20]\"></input></td>\r\n");
      out.write("    \t\t\t<th width=\"100\">手机(多个号码以;进行区分):</th>\r\n");
      out.write("    \t\t\t<td><input class=\"easyui-validatebox input-big\" type=\"text\" name=\"mobile\" validType=\"mobiles\"></input></td>\r\n");
      out.write("    \t\t</tr>\r\n");
      out.write("    \t\t<tr>\r\n");
      out.write("    \t\t\t<th width=\"100\">邮箱:</th>\r\n");
      out.write("    \t\t\t<td><input class=\"easyui-validatebox input-big\" type=\"text\" name=\"email\" maxlength=\"100\" validType=\"email\"></input></td>\r\n");
      out.write("    \t\t\t<th width=\"100\">传真:</th>\r\n");
      out.write("    \t\t\t<td><input class=\"easyui-validatebox input-big\" type=\"text\" name=\"fax\" validType=\"length[0,30]\"></input></td>\r\n");
      out.write("    \t\t</tr>\r\n");
      out.write("    \t\t<tr >\r\n");
      out.write("    \t\t\t<th width=\"100\">联系地址:</th>\r\n");
      out.write("    \t\t\t<td colspan=\"3\"><input class=\"easyui-validatebox input-big\" style=\"width: 423px\"  name=\"addr\"  validType=\"length[0,100]\" ></input></td>\r\n");
      out.write("    \t\t</tr>\r\n");
      out.write("    \t\t<tr >\r\n");
      out.write("    \t\t\t<th width=\"100\" class=\"tdyhlx\">用户类型:</th>\r\n");
      out.write("    \t\t\t<td class=\"tdyhlx\"><input type=\"radio\"  name=\"yhlx\" checked=\"checked\" value=\"0\">管局侧用户<input type=\"radio\" name=\"yhlx\"   value=\"1\"> 企业侧用户</td>\r\n");
      out.write("    \t\t\t<th width=\"100\" class=\"manger\">是否是管理员:</th>\r\n");
      out.write("    \t\t\t<td class=\"manger\" ><input type=\"radio\"  name=\"isManger\" value=\"1\">是 <input type=\"radio\" name=\"isManger\" checked=\"checked\"  value=\"0\">否</td>\r\n");
      out.write("    \t\t</tr>\r\n");
      out.write("    \t</table>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("<div style=\"text-align:center;padding:5px\">\r\n");
      out.write("   \t<a href=\"javascript:void(0)\" id=\"userAdd\" class=\"easyui-linkbutton\" >保存</a>\r\n");
      out.write("   \t<a href=\"javascript:void(0)\" id=\"userCancel\" class=\"easyui-linkbutton\">取消</a>\r\n");
      out.write("</div>\r\n");
      out.write("<input type=\"hidden\" id=\"userType\"  value=\"");
      out.print(request.getSession().getAttribute("userType"));
      out.write("\"/>\r\n");
      out.write("<input type=\"hidden\" id=\"yhlx\"  value=\"");
      out.print(request.getSession().getAttribute("yhlx"));
      out.write("\"/>\r\n");
      out.write("<input type=\"hidden\" id=\"crrentdiverpath\" value=\"");
      out.print(request.getSession().getAttribute("crrentdiverpath") );
      out.write("\"/>\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("<object type=\"application/x-dongle\" id=\"don\" width=\"0\" height=\"0\"></object>  \r\n");
      out.write("<script type=\"text/javascript\" src=\"js/modules/system/user_add.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/common/validate.js\"></script>\r\n");
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
