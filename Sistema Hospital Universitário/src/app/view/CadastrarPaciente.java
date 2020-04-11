package app.view;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CadastrarPaciente extends Application {

    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("CadastrarPaciente.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Cadastrar Paciente");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        setStage(stage);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        CadastrarPaciente.stage = stage;
    }
}
