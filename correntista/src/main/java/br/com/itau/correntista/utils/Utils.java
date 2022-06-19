package br.com.itau.correntista.utils;

import java.text.NumberFormat;
import java.util.Locale;

import org.jasypt.util.password.BasicPasswordEncryptor;

public class Utils {

	private static BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();

	
	private static Locale localeBR = new Locale( "pt", "BR" );  
	
	private static NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);  

	private Utils() {
	}

	public static String criprografaSenha(String senha) {
		return encryptor.encryptPassword(senha);
	}

	public static Boolean verificaSenha(String senha, String senhaCriprografa) {
		return encryptor.checkPassword(senha, senhaCriprografa);
	}
	
	public static String converteDinheiro(Double moeda) {
		return dinheiroBR.format(moeda);
	}

	public static NumberFormat getDinheiroBR() {
		return dinheiroBR;
	}


}
