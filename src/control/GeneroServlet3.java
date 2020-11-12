package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Genero;
import dao.DataSource;
import dao.GeneroDao;

/**
 * Servlet implementation class GeneroServlet2
 */
@WebServlet(name="GeneroServlet3", urlPatterns = {"/generoServlet"})
public class GeneroServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource dataSource;
	private GeneroDao generoDao;   
	
	
    /**
     * @see Servlet#init(ServletConfig)
     */
    public void init() throws ServletException {
    	dataSource = new DataSource();
    	generoDao = new GeneroDao(dataSource);
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
		Genero genero = new Genero();
		genero.setNome(nome);
		generoDao.insert(genero);
		response.sendRedirect("generoServlet");
	}	
	
	// Delete
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			generoDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("generoServlet");
	}	

	// Página de Edição
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int id = Integer.parseInt(request.getParameter("id"));
		Genero genero;
		try {
			// busca o genero a ser editado e repassa para a pagina de edição
			genero = generoDao.select(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("genero-form.jsp");
			request.setAttribute("genero", genero);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//response.sendRedirect("generoServlet/list");
	}	
	
	// Update
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");	
		Genero genero = new Genero();
		genero.setId(id);
		genero.setNome(nome);
		generoDao.update(genero);
		response.sendRedirect("generoServlet");
	}	
	
	// Default
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {
			// Preenche os elementos da Lista Padrão
			List<Genero> generos = generoDao.selectAll();
			request.setAttribute("generos", generos);
			RequestDispatcher dispatcher = request.getRequestDispatcher("genero-list.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	

	
	
}
