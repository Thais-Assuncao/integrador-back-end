package br.com.itau.correntista.views.atm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.itau.correntista.models.Correntista;
import br.com.itau.correntista.models.Transacao;
import br.com.itau.correntista.repositories.ITransacaoRepository;
import br.com.itau.correntista.repositories.impl.TransacaoRepository;
import br.com.itau.correntista.store.CorrentistaLogado;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class ExibeSaldoScreen extends JFrame {

	private JPanel contentPane;
	private JFormattedTextField txtSaldo;
	private JLabel lblConta;
	private JLabel lblAgencia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExibeSaldoScreen frame = new ExibeSaldoScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ExibeSaldoScreen() {
		setTitle("SALDO - ICARROS");
		setResizable(false);
		setBounds(450, 250, 425, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				irTelaPrincipal();
				setVisible(false);
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVoltar.setBounds(142, 225, 133, 32);
		contentPane.add(btnVoltar);
		
		JLabel lblSaldo = new JLabel("SALDO");
		lblSaldo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSaldo.setBounds(173, 91, 80, 23);
		contentPane.add(lblSaldo);

		txtSaldo = new JFormattedTextField(new java.text.DecimalFormat("#,###.00"));
		txtSaldo.setHorizontalAlignment(SwingConstants.CENTER);
		txtSaldo.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtSaldo.setEditable(false);
		txtSaldo.setEnabled(false);
		txtSaldo.setBounds(142, 153, 133, 32);
		contentPane.add(txtSaldo);
		txtSaldo.setColumns(10);
		
		lblAgencia = new JLabel("AGÊNCA: null");
		lblAgencia.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAgencia.setBounds(149, 22, 118, 14);
		contentPane.add(lblAgencia);
		
		lblConta = new JLabel("CONTA: null");
		lblConta.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConta.setBounds(149, 47, 118, 14);
		contentPane.add(lblConta);
		carregaInformacoesUsuarioLogado();
	}
	public void carregaInformacoesUsuarioLogado() {
		lblAgencia.setText("AGÊNCA: " + CorrentistaLogado.getInstance().getAgencia());
		lblConta.setText("CONTA: " + CorrentistaLogado.getInstance().getConta());
		ITransacaoRepository repository = new TransacaoRepository();
		Correntista correntista = new Correntista();
		correntista.setId(CorrentistaLogado.getInstance().getId());
		Double saldoAtual = repository.buscaSaldoCorrentista(correntista.getId());
		txtSaldo.setText("R$ " + saldoAtual.toString());
	}
	
	private void irTelaPrincipal() {
		PrincipalATM principal = new PrincipalATM();
		principal.setVisible(true);
	}

	protected JLabel getLblConta() {
		return lblConta;
	}
	protected JLabel getLblAgencia() {
		return lblAgencia;
	}
}
