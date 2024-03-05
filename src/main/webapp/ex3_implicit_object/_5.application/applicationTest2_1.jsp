<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Application Test</title>
</head>
<%--
    초기화 파라미터는 web.xml에 작성합니다.
    1. getInitParameter(Name) : 웹 어플리케이션 초기화 파라미터의 이름 목록을 리턴합니다.
    2. getInitParameter(Name) : 이름이 Name인 웹 어플리케이션 초기화 파라미터의 값을 읽어옵니다.
                                존재하지 않을 경우 null을 리턴합니다.
--%>
<body>
<h2>초기화 파라미터 목록</h2>
<ul>
    <%
        Enumeration<String> initParameterNames = application.getInitParameterNames();

        while (initParameterNames.hasMoreElements()) {
            String initParamName = initParameterNames.nextElement();

    %>
    <li>
        <%=initParamName%> =
        <%=application.getInitParameter(initParamName)%>
    </li>
    <%
        }
    %>
</ul>
</body>
</html>
