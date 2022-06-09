package br.com.itau.correntista.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ControleClienteScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtEndereco;
	private JTextField textField_5;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField textField_8;
	private JTextField textField_9;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControleClienteScreen frame = new ControleClienteScreen();
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
	public ControleClienteScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 549, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 417, 30);
		contentPane.add(toolBar);
		
		JButton btnNovo = new JButton("");
		btnNovo.setIcon(new ImageIcon(ControleClienteScreen.class.getResource("/icons/file-rounded-empty-sheet.png")));
		toolBar.add(btnNovo);
		
		JButton btnSalvar = new JButton("");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Erro ao Inserir", "Título de Erro", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnSalvar.setIcon(new ImageIcon(ControleClienteScreen.class.getResource("/icons/diskette.png")));
		toolBar.add(btnSalvar);
		
		JButton btnEditar = new JButton("");
		btnEditar.setIcon(new ImageIcon(ControleClienteScreen.class.getResource("/icons/pencil.png")));
		toolBar.add(btnEditar);
		
		JButton btnExcluir = new JButton("");
		btnExcluir.setIcon(new ImageIcon(ControleClienteScreen.class.getResource("/icons/bin.png")));
		toolBar.add(btnExcluir);
		
		JLabel lblBuscar = new JLabel("Buscar por Id:  ");
		toolBar.add(lblBuscar);
		
		textField = new JTextField();
		toolBar.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setIcon(new ImageIcon(ControleClienteScreen.class.getResource("/icons/search.png")));
		toolBar.add(btnNewButton_4);
		
		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setBounds(10, 41, 17, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(51, 41, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("E-mail");
		lblNewLabel_1_1.setBounds(10, 95, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Endreço");
		lblNewLabel_1_1_1.setBounds(10, 138, 72, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("CEP");
		lblNewLabel_1_1_1_1.setBounds(10, 178, 46, 14);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Bairro");
		lblNewLabel_1_1_1_1_1.setBounds(111, 178, 46, 14);
		contentPane.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Cidade");
		lblNewLabel_1_1_1_1_1_1.setBounds(286, 178, 46, 14);
		contentPane.add(lblNewLabel_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("UF");
		lblNewLabel_1_1_1_1_1_1_1.setBounds(484, 178, 25, 14);
		contentPane.add(lblNewLabel_1_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Telefone");
		lblNewLabel_1_1_1_1_2.setBounds(10, 223, 72, 14);
		contentPane.add(lblNewLabel_1_1_1_1_2);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(10, 64, 31, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(51, 64, 467, 20);
		contentPane.add(txtNome);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(10, 108, 508, 20);
		contentPane.add(txtEmail);
		
		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(10, 151, 508, 20);
		contentPane.add(txtEndereco);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(10, 192, 72, 20);
		contentPane.add(textField_5);
		
		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(111, 192, 149, 20);
		contentPane.add(txtBairro);
		
		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(284, 192, 171, 20);
		contentPane.add(txtCidade);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(481, 192, 31, 20);
		contentPane.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(10, 235, 120, 20);
		contentPane.add(textField_9);
	}
}
