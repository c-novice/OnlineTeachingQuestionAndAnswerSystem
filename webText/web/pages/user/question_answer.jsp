<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="ch">

<head>
    <meta charset="utf-8">
    <title>我的问题/回答页面</title>

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head_manager.jsp" %>

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
            <p id="logoP"><img id="logo" src="static/img/logo.png"><span>我的问题/回答</span></p>
        </div>
        <div class="meun-item meun-item-active" href="#sour" aria-controls="sour" role="tab" data-toggle="tab"><img
                src="static/img/icon_source.png">我的问题
        </div>
        <div class="meun-item" href="#user" aria-controls="user" role="tab" data-toggle="tab"><img
                src="static/img/icon_user_grey.png">我的回答
        </div>
    </div>

    <!-- 右侧具体内容栏目 -->
    <div id="rightContent">
        <a class="toggle-btn" id="nimei">
            <i class="glyphicon glyphicon-align-justify"></i>
        </a>
        <!-- Tab panes -->
        <div class="tab-content">
            <!-- 我的问题模块 -->
            <div role="tabpanel" class="tab-pane active" id="sour">
                <div class="check-div form-inline">
                    <div class="col-xs-4">
                        <form action="answerServlet">
                            <input type="hidden" name="action" value="searchQuestion">
                            <input type="text" class="form-control input-sm" placeholder="输入要查询的问题名" name="searchName">
                            <button class="btn btn-white btn-xs " type="submit">查 询</button>
                        </form>
                    </div>
                </div>
                <div class="data-div">
                    <div class="row tableHeader">
                        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                            &emsp;&emsp;&emsp;id
                        </div>
                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
                            问题
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                            操作
                        </div>
                    </div>
                </div>

                <div class="tablebody">
                    <c:forEach items="${requestScope.page.items}" var="question">
                        <form class="form-horizontal" action="answerServlet" method="post">
                            <input type="hidden" name="action" value="changeQuestion"/>
                            <input type="hidden" name="changeQuestion" value="${question.id}">
                            <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
                                <div class="panel-group" id="accordion}">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <a data-toggle="collapse" data-parent="#accordion"
                                               href="#collapse${question.id}">
                                                    ${question.id}&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;${question.name}
                                            </a>
                                        </div>
                                        <div id="collapse${question.id}" class="panel-collapse collapse">
                                            <c:forEach items="${question.answers}" var="answer">
                                                <div class="panel-body">
                                                        ${answer.username} : ${answer.context}
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                <a href="answerServlet?action=deleteQuestion&deleteQuestionId=${question.id}&deleteContext=${question.name}">
                                    <button class="btn btn-danger btn-xs" type="button">
                                        删除
                                    </button>
                                </a>
                            </div>
                        </form>
                        <%--                        <br><br><br>--%>
                    </c:forEach>
                </div>
                <!--页码块-->
                <div style="position: fixed;bottom: 10px">
                    <%@include file="/pages/common/page_nav.jsp" %>
                </div>
            </div>


            <!--我的回答模块-->
            <div role="tabpanel" class="tab-pane" id="user">
                <div class="check-div form-inline">
                    <div class="col-xs-4">
                        <form action="userServlet">
                            <input type="hidden" name="action" value="searchQuestionName">
                            <input type="text" class="form-control input-sm" placeholder="输入要查询的问题名"
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
                            问题
                        </div>
                        <div class="col-xs-6">
                            我的回答
                        </div>
                        <div class="col-xs-2">
                            操作
                        </div>
                    </div>
                    <div class="tablebody">
                        <%--                        <c:forEach items="${requestScope.pageUsers.items}" var="user">--%>
                        <div class="row">
                            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                123
                                <%--                                        ${answer.id}--%>
                            </div>
                            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                java
                                <%--                                        ${question.name}--%>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                ok
                                <%--                                        ${question.answer}--%>
                            </div>
                            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                <a href="answerServlet?action=deleteAnswer&deleteAnswerId=${Answer.id}">
                                    <button class="btn btn-danger btn-xs" type="button">
                                        删除
                                    </button>
                                </a>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>