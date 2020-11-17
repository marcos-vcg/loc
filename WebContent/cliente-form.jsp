<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Formulario de Cliente</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="http://localhost:8080/loc/" class="navbar-brand"> Locadora Digital </a>
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
	<div class="container col-md-10">
		<div class="card">
			<div class="card-body">
				
				<c:if test="${cliente != null}">
					<caption>
						<h2>Editar Cliente</h2>
					</caption>
				
					<form action="cliente" method="post">
						<input type="hidden" name="action" value="update" />
						<input type="hidden" name="id" value="<c:out value='${cliente.id}' />" />
				</c:if>
				
				
				<c:if test="${cliente == null}">
					<caption>
						<h2>Novo Cliente</h2>
					</caption>
				
					<form action="cliente" method="post">
						<input type="hidden" name="action" value="insert" />
				</c:if>

					<fieldset class="form-group">
						<label>Nome*</label> 
						<input name="nome" type="text" maxlength="40" placeholder="MeuNome..." value="<c:out value='${cliente.nome}' />" class="form-control" required="required">
					</fieldset>
					
					<fieldset class="form-group">
						<label>CPF*</label> 
						<input name="cpf" type="text" maxlength="15" placeholder="XXX.XXX.XXX-XX" value="<c:out value='${cliente.cpf}' />" class="form-control" required="required">
					</fieldset>
					
					<fieldset class="form-group">
						<label>Telefone*</label> 
						<input name="telefone" type="tel" maxlength="15" placeholder="(XX)XXXXX-XXXX" pattern="\([0-9]{2}\) [0-9]{4,6}-[0-9]{3,4}$" value="<c:out value='${cliente.telefone}' />" class="form-control" required="required">
					</fieldset>
					
					<fieldset class="form-group">
						<label>Nascimento</label> 
						<input name="email" type="date" maxlength="15"  value="<c:out value='${cliente.telefone}' />" class="form-control" required="required">
					</fieldset>
					
					<fieldset class="form-group">
						<label>Endereço</label> 
						<input name="email" type="text" maxlength="40" placeholder="Rua tal, núm 1234" pattern="" value="<c:out value='${cliente.telefone}' />" class="form-control" required="required">
					</fieldset>
					
					
					<fieldset class="form-group">
						<label>Email</label> 
						<input name="email" type="email" maxlength="20" placeholder="exemplo@host.com" pattern="" value="<c:out value='${cliente.telefone}' />" class="form-control" required="required">
					</fieldset>
					
					
					
					<div class="container col-md-10">
						
						<div class="card">
						
							<div class="card-body">
							
								<form action="dependente" method="post">
									<legend>Dependentes</legend>
									
									<fieldset class="form-group">
										<label>Dependente*</label> 
										<input name="nome" type="text" maxlength="40" placeholder="MeuNome..." value="<c:out value='${cliente.nome}' />" class="form-control" required="required">
									</fieldset>
									
									<fieldset class="form-group">
										<label>Parentesco*</label> 
											<select name="categoria" class="form-control" required="required">
													<option value="" selected disabled hidden="hidden">  --  Selecione --  </option>
												<c:forEach var="categ" items="${categorias}">
													<option value="<c:out value='${categ.id}' />" <c:if test="${filme.categoria.id == categ.id}">selected</c:if> > <c:out value="${categ.nome}" /></option>
												</c:forEach>
											</select>	
										
									</fieldset>
									<div class="container text-right">
										<button type="submit" class="btn btn-success">Save</button>	
									</div>
								</form>
								
								
								<div class="row">
						
									<div class="container">
										<h5 class="text-center">Lista de Dependentes</h3>
										
										<table class="table table-bordered" style="text-align:center;">
											<thead>
												<tr>
													<th>Id</th>
													<th>Nome</th>
													<th>Grau</th>
													<th>Ações</th>
												</tr>
											</thead>
											<tbody>
											
												<c:forEach var="dependente" items="${dependentes}">
							
													<tr>
														<td><c:out value="${dependente.id}" /></td>
											            <td><c:out value="${dependente.nome}"/></td>
											            <td><c:out value="${dependente.grau}"/></td>					
														
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
								
								
								
								
								
								
								
							</div>
						</div>
					</div>
									
					
					
					
					
					
					
					
					
					
			
					
					<fieldset class="form-group">
						
						<label for="imagem">Imagem</label>
						<br>
	  					<input type="file" id="imagem" name="imagem" accept="image/*" onchange="">
	  					<br>
	  					<img id="img" alt="Foto de Capa" src="<c:out value='${filme.imagem}' />" style="width: 200px" />
					</fieldset>
					
					<fieldset class="form-group">
						<label for="site-search">Search the site:</label>
						<input type="search" id="site-search" name="q" aria-label="Search through site content">
						<button>Search</button>
					</fieldset>
					
					<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>