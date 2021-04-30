<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";

    pageContext.setAttribute("basePath", basePath);
%>

<!--写base标签，永远固定相对路径跳转的结果-->
<base href="<%=basePath%>">
<script src="static/js/jquery.js"></script>
<script src="static/js/Particleground.js" tppabs="js/Particleground.js"></script>

<link rel="stylesheet" type="text/css" href="static/css/style_2_common.css"/>
<link rel="stylesheet" type="text/css" href="static/css/style_2_portal_index.css"/>
