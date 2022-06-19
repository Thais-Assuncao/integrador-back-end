package br.com.itau.correntista.repositories;

import java.sql.SQLException;
import java.util.List;

import br.com.itau.correntista.models.Correntista;

public interface ICorrentistaRepository {
	
	public void gravarCorrentista(Correntista correntista) throws SQLException;
	
	public void atualizarCorrentista(Correntista correntista) throws SQLException;
	
	public boolean excluirCorrentista(Long id) throws SQLException;
	
	public Correntista consultaPorId(Long id) throws SQLException;
	
	public Correntista consultaPorAgenciaConta(Integer agencia, Integer conta) throws SQLException;
	
	List<Correntista> listaTodosCorrentistas() throws SQLException;

}
