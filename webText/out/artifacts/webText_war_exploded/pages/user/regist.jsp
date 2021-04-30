<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>注册界面</title>

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head_user.jsp" %>

    <%--    以下jQuery事件用来验证注册信息--%>
    <script type="text/javascript">
        // 页面加载完成之后
        $(function () {

            // 给验证码的图片，绑定单击事件
            $("#code_img").click(function () {
                // 在事件响应的function函数中有一个this对象。这个this对象，是当前正在响应事件的dom对象
                // src属性表示验证码img标签的 图片路径。它可读，可写
                // alert(this.src);
                this.src = "${basePath}kaptcha.jpg?d=" + new Date();
            });

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

                // 验证确认密码：和密码相同
                //1 获取确认密码内容
                var repwdText = $("#repwd").val();
                //2 和密码相比较
                if (repwdText != passwordText) {
                    //3 提示用户
                    $("span.errorMsg").text("确认密码和密码不一致！");

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

                // 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
                var codeText = $("#code").val();

                //去掉验证码前后空格
                // alert("去空格前：["+codeText+"]")
                codeText = $.trim(codeText);
                // alert("去空格后：["+codeText+"]")

                if (codeText == null || codeText == "") {
                    //4 提示用户
                    $("span.errorMsg").text("验证码不能为空！");

                    return false;
                }

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
                    注册
                </div>
                <div class="msg_cont">
                    <span class="errorMsg">
                        ${ requestScope.msg }
                    </span>
                </div>
                <form action="userServlet" method="post">
                    <input type="hidden" name="action" value="regist">
                    <div class="form_text_ipt">
                        <input name="username" type="text" placeholder="账号" id="username">
                    </div>
                    <div class="ececk_warning"><span>账号不能为空</span></div>
                    <div class="form_text_ipt">
                        <input name="password" type="password" placeholder="密码" id="password">
                    </div>
                    <div class="ececk_warning"><span>密码不能为空</span></div>
                    <div class="form_text_ipt">
                        <input name="repwd" type="password" placeholder="确认密码" id="repwd">
                    </div>
                    <div class="ececk_warning"><span>确认密码不能为空</span></div>
                    <div class="form_text_ipt">
                        <input name="email" type="text" placeholder="邮箱" id="email">
                    </div>
                    <div class="ececk_warning"><span>邮箱不能为空</span></div>
                    <div class="form_text_ipt">
                        <input name="code" type="text" placeholder="验证码" id="code">
                        <br>
                        <br>
                        <img id="code_img" alt="" src="kaptcha.jpg"
                             style="float: right; margin-right: 10px; width: 100px; height: 30px;">
                    </div>
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
                    <div class="form_btn">
                        <button type="submit" id="sub_btn">注册</button>
                    </div>
                    <div class="form_reg_btn">
                        <span>已有帐号？</span><a href="pages/user/login.jsp">马上登录</a>
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