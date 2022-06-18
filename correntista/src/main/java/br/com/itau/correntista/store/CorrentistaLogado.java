package br.com.itau.correntista.store;

public class CorrentistaLogado {

	private static CorrentistaLogado instance = null;

	private Long id;

	private Integer agencia;

	private Integer conta;

	private CorrentistaLogado() {
	}

	public static synchronized CorrentistaLogado getInstance() {
		if (instance == null) {
			instance = new CorrentistaLogado();
		}

		return instance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public Integer getConta() {
		return conta;
	}

	public void setConta(Integer conta) {
		this.conta = conta;
	}
	
	
}
