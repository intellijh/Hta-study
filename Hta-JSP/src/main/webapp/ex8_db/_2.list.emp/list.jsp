<%@ page import="java.util.ArrayList" %>
<%@ page import="ex8.emp.Emp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>DEPT LIST</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<%--    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>--%>
    <script src="js/jquery-3.7.1.min.js"></script>
    <style>
        .container {
            width: 800px;
            margin-top: 3em;
        }
        table, h4 {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <input class="form-control" id="search-input" type="search" placeholder="Search..">
    <%
        ArrayList<Emp> list = (ArrayList<Emp>) request.getAttribute("list");

        if (!list.isEmpty()) {
    %>
    <table class="table">
        <thead>
        <tr>
            <th>EMPNO</th>
            <th>ENAME</th>
            <th>JOB</th>
            <th>MGR</th>
            <th>HIREDATE</th>
            <th>SAL</th>
            <th>COMM</th>
            <th>DEPTNO</th>
        </tr>
        </thead>
        <tbody id="myTable">
        <%
            for (Emp emp : list) {
        %>
        <tr>
            <td><%=emp.getEmpno()%></td>
            <td><%=emp.getEname()%></td>
            <td><%=emp.getJob()%></td>
            <td><%=emp.getMgr()%></td>
            <td><%=emp.getHiredate()%></td>
            <td><%=emp.getSal()%></td>
            <td><%=emp.getComm()%></td>
            <td><%=emp.getDeptno()%></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <%
        } else  {
            out.print("<h4>조회된 데이터가 없습니다.</h4>");
        }
    %>
    <script>
        $(function () {
            $("#search-input").on("keyup", function () {
                var value = $(this).val().toLowerCase();
                $("#myTable tr").filter(function () {
                    //toggle(true)는 선택한 요소를 보이도록 설정하는 메소드입니다.
                    //toggle(false)는 선택한 요소에 style="display: none;" 속성이 추가되어 보이지 않도록 합니다.
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                });
            });
        });
    </script>
</div>
</body>
</html>
