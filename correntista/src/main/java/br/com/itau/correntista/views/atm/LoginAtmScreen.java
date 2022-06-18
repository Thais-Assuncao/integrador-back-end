package br.com.itau.correntista.views.atm;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.itau.correntista.models.Correntista;
import br.com.itau.correntista.repositories.ICorrentistaRepository;
import br.com.itau.correntista.repositories.impl.CorrentistaRepository;
import br.com.itau.correntista.store.CorrentistaLogado;

public class LoginAtmScreen extends JFrame {

	private JPanel contentPane;
	private JTextField txtConta;
	private JPasswordField txtSenha;
	private JLabel lblSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginAtmScreen frame = new LoginAtmScreen();
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
	public LoginAtmScreen() {
		setTitle("DEPÓSITO - ICARROS");
		setResizable(false);
		setBounds(100, 100, 425, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSacar = new JButton("Entrar");
		btnSacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtConta.getText().isEmpty() || txtSenha.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Usuário e senha devem ser preenchidos.", "Preenchimento obrigatório!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				ICorrentistaRepository repository = new CorrentistaRepository();
				Correntista correntista;
				try {
					correntista = repository.consultaPorContaSenha(Integer.parseInt(txtConta.getText()), Integer.parseInt(txtSenha.getText()));
					if(correntista == null) {
						JOptionPane.showMessageDialog(null, "Usuário e/ou senha incorretos!", "Erro de autenticação!", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					CorrentistaLogado correntistaLogado = CorrentistaLogado.getInstance();
					correntistaLogado.setId(correntista.getId());
					correntistaLogado.setConta(correntista.getConta());
					correntistaLogado.setAgencia(correntista.getAg());
					PrincipalATM principalATM = new PrincipalATM();
					principalATM.setVisible(true);
					setVisible(false);
					dispose();
				} catch (NumberFormatException | SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnSacar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSacar.setBounds(138, 193, 133, 32);
		contentPane.add(btnSacar);
		
		JLabel lblConta = new JLabel("CONTA:");
		lblConta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConta.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblConta.setBounds(10, 89, 107, 23);
		contentPane.add(lblConta);
		
		txtConta = new JTextField();
		txtConta.setBounds(138, 87, 133, 32);
		contentPane.add(txtConta);
		txtConta.setColumns(10);
		
		txtSenha = new JPasswordField();
		txtSenha.setColumns(10);
		txtSenha.setBounds(138, 144, 133, 32);
		contentPane.add(txtSenha);
		
		lblSenha = new JLabel("SENHA:");
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSenha.setBounds(37, 146, 80, 23);
		contentPane.add(lblSenha);
	}
}
