<%--
  Created by IntelliJ IDEA.
  User: jhta
  Date: 2024-03-04
  Time: 오전 11:06:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>redirect.jsp</title>
</head>
<body>
request 속성 food 값 : <%=request.getAttribute("food")%><br>
session 속성 food 값 : <%=session.getAttribute("food")%><br>
ServletContext 속성 food 값 : <%=application.getAttribute("food")%><br>
</body>
</html>
