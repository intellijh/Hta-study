<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Cookie cookie = new Cookie("language1", request.getParameter("language"));
    cookie.setMaxAge(60 * 60 * 24);
    response.addCookie(cookie);
%>
<script>
    location.href = "cookieExample.jsp";
</script>