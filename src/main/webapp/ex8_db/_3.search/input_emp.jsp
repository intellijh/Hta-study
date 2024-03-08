<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DEPT LIST</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="js/jquery-3.7.1.js"></script>
</head>
<body>
<div class="container"e>
    <form action='field_select'>
        <h3>검색할 컬럼명을 선택하세요</h3>
        <input type="search"  name="search" class="form-control mb-3"  placeholder="검색어를 입력하세요">
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
    <script>
        const menus = [ "사원번호", "사원이름", "직급", "매니저", "입사일자", "급여", "커미션", "부서번호"];
        let output = "<select name='field' class='custom-select mt-3 mb-3'>";
        $.each(menus, function (index, item) {
            output += "<option value=" + index + ">" + menus[index] + "</option>";
        });
        output += "</select>";
        $("form").find("input").before(output);
    </script>
</div>
</body>
</html>
