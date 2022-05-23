package br.com.itau.correntista.core.infra.arquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorArquivo<T extends IFormatoArquivo<?>> {
	
	private String nomeArquivo;
	

	public GerenciadorArquivo(String arquivo) {
		this.nomeArquivo = arquivo;
	}
	
	/**
	 * Adiciona linha de cabeçalho caso seja necessário.
	 * @param textoCabecalho - linha de cabeçalho incluindo delimitadores
	 */
	public void adicionarCabecalho(String textoCabecalho) {
		File file = new File(nomeArquivo);
		if(file.exists()) {
			adicionarLinha(file, textoCabecalho);
		} else {
			try {
				if(file.createNewFile()) {
					adicionarLinha(file, textoCabecalho);
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Adiciona um registro ao arquivo.
	 * @param object - objeto que implemente a interface IFormatoArquivo.
	 */
	public void adicionar(T object) {
		File file = new File(nomeArquivo);
		if(file.exists()) {
			adicionarLinha(file, object.toFormatoArquivo());
			
		} else {
			try {
				if(file.createNewFile()) {
					adicionarLinha(file, object.toFormatoArquivo());
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Adiciona um conjunto de registros ao arquivo.
	 * @param lista - lista de objetos a serem adicionados no arquivo. 
	 * É necessário que o arquivo implemente a interface IFormatoArquivo
	 */
	public void adicionar(List<T> lista) {
		File file = new File(nomeArquivo);
		FileWriter fileWriter = null;
		PrintWriter printWriter = null;
		try {
			fileWriter = new FileWriter(file, true);
			printWriter = new PrintWriter(fileWriter);
			for (T t : lista) {
				printWriter.println(t.toFormatoArquivo());
			}
			printWriter.flush();
			fileWriter.flush();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		finally {
			if(printWriter != null)
				printWriter.close();
			if(fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
	
	/**
	 * Busca uma lista de objetos do arquivo.
	 * @param delimitador - String ou caracter delimitador
	 * @param comCabecalho - Se o arquivo contém cabeçalho ou não
	 * @param clazz - Objeto que deverá ser feito o mapamento de retorno.
	 * @return - Lista de objetos retornados a partir do parsing do arquivo.
	 */
	@SuppressWarnings("unchecked")
	public List<T> buscarTodasLinhasDoArquivo(String delimitador, boolean comCabecalho, Class<T> clazz) {

		List<T> lista = new ArrayList<T>();
		
		File file = new File(nomeArquivo);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		String linha = "";
		
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			while((linha = bufferedReader.readLine()) != null) {
				if(comCabecalho) {
					comCabecalho = false;
					continue;
				}
				T t = null;
				try {
					t = clazz.getDeclaredConstructor().newInstance();
					lista.add((T) t.FromArquivoToObject(delimitador, linha));
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					System.out.println(e.getMessage());
				}
			}
			return lista;
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
	
	private void adicionarLinha(File file, String texto) {
		FileWriter fileWriter = null;
		PrintWriter printWriter = null;
		try {
			fileWriter = new FileWriter(file, true);
			printWriter = new PrintWriter(fileWriter);
			printWriter.println(texto);
			printWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			if(printWriter != null)
				printWriter.close();
			if(fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	

}
