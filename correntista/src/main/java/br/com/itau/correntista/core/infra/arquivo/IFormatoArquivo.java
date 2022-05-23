package br.com.itau.correntista.core.infra.arquivo;

public interface IFormatoArquivo<T> {
	
	public String toFormatoArquivo();
	public T FromArquivoToObject(String delimitador, String texto);

}
