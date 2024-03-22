let option = 1; //선택한 등록순과 최신순을 수정, 삭제 추가 후에도 유지되도록 하기위한 변수로 사용됩니다.

function getList(state) {//현재 선택한 댓글 정렬방식을 저장합니다. 1=>등록순, 2=>최신순
    console.log(state);
    option = state;
    $.ajax({
        type: "post",
        url: "CommentList.bo",
        data: {"comment_board_num": $("#comment_board_num").val(), state: state},
        dataType: "json",
        success: function (rdata) {
            $("#count").text(rdata.listcount).css("font-family", "arial, sans-serif");
            let red1 = "red";
            let red2 = "red";
            if (state == 1) {
                red2 = "gray";
            } else if (state == 2) {
                red1 = "gray";
            }

            let output = "";
            if (rdata.commentlist.length > 0) {
                output +=
                    '<li class="comment-order-item ' + red1 + '">   \n' +
                    '    <a href="javascript:getList(1)" class="comment-order-button">등록순 </a>\n' +
                    '</li>\n' +
                    '<li class="comment-order-item ' + red2 + '">   \n' +
                    '    <a href="javascript:getList(2)" class="comment-order-button">최신순</a>\n' +
                    '</li>';
                $(".comment-order-list").html(output);

                output = "";
                $(rdata.commentlist).each(function () {
                    const lev = this.comment_re_lev;
                    let comment_reply = "";
                    if (lev == 1) {
                        comment_reply = " comment-list-item--reply lev1";
                    } else if (lev == 2) {
                        comment_reply = " comment-list-item--reply lev2";
                    }
                    const profile = this.memberfile;
                    let src = "image/profile.png";
                    if (profile) {
                        src = "memberupload/" + profile;
                    }

                    output +=
                        '<li id="' + this.num + '" class="comment-list-item' + comment_reply + '">   \n' +
                        ' <div class="comment-nick-area">    \n' +
                        '  <img src="' + src + '" alt="프로필 사진" width="36" height="36">    \n' +
                        '  <div class="comment-box">      \n' +
                        '     <div class="comment-nick-box">            \n' +
                        '        <div class="comment-nick-info">               \n' +
                        '           <div class="comment-nickname">' + this.id + '</div>\n' +
                        '        </div>       \n' +
                        '     </div>    \n' +
                        '   </div>    \n' +
                        '   <div class="comment-text-box">       \n' +
                        '      <p class="comment-text-view">         \n' +
                        '         <span class="text-comment">' + this.content + '</span>       \n' +
                        '      </p>    \n' +
                        '   </div>    \n' +
                        '   <div class="comment-info-box">      \n' +
                        '      <span class="comment-info-date">2022-08-05 14:07:15</span>  \n';
                    if (lev < 2) {
                        output +=
                            '      <a href="javascript:replyform(' + this.num + ',' +
                            lev + ',' + this.comment_re_seq + ',' + this.comment_re_seq +
                            ')" class="comment-info-button">답글쓰기</a>   \n';
                    }
                    output +=
                        '   </div>\n';

                    if ($("loginid").val() == this.id) {
                        output +=
                            '   <div class="comment-tool">    \n' +
                            '      <div title="더보기" class="comment-tool-button">       \n' +
                            '       <div>...</div>    \n' +
                            '      </div>    \n' +
                            '      <div id="comment-list-item-layer' + this.num + '"  class="LayerMore">     \n' +
                            '       <ul class="layer-list">      \n' +
                            '          <li class="layer-item">       \n' +
                            '            <a href="javascript:updateForm(' + this.num + ')" class="layer-button">수정</a>&nbsp;&nbsp;       \n' +
                            '            <a href="javascript:del(' + this.num + ')" class="layer-button">삭제</a>\n' +
                            '          </li>\n' +
                            '       </ul>    \n' +
                            '      </div>   \n' +
                            '    </div>\n';
                    }
                    output +=
                        ' </div>\n' +
                        '</li>';
                });

                $(".comment-list").html(output);
            } else { //댓글 1개가 있는 상태에서 삭제하는 경우 갯순는 0이라 if문을 수행하지 않고 이곳으로 옵니다.
                     //이곳에서 아래의 두 영역을 없앱니다.
                $(".comment-list").empty();
                $(".comment-order-list").empty();
            }

        },
    })
}//function(getList) end

//더보기-수정 클릭한 경우에 수정 폼을 보여줍니다.
function updateForm(num) { //num : 수정할 댓글 글번호

}//function(updateForm) end


//더보기 -> 삭제 클릭한 경우 실행하는 함수
function del(num) {//num : 댓글 번호

}//function(del) end


//답글 달기 폼
function replyform(num, lev, seq, ref) {

}//function(replyform) end

$(function () {

    getList(option);  //처음 로드 될때는 등록순 정렬

    $('form[name="deleteForm"]').submit(function () {
        if ($("#board_pass").val() == "") {
            alert("비밀번호를 입력하세요");
            $("#board_pass").focus();
            return false;
        }
    });// form


    $('.comment-area').on('keyup', '.comment-write-area-text', function () {
        const length = $(this).val().length;
        $(this).prev().text(length + "/200");
    });// keyup','.comment-write-area-text', function() {


    //댓글 등록을 클릭하면 데이터베이스에 저장 -> 저장 성공 후에 리스트 불러옵니다.
    $('ul+.comment-write .btn-register').click(function () {
        const content = $(".comment-write-area-text").val();
        if (!content) {
            alert("댓글을 입력하세요");
            return;
        }

        $.ajax({
            url: "CommentAdd.bo", //원문 등록
            data: {
                id: $("#loginid").val(),
                content: content,
                comment_board_num: $("#comment_board_num").val(),
                comment_re_lev: 0, //원문인 경우 comment_re_seq는 0,
                                   //comment_re_ref는 댓글의 원문 글번호
                comment_re_seq: 0
            },
            type: "post",
            success: function (rdata) {
                if (rdata == 1) {
                    getList(option);
                }
            },
        })

        $(".comment-write-area-text").val(""); //textarea 초기화
        $(".comment-write-area-count").text("0/200"); //입력한 글 카운트 초기화
    });// $('.btn-register').click(function(){


    //더보기를 클릭한 경우
    $(".comment-list").on('click', '.comment-tool-button', function () {

    });

    //수정 후 수정완료를 클릭한 경우
    $('.comment-area').on('click', '.update', function () {

    });//수정 후 수정완료를 클릭한 경우


    //수정 후 취소 버튼을 클릭한 경우
    $('.comment-area').on('click', '.btn-cancel', function () {

    });//수정 후 취소 버튼을 클릭한 경우


    //답글완료 클릭한 경우
    $('.comment-area').on('click', '.reply', function () {


    });//답글완료 클릭한 경우


    //답글쓰기 후 취소 버튼을 클릭한 경우
    $('.comment-area').on('click', '.reply-cancel', function () {

    });//답글쓰기  후 취소 버튼을 클릭한 경우


    //답글쓰기 클릭 후 계속 누르는 것을 방지하기 위한 작업
    $('.comment-area').on('click', '.comment-info-button', function (event) {

    });//답글쓰기 클릭 후 계속 누르는 것을 방지하기 위한 작업
});//ready
