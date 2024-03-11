<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSTL core 라이브러리 사용 예제 2</title>
</head>
<body>
<%--
    * Cross-Site Scripting(XSS)
    악의적으로 사용자가 공격하려는 사이트에 스크립트를 넣는 기법이며
    공격에 성공하면 사이트에 접속한 사용자는 삽입된 코드를 실행하게 되면서 의도치 않은 행동을 수행시킵니다.
--%>
<c:set var="test" value="<script>alert('환영합니다-escapeXml=false')</script>"/>
<c:out value="${test}" escapeXml="false"/>
<%--
    escapeXml=false는 html태그를 인식합니다.
    페이지 소스 보기 : <script>alert('환영합니다-escapeXml=false')</script>
    실행 결과 : 스크립트 실행
--%>
</body>
</html>
