package app.view.professor;

import app.controller.professor.MinhaTurmaController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MinhaTurma extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        MinhaTurma.stage = stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader root = new FXMLLoader(getClass().getResource("/app/view/fxml/professor/MinhaTurma.fxml"));
        Scene scene = new Scene(root.load());
        stage.setScene(scene);
        MinhaTurmaController controller = root.getController();
        stage.setResizable(false);
        stage.setTitle("Seus Alunos");
        stage.centerOnScreen();
        stage.show();
        MinhaTurma.setStage(stage);
        stage.setOnShowing((WindowEvent event) -> {
            controller.carregarTableViewAlunos();
        });
        stage.setOnCloseRequest((WindowEvent event) -> {
            HomeProfessor.getStage().show();
        });
    }
}
