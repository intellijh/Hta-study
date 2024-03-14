<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSON 이용하기</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <style>
        #error {
            color: green;
        }
    </style>
    <script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
    <script>
        $(function () {
            $("button").click(function () {
                $(".container div").remove();

                $.ajax({
                    url: "${pageContext.request.contextPath}/get_array_lib_dept",
                    dataType: "json",
                    cache: false,
                    success: function (rdata) {
                        let output = "";
                        output +=
                                "<table class='table'>" +
                                "<thead>" +
                                "   <tr>" +
                                "       <th>부서번호</th>" +
                                "       <th>부서명</th>" +
                                "       <th>지역</th>" +
                                "   </tr>" +
                                "</thead>" +
                                "<tbody>";
                        $(rdata).each(function () {
                            output +=
                                "   <tr>" +
                                "       <td>" + this.deptno + "</td>" +
                                "       <td>" + this.dname + "</td>" +
                                "       <td>" + this.loc + "</td>" +
                                "   </tr>";
                        });
                        output +=
                                "</tbody>" +
                                "</table>";
                        $("body .container").append(output);
                    },
                    error: function (request, status, error) {
                        $(".container").append("<div id='error'>code :"
                            + request.status + "<br>"
                            + "받은 데이터 : " + request.responseText + "<br>"
                            + "error status : " + status + "<br>"
                            + "error 메세지 : " + error + "</div>");
                    },
                    complete: function () {
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
