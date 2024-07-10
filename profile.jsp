<%@page import="loginregisterform.user"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
user user= (user)session.getAttribute("session_user");
%>
<h2>welcome</h2>
<h3>name:<%=user.getName() %></h3>
<h3> email:<%=user.getEmail() %></h3>
<h3> city:=<%=user.getCity() %></h3>

<a href="logout">logout</a>
</body>
</html>
