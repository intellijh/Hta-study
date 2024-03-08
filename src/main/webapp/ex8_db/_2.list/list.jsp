<%@ page import="java.util.ArrayList" %>
<%@ page import="ex8.Dept" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DEPT LIST</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <style>
        .container {
            width: 500px;
            margin-top: 3em;
        }
        table, h4 {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <%
        ArrayList<Dept> list = (ArrayList<Dept>) request.getAttribute("list");

        if (!list.isEmpty()) {
    %>
    <table class="table">
        <thead>
        <tr>
            <th>DEPTNO</th>
            <th>DNAME</th>
            <th>LOC</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Dept dept : list) {
        %>
        <tr>
            <td><%=dept.getDeptno()%></td>
            <td><%=dept.getDname()%></td>
            <td><%=dept.getLoc()%></td>
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

</div>
</body>
</html>
