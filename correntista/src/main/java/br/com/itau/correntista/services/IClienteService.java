package br.com.itau.correntista.services;

import java.util.List;

import br.com.itau.correntista.exceptions.AgenciaInvalidaException;
import br.com.itau.correntista.models.Cliente;

public interface IClienteService {
	
	public void gravar(Cliente cliente)  throws AgenciaInvalidaException;
	
	public List<Cliente> listarClientes();

}
