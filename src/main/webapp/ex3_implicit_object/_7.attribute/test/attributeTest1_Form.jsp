<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>application</title>
    <style>
        table {
            text-align: center;
            margin: 0 auto;
            width: 500px;
            height: 250px;
        }
        thead > tr > td {
            background: aquamarine;
            height: 70px;
        }
        .key {
            background: orange;
            width: 30%;
        }
        .value {
            background: yellow;
        }
        input[type="text"] {
            width: 90%;
            height: 80%;
        }
        tr:last-child {
            background: green;
        }
        button {
            width: 60px;
            height: 40px;
            font-size: 1em;
        }
    </style>
</head>
<body>
<form action="attributeTest1.jsp" method="post">
    <table>
        <caption><h2>영역과 속성 테스트</h2></caption>
        <thead>
        <tr>
            <td colspan="2">Application 영역에 저장할 내용들</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="key"> 이름</td>
            <td class="value"><input type="text" name="name"></td>
        </tr>
        <tr>
            <td class="key">아이디</td>
            <td class="value"><input type="text" name="id"></td>
        </tr>
        <tr>
            <td colspan="2"><button type="submit" name="submit">전송</button></td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>
