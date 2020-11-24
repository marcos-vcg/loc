<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
	
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="style.css">
		<meta charset="utf-8">
		<title>Lista de Categorias</title>
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
				<h3 class="text-center">Lista de Categorias</h3>
				<hr>
				<div class="container text-left">
	
					<a href="<%=request.getContextPath()%>/categoria-form.jsp" class="btn btn-success">Nova Categoria</a>
				</div>
				<br>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Id</th>
							<th>Categoria</th>
							<th>Preço</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
					
						<c:forEach var="categoria" items="${categorias}">
	
							<tr>
								<td><c:out value="${categoria.id}" /></td>
					            <td><c:out value="${categoria.nome}"/></td>
					            <td><c:out value="${categoria.preco}"/></td>
								
								<td>
									<a href="categoria?action=edit&id=<c:out value='${categoria.id}' />" class="btn btn-warning"><img alt="Imagem não encontrada" src="imagens/edit.png"></a>
										&nbsp;&nbsp;&nbsp; 
									<a href="categoria?action=delete&id=<c:out value='${categoria.id}' />" class="btn btn-danger" type=""><img alt="Imagem não encontrada" src="imagens/delete.png"></a>
									<form method="post" action="categoria">
										<input type="hidden" name="id" value="<c:out value='${categoria.id}' />" />
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