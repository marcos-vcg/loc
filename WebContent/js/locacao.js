
// Funcao preenche o campo situacao dependendo sa situacao de entrega do filme
function situacao(td){
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
}