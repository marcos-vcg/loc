<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="utf-8">
		<title>Cadastro de Genero</title>
		<link rel="icon" href="imagens/favicon.png">
		<link rel="stylesheet" href="style.css">
	</head>
	<body>
		<h1>Novo Genero</h1>
		<form action="generoServlet" method="post">
		  	<input type="hidden" name="id" id="id" value="0" >
		  	<label for="nome">Nome:</label>
		  	<input type="text" id="nome" name="nome"><br><br>
			<input type="hidden" name="acao" id="acao" value="inserir" >
		  	<input type="submit" value="Gravar">
		</form>
	</body>
</html>