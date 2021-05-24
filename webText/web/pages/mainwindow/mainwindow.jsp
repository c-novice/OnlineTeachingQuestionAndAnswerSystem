<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>首页</title>
    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head_mainwindow.jsp" %>
</head>
<body style="overflow-x:hidden">
<div class="wk_inav">
    <ul class="nav">
        <li><a href="pages/mainwindow/mainwindow.jsp">首页</a></li>
        <c:if test="${empty sessionScope.user}">
            <li><a href="pages/user/login.jsp">登录</a></li>
        </c:if>
        <c:if test="${not empty sessionScope.user}">
            <li><a>${sessionScope.user.username}</a></li>
        </c:if>
        <c:if test="${empty sessionScope.user}">
            <li><a href="pages/user/regist.jsp">注册</a></li>
        </c:if>
        <c:if test="${not empty sessionScope.user}">
            <li><a href="userServlet?action=logout">注销</a></li>
        </c:if>
        <c:if test="${not empty sessionScope.user}">
            <li><a href="pages/user/user_change.jsp">个人信息</a></li>
        </c:if>

        <c:if test="${not empty sessionScope.user}">
            <li><a href="messageServlet?action=page">消息</a></li>
        </c:if>

        <c:if test="${sessionScope.user.username=='admin'}">
            <li><a href="managerServlet?action=page">后台管理</a></li>
        </c:if>
        <c:if test="${sessionScope.user.username!='admin'}">
            <li><a>联系我们</a></li>
        </c:if>
    </ul>
</div>

<script>
    $(window).load(function () {
        //粒子背景特效
        $('.main_body').particleground({
            dotColor: '#5cbdaa',
            lineColor: '#5cbdaa'
        });
    });
</script>


<div class="main_body" style="background: #0e947a">
    <br><br><br>
</div>


<div style="background: #BABABA;overflow-x:hidden">
    <div id="wk_slide-nav">
        <br>
        <ul>
            <li class="wk_nav-line"></li>
            <li class="wk_nav-bullet-container active" data-index="0"><a class="nav-link"
                                                                         href="pages/search/search.jsp">
                <div class="nav-bullet"></div>
                <div class="nav-text">搜索引擎</div>
            </a></li>
            <li class="wk_nav-line"></li>
            <li class="wk_nav-bullet-container" data-index="1"><a class="nav-link" href="communityServlet?action=page">
                <div class="nav-bullet"></div>
                <div class="nav-text">用户社区</div>
            </a></li>
            <li class="wk_nav-line"></li>
            <li class="wk_nav-bullet-container" data-index="2"><a class="nav-link"
                                                                  href="pages/user/question_answer.jsp">
                <div class="nav-bullet"></div>
                <div class="nav-text">我的问题/回答</div>
            </a></li>
            <li class="wk_nav-line"></li>
        </ul>
    </div>
    <br><br><br>
</div>
<div class="main_body" style="background: #0e947a">
    <br><br><br><br><br><br><br>
</div>
<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>