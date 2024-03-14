<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CSV(Comma Separated Values) 예제</title>
</head>
<body>
<script>
    let input = "홍 길동, 서울시, 25세\n";
    input += "신사임당, 울산시, 45세";

    const inputs = input.split("\n");

    for (let i = 0; i < inputs.length; i++) {
        const result = inputs[i].split(",");
        for (let j = 0; j < result.length; j++) {
            document.body.innerHTML += result[j].trim() + "<br>";
        }
    }
</script>
</body>
</html>
