package model.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DatabaseMySQL {

    public static Connection conectar() {
        Connection conexao = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://ip/nomeBanco", "usuario", "senha");
        } catch (ClassNotFoundException | SQLException exception) {

        }
        return conexao;
    }

    public static void desconectar(Connection conexao) {
        try {
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException exception) {

        }
    }
}
