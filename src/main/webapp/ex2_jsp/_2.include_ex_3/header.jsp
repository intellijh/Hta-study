<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <div>
        <%
            String id = (String) session.getAttribute("id");
            if (id != null && !id.equals("")) {
        %>
            <%=id%>님이 로그인 되었습니다.
            <a href="Logout">(로그아웃)</a>
        <%
            } else {
        %>
            <a href="login.jsp">로그인</a>
        <%
            }
        %>
    </div>
</header>