<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>session</title>
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
        input[type="submit"] {
            width: 15%;
            height: 80%;
            font-size: 1em;
        }
    </style>
</head>
<body>
<form action="attributeTest2.jsp" method="post">
    <table>
        <caption>
            <h2>영역과 속성 테스트</h2>
            <h3><%=application.getAttribute("name")%>님 반갑습니다.</h3>
            <h3><%=application.getAttribute("name")%>님의 아이디는 <%=application.getAttribute("id")%>입니다.</h3>
        </caption>
        <thead>
        <tr>
            <td colspan="2">Session 영역에 저장할 내용들</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="key">e-mail 주소</td>
            <td class="value"><input type="text" name="email" id="email"></td>
        </tr>
        <tr>
            <td class="key">집 주소</td>
            <td class="value"><input type="text" name="address" id="address"></td>
        </tr>
        <tr>
            <td class="key">전화번호</td>
            <td class="value"><input type="text" name="tel" id="tel"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" name="submit" value="전송"></td>
        </tr>
        </tbody>
    </table>
    <%application.setAttribute("name", request.getParameter("name"));%>
    <%application.setAttribute("id", request.getParameter("id"));%>
</form>
</body>
</html>
