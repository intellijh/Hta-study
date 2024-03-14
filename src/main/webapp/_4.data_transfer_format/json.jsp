<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>JSON 이용하기</title>
    <script>
        /*
            1. JSON은 JavaScript Object Notation 의 약어로 XML 데이터를 대신하기 위해서 사용됩니다.
            2. JSON은 키와 값을 쌍으로 가지는 구조입니다.
            3. 배열을 사용할 때는 대괄호([ ])안에 중괄호({ })를 사용하여 조합합니다.
            4. jQuery에서는 JSON으로 표현한 데이터를 파일에 저장해 두었다가 필요할 때 이를 로드할 수 있는 $.getJSON()메서드를 제공합니다

            5. 서버로 부터 get방식의 요청을 하고, 응답을 JSON 형식으로 받기 위해서
                $.getJSON()메서드를 사용합니다.
                (1) $.getJSON()메서드의 첫번째 매개변수는 서버의 URL주소를 지정합니다.
                (2) 서버로 요청이 성공적으로 완료되면, 두번째 매개변수로 기술한 콜백함수가 호출됩니다.
                    형식) function(data, textStatus)
                    1 콜백함수의 첫번째 매개변수인 data는 서버에서 돌려받은 JSON 객체 형식의 데이터입니다.
                    2 두번째 매개변수인 textStatus는 성공일때 "success" 라는 문자열이 전달됩니다.

            (3) 자바스크립트의 JSON객체는 JSON데이터를 자바스크립트 객체로 변환할 수 있습니다.
                또한 자바스크립트 객체를 문자열로 변환하기도 합니다.
                1 JSON.stringify()메서드 : 자바스크립트 객체를 JSON형식의 문자열로 변환합니다.
                  이를 이용해서 자바스크립트 객체를 브라우저로부터 다른 애플리케이션으로 전송할 수 있습니다.
                2 JSON.parse()메서드 : JSON데이터를 브라우저가 사용할 수 있는 자바스크립트 객체로 변환합니다.
        */
        $(function () {
            $("button").click(function () {
                $.getJSON("item.json", function (data, textStatus) {
                    console.log("data=" + data);
                    console.log("textStatus=" + textStatus);
                    console.log("JSON.stringify(data)=" + JSON.stringify(data));

                    if (data.length > 0) {
                        let output = "<table class=table>";
                        output += "<thead>";
                        output += "<tr>";
                        output += "<th>id</th>";
                        output += "<th>name</th>";
                        output += "<th>price</th>";
                        output += "<th>description</th>";
                        output += "</tr>";
                        output += "</thead>";

                        $(data).each(function (index, item) {
                            output += "<tr>";
                            output += "<td>" + this.id + "</td>";
                            output += "<td>" + this.name + "</td>";
                            output += "<td>" + this.price + "</td>";
                            output += "<td>" + this.description + "</td>";
                            output += "</tr>";
                        });
                        output += "</tbody>";
                        output += "</table>";
                        $("button").after(output);
                    } else {
                        $("button").after("<div>데이터가 존재하지 않습니다.</div>");
                    }
                });
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
