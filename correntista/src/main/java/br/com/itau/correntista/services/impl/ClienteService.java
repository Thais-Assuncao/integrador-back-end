package br.com.itau.correntista.services.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.itau.correntista.exceptions.BusinessException;
import br.com.itau.correntista.models.Cliente;
import br.com.itau.correntista.repositories.impl.ClienteRepository;
import br.com.itau.correntista.services.IClienteService;

public class ClienteService implements IClienteService {
	private List<Cliente> listaClientes = new ArrayList<Cliente>();
	
	@Override
	public void gravar() {
		ClienteRepository repository = new ClienteRepository();
				
		repository.gravarCliente(listaClientes);
		
		listaClientes = new ArrayList<>();
	}

	private void validaCliente(Cliente cliente) throws BusinessException {
		if(cliente.getAgencia()==null||cliente.getConta()==null||
		  cliente.getNomeCliente()==null||cliente.getTelefone()==null ||
		  cliente.getEmail() == null) {
			throw new BusinessException("Os campos agência, conta, nome, email e telefone são obrigatórios.");
		}
		
		if (!cliente.getEmail().matches("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$")) {
			throw new BusinessException("Email inválido!");
		}
	}
	
	@Override
	public List<Cliente> listarClientes() throws BusinessException {
		ClienteRepository repository = new ClienteRepository();
		List<Cliente> listaRetorno = repository.listarClientes();
	
		if(listaRetorno !=null && !listaRetorno.isEmpty() ) {
			return listaRetorno;
		} else {
			throw new BusinessException("Nenhum cliente foi encontrado.");
		}
	}
	
	@Override
	public void gravarEmMemoria(Cliente cliente) throws BusinessException{
		validaCliente(cliente);
		listaClientes.add(cliente);
	}
	
	@Override
	public List<Cliente> listarDaMemoria() throws BusinessException {
		if(listaClientes != null && !listaClientes.isEmpty()) {
			return listaClientes;
		} else {
			throw new BusinessException("Nenhum cliente foi encontrado.");
		}
	}
}
