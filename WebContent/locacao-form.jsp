<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
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
				<h2>Locações de <c:out value="${cliente.nome}"/></h2>
				
				<h3 class="text-center">Lista de Locações</h3>
				<hr>
				
				<div class="navbar">	
								<!-- Botão para acionar modal -->
					<a href="" class="btn btn-success" type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalExemplo"  >Nova Locação</a>
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
									<a id="clicavel" href="locacao?action=update&id=<c:out value='${locacao.id}'/>" class="botao-exclui" class="btn btn-warning" type="" onclick="return confirm('Devolver Filme?')">
										<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-check2-square" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
										  <path fill-rule="evenodd" d="M15.354 2.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3-3a.5.5 0 1 1 .708-.708L8 9.293l6.646-6.647a.5.5 0 0 1 .708 0z"/>
										  <path fill-rule="evenodd" d="M1.5 13A1.5 1.5 0 0 0 3 14.5h10a1.5 1.5 0 0 0 1.5-1.5V8a.5.5 0 0 0-1 0v5a.5.5 0 0 1-.5.5H3a.5.5 0 0 1-.5-.5V3a.5.5 0 0 1 .5-.5h8a.5.5 0 0 0 0-1H3A1.5 1.5 0 0 0 1.5 3v10z"/>
										</svg>
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
					<!-- Início do Modal --> 
									
						<div class="modal fade" id="modalExemplo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  	<div class="modal-dialog modal-lg" role="document">
						    	<div class="modal-content">
						      		<div class="modal-header">
						        		<h5 class="modal-title" id="exampleModalLabel">Lista de Filmes</h5>
						        		<button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
						          			<span aria-hidden="true">&times;</span>
						        		</button>
						      		</div>
						      		
						      		<div class="modal-body">
		        						<table class="table table-bordered" style="text-align:center;" id="tblLocacoes">
											<thead>
												<tr>
													<th>Id</th>
													<th>Filme</th>
													<th>Gênero</th>
													<th>Ações</th>
												</tr>
											</thead>
											
											<tbody>
												<c:forEach var="filme" items="${filmes}">
													<tr>
														<td><c:out value="${filme.id}" /></td>
														<td><c:out value="${filme.titulo}"/></td>
														<td><c:out value="${filme.genero.nome}"/></td>
														<td>
															<a id="clicavel" href="locacao?action=insert&idCliente=<c:out value='${cliente.id}'/>&idFilme=<c:out value='${filme.id}'/>" class="botao-exclui" class="btn btn-warning" type="" onclick="return confirm('Alugar Filme?')">
																<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-plus-square" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
																  <path fill-rule="evenodd" d="M14 1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
																  <path fill-rule="evenodd" d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
																</svg>
															</a>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
						      		</div>
						      		
						      		<div class="modal-footer">
								        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
								        <button type="button" class="btn btn-primary">Salvar mudanças</button>
						      		</div>
						    	</div>
						  	</div>
						</div>
				
				<!-- Término do Modal --> 
				
			</div>
		</div>
	
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	</body>
</html>