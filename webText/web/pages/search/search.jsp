<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<head>
    <title>搜索引擎页面</title>
    <!-- Meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head_search.jsp" %>
</head>

<body>
<section class="coming-61-w3l-vv-soon">
    <!-- /coming-61-w3l-vv-section -->
    <div class="coming-61-w3l-vv-mian">
        <div class="wrapper">
            <div class="coming-61-w3l-vv-top">
                <div class="coming-61-w3l-vv-info">
                    <h1>Lightweight Search Engine</h1>

                    <form action="#" method="GET" class="d-flex coming-form">
                        <input type="text" placeholder="Enter Your Question" required="required">
                        <button type="submit">Go</button>
                    </form>

                </div>

            </div>
            <div class="social-coming-soon">
            </div>
        </div>
    </div>
</section>
<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>

</html>