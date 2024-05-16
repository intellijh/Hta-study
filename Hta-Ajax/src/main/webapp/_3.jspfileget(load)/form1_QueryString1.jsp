<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ajax</title>
    <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
    <script>
        $(function () {
            $("form").submit(function (e) {
                e.preventDefault();

                const name = "name=" + $("#name").val();
                const age = "age=" + $("#age").val();
                const address = "address=" + $("#address").val();
                const data = name + "&" + age + "&" + address;

                $("div").load(
                    "process.jsp",
                    data
                    );
            });
        });
    </script>
</head>
<body>
<%--
    form 태그의 action 속성이 없습니다. 이 상태에서 전송을 클릭하면 현재 URL을 다시 불러옵니다.
--%>
<form>
    <span>이름</span><input type="text" placeholder="이름" id="name" name="name" required><br>
    <span>나이</span><input type="text" placeholder="나이" id="age" name="age" required><br>
    <span>주소</span><input type="text" placeholder="주소" id="address" name="address" required><br>
    <input type="submit" value="전송">
</form>
<div></div>
</body>
</html>
