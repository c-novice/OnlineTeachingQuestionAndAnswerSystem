<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>登录页面</title>
    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head_user.jsp" %>
</head>
<body>
<div class="login_wrap">
    <div class="content">
        <div class="logo"></div>
        <div class="login_box">

            <div class="login_form">
                <div class="login_title">
                    登录
                </div>
                <div class="msg_cont">
                            <span class="errorMsg">
                                ${ empty requestScope.msg ? "":requestScope.msg }
                            </span>
                </div>
                <form action="userServlet" method="post">
                    <input type="hidden" name="action" value="login"/>
                    <div class="form_text_ipt">
                        <input name="username" type="text" placeholder="账号" value="${requestScope.username}">
                    </div>
                    <div class="ececk_warning"><span>账号不能为空</span></div>
                    <div class="form_text_ipt">
                        <input name="password" type="password" placeholder="密码">
                    </div>
                    <div class="ececk_warning"><span>密码不能为空</span></div>
                    <div class="form_check_ipt">
                        <div class="left check_left">
                            <label><input name="" type="checkbox"> 下次自动登录</label>
                        </div>
                    </div>
                    <div class="form_btn">
                        <button type="submit">登录</button>
                    </div>
                    <div class="form_reg_btn">
                        <span>还没有帐号？</span><a href="pages/user/regist.jsp">马上注册</a>
                    </div>
                </form>
                <br>
            </div>
        </div>
    </div>
</div>
<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>
