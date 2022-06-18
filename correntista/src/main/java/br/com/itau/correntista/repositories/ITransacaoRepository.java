package br.com.itau.correntista.repositories;

import br.com.itau.correntista.models.Transacao;

public interface ITransacaoRepository {
	
	public Double buscaSaldoCorrentista(Long idCorrentista);
	
	public Integer gravaTransacao(Transacao transacao);

}
