<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>个人信息页面</title>

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head_user.jsp" %>

    <%--    以下jQuery事件用来验证注册信息--%>
    <script type="text/javascript">
        // 页面加载完成之后
        $(function () {
            // 给注册绑定单击事件
            $("#sub_btn").click(function () {
                // 验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
                //1 获取用户名输入框里的内容
                var usernameText = $("#username").val();
                //2 创建正则表达式对象
                var usernamePatt = /^\w{5,12}$/;
                //3 使用test方法验证
                if (!usernamePatt.test(usernameText)) {
                    //4 提示用户结果
                    $("span.errorMsg").text("用户名应由5~12为的数字、字母或下划线组成！");

                    return false;
                }

                // 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
                //1 获取用户名输入框里的内容
                var passwordText = $("#password").val();
                //2 创建正则表达式对象
                var passwordPatt = /^\w{5,12}$/;
                //3 使用test方法验证
                if (!passwordPatt.test(passwordText)) {
                    //4 提示用户结果
                    $("span.errorMsg").text("密码应由5~12为的数字、字母或下划线组成！");

                    return false;
                }


                // 邮箱验证：xxxxx@xxx.com
                //1 获取邮箱里的内容
                var emailText = $("#email").val();
                //2 创建正则表达式对象
                var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                //3 使用test方法验证是否合法
                if (!emailPatt.test(emailText)) {
                    //4 提示用户
                    $("span.errorMsg").text("请输入正确的邮箱！");

                    return false;
                }


                // 在事件的function函数中，有一个this对象。这个this对象，是当前正在响应事件的dom对象。
                /**
                 * confirm是确认提示框函数
                 * 参数是它的提示内容
                 * 它有两个按钮，一个确认，一个是取消。
                 * 返回true表示点击了，确认，返回false表示点击取消。
                 */
                return confirm("你确定要保存吗?");
                // return false// 阻止元素的默认行为===不提交请求

                // 去掉错误信息
                $("span.errorMsg").text("");

            });

        });

    </script>
</head>

<body>
<div class="login_wrap">
    <div class="content">

        <div class="logo"></div>

        <div class="login_box">

            <div class="login_form">
                <div class="login_title">
                    个人信息
                </div>
                <div class="msg_cont">
                    <span class="errorMsg">
                        ${ requestScope.msg }
                    </span>
                </div>
                <form action="userServlet" method="post">
                    <input type="hidden" name="action" value="change">
                    <input type="hidden" name="id" value="${sessionScope.user.id}">
                    <div class="form_text_ipt">
                        <input name="username" type="text" placeholder="账号" id="username"
                               value="${sessionScope.user.username}">
                    </div>
                    <div class="ececk_warning"><span>账号不能为空</span></div>
                    <div class="form_text_ipt">
                        <input name="password" type="password" placeholder="密码" id="password"
                               value="${sessionScope.user.password}">
                    </div>
                    <div class="ececk_warning"><span>密码不能为空</span></div>
                    <div class="form_text_ipt">
                        <input name="email" type="text" placeholder="邮箱" id="email" value="${sessionScope.user.email}">
                    </div>
                    <div class="ececk_warning"><span>邮箱不能为空</span></div>
                    <br>
                    <br>
                    <div class="form_btn">
                        <button type="submit" id="sub_btn">保存</button>
                    </div>
                    <br>
                </form>
            </div>
        </div>
    </div>
</div>

<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>