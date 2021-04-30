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
<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="static/css/common_2.css"/>
<link rel="stylesheet" type="text/css" href="static/css/slide.css"/>
<link rel="stylesheet" type="text/css" href="static/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="static/css/flat-ui.min.css"/>
<link rel="stylesheet" type="text/css" href="static/css/jquery.nouislider.css">
