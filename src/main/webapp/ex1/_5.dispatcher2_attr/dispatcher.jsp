<%--
  Created by IntelliJ IDEA.
  User: jhta
  Date: 2024-03-04
  Time: 오전 10:10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>dispatcher.jsp</title>
    <style>
        body {
            background: pink;
        }
        div {
            color: red;
        }
        p {
            color: green;
        }
    </style>
</head>
<body>
<%--
    request 객체의 getAttribute() 메소드를 이용해서 속성 "food"의 값을 가져옵니다.
    jsp 에서 <%= 값 또는 수식 또는 변수 %>는 '값 또는 수식 또는 변수'를 출력하라는 의미입니다.
--%>
<div>
    request 속성 food 값 : <%=request.getAttribute("food")%><br>
    request 속성 name 값 : <%=request.getAttribute("name")%><br>
</div>
<p>
    요청 주소 : <%=request.getRequestURL()%>
</p>
</body>
</html>
