<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--
        액션 태그 : 태그를 이용해서 다른 페이지의 서블릿에 접근할 수 있도록 구현된 기능
        forward 액션은 현재 페이지를 다른 페이지로 전환할 때 사용합니다.
    --%>
    <title>액션 태그 - forward</title>
</head>
<body>
<%
    request.setAttribute("food", "과자");
%>
<jsp:forward page="forwardTest2.jsp"/>
</body>
</html>
