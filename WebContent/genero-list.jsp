


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
	
		<link rel="stylesheet"
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
		>
		
		<meta charset="utf-8">
		<title>Cadastro de Generos</title>
		<link rel="icon" href="imagens/favicon.png">
		
	</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="http://localhost:8080/loc" class="navbar-brand"> Locadora </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/generoServlet/list"
					class="nav-link">Generos List</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		
		<div class="container">
			<h3 class="text-center">Lista de Gêneros</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/genero-form.jsp" class="btn btn-success">Novo Genero</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Id</th>
						<th>Genero</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="genero" items="${generos}">

						<tr>
							<td><c:out value="${genero.id}" /></td>
				            <td><c:out value="${genero.nome}"/></td>
							
							<td><a href="generoServlet?action=edit&id=<c:out value='${genero.id}' />" class="Botao3">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="generoServlet?action=delete&id=<c:out value='${genero.id}' />" class="Botao4" type="">Delete</a>
								<form method="post" action="generoServlet">
									<input type="hidden" name="id" value="<c:out value='${genero.id}' />" />
								</form> </td>
						</tr>
					</c:forEach>
					
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>