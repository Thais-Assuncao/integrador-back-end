package br.com.itau.correntista.views;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import br.com.itau.correntista.models.Correntista;
import br.com.itau.correntista.repositories.ICorrentistaRepository;
import br.com.itau.correntista.repositories.impl.CorrentistaRepository;
import javax.swing.JScrollPane;

public class ListaCorrentistasScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaCorrentistasScreen frame = new ListaCorrentistasScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ListaCorrentistasScreen() throws SQLException {
		setTitle("ICARROS - LISTA CORRENTISTAS");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 526, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(35, 20, 440, 286);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 441, 286);
		panel.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setRows(8);
		textArea.setEditable(false);
		listaCorrentistas(textArea);
	}
	
	private void listaCorrentistas(JTextArea textArea) throws SQLException {
		ICorrentistaRepository repository = new CorrentistaRepository();
		List<Correntista> lista = repository.listaTodosCorrentistas();
		String cabecalho = "    ID: " + "\t" +"CORRENTISTA: " + "\t" + "AGÊNCIA: " + "\t" + "CONTA: \n";
		textArea.append(cabecalho);
		textArea.append("   =========================================================\n");
		for(Correntista c: lista) {
			String linha = "    "+c.getId() + "\t" + c.getNome() + "\t" +c.getAg()+ "\t" + c.getConta() + "\n";
			textArea.append(linha);
			textArea.append("   =========================================================\n");
		}
	}
}
