<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<script>
    $(function () {
        $("#logout").click(function (event) {
            event.preventDefault();
            $("form[name=logout]").submit();
        });
    });
</script>

<style>
    body > nav.navbar {
        justify-content: flex-end; /* 오른쪽 정렬 */
    }

    .dropdown-menu {
        min-width: 0rem;
    }

    /* nav 색상 지정 */
    .navbar {
        background: #096988;
        margin-bottom: 3em;
        padding-right: 3em;
    }

    .navbar-dark .navbar-nav .nav-link {
        color: rgb(255, 255, 255);
    }

    textarea {
        resize: none;
    }
</style>

<%-- 현재 사용자가 인증되지 않은 사용자인 경우 --%>
<sec:authorize access="isAnonymous()">
    <script>
        location.href = "${pageContext.request.contextPath}/member/login";
    </script>
</sec:authorize>

<nav class="navbar navbar-expand-sm right-block navbar-dark">
    <ul class="navbar-nav">
        <sec:authorize access="isAuthenticated()"> <%-- 현재 사용자가 인증이 된 사용자인 경우 --%>
            <%--
                현재 사용자의 인증 정보를 쉽게 접근할 수 있도록 도와줍니다.
                인증된 정보를 변수 pinfo에 저장하라는 의미입니다.
            --%>
            <sec:authentication property="principal" var="pinfo"/>
        </sec:authorize>
        <c:if test="${!empty pinfo.username}">
            <li class="nav-item">
                <form name="logout" action="${pageContext.request.contextPath}/member/logout" method="post"
                      style="margin-bottom: 0">
                    <a class="nav-link" href="#" id="logout">
                        <span id="loginid">${pinfo.username}</span>님(로그아웃)
                    </a>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                </form>
            </li>
            <li class="nav-item">
                <a class="nav-Link" href="${pageContext.request.contextPath}/member/update">정보수정</a>
            </li>

            <c:if test="${pinfo.username == 'admin'}">
                <%-- Drop down --%>
                <li class="nav-item dropdown">
                    <a class="nav-Link dropdown-toggle" href="#" id="navbardrop"
                       data-toggle="dropdown"> 관리자 </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/member/list">회원정보</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/board/list">게시판</a>
                    </div>
                </li>
            </c:if>
        </c:if>
    </ul>
</nav>