package com.connections.almoxarifado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class HistoricoLoginDAO {

    // Utilize a conexão passada como parâmetro
    public static void registrarLogin(Connection connection, String usuario) {
        try {
            String sql = "INSERT INTO historico_login (usuario, data_hora_login) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, usuario);
                statement.setTimestamp(2, new Timestamp(new Date().getTime()));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Utilize a conexão passada como parâmetro
    public static void registrarLogoff(Connection connection, String usuario) {
        try {
            String sql = "UPDATE historico_login SET data_hora_logoff = ? WHERE usuario = ? AND data_hora_logoff IS NULL";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setTimestamp(1, new Timestamp(new Date().getTime()));
                statement.setString(2, usuario);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


