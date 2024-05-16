<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>쿠키를 이용한 화면 설정 예제</title>
    <style>
        body {
            margin: 100px auto;
            text-align: center;
            width: 500px;
        }
        fieldset {
            width: 100%;
        }
        form {
            height: 3em;
            line-height: 3em;
        }
    </style>
    <script src="<%=request.getContextPath()%>/js/jquery-3.7.1.js"></script>
    <%
        String language = "korea";

        String cookie = request.getHeader("Cookie");

        if (cookie != null) {
            Cookie[] cookies = request.getCookies();

            for (Cookie c : cookies) {
                if (c.getName().equals("language1")) {
                    language = c.getValue();
                }
            }
        }
    %>
</head>
<body>
<%
    if (language.equals("korea")) {
%>
<h3>안녕하세요. 이것은 쿠키 예제입니다.</h3>
<%
    } else {
%>
<h3>Hello. This is Cookie example.</h3>
<%
    }
%>
<form action="cookieExample2.jsp" method="post">
    <fieldset>
        <input type="radio" name="language" value="korea" id="korea">한국어 페이지 보기
        <input type="radio" name="language" value="english" id="english">영어 페이지 보기
        <input type="submit" value="설정">
    </fieldset>
</form>
<script>
    $(function () {
        const language = "#<%=language%>";
        $(language).prop("checked", true);
    });
</script>
</body>
</html>
