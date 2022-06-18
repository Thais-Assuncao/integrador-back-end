package br.com.itau.correntista.repositories;

import java.sql.SQLException;

import br.com.itau.correntista.models.Gerente;

public interface IGerenteRepository {
	public Gerente consultaPorEmail(String email) throws SQLException;
}
