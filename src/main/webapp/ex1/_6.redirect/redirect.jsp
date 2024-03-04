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
    <style>
        body {
            background: #c7c7ef;
        }
        div {
            color: white;
            font-size: 30px;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div>
    request 파라미터 food 값 : <%=request.getParameter("food")%>
</div>
<p>
    요청 주소 <%=request.getRequestURL()%>
</p>
</body>
</html>
