package control;

import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import bean.Cliente;
import bean.Dependente;
import bean.Filme;
import bean.Grau;
import bean.Locacao;
import dao.DataSource;
import dao.ClienteDao;
import dao.DependenteDao;
import dao.FilmeDao;
import dao.LocacaoDao;


/**
 * Servlet implementation class LocacaoServlet
 */
@WebServlet(name="LocacaoServlet", urlPatterns = {"/locacao"})
public class LocacaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource dataSource;
	private ClienteDao clienteDao;  
	private DependenteDao dependenteDao;
	private FilmeDao filmeDao;
	private LocacaoDao locacaoDao;
	
	
    /**
     * @see Servlet#init(ServletConfig)
     */
    public void init() throws ServletException {
    	dataSource = new DataSource();
    	clienteDao = new ClienteDao(dataSource);
    	dependenteDao = new DependenteDao(dataSource);
    	filmeDao = new FilmeDao(dataSource);
    	locacaoDao = new LocacaoDao(dataSource);
    }

    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);	
	}
    

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		
		String action = request.getParameter("action");
		if(action != null) {
					
			switch(action) {
			
					// Funções Relacionadas a Locação em Si
			
				case ("new"):
					// Abrir o modal para selecionar filme
					showNewForm(request, response);
					break;
			
				case ("insert"):
					// Inserir locação do filme selecionado no modal
					insert(request, response);
					break;
					
				case ("update"):
					// Atualiza status para devolvido
					update(request, response);
					break;
				
					
			  /*case ("delete"):
					// Não tem exclusão, uma vez locado só pode devolver
					delete(request, response);
					break;
				
				case ("edit"):
					// Não tem edição, uma vez locado só pode devolver
					showEditForm(request, response);
					break;*/
				

					
					// Funções Relacionadas aos Clientes
					
				case ("select"):
					// Seleciona um Cliente e encaminha pra página de Locação dele.
					select(request, response);
					break;
					
				case ("search"):
					// Busca Clientes com nome da busca
					search(request, response);
					break;

				default: 
					// Mostra todos os cliente na página inicial de locação
					list(request, response);
					break;
			}
		} else {
			list(request, response);
		}
		
	}

	
	// Redirect to New Page => Nova locação é feita através de um MODAL
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		ArrayList<Grau> graus;
		
		try {
			graus = Grau.getGraus();
			RequestDispatcher dispatcher = request.getRequestDispatcher("cliente-form.jsp");
			request.setAttribute("graus", graus);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	// Insert
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int idCliente = Integer.parseInt(request.getParameter("idCliente"));
		int idFilme = Integer.parseInt(request.getParameter("idFilme"));
		
		
		Cliente cliente = clienteDao.select(idCliente);
		Locacao locacao = new Locacao();
		
		locacao.setCliente(cliente);
		locacao.setFilme(filmeDao.select(idFilme));
		locacao.setLocacao(new Date());
		
		List<Locacao> locacoes = locacaoDao.filmesDoCLiente(cliente.getId());
		long hoje = new Date().getTime();
		boolean atrasado = false;
		
		for(Locacao l: locacoes) {
			long loc = l.getLocacao().getTime();
			long diff = (hoje - loc)/(1000*60*60*24);
			
			if(diff > 7) {
				atrasado = true;
			} 	
		}
		
		if(!atrasado) {
			locacaoDao.inserir(locacao);
			System.out.println("Nova Locação Inserida!");
		} else {
			System.out.println("Locação não Inserida, Filmes em atraso!");
		}
		
			
		// Redireciona para a página de locações do mesmo cliente atualizando o filme devolvido
		List<Filme> filmes = filmeDao.selectAll();
		locacoes = locacaoDao.filmesDoCLiente(cliente.getId());
		
		request.setAttribute("cliente", cliente);
		request.setAttribute("locacoes", locacoes);
		request.setAttribute("filmes", filmes);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("locacao-form.jsp");
		dispatcher.forward(request, response);
	}	
	
	
	// Delete não está sendo usado pois não é possível Excluir uma locação
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			clienteDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("cliente");
	}	

	
	// Pagina de Edicao Não está sendo usado pois não é possível editar uma locacao
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int id = Integer.parseInt(request.getParameter("id"));
		Cliente cliente;
		ArrayList<Dependente> dependentes;
		ArrayList<Grau> graus;
		
		try {
			// busca o genero a ser editado e repassa para a pagina de ediï¿½ï¿½o
			cliente = clienteDao.select(id);
			dependentes = dependenteDao.selectAllOf(id);
			graus = Grau.getGraus();
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("cliente-form.jsp");
			request.setAttribute("cliente", cliente);
			request.setAttribute("dependentes", dependentes);
			request.setAttribute("graus", graus);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	
	// Update
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int id = Integer.parseInt(request.getParameter("id"));
		Locacao locacao = locacaoDao.busca(id);
		locacao.setDevolucao(new Date());
		locacaoDao.editar(locacao);
		
		System.out.println("Filme Devolvido!");
		
		// Redireciona para a página de locações do mesmo cliente atualizando o filme devolvido
		
		
		// Redireciona para a página de locações do mesmo cliente atualizando o filme devolvido
		Cliente cliente = locacao.getCliente();
		request.setAttribute("cliente", cliente);
		
		List<Locacao> locacoes = locacaoDao.filmesDoCLiente(cliente.getId());
		request.setAttribute("locacoes", locacoes);
		
		List<Filme> filmes = filmeDao.selectAll();
		request.setAttribute("filmes", filmes);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("locacao-form.jsp");
		dispatcher.forward(request, response);
	}	
	

	// Search - Filtra a Lista de Clientes
	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		// Recebe o parametro a ser buscado e concatena com o elemento % para pegar elementos parecidos e não exatamente iguais
		String busca = request.getParameter("txtBusca");
		
		try {
			// Preenche os elementos da Lista Filtrada
			List<Cliente> clientes = clienteDao.search(busca);
			request.setAttribute("clientes", clientes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("locacao-list.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	
	
	// Select 
	private void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		try {
			// Preenche os elementos da Lista Padrao
			int id = Integer.parseInt(request.getParameter("id"));
			
			List<Locacao> locacoes = locacaoDao.filmesDoCLiente(id);
			request.setAttribute("locacoes", locacoes);
			
			Cliente cliente = clienteDao.select(id);
			request.setAttribute("cliente", cliente);
			
			List<Filme> filmes = filmeDao.selectAll();
			request.setAttribute("filmes", filmes);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("locacao-form.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	// Default Página Inicial com todos os clientes
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {
			// Preenche os elementos da Lista Padrao
			List<Cliente> clientes = clienteDao.selectAll();
			request.setAttribute("clientes", clientes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("locacao-list.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	

	
	
}
