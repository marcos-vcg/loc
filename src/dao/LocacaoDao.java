package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import bean.Locacao;


public class LocacaoDao {
	private DataSource datasource;
	private ClienteDao clienteDao; 
	private FilmeDao filmeDao;
	private String tabela;
	
	public LocacaoDao(DataSource datasource){
		this.datasource = datasource;
		this.clienteDao = new ClienteDao(datasource);
		this.filmeDao = new FilmeDao(datasource);
		this.tabela = "locacao";
	}
	
	public Locacao select(int id) {
		try {
			String SQL = "SELECT * FROM " + tabela + " WHERE id = ?";
			java.sql.PreparedStatement ps = datasource.getConnection().prepareStatement(SQL);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			Locacao locacao = new Locacao();
			
			while(rs.next()) {
				
				locacao.setId(rs.getInt("id"));
				locacao.setCliente(clienteDao.select(rs.getInt("cliente")));
				locacao.setFilme(filmeDao.select(rs.getInt("filme")));
				locacao.setLocacao(rs.getDate("aluguel"));
				locacao.setDevolucao(rs.getDate("devolucao"));
		
			}
			
			ps.close();
			return locacao;
			
		} catch(SQLException ex) {
			System.err.println("Erro ao Recuperar locacao: " + ex.getMessage());
		} catch(Exception ex) {
			System.err.println("Erro Geral LocacaoDAO: " + ex.getMessage());
		}
		return null;
	}
	
	public ArrayList<Locacao> filmesDoCLiente(int id){
		try {
			String SQL = "SELECT * FROM " + tabela + " WHERE cliente = ? ORDER BY aluguel";
			java.sql.PreparedStatement ps = datasource.getConnection().prepareStatement(SQL);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			ArrayList<Locacao> lista = new ArrayList<Locacao>();
			
			while(rs.next()) {
				Locacao locacao = new Locacao();
				locacao.setId(rs.getInt("id"));
				locacao.setCliente(clienteDao.select(rs.getInt("cliente")));
				locacao.setFilme(filmeDao.select(rs.getInt("filme")));
				locacao.setLocacao(rs.getDate("aluguel"));
				locacao.setDevolucao(rs.getDate("devolucao"));
		
				lista.add(locacao);
			}
			ps.close();
			return lista;
			
		} catch(SQLException ex) {
			System.err.println("Erro ao Recuperar Locacao: " + ex.getMessage());
		} catch(Exception ex) {
			System.err.println("Erro Geral LocacaoDAO" + ex.getMessage());
		}
		return null;
	}
	
	public ArrayList<Locacao> selectAll(){
		try {
			String SQL = "SELECT * FROM " + tabela + " ORDER BY aluguel";
			java.sql.PreparedStatement ps = datasource.getConnection().prepareStatement(SQL);
			
			ResultSet rs = ps.executeQuery();
			
			ArrayList<Locacao> lista = new ArrayList<Locacao>();
			
			while(rs.next()) {
				Locacao locacao = new Locacao();
				locacao.setId(rs.getInt("id"));
				locacao.setCliente(clienteDao.select(rs.getInt("cliente")));
				locacao.setFilme(filmeDao.select(rs.getInt("filme")));
				locacao.setLocacao(rs.getDate("aluguel"));
				locacao.setDevolucao(rs.getDate("devolucao"));
		
				lista.add(locacao);
			}
			ps.close();
			return lista;
			
		} catch(SQLException ex) {
			System.err.println("Erro ao Recuperar Locacao: " + ex.getMessage());
		} catch(Exception ex) {
			System.err.println("Erro Geral LocacaoDAO" + ex.getMessage());
		}
		return null;
	}
	
	
	public void insert(Locacao l) {
		try {
			
			String SQL = "INSERT INTO " + tabela + " (cliente, filme, aluguel) VALUES (?,?,?)";
			java.sql.PreparedStatement ps = datasource.getConnection().prepareStatement(SQL);
			
			ps.setInt(1, l.getCliente().getId());
			ps.setInt(2, l.getFilme().getId());
			ps.setDate(3, new Date(l.getLocacao().getTime()));
			
			ps.executeUpdate();						// Usado para fazer qualquer altera��o. N�o tem nenhum retorno
			ps.close();
			
		} catch (Exception e) {
			System.out.println("Erro Geral LocacaoDAO: " + e.getMessage());
		}
	}
	
	public int quantosAlugados(int id){
		try {
			
			String SQL = "SELECT COUNT(*) FROM " + tabela + " WHERE filme = ? AND devolucao = null ;";
			java.sql.PreparedStatement ps = datasource.getConnection().prepareStatement(SQL);
			
			ps.setInt(1, id);
			
			
			ResultSet rs = ps.executeQuery();
			
			int quantidade;
			
			while(rs.next()) {
				quantidade = rs.getInt(1);
				return quantidade;
			}
			ps.close();
			
		} catch(SQLException ex) {
			System.err.println("Erro ao Recuperar Locacao: " + ex.getMessage());
		} catch(Exception ex) {
			System.err.println("Erro Geral LocacaoDAO" + ex.getMessage());
		}
		return 0;
	}
	
	
	public void update(Locacao l) {
		try {
			
			String SQL = "UPDATE " + tabela + " SET devolucao = ? WHERE id = ?;" ;			// id � int, n�o colocar aspassimples
			java.sql.PreparedStatement ps = datasource.getConnection().prepareStatement(SQL);
			
			ps.setDate(1, new Date(l.getDevolucao().getTime()));
			ps.setInt(2, l.getId());
			
			ps.executeUpdate();
			ps.close();
			
		} catch (Exception e) {
			System.out.println("Erro Geral LocacaoDAO: " + e.getMessage());
		}
	}
	
	public void delete(Integer id) {
		try {
			
			String SQL = "DELETE FROM " + tabela + " WHERE id = ?;" ;			
			java.sql.PreparedStatement ps = datasource.getConnection().prepareStatement(SQL);
			
			ps.setInt(1, id);
			
			ps.executeUpdate();												// Usado para fazer qualquer altera��o. N�o tem nenhum retorno
			ps.close();
			
		} catch (Exception e) {
			System.out.println("Erro Geral LocacaoDAO: " + e.getMessage());
		}
	}
}
