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

import bean.Cliente;
import bean.Dependente;
import dao.DataSource;
import dao.ClienteDao;
import dao.DependenteDao;

/**
 * Servlet implementation class ClienteServlet
 */
@WebServlet(name="ClienteServlet", urlPatterns = {"/cliente"})
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource dataSource;
	private ClienteDao clienteDao;  
	private DependenteDao dependenteDao;
	
	
    /**
     * @see Servlet#init(ServletConfig)
     */
    public void init() throws ServletException {
    	dataSource = new DataSource();
    	clienteDao = new ClienteDao(dataSource);
    	dependenteDao = new DependenteDao(dataSource);
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
					
				default: 
					list(request, response);
					break;
			}
		} else {
			list(request, response);
		}
		
	}

	
	// Redirect to New Page
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("cliente-form.jsp");
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

	
	// Página de Edição
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int id = Integer.parseInt(request.getParameter("id"));
		Cliente cliente;
		ArrayList<Dependente> dependentes;
		try {
			// busca o genero a ser editado e repassa para a pagina de edição
			cliente = clienteDao.select(id);
			dependentes = dependenteDao.selectAllOf(id);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("cliente-form.jsp");
			request.setAttribute("cliente", cliente);
			request.setAttribute("dependentes", dependentes);
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
		response.sendRedirect("cliente");
	}	
	
	
	// Default
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {
			// Preenche os elementos da Lista Padrão
			List<Cliente> clientes = clienteDao.selectAll();
			request.setAttribute("clientes", clientes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("cliente-list.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	

	
	
}
