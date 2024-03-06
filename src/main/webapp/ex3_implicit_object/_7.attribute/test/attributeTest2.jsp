<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        * {
            text-align: center;
        }
    </style>
</head>
<body>
<h1>영역과 속성 테스트</h1>
<h2><%=application.getAttribute("name")%>님의 정보가 모두 저장되었습니다.</h2>
<a href="attributeTest3.jsp">확인하러 가기</a>
<%session.setAttribute("email", request.getParameter("email"));%>
<%session.setAttribute("address", request.getParameter("address"));%>
<%session.setAttribute("tel", request.getParameter("tel"));%>
</body>
</html>
