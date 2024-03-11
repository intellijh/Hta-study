<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>logout.jsp</title>
</head>
<body>
<%
    session.invalidate();
%>
<script>
    alert("로그아웃 되었습니다");
    location.href = "templatetest.jsp";
</script>
</body>
</html>
