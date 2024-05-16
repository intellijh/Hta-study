<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CSV(Comma Separated Values) 예제</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script>
        /*
            - $.get() 메서드를 이용해서 item.xml 파일을 불러옵니다.
            - $.get() 메서드의 첫번째 매개변수에는 서버의 URL 주소를 지정합니다.
            - $.get() 메서드의 두번째 매개변수인 콜백함수를 이용해서 서버에서 보내온
                XML 형식의 데이터를 변수 data로 받습니다.
        */
        $(function () {
            $("button").click(function () {
                $.get("item.xml", function (data) { //data에는 item.xml의 내용이 들어있습니다.
                    $("table").remove(); //테이블이 존재하면 제거해 계속 추가되지 않도록 합니다.

                    let output =
                        "<table class='table'>" +
                        "<thead>" +
                        "<tr>" +
                        "<th>id</th>" +
                        "<th>name</th>" +
                        "<th>price</th>" +
                        "<th>description</th>" +
                        "</tr>" +
                        "</thead>" +
                        "<tbody>";

                    $(data).find("item").each(function () {
                        output += "<tr>";
                        output += " <td>"+$(this).attr("id")+"</td>";
                        output += " <td>"+$(this).attr("name")+"</td>";
                        output += " <td>"+$(this).find("price").text()+"</td>";
                        output += " <td>"+$(this).find("description").text()+"</td>";
                        output += "</tr>";
                    });

                    output +=
                        "</tbody>" +
                        "</table>";
                    $(".container").append(output);
                });
            });
        });
    </script>
</head>
<body>
<div class="container">
    <button class="btn btn-info">XML형식으로 데이터 가져옵니다.</button>
</div>
</body>
</html>
