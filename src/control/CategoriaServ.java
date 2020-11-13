package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Categoria;
import bean.Genero;
import dao.CategoriaDao;
import dao.DataSource;
import dao.GeneroDao;

/**
 * Servlet implementation class CategoriaServlet
 */
@WebServlet("/categoriaServlet")
public class CategoriaServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriaServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("UTF-8");
		
		//int id = Integer.parseInt(request.getParameter("id"));
		//String nome = request.getParameter("nome");	
		
		DataSource dataSource = new DataSource();
		CategoriaDao categoriaDao = new CategoriaDao(dataSource);
				
		// Cria um novo Genero
		//Genero genero = new Genero();
		//genero.setId(id);
		//genero.setNome(nome);
		
		// Le todos os Generos
		ArrayList<Categoria> categorias = categoriaDao.selectAll();			
		request.setAttribute("categorias", categorias);
		request.getRequestDispatcher("/WEB-INF/paginas/categorias.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
