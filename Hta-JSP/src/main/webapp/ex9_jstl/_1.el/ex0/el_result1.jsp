<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL 내장객체 사용 예제</title>
</head>
<body>
<h2>넘어온 이름은 : ${param.name}</h2>
<h2>넘어온 취미는 ${paramValues.hobby[0]}, ${paramValues.hobby[1]}</h2>
<h2>page 영역의 속성 id의 값 : ${pageScope.id}</h2>
<h2>request 영역의 속성 id의 값 : ${requestScope.id}</h2>
<h2>session 영역의 속성 id의 값 : ${sessionScope.id}</h2>
<h2>application 영역의 속성 id의 값 : ${applicationScope.id}</h2>
<h2>page -> request -> session -> application 영역에서 속성 id의 값을 찾습니다.</h2>
</body>
</html>
