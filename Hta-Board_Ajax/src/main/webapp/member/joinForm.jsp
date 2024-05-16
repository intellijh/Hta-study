<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>유효성 검사</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/css/join.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
    <script>
        $(function () {
            let checkid= false;
            let checkemail = false;

            $("input[name=id]").keyup(function () {
                const pattern = /^\w{5,12}$/;
                const id = $(this).val();
                if (!pattern.test(id)) {
                    $("#id_message").css("color", "red")
                        .html("영문자 숫자 _로 5~12자만 가능합니다.");
                    checkid = false;
                    return;
                }

                $.ajax({
                    url: "idcheck.net",
                    data: {"id": id},
                    success: function (data) {
                        if (data == "-1") {
                            $("#id_message").css("color", "green")
                                .html("사용가능한 아이디 입니다.");
                            checkid = true;
                        } else {
                            $("#id_message").css("color", "blue")
                                .html("사용 중인 아이디 입니다.");
                            checkid = false;
                        }
                    },
                })
            });

            $("input[name=email]").keyup(function () {
                const pattern = /^\w+@\w+[.]\w+$/;
                const email_value = $(this).val();
                if (!pattern.test(email_value)) {
                    $("#email_message").css("color", "red")
                        .html("이메일 형식이 맞지 않습니다.");
                    checkemail = false;
                } else {
                    $("#email_message").css("color", "green")
                        .html("이메일 형식에 맞습니다.");
                    checkemail = true;
                }
            });

            $("form").submit(function () {
                if (!$.isNumeric($("input[name=age]").val())) {
                    alert("나이는 숫자를 입력하세요");
                    $("input[name=age]").val("").focus();
                    return false;
                }

                if (!checkid) {
                    alert("사용가능한 id로 입력하세요.");
                    $("input[name=id]").val("").focus();
                    $("#id_message").text("");
                    return false;
                }

                if (!checkemail) {
                    alert("email 형식을 확인하세요");
                    $("input[name=email]").focus();
                    return false;
                }
            });
        });
    </script>
</head>
<body>
<form method="post" action="joinProcess.net" name="joinForm">
    <h1>회원가입</h1>
    <hr>
    <b>아이디</b>
    <input type="text" placeholder="Enter ID" name="id" maxlength="12" required>
    <span id="id_message"></span>

    <b>비밀번호</b>
    <input type="password" placeholder="Enter Password" name="pass" maxlength="20" required>

    <b>이름</b>
    <input type="text" name="name" placeholder="Enter name" maxlength="5" required>

    <b>나이</b>
    <input type="text" name="age" maxlength="2" placeholder="Enter age" required>

    <b>성별</b>
    <div>
        <input type="radio" name="gender" value="남" checked><span>남자</span>
        <input type="radio" name="gender" value="여"><span>여자</span>
    </div>
    <br>

    <b>이메일 주소</b>
    <input type="text" name="email" placeholder="Enter email" maxlength="30" required>
    <span id="email_message"></span>
    <div class="clearfix">
        <button type="submit" class="submitbtn">회원가입</button>
        <button type="reset" class="cancelbtn">다시작성</button>
    </div>
</form>
</body>
</html>