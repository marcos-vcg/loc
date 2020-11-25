package control;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;

import org.json.JSONArray;
import org.json.JSONObject;

import bean.Cliente;
import bean.Dependente;
import bean.Grau;
import dao.DataSource;
import dao.ClienteDao;
import dao.DependenteDao;
import dao.FilmeDao;


/**
 * Servlet implementation class ClienteServlet
 */
@MultipartConfig
@WebServlet(name="ImagemServlet", urlPatterns = {"/imagem"})
public class ImagemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource dataSource;
	private ClienteDao clienteDao;  
	private DependenteDao dependenteDao;
	private FilmeDao filmeDao;
	
	
    /**
     * @see Servlet#init(ServletConfig)
     */
    public void init() throws ServletException {
    	dataSource = new DataSource();
    	clienteDao = new ClienteDao(dataSource);
    	dependenteDao = new DependenteDao(dataSource);
    	filmeDao = new FilmeDao(dataSource);
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
	
		if(request.getParameter("id") != null) {
			
			Integer id = Integer.parseInt(request.getParameter("id"));
			String action = request.getParameter("action");
			
			switch(action) {
			
				case ("cliente"):	
					byte[] imagem = clienteDao.select(id).getImagem();
					response.setContentType("image/jpeg");
			        response.getOutputStream().write(imagem);  
					break;
			
				case ("filme"):
					byte[] image = filmeDao.select(id).getImagem();
					response.setContentType("image/jpeg");
			        response.getOutputStream().write(image);  
					break;

			}
		} 
		
	}

}