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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SaqueAtmScreen extends JFrame {

	private JPanel contentPane;
	private JButton btnCinquenta;
	private JButton btnCem;
	private JButton btnR;
	private JButton btnTrezentos;
	private JButton btnOutroValor;
	private JLabel lblConta;
	private JLabel lblAgencia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaqueAtmScreen frame = new SaqueAtmScreen();
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
	public SaqueAtmScreen() {
		setTitle("SAQUE - ICARROS");
		setResizable(false);
		setBounds(100, 100, 425, 410);
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
		btnVoltar.setBounds(138, 321, 133, 32);
		contentPane.add(btnVoltar);
		
		JButton btnVinte = new JButton("R$ 20,00");
		btnVinte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fazerSaque(20d);
				irTelaPrincipal();
				setVisible(false);
				dispose();
			}
		});
		btnVinte.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVinte.setBounds(9, 141, 133, 32);
		contentPane.add(btnVinte);
		
		JLabel lblSaque = new JLabel("SAQUE");
		lblSaque.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSaque.setBounds(168, 91, 80, 23);
		contentPane.add(lblSaque);
		
		btnCinquenta = new JButton("R$ 50,00");
		btnCinquenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fazerSaque(50d);
				irTelaPrincipal();
				setVisible(false);
				dispose();
			}
		});
		btnCinquenta.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCinquenta.setBounds(9, 211, 133, 32);
		contentPane.add(btnCinquenta);
		
		btnCem = new JButton("R$ 100,00");
		btnCem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fazerSaque(100d);
				irTelaPrincipal();
				setVisible(false);
				dispose();
			}
		});
		btnCem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCem.setBounds(9, 278, 133, 32);
		contentPane.add(btnCem);
		
		btnR = new JButton("R$ 200,00");
		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fazerSaque(200d);
				irTelaPrincipal();
				setVisible(false);
				dispose();
			}
		});
		btnR.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnR.setBounds(261, 141, 133, 32);
		contentPane.add(btnR);
		
		btnTrezentos = new JButton("R$ 300,00");
		btnTrezentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fazerSaque(300d);
				irTelaPrincipal();
				setVisible(false);
				dispose();
			}
		});
		btnTrezentos.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTrezentos.setBounds(261, 211, 133, 32);
		contentPane.add(btnTrezentos);
		
		btnOutroValor = new JButton("Outro Valor");
		btnOutroValor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaqueAtmOutroValorScreen saqueAtmOutroValorScreen = new SaqueAtmOutroValorScreen();
				saqueAtmOutroValorScreen.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnOutroValor.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnOutroValor.setBounds(261, 278, 133, 32);
		contentPane.add(btnOutroValor);
		
		lblAgencia = new JLabel("AGÊNCA: null");
		lblAgencia.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAgencia.setBounds(143, 28, 118, 14);
		contentPane.add(lblAgencia);
		
		lblConta = new JLabel("CONTA: null");
		lblConta.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConta.setBounds(143, 53, 118, 14);
		contentPane.add(lblConta);
		carregaInformacoesUsuarioLogado();
	}
	
	public void carregaInformacoesUsuarioLogado() {
		lblAgencia.setText("AGÊNCA: " + CorrentistaLogado.getInstance().getAgencia());
		lblConta.setText("CONTA: " + CorrentistaLogado.getInstance().getConta());
	}
	
	private void fazerSaque(Double valor) {
		ITransacaoRepository repository = new TransacaoRepository();
		Correntista correntista = new Correntista();
		correntista.setId(CorrentistaLogado.getInstance().getId());
		Double saldoAnterior = repository.buscaSaldoCorrentista(correntista.getId());
		if(valor > saldoAnterior) {
			JOptionPane.showMessageDialog(null, "Saldo insuficiente para esta transação!", "Saldo insuficiente", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Double valorSaque = valor;
		int rows = repository.gravaTransacao(new Transacao(valorSaque, saldoAnterior, saldoAnterior - valorSaque, correntista));
		if(rows > 0 ) {
			JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!", "Saque: sucesso!", JOptionPane.INFORMATION_MESSAGE);
			PrincipalATM principalATM = new PrincipalATM();
			principalATM.setVisible(true);
			setVisible(false);
			dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Erro ao registrar o saque", "Saque: erro!", JOptionPane.ERROR_MESSAGE);
		}
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
