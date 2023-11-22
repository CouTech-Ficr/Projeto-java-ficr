package com.telas.almoxarifado;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.ImageIcon;
import java.awt.Toolkit;


public class telahistorico extends JFrame {

	private JPanel contentPane;
	private JTextField filtrocod;
	private JTable historicotable;
	private DefaultTableModel model;
	private JButton btnapagar;
	private JComboBox filtrotipo;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telahistorico frame = new telahistorico();
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
	public telahistorico() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/img/caixa-de-entrega.ico"));
		setTitle("Tela Histórico");
		addWindowListener(new WindowAdapter() {

		@Override

		public void windowOpened(WindowEvent e) {
		    String[] column = { "Cód.Req", "Nome Requisitante", "Tipo", "Quantidade", "Data", "Cód.Barras/Sku", "Item", "Aplicação"};
		    ArrayList<String[]> dados = Data.lerDados("retiradaereposicao1", "retiradaereposicao2", "retiradaereposicao3", "retiradaereposicao4", "retiradaereposicao5");
		    DefaultTableModel model = (DefaultTableModel) historicotable.getModel();
		    model.setColumnIdentifiers(column);
		    for (String[] linha : dados) {
		        model.addRow(linha);
		    }
		}

	});
	
		setBounds(100, 100, 1022, 694);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnapagar = new JButton("Deletar");
			btnapagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int i = historicotable.getSelectedRow();
				if (i >= 0) {
					switch (JOptionPane.showConfirmDialog(null, "Deletar registro ?", "Apagar dados:",
							JOptionPane.YES_NO_OPTION)) {
					case 0:
						// apagando a linha no arquivo
						apagando();
						((DefaultTableModel) historicotable.getModel()).removeRow(i);
						JOptionPane.showMessageDialog(null, "Removido!");

					case 1:
						return;

					}

				} else {
					JOptionPane.showMessageDialog(null, "SELECIONE UMA LINHA NA TABELA!");
				}

			}
		});
		btnapagar.setForeground(Color.WHITE);
		btnapagar.setBackground(Color.RED);
		btnapagar.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnapagar.setBounds(840, 56, 143, 53);
		contentPane.add(btnapagar);
		
		filtrocod = new JTextField();
		filtrocod.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarTabela();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarTabela();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarTabela();
            }
        });
		filtrocod.setFont(new Font("Verdana", Font.PLAIN, 16));
		filtrocod.setBounds(10, 108, 199, 30);
		contentPane.add(filtrocod);
		filtrocod.setColumns(10);
		
		filtrotipo = new JComboBox();
		filtrotipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	filtrarTabelaCombobox();
            }
        });
		filtrotipo.setForeground(Color.BLACK);
		filtrotipo.setBackground(Color.WHITE);
		filtrotipo.setModel(new DefaultComboBoxModel(new String[] {"", "Retirada", "Reposição"}));
		filtrotipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		filtrotipo.setBounds(240, 108, 148, 30);
		contentPane.add(filtrotipo);
		
		JLabel lblNewLabel = new JLabel("Filtrar por:");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 39, 185, 30);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 148, 988, 499);
		contentPane.add(scrollPane);
		
		historicotable = new JTable();
		scrollPane.setViewportView(historicotable);
		model = new DefaultTableModel();
		historicotable.setModel(model);
		
		JLabel lblSkuEan = new JLabel("SKU / EAN");
		lblSkuEan.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblSkuEan.setBounds(10, 79, 185, 30);
		contentPane.add(lblSkuEan);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblTipo.setBounds(240, 79, 185, 30);
		contentPane.add(lblTipo);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("src/img/logo_elis.png"));
		lblNewLabel_1.setBounds(385, 10, 222, 88);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("JeanLm TI ©");
		lblNewLabel_2.setBounds(904, 10, 79, 20);
		contentPane.add(lblNewLabel_2);
		
		
		initComplementos();
	}
	public void initComplementos() {
		this.setLocationRelativeTo(null);
	}
	public void apagando() {
	    int index = historicotable.getSelectedRow();
	    if (index >= 0) {
	        String id = historicotable.getValueAt(index, 0).toString(); // Supondo que o ID esteja na primeira coluna
	        Data.deletarDados("retiradaereposicao1", "codnome = " + id); // Substitua "codnome" pelo nome da coluna que você deseja usar como condição
	    }
	}

	private void filtrarTabela() {
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
        historicotable.setRowSorter(rowSorter);

        String textoFiltro = filtrocod.getText();
        if (textoFiltro.trim().length() == 4) {
            rowSorter.setRowFilter(null); // Remove o filtro se o campo de filtro estiver vazio
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter(textoFiltro, 4)); // Filtra pelo código
        }
    }
	 private void filtrarTabelaCombobox() {
	        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
	        historicotable.setRowSorter(rowSorter);

	        int selectedIndex = filtrotipo.getSelectedIndex();
	        if (selectedIndex == 0) {
	            rowSorter.setRowFilter(null); // Remove o filtro se "Todos" for selecionado
	        } else {
	            rowSorter.setRowFilter(RowFilter.regexFilter(filtrotipo.getSelectedItem().toString(), 6));
	        }
	    }
}
