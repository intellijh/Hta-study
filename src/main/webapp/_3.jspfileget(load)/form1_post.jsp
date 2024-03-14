<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ajx</title>
    <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="process.jsp" method="post">
    <span>이름</span><input type="text" placeholder="이름" id="name" name="name" required><br>
    <span>나이</span><input type="text" placeholder="나이" id="age" name="age" required><br>
    <span>주소</span><input type="text" placeholder="주소" id="address" name="address" required><br>
    <input type="submit" value="전송">
</form>
<div></div>
</body>
</html>
