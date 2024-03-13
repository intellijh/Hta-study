<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/css/NewFile.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/validate.js"></script>
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
    <title>update.jsp</title>
</head>
<body>
<%--<jsp:include page="top.jsp"/>--%>
<c:if test="${!empty temp}">
    <form method="post" action="updateProcess.net" name="myform" id="myform">
        <div class="container">
            <fieldset>
                <legend>정보 수정</legend>
                <label for="id">ID</label>
                <div>
                    <input type="text" name="id" id="id" readonly
                           value="${temp.id}" style="background: #ccc" width="100%">
                </div>

                <label for="pass">Password</label>
                <input type="password" placeholder="Enter Password" name="pass" id="pass"
                       value="${temp.password}" maxlength="20">

                <label for="jumin1">주민번호</label>
                <input type="text" maxlength="6" placeholder="주민번호 앞자리"
                       name="jumin1" id="jumin1" value="${temp.jumin.substring(0,6)}">
                <b> - </b>
                <input type="text" maxlength="7" placeholder="주민번호 뒷자리"
                       name="jumin2" id="jumin2" value="${temp.jumin.substring(7)}">

                <label for="email">E-Mail</label>
                <input type="text" name="email" id="email" value="${temp.email.split("@")[0]}"> @
                <input type="text" name="domain" id="domain" value="${temp.email.split("@")[1]}">
                <select name="sel" id="sel">
                    <option value="">직접입력</option>
                    <option value="naver.com">naver.com</option>
                    <option value="daum.net">daum.net</option>
                    <option value="nate.com">nate.com</option>
                    <option value="gmail.com">gmail.com</option>
                </select>

                <label for="gender1">성별</label>
                <div class="container2">
                    <input type="radio" name="gender" value="m" id="gender1" onclick="return false">남자
                    <input type="radio" name="gender" value="f" id="gender2" onclick="return false">여자
                </div>

                <label for="hobby1">취미</label>
                <div class="container2">
                    <input type="checkbox" name="hobby" id="hobby1" value="공부">공부
                    <input type="checkbox" name="hobby" id="hobby2" value="게임">게임
                    <input type="checkbox" name="hobby" id="hobby3" value="운동">운동
                    <input type="checkbox" name="hobby" id="hobby4" value="등산">등산
                    <input type="checkbox" name="hobby" id="hobby5" value="낚시">낚시
                </div>

                <label for="post1">우편번호</label>
                <input type="text" size="5" maxLength="5" name="post1" id="post1" value="${temp.post}">
                <input type="button" value="우편검색" id="postcode">

                <label for="address">주소</label>
                <input type="text" size="50" name="address" id="address" value="<c:out value='${temp.address}'/>">

                <label for="intro">자기소개</label>
                <textarea rows="10" cols="75" name="intro" id="intro">${temp.intro}</textarea>

                <div class="clearfix">
                    <button type="submit" class="signupbtn">Update</button>
                    <button type="reset" class="cancelbtn">Cancel</button>
                </div>
            </fieldset>
            <a href="templatetest.net">templatetest.jsp 이동하기</a>
            <a href="javascript:history.back()">이전 페이지로 이동하기</a>
        </div>
    </form>
</c:if>
<c:if test="${empty temp}">
    <h3 style="text-align: center; position: relative; top: 3em;">g해당 정보가 존재하지 않습니다.</h3>
</c:if>
<script>
    const gender = "${temp.gender}"
    $("input[value=" + gender + "]").prop("checked", true);

    const hobbys = "${temp.hobby}".split(",");
    for (let i = 0; i < hobbys.length; i++) {
        $("input[value=" + hobbys[i] + "]").prop("checked", true);
    }
</script>
</body>
</html>