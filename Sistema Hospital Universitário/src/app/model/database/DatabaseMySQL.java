package app.model.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DatabaseMySQL {

    public static Connection conectar() {
        Connection conexao = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2?serverTimezone=UTC", "root", "");
        } catch (ClassNotFoundException | SQLException exception) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro de conexão");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao tentar conectar com o banco de dados.");
            alert.showAndWait();
        }
        return conexao;
    }

    public static void desconectar(Connection conexao) {
        try {
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException exception) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro de conexão");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao tentar desconectar conexão com o banco de dados.");
            alert.showAndWait();
        }
    }
}
