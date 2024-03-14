<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CSV(Comma Separated Values) 예제</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script>
        /*
            * jQuery.get() = $.get()
            - 서버에 데이터를 HTTP GET방식으로 보냅니다.
            - $.get() 함수를 이용해서 data.csv 파일을 불러옵니다.
            - $.get() 함수의 첫번째 매개변수에는 서버의 URL 주소를 지정합니다.
            - $.get() 함수의 두번째 매개변수인 콜백함수를 이용해서 서버에서 보내온 CsV 형식의 데이터를 매개변수(input)로 받습니다.
        */
        $(function () {
            $("button").click(function () {
                $.get("data.csv", function (input) {
                    console.log(input);

                    const inputs = input.split("\n");

                    let output = "<table class='table'><tbody><tr><th>이름</th><th>지역</th><th>나이</th></tr>";
                    for (let i = 0; i < inputs.length; i++) {
                        const result = inputs[i].split(",");
                        output += "<tr>";
                        for (let j = 0; j < result.length; j++) {
                            output += "<td>" + result[j].trim() + "</td>";
                        }
                        output += "</tr>";
                    }
                    output += "</tbody></table>";
                    $("pre").html(output);
                });
            });
        });
    </script>
</head>
<body>
<div class="container">
    <button class="btn btn-primary mt-3 mb-3">데이터 불러오기</button>
    <pre>변경 전</pre>
</div>
</body>
</html>
