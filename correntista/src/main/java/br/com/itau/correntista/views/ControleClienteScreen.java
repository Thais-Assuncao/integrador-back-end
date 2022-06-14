package br.com.itau.correntista.views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.itau.correntista.models.Correntista;
import br.com.itau.correntista.repositories.ICorrentistaRepository;
import br.com.itau.correntista.repositories.impl.CorrentistaRepository;

public class ControleClienteScreen extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscaId;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtEndereco;
	private JFormattedTextField txtCep;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JComboBox txtUf;
	private JFormattedTextField txtTelefone;
	private MaskFormatter fmtCEP;
	private JFormattedTextField txtAgencia;
	private JFormattedTextField txtConta;

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
	 * @throws ParseException 
	 */
	public ControleClienteScreen() throws ParseException {
		setTitle("Gestão de clientes (correntistas)");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		fmtCEP = new MaskFormatter("#####-###");
		
		String[] ufs = new String[] {
				"AC",
				"AL",
				"AP",
				"AM",
				"BA",
				"CE",
				"DF",
				"ES",
				"GO",
				"MA",
				"MT",
				"MS",
				"MG",
				"PA",
				"PB",
				"PR",
				"PE",
				"PI",
				"RJ",
				"RN",
				"RS",
				"RO",
				"RR",
				"SC",
				"SP",
				"SE",
				"TO"
		};
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 417, 30);
		contentPane.add(toolBar);
		
		JButton btnNovo = new JButton("");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
				habilitarCampos(true);
			}
		});
		btnNovo.setIcon(new ImageIcon(ControleClienteScreen.class.getResource("/icons/file-rounded-empty-sheet.png")));
		toolBar.add(btnNovo);
		
		JButton btnSalvar = new JButton("");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ICorrentistaRepository correntistaRepository = new CorrentistaRepository();
				Integer agencia = 1001;
				Integer conta = 	new Random().nextInt(90000) + 10000;
				
				try {
					if(isCadastroValido()) {
						if(!txtId.getText().isEmpty()) {
							correntistaRepository.atualizarCorrentista(new Correntista(Long.parseLong(txtId.getText()), agencia, 
									Integer.parseInt(txtConta.getText()), txtNome.getText() , 
									txtEmail.getText(), txtTelefone.getText(), 0.0, txtEndereco.getText(), 
									txtCep.getText().replace("-", ""), txtBairro.getText(), txtCidade.getText(), 
									txtUf.getSelectedItem().toString()));
							limparCampos();
						}else {
							correntistaRepository.gravarCorrentista(new Correntista(agencia, 
									conta, txtNome.getText() , 
									txtEmail.getText(), txtTelefone.getText(), 0.0, txtEndereco.getText(), 
									txtCep.getText().replace("-", ""), txtBairro.getText(), txtCidade.getText(), 
									txtUf.getSelectedItem().toString()));
							JOptionPane.showMessageDialog(null, "Conta: " + conta +" criada com sucesso", "Abertura de conta", JOptionPane.INFORMATION_MESSAGE);
							limparCampos();
						}
					}
				}catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro inesperado!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnSalvar.setIcon(new ImageIcon(ControleClienteScreen.class.getResource("/icons/diskette.png")));
		toolBar.add(btnSalvar);
		
		JButton btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarCampos(true);
			}
		});
		btnEditar.setIcon(new ImageIcon(ControleClienteScreen.class.getResource("/icons/pencil.png")));
		toolBar.add(btnEditar);
		
		JButton btnExcluir = new JButton("");
		btnExcluir.setIcon(new ImageIcon(ControleClienteScreen.class.getResource("/icons/bin.png")));
		toolBar.add(btnExcluir);
		
		JLabel lblBuscar = new JLabel("Buscar por Id:  ");
		toolBar.add(lblBuscar);
		
		txtBuscaId = new JTextField();
		toolBar.add(txtBuscaId);
		txtBuscaId.setColumns(10);
		
		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Long id = Long.parseLong(txtBuscaId.getText().isBlank() ? "0" : txtBuscaId.getText() );
				CorrentistaRepository dao = new CorrentistaRepository();
				Correntista correntista;
				try {
					correntista = dao.consultaPorId(id);
					if(correntista == null || txtBuscaId.getText().isBlank()) {
						JOptionPane.showMessageDialog(null, "Não foi encontrado o correntista.", "Erro ao buscar", JOptionPane.ERROR_MESSAGE);
						limparCampos();
					} else {
						txtId.setText(correntista.getId().toString());
						txtNome.setText(correntista.getNome());
						txtEndereco.setText(correntista.getEndereco());
						txtBairro.setText(correntista.getBairro());
						txtCep.setText(correntista.getCep());
						txtCidade.setText(correntista.getCidade());
						txtEmail.setText(correntista.getEmail());
						txtUf.setSelectedItem(correntista.getUf());
						txtTelefone.setText(correntista.getTelefone());
						txtAgencia.setText(correntista.getAg().toString());
						txtConta.setText(correntista.getConta().toString());
						habilitarCampos(false);					
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro inesperado!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnBuscar.setIcon(new ImageIcon(ControleClienteScreen.class.getResource("/icons/search.png")));
		toolBar.add(btnBuscar);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(10, 41, 17, 14);
		contentPane.add(lblId);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(51, 41, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(10, 95, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblEndereco = new JLabel("Endreço");
		lblEndereco.setBounds(11, 146, 72, 14);
		contentPane.add(lblEndereco);
		
		JLabel lblCEP = new JLabel("CEP");
		lblCEP.setBounds(14, 197, 46, 14);
		contentPane.add(lblCEP);
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(115, 197, 46, 14);
		contentPane.add(lblBairro);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(290, 197, 46, 14);
		contentPane.add(lblCidade);
		
		JLabel lblUf = new JLabel("UF");
		lblUf.setBounds(488, 197, 25, 14);
		contentPane.add(lblUf);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(10, 253, 72, 14);
		contentPane.add(lblTelefone);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(10, 64, 31, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(51, 64, 495, 20);
		contentPane.add(txtNome);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(10, 120, 535, 20);
		contentPane.add(txtEmail);
		
		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(10, 169, 535, 20);
		contentPane.add(txtEndereco);
		
		txtCep = new JFormattedTextField(fmtCEP);
		txtCep.setColumns(10);
		txtCep.setBounds(10, 222, 72, 20);
		contentPane.add(txtCep);
		
		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(111, 222, 149, 20);
		contentPane.add(txtBairro);
		
		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(284, 222, 171, 20);
		contentPane.add(txtCidade);
		
		txtUf = new JComboBox(ufs);
		txtUf.setBounds(481, 222, 61, 20);
		contentPane.add(txtUf);
		
		txtTelefone = new JFormattedTextField();
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(10, 281, 108, 20);
		contentPane.add(txtTelefone);
		
		JLabel lblAgencia = new JLabel("Agência");
		lblAgencia.setBounds(150, 253, 72, 14);
		contentPane.add(lblAgencia);
		
		txtAgencia = new JFormattedTextField();
		txtAgencia.setEditable(false);
		txtAgencia.setColumns(10);
		txtAgencia.setBounds(150, 281, 72, 20);
		contentPane.add(txtAgencia);
		
		JLabel lblConta = new JLabel("Conta");
		lblConta.setBounds(252, 253, 72, 14);
		contentPane.add(lblConta);
		
		txtConta = new JFormattedTextField();
		txtConta.setEditable(false);
		txtConta.setColumns(10);
		txtConta.setBounds(252, 281, 138, 20);
		contentPane.add(txtConta);
	}
	
	private void habilitarCampos(boolean habilita) {
		txtNome.setEditable(habilita);
		txtEndereco.setEditable(habilita);
		txtBairro.setEditable(habilita);
		txtCep.setEditable(habilita);
		txtCidade.setEditable(habilita);
		txtEmail.setEditable(habilita);
		txtUf.setEnabled(habilita);
		txtTelefone.setEditable(habilita);
	}
	
	private void limparCampos() {
		txtId.setText("");
		txtNome.setText("");
		txtEndereco.setText("");
		txtBairro.setText("");
		txtCep.setText("");
		txtCidade.setText("");
		txtEmail.setText("");
		txtUf.setSelectedIndex(-1);
		txtTelefone.setText("");
		txtAgencia.setText("");
		txtConta.setText("");
	}
	
	private boolean isCadastroValido() {
		if(camposEmBranco()) {
			JOptionPane.showMessageDialog(null, "Todos os campos são de preenchimento obrigatório!", "Erro: campos obrigatórios", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(!txtEmail.getText().matches("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$")) {
			JOptionPane.showMessageDialog(null, "E-mail inválido!", "Erro: validação de e-mail", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	private boolean camposEmBranco() {
		return txtNome.getText().isBlank() && txtEmail.getText().isBlank()
				|| txtCep.getText().replaceAll("-", "").isBlank()
				|| txtEndereco.getText().isBlank()
				|| txtBairro.getText().isBlank()
				|| txtCidade.getText().isBlank()
				|| txtTelefone.getText().isBlank();
	}
	protected JFormattedTextField getTxtAgencia() {
		return txtAgencia;
	}
	protected JFormattedTextField getTxtConta() {
		return txtConta;
	}
}
