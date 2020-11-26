package control;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.Categoria;
import bean.Cliente;
import bean.Filme;
import bean.Genero;
import dao.CategoriaDao;
import dao.DataSource;
import dao.FilmeDao;
import dao.GeneroDao;

/**
 * Servlet implementation class FilmeServlet
 */

@MultipartConfig
@WebServlet(name="FilmeServlet", urlPatterns = {"/filme"})
public class FilmeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource dataSource;
	private FilmeDao filmeDao;  
	private GeneroDao generoDao;
	private CategoriaDao categoriaDao;
	
	
    /**
     * @see Servlet#init(ServletConfig)
     */
    public void init() throws ServletException {
    	dataSource = new DataSource();
    	filmeDao = new FilmeDao(dataSource);
    	generoDao = new GeneroDao(dataSource);
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

	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		new ArrayList<Genero>();
		try {
			// busca o genero a ser editado e repassa para a pagina de edição
			List<Genero> generos = generoDao.selectAll();
			List<Categoria> categorias = categoriaDao.selectAll();
			
			request.setAttribute("generos", generos);
			request.setAttribute("categorias", categorias);
			RequestDispatcher dispatcher = request.getRequestDispatcher("filme-form.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	// Insert
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String titulo = request.getParameter("titulo");	
		Genero genero = generoDao.select(Integer.parseInt(request.getParameter("genero")));
		Integer copias = Integer.parseInt(request.getParameter("copias"));
		String sinopse = request.getParameter("sinopse");
		String duracao = request.getParameter("duracao");
		String lancamento = request.getParameter("lancamento");
		Categoria categoria = categoriaDao.select(Integer.parseInt(request.getParameter("categoria")));
		
		Part filePart = request.getPart("imagem");
	    InputStream fileContent = filePart.getInputStream();
	    byte[] imagem = fileContent.readAllBytes();
		
		
		Filme filme = new Filme();
		filme.setTitulo(titulo);
		filme.setGenero(genero);
		filme.setCopias(copias);
		filme.setSinopse(sinopse);
		filme.setDuracao(duracao);
		filme.setLancamento(lancamento);
		filme.setImagem(imagem);
		filme.setCategoria(categoria);
		
		
		filmeDao.insert(filme);
		response.sendRedirect("filme");
	}	
	
	
	// Delete
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			filmeDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("filme");
	}	

	
	// Página de Edição
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int id = Integer.parseInt(request.getParameter("id"));
		Filme filme;
		try {
			// busca o genero a ser editado e repassa para a pagina de edição
			filme = filmeDao.select(id);
			
			List<Genero> generos = generoDao.selectAll();
			List<Categoria> categorias = categoriaDao.selectAll();
			request.setAttribute("generos", generos);
			request.setAttribute("categorias", categorias);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("filme-form.jsp");
			request.setAttribute("filme", filme);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	
	// Update
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int id = Integer.parseInt(request.getParameter("id"));
		String titulo = request.getParameter("titulo");	
		Genero genero = generoDao.select(Integer.parseInt(request.getParameter("genero")));
		Integer copias = Integer.parseInt(request.getParameter("copias"));
		String sinopse = request.getParameter("sinopse");
		String duracao = request.getParameter("duracao");
		String lancamento = request.getParameter("lancamento");
		Categoria categoria = categoriaDao.select(Integer.parseInt(request.getParameter("categoria")));
		
		Part filePart = request.getPart("imagem");
	    InputStream fileContent = filePart.getInputStream();
	    byte[] imagem = fileContent.readAllBytes();
		
		
		Filme filme = new Filme();
		filme.setId(id);
		filme.setTitulo(titulo);
		filme.setGenero(genero);
		filme.setCopias(copias);
		filme.setSinopse(sinopse);
		filme.setDuracao(duracao);
		filme.setLancamento(lancamento);
		filme.setImagem(imagem);
		filme.setCategoria(categoria);
		
		
		// Testa se foi enviado uma nova imagem, caso contrário seta a imagem com a mesma já cadastrada
		if(request.getPart("imagem").getSize()>0) {
			filme.setImagem(imagem);
		} else {
			filme.setImagem(filmeDao.select(filme.getId()).getImagem());
		}
		
		
		filmeDao.update(filme);
		response.sendRedirect("filme");
	}	
	

	// Search - Filtra a Lista
	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		// Recebe o parametro a ser buscado e concatena com o elemento % para pegar elementos parecidos e não exatamente iguais
		String busca = request.getParameter("txtBusca");
		
		try {
			// Preenche os elementos da Lista Filtrada
			List<Filme> filmes = filmeDao.search(busca);
			request.setAttribute("filmes", filmes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("filme-list.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	// Default
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {
			// Preenche os elementos da Lista Padrão
			List<Filme> filmes = filmeDao.selectAll();
			request.setAttribute("filmes", filmes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("filme-list.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	

	
	
}
