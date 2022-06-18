package br.com.itau.correntista.repositories.impl;

import br.com.itau.correntista.dao.ConexaoDAO;
import br.com.itau.correntista.dao.TransacaoDAO;
import br.com.itau.correntista.models.Transacao;
import br.com.itau.correntista.repositories.ITransacaoRepository;

public class TransacaoRepository implements ITransacaoRepository {

	private TransacaoDAO dao;
	
	@Override
	public Double buscaSaldoCorrentista(Long idCorrentista) {
		dao = new TransacaoDAO(ConexaoDAO.getConnection());
		return dao.getUltimoRegistroCorrentista(idCorrentista);
	}

	@Override
	public Integer gravaTransacao(Transacao transacao) {
		dao = new TransacaoDAO(ConexaoDAO.getConnection());
		 return dao.adiciona(transacao);
	}

}
