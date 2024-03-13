<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인 화면</title>
<%--    <link href="../../css/ch03-1.css" type="text/css" rel="stylesheet">--%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/js/jquery-3.7.1.min.js"></script>
    <style>
        .container{margin:3em auto; border:1px solid lightgray;width:500px}
    </style>
    <script>
        $(function () {
            const result = "${message}";
            if (result != "") {
                alert(result);
                <%session.removeAttribute("message");%>
            }

            const id = "${cookie_id}";
            if (id) {
                $("#id").val(id).css("font-weight", "bold");
                $("#remember").prop("checked", true);
            }
        });
    </script>
</head>
<body>
<div class="container">
    <form action="loginProcess.net" method="post" class="border-light p-5" >
        <p class="h4 mb-4 text-center">login</p>
        <div class="form-group">
            <label for="id">id</label>
            <input type="text" class="form-control"  id="id" name="id" placeholder="Enter id" required>
        </div>
        <div class="form-group">
            <label for="pass">Password</label>
            <input type="password" class="form-control" id="pass"
                   name="passwd" placeholder="Enter password" required>
        </div>

        <div class="form-group custom-control custom-checkbox">
            <input type="checkbox" class="custom-control-input"
                   id="remember" name="remember" value="store">
            <label class="custom-control-label" for="remember">아이디 기억하기</label>
        </div>

        <button type="submit" class="btn btn-info my-4 btn-block">Submit</button>

    </form>
</div>
</body>
</html>
