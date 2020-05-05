package app.view.aluno;

import app.utilits.Sistema;
import app.view.main.MainFrame;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GerarConsulta extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        GerarConsulta.stage = stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/view/fxml/aluno/GerarConsulta.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Cadastro de consultas");
        stage.centerOnScreen();
        stage.show();
        GerarConsulta.setStage(stage);
        stage.setOnCloseRequest((WindowEvent event) -> {
            HomeAluno.getStage().show();
        });
    }
}
