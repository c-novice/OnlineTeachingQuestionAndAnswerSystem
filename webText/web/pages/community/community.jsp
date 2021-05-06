<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户社区</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="static/css/community.css">
</head>

<body background="static/img/img_1.png" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">
<div id="header">
    <span style="display: block;" class="wel_word">用户社区</span>
    <div id="header_content">
        <div>
            <%--如果用户还没有登录，显示 【登录和注册的菜单】 --%>
            <c:if test="${empty sessionScope.user}">

                <a class="link1" href="pages/user/login.jsp">登录</a> | <a class="link1" href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;


            </c:if>
            <%--如果已经登录，则显示 登录 成功之后的用户信息。--%>
            <c:if test="${not empty sessionScope.user}">
                <span>欢迎 ${sessionScope.user.username}</span>
                <a class="link1" href="userServlet?action=logout">注销</a>&nbsp;&nbsp;&nbsp;
            </c:if>
        </div>
    </div>
</div>

<div id="create">
    <form action="communityServlet">
        <input type="hidden" name="action" value="addQuestion">
        <input id="create_input" type="text" placeholder="请输入你的问题" name="name">
        &nbsp;
        <button id="create_btn" type="submit">新建问题</button>
    </form>
</div>

<ul>
    <br>
    <c:forEach items="${requestScope.page.items}" var="question">
        <li class="question_name" type="none">
            <a class="link2" data-toggle="collapse" href="#collapse${question.id}">
                    ${question.name}
            </a>
        </li>
        <li type="none">
            <div id="collapse${question.id}" class="panel-collapse collapse">
                <c:forEach items="${question.answers}" var="answer">
                    <div id="anwser_frame">
                        <div id="anwser">
                                ${answer.username}:${answer.context}<br>
                        </div>

                    </div>
                </c:forEach>
                <br>
                <c:if test="${ empty sessionScope.user}">
                    <form action="communityServlet">
                        <input type="hidden" name="action" value="createAnswer">
                        <input type="hidden" name="questionName" value="${question.name}">
                        <input type="hidden" name="username" value="${sessionScope.user.username}">
                        <input id="anwser_input" type="text" placeholder="回答问题" name="context">
                        <button id="anwser_btn" type="submit">提交</button>
                    </form>
                    <br>
                </c:if>
            </div>
        </li>
        <br/>
    </c:forEach>
    <%--静态包含分页条--%>
    <%@include file="/pages/common/page_nav.jsp" %>
    <br>
</ul>
<br>
<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>