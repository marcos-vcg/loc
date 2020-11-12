package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Categoria;

public class CategoriaDAO {
	private DataSource datasource;
	private String tabela;
	
	public CategoriaDAO(DataSource datasource){
		this.datasource = datasource;
		this.tabela = "categoria";
	}
	
	public Categoria busca(int id) {
		try {
			String SQL = "SELECT * FROM " + tabela + " WHERE id = '" + id + "'";
			java.sql.PreparedStatement ps = datasource.getConnection().prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			
			Categoria categoria = new Categoria();
			while(rs.next()) {
				categoria.setId(rs.getInt("id"));
				categoria.setNome(rs.getString("nome"));
				categoria.setPreco(rs.getString("preco"));
			}
			
			ps.close();
			return categoria;
			
		} catch(SQLException ex) {
			System.err.println("Erro ao Recuperar categoria " + ex.getMessage());
		} catch(Exception ex) {
			System.err.println("Erro Geral " + ex.getMessage());
		}
		return null;
	}
	
	public ArrayList<Categoria> readAll(){
		try {
			String SQL = "SELECT * FROM " + tabela + " ORDER BY nome";
			java.sql.PreparedStatement ps = datasource.getConnection().prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			
			ArrayList<Categoria> lista = new ArrayList<Categoria>();
			
			while(rs.next()) {
				Categoria categ = new Categoria();
				categ.setId(rs.getInt("id"));
				categ.setNome(rs.getString("nome"));
				categ.setPreco(rs.getString("preco"));
		
				lista.add(categ);
			}
			ps.close();
			return lista;
			
		} catch(SQLException ex) {
			System.err.println("Erro ao Recuperar " + ex.getMessage());
		} catch(Exception ex) {
			System.err.println("Erro Geral " + ex.getMessage());
		}
		return null;
	}
	
	public void inserir(String nome, String preco) {
		try {
			
			String SQL = "INSERT INTO " + tabela + " (nome, preco) VALUES ('" + nome + "', '" + preco + "');";
			java.sql.PreparedStatement ps = datasource.getConnection().prepareStatement(SQL);
			ps.executeUpdate(SQL);						// Usado para fazer qualquer altera��o. N�o tem nenhum retorno
			ps.close();
			
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	public void editar(Integer id, String nome, String preco) {
		try {
			
			String SQL = "UPDATE " + tabela + " SET nome = '" + nome + "', preco = '" + preco + "' WHERE id = " + id + ";" ;			// id � int, n�o colocar aspassimples
			java.sql.PreparedStatement ps = datasource.getConnection().prepareStatement(SQL);
			ps.executeUpdate(SQL);
			ps.close();
			
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	public void apagar(Integer id) {
		try {
			
			String SQL = "DELETE FROM " + tabela + " WHERE id = " + id + ";" ;			// id � int, n�o colocar aspassimples
			java.sql.PreparedStatement ps = datasource.getConnection().prepareStatement(SQL);
			ps.executeUpdate(SQL);												// Usado para fazer qualquer altera��o. N�o tem nenhum retorno
			ps.close();
			
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
}
