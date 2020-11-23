package control;

import java.io.IOException;
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
			
				case ("new"):
					showNewForm(request, response);
					break;
			
				case ("insert"):
					insert(request, response);
					break;
					
				case ("delete"):
					delete(request, response);
					break;
				
				case ("edit"):
					showEditForm(request, response);
					break;
				
				case ("update"):
					update(request, response);
					break;
					
				case ("select"):
					select(request, response);
					break;
					
				case ("search"):
					search(request, response);
					break;

				default: 
					list(request, response);
					break;
			}
		} else {
			list(request, response);
		}
		
	}

	
	// Redirect to New Page
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
		
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String telefone = request.getParameter("telefone");
		String email = request.getParameter("email");
		String nascimento = request.getParameter("nascimento");
		String endereco = request.getParameter("endereco");
		//byte[] imagem = request.getParameter("imagem");
		
			
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setTelefone(telefone);
		cliente.setEmail(email);
		cliente.setNascimento(nascimento);
		cliente.setEndereco(endereco);
		//cliente.setImagem(imagem);
		
		clienteDao.insert(cliente);
		
		
		// Apos Inserir Cliente Insere seus Dependentes	
		try {
			//Recebe o JSON em uma String e a armazena em um Array JSON
			String jsonString = request.getParameter("dependentes");
			JSONArray jsonArray = new JSONArray(jsonString);
				//JSONArray jsonArray = (JSONArray) new JSONParser(request.getParameter("dependentes")).parse();

			
			// Percorre o Array pegando os Objetos um a um.
			for(Object obj: jsonArray) {	
				
				// Pega um dos Objetos JSON
				JSONObject jsonObect = (JSONObject) obj;
				
				// Pega os valores dos seus campos
				String jNome = jsonObect.get("nomeDep").toString();
				String jGrau = jsonObect.get("grauDep").toString();
				
				// Cria um Objeto JAVA seta seus atributos e Insere no BD
				Dependente dependente = new Dependente();
				dependente.setNome(jNome);
				dependente.setGrau(Grau.valueOf(jGrau));
				dependente.setTitular(clienteDao.select(clienteDao.selectLast()));
				
				//dependenteDao.selectAllOf();
				dependenteDao.inserir(dependente);	
				System.out.println("Dependente Inserido no BD");
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		response.sendRedirect("cliente");
	}	
	
	
	// Delete
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			clienteDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("cliente");
	}	

	
	// Pagina de Edicao
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
		
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String telefone = request.getParameter("telefone");
		String email = request.getParameter("email");
		String nascimento = request.getParameter("nascimento");
		String endereco = request.getParameter("endereco");
		//byte[] imagem = request.getParameter("imagem");
		
		
		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setTelefone(telefone);
		cliente.setEmail(email);
		cliente.setNascimento(nascimento);
		cliente.setEndereco(endereco);
		//cliente.setImagem(imagem);
		
		clienteDao.update(cliente);
		
		// Apos Atualizar Cliente Atualiza seus Dependentes	
		try {
			//Recebe o JSON em uma String e a armazena em um Array JSON
			String jsonString = request.getParameter("dependentes");
			JSONArray jsonArray = new JSONArray(jsonString);
			
			// Percorre o Array pegando os Objetos um a um.
			for(Object obj: jsonArray) {	
				
				// Pega um dos Objetos JSON
				JSONObject jsonObect = (JSONObject) obj;
				
				// Cria um Objeto JAVA seta seus atributos para alterar no BD
				Dependente dependente = new Dependente();
				
				String jNome = jsonObect.get("nomeDep").toString();
				dependente.setNome(jNome);
				
				String jGrau = jsonObect.get("grauDep").toString();
				dependente.setGrau(Grau.valueOf(jGrau));
				
				String jAcao = jsonObect.get("acaoDep").toString();
				dependente.setTitular(cliente);			
				
				
				switch(jAcao) {
					case ("inserir"):
						dependenteDao.inserir(dependente);
						break;
						
					case ("apagar"):
						dependente.setId(Integer.parseInt(jsonObect.get("idDep").toString()));
						dependenteDao.apagar(dependente.getId());
						break;
						
					case ("editar"):
						dependente.setId(Integer.parseInt(jsonObect.get("idDep").toString()));
						dependenteDao.editar(dependente);
						break;
				}
			System.out.println("Dependente Modificado no BD");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		response.sendRedirect("cliente");
	}	
	

	// Search - Filtra a Lista
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("locacao-form.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	// Default
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
