<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>서버의 데이터를 대상 엘리멘트에 삽입하기</title>
    <link href="../css/ex2.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
    <script>
        $(function () {
            $("button").click(function () {
                $("button").text("로딩 완료").css("color", "red");
                $("#container").load("resource1.html");
            });
        });
    </script>
</head>
<body>
<button>서버로부터 데이터 가져오기</button>
<div id="container">데이터 가져오기 전</div>
</body>
</html>
