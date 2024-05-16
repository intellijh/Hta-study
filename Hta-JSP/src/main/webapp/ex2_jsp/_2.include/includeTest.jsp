<%--
    실행하는 페이지 입니다.

    include 지시어(디렉티브)
    1. 특정한 JSP 파일 또는 HTML 파일을 해당 JPS페이지에 삽입할 수 있도록 기능을 제공하는 지시어입니다.

    2. include 지시어로 지정되어 현재 페이지에 표함되는 JSP파일들을 웹 컨테이너에 의해 따로따로 컴파일 되는 것이 아니라 그 소스가
    include 지시어를 사용한 해당 페이지에 복사되어 더해지는 것으로
    웹 컨테이너는 전체 JSP파일들의 소스가 합져진 하나의 페이지만을 컴파일합니다.

    3. 여러 JSP 페이지에서 공통되는 부분이 많을 때 이러한 공통되는 부분을 파일로 만들어 include 지시어로 삽입합니다.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>include 테스트</title>
</head>
<body>
<%@ include file="header.jsp"%>
<h1>includeTest.jsp 파일입니다.</h1>
<%@ include file="footer.jsp"%>
</body>
</html>
