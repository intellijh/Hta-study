<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSON 이용하기</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <style>
        #error {
            color: green;
        }
        #com {
            color: blue;
        }
    </style>
    <script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
    <script>
        $(function () {
            $("button").click(function () {
                $(".container div").remove();

                $.ajax({
                    url: "${pageContext.request.contextPath}/get_name",
                    dataType: "json",
                    cache: false,
                    success: function (rdata) {
                        $("body .container")
                            .append("<div>서버에서 가져온 데이터는 <span style='color: red'>" + rdata.name + "</span>입니다.</div>");
                    },
                    error: function (request, status, error) {
                        $(".container").append("<div id='error'>code :"
                            + request.status + "<br>"
                            + "받은 데이터 : " + request.responseText + "<br>"
                            + "error status : " + status + "<br>"
                            + "error 메세지 : " + error + "</div>");
                    },
                    complete: function () {
                        $("body .container").append("<div id='com'>Ajax가 완료되었습니다.</div>");
                        console.log("ajax() - complete");
                    }
                });
                console.log("ajax() 뒤");
            });
        });
    </script>
</head>
<body>
<div class="container">
    <button class="btn btn-info">JSON 데이터를 불러옵니다.</button>
</div>
</body>
</html>
