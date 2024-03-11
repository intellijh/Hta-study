<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
    empty 연산자
    형식 ) empty 값
        1. 값이 비어 있거나 null인지를 조사할 때 사용하는 연산자 입니다.
        2. 값이 null이면 true를 반환합니다.
        3. 문자열과 배열의 크기가 0인 경우에도 true을 반환합니다.
--%>

<%
    session.setAttribute("login_id", "hong");
    session.invalidate();
%>

<c:out value="${login_id}"/>
<hr>

<c:if test="${empty login_id}">
    <strong style="color: red">아이디가 존재하지 않습니다.</strong>
</c:if>

<c:if test="${!empty login_id}">
    <strong style="color: green">아이디가 존재합니다.</strong>
</c:if>