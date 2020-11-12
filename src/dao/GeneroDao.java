package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Genero;

public class GeneroDao {
	private DataSource datasource;
	private String tabela;
	
	public GeneroDao(DataSource datasource){
		this.datasource = datasource;
		this.tabela = "genero";
	}
	
	
	// Select Genero By Id
	public Genero select(int id){
		Genero genero = null;
		
		try {
			String SQL = "SELECT * FROM " + tabela + " WHERE id = ?";
			java.sql.PreparedStatement ps = datasource.getConnection().prepareStatement(SQL);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				genero = new Genero();
				genero.setId(id);
				genero.setNome(rs.getString("nome"));
			}
			
			ps.close();
			
		} catch(SQLException ex) {
			System.err.println("Erro ao Recuperar genero " + ex.getMessage());
		} catch(Exception ex) {
			System.err.println("Erro Geral " + ex.getMessage());
		}
		return genero;
	}
	
	
	// Select All Generos
	public List<Genero> selectAll(){
		
		List<Genero> generos = new ArrayList<Genero>();
		
		try {
			String SQL = "SELECT * FROM " + tabela + " ORDER BY nome";
			
			// Create Statement using connection
			java.sql.PreparedStatement ps = datasource.getConnection().prepareStatement(SQL);
			
			// Execute the query or update query
			ResultSet rs = ps.executeQuery();
			
			// Process the ResultSet Objects
			while(rs.next()) {
				Genero gen = new Genero();
				gen.setId(rs.getInt("id"));
				gen.setNome(rs.getString("nome"));
		
				generos.add(gen);
			}
			ps.close();
			
		} catch(SQLException ex) {
			System.err.println("Erro ao Recuperar " + ex.getMessage());
		} catch(Exception ex) {
			System.err.println("Erro Geral " + ex.getMessage());
		}
		return generos;
	}
	
	
	// Insert Genero passed
	public void insert(Genero genero) {
		try {
			String SQL = "INSERT INTO " + tabela + " (nome) VALUES (?);";
			java.sql.PreparedStatement ps = datasource.getConnection().prepareStatement(SQL);
			ps.setString(1, genero.getNome());
			ps.executeUpdate();						// Usado para fazer qualquer alteração. Não tem nenhum retorno
			ps.close();
			
		} catch (SQLException e) {
			printSQLException(e);				//Funcao criada para tratar Exceções SQL
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			
		}
	}
	
	
	// Update Genero
	public boolean update(Genero genero) {
		
		boolean rowUpdated = false;
		
		try {
			
			String SQL = "UPDATE " + tabela + " SET nome = ? WHERE id = ? ;" ;			// id é int, não colocar aspassimples
			java.sql.PreparedStatement ps = datasource.getConnection().prepareStatement(SQL);
			
			ps.setString(1, genero.getNome());
			ps.setInt(2, genero.getId());
			
			rowUpdated = ps.executeUpdate() > 0;
			ps.close();
			
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return rowUpdated;
	}
	
	
	// Delete Genero
	public boolean delete(int id) {
		
		boolean rowDeleted = false;
		
		try {
			
			String SQL = "DELETE FROM " + tabela + " WHERE id = ?;" ;			
			java.sql.PreparedStatement ps = datasource.getConnection().prepareStatement(SQL);
			
			ps.setInt(1, id);
			
			//ps.executeUpdate()							// Usado para fazer qualquer alteração. Não tem nenhum retorno
			rowDeleted = ps.executeUpdate() > 0;			// Retorno Indica se obteve sucesso								
			ps.close();
			
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return rowDeleted;
	}
	
	
	// Trata os erros de todas as excessões das chamadas SQL
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while(t != null) {
					System.out.println("Cause: " + t);
					t.getCause();
				}
			}
		}
	}



}
