<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="../../css/ch03-1.css" type="text/css" rel="stylesheet">
</head>
<body>
<form action="LoginProcess" method="post">
    <h1>로그인</h1>
    <hr>
    <b>아이디</b>
    <input type="text" name="id" placeholder="Enter id" required>
    <b>Password</b>
    <input type="text" name="passwd" placeholder="Enter password" required>
    <div class="clearfix">
        <button type="submit" class="submitbtn">Submit</button>
        <button type="reset" class="cancelbtn">Cancel</button>
    </div>
</form>
</body>
</html>