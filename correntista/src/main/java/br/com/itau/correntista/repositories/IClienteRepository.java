package br.com.itau.correntista.repositories;


import java.util.List;

import br.com.itau.correntista.models.Cliente;

public interface IClienteRepository {
	
	public void gravarCliente(Cliente cliente);
	
	public List<Cliente> listarClientes();
	
}
