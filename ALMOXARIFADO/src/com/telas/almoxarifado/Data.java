package com.telas.almoxarifado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Data {

    // Grava um texto no banco de dados
    public static void gravarDados(String tabela, String coluna, String valor) {
        String sql = "INSERT INTO " + tabela + " (" + coluna + ") VALUES (?)";

        try (Connection connection = new DB_Connection().get_connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, valor);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro na gravação do banco de dados ...");
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
                    System.out.println("Erro na leitura do banco de dados ...");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro na conexão com o banco de dados ...");
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
            System.out.println("Erro na modificação do banco de dados ...");
        }
    }

    // Deleta um texto do banco de dados
    public static void deletarDados(String tabela, String condicao) {
        String sql = "DELETE FROM " + tabela + " WHERE " + condicao;

        try (Connection connection = new DB_Connection().get_connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro na deleção do banco de dados ...");
        }
    }
}
