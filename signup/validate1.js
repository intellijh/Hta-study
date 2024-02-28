function check() {
    const id = document.getElementById("id");
    const pass = document.getElementById("pass");
    const jumin1 = document.getElementById("jumin1");
    const jumin2 = document.getElementById("jumin2");
    const email = document.getElementById("email");
    const domain = document.getElementById("domain");
    const post = document.getElementById("post1");
    const address = document.getElementById("address");
    const intro = document.getElementById("intro");

    // ID
    if (id.value.trim() === "") {
        alert("ID를 입력하세요")
        id.focus();
        return false;
    }

    //Password
    if (pass.value.trim() === "") {
        alert("Password를 입력하세요")
        pass.focus();
        return false;
    }

    // 주민번호
    if (jumin1.value.trim() === "") {
        alert("주민번호 앞자리를 입력하세요")
        jumin1.focus();
        return false;
    }
    if (jumin1.value.length !== 6) {
        alert("주민번호 앞자리 6자리를 입력하세요");
        jumin1.value = "";
        jumin1.focus();
        return false;
    }
    if (jumin2.value.trim() === "") {
        alert("주민번호 뒷자리를 입력하세요");
        jumin2.focus();
        return false;
    }
    if (jumin2.value.length !== 7) {
        alert("주민번호 뒷자리 7자리를 입력하세요");
        jumin2.value = "";
        jumin2.focus();
        return false;
    }

    //성별
    const gender = document.querySelectorAll("input[type=radio]:checked");
    if (gender.length === 0) {
        alert("성별을 체크해주세요")
        document.getElementById("gender1").focus();
        return false;
    }
    /*
    const gender1 = document.getElementById("gender1");
    const gender2 = document.getElementById("gender2");
    if (!gender1.checked && !gender2.checked) {
        alert("성별을 체크해주세요")
        gender1.focus();
        return false;
    }
*/

    //취미
    const hobbys = document.querySelectorAll("input[type=checkbox]:checked");
    if (hobbys.length < 2) {
        alert("취미를 2개 이상 선택하세요")
        return false;
    }
    /*
    let cnt = 0;
    const hobbys = document.getElementsByName("hobby");
    for (let i = 0; i < hobbys.length; i++) {
        if (hobbys[i].checked) {
            cnt++;
        }
    }
    if (cnt < 2) {
        alert("취미를 2개 이상 선택하세요")
        return false;
    }
*/

    //이메일
    if (email.value.trim() === "") {
        alert("E-Mail을 입력하세요")
        email.focus();
        return false;
    }
    if (domain.value.trim() === "") {
        alert("E-Mail을 입력하세요")
        email.focus();
        return false;
    }

    // 우편번호
    if (post.value.trim() === "") {
        alert("우편번호를 입력하세요")
        post.focus();
        return false;
    }

    // 주소
    if (address.value.trim() === "") {
        alert("주소를 입력하세요")
        address.focus();
        return false;
    }

    // 자기소개
    if (intro.value.trim() === "") {
        alert("자기소개를 입력하세요")
        intro.focus();
        return false;
    }
}

function idcheck() {
    const id = document.getElementById("id");
    const reg = /^[A-Z][A-Za-z0-9_]{4,}$/;

    // ID
    if (id.value.trim() === "") {
        alert("ID를 입력하세요")
        id.focus();
        return false;
    }
    if (!reg.test(id.value.trim())) {
        alert("첫글자는 대문자이고 두번째부터는 대소문자, 숫자, _로 총 4개 이상이어야 합니다.");
        return false;
    }

    const openWin = window.open(`idcheck.html?id=${id.value}`, "아이디 중복 검사", "width=300, height=250");
    openWin.onload = function() {
        openWin.document.getElementById("id").value = id.value;
    };
}

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

    // document.getElementById("jumin1").addEventListener("input", move);
    // document.getElementById("jumin2").addEventListener("input", move);
}


function domain1() {
    const selectedDomain = document.getElementById("sel");
    const domain = document.getElementById("domain");
    const domainOption = selectedDomain.options[selectedDomain.selectedIndex].value;

    domain.readOnly = domainOption !== "";
    domain.value = domainOption;
}

/*
function domain1() {
    var sel = document.getElementById('sel');
    var domain = document.getElementById('domain');
    if (sel.value == "") {  // 직접입력 선택된 경우
        domain.readOnly = false;
        domain.value = "";
        domain.focus();
    } else { // option 중에서 선택한 경우
        domain.readOnly = true; // 수정 불가
        domain.value = sel.value;
    }
}
*/

function post() {
    window.open("post.html", "우편번호 검색", "width=400, height=500");
}