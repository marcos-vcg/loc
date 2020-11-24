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
		<script type="text/javascript" src="js/locacao.js" DEFER="DEFER"></script>
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
				
				<h3 class="text-center">Lista de Locações</h3>
				<hr>
				
				<div class="navbar">	
					<a href="<%=request.getContextPath()%>/locacao?action=new" class="btn btn-success">Nova Locação</a>
					<form class="form-inline" action="locacao">
						<input class="form-control" type="text" name="txtBusca">
						<input type="submit" value="search" class="btn btn-outline-success" name="action">
					</form>
				</div>	
				
				<br>

				<table class="table table-bordered" style="text-align:center;" id="tblLocacoes">
					<thead>
						<tr>
							<th>Id</th>
							<th>Filme</th>
							<th>Locação</th>
							<th>Devolução</th>
							<th>Situação</th>
							<th>Ações</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach var="locacao" items="${locacoes}">
							<tr>
								<td><c:out value="${locacao.id}" /></td>
								<td><c:out value="${locacao.filme.titulo}"/></td>
								<td><c:out value="${locacao.locacao}"/></td>
								<td><c:out value="${locacao.devolucao}"/></td>
								<td id="<c:out value="${locacao.id}" />" onChange="situacao(this)"></td>
								<td>
									<a href="locacao?action=edit&id=<c:out value='${locacao.id}'/>" class="botao-exclui" class="btn btn-warning" type="">
										<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-arrow-clockwise" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
											<path fill-rule="evenodd" d="M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2v1z"/>
											<path d="M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466z"/>
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
	<script type="text/javascript">
		// Acessa a tabela:
		var minhaTabela = document.getElementById('tblLocacoes');
	
		// Acessa o primeiro tbody da tabela:
		var tBody = minhaTabela.tBodies[0];
	
		// Acessa cada linha da tabela:
		for (i = 0; i < tBody.rows.length; i++) {
	
		    // Define o valor célula situação (cells[4]) de cada linha (rows[i]) da tabela:
		    tBody.rows[i].cells[4].innerHTML = situacao;
		}
	</script>
</html>