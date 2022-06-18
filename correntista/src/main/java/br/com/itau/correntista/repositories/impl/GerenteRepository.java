package br.com.itau.correntista.repositories.impl;

import java.sql.SQLException;

import br.com.itau.correntista.dao.ConexaoDAO;
import br.com.itau.correntista.dao.GerenteDAO;
import br.com.itau.correntista.models.Gerente;
import br.com.itau.correntista.repositories.IGerenteRepository;

public class GerenteRepository implements IGerenteRepository {
	private GerenteDAO dao;
	@Override
	public Gerente consultaPorEmail(String email) throws SQLException {
		dao = new GerenteDAO(ConexaoDAO.getConnection());
		return dao.consultaPorEmail(email);
	}

}
