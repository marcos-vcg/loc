package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Filme;

public class FilmeDAO {
	private DataSource dataSource;
	private String tabela;
	private GeneroDao generoDao;
	private CategoriaDAO categoriaDao;
	
	public FilmeDAO(DataSource datasource){
		this.dataSource = datasource;
		this.tabela = "filme";
		generoDao = new GeneroDao(dataSource);
		categoriaDao = new CategoriaDAO(dataSource);
	}
	
	
	public Filme busca(Integer id){
		try {
			String SQL = "SELECT * FROM " + tabela + " WHERE id = '" + id + "';";
			java.sql.PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			
			Filme filme = new Filme();
			
			while(rs.next()) {
				
				filme.setId(rs.getInt("id"));
				filme.setTitulo(rs.getString("titulo"));
				filme.setGenero(generoDao.select(rs.getInt("genero")));
				filme.setCopias(rs.getInt("copias"));
				filme.setSinopse(rs.getString("sinopse"));
				filme.setDuracao(rs.getString("duracao"));
				filme.setLancamento(rs.getString("lancamento"));
				filme.setImagem(rs.getBytes("imagem"));
				filme.setCategoria(categoriaDao.busca(rs.getInt("categoria")));
				System.out.println("Filme lido");
				
			}
			ps.close();
			return filme;
			
		} catch(SQLException ex) {
			System.err.println("Erro ao Recuperar filme " + ex.getMessage());
		} catch(Exception ex) {
			System.err.println("Erro Geral " + ex.getMessage());
		}
		return null;
	}
	
	
	public ArrayList<Filme> readAll(){
		try {
			String SQL = "SELECT * FROM " + tabela + "  ORDER BY titulo;";
			java.sql.PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			
			ArrayList<Filme> lista = new ArrayList<Filme>();
			
			while(rs.next()) {
				Filme filme = new Filme();
				filme.setId(rs.getInt("id"));
				filme.setTitulo(rs.getString("titulo"));
				filme.setGenero(generoDao.select(rs.getInt("genero")));
				filme.setCopias(rs.getInt("copias"));
				filme.setSinopse(rs.getString("sinopse"));
				filme.setDuracao(rs.getString("duracao"));
				filme.setLancamento(rs.getString("lancamento"));
				filme.setImagem(rs.getBytes("imagem"));
				filme.setCategoria(categoriaDao.busca(rs.getInt("categoria")));
				System.out.println("Filme lido");
				
				if(filme.getId() != null ) {
					lista.add(filme);
				}
				
			}
			ps.close();
			return lista;
			
		} catch(SQLException ex) {
			System.err.println("Erro ao Recuperar filme " + ex.getMessage());
		} catch(Exception ex) {
			System.err.println("Erro Geral " + ex.getMessage());
		}
		return null;
	}
	
	
	
	public Boolean nserir(Filme f) {
		Boolean retorno = false;
		try {
			
			String SQL = "INSERT INTO " + tabela + " VALUES (DEFAULT,  '" + f.getTitulo() + "', '" + f.getGenero().getId() + "', '" + f.getCopias() + "', '" + f.getSinopse() + "', '" + f.getDuracao() + "', '" + f.getLancamento() + "', '" + f.getImagem() + "', '" + f.getCategoria().getId() + "');";
			java.sql.PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);

			ps.executeUpdate(SQL);						// Usado para fazer qualquer altera��o. N�o tem nenhum retorno
			ps.close();
			retorno = true;
			
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return retorno;
	}
	
	
	
	public Boolean inserir(Filme f) {
		
		Boolean retorno = false;
		
		try {
			
			String SQL = "INSERT INTO filme (titulo, genero, copias, sinopse, duracao, lancamento, imagem , categoria) VALUES (?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
			
			//ps.setString(1, tabela);
			ps.setString(1, f.getTitulo());
			ps.setInt(2, f.getGenero().getId());
			ps.setInt(3, f.getCopias());
			ps.setString(4, f.getSinopse());
			ps.setString(5, f.getDuracao());
			ps.setString(6, f.getLancamento());
			ps.setBytes(7, f.getImagem());
			ps.setInt(8, f.getCategoria().getId());
			
			ps.executeUpdate();						// Usado para fazer qualquer altera��o. N�o tem nenhum retorno
			ps.close();
			
			retorno = true;
			
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return retorno;
	}
	
	
	public void editar(Filme f) {
		try {
			
			String SQL = "UPDATE " + tabela + " SET titulo = '" + f.getTitulo() + "', genero = '" + f.getGenero().getId() + "', copias = '" + f.getCopias() + "', sinopse = '" + f.getSinopse() + "', duracao = '" + f.getDuracao() + "', lancamento = '" + f.getLancamento() + "', imagem = '" + f.getImagem() + "', categoria = '" + f.getCategoria().getId() + "' WHERE id = " + f.getId() + ";" ;			// id � int, n�o colocar aspassimples
			java.sql.PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
			ps.executeUpdate(SQL);
			ps.close();
			
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	public void apagar(Integer id) {
		try {
			
			String SQL = "DELETE FROM " + tabela + " WHERE id = " + id + ";" ;			// id � int, n�o colocar aspassimples
			java.sql.PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
			ps.executeUpdate(SQL);												// Usado para fazer qualquer altera��o. N�o tem nenhum retorno
			ps.close();
			
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	
	
	
}
