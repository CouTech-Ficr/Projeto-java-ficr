package com.telas.almoxarifado;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.connections.almoxarifado.DB_Connection;
import com.connections.almoxarifado.Data;

public class Cad_Colaborador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField txtmatricula;
	private static JTextField txtnome;
	private JTable requisitantes;
	public static DefaultTableModel model;
	private JButton btncad;
	private JButton btndeletar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cad_Colaborador frame = new Cad_Colaborador();
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
	public Cad_Colaborador() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/img/caixa-de-entrega.ico"));
		setTitle("Cadastro de colaboradores");
		addWindowListener(new WindowAdapter() {

			@Override

			public void windowOpened(WindowEvent e) {
				String[] column = { "Matrícula", "Nome" };
				ArrayList<String[]> dados = Data.lerDadosrequisitantes("requisitantes");
				DefaultTableModel model = (DefaultTableModel) requisitantes.getModel();
				model.setColumnIdentifiers(column);
				for (String[] linha : dados) {
					model.addRow(linha);
				}
			}

		});
		setBounds(100, 100, 822, 731);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtmatricula = new JTextField();
		txtmatricula.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		txtmatricula.setBounds(285, 33, 206, 44);
		contentPane.add(txtmatricula);
		txtmatricula.setColumns(10);
		// Limita o JTextField a 10 caracteres usando DocumentListener
        txtmatricula.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                limitarCaracteres();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                limitarCaracteres();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // not needed for plain text fields
            }
        });
 

   
		
		txtnome = new JTextField();
		txtnome.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		txtnome.setColumns(10);
		txtnome.setBounds(285, 106, 206, 44);
		contentPane.add(txtnome);

		JLabel lnlmatricula = new JLabel("Matrícula");
		lnlmatricula.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lnlmatricula.setBounds(285, 10, 72, 27);
		contentPane.add(lnlmatricula);

		JLabel lblnome = new JLabel("Nome");
		lblnome.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblnome.setBounds(285, 82, 72, 27);
		contentPane.add(lblnome);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(191, 207, 404, 466);
		contentPane.add(scrollPane);

		requisitantes = new JTable();
		requisitantes.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 12));
		requisitantes.setForeground(Color.BLACK);
		requisitantes.setBackground(Color.WHITE);
		model = new DefaultTableModel();
		requisitantes.setModel(model);
		requisitantes.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(requisitantes);

		JLabel logo = new JLabel("");
		logo.setBounds(12, 12, 149, 144);
		logo.setIcon(new ImageIcon("src/img/EmblemaElis.png"));
		contentPane.add(logo);

		btncad = new JButton("Cadastrar");
		btncad.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String matricula1 = txtmatricula.getText();
		        String nome1 = txtnome.getText();

		        // Verificar se a matrícula já existe no banco de dados
		        if (registroExistenteNoBanco(matricula1) || registroExistenteNaTabela(matricula1)) {
		            JOptionPane.showMessageDialog(null, "Erro: Matrícula já cadastrada .");
		            return; // Não prosseguir com a inserção no banco
		        }else if(txtmatricula.getText().equals("") || txtnome.getText().equals("")){
		        	JOptionPane.showMessageDialog(null, "Campos vazios não são permitidos.","Aviso",JOptionPane.WARNING_MESSAGE);
		        	return;
		        }else {
		        	 // Se não existir, realizar a inserção no banco
			        gravarDadosRequisitantes(matricula1, nome1);

			        
		        }

		       
		    }
		});
		btncad.setForeground(Color.WHITE);
		btncad.setBackground(new Color(0, 165, 170));
		btncad.setFont(new Font("Microsoft JhengHei", Font.BOLD, 10));
		btncad.setBounds(344, 160, 85, 37);
		contentPane.add(btncad);

		btndeletar = new JButton("");
		btndeletar.setIcon(new ImageIcon("src/img/lixeiraM.png"));
		btndeletar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {

			int i = requisitantes.getSelectedRow();
			if (i >= 0) {
				switch (JOptionPane.showConfirmDialog(null, "Deletar registro ?", "Apagar dados:",
						JOptionPane.YES_NO_OPTION)) {
				case 0:
					// apagando a linha no arquivo
					apagando();
					((DefaultTableModel) requisitantes.getModel()).removeRow(i);
					JOptionPane.showMessageDialog(null, "Removido!","Sucesso",JOptionPane.OK_OPTION);

				case 1:
					return;

				}

			} else {
				JOptionPane.showMessageDialog(null, "SELECIONE UMA LINHA NA TABELA!","ATENÇÃO",JOptionPane.WARNING_MESSAGE);
			}

		}
	});
		btndeletar.setForeground(Color.WHITE);
		btndeletar.setBackground(new Color(0, 165, 170));
		btndeletar.setFont(new Font("Microsoft JhengHei", Font.BOLD, 13));
		btndeletar.setBounds(605, 210, 92, 44);
		contentPane.add(btndeletar);
		
		JLabel lblNewLabel = new JLabel("JeanLM TI©");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 12));
		lblNewLabel.setBounds(671, 10, 116, 21);
		contentPane.add(lblNewLabel);
		initComplementos();
	}
	public void initComplementos() {
		this.setLocationRelativeTo(null);
	}
	// Grava um texto no banco de dados
	public static void gravarDadosRequisitantes(String matricula, String nome) {
		try (Connection connection = new DB_Connection().get_connection()) {
			String sql = "INSERT INTO requisitantes (matricula, nome) VALUES (?, ?)";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, matricula);
				statement.setString(2, nome);

				
				int linhasAfetadas = statement.executeUpdate();

				if (linhasAfetadas > 0) {
					String matricula1 = txtmatricula.getText();
			        String nome1 = txtnome.getText();
					// Adicione os novos dados como uma linha à JTable
			        Object[] valornovo = {matricula1, nome1};
			        model.addRow(valornovo);

			        // Notifique a JTable que o modelo foi alterado
			        model.fireTableDataChanged();
			        JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);
			        // Limpe os JTextFields
			        txtnome.setText("");
			        txtmatricula.setText("");
					
				} else {
					JOptionPane.showMessageDialog(null, "Falha ao inserir dados.", "Erro", JOptionPane.ERROR_MESSAGE);
				
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Matrícula inválida","Atenção",
						JOptionPane.WARNING_MESSAGE);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro de banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	// Método auxiliar para verificar se a matrícula já existe no banco de dados
	private boolean registroExistenteNoBanco(String matricula) {
	    try (Connection connection = new DB_Connection().get_connection()) {
	        String sql = "SELECT * FROM requisitantes WHERE matricula = ?";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, matricula);

	            try (ResultSet resultSet = statement.executeQuery()) {
	                return resultSet.next(); // Retorna true se a matrícula já existe
	            }
	        }
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Erro na execução da consulta SQL", "Erro", JOptionPane.ERROR_MESSAGE);
	        ex.printStackTrace();
	        return false;
	    }
	}

	// Método auxiliar para verificar se a matrícula já existe na tabela
	private boolean registroExistenteNaTabela(String matricula) {
	    for (int i = 0; i < model.getRowCount(); i++) {
	        if (matricula.equals(model.getValueAt(i, 0))) {
	            return true; 
	        }
	    }
	    return false; // Matrícula não existe na tabela
	}
	
	private void limitarCaracteres() {
	    int limite = 10;
	    if (txtmatricula.getText().length() > limite) {
	        SwingUtilities.invokeLater(() -> {
	            // Executar a alteração no EDT (Event Dispatch Thread)
	            txtmatricula.setText(txtmatricula.getText().substring(0, limite));
	        });
	    }
	}
	public void apagando() {
	    int index = requisitantes.getSelectedRow();
	    if (index >= 0) {
	         String matricula = requisitantes.getValueAt(index, 0).toString(); // Índice 0 para "id"
	        String primarykey = "matricula = " + matricula;
	        Data.deletarDadosRequisitantes("requisitantes",primarykey);
	       
	    
	    }
	}
}
