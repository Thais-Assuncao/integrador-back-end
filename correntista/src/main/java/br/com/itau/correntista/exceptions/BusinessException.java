package br.com.itau.correntista.exceptions;

public class BusinessException extends Exception {

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException() {
		super();
	}

}
