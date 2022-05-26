package br.com.itau.correntista.models;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import br.com.itau.correntista.core.infra.arquivo.IFormatoArquivo;

public class Cliente implements IFormatoArquivo<Object> {
	
	private String agencia;
	private String conta;
	private String nomeCliente;
	private String telefone;
	private BigDecimal saldo;
	
	public Cliente() {
	}
	
	public Cliente(String agencia, String conta, String nomeCliente, String telefone, BigDecimal saldo) {
		this.agencia = agencia;
		this.conta = conta;
		this.nomeCliente = nomeCliente;
		this.telefone = telefone;
		this.saldo = saldo;
	}

	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getConta() {
		return conta;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toFormatoArquivo() {
		StringBuilder sb = new StringBuilder();
		sb.append(agencia);
		sb.append(",");
		sb.append(conta);
		sb.append(",");
		sb.append(nomeCliente);
		sb.append(",");
		sb.append(telefone);
		sb.append(",");
		sb.append(saldo);
		return sb.toString();
	}
	
	@Override
	public Cliente FromArquivoToObject(String delimitador, String texto) {
		if(texto == null) {
			return null;
		}
		if(texto.isBlank()) {
			return null;
		}
		List<String> palavras = Arrays.asList(texto.split("\\s*"+ delimitador +"\\s*"));
		if(palavras != null) {
			Cliente cliente = new Cliente();
			cliente.setAgencia(palavras.get(0));
			cliente.setConta(palavras.get(1));
			cliente.setNomeCliente(palavras.get(2));
			cliente.setTelefone(palavras.get(3));
			cliente.setSaldo(new BigDecimal(palavras.get(4)));
			return cliente;
		}
		return null;
	}
	

}
