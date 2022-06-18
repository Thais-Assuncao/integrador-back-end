package br.com.itau.correntista.utils;

import org.jasypt.util.password.BasicPasswordEncryptor;

public class Utils {

	private static BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();

	private Utils() {
	}

	public static String criprografaSenha(String senha) {
		return encryptor.encryptPassword(senha);
	}

	public static Boolean verificaSenha(String senha, String senhaCriprografa) {
		return encryptor.checkPassword(senha, senhaCriprografa);
	}

}
