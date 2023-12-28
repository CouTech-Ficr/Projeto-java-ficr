package com.telas.almoxarifado;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.connections.almoxarifado.DB_Connection;
import com.connections.almoxarifado.Data;
import com.connections.almoxarifado.HistoricoLoginDAO;

public class Tela_Login_Inicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField login;
	private JPasswordField senha;
	private JButton visiblepass;
	private JLabel logo;
	private JButton entrar;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JPanel panel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_Login_Inicial frame = new Tela_Login_Inicial();
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
	public Tela_Login_Inicial() {
		setBackground(Color.WHITE);
		setResizable(true);
		setTitle("Login");
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0,1680,1050 );
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(505, 165, 573, 492);
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(null);
		
		logo = new JLabel("");
		logo.setBackground(Color.WHITE);
		logo.setIcon(new ImageIcon("src/img/LogoElis.png"));
		logo.setBounds(230, 51, 149, 144);
		panel.add(logo);
		
		login = new JTextField();
		login.setFont(new Font("Microsoft YaHei", Font.PLAIN, 17));
		login.setBounds(199, 223, 211, 45);
		panel.add(login);
		login.setColumns(10);
		
		senha = new JPasswordField();
		senha.setFont(new Font("Dialog", Font.PLAIN, 17));
		senha.setEchoChar('\u25cf');
		senha.addKeyListener(new KeyAdapter() {
			

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					 String matricula = login.getText();
				        char[] senhaChars = senha.getPassword();
				        String senha = new String(senhaChars);

				        try (Connection connection = DB_Connection.get_connection()) {
				            String nomeUsuario = Data.autenticarUsuario(connection, matricula, senha);

				            if (nomeUsuario != null) {
				                // Autenticação bem-sucedida
				                HistoricoLoginDAO.registrarLogin(connection, matricula);
				                dispose();
				                Tela_inicial telaInicial = new Tela_inicial(matricula, nomeUsuario);
				                telaInicial.inicializarTela(nomeUsuario, matricula);

				                
				            } else {
				                // Autenticação falhou
				                JOptionPane.showMessageDialog(null, "Matrícula ou senha inválidas");
				            }

				            // Limpar o array de caracteres da senha após o uso
				            java.util.Arrays.fill(senhaChars, ' ');
				        } catch (SQLException ex) {
				            ex.printStackTrace();
				        }
			    
				}
			}
		});
		senha.setBounds(199, 294, 211, 45);
		panel.add(senha);
		
		entrar = new JButton("Entrar");
		entrar.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		entrar.setForeground(new Color(255, 255, 255));
		entrar.setBackground(new Color(0, 165, 170));
		entrar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String matricula = login.getText();
		        char[] senhaChars = senha.getPassword();
		        String senha = new String(senhaChars);

		        try (Connection connection = DB_Connection.get_connection()) {
		            String nomeUsuario = Data.autenticarUsuario(connection, matricula, senha);

		            if (nomeUsuario != null) {
		                // Autenticação bem-sucedida
		                HistoricoLoginDAO.registrarLogin(connection, matricula);
		                dispose();
		                Tela_inicial telaInicial = new Tela_inicial(matricula, nomeUsuario);
		                telaInicial.inicializarTela(nomeUsuario, matricula);

		              
		            } else {
		                // Autenticação falhou
		                JOptionPane.showMessageDialog(null, "Matrícula ou senha inválidas");
		            }

		            // Limpar o array de caracteres da senha após o uso
		            java.util.Arrays.fill(senhaChars, ' ');
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		});

		entrar.setBounds(245, 389, 118, 45);
		panel.add(entrar);
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("src/img/login.png"));
		lblNewLabel_1.setBounds(152, 223, 41, 45);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon("src/img/password.png"));
		lblNewLabel_1_1.setBounds(152, 301, 50, 38);
		panel.add(lblNewLabel_1_1);
		
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
		visiblepass.setBounds(414, 301, 41, 33);
		panel.add(visiblepass);
		
		lblNewLabel = new JLabel("ProSync Innovations ©");
		lblNewLabel.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblNewLabel.setBounds(12, 12, 142, 16);
		panel.add(lblNewLabel);
		
		lblNewLabel_4 = new JLabel("Matrícula");
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblNewLabel_4.setBounds(199, 196, 85, 33);
		panel.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Senha");
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblNewLabel_5.setBounds(199, 269, 85, 33);
		panel.add(lblNewLabel_5);
		
		lblNewLabel_2 = new JLabel("ProSync Innovations ©");
		lblNewLabel_2.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblNewLabel_2.setBounds(12, 12, 142, 16);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("ProSync Innovations ©");
		lblNewLabel_3.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblNewLabel_3.setBounds(12, 777, 142, 16);
		contentPane.add(lblNewLabel_3);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 90));
		panel_1.setBounds(0, 0, 1900, 920);
		contentPane.add(panel_1);

		initComplementos();
	}
	public void initComplementos() {
		this.setLocationRelativeTo(null);
	}
}
