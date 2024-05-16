<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인 페이지</title>
    <link href="${pageContext.request.contextPath}/image/apple.png" rel="icon">
    <link href="${pageContext.request.contextPath}/css/login.css" type="text/css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/js/jquery-3.7.1.min.js"></script>
    <script>
        $(function () {
            $(".join").click(function () {
                location.href = "join.net";
            });

            const id = "${cookie_id}";
            if (id) {
                $("#id").val(id).css("font-weight", "bold");
                $("#remember").prop("checked", true);
            }
        });
    </script>
</head>
<body>
<form action="loginProcess.net" method="post" name="loginform">
    <h1>로그인</h1>
    <hr>
    <b>아이디</b>
    <input type="text" id="id" name="id" placeholder="Enter id" required>

    <b>Password</b>
    <input type="password" name="pass" placeholder="Enter password" required>
    <input type="checkbox" id="remember" name="remember" value="store">
    <span>remember</span>

    <div class="clearfix">
        <button type="submit" class="submitbtn">로그인</button>
        <button type="button" class="join">회원가입</button>
    </div>
</form>
</body>
</html>
