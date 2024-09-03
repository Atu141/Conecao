<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Projeto</title>
</head>
<body>

	<form action="home.jsp" method="post">
		<label for= "nome">Nome:</label>
			<input type="text" id="nome" name="nome" value="" size="30">
			
		<label for="senha">Senha:</label>
			<input type="password" id="senha" name="senha" value="" size="20">
	
			<input type="submit" value="Entrar">
	</form>
</body>
</html>