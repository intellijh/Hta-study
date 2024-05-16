<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-03-04
  Time: 오후 6:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success</title>
    <style>
        table {
            width: 600px;
            height: 500px;
        }
        td {
            border-bottom: 1px solid darkgrey;
        }
        tr>td:first-child {
            width: 20%;
            text-align: right;
        }
    </style>
</head>
<body>
<div class="container">
    <table class="table table-bordered">
        <tbody>
        <tr>
            <td>아이디</td>
            <td><%=request.getAttribute("id")%></td>
        </tr>
        <tr>
            <td>비밀번호</td>
            <td><%=request.getAttribute("pass")%></td>
        </tr>
        <tr>
            <td>주민번호</td>
            <td><%=request.getAttribute("jumin")%></td>
        </tr>
        <tr>
            <td>이메일</td>
            <td><%=request.getAttribute("email")%></td>
        </tr>
        <tr>
            <td>성별</td>
            <td><%=request.getAttribute("gender")%></td>
        </tr>
        <tr>
            <td>취미</td>
            <td><%=request.getAttribute("hobby")%></td>
        </tr>
        <tr>
            <td>우편번호</td>
            <td><%=request.getAttribute("post")%></td>
        </tr>
        <tr>
            <td>주소</td>
            <td><%=request.getAttribute("address")%></td>
        </tr>
        <tr>
            <td>자기소개</td>
            <td><%=request.getAttribute("intro")%></td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>