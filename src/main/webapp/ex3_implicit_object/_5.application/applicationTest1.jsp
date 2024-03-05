<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Application Test</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<%-- application 객체는 웹 어플리케이션 전반에 걸쳐서 사용되는 정보를 담고 있습니다.--%>
<body>
<div class="container">
    <h1>세션 설정 및 메소드 사용법</h1>
    <table class="table table-striped">
        <tr>
            <td>Servlet API 스펙의 버전</td>
            <td>
                <%=application.getMajorVersion()%>.
                <%=application.getMinorVersion()%>
            </td>
        </tr>
        <tr>
            <td>컨테이너 이름과 버전</td>
            <td>
                <%=application.getServerInfo()%>
            </td>
        </tr>
        <tr>
            <td>웹 어플리케이션의 context 경로</td>
            <td>
                <%=application.getContextPath()%>
            </td>
        </tr>
        <tr>
            <td>웹 어플리케이션의 실제 파일 시스템 경로</td>
            <td>
                <%=application.getRealPath("/")%>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
