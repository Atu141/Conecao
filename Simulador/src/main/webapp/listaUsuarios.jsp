<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Usuários</title>
</head>
<body>

	Lista de usuários cadastrados:
	<ol>
		<c:forEach items="${usuarios}" var="usuario">
			<li>${usuario.nome}<fmt:formatDate value ="${usuario.dataCadastro}"/>
				<a href="/simuladorCRUD/removeUsuario?id=${usuario.id}">remover</a>
				<a href="/simuladorCRUD/mostraUsuario?id=${usuario.id}">editar</a>
			</li>
		</c:forEach>
	</ol>
</body>
</html>