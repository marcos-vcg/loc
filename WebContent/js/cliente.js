window.onload = function() {
	//
};



// Linha selecionada para edição começa nula
var selectedRow = null;

//array para que os dependentes sejam armazenados
let dependentes = [ ];

// Array para enviar para a Servlet poder primeiro editar e excluir e por fim editar (Evitar adicionar um quarto dependente antes de excluir o anterior)
let dependentesInvertido = [ ];

// Inicializar o array vazio caso não cadastre ninguem.
var myJSON = JSON.stringify(dependentesInvertido);
document.getElementById("dependentes").value = myJSON;



function salvarDependente() {
    if (validar()) {
        var dependente = lerCampos();
        if (selectedRow == null){
			 // Captura a referência da tabela com id “minhaTabela”
			var table = document.getElementById("dependentesTable");
			// Captura a quantidade de linhas já existentes na tabela
			var numOfRows = table.rows.length;
			// Testa se o limite de dependentes ja foi atingido
			if(numOfRows > 3){
				alert("O número máximo de dependentes é 3!");
			} else{
				inserir(dependente);
			}
			
        } else {
            update(dependente); 
		}
	
	limpar();
    }
}

function lerCampos() {
    var dependente = {}; //Objeto com pares chave: valor
	
	dependente["idDep"] =  document.getElementById("idDep").value;
    dependente["nomeDep"] = document.getElementById("nomeDep").value;
    dependente["grauDep"] = document.getElementById("grauDep").value;

    return dependente;
}

function inserir(dependente) {
	
	dependente["acaoDep"] = "inserir";
    var table = document.getElementById("dependentesTable").getElementsByTagName('tbody')[0];
    var newRow = table.insertRow(0);
	//var newRow = table.insertRow(table.length); Insere linha no final da tabela
    cell1 = newRow.insertCell(0);
    cell1.innerHTML = dependente.idDep;
    cell2 = newRow.insertCell(1);
    cell2.innerHTML = dependente.nomeDep;
    cell3 = newRow.insertCell(2);
    cell3.innerHTML = dependente.grauDep;
    cell4 = newRow.insertCell(3);
    cell4.innerHTML = `<a id=clicavel onClick="editar(this)"><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil-square" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
					  									<path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
					  									<path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/></svg></a>
                        &nbsp;&nbsp;
						<a id=clicavel onClick="deletar(this)"><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
					  									<path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
					  									<path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/></svg></a>`;
	
	// Insere no array e atualiza o input hidden do form a ser enviado.
	//dependentes.push(dependente); INSERE NO FINAL DO ARRAY
	// INSERE NO INÍCIO DO ARRAY
	dependentes.unshift(dependente); 
	
	// Inverte para enviar ao Servlet ao contrário para primeiro fazer as alterações ou
	// exclusões nos dependentes já existentes e só por ultimo inserir os novos	evitando 
	// ficar com mais de 3 dependentes ao tentar inserir antes de ecluir.
	dependentesInvertido = dependentes.slice(0).reverse(); 
	
	var myJSON = JSON.stringify(dependentesInvertido);
	document.getElementById("dependentes").value = myJSON;
	
	console.log(dependentes);
	console.log(dependentesInvertido);
	
}

function limpar() {
	document.getElementById("idDep").value = "";
    document.getElementById("nomeDep").value = "";
    document.getElementById("grauDep").value = "";
    selectedRow = null;
	
}

function editar(td) {
    
	//Pega elemento Pai duas vezes para pegar a linha selecionada
	selectedRow = td.parentElement.parentElement;
    
	//Pega os elementos da linha da tabela a serem editados e preenche os campos
	document.getElementById("idDep").value = selectedRow.cells[0].innerHTML;
    document.getElementById("nomeDep").value = selectedRow.cells[1].innerHTML;
    document.getElementById("grauDep").value = selectedRow.cells[2].innerHTML;
}

function update(dep) {
	
	var linhaSelecionada = selectedRow;
	var indiceLinha = linhaSelecionada.rowIndex;
	
	//Pega os elementos dos campos e atualiza o cadastro do dependente na tabela
    selectedRow.cells[0].innerHTML = dep.idDep;
    selectedRow.cells[1].innerHTML = dep.nomeDep;
    selectedRow.cells[2].innerHTML = dep.grauDep;
	
	// Faz upgrade do elemento do array e atualiza o input hidden do form a ser enviado.
	//dependentes[selectedRow] = dependente;
	//document.getElementById("dependentes").value = JSON.stringfy(dependentes);
	
	// Caso o elemento não tenha um ID
		if(linhaSelecionada.cells[0].innerHTML == ""){
			// Pega o elemento do array -1 pois considera o cabeçalho da tabela como elemento
			var indiceArray = indiceLinha - 1;
			// Substitui o elemento do array
			dependentes.splice(indiceArray, 1, dependente);
		} else {
			
			// Caso o elemento possua um ID envia as informações a serem alteradas
			var dependent = {}; //Objeto com pares chave: valor
	
			dependent["idDep"] =  dep.idDep;
			dependent["nomeDep"] = dep.nomeDep;
   			dependent["grauDep"] = dep.grauDep;

		    dependent["acaoDep"] = "editar";
			dependentes.push(dependent);
		}
	
	// Inverte para enviar ao Servlet ao contrário para primeiro fazer as alterações ou
	// exclusões nos dependentes já existentes e só por ultimo inserir os novos	evitando 
	// ficar com mais de 3 dependentes ao tentar inserir antes de ecluir.
	dependentesInvertido = dependentes.slice(0).reverse(); 
	
	// Atualiza o input hidden do form a ser enviado.
	var myJSON = JSON.stringify(dependentesInvertido);
	document.getElementById("dependentes").value = myJSON;
	
	console.log(dependentes);
	console.log(dependentesInvertido);
}


function deletar(td) {
	
    if (confirm('Tem certeza que quer deletar?')) {
        var linhaSelecionada = td.parentElement.parentElement;
		var indiceLinha = linhaSelecionada.rowIndex;
		
        document.getElementById("dependentesTable").deleteRow(indiceLinha);
		
		// Caso o elemento não tenha um ID
		if(linhaSelecionada.cells[0].innerHTML == ""){
			// Deleta o elemento do array -1 pois considera o cabeçalho da tabela como elemento
			var indiceArray = indiceLinha - 1;
			dependentes.splice(indiceArray, 1);
			
		} else {
			
			var dependent = {}; //Objeto com pares chave: valor
	
			dependent["idDep"] =  linhaSelecionada.cells[0].innerHTML;
			dependent["nomeDep"] =  linhaSelecionada.cells[1].innerHTML;
			dependent["grauDep"] =  linhaSelecionada.cells[2].innerHTML;
		    dependent["acaoDep"] = "apagar";
			dependentes.push(dependent);
		}
		
		
		
		limpar();
    }

	// Inverte para enviar ao Servlet ao contrário para primeiro fazer as alterações ou
	// exclusões nos dependentes já existentes e só por ultimo inserir os novos	evitando 
	// ficar com mais de 3 dependentes ao tentar inserir antes de ecluir.
	dependentesInvertido = dependentes.slice(0).reverse(); 	
	
	// Atualiza o input hidden do form a ser enviado.
	var myJSON = JSON.stringify(dependentesInvertido);
	document.getElementById("dependentes").value = myJSON;
	
	console.log(dependentes);
	console.log(dependentesInvertido);
}


function validar() {
    isValid = true;
    if (document.getElementById("nomeDep").value == ""){
        isValid = false;
        document.getElementById("erroValidacaoDependenteNome").classList.remove("hide");
    } else if (document.getElementById("grauDep").value == "") {
		isValid = false;
        document.getElementById("erroValidacaoDependenteGrau").classList.remove("hide");
	}else {
        isValid = true;
        if (!document.getElementById("erroValidacaoDependenteNome").classList.contains("hide")){
            document.getElementById("erroValidacaoDependenteNome").classList.add("hide");
    	}
		if (!document.getElementById("erroValidacaoDependenteGrau").classList.contains("hide")){
            document.getElementById("erroValidacaoDependenteGrau").classList.add("hide");
    	}
	}
    return isValid;
}


