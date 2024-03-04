<%--
  Created by IntelliJ IDEA.
  User: jhta
  Date: 2024-03-04
  Time: 오전 9:30:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>dispatcher 방식으로 이동</title>
    <link href="../../css/ch03-5.css" type="text/css" rel="stylesheet">
</head>
<body>
<form action="DispatcherServlet" method="get">
    <b>좋아하는 음식은 무엇인가요?</b>
    <input type="text" name="food">
    <input type="submit" value="전송">
</form>
</body>
</html>
