<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="style.css">
		<meta charset="utf-8">
		<title>Lista de Clientes</title>
		<link rel="icon" href="imagens/favicon.png">
	</head>

	<body>
		<header>
			<nav class="navbar navbar-expand-md navbar-dark" style="background-color: blue">
				<a href="http://localhost:8080/loc" class="navbar-brand"> Locadora Digital</a>
				<ul class="navbar-nav">
					<li><a href="<%=request.getContextPath()%>/genero" class="nav-link">Generos</a></li>
					<li><a href="<%=request.getContextPath()%>/categoria" class="nav-link">Categorias</a></li>
					<li><a href="<%=request.getContextPath()%>/filme" class="nav-link">Filmes</a></li>
					<li><a href="<%=request.getContextPath()%>/cliente" class="nav-link">Clientes</a></li>
					<li><a href="<%=request.getContextPath()%>/locacao" class="nav-link">Locações</a></li>
				</ul>				
			</nav>
		</header>
		
		<br>

		<div class="row">
			<div class="container">	
				<h2>Locações</h2>
				
				<h3 class="text-center">Lista de Clientes</h3>
				<hr>
				
				<div class="navbar">	
					<a href="<%=request.getContextPath()%>/cliente?action=new" class="btn btn-success">Novo Cliente</a>
					<form class="form-inline" action="locacao">
						<input class="form-control" type="text" name="txtBusca">
						<input type="submit" value="search" class="btn btn-outline-success" name="action">
					</form>
				</div>	
				
				<br>

				<table class="table table-bordered" style="text-align:center;">
					<thead>
						<tr>
							<th>Id</th>
							<th>Nome</th>
							<th>CPF</th>
							<th>Ações</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach var="cliente" items="${clientes}">
							<tr>
								<td><c:out value="${cliente.id}" /></td>
								<td><c:out value="${cliente.nome}"/></td>
								<td><c:out value="${cliente.cpf}"/></td>
								<td>
									<a href="locacao?action=select&id=<c:out value='${cliente.id}'/>" class="botao-exclui" class="btn btn-warning" type="">
										<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
										  	<path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z"/>
										  	<path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z"/>
										</svg>
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>