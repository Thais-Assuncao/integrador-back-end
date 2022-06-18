package br.com.itau.correntista.models;

public class Correntista {

	private Long id;
	
	private Integer ag;
	
	private Integer conta;
	
	private String nome;
	
	private String email;
	
	private String telefone;
	
	private Double saldo;
	
	private String endereco;
	
	private String cep;
	
	private String bairro;
	
	private String cidade;
	
	private String uf;

	public Correntista(Long id, Integer ag, Integer conta, String nome, String email, String telefone, Double saldo,
			String endereco, String cep, String bairro, String cidade, String uf) {
		super();
		this.id = id;
		this.ag = ag;
		this.conta = conta;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.saldo = saldo;
		this.endereco = endereco;
		this.cep = cep;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
	}

	public Correntista(Integer ag, Integer conta, String nome, String email, String telefone, Double saldo,
			String endereco, String cep, String bairro, String cidade, String uf) {
		super();
		this.ag = ag;
		this.conta = conta;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.saldo = saldo;
		this.endereco = endereco;
		this.cep = cep;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
	}

	public Correntista() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAg() {
		return ag;
	}

	public void setAg(Integer ag) {
		this.ag = ag;
	}

	public Integer getConta() {
		return conta;
	}

	public void setConta(Integer conta) {
		this.conta = conta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
}
