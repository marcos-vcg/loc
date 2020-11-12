package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Genero;
import dao.DataSource;
import dao.GeneroDao;

/**
 * Servlet implementation class GeneroServlet
 */
@WebServlet(urlPatterns = {"/generoServle"})
public class GeneroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeneroServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("UTF-8");
		
		DataSource dataSource = new DataSource();
		GeneroDao generoDao = new GeneroDao(dataSource);
		
		// Le todos os Generos
		List<Genero> generos = generoDao.selectAll();			
		request.setAttribute("generos", generos);
		request.getRequestDispatcher("/WEB-INF/paginas/generos.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		request.setCharacterEncoding("UTF-8");
		
		DataSource dataSource = new DataSource();
		GeneroDao generoDao = new GeneroDao(dataSource);
				
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");	
		String acao = request.getParameter("acao");
		
		// Cria um novo Genero
		Genero genero = new Genero();
		genero.setId(id);
		genero.setNome(nome);
		
		
		switch(acao) {
			case("inserir"):
				// Adiciona o Genero ao BD
				generoDao.insert(genero);
				break;
				
			case("deletar"):
				// Exclui o Genero
				//generoDao.delete(genero);
				//request.setAttribute("genero", genero);
				//request.getRequestDispatcher("generoView.jsp").forward(request, response);
			
			case("editar"):
				// Edita o Genero
				generoDao.update(genero);
				//request.setAttribute("genero", genero);
				//request.getRequestDispatcher("generoView.jsp").forward(request, response);
				break;
				
			default: 
				break;
		}
		
		
		// Le todos os Generos
		List<Genero> generos = generoDao.selectAll();			
		request.setAttribute("generos", generos);
		request.getRequestDispatcher("WEB-INF/paginas/generos.jsp").forward(request, response);

	}
		
		
	

}
