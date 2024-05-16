<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>sample1</title>
    <link href="${pageContext.request.contextPath}/css/ex1.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="${pageContext.request.contextPath}/image/apple.png">
    <script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
    <script>
        $(function () {
            function error(responseTxt, status, xhr) {
                console.log(responseTxt);

                if (xhr.status == "404") {
                    alert("해당 파일이 존재하지 않습니다.")
                }
            }
            $("button").click(function () {
                $("button").text("로딩완료").css("color", "red");
                $("p").load("sample12.txt", error);
            });
        });
    </script>
</head>

<body>
<button>변경</button>
<p>변경 전 : 줄이 안 바뀌어요</p>
<br>
<pre>변경 전 : 줄이 바뀝니다.(입력한 대로 출력됩니다.)</pre>
</body>
</html>
