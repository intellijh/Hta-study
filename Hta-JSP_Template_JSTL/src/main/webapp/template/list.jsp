<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
    <title>list.jsp</title>
</head>
<body>
<jsp:include page="top.jsp"/>
<div class="container mt-3">
    <c:if test="${!empty list}">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>번호</th>
                <th>아이디</th>
                <th>성별</th>
                <th>취미</th>
                <th>자기소개</th>
                <th>가입일</th>
                <th>삭제</th>
            </tr>
            </thead>
            <tbody id="myTable">
            <c:forEach var="temp" items="${list}" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td><a href="info.net?id=${temp.id}">${temp.id}</a></td>
                    <td>${temp.genderView}</td>
                    <td>${temp.hobby}</td>
                    <td>${temp.intro}</td>
                    <td>${temp.register_date}</td>
                    <td><button class="btn btn-danger btn-sm">삭제</button></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty list}">
        <h4>조회된 데이터가 없습니다.</h4>
    </c:if>
</div>
<script>
    $(function () {
        $("button").click(function () {
            const hasDelete = confirm("정말 삭제하시겠습니까?");
            if (hasDelete) {
                const id = $(this).parents("tr").children(":eq(1)").text();

                let output = "<form action='delete.net' method='post'>";
                output += " <input name='id' value=" + id + ">";
                output += " <input type='submit'>";
                output += "</form>";

                $("body").append(output);
                $("form").hide().submit();
            } else {
                alert("취소를 선택하셨습니다.");
            }
        });
    });
</script>
</body>
</html>