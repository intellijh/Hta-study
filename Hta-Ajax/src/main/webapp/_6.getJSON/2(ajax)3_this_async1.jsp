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
    <%--
        $.ajax(object)
        1 url : 요청 전송 url,
        2 type : 전송 방식(get(기본) 또는 post),
        3 data : 전송할 자료들,
        4 cache: false를 설정하면 jQuery는 Ajax 요청에 대해 브라우저의 캐시를 사용하지 않도록 합니다.
                 이렇게 하면 매번 서버로부터 최신 데이터를 가져오며, 캐시된 응답을 사용하지 않습니다.
        5 dataType : return data의 Type(에이잭스 성공 후 돌려받은 자료의 형을 정의-"json", "xml", "html"),
        6 success : HTTP 요청이 성공한 경우 실행할 함수 정의,
        7 error : HTTP 요청이 실패한 경우 실행할 함수 정의,
        8 complete : 요청의 실패, 성공과 상관 없이 완료될 경우 실행할 함수 정의
    --%>
    <script>
        $(function () {
            $("button").click(function () {
                $("table").remove();
                $(".container div").remove();

                $.ajax({
                    url: "item_error.json",
                    dataType: "json",
                    cache: false,
                    success: function (rdata) {
                        if (rdata.length > 0) {
                            let output = "<table class=table>";
                            output += "<thead>";
                            output += "<tr>";
                            output += "<th>id</th>";
                            output += "<th>name</th>";
                            output += "<th>price</th>";
                            output += "<th>description</th>";
                            output += "</tr>";
                            output += "</thead>";

                            $(rdata).each(function (index, item) {
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
                    },
                    error: function (request, status, error) {
                        $(".container").append("<div id='error'>code :"
                            + request.status + "<br>"
                            + "받은 데이터 : " + request.responseText + "<br>"
                            + "error status : " + status + "<br>"
                            + "error 메세지 : " + error + "</div>");
                    },
                    complete: function () {
                        $(".container").append("<div id='com'>Ajax가 완료되었습니다.</div>");
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
