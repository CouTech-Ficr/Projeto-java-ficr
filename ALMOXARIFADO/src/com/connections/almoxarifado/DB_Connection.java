package com.connections.almoxarifado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.io.IOException;

public class DB_Connection {

    public static void main(String[] args) {
        DB_Connection obj_DB_Connection = new DB_Connection();
        System.out.println(obj_DB_Connection.get_connection());
    }

    public static Connection get_connection() {
        Connection connection = null;
        try {
            // Tenta estabelecer a conexão
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/almoxarifado", "root", "6503$KMs");
        } catch (Exception e) {
            System.out.println("Não foi possível conectar ao banco de dados. Verificando e iniciando o servidor MySQL...");

            // Se ocorrer uma exceção ao conectar, inicia o servidor MySQL
            startMySQLServer();

            try {
                // Aguarda um pouco para dar tempo ao servidor MySQL de iniciar
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            // Tenta estabelecer a conexão novamente
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/almoxarifado", "root", "$3nh4");
                System.out.println("Conexão bem-sucedida após iniciar o servidor MySQL.");
            } catch (Exception ex) {
                System.out.println("Não foi possível conectar ao banco de dados mesmo após iniciar o servidor MySQL.");
                ex.printStackTrace();
            }
        }
        return connection;
    }

    // Método para iniciar o servidor MySQL
    private static void startMySQLServer() {
        try {
            // Caminho para o executável mysqld
            String mysqlPath = "C:\\Program Files\\MySQL\\MySQL Server 8.2\\bin\\mysqld.exe";

            // Comando para iniciar o servidor MySQL
            String command = mysqlPath + " --console";

            // Inicia o processo
            Process process = Runtime.getRuntime().exec(command);

            // Aguarda até que o processo seja encerrado
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
