<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <style>
        select.form-control {
            width: auto;
            margin-bottom: 2em;
            display: inline-block;
        }

        .rows {
            text-align: right;
        }

        .gray {
            color: gray;
        }

        body > div > table > thead > tr:nth-child(2) > th:nth-child(1) {width: 8%}
        body > div > table > thead > tr:nth-child(2) > th:nth-child(2) {width: 50%}
        body > div > table > thead > tr:nth-child(2) > th:nth-child(3) {width: 14%}
        body > div > table > thead > tr:nth-child(2) > th:nth-child(4) {width: 17%}
        body > div > table > thead > tr:nth-child(2) > th:nth-child(5) {width: 11%}
    </style>
    <script src="${pageContext.request.contextPath}/js/list.js"></script>
    <title>MVC 게시판</title>
</head>
<body>
<div class="container">
    <%-- 게시글이 있는 경우 --%>
    <c:if test="${listcount > 0}">
        <div class="rows">
            <span>줄보기</span>
            <select class="form-control" id="viewcount">
                <option value="1">1</option>
                <option value="3">3</option>
                <option value="5">5</option>
                <option value="7">7</option>
                <option value="10" selected>10</option>
            </select>
        </div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th colspan="3">MVC 게시판 - list</th>
                <th colspan="2">
                    <span>글 개수 : ${listcount}</span>
                </th>
            </tr>
            <tr>
                <th><div>번호</div></th>
                <th><div>제목</div></th>
                <th><div>작성자</div></th>
                <th><div>날짜</div></th>
                <th><div>조회수</div></th>
            </tr>
            </thead>
            <tbody>
            <c:set var="num" value="${listcount - (page - 1) * limit}"/>
            <c:forEach var="b" items="${boardlist}">
                <tr>
                    <td><%-- 번호 --%>
                        <c:out value="${num}"/> <%-- num 출력 --%>
                        <c:set var="num" value="${num-1}"/> <%-- num = num - 1; 의미 --%>
                    </td>
                    <td><%-- 제목--%>
                        <div>
                            <%-- 답변글 제목앞에 여백 처리 부분
                                board_re_lev, board_num,
                                board_subject, board_name, board_date,
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>
