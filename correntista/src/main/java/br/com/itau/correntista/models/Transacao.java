package br.com.itau.correntista.models;

import java.util.Objects;

public class Transacao {

	private Long id;
	
	private Double fluxo;
	
	private Double saldoAnterior;
	
	private Double saldoAtualizado;
	
	private Correntista correntista;
	
	public Transacao() {
		super();
	}

	public Transacao(Double fluxo, Double saldoAnterior, Double saldoAtualizado, Correntista correntista) {
		super();
		this.fluxo = fluxo;
		this.saldoAnterior = saldoAnterior;
		this.saldoAtualizado = saldoAtualizado;
		this.correntista = correntista;
	}

	public Transacao(Long id, Double fluxo, Double saldoAnterior, Double saldoAtualizado, Correntista correntista) {
		super();
		this.id = id;
		this.fluxo = fluxo;
		this.saldoAnterior = saldoAnterior;
		this.saldoAtualizado = saldoAtualizado;
		this.correntista = correntista;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getFluxo() {
		return fluxo;
	}

	public void setFluxo(Double fluxo) {
		this.fluxo = fluxo;
	}

	public Double getSaldoAnterior() {
		return saldoAnterior;
	}

	public void setSaldoAnterior(Double saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}

	public Double getSaldoAtualizado() {
		return saldoAtualizado;
	}

	public void setSaldoAtualizado(Double saldoAtualizado) {
		this.saldoAtualizado = saldoAtualizado;
	}

	public Correntista getCorrentista() {
		return correntista;
	}

	public void setCorrentista(Correntista correntista) {
		this.correntista = correntista;
	}

	@Override
	public int hashCode() {
		return Objects.hash(correntista, fluxo, id, saldoAnterior, saldoAtualizado);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		return Objects.equals(correntista, other.correntista) && Objects.equals(fluxo, other.fluxo)
				&& Objects.equals(id, other.id) && Objects.equals(saldoAnterior, other.saldoAnterior)
				&& Objects.equals(saldoAtualizado, other.saldoAtualizado);
	}
	
}
