package br.com.itau.correntista.repositories;

import java.sql.SQLException;

import br.com.itau.correntista.models.Correntista;

public interface ICorrentistaRepository {
	
	public void gravarCorrentista(Correntista correntista) throws SQLException;
	
	public void atualizarCorrentista(Correntista correntista) throws SQLException;
	
	public boolean excluirCorrentista(Long id) throws SQLException;
	
	public Correntista consultaPorId(Long id) throws SQLException;
	
	public Correntista consultaPorContaSenha(Integer conta, Integer senha) throws SQLException;

}
