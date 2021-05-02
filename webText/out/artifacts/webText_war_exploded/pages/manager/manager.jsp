<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="ch">

<head>
    <meta charset="utf-8">
    <title>后台管理</title>

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head_manager.jsp" %>

    <style type="text/css">
        .input_username {
            height: 40px;
            width: 150px;
        }

        .input_password {
            height: 40px;
            width: 250px;
        }

        .input_email {
            height: 40px;
            width: 250px;
        }

        .input_question {
            height: 40px;
            width: 350px;
        }

        .input_answer {
            height: 40px;
            width: 350px;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $("button.btn-danger").click(function () {
                return confirm("你确定要删除这个问题吗?");
            });
        });
    </script>
    <script>
        $(function () {
            $(".meun-item").click(function () {
                $(".meun-item").removeClass("meun-item-active");
                $(this).addClass("meun-item-active");
                var itmeObj = $(".meun-item").find("img");
                itmeObj.each(function () {
                    var items = $(this).attr("src");
                    items = items.replace("_grey.png", ".png");
                    items = items.replace(".png", "_grey.png")
                    $(this).attr("src", items);
                });
                var attrObj = $(this).find("img").attr("src");

                attrObj = attrObj.replace("_grey.png", ".png");
                $(this).find("img").attr("src", attrObj);
            });
            $("#topAD").click(function () {
                $("#topA").toggleClass(" glyphicon-triangle-right");
                $("#topA").toggleClass(" glyphicon-triangle-bottom");
            });
            $("#topBD").click(function () {
                $("#topB").toggleClass(" glyphicon-triangle-right");
                $("#topB").toggleClass(" glyphicon-triangle-bottom");
            });
            $("#topCD").click(function () {
                $("#topC").toggleClass(" glyphicon-triangle-right");
                $("#topC").toggleClass(" glyphicon-triangle-bottom");
            });
            $(".toggle-btn").click(function () {
                $("#leftMeun").toggleClass("show");
                $("#rightContent").toggleClass("pd0px");
            })
        })
    </script>
</head>

<body>
<div id="wrap">
    <!-- 左侧菜单栏目块 -->
    <div class="leftMeun" id="leftMeun">
        <div id="logoDiv">
            <p id="logoP"><img id="logo" alt="后台管理系统" src="static/img/logo.png"><span>后台管理系统</span></p>
        </div>
        <div id="personInfor">
            <p id="userName">${sessionScope.user.username}</p>
            <p>
                <a href="userServlet?action=logout">退出登录</a>
            </p>
        </div>
        <div class="meun-title">管理系统</div>
        <div class="meun-item meun-item-active" href="#sour" aria-controls="sour" role="tab" data-toggle="tab"><img
                src="static/img/icon_source.png">问题管理
        </div>
        <div class="meun-item" href="#user" aria-controls="user" role="tab" data-toggle="tab"><img
                src="static/img/icon_user_grey.png">用户管理
        </div>
    </div>

    <!-- 右侧具体内容栏目 -->
    <div id="rightContent">
        <a class="toggle-btn" id="nimei">
            <i class="glyphicon glyphicon-align-justify"></i>
        </a>
        <!-- Tab panes -->
        <div class="tab-content">
            <!-- 问题管理模块 -->
            <div role="tabpanel" class="tab-pane active" id="sour">
                <div class="check-div form-inline">
                    <button class="btn btn-yellow btn-xs" data-toggle="modal" data-target="#addSource">新建问题</button>
                    <div class="col-xs-4">
                        <form action="managerServlet">
                            <input type="hidden" name="action" value="searchQuestion">
                            <input type="text" class="form-control input-sm" placeholder="输入要查询的问题名"
                                   name="searchName">
                            <button class="btn btn-white btn-xs " type="submit">查 询</button>
                        </form>
                    </div>
                </div>
                <div class="data-div">
                    <div class="row tableHeader">
                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
                            id
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                            问题
                        </div>
                        <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
                            回答
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                            操作
                        </div>
                    </div>
                </div>
                <div class="tablebody">
                    <c:forEach items="${requestScope.pageManager.items}" var="question">
                        <c:if test="${empty question.answers}">
                            <c:set var="context" scope="request" value=""/>
                            <form class="form-horizontal" action="managerServlet" method="post">
                                <input type="hidden" name="action" value="changeQuestion"/>
                                <input type="hidden" name="changeQuestion" value="${question.id}">
                                <div class="row">
                                    <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
                                            ${question.id}
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                                            ${question.name}
                                    </div>
                                    <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
                                            ${requestScope.context}
                                    </div>
                                    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                        <a href="managerServlet?action=deleteQuestion&deleteAnswer=&deleteQuestion=${question.id}&deleteName=${question.name}">
                                            <button class="btn btn-danger btn-xs" type="button">
                                                删除
                                            </button>
                                        </a>
                                    </div>
                                </div>
                            </form>
                        </c:if>
                        <c:if test="${not empty question.answers}">
                            <c:forEach items="${question.answers}" var="answer">
                                <div class="row">
                                    <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
                                            ${question.id}
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                                            ${question.name}
                                    </div>
                                    <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
                                            ${answer.context}
                                    </div>
                                    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                        <a href="managerServlet?action=deleteQuestion&deleteAnswer=${answer.context}&deleteQuestion=${question.id}&deleteName=${question.name}">
                                            <button class="btn btn-danger btn-xs" type="button">
                                                删除
                                            </button>
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </c:forEach>
                </div>
                <!--页码块-->
                <%@include file="/pages/common/page_manager_nav.jsp" %>
            </div>

            <!--弹出窗口 添加资源-->
            <div class="modal fade" id="addSource" role="dialog" aria-labelledby="gridSystemModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">新建问题</h4>
                        </div>
                        <div class="modal-body">
                            <div class="container-fluid">
                                <form class="form-horizontal" action="managerServlet" method="post">
                                    <input type="hidden" name="action" value="addQuestion"/>
                                    <div class="form-group ">
                                        <label class="col-xs-3 control-label">问题：</label>
                                        <div class="col-xs-8 ">
                                            <input name="newQuestionName" class="form-control input-sm duiqi">
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-xs btn-xs btn-white"
                                                data-dismiss="modal">取 消
                                        </button>
                                        <button type="submit" class="btn btn-xs btn-xs btn-green">保 存</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-- /.modal -->


            <!--用户管理模块-->
            <div role="tabpanel" class="tab-pane" id="user">
                <div class="check-div form-inline">
                    <div class="col-xs-4">
                        <form action="managerServlet">
                            <input type="hidden" name="action" value="searchUsername">
                            <input type="text" class="form-control input-sm" placeholder="输入要查询的用户名"
                                   name="searchName">
                            <button class="btn btn-white btn-xs " type="submit">查 询</button>
                        </form>
                    </div>
                </div>
                <div class="data-div">
                    <div class="row tableHeader">
                        <div class="col-xs-2">
                            id
                        </div>
                        <div class="col-xs-2">
                            用户名
                        </div>
                        <div class="col-xs-3">
                            密码
                        </div>
                        <div class="col-xs-3">
                            邮箱
                        </div>
                        <div class="col-xs-2">
                            操作
                        </div>
                    </div>
                    <div class="tablebody">
                        <c:forEach items="${requestScope.pageUsers.items}" var="user">
                            <div class="row">
                                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                        ${user.id}
                                </div>
                                <div class="col-lg-2col-md-2 col-sm-2 col-xs-2">
                                        ${user.username}
                                </div>
                                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                                        ${user.password}
                                </div>
                                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                                        ${user.email}
                                </div>
                                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                    <a href="managerServlet?action=deleteUser&deleteUserId=${user.id}">
                                        <button class="btn btn-danger btn-xs" type="button">
                                            删除
                                        </button>
                                    </a>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>