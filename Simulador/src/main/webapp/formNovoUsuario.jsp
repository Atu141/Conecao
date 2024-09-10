<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<meta charset="ISO-8859-1">
<head>
<title>Cadastro</title>
</head>
<body>

	<form action="NovoUsuarioServlet" method="post">
	
		<label>Nome</label>
		<br>
		<input type=text name="nome">
		<br>
		<label>Email</label> 
		<input type=text name="email">
		<br>
		<label>Telefone</label>
		<br> 
		<input type=text name="tel">
		<br>
		<label>Senha</label>
		<br> 
		<input type=password name="senha">
		<br>
		<input type=hidden name="dataCadastro">
		<br>
		<input type=submit name="enviar" value="Enviar">
	</form>
</body>
</html>