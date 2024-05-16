$(function () {
    $("#myform").submit(function () {
        const id = $("#id");
        if ($.trim(id.val()) === "") {
            alert("ID를 입력하세요")
            id.focus();
            return false;
        }

        if (!id.prop("readOnly")) {
            //회원가입 폼과 정보 수정 폼에서 동시에 사용할 js입니다.
            //회원가입 폼에서만 사용할 문장들 입니다.
            //정보 수정 폼에서는 아이디를 수정하지 않기 때문에 필요없는 부분입니다.
            console.log(id.prop("readOnly"));
            const submit_id_value = $.trim(id.val());
            if (submit_id_value !== idcheck_value) { //submit 당시 아이디 값과 아이디 중복검사에 사용된 아이디 비교
                alert("ID 중복검사를 하세요");
                $("#opener_message").text("");
                return false;
            }
        }

        const result = $("#result").val();
        if (result === "-1") {
            alert("입력하신 아이디는 이미 사용 중입니다.");
            id.val("").focus();
            $("#opener_message").text("");
            return false;
        }

        //Password
        const $pass = $("#pass");
        if ($.trim($pass.val()) === "") {
            alert("Password를 입력하세요")
            $pass.focus();
            return false;
        }

        // 주민번호
        const $jumin1 = $("#jumin1");
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
        const $jumin2 = $("#jumin2");
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
        const $email = $("#email");
        if ($.trim($email.val()) === "") {
            alert("E-Mail을 입력하세요")
            $email.focus();
            return false;
        }
        const $domain = $("#domain");
        if ($.trim($domain.val()) === "") {
            alert("E-Mail을 입력하세요")
            $domain.focus();
            return false;
        }

        //성별
        const $gender = $("input:radio:checked");
        if ($gender.length === 0) {
            alert("성별을 체크해주세요")
            $("#gender1").focus();
            return false;
        }

        //취미
        const $hobbys = $("input:checkbox:checked");
        if ($hobbys.length < 2) {
            alert("취미를 2개 이상 선택하세요")
            return false;
        }

        // 우편번호
        const $post = $("#post1");
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
        const $address = $("#address");
        if ($.trim($address.val()) === "") {
            alert("주소를 입력하세요");
            $address.focus();
            return false;
        }

        // 자기소개
        const $intro = $("#intro");
        if ($.trim($intro.val()) === "") {
            alert("자기소개를 입력하세요")
            $intro.focus();
            return false;
        }
    });

    let idcheck_value = "";
    $("#idcheck").click(function () {
        const id = $("#id");
        const id_value = $.trim(id.val());

        if (id_value === "") {
            alert("ID를 입력하세요");
            id.focus();
            return false;
        } else {
            pattern = /^[A-Z][A-Za-z0-9_]{4,19}$/;
            if (pattern.test(id_value)) {
                idcheck_value = id_value;
                const ref = "idcheck.net?id=" + id_value;
                window.open(ref, "idcheck", "width=350, height=200");
            } else {
                alert("첫글자는 대문자이고 두번째부터는 대소문자, 숫자, _로 총 4~20자 입니다.");
                id.val("").focus();
            }
        }
    });

    // 우편검색 버튼 클릭
    $("#postcode").click(function () {
        new daum.Postcode({
            oncomplete: function (data) {
                console.log(data.zonecode)
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 도로명 조합형 주소 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if (data.buildingName !== '' && data.apartment === 'Y') {
                    extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if (extraRoadAddr !== '') {
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }
                // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
                if (fullRoadAddr !== '') {
                    fullRoadAddr += extraRoadAddr;
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                $("#post1").val(data.zonecode);
                $("#address").val(fullRoadAddr)
            }
        }).open();
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
        } else {
            $("input[type=radio]").prop("checked", false);
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
});