<%@ page import="java.util.Calendar" %><%--
  Created by IntelliJ IDEA.
  User: jhta
  Date: 2024-02-29
  Time: 오후 2:11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
    %>
    <title>ServletTest.jsp</title>
    <style>
        span {
            color: red;
        }
    </style>
</head>
<body>
<h1>현재 시간은 <%=hour %>시 <%=minute %>분 <%=second %>초 입니다.</h1>
<span>나는 JSP2 입니다.</span>
</body>
</html>
