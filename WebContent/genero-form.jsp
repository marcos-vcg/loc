

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Formulario de Genero</title>
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
				<li><a href="<%=request.getContextPath()%>/generoServlet"
					class="nav-link">Generos</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				
				<c:if test="${genero != null}">
					<caption>
						<h2>Editar Genero</h2>
					</caption>
				
					<form action="generoServlet" method="post">
						<input type="hidden" name="action" value="update" />
						<input type="hidden" name="id" value="<c:out value='${genero.id}' />" />
				</c:if>
				
				
				<c:if test="${genero == null}">
					<caption>
						<h2>Novo Genero</h2>
					</caption>
				
					<form action="generoServlet" method="post">
						<input type="hidden" name="action" value="insert" />
				</c:if>

					<fieldset class="form-group">
						<label>Genero</label> <input type="text"
							value="<c:out value='${genero.nome}' />" class="form-control"
							name="nome" required="required">
					</fieldset>

					<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>