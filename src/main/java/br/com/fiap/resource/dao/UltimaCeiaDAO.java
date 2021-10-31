package br.com.fiap.resource.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.resource.to.UltimaCeiaTO;

public class UltimaCeiaDAO {

	public UltimaCeiaDAO() {
	}

	public List<UltimaCeiaTO> listarTodos() {
		try {
			Connection con = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM MENU";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<UltimaCeiaTO> listaCeia = new ArrayList<UltimaCeiaTO>();
			while (rs.next()) {
				UltimaCeiaTO obj = new UltimaCeiaTO(rs.getInt("ID_ULTIMACEIA"),
						rs.getString("NOME_PRATO"),
						rs.getString("RECEITA"), rs.getInt("KCAL"), 
						rs.getInt("VOTO"));
				listaCeia.add(obj);
			}
			ps.close();
			return listaCeia;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void inserir(UltimaCeiaTO UltimaCeiaTO) {
		try {
			Connection con = ConnectionFactory.getConnection();
			String sql = "INSERT INTO MENU VALUES (sq_menu.nextval,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, UltimaCeiaTO.getNomePrato());
			ps.setString(2, UltimaCeiaTO.getReceita());
			ps.setInt(3, UltimaCeiaTO.getKcal());
			ps.setInt(4, 0);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.getCause();
		}
	}

	public UltimaCeiaTO listarPorId(int id) {
		try {
			Connection con = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM MENU WHERE ID_ULTIMACEIA = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				UltimaCeiaTO uct = new UltimaCeiaTO(rs.getInt("ID_ULTIMACEIA"), rs.getString("NOME_PRATO"),
						rs.getString("RECEITA"), rs.getInt("KCAL"), rs.getInt("VOTO"));
				return uct;
				
			}
			ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void editar(UltimaCeiaTO uct) {
		try {
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement ps = con.prepareStatement(constroeSQLUpdate());
			ps.setString(1, uct.getNomePrato());
			ps.setString(2, uct.getReceita());
			ps.setInt(3, uct.getKcal());
			ps.setInt(4, uct.getId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void votar(int id) {
		try {
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement ps = con.prepareStatement(constroeSQLVota());
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<UltimaCeiaTO> buscarOrdemVotacao() {
		try {
			Connection con = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM MENU ORDER BY VOTO DESC";
			PreparedStatement ps = con.prepareStatement(sql);
			List<UltimaCeiaTO> maisVotados = new ArrayList<UltimaCeiaTO>();
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				UltimaCeiaTO uct = new UltimaCeiaTO(rs.getInt("ID_ULTIMACEIA"), 
						rs.getString("NOME_PRATO"), rs.getString("RECEITA"), 
						rs.getInt("KCAL"), rs.getInt("VOTO"));
				maisVotados.add(uct);
				
			}
			ps.close();
			return maisVotados;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	private String constroeSQLVota() {
		var sql = new StringBuilder();
		sql.append("UPDATE MENU ")
		.append("SET ")
		.append("VOTO = VOTO + 1 ")
		.append("WHERE ID_ULTIMACEIA = ? ");
		return sql.toString();
	}

	private String constroeSQLUpdate() {
		var sql = new StringBuilder();
		sql.append("UPDATE ")
		.append("MENU ")
		.append("SET ")
		.append("NOME_PRATO=?, ")
		.append("RECEITA=?, ")		
		.append("KCAL=? ")
		.append("WHERE ID_ULTIMACEIA = ? ");
		return sql.toString();
	}

}
