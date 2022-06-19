package br.com.itau.correntista.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.itau.correntista.models.Correntista;

public class CorrentistaDAO {

	private Connection conexao;

	public CorrentistaDAO(Connection conexao) {
		this.conexao = conexao;
	}


	public void adiciona(Correntista correntista) {
		String sqlInsereCorrentista = "insert into correntista(ag, conta, nome, email, telefone, saldo, endereco, "
				+ "cep, bairro, cidade, uf, senha) value (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try(PreparedStatement ps = this.conexao
				.prepareStatement(sqlInsereCorrentista)){
			ps.setInt(1, correntista.getAg());
			ps.setInt(2, correntista.getConta());
			ps.setString(3, correntista.getNome());
			ps.setString(4, correntista.getEmail());
			ps.setString(5, correntista.getTelefone());
			ps.setDouble(6, correntista.getSaldo());
			ps.setString(7, correntista.getEndereco());
			ps.setString(8, correntista.getCep());
			ps.setString(9, correntista.getBairro());
			ps.setString(10, correntista.getCidade());
			ps.setString(11, correntista.getUf());
			ps.setString(12, correntista.getSenha());
			ps.execute();
			ps.close();

		} catch (SQLException e) {
			System.err.println("Erro ao adicionar o curso --> "+e.getMessage());
		}
	}


	public int remover(Long id) {
		String sql = "delete from correntista where id = ?";
		int row = 0;
		try (PreparedStatement ps = this.conexao.prepareStatement(sql)) {
			ps.setLong(1, id);
			row = ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Erro ao remover o curso de id "+ id + " --> "+ e.getMessage());
		} 
		return row;
	}

	public int atualiza(Correntista correntista) {
		String sql = "UPDATE correntista SET ag = ?, conta = ?, nome = ?, email = ?,  telefone = ?, saldo = ?, "
				+ "endereco = ?, cep = ?, bairro = ?,  cidade = ?, uf = ? WHERE id = ?";
		try (PreparedStatement ps = this.conexao.prepareStatement(sql)) {
			ps.setInt(1, correntista.getAg());
			ps.setInt(2, correntista.getConta());
			ps.setString(3, correntista.getNome());
			ps.setString(4, correntista.getEmail());
			ps.setString(5, correntista.getTelefone());
			ps.setDouble(6, correntista.getSaldo());
			ps.setString(7, correntista.getEndereco());
			ps.setString(8, correntista.getCep());
			ps.setString(9, correntista.getBairro());
			ps.setString(10, correntista.getCidade());
			ps.setString(11, correntista.getUf());
			ps.setLong(12, correntista.getId());
			return ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Erro ao atualizar o correntista de id "+ correntista.getId() + " --> "+ e.getMessage());
		}
		return 0;
	}

	public Correntista consultaPorAgenciaConta(Integer agencia, Integer conta) throws SQLException {
		if(conta == null || conta == null) {
			return null;
		}

		try (PreparedStatement ps = this.conexao
				.prepareStatement("select id, ag, conta, nome, email, telefone, saldo, endereco, cep, bairro, cidade, uf, senha  FROM correntista WHERE ag = ? and conta = ?")) {
			ps.setInt(1, agencia);
			ps.setInt(2, conta);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return new Correntista(rs.getLong("id"), 
						rs.getInt("ag"), 
						rs.getInt("conta"), 
						rs.getString("nome"), 
						rs.getString("email"), 
						rs.getString("telefone"), 
						rs.getDouble("saldo"),
						rs.getString("endereco"),
						rs.getString("cep"),
						rs.getString("bairro"),
						rs.getString("cidade"),
						rs.getString("uf"),
						rs.getString("senha")
						);
			}
			ConexaoDAO.closeConnection();
			return null;
		} catch (SQLException e) {
			System.err.println("Erro ao buscar o curso de "+ conta + " --> "+ e.getMessage());
			if(conexao != null) {
				conexao.close();
			}
		}
		return null;
	}

	public Correntista consultaPorId(Long id) throws SQLException {
		if(id <= 0) {
			return null;
		}

		try (PreparedStatement ps = this.conexao
				.prepareStatement("select id, ag, conta, nome, email, telefone, saldo, endereco, cep, bairro, cidade, uf  FROM correntista WHERE id= ?")) {
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return new Correntista(rs.getLong("id"), 
						rs.getInt("ag"), 
						rs.getInt("conta"), 
						rs.getString("nome"), 
						rs.getString("email"), 
						rs.getString("telefone"), 
						rs.getDouble("saldo"),
						rs.getString("endereco"),
						rs.getString("cep"),
						rs.getString("bairro"),
						rs.getString("cidade"),
						rs.getString("uf")
						);
			}
			ConexaoDAO.closeConnection();
			return null;
		} catch (SQLException e) {
			System.err.println("Erro ao buscar o curso de id "+ id + " --> "+ e.getMessage());
			if(conexao != null) {
				conexao.close();
			}
		}
		return null;
	}
	
	public List<Correntista> listaTodos() {

		try (PreparedStatement ps = this.conexao
				.prepareStatement("select id, ag, conta, nome, email, telefone, saldo, endereco, cep, bairro, cidade, uf  FROM correntista")) {
			List<Correntista> listaCorrentistas = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listaCorrentistas.add(new Correntista(rs.getLong("id"), 
						rs.getInt("ag"), 
						rs.getInt("conta"), 
						rs.getString("nome"), 
						rs.getString("email"), 
						rs.getString("telefone"), 
						rs.getDouble("saldo"),
						rs.getString("endereco"),
						rs.getString("cep"),
						rs.getString("bairro"),
						rs.getString("cidade"),
						rs.getString("uf")
						));
			}
			return listaCorrentistas;
		} catch (Exception e1) {
			System.err.print("Erro ao listar todos os correntistas --> " + e1.getMessage());
		}
		return null;
	}

}
