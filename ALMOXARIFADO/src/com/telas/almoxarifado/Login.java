package com.telas.almoxarifado;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;



import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.WindowStateListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Window.Type;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField login;
	private JPasswordField senha;
	private JButton btnvisua;
	private JButton entrar1;
	private JButton btntabela;
	private String pass;
	private String log;
	private JLabel lblNewLabel_2;
	private JButton entrar;
	private JButton visiblepass;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setType(Type.UTILITY);
		setResizable(false);
		
		setTitle("Desbloquear Histórico");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 512, 357);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		login = new JTextField();
		login.setEnabled(false);
		login.setText("Almoxarife");
		login.setEditable(false);
		login.setToolTipText("Login");
		login.setBounds(153, 91, 207, 38);
		contentPane.add(login);
		login.setColumns(10);

		senha = new JPasswordField();
		senha.setEchoChar('\u25cf');
		senha.addKeyListener(new KeyAdapter() {
			

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					pass = senha.getText();
					log = login.getText();

					if(log.equals("Almoxarife") && pass.equals("") || !pass.equals("almoxarife@23")) {
						JOptionPane.showMessageDialog(null, "Preencha Corretamente!");
						
					}else {
						new telahistorico().setVisible(true);
						dispose();
					}

				}
			}
		});
		senha.setBounds(153, 150, 207, 38);
		contentPane.add(senha);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("src/img/login.png"));
		lblNewLabel.setBounds(111, 97, 32, 32);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("src/img/password.png"));
		lblNewLabel_1.setBounds(111, 150, 50, 38);
		contentPane.add(lblNewLabel_1);

		// botao de logar com tratamento caso de senha incorreta
		entrar = new JButton("LOGIN");
		entrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				log = login.getText().toUpperCase();
				pass = senha.getText();

				if (log.equals("Almoxarife") && pass.isEmpty() || !pass.equals("almoxarife@23")) {
					JOptionPane.showMessageDialog(null, "Preencha Corretamente!");
					
				} else {
					new telahistorico().setVisible(true);
					dispose();
					
				}
			}
		});
		entrar.setFont(new Font("Verdana", Font.BOLD, 13));
		entrar.setForeground(Color.WHITE);
		entrar.setBackground(new Color(135, 206, 250));
		entrar.setBounds(206, 226, 94, 38);
		contentPane.add(entrar);
		
		lblNewLabel_2 = new JLabel("CouTech ©");
		lblNewLabel_2.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_2.setFont(new Font("Verdana", Font.ITALIC, 12));
		lblNewLabel_2.setBounds(10, 281, 105, 13);
		contentPane.add(lblNewLabel_2);
		
		visiblepass = new JButton("");
		visiblepass.setBackground(Color.WHITE);
		visiblepass.setIcon(new ImageIcon("src/img/view.png"));
		visiblepass.addActionListener(new ActionListener() {
			private boolean passwordVisible = false;
			public void actionPerformed(ActionEvent e) {
				passwordVisible = !passwordVisible;
                if (passwordVisible) {
                    senha.setEchoChar((char) 0); // Mostrar a senha
                    visiblepass.setIcon(new ImageIcon("src/img/hide.png"));
                } else {
                    senha.setEchoChar('\u25cf'); // Esconder a senha
                    visiblepass.setIcon(new ImageIcon("src/img/view.png"));
                }
				
			}
		});
		visiblepass.setBounds(366, 152, 32, 36);
		contentPane.add(visiblepass);

		
		// telacentralizada
		initComplementos();

	}

	public void initComplementos() {
		this.setLocationRelativeTo(null);
	}
}
