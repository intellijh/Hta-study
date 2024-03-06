<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cookie Test</title>
</head>
<body>
<%
    String name = "";
    String value = "";

    //쿠키가 생성되어 있는지 확인합니다.
    String cookie = request.getHeader("Cookie");

    if (cookie != null) {
        //request 헤더의 Cookie는 name=hong; JSESSIONID=6123213234455555555 로 구성되어 있습니다.
        //쿠키 이름과 값이 세미콜론으로 구분된 하나의 쌍으로 구성 되어있는 것을 확인할 수 있습니다.
        //쿠키의 쌍이 여러개 존재할 수 있어 배열로 가져옵니다
        Cookie[] cookies = request.getCookies();

        //쿠키 배열에서 쿠키 이름이 "name"인 것을 찾아서 쿠키의 이름과 값을 가져옵니다.
        for (Cookie c : cookies) {
            if (c.getName().equals("name")) {
                name = c.getName();
                value = c.getValue();
            }
        }
%>
<h2>쿠키 이름 = <%=name%></h2>
<h2>쿠키 값 = <%=value%></h2>
<%
    } else {
%>
            존재하는 쿠키가 없습니다.
<%
    }
%>
</body>
</html>
