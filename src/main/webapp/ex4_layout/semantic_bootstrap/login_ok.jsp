<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인 처리</title>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
</head>
<body>
<script>
    $(function () {
        <%
        String id = request.getParameter("id");
        String passwd = request.getParameter("passwd");

        if (!id.equals(application.getInitParameter("id"))) {
        %>
        alert("아이디를 확인해주세요");
        history.back();
        <%
        } else if (!passwd.equals(application.getInitParameter("password"))) {
        %>
        alert("비밀번호를 확인해주세요");
        history.back();
        <%
        } else {
            session.setAttribute("id",id);
            Cookie rememberCookie = new Cookie("rememberId", request.getParameter("remember"));
            rememberCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(rememberCookie);
            Cookie idCookie = new Cookie("id", request.getParameter("id"));
            idCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(idCookie);
        %>
        alert("<%=request.getParameter("id")%>님 환영합니다");
        location.href = "templatetest.jsp";
        <%
        }
        %>
    });
</script>
</body>
</html>
