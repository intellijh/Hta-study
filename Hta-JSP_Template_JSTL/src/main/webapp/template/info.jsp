<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
    <style>
        .container {
            margin-top: 3em;
        }
        table, h4 {
            text-align: center;
        }
        caption {
            text-align: center;
            font-weight: bold;
            caption-side: top;
            font-size: 1.5em;
        }
    </style>
    <title>list.jsp</title>
</head>
<body>
<jsp:include page="top.jsp"/>
<div class="container mt-3">
    <c:if test="${!empty temp}">
        <table class="table">
            <thead>
            <caption>${temp.id}님의 상세 정보</caption>
            </thead>
            <tbody>
            <tr>
                <th>아이디</th>
                <td>${temp.id}</td>
            </tr>
            <tr>
                <th>주민번호</th>
                <td>${temp.jumin}</td>
            </tr>
            <tr>
                <th>이메일</th>
                <td>${temp.email}</td>
            </tr>
            <tr>
                <th>성별</th>
                <td>${temp.genderView}</td>
            </tr>
            <tr>
                <th>취미</th>
                <td>${temp.hobby}</td>
            </tr>
            <tr>
                <th>우편번호</th>
                <td>${temp.post}</td>
            </tr>
            <tr>
                <th>주소</th>
                <td>${temp.address}</td>
            </tr>
            <tr>
                <th>자기소개</th>
                <td>${temp.intro}</td>
            </tr>
            <tr>
                <th>가입일</th>
                <td>${temp.register_date}</td>
            </tr>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty temp}">
        <h4>조회된 데이터가 없습니다.</h4>
    </c:if>
</div>
</body>
</html>