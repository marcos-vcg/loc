<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="utf-8">
		<title>Cadastro de Categorias</title>
	</head>
	<body>
		<h1>Categorias Cadastradas</h1>
		<h4>Lista de Categorias</h4>
		
		<table border="1">
			<tr>
				<th>Id</th>
				<th>Categoria</th>
				<th>Preço</th>
				<th>Ações</th>
			</tr>
			<c:forEach var="categoria" items="${categorias}">
		        <tr>
		            <td><c:out value="${categoria.id}" /></td>
		            <td><c:out value="${categoria.nome}"/></td>
		            <td><c:out value="${categoria.preco}"/></td>
		            <td><a href="Servlet?action=editar&id" >Editar</a>
		            	<a href="Servlet?action=deletar&id" >Deletar</a></td>
		        </tr>
			</c:forEach>
		</table>
	</body>
</html>