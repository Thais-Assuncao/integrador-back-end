package br.com.itau.correntista.services.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.itau.correntista.exceptions.BusinessException;
import br.com.itau.correntista.models.Cliente;
import br.com.itau.correntista.repositories.impl.ClienteRepository;
import br.com.itau.correntista.services.IClienteService;

public class ClienteService implements IClienteService {

	@Override
	public void gravar(Cliente cliente) {
		ClienteRepository repository = new ClienteRepository();
		
		if(cliente.getAgencia()==null||cliente.getConta()==null||
		  cliente.getNomeCliente()==null||cliente.getTelefone()==null ||
		  cliente.getEmail() == null) {
			throw new BusinessException("Os campos agência, conta, nome, email e telefone são obrigatórios.");
		}
		
		if (!cliente.getEmail().contains("@")) {
			throw new BusinessException("Email inválido!");
		}
				
		repository.gravarCliente(cliente);
	}
	
	@Override
	public List<Cliente> listarClientes() {
		ClienteRepository repository = new ClienteRepository();
		List<Cliente> listaRetorno = repository.listarClientes();
	
		if(!listaRetorno.isEmpty()) {
			return listaRetorno;
		} else {
			throw new BusinessException("Nenhum cliente foi encontrado.");
		}
	}
	
	public void gravarEmMemoria(Cliente cliente) {
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		listaClientes.add(cliente);
	}
	
	public void listarDaMemoria(List<Cliente> listaClientes) {
		listaClientes.forEach(contato 
				-> System.out.println("Nome: " + contato.getNomeCliente() +
						            "; Agencia: "+ contato.getAgencia() +
						            "; Conta: " + contato.getConta() +
						            "; Saldo: " + contato.getSaldo() +
						            "; Telefone: " +contato.getTelefone() +
						            "; Email: " + contato.getEmail()));
	}
}
