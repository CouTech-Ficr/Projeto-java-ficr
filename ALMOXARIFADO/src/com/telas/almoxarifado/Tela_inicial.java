package com.telas.almoxarifado;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import com.connections.almoxarifado.DB_Connection;
import com.connections.almoxarifado.Data;
import com.connections.almoxarifado.Data.CampoBD;
import com.connections.almoxarifado.HistoricoLoginDAO;
import javax.swing.SwingConstants;

public class Tela_inicial {

	private JFrame frmTelaInicial;
	private JTextField cod_nome;
	private JComboBox tipo;
	private JSpinner qtd;
	private JTextField codbarrasOUsku;
	private JTextField lblcodbarras;
	private JTextField lblNomeRequisitante;
	private JTextField data1;
	private SimpleDateFormat dat;
	private JButton btnfinalizar;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JSpinner qtd1;
	private JSpinner qtd2;
	private JSpinner qtd3;
	private JSpinner qtd4;
	private JButton btnlimpar;
	private JButton btnbloq1;
	private JButton btnbloq2;
	private JButton btnbloq3;
	private JButton btnbloq4;
	private JButton btnDesbloquear;
	private boolean isBloqueado2;
	private boolean isBloqueado1;
	private boolean isBloqueado3;
	private boolean isBloqueado4;
	private boolean isDesbloq2;
	private boolean isDesbloq1;
	private boolean isDesbloq3;
	private boolean isDesbloq4;
	private JButton btnNewButton;
	private JLabel lblNewLabel_2;
	private JComboBox textaplicacao;
	private JTextField login;
	private JButton btnsair;
	private String copy = "JeanLM TI ©";
	private JLabel lblNewLabel_2_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_inicial window = new Tela_inicial(null, null);
					window.frmTelaInicial.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tela_inicial(String nomeUsuario, String matricula) {
		initialize(nomeUsuario, matricula);
	}

	public void inicializarTela(String nomeUsuario, String matricula) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_inicial window = new Tela_inicial(nomeUsuario, matricula);
					window.frmTelaInicial.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param nomeUsuario
	 */
	private void initialize(String nomeUsuario, String matricula) {

		frmTelaInicial = new JFrame();
		frmTelaInicial.setBackground(Color.WHITE);
		frmTelaInicial.setResizable(false);
		frmTelaInicial.setTitle("Tela Inicial");
		frmTelaInicial.setOpacity(1.0f);
		frmTelaInicial.setFont(new Font("Verdana", Font.PLAIN, 15));
		frmTelaInicial.getContentPane().setBackground(SystemColor.window);
		frmTelaInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTelaInicial.setExtendedState(JFrame.MAXIMIZED_BOTH);

		cod_nome = new JTextField("");
		cod_nome.setBounds(617, 99, 197, 42);
		cod_nome.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String codigoDigitado = cod_nome.getText();
				if (codigoDigitado.isEmpty()) {
					lblNomeRequisitante.setText("Nome Requisitante");
				} else {
					boolean encontrou = false;
					String sql = "SELECT * FROM requisitantes WHERE matricula = ?";
					try (Connection connection = new DB_Connection().get_connection();
							PreparedStatement statement = connection.prepareStatement(sql)) {
						statement.setString(1, codigoDigitado);
						ResultSet resultSet = statement.executeQuery();
						while (resultSet.next()) {
							lblNomeRequisitante.setText(resultSet.getString("nome"));
							encontrou = true;
							break; // Encerra o loop assim que encontrar o código correspondente
						}
					} catch (SQLException ex) {
						System.out.println("Erro na leitura do banco de dados ...");
					}
					if (!encontrou) {
						lblNomeRequisitante.setText("Não cadastrado");
					}
				}
			}
		});

		cod_nome.setFont(new Font("Verdana", Font.PLAIN, 15));
		cod_nome.setDropMode(DropMode.INSERT);
		cod_nome.setToolTipText("");
		cod_nome.setColumns(10);

		tipo = new JComboBox();
		tipo.setBackground(new Color(255, 255, 255));
		tipo.setBounds(845, 94, 197, 47);
		tipo.setModel(new DefaultComboBoxModel(new String[] { "(Nenhum)", "Retirada", "Reposição" }));
		tipo.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(846, 68, 173, 30);
		lblTipo.setFont(new Font("Verdana", Font.PLAIN, 16));

		int minValue = 1;
		int maxValue = 999999;
		int initialValue = 1;

		SpinnerNumberModel spinnerModel1 = new SpinnerNumberModel(initialValue, minValue, maxValue, 1);
		SpinnerNumberModel spinnerModel2 = new SpinnerNumberModel(initialValue, minValue, maxValue, 1);
		SpinnerNumberModel spinnerModel3 = new SpinnerNumberModel(initialValue, minValue, maxValue, 1);
		SpinnerNumberModel spinnerModel4 = new SpinnerNumberModel(initialValue, minValue, maxValue, 1);
		SpinnerNumberModel spinnerModel5 = new SpinnerNumberModel(initialValue, minValue, maxValue, 1);

		qtd = new JSpinner(spinnerModel1);
		qtd.setBounds(845, 197, 115, 42);
		qtd.setFont(new Font("Verdana", Font.PLAIN, 20));

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(845, 167, 173, 30);
		lblQuantidade.setFont(new Font("Verdana", Font.PLAIN, 16));

		lblNomeRequisitante = new JTextField("Nome Requisitante");
		lblNomeRequisitante.setBounds(617, 68, 197, 30);
		lblNomeRequisitante.setEditable(false);
		lblNomeRequisitante.setForeground(SystemColor.desktop);
		lblNomeRequisitante.setBackground(SystemColor.window);
		lblNomeRequisitante.setFont(new Font("Verdana", Font.PLAIN, 16));

		codbarrasOUsku = new JTextField();
		codbarrasOUsku.setBounds(617, 197, 197, 42);
		codbarrasOUsku.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String codigoDigitado = codbarrasOUsku.getText();
				if (codigoDigitado.isEmpty()) {
					lblcodbarras.setText("Cód. Barras/SKU");
					btnfinalizar.setEnabled(false);
				} else {
					boolean encontrou = false;
					String sql = "SELECT * FROM codigoproduto WHERE ? IN (sku, ean)";
					try (Connection connection = new DB_Connection().get_connection();
							PreparedStatement statement = connection.prepareStatement(sql)) {
						statement.setString(1, codigoDigitado);
						ResultSet resultSet = statement.executeQuery();
						while (resultSet.next()) {
							lblcodbarras.setText(resultSet.getString("nomeprod"));
							encontrou = true;
							verificarVazio();
							break; // Encerra o loop assim que encontrar o código correspondente
						}
					} catch (SQLException ex) {
						System.out.println("Erro na leitura do banco de dados ...");
					}
					if (!encontrou) {
						btnfinalizar.setEnabled(false);
						lblcodbarras.setText("Não cadastrado");
					}
				}
			}
		});
		codbarrasOUsku.setFont(new Font("Verdana", Font.PLAIN, 15));
		codbarrasOUsku.setDropMode(DropMode.INSERT);
		codbarrasOUsku.setColumns(10);

		lblcodbarras = new JTextField("Cód. Barras/SKU");
		lblcodbarras.setBounds(617, 168, 197, 30);
		lblcodbarras.setEditable(false);
		lblcodbarras.setFont(new Font("Verdana", Font.PLAIN, 16));

		btnfinalizar = new JButton("Finalizar - F10");
		btnfinalizar.setBounds(720, 635, 185, 55);
		KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0);
		btnfinalizar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "F10");
		btnfinalizar.getActionMap().put("F10", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				performAction();
			}
		});

		btnfinalizar.setFocusable(true);
		btnfinalizar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (cod_nome.getText().equals("") || codbarrasOUsku.getText().equals("")
						|| tipo.getSelectedItem().equals("(Nenhum)") || lblcodbarras.getText().equals("Cód. Barras/SKU")
						|| lblcodbarras.getText().equals("Não cadastrado")
						|| textField.getText().equals("Não cadastrado")
						|| textField_2.getText().equals("Não cadastrado")
						|| textField_4.getText().equals("Não cadastrado")
						|| textField_6.getText().equals("Não cadastrado")
						|| lblNomeRequisitante.getText().equals("Não cadastrado")
						|| textaplicacao.getSelectedItem().equals("-Selecione-")) {
					JOptionPane.showMessageDialog(null, "Preencha corretamente");
					return;
				} else {
					if (!lblcodbarras.getText().equals("Cód. Barras/SKU")
							|| !lblcodbarras.getText().equals("Não cadastrado")) {
						String codnome = cod_nome.getText();
						String nome = lblNomeRequisitante.getText();
						Object tp = tipo.getSelectedItem();
						String tpString = (String) tp;
						String qt1 = qtd.getValue().toString();
						String codbarrassku = codbarrasOUsku.getText(); // codigo do item
						String barrasku = lblcodbarras.getText(); // nome do item
						Object app = textaplicacao.getSelectedItem();
						Date dataAtual = new Date();
						// Formata a data para o formato 'yyyy-MM-dd'
						SimpleDateFormat formatadorMySQL = new SimpleDateFormat("yyyy-MM-dd");
						String dataFormatadaMySQL = formatadorMySQL.format(dataAtual);
						Data.gravarDados("RETIRADAEREPOSICAO1", new CampoBD("codnome", codnome),
								new CampoBD("nome", nome), new CampoBD("tipo", tpString), new CampoBD("qtd1", qt1),
								new CampoBD("data", dataFormatadaMySQL), new CampoBD("codbarrassku1", codbarrassku),
								new CampoBD("barrassku1", barrasku), new CampoBD("app", app));

					}
					if (isDesbloq1) {
						String codnome = cod_nome.getText();
						String nome = lblNomeRequisitante.getText();
						Object tp = tipo.getSelectedItem();
						String tpString = (String) tp;
						String qt2 = qtd1.getValue().toString();
						String codbarrassku1 = textField_1.getText(); // codigo do item
						String barrasku1 = textField.getText(); // nome do item
						Object app = textaplicacao.getSelectedItem();
						Date dataAtual = new Date();
						// Formata a data para o formato 'yyyy-MM-dd'
						SimpleDateFormat formatadorMySQL = new SimpleDateFormat("yyyy-MM-dd");
						String dataFormatadaMySQL = formatadorMySQL.format(dataAtual);

						Data.gravarDados("RETIRADAEREPOSICAO2", new CampoBD("codnome", codnome),
								new CampoBD("nome", nome), new CampoBD("tipo", tpString), new CampoBD("qtd2", qt2),
								new CampoBD("data", dataFormatadaMySQL), new CampoBD("codbarrassku2", codbarrassku1),
								new CampoBD("barrassku2", barrasku1), new CampoBD("app", app));

						isDesbloq1 = false;

					}
					if (isDesbloq2) {
						String codnome = cod_nome.getText();
						String nome = lblNomeRequisitante.getText();
						Object tp = tipo.getSelectedItem();
						String tpString = (String) tp;
						String qt3 = qtd2.getValue().toString();
						String codbarrassku2 = textField_3.getText(); // codigo do item
						String barrasku2 = textField_2.getText(); // nome do item
						Object app = textaplicacao.getSelectedItem();
						Date dataAtual = new Date();
						// Formata a data para o formato 'yyyy-MM-dd'
						SimpleDateFormat formatadorMySQL = new SimpleDateFormat("yyyy-MM-dd");
						String dataFormatadaMySQL = formatadorMySQL.format(dataAtual);

						Data.gravarDados("RETIRADAEREPOSICAO3", new CampoBD("codnome", codnome),
								new CampoBD("nome", nome), new CampoBD("tipo", tpString), new CampoBD("qtd3", qt3),
								new CampoBD("data", dataFormatadaMySQL), new CampoBD("codbarrassku3", codbarrassku2),
								new CampoBD("barrassku3", barrasku2), new CampoBD("app", app));

						isDesbloq2 = false;
					}
					if (isDesbloq3) {
						String codnome = cod_nome.getText();
						String nome = lblNomeRequisitante.getText();
						Object tp = tipo.getSelectedItem();
						String tpString = (String) tp;
						String qt4 = qtd3.getValue().toString();
						String codbarrassku3 = textField_5.getText(); // codigo do item
						String barrasku3 = textField_4.getText(); // nome do item
						Object app = textaplicacao.getSelectedItem();
						Date dataAtual = new Date();
						// Formata a data para o formato 'yyyy-MM-dd'
						SimpleDateFormat formatadorMySQL = new SimpleDateFormat("yyyy-MM-dd");
						String dataFormatadaMySQL = formatadorMySQL.format(dataAtual);

						Data.gravarDados("RETIRADAEREPOSICAO4", new CampoBD("codnome", codnome),
								new CampoBD("nome", nome), new CampoBD("tipo", tpString), new CampoBD("qtd4", qt4),
								new CampoBD("data", dataFormatadaMySQL), new CampoBD("codbarrassku4", codbarrassku3),
								new CampoBD("barrassku4", barrasku3), new CampoBD("app", app));

						isDesbloq3 = false;
					}
					if (isDesbloq4) {
						String codnome = cod_nome.getText();
						String nome = lblNomeRequisitante.getText();
						Object tp = tipo.getSelectedItem();
						String tpString = (String) tp;
						String qt5 = qtd4.getValue().toString();
						String codbarrassku4 = textField_7.getText(); // codigo do item
						String barrasku4 = textField_6.getText(); // nome do item
						Object app = textaplicacao.getSelectedItem();
						Date dataAtual = new Date();
						// Formata a data para o formato 'yyyy-MM-dd'
						SimpleDateFormat formatadorMySQL = new SimpleDateFormat("yyyy-MM-dd");
						String dataFormatadaMySQL = formatadorMySQL.format(dataAtual);

						Data.gravarDados("RETIRADAEREPOSICAO5", new CampoBD("codnome", codnome),
								new CampoBD("nome", nome), new CampoBD("tipo", tpString), new CampoBD("qtd5", qt5),
								new CampoBD("data", dataFormatadaMySQL), new CampoBD("codbarrassku5", codbarrassku4),
								new CampoBD("barrassku5", barrasku4), new CampoBD("app", app));

						isDesbloq4 = false;
					}
					bloqueartodos();
					JOptionPane.showMessageDialog(null, "ENVIADO!");
					apagar();

				}

			}

		});
		btnfinalizar.setBackground(new Color(0, 165, 170));
		btnfinalizar.setForeground(new Color(255, 255, 255));
		btnfinalizar.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 18));

		JButton btnHistrico = new JButton("  Histórico");
		btnHistrico.setHorizontalAlignment(SwingConstants.LEFT);
		btnHistrico.setIcon(new ImageIcon("src/img/historico.png"));
		btnHistrico.setBounds(10, 413, 189, 52);
		btnHistrico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
			}
		});
		btnHistrico.setForeground(Color.WHITE);
		btnHistrico.setBackground(new Color(0, 165, 170));
		btnHistrico.setFont(new Font("Microsoft JhengHei", Font.BOLD, 13));

		Date dataAtual = new Date();
		// Formata a data para o formato 'yyyy-MM-dd'
		SimpleDateFormat formatadorMySQL = new SimpleDateFormat("yyyy-MM-dd");

		String dataFormatadaMySQL = formatadorMySQL.format(dataAtual);

		// Declare a variável dat como final para que ela seja acessível dentro do Timer
		final SimpleDateFormat dat = new SimpleDateFormat("dd/MM/yyyy");

		data1 = new JFormattedTextField(dat);
		data1.setHorizontalAlignment(SwingConstants.CENTER);
		data1.setForeground(Color.BLACK);
		data1.setBackground(Color.WHITE);
		data1.setBounds(10, 479, 189, 30);
		data1.setEditable(false);
		data1.setFont(new Font("Microsoft JhengHei", Font.BOLD, 16));
		data1.setColumns(10);
		frmTelaInicial.getContentPane().setLayout(null);
		frmTelaInicial.getContentPane().setLayout(null);
		frmTelaInicial.getContentPane().add(cod_nome);
		frmTelaInicial.getContentPane().add(tipo);
		frmTelaInicial.getContentPane().add(lblTipo);
		frmTelaInicial.getContentPane().add(qtd);
		frmTelaInicial.getContentPane().add(lblQuantidade);
		frmTelaInicial.getContentPane().add(lblNomeRequisitante);
		frmTelaInicial.getContentPane().add(codbarrasOUsku);
		frmTelaInicial.getContentPane().add(lblcodbarras);
		frmTelaInicial.getContentPane().add(btnfinalizar);
		frmTelaInicial.getContentPane().add(btnHistrico);
		frmTelaInicial.getContentPane().add(data1);

		textField = new JTextField("Cód. Barras/SKU");
		textField.setFont(new Font("Verdana", Font.PLAIN, 16));
		textField.setEditable(false);
		textField.setBounds(617, 251, 197, 30);
		frmTelaInicial.getContentPane().add(textField);

		textField_1 = new JTextField();
		textField_1.setText("Desbloquei para digitar");
		textField_1.setEnabled(false);
		textField_1.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String codigoDigitado = textField_1.getText();
				if (codigoDigitado.isEmpty()) {
					textField.setText("Cód. Barras/SKU");
					btnfinalizar.setEnabled(false);
				} else {
					boolean encontrou = false;
					String sql = "SELECT * FROM codigoproduto WHERE ? IN (sku, ean)";
					try (Connection connection = new DB_Connection().get_connection();
							PreparedStatement statement = connection.prepareStatement(sql)) {
						statement.setString(1, codigoDigitado);
						ResultSet resultSet = statement.executeQuery();
						while (resultSet.next()) {
							textField.setText(resultSet.getString("nomeprod"));
							encontrou = true;
							verificarVazio();
							break; // Encerra o loop assim que encontrar o código correspondente
						}
					} catch (SQLException ex) {
						System.out.println("Erro na leitura do banco de dados ...");
					}
					if (!encontrou) {
						btnfinalizar.setEnabled(false);
						textField.setText("Não cadastrado");
					}
				}
			}
		});

		textField_1.setFont(new Font("Verdana", Font.PLAIN, 15));
		textField_1.setDropMode(DropMode.INSERT);
		textField_1.setColumns(10);
		textField_1.setBounds(617, 280, 197, 42);
		frmTelaInicial.getContentPane().add(textField_1);

		textField_2 = new JTextField("Cód. Barras/SKU");
		textField_2.setFont(new Font("Verdana", Font.PLAIN, 16));
		textField_2.setEditable(false);
		textField_2.setBounds(617, 335, 197, 30);
		frmTelaInicial.getContentPane().add(textField_2);

		textField_3 = new JTextField();
		textField_3.setText("Desbloquei para digitar");
		textField_3.setEnabled(false);
		textField_3.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String codigoDigitado = textField_3.getText();
				if (codigoDigitado.isEmpty()) {
					textField_2.setText("Cód. Barras/SKU");
					btnfinalizar.setEnabled(false);
				} else {
					boolean encontrou = false;
					String sql = "SELECT * FROM codigoproduto WHERE ? IN (sku, ean)";
					try (Connection connection = new DB_Connection().get_connection();
							PreparedStatement statement = connection.prepareStatement(sql)) {
						statement.setString(1, codigoDigitado);
						ResultSet resultSet = statement.executeQuery();
						while (resultSet.next()) {
							textField_2.setText(resultSet.getString("nomeprod"));
							encontrou = true;
							verificarVazio();
							break; // Encerra o loop assim que encontrar o código correspondente
						}
					} catch (SQLException ex) {
						System.out.println("Erro na leitura do banco de dados ...");
					}
					if (!encontrou) {
						btnfinalizar.setEnabled(false);
						textField_2.setText("Não cadastrado");
					}
				}
			}
		});
		textField_3.setFont(new Font("Verdana", Font.PLAIN, 15));
		textField_3.setDropMode(DropMode.INSERT);
		textField_3.setColumns(10);
		textField_3.setBounds(617, 364, 197, 42);
		frmTelaInicial.getContentPane().add(textField_3);

		qtd1 = new JSpinner(spinnerModel2);
		qtd1.setEnabled(false);
		qtd1.setFont(new Font("Verdana", Font.PLAIN, 20));
		qtd1.setBounds(845, 281, 115, 42);
		frmTelaInicial.getContentPane().add(qtd1);

		JLabel lblQuantidade_1 = new JLabel("Quantidade");
		lblQuantidade_1.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblQuantidade_1.setBounds(845, 251, 173, 30);
		frmTelaInicial.getContentPane().add(lblQuantidade_1);

		textField_4 = new JTextField("Cód. Barras/SKU");
		textField_4.setFont(new Font("Verdana", Font.PLAIN, 16));
		textField_4.setEditable(false);
		textField_4.setBounds(617, 418, 197, 30);
		frmTelaInicial.getContentPane().add(textField_4);

		textField_5 = new JTextField();
		textField_5.setText("Desbloquei para digitar");
		textField_5.setEnabled(false);
		textField_5.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String codigoDigitado = textField_5.getText();
				if (codigoDigitado.isEmpty()) {
					textField_4.setText("Cód. Barras/SKU");
					btnfinalizar.setEnabled(false);
				} else {
					boolean encontrou = false;
					String sql = "SELECT * FROM codigoproduto WHERE ? IN (sku, ean)";
					try (Connection connection = new DB_Connection().get_connection();
							PreparedStatement statement = connection.prepareStatement(sql)) {
						statement.setString(1, codigoDigitado);
						ResultSet resultSet = statement.executeQuery();
						while (resultSet.next()) {
							textField_4.setText(resultSet.getString("nomeprod"));
							encontrou = true;
							verificarVazio();
							break; // Encerra o loop assim que encontrar o código correspondente
						}
					} catch (SQLException ex) {
						System.out.println("Erro na leitura do banco de dados ...");
					}
					if (!encontrou) {
						btnfinalizar.setEnabled(false);
						textField_4.setText("Não cadastrado");
					}
				}
			}
		});
		textField_5.setFont(new Font("Verdana", Font.PLAIN, 15));
		textField_5.setDropMode(DropMode.INSERT);
		textField_5.setColumns(10);
		textField_5.setBounds(617, 447, 197, 42);
		frmTelaInicial.getContentPane().add(textField_5);

		qtd2 = new JSpinner(spinnerModel3);
		qtd2.setEnabled(false);
		qtd2.setFont(new Font("Verdana", Font.PLAIN, 20));
		qtd2.setBounds(845, 364, 115, 42);
		frmTelaInicial.getContentPane().add(qtd2);

		JLabel lblQuantidade_1_1 = new JLabel("Quantidade");
		lblQuantidade_1_1.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblQuantidade_1_1.setBounds(845, 334, 173, 30);
		frmTelaInicial.getContentPane().add(lblQuantidade_1_1);

		textField_6 = new JTextField("Cód. Barras/SKU");
		textField_6.setFont(new Font("Verdana", Font.PLAIN, 16));
		textField_6.setEditable(false);
		textField_6.setBounds(617, 518, 197, 30);
		frmTelaInicial.getContentPane().add(textField_6);

		textField_7 = new JTextField();
		textField_7.setText("Desbloquei para digitar");
		textField_7.setEnabled(false);
		textField_7.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String codigoDigitado = textField_7.getText();
				if (codigoDigitado.isEmpty()) {
					textField_6.setText("Cód. Barras/SKU");
					btnfinalizar.setEnabled(false);
				} else {
					boolean encontrou = false;
					String sql = "SELECT * FROM codigoproduto WHERE ? IN (sku, ean)";
					try (Connection connection = new DB_Connection().get_connection();
							PreparedStatement statement = connection.prepareStatement(sql)) {
						statement.setString(1, codigoDigitado);
						ResultSet resultSet = statement.executeQuery();
						while (resultSet.next()) {
							textField_6.setText(resultSet.getString("nomeprod"));
							encontrou = true;
							verificarVazio();
							break; // Encerra o loop assim que encontrar o código correspondente
						}
					} catch (SQLException ex) {
						System.out.println("Erro na leitura do banco de dados ...");
					}
					if (!encontrou) {
						btnfinalizar.setEnabled(false);
						textField_6.setText("Não cadastrado");
					}
				}
			}
		});
		textField_7.setFont(new Font("Verdana", Font.PLAIN, 15));
		textField_7.setDropMode(DropMode.INSERT);
		textField_7.setColumns(10);
		textField_7.setBounds(617, 547, 197, 42);
		frmTelaInicial.getContentPane().add(textField_7);

		qtd3 = new JSpinner(spinnerModel4);
		qtd3.setEnabled(false);
		qtd3.setFont(new Font("Verdana", Font.PLAIN, 20));
		qtd3.setBounds(845, 448, 115, 42);
		frmTelaInicial.getContentPane().add(qtd3);

		JLabel lblQuantidade_1_1_1 = new JLabel("Quantidade");
		lblQuantidade_1_1_1.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblQuantidade_1_1_1.setBounds(845, 418, 173, 30);
		frmTelaInicial.getContentPane().add(lblQuantidade_1_1_1);

		qtd4 = new JSpinner(spinnerModel5);
		qtd4.setEnabled(false);
		qtd4.setFont(new Font("Verdana", Font.PLAIN, 20));
		qtd4.setBounds(846, 548, 115, 42);
		frmTelaInicial.getContentPane().add(qtd4);

		JLabel lblQuantidade_1_1_1_1 = new JLabel("Quantidade");
		lblQuantidade_1_1_1_1.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblQuantidade_1_1_1_1.setBounds(846, 518, 173, 30);
		frmTelaInicial.getContentPane().add(lblQuantidade_1_1_1_1);

		isBloqueado1 = false;
		isBloqueado2 = false;
		isBloqueado3 = false;
		isBloqueado4 = false;
		btnbloq1 = new JButton("");
		btnbloq1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isBloqueado1) {
					btnbloq1.setIcon(new ImageIcon("src/img/cadeado-fechado.png"));
					btnbloq1.setBackground(new Color(255, 0, 0));
					textField_1.setEnabled(false);
					qtd1.setEnabled(false);
					isBloqueado1 = false;
					textField_1.setText("Desbloquei para digitar");
					isDesbloq1 = false;
				} else {
					btnbloq1.setIcon(new ImageIcon("src/img/cadeado-aberto.png"));
					btnbloq1.setBackground(new Color(173, 255, 47));
					textField_1.setEnabled(true);
					qtd1.setEnabled(true);
					isBloqueado1 = true;
					isDesbloq1 = true;
					textField_1.setText("");
					btnfinalizar.setEnabled(false);
				}
			}
		});
		btnbloq1.setBackground(new Color(255, 0, 0));
		btnbloq1.setIcon(new ImageIcon("src/img/cadeado-fechado.png"));
		btnbloq1.setBounds(571, 291, 24, 24);
		frmTelaInicial.getContentPane().add(btnbloq1);

		btnbloq2 = new JButton("");
		btnbloq2.setIcon(new ImageIcon("src/img/cadeado-fechado.png"));
		btnbloq2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isBloqueado2) {
					btnbloq2.setIcon(new ImageIcon("src/img/cadeado-fechado.png"));
					btnbloq2.setBackground(new Color(255, 0, 0));
					textField_3.setEnabled(false);
					textField_3.setText("Desbloquei para digitar");
					qtd2.setEnabled(false);
					isBloqueado2 = false;
					isDesbloq2 = false;
				} else {
					btnbloq2.setIcon(new ImageIcon("src/img/cadeado-aberto.png"));
					btnbloq2.setBackground(new Color(173, 255, 47));
					textField_3.setEnabled(true);
					qtd2.setEnabled(true);
					isBloqueado2 = true;
					isDesbloq2 = true;
					textField_3.setText("");
					btnfinalizar.setEnabled(false);
				}
			}
		});
		btnbloq2.setBackground(new Color(255, 0, 0));
		btnbloq2.setBounds(571, 373, 24, 24);
		frmTelaInicial.getContentPane().add(btnbloq2);

		btnbloq3 = new JButton("");
		btnbloq3.setIcon(new ImageIcon("src/img/cadeado-fechado.png"));
		btnbloq3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isBloqueado3) {
					btnbloq3.setIcon(new ImageIcon("src/img/cadeado-fechado.png"));
					btnbloq3.setBackground(new Color(255, 0, 0));
					textField_5.setEnabled(false);
					textField_5.setText("Desbloquei para digitar");
					qtd3.setEnabled(false);
					isBloqueado3 = false;
					isDesbloq3 = false;
				} else {
					btnbloq3.setIcon(new ImageIcon("src/img/cadeado-aberto.png"));
					btnbloq3.setBackground(new Color(173, 255, 47));
					textField_5.setEnabled(true);
					qtd3.setEnabled(true);
					isBloqueado3 = true;
					isDesbloq3 = true;
					textField_5.setText("");
					btnfinalizar.setEnabled(false);
				}
			}
		});
		btnbloq3.setBackground(new Color(255, 0, 0));
		btnbloq3.setBounds(571, 456, 24, 24);
		frmTelaInicial.getContentPane().add(btnbloq3);

		btnbloq4 = new JButton("");
		btnbloq4.setIcon(new ImageIcon("src/img/cadeado-fechado.png"));
		btnbloq4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isBloqueado4) {
					btnbloq4.setIcon(new ImageIcon("src/img/cadeado-fechado.png"));
					btnbloq4.setBackground(new Color(255, 0, 0));
					textField_7.setEnabled(false);
					textField_7.setText("Desbloquei para digitar");
					qtd4.setEnabled(false);
					isBloqueado4 = false;
					isDesbloq4 = false;
				} else {
					btnbloq4.setIcon(new ImageIcon("src/img/cadeado-aberto.png"));
					btnbloq4.setBackground(new Color(173, 255, 47));
					textField_7.setEnabled(true);
					qtd4.setEnabled(true);
					isBloqueado4 = true;
					isDesbloq4 = true;
					textField_7.setText("");
					btnfinalizar.setEnabled(false);
				}
			}
		});
		btnbloq4.setBackground(new Color(255, 0, 0));
		btnbloq4.setBounds(571, 556, 24, 24);
		frmTelaInicial.getContentPane().add(btnbloq4);

		btnlimpar = new JButton("Limpar - F7");
		btnlimpar.setHorizontalAlignment(SwingConstants.LEFT);
		KeyStroke keyStroke2 = KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0);
		btnlimpar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke2, "F7");
		btnlimpar.getActionMap().put("F7", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				f7limpar();
			}
		});

		btnlimpar.setFocusable(true);
		btnlimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apagar();
				bloqueartodos();
			}
		});
		btnlimpar.setIcon(new ImageIcon("src/img/borracha.png"));
		btnlimpar.setForeground(Color.WHITE);
		btnlimpar.setFont(new Font("Microsoft JhengHei", Font.BOLD, 12));
		btnlimpar.setBackground(new Color(0, 165, 170));
		btnlimpar.setBounds(10, 83, 189, 52);
		frmTelaInicial.getContentPane().add(btnlimpar);

		btnDesbloquear = new JButton("Desbloquear todos");
		btnDesbloquear.setHorizontalAlignment(SwingConstants.LEFT);
		btnDesbloquear.setIcon(new ImageIcon("src/img/cadeado-desbloqueado.png"));
		btnDesbloquear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desbloqueartodos();

			}
		});
		btnDesbloquear.setForeground(Color.WHITE);
		btnDesbloquear.setFont(new Font("Microsoft JhengHei", Font.BOLD, 12));
		btnDesbloquear.setBackground(new Color(0, 165, 170));
		btnDesbloquear.setBounds(10, 276, 189, 52);
		frmTelaInicial.getContentPane().add(btnDesbloquear);

		btnNewButton = new JButton("Bloquear todos");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setIcon(new ImageIcon("src/img/cadeado.png"));
		btnNewButton.setFont(new Font("Microsoft JhengHei", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bloqueartodos();
			}
		});
		btnNewButton.setBackground(new Color(0, 165, 170));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(10, 345, 189, 52);
		frmTelaInicial.getContentPane().add(btnNewButton);

		JLabel lblNewLabel = new JLabel(copy);
		lblNewLabel.setFont(new Font("Dialog", Font.ITALIC, 14));
		lblNewLabel.setBounds(329, 18, 173, 24);
		frmTelaInicial.getContentPane().add(lblNewLabel);

		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("src/img/EmblemaElis.png"));
		logo.setBounds(20, 573, 350, 255);
		frmTelaInicial.getContentPane().add(logo);

		lblNewLabel_2 = new JLabel(copy);
		lblNewLabel_2.setFont(new Font("Dialog", Font.ITALIC, 14));
		lblNewLabel_2.setBounds(10, 521, 153, 24);
		frmTelaInicial.getContentPane().add(lblNewLabel_2);

		lblNewLabel_2_1 = new JLabel(copy);
		lblNewLabel_2_1.setBounds(1316, 770, 173, 36);
		frmTelaInicial.getContentPane().add(lblNewLabel_2_1);
		lblNewLabel_2_1.setFont(new Font("Dialog", Font.ITALIC, 14));

		JLabel lblaplicação = new JLabel("Aplicação");
		lblaplicação.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblaplicação.setBounds(403, 73, 113, 21);
		frmTelaInicial.getContentPane().add(lblaplicação);

		textaplicacao = new JComboBox();
		textaplicacao.setModel(new DefaultComboBoxModel(new String[] {"-Selecione-", "Producao", "T.I", "Area Critica", "Hotelaria", "Hospitalar", "Cliente  Hospital", "Cliente  Hotel", "Expedicao", "Logistica", "Administrativo", "Seguranca do Trabalho", "RH", "Superintendencia"}));
		textaplicacao.setBackground(Color.WHITE);
		textaplicacao.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		textaplicacao.setBounds(401, 100, 197, 42);
		frmTelaInicial.getContentPane().add(textaplicacao);

		JButton btncadcolaborador = new JButton("Novo colaborador");
		btncadcolaborador.setIcon(new ImageIcon("src/img/novouser.png"));
		btncadcolaborador.setHorizontalAlignment(SwingConstants.LEFT);
		btncadcolaborador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login2().setVisible(true);
			}
		});
		btncadcolaborador.setForeground(Color.WHITE);
		btncadcolaborador.setFont(new Font("Microsoft JhengHei", Font.BOLD, 12));
		btncadcolaborador.setBackground(new Color(0, 165, 170));
		btncadcolaborador.setBounds(10, 145, 189, 52);
		frmTelaInicial.getContentPane().add(btncadcolaborador);

		JButton btncadprodut = new JButton("Novo item");
		btncadprodut.setIcon(new ImageIcon("src/img/newproduct.png"));
		btncadprodut.setHorizontalAlignment(SwingConstants.LEFT);
		btncadprodut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login3().setVisible(true);
			}
		});
		btncadprodut.setForeground(Color.WHITE);
		btncadprodut.setFont(new Font("Microsoft JhengHei", Font.BOLD, 12));
		btncadprodut.setBackground(new Color(0, 165, 170));
		btncadprodut.setBounds(10, 208, 189, 52);
		frmTelaInicial.getContentPane().add(btncadprodut);

		login = new JTextField(nomeUsuario);
		login.setBackground(Color.WHITE);
		login.setForeground(Color.BLACK);
		login.setFont(new Font("Microsoft JhengHei", Font.BOLD, 10));
		login.setEditable(false);
		login.setBounds(12, 12, 173, 30);
		frmTelaInicial.getContentPane().add(login);
		login.setColumns(10);

		btnsair = new JButton("Sair");
		btnsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Connection connection = DB_Connection.get_connection()) {

					switch (JOptionPane.showConfirmDialog(null, "Deseja sair ?", "Desconecta-se",
							JOptionPane.YES_NO_OPTION)) {
					case 0:
						// Registra o logoff ao clicar em "Sair"
						HistoricoLoginDAO.registrarLogoff(connection, matricula);
						// Fecha a janela
						frmTelaInicial.dispose();
						new Tela_Login_Inicial().setVisible(true);

					case 1:
						return;

					}

				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnsair.setForeground(Color.WHITE);
		btnsair.setBackground(Color.RED);
		btnsair.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
		btnsair.setBounds(189, 11, 60, 30);
		frmTelaInicial.getContentPane().add(btnsair);

		Timer timer2 = new Timer(1000, new ActionListener() {
			Date dataAtual = new Date();

			public void actionPerformed(ActionEvent e) {
				// Obtém a data atual do sistema
				Date novaDataa = new Date();

				// Verifica se a data mudou
				if (!novaDataa.equals(dataAtual)) {
					// Atualiza a data do campo JFormattedTextField
					dataAtual = novaDataa;
					String dataFormatada = dat.format(dataAtual);
					data1.setText(dataFormatada);
				}
			}
		});

		timer2.start();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int largura = 750;
		int altura = 500;
		frmTelaInicial.setSize(895, 655);
		int x = (screenSize.width - largura) / 2;
		int y = (screenSize.height - altura) / 2;
		frmTelaInicial.setLocation(x, y);

	}

	public void apagar() {
		cod_nome.setText("");
		codbarrasOUsku.setText("");
		tipo.setSelectedItem("(Nenhum)");
		textaplicacao.setSelectedItem("-Selecione-");
		qtd.setValue(1);
		qtd1.setValue(1);
		qtd2.setValue(1);
		qtd3.setValue(1);
		qtd4.setValue(1);
		lblcodbarras.setText("Cód. Barras/SKU");
		lblNomeRequisitante.setText("Nome Requisitante");
		textField_1.setText("Desbloquei para digitar");
		textField.setText("Cód. Barras/SKU");
		textField_3.setText("Desbloquei para digitar");
		textField_2.setText("Cód. Barras/SKU");
		textField_4.setText("Cód. Barras/SKU");
		textField_5.setText("Desbloquei para digitar");
		textField_6.setText("Cód. Barras/SKU");
		textField_7.setText("Desbloquei para digitar");
	}

	private void bloqueartodos() {
		textField_1.setEnabled(false);
		textField_3.setEnabled(false);
		textField_5.setEnabled(false);
		textField_7.setEnabled(false);
		qtd1.setEnabled(false);
		qtd2.setEnabled(false);
		qtd3.setEnabled(false);
		qtd4.setEnabled(false);
		btnbloq1.setIcon(new ImageIcon("src/img/cadeado-fechado.png"));
		btnbloq2.setIcon(new ImageIcon("src/img/cadeado-fechado.png"));
		btnbloq3.setIcon(new ImageIcon("src/img/cadeado-fechado.png"));
		btnbloq4.setIcon(new ImageIcon("src/img/cadeado-fechado.png"));
		textField_1.setText("Desbloquei para digitar");
		textField_3.setText("Desbloquei para digitar");
		textField_5.setText("Desbloquei para digitar");
		textField_7.setText("Desbloquei para digitar");
		btnbloq1.setBackground(new Color(255, 0, 0));
		btnbloq2.setBackground(new Color(255, 0, 0));
		btnbloq3.setBackground(new Color(255, 0, 0));
		btnbloq4.setBackground(new Color(255, 0, 0));
		isBloqueado1 = false;
		isBloqueado2 = false;
		isBloqueado3 = false;
		isBloqueado4 = false;
		isDesbloq1 = false;
		isDesbloq2 = false;
		isDesbloq3 = false;
		isDesbloq4 = false;
		btnfinalizar.setEnabled(true);
	}

	private void desbloqueartodos() {
		textField_1.setEnabled(true);
		textField_3.setEnabled(true);
		textField_5.setEnabled(true);
		textField_7.setEnabled(true);
		textField_1.setText("");
		textField_3.setText("");
		textField_5.setText("");
		textField_7.setText("");
		qtd1.setEnabled(true);
		qtd2.setEnabled(true);
		qtd3.setEnabled(true);
		qtd4.setEnabled(true);
		btnbloq1.setIcon(new ImageIcon("src/img/cadeado-aberto.png"));
		btnbloq2.setIcon(new ImageIcon("src/img/cadeado-aberto.png"));
		btnbloq3.setIcon(new ImageIcon("src/img/cadeado-aberto.png"));
		btnbloq4.setIcon(new ImageIcon("src/img/cadeado-aberto.png"));
		btnbloq1.setBackground(new Color(173, 255, 47));
		btnbloq2.setBackground(new Color(173, 255, 47));
		btnbloq3.setBackground(new Color(173, 255, 47));
		btnbloq4.setBackground(new Color(173, 255, 47));
		isBloqueado1 = true;
		isBloqueado2 = true;
		isBloqueado3 = true;
		isBloqueado4 = true;
		isDesbloq1 = true;
		isDesbloq2 = true;
		isDesbloq3 = true;
		isDesbloq4 = true;
		btnfinalizar.setEnabled(false);
	}

	private void verificarVazio() {
		if (codbarrasOUsku.getText().equals("") || textField_1.getText().equals("") || textField_3.getText().equals("")
				|| textField_5.getText().equals("") || textField_7.getText().equals("")) {
			return;
		} else {
			btnfinalizar.setEnabled(true);
		}
	}

	private void performAction() {
		if (cod_nome.getText().equals("") || codbarrasOUsku.getText().equals("")
				|| tipo.getSelectedItem().equals("(Nenhum)") || lblcodbarras.getText().equals("Cód. Barras/SKU")
				|| lblcodbarras.getText().equals("Não cadastrado") || textField.getText().equals("Não cadastrado")
				|| textField_2.getText().equals("Não cadastrado") || textField_4.getText().equals("Não cadastrado")
				|| textField_6.getText().equals("Não cadastrado")
				|| lblNomeRequisitante.getText().equals("Não cadastrado") || textaplicacao.getSelectedItem().equals("-Selecione-")) {
			JOptionPane.showMessageDialog(null, "Preencha corretamente");
			return;
		} else {
			if (!lblcodbarras.getText().equals("Cód. Barras/SKU") || !lblcodbarras.getText().equals("Não cadastrado")) {
				String codnome = cod_nome.getText();
				String nome = lblNomeRequisitante.getText();
				Object tp = tipo.getSelectedItem();
				String tpString = (String) tp;
				String qt1 = qtd.getValue().toString();
				String codbarrassku = codbarrasOUsku.getText(); // codigo do item
				String barrasku = lblcodbarras.getText(); // nome do item
				Object app = textaplicacao.getSelectedItem();
				Date dataAtual = new Date();
				// Formata a data para o formato 'yyyy-MM-dd'
				SimpleDateFormat formatadorMySQL = new SimpleDateFormat("yyyy-MM-dd");
				String dataFormatadaMySQL = formatadorMySQL.format(dataAtual);
				Data.gravarDados("RETIRADAEREPOSICAO1", new CampoBD("codnome", codnome), new CampoBD("nome", nome),
						new CampoBD("tipo", tpString), new CampoBD("qtd1", qt1),
						new CampoBD("data", dataFormatadaMySQL), new CampoBD("codbarrassku1", codbarrassku),
						new CampoBD("barrassku1", barrasku), new CampoBD("app", app));

			}
			if (isDesbloq1) {
				String codnome = cod_nome.getText();
				String nome = lblNomeRequisitante.getText();
				Object tp = tipo.getSelectedItem();
				String tpString = (String) tp;
				String qt2 = qtd1.getValue().toString();
				String codbarrassku1 = textField_1.getText(); // codigo do item
				String barrasku1 = textField.getText(); // nome do item
				Object app = textaplicacao.getSelectedItem();
				Date dataAtual = new Date();
				// Formata a data para o formato 'yyyy-MM-dd'
				SimpleDateFormat formatadorMySQL = new SimpleDateFormat("yyyy-MM-dd");
				String dataFormatadaMySQL = formatadorMySQL.format(dataAtual);

				Data.gravarDados("RETIRADAEREPOSICAO2", new CampoBD("codnome", codnome), new CampoBD("nome", nome),
						new CampoBD("tipo", tpString), new CampoBD("qtd2", qt2),
						new CampoBD("data", dataFormatadaMySQL), new CampoBD("codbarrassku2", codbarrassku1),
						new CampoBD("barrassku2", barrasku1), new CampoBD("app", app));

				isDesbloq1 = false;

			}
			if (isDesbloq2) {
				String codnome = cod_nome.getText();
				String nome = lblNomeRequisitante.getText();
				Object tp = tipo.getSelectedItem();
				String tpString = (String) tp;
				String qt3 = qtd2.getValue().toString();
				String codbarrassku2 = textField_3.getText(); // codigo do item
				String barrasku2 = textField_2.getText(); // nome do item
				Object app = textaplicacao.getSelectedItem();
				Date dataAtual = new Date();
				// Formata a data para o formato 'yyyy-MM-dd'
				SimpleDateFormat formatadorMySQL = new SimpleDateFormat("yyyy-MM-dd");
				String dataFormatadaMySQL = formatadorMySQL.format(dataAtual);

				Data.gravarDados("RETIRADAEREPOSICAO3", new CampoBD("codnome", codnome), new CampoBD("nome", nome),
						new CampoBD("tipo", tpString), new CampoBD("qtd3", qt3),
						new CampoBD("data", dataFormatadaMySQL), new CampoBD("codbarrassku3", codbarrassku2),
						new CampoBD("barrassku3", barrasku2), new CampoBD("app", app));

				isDesbloq2 = false;
			}
			if (isDesbloq3) {
				String codnome = cod_nome.getText();
				String nome = lblNomeRequisitante.getText();
				Object tp = tipo.getSelectedItem();
				String tpString = (String) tp;
				String qt4 = qtd3.getValue().toString();
				String codbarrassku3 = textField_5.getText(); // codigo do item
				String barrasku3 = textField_4.getText(); // nome do item
				Object app = textaplicacao.getSelectedItem();
				Date dataAtual = new Date();
				// Formata a data para o formato 'yyyy-MM-dd'
				SimpleDateFormat formatadorMySQL = new SimpleDateFormat("yyyy-MM-dd");
				String dataFormatadaMySQL = formatadorMySQL.format(dataAtual);

				Data.gravarDados("RETIRADAEREPOSICAO4", new CampoBD("codnome", codnome), new CampoBD("nome", nome),
						new CampoBD("tipo", tpString), new CampoBD("qtd4", qt4),
						new CampoBD("data", dataFormatadaMySQL), new CampoBD("codbarrassku4", codbarrassku3),
						new CampoBD("barrassku4", barrasku3), new CampoBD("app", app));

				isDesbloq3 = false;
			}
			if (isDesbloq4) {
				String codnome = cod_nome.getText();
				String nome = lblNomeRequisitante.getText();
				Object tp = tipo.getSelectedItem();
				String tpString = (String) tp;
				String qt5 = qtd4.getValue().toString();
				String codbarrassku4 = textField_7.getText(); // codigo do item
				String barrasku4 = textField_6.getText(); // nome do item
				Object app = textaplicacao.getSelectedItem();
				Date dataAtual = new Date();
				// Formata a data para o formato 'yyyy-MM-dd'
				SimpleDateFormat formatadorMySQL = new SimpleDateFormat("yyyy-MM-dd");
				String dataFormatadaMySQL = formatadorMySQL.format(dataAtual);

				Data.gravarDados("RETIRADAEREPOSICAO5", new CampoBD("codnome", codnome), new CampoBD("nome", nome),
						new CampoBD("tipo", tpString), new CampoBD("qtd5", qt5),
						new CampoBD("data", dataFormatadaMySQL), new CampoBD("codbarrassku5", codbarrassku4),
						new CampoBD("barrassku5", barrasku4), new CampoBD("app", app));

				isDesbloq4 = false;
			}
			bloqueartodos();
			JOptionPane.showMessageDialog(null, "ENVIADO!");
			apagar();
		}
	}

	private void f7limpar() {
		apagar();
	}
}
