package br.com.itau.correntista.views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.itau.correntista.models.Gerente;
import br.com.itau.correntista.repositories.impl.GerenteRepository;

public class LoginScreen extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;
	private GerenteRepository rep = new GerenteRepository();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen frame = new LoginScreen();
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
	public LoginScreen() {
		setResizable(false);
		setTitle("Icarros Bank");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 447, 222);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(122, 41, 202, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(122, 87, 202, 20);
		contentPane.add(txtSenha);
		
		JLabel lblUsuario = new JLabel("Usuário:");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(66, 44, 46, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenha.setLabelFor(txtSenha);
		lblSenha.setBounds(66, 90, 46, 14);
		contentPane.add(lblSenha);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtUsuario.getText().isBlank()||txtSenha.getPassword().toString().isBlank()) {
					JOptionPane.showMessageDialog(null, "Preencha os campos de usuário e senha");
				}
				else {
					GerenteRepository gerenteRepository = new GerenteRepository();
					try {
						Gerente gerente = gerenteRepository.consultaPorEmail(txtUsuario.getText());
						if(gerente==null) JOptionPane.showMessageDialog(null, "Usuário não encontrado");
						else if(gerente.getSenha().equals(txtSenha.getPassword().toString())) JOptionPane.showMessageDialog(null, "Senha invalida");
						else {
							try {
								new ControleClienteScreen();
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e.getMessage(), "Erro inesperado!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnEntrar.setBounds(84, 131, 89, 23);
		contentPane.add(btnEntrar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(235, 131, 89, 23);
		contentPane.add(btnSair);
	}
}
