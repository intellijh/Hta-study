<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>중식메뉴</title>
    <style>
        div {
            width: 100px;
            height: 80px;
            margin: 3px;
            float: left;
        }
    </style>
    <script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
    <script>
        $(function () {
            $("#menu1").click(function () {
                $("#message1").load("menu.html");
            });

            $("#menu2").click(function () {
                $("#message2").load("menu.html li");
            });
        });
    </script>
</head>
<body>
<div>
    <%-- href="#"는 클릭시 현재 페이지 상단으로 이동합니다. --%>
    <a href="#" id="menu1">메뉴 보기 1</a>
    <p>
        <span id="message1"></span>
    </p>
</div>
<div>
    <a href="#" id="menu2">메뉴 보기 2</a>
    <p>
        <span id="message2"></span>
    </p>
</div>
</body>
</html>
