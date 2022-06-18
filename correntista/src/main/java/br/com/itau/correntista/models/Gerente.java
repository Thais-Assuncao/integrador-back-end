package br.com.itau.correntista.models;

public class Gerente {
	private String email;
	private String senha;
	private Long id;
	public Gerente(String email, String senha, Long id) {
		super();
		this.email = email;
		this.senha = senha;
		this.id = id;
	}
	public Gerente(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}
	public boolean autenticar(String senhaPassada) {
		return this.senha == senhaPassada;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
