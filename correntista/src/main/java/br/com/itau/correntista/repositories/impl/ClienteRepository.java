package br.com.itau.correntista.repositories.impl;

import java.util.List;

import br.com.itau.correntista.core.infra.arquivo.GerenciadorArquivo;
import br.com.itau.correntista.models.Cliente;
import br.com.itau.correntista.repositories.IClienteRepository;

public class ClienteRepository implements IClienteRepository {
	private GerenciadorArquivo<Cliente> gerenciador = new GerenciadorArquivo<Cliente>("./db.txt");
	private boolean useHeader;
	
	public ClienteRepository() {
		this.useHeader = false;
	}
	public ClienteRepository(String textoCabecalho) {
		this.gerenciador.adicionarCabecalho(textoCabecalho);
		this.useHeader =true;
	}
	@Override
	public void gravarCliente(Cliente cliente) {
		this.gerenciador.adicionar(cliente);

	}
	public void gravarCliente(List<Cliente> clientes) {
		this.gerenciador.adicionar(clientes);
	}
	
	@Override
	public List<Cliente> listarClientes() {
		
		return this.gerenciador.buscarTodasLinhasDoArquivo(",", this.useHeader, Cliente.class); 
	}	
}
