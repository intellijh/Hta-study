<%--
    리터럴 표현식 : EL 블록(${})에서 사용할 수 잇는 값은
    문자열, 정수, 부동소수점, boolean, null 가능합니다.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>리터럴 표현식</title>
</head>
<body>
문자열 : ${"test"}<br>
문자열 : ${"test"}<br>
<%--
    자바의 +는 사용할 수 없어요. 아래처럼 ${}를 붙여서 사용합니다.
--%>
문자열 연결 ${'test'}${'test2'}<br>
<%--
    에러 발생합니다.
    문자열 연결 : ${'test' + 'test2'}<br>
--%>
<%--
    "\"를 출력하려면 두 개 사용합니다.
--%>
문자열 : ${"\\test"}<br>
정수 : ${20}<br>
부동소수점 : ${3.14}<br>
boolean : ${true}<br>
null : ${null}
</body>
</html>
