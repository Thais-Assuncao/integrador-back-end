package br.com.itau.correntista.views.atm;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import br.com.itau.correntista.models.Transacao;
import br.com.itau.correntista.repositories.ITransacaoRepository;
import br.com.itau.correntista.repositories.impl.TransacaoRepository;
import br.com.itau.correntista.store.CorrentistaLogado;
import br.com.itau.correntista.utils.Utils;

public class ExtratoAtmScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExtratoAtmScreen frame = new ExtratoAtmScreen();
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
	public ExtratoAtmScreen() throws SQLException {
		setTitle("ICARROS - LISTA CORRENTISTAS");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 526, 400);
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
		listatTransacoes(textArea);
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrincipalATM p = new PrincipalATM();
				p.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVoltar.setBounds(184, 317, 133, 32);
		contentPane.add(btnVoltar);
	}
	
	private void listatTransacoes(JTextArea textArea) throws SQLException {
		ITransacaoRepository repository = new TransacaoRepository();
		List<Transacao> lista = repository.listaTransacoesPorCorrentista(CorrentistaLogado.getInstance().getId());
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		if(lista == null || lista.size() == 0) {
			textArea.append("   =========================================================\n");
			textArea.append("   ================== CORRENT. S/ MOV. FIN. ===================\n");
			textArea.append("   =========================================================\n");
		} else {
			String cabecalho = "    DATA " + "\t" +"HISTÓRICO " + "\t\t" + "VALOR" + "\n";
			textArea.append(cabecalho);
			textArea.append("   =========================================================\n");
			Transacao primeiro = lista.get(0);
			Transacao ultimo = lista.get(lista.size() - 1);
			textArea.append("    "+ format.format(primeiro.getDataCriacao()) + "\t" + "SALDO ANTERIOR" + "\t" + Utils.converteDinheiro(primeiro.getSaldoAnterior()) + "\n");
			textArea.append("   =========================================================\n");
			for(Transacao t: lista) {
				String linha = "    "+ format.format(t.getDataCriacao()) + "\t" + t.getTipo() + "\t\t" + Utils.converteDinheiro(t.getFluxo()) + "\n";
				textArea.append(linha);
				textArea.append("   =========================================================\n");
			}
			textArea.append("\n\n");
			textArea.append("    "+ format.format(ultimo.getDataCriacao()) + "\t" + "SALDO ATUALIZADO" + "\t" + Utils.converteDinheiro(ultimo.getSaldoAtualizado()) + "\n");			
		}
	}
}
