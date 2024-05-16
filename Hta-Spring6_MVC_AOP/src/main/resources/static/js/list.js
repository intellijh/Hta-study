function go(page) {
    const limit = $("#viewcount").val();
    // const data = `limit=${limit}&state=ajax&page=${page}`;
    const data = {limit: limit, state: "ajax", page: page};
    ajax(data);
}

function setPaging(href, digit) {
    let active = "";
    let gray = "";
    if (href == "") {
        if (isNaN(digit)) {
            gray = "gray";
        } else {
            active = "active";
        }
    }

    let output = `<li class='page-item ${active}'>`;
    let anchor = `<a class='page-link ${gray}' ${href}>${digit}</a></li>`;
    output += anchor;
    return output;
}

function ajax(sdata) {
    console.log(sdata);

    $.ajax({
        type: "POST",
        data: sdata,
        url: "list_ajax",
        dataType: "json",
        cache: false,
        success: function (data) {
            $("#viewcount").val(data.limit);
            $("thead").find("span").text("글 개수 : " + data.listCount);

            if (data.listCount > 0) {
                $("tbody").remove();
                let num = data.listCount - (data.page - 1) * data.limit;
                console.log(num);
                let output = "<tbody>";
                $(data.boardList).each(function (index, item) {
                    output += "<tr><td>" + (num--) + "</td>";
                    const blank_count = item.board_re_lev * 2 + 1;
                    let blank = "&nbsp;";
                    for (let i = 0; i < blank_count; i++) {
                        blank += "&nbsp;&nbsp;";
                    }

                    let img = "";
                    if (item.board_re_lev > 0) {
                        img = "<img src='image/line.gif'>";
                    }

                    let subject = item.board_subject;
                    if (subject.length >= 20) {
                        subject = subject.substring(0, 21) + "...";
                    }

                    output += "<td><div>" + blank + img;
                    output += "<a href='BoardDetail.bo?num=" + item.board_num + "'>";
                    output += subject.replace(/</g, "&lt;").replace(/>/g, "&gt;")
                        + "</a>[" + item.cnt + "]</div></td>";
                    output += "<td><div>" + item.board_name + "</div></td>";
                    output += "<td><div>" + item.board_date + "</div></td>";
                    output += "<td><div>" + item.board_readcount + "</div></td></tr>";
                });
                output += "</tbody>"
                $("table").append(output);

                $(".pagination").empty();
                output = "";

                let digit = "이전&nbsp;";
                let href = "";
                if (data.page > 1) {
                    href = "href=javascript:go(" + (data.page - 1) + ")";
                }
                output += setPaging(href, digit);

                for (let i = data.startPage; i <= data.endPage; i++) {
                    digit = i;
                    href = "";
                    if (i != data.page) {
                        href = "href=javascript:go(" + i + ")";
                    }
                    output += setPaging(href, digit);
                }

                digit = "&nbsp;다음&nbsp;";
                href = "";
                if (data.page < data.maxPage) {
                    href = "href=javascript:go(" + (data.page + 1) + ")";
                }
                output += setPaging(href, digit);

                $(".pagination").append(output);
            }
        },
        error: function () {
            console.log("에러");
        },
    })
}

$(function () {
    $("button").click(function () {
        location.href = "write";
    });

    $("#viewcount").change(function () {
        go(1);
    });
});