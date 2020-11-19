




var selectedRow = null
var dependentes = [];


function initPage(){
	console.log("chamou a funcao");
	dependentes = elementos;
	dependentes.forEach(carregarTabela);
	console.log(dependentes);
	
	/*var elemento = {};
	for each(elemento in elementos){
		
		dependentes.push(elemento);
	}*/
	
}

function carregarTabela(dependente){
	var table = document.getElementById("dependentesTable").getElementsByTagName('tbody')[0];
    var newRow = table.insertRow(table.length);
    cell1 = newRow.insertCell(0);
    cell1.innerHTML = dependente.id;
    cell2 = newRow.insertCell(1);
    cell2.innerHTML = dependente.nome;
    cell3 = newRow.insertCell(2);
    cell3.innerHTML = dependente.grau;
    cell4 = newRow.insertCell(3);
    cell4.innerHTML = `<a onClick="editar(this)">Edit</a>
                       <a onClick="deletar(this)">Delete</a>`;
}


function onFormSubmit() {
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
    var table = document.getElementById("dependentesTable").getElementsByTagName('tbody')[0];
    var newRow = table.insertRow(table.length);
    cell1 = newRow.insertCell(0);
    cell1.innerHTML = dependente.idDep;
    cell2 = newRow.insertCell(1);
    cell2.innerHTML = dependente.nomeDep;
    cell3 = newRow.insertCell(2);
    cell3.innerHTML = dependente.grauDep;
    cell4 = newRow.insertCell(3);
    cell4.innerHTML = `<a onClick="editar(this)">Edit</a>
                       <a onClick="deletar(this)">Delete</a>`;
}

function limpar() {
	document.getElementById("idDep").value = "";
    document.getElementById("nomeDep").value = "";
    document.getElementById("grauDep").value = "";
    selectedRow = null;
}

function editar(td) {
    selectedRow = td.parentElement.parentElement;
    document.getElementById("idDep").value = selectedRow.cells[0].innerHTML;
    document.getElementById("nomeDep").value = selectedRow.cells[1].innerHTML;
    document.getElementById("grauDep").value = selectedRow.cells[2].innerHTML;
}

function update(dependente) {
    selectedRow.cells[0].innerHTML = dependente.idDep;
    selectedRow.cells[1].innerHTML = dependente.nomeDep;
    selectedRow.cells[2].innerHTML = dependente.grauDep;
}


function deletar(td) {
    if (confirm('Tem certeza que quer deletar?')) {
        row = td.parentElement.parentElement;
        document.getElementById("dependentesTable").deleteRow(row.rowIndex);
		clear();
    }
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

