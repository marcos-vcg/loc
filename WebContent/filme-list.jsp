<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
	
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="style.css">
		<meta charset="utf-8">
		<title>Lista de Filmes</title>
		<link rel="icon" href="imagens/favicon.png">
		
	</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="http://localhost:8080/loc" class="navbar-brand"> Locadora Digital</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/generoServlet" class="nav-link">Generos</a></li>
			</ul>
			
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/categoria" class="nav-link">Categorias</a></li>
			</ul>
			
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/filme" class="nav-link">Filmes</a></li>
			</ul>
			
		</nav>
	</header>
	<br>

	<div class="row">
		
		<div class="container">
			<h3 class="text-center">Lista de Filmes</h3>
			<hr>
			<div class="container text-left">
				
				<a href="<%=request.getContextPath()%>/filme?action=new" class="btn btn-success">Novo Filme</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Id</th>
						<th>Título</th>
						<th>Genero</th>
						<th>Duração</th>
						<th>Lançamento</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="filme" items="${filmes}">

						<tr>
							<td><c:out value="${filme.id}" /></td>
				            <td><c:out value="${filme.titulo}"/></td>
				            <td><c:out value="${filme.genero.nome}"/></td>
				            <td><c:out value="${filme.duracao}"/></td>
				            <td><c:out value="${filme.lancamento}"/></td>
							
							<td>
								<a href="filme?action=edit&id=<c:out value='${filme.id}' />" class="botao-editar" class="btn btn-info">Editar</a>
									&nbsp;&nbsp;&nbsp; 
								<a href="filme?action=delete&id=<c:out value='${filme.id}' />" class="botao-excluir" class="btn btn-warning" type="">Deletar</a>
								<form method="post" action="filme">
									<input type="hidden" name="id" value="<c:out value='${filme.id}' />" />
								</form> 
							</td>
						</tr>
					</c:forEach>
					
				</tbody>

			</table>
		</div>
	</div>
	
</body>
</html>