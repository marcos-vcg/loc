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
@WebServlet(name="GeneroServlet2", urlPatterns = {"/generoServlet2"})
public class GeneroServlet2 extends HttpServlet {
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
			
		
		String action = request.getServletPath();

		
		switch(action) {
		
			case "/insert":
				// Insere o Genero ao BD
				insert(request, response);
				break;
				
			case "/generoServlet/delete":
				// Exclui o Genero
				delete(request, response);
				break;
			
			case "/edit":
				// Encaminha para a p�gina de Edi��o de G�nero
				showEditForm(request, response);
				break;
			
			case "/update":
				// Update Genero
				update(request, response);
				break;
				
			default: 
				list(request, response);
				break;
		}
		
		
	}


	

	
	// Insert
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String nome = request.getParameter("nome");	
		Genero genero = new Genero();
		genero.setNome(nome);
		generoDao.insert(genero);
		response.sendRedirect("generoServlet/list");
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

	// P�gina de Edi��o
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int id = Integer.parseInt(request.getParameter("id"));
		Genero genero;
		try {
			// busca o genero a ser editado e repassa para a pagina de edi��o
			genero = generoDao.select(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("genero-form.jsp");
			request.setAttribute("genero", genero);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("generoServlet/list");
	}	
	
	// Update
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");	
		Genero genero = new Genero();
		genero.setId(id);
		genero.setNome(nome);
		generoDao.update(genero);
		response.sendRedirect("generoServlet/list");
	}	
	
	// Default
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {
			// busca o genero a ser editado e repassa para a pagina de edi��o
			List<Genero> generos = generoDao.selectAll();
			request.setAttribute("generos", generos);
			RequestDispatcher dispatcher = request.getRequestDispatcher("genero-list.jsp");
			dispatcher.forward(request, response);
			//response.sendRedirect("genero-list.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			generoDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("generoServlet");
	}
	
}
