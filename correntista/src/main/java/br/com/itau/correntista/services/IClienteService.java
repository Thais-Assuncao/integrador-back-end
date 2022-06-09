package br.com.itau.correntista.services;

import java.util.List;

import br.com.itau.correntista.exceptions.BusinessException;
import br.com.itau.correntista.models.Cliente;

public interface IClienteService {
	
	public void gravar();
	
	public List<Cliente> listarClientes() throws BusinessException;
	
	public void gravarEmMemoria(Cliente cliente) throws BusinessException;
	
	public List<Cliente> listarDaMemoria() throws BusinessException;

}
