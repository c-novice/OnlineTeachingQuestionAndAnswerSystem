<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--分页条的开始--%>
<div id="page_nav">
    <%--大于首页，才显示--%>
    <c:if test="${requestScope.pageManager.pageNo > 1}">
        <a href="${ requestScope.pageManager.url }&pageNo=1">首页</a>
        <a href="${ requestScope.pageManager.url }&pageNo=${requestScope.pageManger.pageNo-1}">上一页</a>
    </c:if>
    <%--页码输出的开始--%>
    <c:choose>
        <%--情况1：如果总页码小于等于5的情况，页码的范围是：1-总页码--%>
        <c:when test="${ requestScope.pageManager.pageTotal <= 5 }">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.pageManager.pageTotal}"/>
        </c:when>
        <%--情况2：总页码大于5的情况--%>
        <c:when test="${requestScope.pageManager.pageTotal > 5}">
            <c:choose>
                <%--小情况1：当前页码为前面3个：1，2，3的情况，页码范围是：1-5.--%>
                <c:when test="${requestScope.pageManager.pageNo <= 3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>
                <%--小情况2：当前页码为最后3个，8，9，10，页码范围是：总页码减4 - 总页码--%>
                <c:when test="${requestScope.pageManger.pageNo > requestScope.pageManger.pageTotal-3}">
                    <c:set var="begin" value="${requestScope.pageManager.pageTotal-4}"/>
                    <c:set var="end" value="${requestScope.pageManager.pageTotal}"/>
                </c:when>
                <%--小情况3：4，5，6，7，页码范围是：当前页码减2 - 当前页码加2--%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.pageManager.pageNo-2}"/>
                    <c:set var="end" value="${requestScope.pageManager.pageNo+2}"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i == requestScope.pageManager.pageNo}">
            【${i}】
        </c:if>
        <c:if test="${i != requestScope.pageManager.pageNo}">
            <a href="${ requestScope.pageManager.url }&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>
    <%--页码输出的结束--%>


    <%-- 如果已经 是最后一页，则不显示下一页，末页 --%>
    <c:if test="${requestScope.pageManger.pageNo < requestScope.pageManger.pageTotal}">
        <a href="${ requestScope.pageManager.url }&pageNo=${requestScope.pageManager.pageNo+1}">下一页</a>
        <a href="${ requestScope.pageManager.url }&pageNo=${requestScope.pageManager.pageTotal}">末页</a>
    </c:if>

    共${ requestScope.pageManager.pageTotal }页，${ requestScope.pageManager.pageTotalCount }条记录
    到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">

    <script type="text/javascript">
        $(function () {
            // 跳到指定的页码
            $("#searchPageBtn").click(function () {
                var pageNo = $("#pn_input").val();
                location.href = "${pageScope.basePath}${ requestScope.pageManager.url }&pageNo=" + pageNo;
            });
        });
    </script>
</div>
<%--分页条的结束--%>


