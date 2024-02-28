$(function () {
    $("#myform").submit(function () {
        const $id = $("#id");
        const $pass = $("#pass");
        const $jumin1 = $("#jumin1");
        const $jumin2 = $("#jumin2");
        const $email = $("#email");
        const $domain = $("#domain");
        const $gender = $("input:radio:checked");
        const $hobbys = $("input:checkbox:checked");
        const $post = $("#post1");
        const $address = $("#address");
        const $intro = $("#intro");

        if ($.trim($id.val()) === "") {
            alert("ID를 입력하세요")
            $id.focus();
            return false;
        }
        //Password
        if ($.trim($pass.val()) === "") {
            alert("Password를 입력하세요")
            $pass.focus();
            return false;
        }

        // 주민번호
        if ($.trim($jumin1.val()) === "") {
            alert("주민번호 앞자리를 입력하세요")
            $jumin1.focus();
            return false;
        }
        if ($.trim($jumin1.val()).length !== 6) {
            alert("주민번호 앞자리 6자리를 입력하세요");
            $jumin1.val("").focus;
            return false;
        }
        if ($.trim($jumin2.val()) === "") {
            alert("주민번호 뒷자리를 입력하세요");
            $jumin2.focus();
            return false;
        }
        if ($.trim($jumin2.val()).length !== 7) {
            alert("주민번호 뒷자리 7자리를 입력하세요");
            $jumin2.val("").focus();
            return false;
        }

        //이메일
        if ($.trim($email.val()) === "") {
            alert("E-Mail을 입력하세요")
            $email.focus();
            return false;
        }
        if ($.trim($domain.val()) === "") {
            alert("E-Mail을 입력하세요")
            $domain.focus();
            return false;
        }

        //성별
        if ($gender.length === 0) {
            alert("성별을 체크해주세요")
            $("#gender1").focus();
            return false;
        }

        //취미
        if ($hobbys.length < 2) {
            alert("취미를 2개 이상 선택하세요")
            return false;
        }

        // 우편번호
        if ($.trim($post.val()) === "") {
            alert("우편번호를 입력하세요")
            $post.focus();
            return false;
        }
        if (!$.isNumeric($post.val())) {
            alert("우편번호는 숫자만 입력 가능합니다")
            $post.val("").focus();
            return false;
        }

        // 주소
        if ($.trim($address.val()) === "") {
            alert("주소를 입력하세요");
            $address.focus();
            return false;
        }

        // 자기소개
        if ($.trim($intro.val()) === "") {
            alert("자기소개를 입력하세요")
            $intro.focus();
            return false;
        }
    });

    const idCheck = "#myform > fieldset > div:nth-child(3) > input[type=button]:nth-child(2)";
    $(idCheck).click(function () {
        const $id = $("#id");
        const $id_value = $.trim($id.val());
        const reg = /^[A-Z][A-Za-z0-9_]{4,}$/;

        // ID
        if ($id_value === "") {
            alert("ID를 입력하세요")
            $id.focus();
            return false;
        }
        if (!reg.test($id_value)) {
            alert("첫글자는 대문자이고 두번째부터는 대소문자, 숫자, _로 총 4개 이상이어야 합니다.");
            return false;
        }

        const openWin = window.open(`idcheck.html?id=${$id_value}`, "아이디 중복 검사", "width=300, height=250");
        $(openWin).on("load", function() {
            $(openWin.document).find("#id").val($id_value);
        });
    });

    // 우편검색 버튼 클릭
    $("postcode").click(function () {
        window.open("post.html", "post", "width=400, height=500, scrollbars=yes");
    });

    // 도메인 선택 부분
    $("#sel").change(function () {
        const domain = $("#domain");
        const domainOption = $(this).val();

        domain.prop("readOnly", domainOption !== "")
        domain.val(domainOption);
    });

    // 주민번호 형식 검사
    $("#jumin1").keyup(function () {
        const jumin1 = $(this).val();
        const reg = /^[0-9]{2}(0[1-9]|1[012])(0[1-9]|1[0-9]|2[0-9]|3[01])$/;

        if (jumin1.length === 6) {
            if (reg.test(jumin1)) {
                $("#jumin2").focus();
            } else {
                alert("형식에 맞게 입력하세요[yymmdd]");
                $(this).val("").focus();
            }
        }
    });
    $("#jumin2").keyup(function () {
        const jumin2 = $(this).val();
        const reg = /^[1234][0-9]{6}$/;

        if (jumin2.length === 7) {
            if (reg.test(jumin2)) {
                const genderNum = Number(jumin2.substring(0, 1));
                const index = (genderNum - 1) % 2;
                $("input[type=radio]").eq(index).prop("checked", true);
            } else {
                alert("형식에 맞게 입력하세요[(1~4)******]");
                $(this).val("").focus();
            }
        } else {
            $("input[type=radio]").prop("checked", false);
        }
    });

/*
    function move() {
        const $jumin1 = $("#jumin1");
        const $jumin2 = $("#jumin2");
        const jumin1 = $jumin1.val();
        const jumin2 = $jumin2.val();

        const reg = /^[0-9]{2}(0[1-9]|1[012])(0[1-9]|1[0-9]|2[0-9]|3[01])-[1234][0-9]{6}$/;

        if (jumin1.length === 6) {
            jumin2.focus();
        }

        if (!$.isNumeric(jumin1)) {
            alert("숫자를 입력하세요");
            $jumin1.focus();
        } else if (!$.isNumeric(jumin2)) {
            alert("숫자를 입력하세요");
            $jumin2.focus();
        }

        const jumin = Number(jumin1) - Number(jumin2);
        const isValid = reg.test(jumin);

        const $gender1 = $("#gender1");
        const $gender2 = $("#gender2");

        if (jumin.length !== 14) {
            $gender1.prop("checked", false);
            $gender2.prop("checked", false);
            return false;
        }
        if (!isValid) {
            $gender1.prop("checked", false);
            $gender2.prop("checked", false);
            alert("주민번호 형식에 맞지 않습니다");
            return false;
        }

        const genderNum = Number(jumin.substring(7, 8));
        const index = (genderNum - 1) % 2;

        // const gender = document.getElementById(`gender${index+1}`);
        const $gender = $(`#gender${index + 1}`);
        $gender.checked = true;
    }
    // 주민번호 형식 검사
    $("#jumin1").keyup(move);

    $("#jumin2").keyup(move);
*/
});



/*
function move() {
    const jumin1 = document.getElementById("jumin1");
    const jumin2 = document.getElementById("jumin2");
    const reg = /^[0-9]{2}(0[1-9]|1[012])(0[1-9]|1[0-9]|2[0-9]|3[01])-[1234][0-9]{6}$/;

    if (jumin1.value.length === 6) {
        jumin2.focus();
    }

    if (isNaN(jumin1.value)) {
        alert("숫자를 입력하세요");
        jumin1.focus();
    } else if (isNaN(jumin2.value)) {
        alert("숫자를 입력하세요");
        jumin2.focus();
    }

    const jumin = `${jumin1.value}-${jumin2.value}`;
    const isValid = reg.test(jumin);


    if (jumin.length !== 14) {
        document.getElementById("gender1").checked = false;
        document.getElementById("gender2").checked = false;
        return false;
    }
    if (!isValid) {
        document.getElementById("gender1").checked = false;
        document.getElementById("gender2").checked = false;
        alert("주민번호 형식에 맞지 않습니다");
        return false;
    }

    const genderNum = Number(jumin.substring(7, 8));
    const index = (genderNum - 1) % 2;

    const gender = document.getElementById(`gender${index+1}`);
    gender.checked = true;
}
*/