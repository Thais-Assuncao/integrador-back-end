package br.com.itau.correntista.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.itau.correntista.views.atm.LoginAtmScreen;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IcarrosPrincipalScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IcarrosPrincipalScreen frame = new IcarrosPrincipalScreen();
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
	public IcarrosPrincipalScreen() {
		setTitle("ICARROS - TRABALHO");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 250, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGestao = new JButton("GESTÃO");
		btnGestao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginScreen loginScreen = new LoginScreen();
				loginScreen.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnGestao.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGestao.setBounds(144, 54, 145, 57);
		contentPane.add(btnGestao);
		
		JButton btnCorrentista = new JButton("CORRENTISTA");
		btnCorrentista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginAtmScreen loginScreen = new LoginAtmScreen();
				loginScreen.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnCorrentista.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCorrentista.setBounds(122, 139, 189, 57);
		contentPane.add(btnCorrentista);
	}
}
