$(function () {
    $("#upfile").change(function () {
        console.log($(this).val())	//c:\fakepath\upload.png
        const inputfile = $(this).val().split('\\');
        $('#filevalue').text(inputfile[inputfile.length - 1]);// 3 - 1해서 2다.
    });

    // submit 버튼 클릭할 때 이벤트 부분
    $("form[name=boardform]").submit(function () {
        const $board_subject = $("#board_subject");
        if ($.trim($board_subject.val()) == "") {
            alert("제목을 입력하세요");
            $$board_subject.focus();
            return false;
        }

        const $board_content = $("#board_content");
        if ($.trim($board_content.val()) == "") {
            alert("내용을 입력하세요");
            $board_content.focus();
            return false;
        }

        const $board_pass = $("#board_pass");
        if ($.trim($board_pass.val()) == "") {
            alert("비밀번호를 입력하세요");
            $board_pass.focus();
            return false;
        }
    }); // submit end
}); // ready() end