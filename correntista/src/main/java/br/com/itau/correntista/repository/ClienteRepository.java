package br.com.itau.correntista.repository;

import java.util.List;

import br.com.itau.correntista.core.infra.arquivo.GerenciadorArquivo;
import br.com.itau.correntista.model.Cliente;

public class ClienteRepository {
	private GerenciadorArquivo<Cliente> gerenciador = new GerenciadorArquivo<Cliente>("./db.txt");
	private boolean useHeader;
	
	public ClienteRepository() {
		this.useHeader = false;
	}
	public ClienteRepository(String textoCabecalho) {
		this.gerenciador.adicionarCabecalho(textoCabecalho);
		this.useHeader =true;
	}
	public void gravarCliente(Cliente cliente) {
		this.gerenciador.adicionar(cliente);
	}
	public void gravarCliente(List<Cliente> clientes) {
		this.gerenciador.adicionar(clientes);
	}
	
	public List<Cliente> listarClientes(){
		return this.gerenciador.buscarTodasLinhasDoArquivo(",", this.useHeader, Cliente.class); 
	}
	
}
