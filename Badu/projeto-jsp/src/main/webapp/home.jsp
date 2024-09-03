<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	Nome: <%= request.getParameter("nome") %> <br>
	
	<%
	String senha = request.getParameter("senha");
	if(senha.equals("123456")){ %>
		<b> Login efetuado com sucesso.</b>
	<%} else {%>
		<b>Senha inválida.</b>	 
	<%} %>

</body>
</html>