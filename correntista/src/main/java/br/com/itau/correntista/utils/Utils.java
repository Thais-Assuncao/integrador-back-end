package br.com.itau.correntista.utils;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
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
	
	public static String abreviaNome(String nome) {
		String meio = " ";
		String[] tokens = nome.split(" ");
		if(tokens.length > 2) {
			List<String> preposicoes = Arrays.asList("de","di", "da", "do","das", "dos");
			for (int i = 1; i < tokens.length - 1 ; i++) {
				String token = tokens[i];
				boolean isSobrenome = preposicoes.stream().allMatch(preposicao -> { 
					return !preposicao.equalsIgnoreCase(token); 
				});
				if(isSobrenome) {
					meio += String.valueOf(token.toCharArray()[0]).toUpperCase() + ". ";
				} else {
					meio += token + " ";
				}
			}
			return tokens[0] + meio + tokens[tokens.length - 1];			
		}
		return nome;
	}
	
	public static String completaNomePequeno(String nome) {
		if(nome.length() < 13) {
			for(int i = nome.length(); i<29; i++) {
				nome+=" ";
			}
		}
		return nome;
	}

	public static NumberFormat getDinheiroBR() {
		return dinheiroBR;
	}


}
