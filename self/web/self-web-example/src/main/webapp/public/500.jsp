<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
*,html,body{
	font-family:Verdana;
	font-size:12px;
	}
li{ color:#bac2cd; margin-bottom:10px;}
</style>
<head>
		<base href="<%=basePath%>">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Internet Explorer 无法显示该网页</title>
    </head>

    <body style="background:#fff;">

        <table width="100%" height="100%" cellpadding="0" cellspacing="20" border="0" style="background:#FFF;">
            <tr>
                <td align="left" width="*"><img src="js/easyui/themes/smcs/500.jpg"></td>
            </tr>
            <tr>
                <td id="likelyCausesAlign" valign="top" align="left">
                    <h3 style="font-size:14px; color:#89929f">最可能的原因是:</h3>
                                <ul>
                                <li id="errorExpl2">Internet 连接已丢失。</li>
                                <li id="errorExpl3">该网站暂时不可用。</li>
                                <li id="errorExpl4">无法连接到域名服务器(DNS)。</li>
                                <li id="errorExpl5">域名服务器(DNS)没有该网站的域的列表。</li>
                                <li id="errorExpl6">如果这是 HTTPS (安全)地址，请单击“工具”菜单下的“Internet 选项”，再单击“高级”选项卡，然后请检查以确保“安全”部分下的 SSL 和 TLS 协议已启用。</li>
                            </ul>
                </td>
            </tr>
        </table>
    </body>
</html>
