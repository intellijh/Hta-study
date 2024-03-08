<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DEPT LIST</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/ch03-5.css" type="text/css" rel="stylesheet">
</head>
<body>
<form action="search" method="get">
    <b>검색할 부서 번호를 입력하세요</b><br>
    <input type="text" name="deptno" required pattern="[0-9]{2}">
    <input type="submit" value="전송">
</form>
</body>
</html>
