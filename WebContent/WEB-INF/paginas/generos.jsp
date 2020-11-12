<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="utf-8">
		<title>Cadastro de Generos</title>
		<link rel="icon" href="imagens/favicon.png">
		<link rel="stylesheet" href="style.css">
	</head>
	<body>
		<h1>Generos Cadastrados</h1>
		<h4>Lista de Generos</h4>
		
		<table border="1">
			<tr>
				<th>Id</th>
				<th>Genero</th>
				<th>Ações</th>
			</tr>
			<c:forEach var="genero" items="${generos}">
		        <tr>
		            <td><c:out value="${genero.id}" /></td>
		            <td><c:out value="${genero.nome}"/></td>
		            <td><a href="generoServlet?action=editar&id" class="Botao3">Editar</a>		
		            	<a href="generoServlet?acao=deletar&id=${genero.id}" class="Botao4" method="post">Deletar</a></td>
		        </tr>
			</c:forEach>
		</table>
		<br>
		<a href="generoEdit.jsp" class="Botao2">Add Novo</a>
	</body>
</html>