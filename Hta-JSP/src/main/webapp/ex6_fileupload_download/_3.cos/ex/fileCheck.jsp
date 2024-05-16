<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title>파일 업로드 확인 및 다운로드</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	<style>
	.container{margin-top:3em}
	table,h4{text-align:center}
	td:first-child{width:30%}
	td:last-child{width:70%}
	</style>
</head>
<body>
<%
	String systemName1 = (String)request.getAttribute("systemName1");
	String systemName2 = (String)request.getAttribute("systemName2");
	String origfileName1 = (String)request.getAttribute("origfileName1");
	String origfileName2 = (String)request.getAttribute("origfileName2");
	String uploadPath = (String)request.getAttribute("uploadPath");
%>	 
 <div class="container">
 <h4> 파일 다운로드 폼</h4>
 <table class="table table-bordered table-striped">
 	<tr>
 		<td>작성자</td><td><%=request.getAttribute("name") %></td>
 	</tr>
 	
 	<tr>
 		<td>제목</td><td><%=request.getAttribute("subject") %></td>
 	</tr>

 	<tr>
 		<td>파일명1</td>
 		<td>
 		<a href="<%=request.getContextPath()%>/down?file_name=<%=systemName1 %>"><%=origfileName1 %>
 		</a>
 		</td>
 	</tr>

 	<tr>
 		<td>파일명2</td>
 		<td>
 		<a href="<%=request.getContextPath()%>/down?file_name=<%=systemName2 %>"><%=origfileName2 %>
 		</a>
 		</td>
 	</tr>

 	<tr>
 		<td>uploadPath</td><td><%=uploadPath %></td>
 	</tr>
	 </table>
	</div>
</body>
</html>    