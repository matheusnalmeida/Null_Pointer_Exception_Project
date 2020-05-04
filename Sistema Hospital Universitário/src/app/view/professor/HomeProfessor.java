package app.view.professor;

import app.utilits.Sistema;
import app.view.main.MainFrame;
import app.view.medico.HomeMedico;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class HomeProfessor extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        HomeProfessor.stage = stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/view/fxml/professor/HomeProfessor.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Home");
        stage.centerOnScreen();
        stage.show();
        HomeMedico.setStage(stage);
        stage.setOnCloseRequest((WindowEvent event) -> {
            Sistema.setSessao(null);
            MainFrame.getStage().show();
        });
    }
}
