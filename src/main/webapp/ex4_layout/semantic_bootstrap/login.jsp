<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인 화면</title>
<%--    <link href="../../css/ch03-1.css" type="text/css" rel="stylesheet">--%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/js/jquery-3.7.1.js"></script>
    <style>
        .container{margin:3em auto; border:1px solid lightgray;width:500px}
    </style>

</head>
<body>
<div class="container">
    <form action="login_ok.jsp" method="post" class="border-light p-5" >
        <p class="h4 mb-4 text-center">login</p>
        <div class="form-group">
            <label for="id">id</label>
            <input type="text" class="form-control"  id="id" placeholder="Enter id"  name="id">
        </div>
        <div class="form-group">
            <label for="pass">Password</label>
            <input type="password" class="form-control" id="pass"
                   placeholder="Enter password" name="passwd">
        </div>

        <div class="form-group custom-control custom-checkbox">
            <input type="checkbox" class="custom-control-input"
                   id="remember" name="remember" value="store">
            <label class="custom-control-label" for="remember">아이디 기억하기</label>
        </div>

        <button type="submit" class="btn btn-info my-4 btn-block">Submit</button>

    </form>
</div>
<%
    String rememberId = "";
    String id = "";
    String cookie = request.getHeader("Cookie");

    if (cookie != null) {
        Cookie[] cookies = request.getCookies();

        for (Cookie c : cookies) {
            if (c.getName().equals("rememberId")) {
                rememberId = c.getValue();
            } else if (c.getName().equals("id")) {
                id = c.getValue();
            }
        }
    }
%>
<script>
    $(function () {
        const $remember = $("#remember");
        const cookieId = "<%=id%>";
        const rememberId = "<%=rememberId%>";

        if (rememberId === "store") {
            $("#id").val(cookieId);
            $remember.prop("checked", true);
        }
    });
</script>
</body>
</html>
