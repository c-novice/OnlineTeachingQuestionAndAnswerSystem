<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>登录成功页面</title>
    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head_user.jsp" %>
    <%--    定时转发--%>
    <meta http-equiv="Refresh" content="5;pages/mainwindow/mainwindow.jsp"/>
</head>
<body>
<div class="login_wrap">
    <br><br><br><br><br><br>
    <div class="content">
        <div class="logo"></div>
        <div class="login_box">
            <div class="login_form">
                <div class="login_title">
                    登录成功！
                </div>
                <div style="color:black;text-align:center;font-size: 14px"> 5秒后会自动跳转到主界面</div>
                <br>
                <div class="form_reg_btn" style="text-align: center">
                    <a href="pages/mainwindow/mainwindow.jsp">立即跳转</a>
                </div>
                <br>
            </div>
        </div>
    </div>
</div>
<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>
