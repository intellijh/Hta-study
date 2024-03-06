<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container mt-3">
    <h2>신상품 목록 Table</h2>
    <p>상품을 입력하세요</p>
    <input class="form-control" id="myInput" type="search" placeholder="Search..">
    <br>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>상품이름</th>
            <th>가격</th>
            <th>제조사</th>
        </tr>
        </thead>
        <tbody id="myTable">
        <tr>
            <td>갤럭시S24</td>
            <td>1000</td>
            <td>삼성</td>
        </tr>
        <tr>
            <td>갤럭시노트</td>
            <td>1200</td>
            <td>삼성</td>
        </tr>
        <tr>
            <td>갤럭시탭</td>
            <td>800</td>
            <td>삼성</td>
        </tr>
        <tr>
            <td>아이폰15프로</td>
            <td>4000</td>
            <td>애플</td>
        </tr>
        </tbody>
    </table>
</div>

<script>
    $(function () {
        $("#myInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#myTable tr").filter(function () {
                //toggle(true)는 선택한 요소를 보이도록 설정하는 메소드입니다.
                //toggle(false)는 선택한 요소에 style="display: none;" 속성이 추가되어 보이지 않도록 합니다.
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>
