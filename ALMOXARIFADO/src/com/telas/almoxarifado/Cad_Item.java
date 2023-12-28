package com.telas.almoxarifado;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class Cad_Item extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextPane textPane;
	private JScrollPane scrollPane;
	private JButton btncadastro;
	private JLabel lblNewLabel_2;
	private JTextField sku;
	private JTextField ean;
	private JTextField item;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JButton btnapenasUm;
	private JButton btnvarios;
	private JButton btnvoltar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cad_Item frame = new Cad_Item();
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
	public Cad_Item() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/img/caixa-de-entrega.ico"));
		setTitle("Cadastro de Itens");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 750);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblnome = new JLabel("Sku, Ean, Nome");
		lblnome.setFont(new Font("Microsoft JhengHei", Font.BOLD, 15));
		lblnome.setBounds(12, 194, 136, 24);
		lblnome.setVisible(false);
		contentPane.add(lblnome);
		
		btncadastro = new JButton("CADASTRAR");
		btncadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(scrollPane.isVisible()) {
					if(textPane.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Preencha corretamente!","ATENÇÃO",JOptionPane.WARNING_MESSAGE);
						return;
					}else {
						cadastrarvariosProduto();
						ean.setText("");
						sku.setText("");
						item.setText("");
					}
					
				}else {
					if(sku.getText().equals("") || ean.getText().equals("") || item.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Preencha corretamente!","ATENÇÃO",JOptionPane.WARNING_MESSAGE);
						return;
					}else {
						cadastrarUmProduto();
						textPane.setText("");
					}
					
				}
				
			}
		});
		btncadastro.setVisible(false);
		btncadastro.setForeground(Color.WHITE);
		btncadastro.setBackground(new Color(0, 250, 154));
		btncadastro.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		btncadastro.setBounds(328, 568, 136, 62);
		contentPane.add(btncadastro);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("src/img/sualogo.png"));
		logo.setBounds(328, 43, 149, 144);
		contentPane.add(logo);
		
		lblNewLabel = new JLabel("ProSync Innovations ©");
		lblNewLabel.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblNewLabel.setBounds(12, 12, 142, 16);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("ProSync Innovations ©");
		lblNewLabel_1.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblNewLabel_1.setBounds(644, 685, 142, 16);
		contentPane.add(lblNewLabel_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 218, 762, 305);
		scrollPane.setVisible(false);
		contentPane.add(scrollPane);
		
		textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		textPane.setBackground(SystemColor.inactiveCaption);
		
		btnapenasUm = new JButton("Apenas um");
		btnapenasUm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnvarios.setVisible(false);
				btnapenasUm.setVisible(false);
				lblNewLabel_2.setVisible(false);
				lblNewLabel_3.setVisible(true);
				lblNewLabel_4.setVisible(true);
				lblNewLabel_5.setVisible(true);
				ean.setVisible(true);
				sku.setVisible(true);
				item.setVisible(true);
				btncadastro.setVisible(true);
				btnvoltar.setVisible(true);
			}
		});
		btnapenasUm.setForeground(SystemColor.windowText);
		btnapenasUm.setBackground(SystemColor.inactiveCaption);
		btnapenasUm.setFont(new Font("Microsoft YaHei", Font.BOLD, 17));
		btnapenasUm.setBounds(288, 264, 229, 78);
		contentPane.add(btnapenasUm);
		
		btnvarios = new JButton("Dois ou vários");
		btnvarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnvarios.setVisible(false);
				btnapenasUm.setVisible(false);
				lblnome.setVisible(true);
				scrollPane.setVisible(true);
				lblNewLabel_2.setVisible(false);
				lblNewLabel_3.setVisible(false);
				lblNewLabel_4.setVisible(false);
				lblNewLabel_5.setVisible(false);
				ean.setVisible(false);
				sku.setVisible(false);
				item.setVisible(false);
				btncadastro.setVisible(true);
				btnvoltar.setVisible(true);
			}
		});
		btnvarios.setForeground(SystemColor.windowText);
		btnvarios.setBackground(SystemColor.inactiveCaption);
		btnvarios.setFont(new Font("Microsoft YaHei", Font.BOLD, 17));
		btnvarios.setBounds(288, 367, 229, 78);
		contentPane.add(btnvarios);
		
		lblNewLabel_2 = new JLabel("Cadastro de Item");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Microsoft JhengHei", Font.BOLD, 20));
		lblNewLabel_2.setBounds(266, 194, 274, 48);
		contentPane.add(lblNewLabel_2);
		
		sku = new JTextField();
		sku.setBackground(SystemColor.text);
		sku.setVisible(false);
		sku.setBounds(288, 225, 229, 43);
		contentPane.add(sku);
		sku.setColumns(10);
		
		ean = new JTextField();
		ean.setVisible(false);
		ean.setBackground(SystemColor.text);
		ean.setColumns(10);
		ean.setBounds(288, 299, 229, 43);
		contentPane.add(ean);
		
		item = new JTextField();
		item.setColumns(10);
		item.setVisible(false);
		item.setBackground(SystemColor.text);
		item.setBounds(288, 367, 229, 43);
		contentPane.add(item);
		
		lblNewLabel_3 = new JLabel("Item");
		lblNewLabel_3.setVisible(false);
		lblNewLabel_3.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblNewLabel_3.setBounds(288, 350, 96, 16);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Ean");
		lblNewLabel_4.setVisible(false);
		lblNewLabel_4.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblNewLabel_4.setBounds(288, 280, 96, 16);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Sku");
		lblNewLabel_5.setVisible(false);
		lblNewLabel_5.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblNewLabel_5.setBounds(288, 200, 96, 31);
		contentPane.add(lblNewLabel_5);
		
		btnvoltar = new JButton("Voltar");
		btnvoltar.setVisible(false);
		btnvoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnvarios.setVisible(true);
				btnapenasUm.setVisible(true);
				lblNewLabel_2.setVisible(true);
				lblNewLabel_3.setVisible(false);
				lblNewLabel_4.setVisible(false);
				lblNewLabel_5.setVisible(false);
				ean.setVisible(false);
				sku.setVisible(false);
				item.setVisible(false);
				btncadastro.setVisible(false);
				btnvoltar.setVisible(false);
				lblnome.setVisible(false);
				scrollPane.setVisible(false);
				ean.setText("");
				sku.setText("");
				item.setText("");
				textPane.setText("");
			}
		});
		btnvoltar.setBackground(SystemColor.inactiveCaption);
		btnvoltar.setFont(new Font("Microsoft JhengHei", Font.BOLD, 15));
		btnvoltar.setBounds(12, 35, 84, 36);
		contentPane.add(btnvoltar);
		
		
		initComplementos();
	}
	public void initComplementos() {
		this.setLocationRelativeTo(null);
	}
	public void verificar() {
		
	}
	private void cadastrarvariosProduto() {
	    String inputText = textPane.getText();

	    // Dividir a string em linhas
	    String[] linhas = inputText.split("\\n");

	    // Conectar ao banco de dados e inserir os dados
	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/almoxarifado", "root", "$3nh4")) {
	        String querySelect = "SELECT COUNT(*) FROM codigoproduto WHERE sku = ? AND ean = ?";
	        String queryInsert = "INSERT INTO codigoproduto (sku, ean, nomeprod) VALUES (?, ?, ?)";

	        try (PreparedStatement selectStatement = connection.prepareStatement(querySelect);
	             PreparedStatement insertStatement = connection.prepareStatement(queryInsert)) {

	            for (String linha : linhas) {
	                // Dividir cada linha em colunas usando o espaço como delimitador
	                String[] colunas = linha.split("\\s+", 3);

	                // Verificar se há pelo menos três colunas
	                if (colunas.length >= 3) {
	                    String sku = colunas[0];
	                    String ean = colunas[1];
	                    String nome = colunas[2];

	                    // Verificar se o item já existe no banco de dados
	                    selectStatement.setString(1, sku);
	                    selectStatement.setString(2, ean);
	                    ResultSet resultSet = selectStatement.executeQuery();
	                    resultSet.next();
	                    int rowCount = resultSet.getInt(1);

	                    if (rowCount > 0) {
	                        JOptionPane.showMessageDialog(null, "SKU e/ou EAN já estão cadastrados.", "Erro", JOptionPane.ERROR_MESSAGE);
	                        return;
	                    } else {
	                        // Inserir no banco de dados
	                        insertStatement.setString(1, sku);
	                        insertStatement.setString(2, ean);
	                        insertStatement.setString(3, nome);
	                        insertStatement.executeUpdate();
	                    }
	                }
	            }
	            textPane.setText("");
	            JOptionPane.showMessageDialog(this, "Produtos cadastrados com sucesso!");
	           
	            
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Erro de banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	}
	private void cadastrarUmProduto() {
	    String skuprod = sku.getText();
	    String eanprod = ean.getText();
	    String nomeprod = item.getText();

	    // Conectar ao banco de dados e verificar se já existe um registro com o mesmo EAN e SKU
	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/almoxarifado", "root", "$3nh4")) {
	        String querySelect = "SELECT COUNT(*) FROM codigoproduto WHERE sku = ? AND ean = ?";
	        try (PreparedStatement selectStatement = connection.prepareStatement(querySelect)) {
	            selectStatement.setString(1, skuprod);
	            selectStatement.setString(2, eanprod);
	            ResultSet resultSet = selectStatement.executeQuery();
	            resultSet.next();
	            int rowCount = resultSet.getInt(1);

	            if (rowCount > 0) {
	                JOptionPane.showMessageDialog(this, "SKU e/ou EAN já estão cadastrados.", "Erro", JOptionPane.ERROR_MESSAGE);
	                return;
	            } else {
	                // Se não houver um registro com o mesmo EAN e SKU, proceda com a inserção
	                String queryInsert = "INSERT INTO codigoproduto (sku, ean, nomeprod) VALUES (?, ?, ?)";
	                try (PreparedStatement insertStatement = connection.prepareStatement(queryInsert)) {
	                    insertStatement.setString(1, skuprod);
	                    insertStatement.setString(2, eanprod);
	                    insertStatement.setString(3, nomeprod);

	                    // Inserir no banco de dados
	                    insertStatement.executeUpdate();
	                    ean.setText("");
	    				sku.setText("");
	    				item.setText("");
	                    JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
	                }
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Erro de banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	}
}