package com.connections.almoxarifado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.telas.almoxarifado.Cad_Colaborador;

public class Data {

	// Grava um texto no banco de dados
	public static void gravarDados(String tabela, CampoBD... campos) {
		StringBuilder colunas = new StringBuilder();
		StringBuilder valores = new StringBuilder();

		for (CampoBD campo : campos) {
			colunas.append(campo.getNome()).append(",");
			valores.append("?").append(",");
		}

		// Remova a vírgula extra no final das strings
		colunas.deleteCharAt(colunas.length() - 1);
		valores.deleteCharAt(valores.length() - 1);

		String sql = "INSERT INTO " + tabela + " (" + colunas + ") VALUES (" + valores + ")";

		try (Connection connection = new DB_Connection().get_connection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			int i = 1;
			for (CampoBD campo : campos) {
				campo.setValorNoStatement(statement, i);
				i++;
			}

			statement.executeUpdate();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro de gravação no banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	public static class CampoBD {
		private String nome;
		private Object valor;

		public CampoBD(String nome, Object valor) {
			this.nome = nome;
			this.valor = valor;
		}

		public String getNome() {
			return nome;
		}

		public Object getValor() {
			return valor;
		}

		public void setValorNoStatement(PreparedStatement statement, int index) throws SQLException {
			statement.setObject(index, valor);
		}
	}

	// Retorna um array com o conteúdo do banco de dados separado por linhas
	// Retorna um ArrayList com o conteúdo do banco de dados separado por linhas
	public static ArrayList<String[]> lerDados(String... tabelas) {
		ArrayList<String[]> lista = new ArrayList<>();

		try (Connection connection = new DB_Connection().get_connection()) {
			for (String tabela : tabelas) {
				String sql = "SELECT * FROM " + tabela;
				try (PreparedStatement statement = connection.prepareStatement(sql);
						ResultSet resultSet = statement.executeQuery()) {

					while (resultSet.next()) {
						String[] linha = new String[resultSet.getMetaData().getColumnCount()];
						for (int i = 0; i < linha.length; i++) {
							linha[i] = resultSet.getString(i + 1);
						}
						lista.add(linha);
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null,"Erro na leitura do banco de dados");
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro de banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
			
		}

		return lista;
	}

	// Modifica um texto no banco de dados
	public static void modificarDados(String tabela, String coluna, String valor, String condicao) {
		String sql = "UPDATE " + tabela + " SET " + coluna + " = ? WHERE " + condicao;

		try (Connection connection = new DB_Connection().get_connection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, valor);
			statement.executeUpdate();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro na modificação no banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Deleta um texto do banco de dados na tela de histórico
	public static void deletarDados(String tabela, String condicaoIdTable, String condicaoId) {
		String sql = "DELETE FROM `" + tabela + "` WHERE " + condicaoIdTable + " AND " + condicaoId;
		
	    try (Connection connection = new DB_Connection().get_connection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.executeUpdate();
	    } catch (SQLException e) {
	    	JOptionPane.showMessageDialog(null, "Erro de deleção no banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
	        
	    }
	}
	
	public static void deletarDadosRequisitantes(String tabela, String matricula) {
		String sql = "DELETE FROM `" + tabela + "` WHERE " + matricula;
		
	    try (Connection connection = new DB_Connection().get_connection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.executeUpdate();
	    } catch (SQLException e) {
	    	JOptionPane.showMessageDialog(null, "Erro de deleção no banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
	        
	    }
	}
	
	public static String autenticarUsuario(Connection connection, String matricula, String senha) {
	    String sql = "SELECT nome FROM telalogin WHERE matricula = ? AND senha_hash = ?";

	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, matricula);
	        statement.setString(2, senha);

	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                String nomeUsuario = resultSet.getString("nome");
	                return nomeUsuario;
	            }
	        }
	    } catch (SQLException e) {
	    	JOptionPane.showMessageDialog(null, "Erro de banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
	    }
	    return null;
	}
	

	public static ArrayList<String[]> lerDadosrequisitantes(String tabela) {
	    ArrayList<String[]> lista = new ArrayList<>();

	    try (Connection connection = new DB_Connection().get_connection()) {
	        String sql = "SELECT * FROM " + tabela;
	        try (PreparedStatement statement = connection.prepareStatement(sql);
	             ResultSet resultSet = statement.executeQuery()) {

	            while (resultSet.next()) {
	                String[] linha = new String[resultSet.getMetaData().getColumnCount()];
	                for (int i = 0; i < linha.length; i++) {
	                    linha[i] = resultSet.getString(i + 1);
	                }
	                lista.add(linha);
	            }
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Erro na leitura do banco de dados");
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Erro de banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
	    }

	    return lista;
	}
	

	
}
