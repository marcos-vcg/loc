// Acessa a tabela:
var minhaTabela = document.getElementById('tblLocacoes');

// Acessa o primeiro tbody da tabela:
var tBody = minhaTabela.tBodies[0];

// Acessa cada linha da tabela:
for (i = 0; i < tBody.rows.length; i++) {

	// Pega o campo de Devolução para testar se já foi devolvido
	var devolucao = tBody.rows[i].cells[3].innerHTML;

	// Pega a data de Hoje e a data de locação
	var hoje = new Date();
	var locacao = new Date(tBody.rows[i].cells[2].innerHTML);

	// Passa para milissegundos e subtrai as duas arredondando para baixo para saber os dias completos passados
	var diff = Math.abs(hoje.getTime() - locacao.getTime());
	var diasPassados = Math.floor(diff / (1000 * 60 * 60 * 24)); 

	// Define o prazo de 7 dias
	var prazo = 7;

	// Situação recebe o valor para passar para a tabela via javascript
	var situacao;
	
	// Define a situação de acordo com a devolução ou dias passados
	if(devolucao != ""){
		situacao = "Devolvido";
	} else if (diasPassados > prazo){
		situacao = "Atrasado";
	} else {
		situacao = "No Prazo";
	}
	
	
    // Define o valor célula situação (cells[4]) de cada linha (rows[i]) da tabela:
    tBody.rows[i].cells[4].innerHTML = situacao;
}






/*
// Funcao preenche o campo situacao dependendo sa situacao de entrega do filme
function situaca(td){
	//Pega elemento Pai duas vezes para pegar a linha atual
	var selectedRow = td.parentElement.parentElement;
	
	// Pega o campo de Devolução para testar se já foi devolvido
	var devolucao = selectedRow.cells[3].innerHTML;

	// Pega a data de Hoje e a data de locação
	var hoje = new Date();
	var locacao = new Date(selectedRow.cells[2].innerHTML);

	// Passa para milissegundos e subtrai as duas arredondando para baixo para saber os dias completos passados
	var diff = Math.abs(hoje.getTime() - locacao.getTime());
	var diasPassados = Math.floor(diff / (1000 * 60 * 60 * 24)); 

	// Define o prazo de 7 dias
	var prazo = 7;

	// Situação recebe o valor para passar para a tabela via javascript
	var situacao;
	
	// Define a situação de acordo com a devolução ou dias passados
	if(devolucao != ""){
		situacao = "Devolvido";
	} else if (diasPassados > prazo){
		situacao = "Atrasado";
	} else {
		situacao = "No Prazo";
	}
	
	// Seta o campo situação da linha atual
 	selectedRow.cells[4].innerHTML = situacao;
	
	return situacao;
}
*/
