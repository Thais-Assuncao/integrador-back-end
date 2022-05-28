package br.com.itau.correntista.exceptions;

public class BusinessException extends RuntimeException {

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException() {
		super();
	}

}
