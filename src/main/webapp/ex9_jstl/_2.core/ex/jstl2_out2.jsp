<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSTL core 라이브러리 사용 예제 2</title>
</head>
<body>
<c:set var="test" value="<script>alert('환영합니다-escapeXml=true')</script>"/>
<c:out value="${test}" escapeXml="true"/>
<%-- value="${test}" 또는 value='${test}' 로 작성할 수 있습니다. --%>
<%--
    <c:out>의 escapeXml 속성의 기본값은 true이고 html의 특수문자를 해당 코드로 변환합니다.
    특수문자        코드
       <           &1t;
       >           &gt;
       '           &#039;
       "           &#034;

    페이지 소스 보기 : &lt;script&gt;alert(&#039;환영합니다.&#039;)&lt;/script&gt;
    실행 결과 : <script>alert('환영합니다-escapeXml=true')</script>
--%>
<c:set var="test" value='<script>alert("환영합니다2")</script>'/>
<c:out value="${test}"/>
<%-- &lt;script&gt;alert(&#034;환영합니다.&#034;)&lt;/script&gt; --%>
</body>
</html>
