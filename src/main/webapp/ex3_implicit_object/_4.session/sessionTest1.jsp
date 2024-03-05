<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Request Test3(requestTest3.jsp)</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1>세션 설정 및 메소드 사용법</h1>
    <table class="table table-striped">
        <tr>
            <td>쿠키정보</td>
            <%
                Cookie[] cookie = request.getCookies();
                if (cookie == null) { //처음 요청일 때
            %>
            <td>쿠키가 존재하지 않습니다.</td>
            <%
                } else { //두 번째부터 같은 요청일 때
                    for (int i = 0; i < cookie.length; i++) {
            %>
            <td>
                <%=cookie[i].getName()%>(<%=cookie[i].getValue()%>
                &nbsp;&nbsp;
            </td>
            <%
                    }
                }
            %>
        </tr>
        <tr>
            <td>세션 ID</td>
            <td><%=session.getId()%></td>
        </tr>
        <%--
            1. 톰캣은 web.xml에 기본 세션시간을 아래와 같이 30분(1800초)으로 설정되어 있습니다.
                <session-config>
                    <session-timeout>30</session-timeout>
                </session-config>
            2. getMaxInactiveInterval() : 세션 유효 시간을 초 단위로 가져옵니다.
        --%>
        <tr>
            <td>세션 유효 시간</td>
            <td><%=session.getMaxInactiveInterval() / 60 + "분"%></td>
        </tr>
        <tr>
            <td>세션 설정</td>
            <td>속성 "id"에 "hong" 설정<%session.setAttribute("id", "hong");%></td>
        </tr>
        <tr>
            <td>세션 설정값 가져오기</td>
            <td><%=session.getAttribute("id")%></td>
        </tr>
    </table>
</div>
</body>
</html>
