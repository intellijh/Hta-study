<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
    </style>
</head>
<body>
<table>
    <caption><h2>영역과 속성 테스트</h2></caption>
    <thead>
    <tr>
        <td colspan="2">Application 영역에 저장된 내용들</td>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td class="key">이름</td>
        <td class="value"><%=application.getInitParameter("name")%></td>
    </tr>
    <tr>
        <td class="key">아이디</td>
        <td class="value"></td>
    </tr>
    </tbody>
</table>
<br>
<table>
    <thead>
    <tr>
        <td colspan="2">Session 영역에 저장된 내용들</td>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td class="key">e-mail</td>
        <td class="value"><%=application.getInitParameter("name")%></td>
    </tr>
    <tr>
        <td class="key">address</td>
        <td class="value"></td>
    </tr>
    <tr>
        <td class="key">tel</td>
        <td class="value"></td>
    </tr>
    </tbody>
</table>
</body>
</html>
