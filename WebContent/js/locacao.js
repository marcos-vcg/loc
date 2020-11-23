
// Funcao preenche o campo situacao dependendo sa situacao de entrega do filme
function situacao(td){
	//Pega elemento Pai duas vezes para pegar a linha atual
	var selectedRow = td.parentElement.parentElement;
    
	var prazo = 7;
	var locacao = new Date(selectedRow.cells[2].innerHTML);
	var devolucao = new Date(selectedRow.cells[3].innerHTML);
	var hoje = new Date();
	var diff = Math.abs(hoje.getTime() - locacao.getTime());
	var diasPassados = Math.ceil(diff / (1000 * 60 * 60 * 24));
	var situacao = "No Prazo";
	
	
	if(devolucao != ""){
		situacao = "Devolvido";
	} else if (diasPassados > prazo){
		situacao = "Atrasado";
	} else {
		situacao = "No Prazo";
	}
	
	
	// Seta o campo situação da linha atual
 	selectedRow.cells[4].innerHTML = situacao;

	//Pega os elementos da linha da tabela a serem editados e preenche os campos
	document.getElementById("idDep").value = selectedRow.cells[4].innerHTML;
}