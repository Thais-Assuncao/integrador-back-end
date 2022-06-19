package br.com.itau.correntista.views.atm;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.itau.correntista.models.Correntista;
import br.com.itau.correntista.models.Transacao;
import br.com.itau.correntista.repositories.ITransacaoRepository;
import br.com.itau.correntista.repositories.impl.TransacaoRepository;
import br.com.itau.correntista.store.CorrentistaLogado;
import javax.swing.JFormattedTextField;

public class DepositoAtmScreen extends JFrame {

	private JPanel contentPane;
	private JFormattedTextField txtValorDeposito;
	private JLabel lblAgencia;
	private JLabel lblConta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepositoAtmScreen frame = new DepositoAtmScreen();
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
	public DepositoAtmScreen() {
		setTitle("DEPÓSITO - ICARROS");
		setResizable(false);
		setBounds(450, 250, 452, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				irTelaPrincipal();
				setVisible(false);
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVoltar.setBounds(151, 204, 133, 32);
		contentPane.add(btnVoltar);



		txtValorDeposito = new JFormattedTextField(new java.text.DecimalFormat("#,###.00"));
		txtValorDeposito.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtValorDeposito.setBounds(151, 118, 133, 32);
		contentPane.add(txtValorDeposito);
		txtValorDeposito.setColumns(10);

		JButton btnDepositar = new JButton("DEPOSITAR");
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtValorDeposito.getText().isBlank()) {
						JOptionPane.showMessageDialog(null, "O campo valor é de preenchimento obrigatório", "Depósito: erro!", JOptionPane.ERROR_MESSAGE);
						return;
					}
					Double valorDeposito = Double.parseDouble(txtValorDeposito.getText().replace(".", "").replace(",", "."));
					if(valorDeposito <= 0) {
						JOptionPane.showMessageDialog(null, "O campo valor deve ser superior a zero reais.", "Depósito: erro!", JOptionPane.ERROR_MESSAGE);
						return;
					}
					ITransacaoRepository repository = new TransacaoRepository();
					Correntista correntista = new Correntista();
					correntista.setId(CorrentistaLogado.getInstance().getId());
					Double saldoAnterior = repository.buscaSaldoCorrentista(correntista.getId());

					int rows = repository.gravaTransacao(new Transacao(valorDeposito, saldoAnterior, valorDeposito + saldoAnterior, correntista));
					if(rows > 0 ) {
						JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!", "Depósito: sucesso!", JOptionPane.INFORMATION_MESSAGE);
						PrincipalATM principalATM = new PrincipalATM();
						principalATM.setVisible(true);
						setVisible(false);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao registrar o depósito", "Depósito: erro!", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro inesperado ao registrar o depósito: " + e1.getMessage(), "Depósito: erro!", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnDepositar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDepositar.setBounds(151, 161, 133, 32);
		contentPane.add(btnDepositar);

		JLabel lblValor = new JLabel("VALOR:");
		lblValor.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblValor.setBounds(178, 84, 80, 23);
		contentPane.add(lblValor);

		lblAgencia = new JLabel("AGÊNCA: null");
		lblAgencia.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAgencia.setBounds(151, 20, 118, 14);
		contentPane.add(lblAgencia);

		lblConta = new JLabel("CONTA: null");
		lblConta.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConta.setBounds(151, 45, 118, 14);
		contentPane.add(lblConta);
		carregaInformacoesUsuarioLogado();
	}

	public void carregaInformacoesUsuarioLogado() {
		lblAgencia.setText("AGÊNCA: " + CorrentistaLogado.getInstance().getAgencia());
		lblConta.setText("CONTA: " + CorrentistaLogado.getInstance().getConta());
	}
	protected JLabel getLblAgencia() {
		return lblAgencia;
	}
	protected JLabel getLblConta() {
		return lblConta;
	}
	
	private void irTelaPrincipal() {
		PrincipalATM principal = new PrincipalATM();
		principal.setVisible(true);
	}
}
