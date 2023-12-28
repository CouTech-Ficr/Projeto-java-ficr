package com.telas.almoxarifado;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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

public class Login2 extends JFrame {

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
					Login2 frame = new Login2();
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
	public Login2() {
		setType(Type.UTILITY);
		setResizable(false);
		
		setTitle("Desbloquear Cadastro de colaborador");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 512, 357);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		login = new JTextField();
		login.setEnabled(true);
		login.setText("");
		login.setEditable(true);
		login.setToolTipText("Login");
		login.setBounds(153, 91, 207, 38);
		contentPane.add(login);
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
							dispose();
							new Cad_Colaborador().setVisible(true);

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
		    	String matricula = login.getText();
				char[] senhaChars = senha.getPassword();
				String senha = new String(senhaChars);

				try (Connection connection = DB_Connection.get_connection()) {
					String nomeUsuario = Data.autenticarUsuario(connection, matricula, senha);

					if (nomeUsuario != null) {
						// Autenticação bem-sucedida
						dispose();
						new Cad_Colaborador().setVisible(true);

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

		entrar.setFont(new Font("Microsoft YaHei", Font.BOLD, 13));
		entrar.setForeground(Color.WHITE);
		entrar.setBackground(new Color(135, 206, 250));
		entrar.setBounds(206, 226, 94, 38);
		contentPane.add(entrar);
		
		lblNewLabel_2 = new JLabel("ProSync Innovations ©");
		lblNewLabel_2.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_2.setFont(new Font("Verdana", Font.ITALIC, 12));
		lblNewLabel_2.setBounds(10, 281, 166, 13);
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
