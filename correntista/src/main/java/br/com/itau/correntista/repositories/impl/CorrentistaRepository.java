package br.com.itau.correntista.repositories.impl;

import java.sql.SQLException;

import br.com.itau.correntista.dao.ConexaoDAO;
import br.com.itau.correntista.dao.CorrentistaDAO;
import br.com.itau.correntista.models.Correntista;
import br.com.itau.correntista.repositories.ICorrentistaRepository;

public class CorrentistaRepository implements ICorrentistaRepository {
	private CorrentistaDAO dao;
	
	@Override
	public Correntista consultaPorId(Long id) throws SQLException {
		dao = new CorrentistaDAO(ConexaoDAO.getConnection());
		Correntista correntista = dao.consultaPorId(id);
		return correntista;
	}

	@Override
	public void gravarCorrentista(Correntista correntista) throws SQLException {
		dao = new CorrentistaDAO(ConexaoDAO.getConnection());
		dao.adiciona(correntista);
		
	}

	@Override
	public void atualizarCorrentista(Correntista correntista) throws SQLException {
		// TODO Auto-generated method stub
		
	}	

}
