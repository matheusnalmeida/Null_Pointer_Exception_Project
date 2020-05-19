package app.view.professor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ListarConsultasProfessor extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        ListarConsultasProfessor.stage = stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/view/fxml/professor/ListarConsultasProfessor.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Lista de consultas realizadas pelos seus alunos");
        stage.centerOnScreen();
        stage.show();
        ListarConsultasProfessor.setStage(stage);
        stage.setOnCloseRequest((WindowEvent event) -> {
            HomeProfessor.getStage().show();
            ListarConsultasProfessor.setStage(null);
        });
    }
}
