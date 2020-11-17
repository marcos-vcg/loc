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
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="http://localhost:8080/loc" class="navbar-brand"> Locadora Digital</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/genero" class="nav-link">Generos</a></li>
			</ul>
			
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/categoria" class="nav-link">Categorias</a></li>
			</ul>
			
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/filme" class="nav-link">Filmes</a></li>
			</ul>
			
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/cliente" class="nav-link">Clientes</a></li>
			</ul>
			
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/dependente" class="nav-link">Dependentes</a></li>
			</ul>
			
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/locacao" class="nav-link">Locações</a></li>
			</ul>
			
		</nav>
	</header>
	<br>

	<div class="row">
		
		<div class="container">
			<h3 class="text-center">Lista de Clientes</h3>
			<hr>
			<div class="container text-left">
				
				<a href="<%=request.getContextPath()%>/cliente?action=new" class="btn btn-success">Novo Cliente</a>
			</div>
			<br>
			<table class="table table-bordered" style="text-align:center;">
				<thead>
					<tr>
						<th>Id</th>
						<th>Nome</th>
						<th>CPF</th>
						<th>Email</th>
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="cliente" items="${clientes}">

						<tr>
							<td><c:out value="${cliente.id}" /></td>
				            <td><c:out value="${cliente.nome}"/></td>
				            <td><c:out value="${cliente.cpf}"/></td>
				            <td><c:out value="${cliente.email}"/></td>
	
							
							<td>
									&nbsp;
								<a href="cliente?action=edit&id=<c:out value='${cliente.id}' />" class="editar" class="btn btn-info" >
									<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil-square" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  									<path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
  									<path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/></svg>
								</a>
									&nbsp;&nbsp;
								<a href="cliente?action=delete&id=<c:out value='${cliente.id}'/>" class="botao-exclui" class="btn btn-warning" type="" onclick="return confirm('Quer mesmo excluir?')" >
									<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  									<path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
  									<path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/></svg>
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