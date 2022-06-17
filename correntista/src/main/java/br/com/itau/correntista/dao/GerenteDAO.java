package br.com.itau.correntista.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.itau.correntista.models.Correntista;
import br.com.itau.correntista.models.Gerente;

public class GerenteDAO {
	private Connection conexao;

	public GerenteDAO(Connection conexao) {
		super();
		this.conexao = conexao;
	}
	
	public  Gerente consultaPorEmail(String email) throws SQLException {

		
		try (PreparedStatement ps = this.conexao
				.prepareStatement("select email  FROM correntista WHERE email = ?")) {
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return new Gerente(rs.getString("email"), rs.getString("senha"),rs.getLong("id"));
			}
			ConexaoDAO.closeConnection();
			return null;
		} catch (SQLException e) {
			System.err.println("Erro ao buscar o usuário "+ email + " --> "+ e.getMessage());
			if(conexao != null) {
				conexao.close();
			}
		}
		return null;
	}
}
