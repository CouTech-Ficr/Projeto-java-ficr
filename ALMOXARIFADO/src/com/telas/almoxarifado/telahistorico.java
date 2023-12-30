package com.telas.almoxarifado;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.connections.almoxarifado.Data;

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
	private JButton btnExportar;

	
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

	public void inicializarTela() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telahistorico window = new telahistorico();
					window.setVisible(true);
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
				String[] column = { "id", "Cod.Req", "Nome Requisitante", "Tipo", "Quantidade", "Data",
						"Cod.Barras/Sku", "Item", "Aplicacao", "id_table" };
				ArrayList<String[]> dados = Data.lerDados("retiradaereposicao1", "retiradaereposicao2",
						"retiradaereposicao3", "retiradaereposicao4", "retiradaereposicao5");
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

		btnapagar = new JButton("");
		btnapagar.setIcon(new ImageIcon("src/img/lixeiraM.png"));
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
						JOptionPane.showMessageDialog(null, "Removido!", "Sucesso", JOptionPane.OK_OPTION);

					case 1:
						return;

					}

				} else {
					JOptionPane.showMessageDialog(null, "SELECIONE UMA LINHA NA TABELA!", "ATENÇÃO",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnapagar.setForeground(Color.WHITE);
		btnapagar.setBackground(new Color(0, 165, 170));
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
		filtrotipo.setModel(new DefaultComboBoxModel(new String[] { "", "Retirada", "Reposição" }));
		filtrotipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		filtrotipo.setBounds(240, 108, 148, 30);
		contentPane.add(filtrotipo);

		JLabel lblNewLabel = new JLabel("Filtrar por:");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 49, 185, 30);
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 148, 988, 499);
		contentPane.add(scrollPane);

		historicotable = new JTable();
		scrollPane.setViewportView(historicotable);
		model = new DefaultTableModel();
		historicotable.setModel(model);
		historicotable.getTableHeader().setReorderingAllowed(false);

		JLabel lblSkuEan = new JLabel("SKU / EAN");
		lblSkuEan.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblSkuEan.setBounds(10, 79, 185, 30);
		contentPane.add(lblSkuEan);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblTipo.setBounds(240, 79, 185, 30);
		contentPane.add(lblTipo);

		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("src/img/ImagemElisHorizontal.png"));
		logo.setBounds(328, 10, 379, 88);
		contentPane.add(logo);

		lblNewLabel_2 = new JLabel("JeanLM TI ©");
		lblNewLabel_2.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 12));
		lblNewLabel_2.setBounds(840, 10, 143, 20);
		contentPane.add(lblNewLabel_2);

		btnExportar = new JButton("Exportar");
		btnExportar.setIcon(new ImageIcon("src/img/share.png"));
		btnExportar.addActionListener(e -> export(historicotable));
		btnExportar.setBackground(new Color(0, 165, 170));
		btnExportar.setForeground(Color.WHITE);
		btnExportar.setFont(new Font("Microsoft JhengHei", Font.BOLD, 13));
		btnExportar.setBounds(408, 106, 131, 32);
		contentPane.add(btnExportar);

		initComplementos();
	}

	public void initComplementos() {
		this.setLocationRelativeTo(null);
	}

	public void apagando() {
		int index = historicotable.getSelectedRow();
		if (index >= 0) {
			String idTable = historicotable.getValueAt(index, 9).toString(); // Índice 9 para "id_table"
			String id = historicotable.getValueAt(index, 0).toString(); // Índice 0 para "id"

			String condicaoIdTable = "id_table = " + idTable;
			String condicaoId = "id_pk = " + id;
			Data.deletarDados("retiradaereposicao1", condicaoIdTable, condicaoId);
			Data.deletarDados("retiradaereposicao2", condicaoIdTable, condicaoId);
			Data.deletarDados("retiradaereposicao3", condicaoIdTable, condicaoId);
			Data.deletarDados("retiradaereposicao4", condicaoIdTable, condicaoId);
			Data.deletarDados("retiradaereposicao5", condicaoIdTable, condicaoId);

		}
	}

	private void filtrarTabela() {
		TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
		historicotable.setRowSorter(rowSorter);

		String textoFiltro = filtrocod.getText();
		if (textoFiltro.trim().length() == 6) {
			rowSorter.setRowFilter(null); // Remove o filtro se o campo de filtro estiver vazio
		} else {
			rowSorter.setRowFilter(RowFilter.regexFilter(textoFiltro, 6)); // Filtra pelo código (coluna 6)
		}
	}

	private void filtrarTabelaCombobox() {
		TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
		historicotable.setRowSorter(rowSorter);

		int selectedIndex = filtrotipo.getSelectedIndex();
		if (selectedIndex == 0) {
			rowSorter.setRowFilter(null); // Remove o filtro se "Todos" for selecionado
		} else {
			rowSorter.setRowFilter(RowFilter.regexFilter(filtrotipo.getSelectedItem().toString(), 3)); // Filtra pelo
																										// tipo (coluna
																										// 3)
		}
	}
	public static void export(JTable table) {
        JFileChooser fileChooser = new JFileChooser();

       
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos CSV", "csv");
        fileChooser.setFileFilter(filter);

        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            
            if (!file.getName().toLowerCase().endsWith(".csv")) {
                file = new File(file.getPath() + ".csv");
            }

            TableModel model = table.getModel();

            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
                // Escrever os nomes das colunas
                for (int i = 1; i <=8; i++) {
                    writer.write(escapeCsvValue(model.getColumnName(i)));
                    if (i < model.getColumnCount() - 1) {
                        writer.write(";");
                    }
                }
                writer.write(System.lineSeparator());

                // Escrever os dados das células
                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 1; j <=8; j++) {
                        writer.write(escapeCsvValue(model.getValueAt(i, j).toString()));
                        if (j < model.getColumnCount() - 1) {
                            writer.write(";");
                        }
                    }
                    writer.write(System.lineSeparator());
                }

                JOptionPane.showMessageDialog(null, "Exportação concluída com sucesso!");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro durante a exportação para CSV.");
            }
        }
    }

    private static String escapeCsvValue(String value) {
        // Se o valor contiver ponto e vírgula, envolva-o entre aspas duplas
        if (value.contains(";") || value.contains("\"")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        } else {
            return value;
        }
    }


}
