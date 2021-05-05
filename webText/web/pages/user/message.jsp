<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>消息界面</title>
    <style type="text/css">
        <!--
        body {
            margin-left: 4px;
            margin-top: 4px;
            margin-right: 0px;
            margin-bottom: 4px;
            overflow: hidden;
        }

        .STYLE1 {
            color: #FFFFFF;
            font-size: 12px;
            font-weight: bold;
        }

        .STYLE2 {
            font-size: 12px
        }

    </style>
</head>

<body>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td height="25" bgcolor="#1078b5" style="padding-left:15px;"><span class="STYLE1">收件箱</span></td>
    </tr>
    <tr>
        <td valign="top" style="border:solid 1px #8db6cf;">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td height="22" background="images/bg1.gif"
                        style="border-bottom:solid 1px #8db6cf; padding-top:1px; padding-bottom:1px;">
                        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="9%" height="22" style=" border-right: solid 1px #6daed6; ">&nbsp;</td>
                                <td width="11%"
                                    style="padding-left:1px; border-right: solid 1px #6daed6; border-left:solid 1px #e7f4fc;">
                                    <div align="center"><span class="STYLE2"> 发件人</span></div>
                                </td>
                                <td width="56%"
                                    style="padding-left:1px; border-right: solid 1px #6daed6; border-left:solid 1px #e7f4fc;">
                                    <div align="center"><span class="STYLE2">主题</span></div>
                                </td>
                                <td width="12%"
                                    style="padding-left:1px; border-right: solid 1px #6daed6; border-left:solid 1px #e7f4fc;">
                                    <div align="center"><span class="STYLE2">时间</span></div>
                                </td>
                                <td width="12%"
                                    style="padding-left:1px; border-right: solid 1px #6daed6; border-left:solid 1px #e7f4fc;">
                                    <div align="center"><span class="STYLE2">状态</span></div>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>

                <tr>
                    <td style="padding-top:10px;">
                        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
                            <tr>
                                <td style="padding-top:5px;">
                                    <c:forEach items="${requestScope.pageMessages.items}" var="message">
                                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td width="9%" height="22" style="border-bottom:solid 1px #c3d7e3;">
                                                <div align="center"><img src="images/ml.gif" width="13" height="12">
                                                </div>
                                            </td>
                                            <td width="16%" style="border-bottom:solid 1px #c3d7e3;" class="STYLE2">
                                                <div align="left"><span class="STYLE2">${message.usernameFrom}</span>
                                                </div>
                                            </td>
                                            <td width="51%" style="border-bottom:solid 1px #c3d7e3;" class="STYLE2">
                                                <div align="left"><span class="STYLE2">${message.context}</span></div>
                                            </td>

                                            <td width="12%" style="border-bottom:solid 1px #c3d7e3;" class="STYLE2">
                                                <div align="center">2008-08-22</div>
                                            </td>

                                            <td width="12%" style="border-bottom:solid 1px #c3d7e3;" class="STYLE2">
                                                <c:if test="${message.checked==0}">
                                                    <div align="center">未读</div>
                                                </c:if>
                                                <c:if test="${message.checked!=0}">
                                                    <div align="center">已读</div>
                                                </c:if>
                                            </td>

                                            <td>
                                                <a href="messageServlet?action=deleteMessage&deleteMessageId=${message.id}">
                                                    <button type="button" >
                                                        删除
                                                    </button>
                                                </a>


                                            </td>



                                            </c:forEach>
                                        </tr>


                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>

            </table>
        </td>
    </tr>
</table>
</body>
</html>

