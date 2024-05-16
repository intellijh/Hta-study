<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인 페이지</title>
    <link href="${pageContext.request.contextPath}/image/apple.png" rel="icon">
    <link href="${pageContext.request.contextPath}/css/login.css" type="text/css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
    <script>
        const result = "${result}";
        if (result == "joinSuccess") {
            alert("회원가입을 축하합니다.");
        } else if ("${loginfail}" == "loginFailMsg") {
            alert("아이디나 비밀번호 오류입니다.");
        }

        $(function () {
            $(".join").click(function () {
                location.href = "${pageContext.request.contextPath}/member/join";
            });
        });
    </script>
</head>
<body>
<form action="${pageContext.request.contextPath}/member/loginProcess" method="post" name="loginform">
    <h1>로그인</h1>
    <hr>
    <b>아이디</b>
    <input type="text" id="id" name="id" placeholder="Enter id" required
    <c:if test="${!empty saveid}">
           value="${saveid}"
    </c:if>
    >

    <b>Password</b>
    <input type="password" name="password" placeholder="Enter password" required>

    <label>
        <input type="checkbox" name="remember-me" style="margin-bottom: 15px">로그인 유지하기
    </label>

    <div class="clearfix">
        <button type="submit" class="submitbtn">로그인</button>
        <button type="button" class="join">회원가입</button>
    </div>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</form>
</body>
</html>
