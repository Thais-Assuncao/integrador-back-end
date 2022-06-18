package br.com.itau.correntista.views.atm;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.itau.correntista.store.CorrentistaLogado;

public class PrincipalATM extends JFrame {

	private JPanel contentPane;
	private JLabel lblAgencia;
	private JLabel lblConta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalATM frame = new PrincipalATM();
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
	public PrincipalATM() {
		setTitle("ICARROS - CAIXA");
		setBounds(100, 100, 512, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnDeposito = new JButton("Depósito");
		btnDeposito.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDeposito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepositoAtmScreen depositoAtmScreen = new DepositoAtmScreen();
				depositoAtmScreen.setVisible(true);
				setVisible(false);
				dispose();
				
			}
		});
		btnDeposito.setBounds(50, 132, 106, 39);
		contentPane.add(btnDeposito);
		
		JButton btnSaque = new JButton("Saque");
		btnSaque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaqueAtmScreen saqueAtmScreen = new SaqueAtmScreen();
				saqueAtmScreen.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnSaque.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSaque.setBounds(342, 132, 106, 39);
		contentPane.add(btnSaque);
		
		JButton btnSaldo = new JButton("Saldo");
		btnSaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExibeSaldoScreen exibeSaldoScreen = new ExibeSaldoScreen();
				exibeSaldoScreen.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnSaldo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSaldo.setBounds(50, 212, 106, 39);
		contentPane.add(btnSaldo);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginAtmScreen loginAtmScreen = new LoginAtmScreen();
				CorrentistaLogado correntista = CorrentistaLogado.getInstance();
				correntista.setId(null);
				correntista.setAgencia(null);
				correntista.setConta(null);
				loginAtmScreen.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSair.setBounds(342, 212, 106, 39);
		contentPane.add(btnSair);
		
		lblAgencia = new JLabel("AGÊNCIA:");
		lblAgencia.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAgencia.setBounds(178, 33, 139, 14);
		contentPane.add(lblAgencia);
		
		lblConta = new JLabel("CONTA:");
		lblConta.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConta.setBounds(178, 58, 130, 14);
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
}
