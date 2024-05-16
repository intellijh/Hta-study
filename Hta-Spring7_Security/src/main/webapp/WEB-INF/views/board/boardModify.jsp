<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MVC 게시판</title>
    <jsp:include page="header.jsp"/>
    <script src="${pageContext.request.contextPath}/js/modify.js"></script>
    <style>
        h1 {
            font-size: 1.5rem;
            text-align: center;
            color: #1a92b9
        }

        .container {
            width: 60%
        }

        #upfile {
            display: none
        }

        textarea {
            resize: none;
        }
    </style>
    <script>
        if ("${result}" == "passFail") {
            alert("비밀번호가 다릅니다.")
        }
    </script>
</head>
<body>
<%-- 게시판 수정 --%>
<div class="container">
    <form name="modifyform" action="modifyAction" method="post" enctype="multipart/form-data">
        <input type="hidden" name="board_num" value="${boarddata.board_num}">
        <input type="hidden" name="board_file" value="${boarddata.board_file}">
        <h1>MVC 게시판 - 수정</h1>
        <div class="form-group">
            <label for="board_name">글쓴이</label>
            <input type="text" name="board_name" id="board_name" value="${boarddata.board_name}" readOnly>
        </div>
        <div class="form-group">
            <label for="board_subject">제목</label>
            <textarea name="board_subject" id="board_subject"
                      class="form-control" rows="1" maxlength="100">${boarddata.board_subject}</textarea>
        </div>
        <div class="form-group">
            <label for="board_content">내용</label>
            <textarea name="board_content" id="board_content"
                      class="form-control" rows="10">${boarddata.board_content}</textarea>
        </div>
        <%-- 원문글인 경우에만 파일 첨부 수정 가능합니다.--%>
        <c:if test="${boarddata.board_re_lev == 0}">
            <div class="form-group read">
                <label>파일 첨부
                    <img src="${pageContext.request.contextPath}/image/attach.png" alt="파일첨부" width="20px">
                    <input type="file" id="upfile" name="uploadfile">
                </label>
                <span id="filevalue">${boarddata.board_original}</span>
                <img src="${pageContext.request.contextPath}/image/remove.png" alt="파일삭제" width="10px" class="remove">
            </div>
        </c:if>

        <div class="form-group">
            <label for="board_pass">비밀번호</label>
            <input type="password" name="board_pass" id="board_pass"
                   class="form-control"size="10" maxlength="30" placeholder="Enter password">
        </div>
        <div class="form-group">
            <button type=submit class="btn btn-primary">수정</button>
            <button type=reset class="btn btn-danger" onclick="history.go(-1)">취소</button>
        </div>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
</div>
</body>
</html>