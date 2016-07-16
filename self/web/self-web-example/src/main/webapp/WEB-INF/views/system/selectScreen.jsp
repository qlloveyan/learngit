<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
    <script type="text/javascript" src="js/modules/system/selectScreen.js"></script>
    <div class="choose_center">
        <h3>请选择菜单布局方式</h3>
        <ul class="style_list">
            <div id="screenId">
           <!--  <li class="active"><a href="javascript:void(0);" onclick="upAndDown();"><i class="style1"></i><span>上下布局</span></a></li> 
            <li><a href="javascript:void(0);" onclick="leftAndRight();"><i class="style2"></i><span>左右布局</span></a></li> -->
            </div>
        </ul>
    </div>
