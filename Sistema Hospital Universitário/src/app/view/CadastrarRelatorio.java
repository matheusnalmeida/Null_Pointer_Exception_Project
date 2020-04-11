package app.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author serbi
 */
public class CadastrarRelatorio extends Application {

    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("CadastrarRelatorio.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Cadastro de Relatorio");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
        setStage(stage);
        stage.setResizable(false);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        CadastrarRelatorio.stage = stage;
    }
}
