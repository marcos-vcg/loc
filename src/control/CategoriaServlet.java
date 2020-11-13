package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Categoria;
import dao.DataSource;
import dao.CategoriaDao;

/**
 * Servlet implementation class GeneroServlet2
 */
@WebServlet(name="CategoriaServlet", urlPatterns = {"/categoria"})
public class CategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource dataSource;
	private CategoriaDao categoriaDao;   
	
	
    /**
     * @see Servlet#init(ServletConfig)
     */
    public void init() throws ServletException {
    	dataSource = new DataSource();
    	categoriaDao = new CategoriaDao(dataSource);
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
			
				case ("insert"):
					// Insere Genero
					insert(request, response);
					break;
					
				case ("delete"):
					// Delete Genero
					delete(request, response);
					break;
				
				case ("edit"):
					// Encaminha para a página de Edição de Gênero
					showEditForm(request, response);
					break;
				
				case ("update"):
					// Update Genero
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


	
	// Insert
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String nome = request.getParameter("nome");	
		String preco = request.getParameter("preco");
		Categoria categoria = new Categoria();
		categoria.setNome(nome);
		categoria.setPreco(preco);
		categoriaDao.insert(categoria);
		response.sendRedirect("categoria");
	}	
	
	// Delete
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			categoriaDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("categoria");
	}	

	// Página de Edição
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int id = Integer.parseInt(request.getParameter("id"));
		Categoria categoria;
		try {
			// busca o genero a ser editado e repassa para a pagina de edição
			categoria = categoriaDao.select(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("categoria-form.jsp");
			request.setAttribute("categoria", categoria);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	// Update
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");	
		String preco = request.getParameter("preco");	
		Categoria categoria = new Categoria();
		categoria.setId(id);
		categoria.setNome(nome);
		categoria.setPreco(preco);
		categoriaDao.update(categoria);
		response.sendRedirect("categoria");
	}	
	
	// Default
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {
			// Preenche os elementos da Lista Padrão
			List<Categoria> categorias = categoriaDao.selectAll();
			request.setAttribute("categorias", categorias);
			RequestDispatcher dispatcher = request.getRequestDispatcher("categoria-list.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	

	
	
}
