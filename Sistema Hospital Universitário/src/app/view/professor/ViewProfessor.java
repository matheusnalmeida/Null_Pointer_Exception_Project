package app.view.professor;

import app.controller.professor.ViewProfessorController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Matheus Nunes
 */
public class ViewProfessor extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        ViewProfessor.stage = stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader root = new FXMLLoader(getClass().getResource("/app/view/fxml/professor/ViewProfessor.fxml"));
        Scene scene = new Scene(root.load());
        ViewProfessorController controller = root.getController();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Dados");
        stage.centerOnScreen();
        stage.show();
        stage.setOnShowing((WindowEvent event) -> {
            controller.preencherCampos();
        });
        ViewProfessor.setStage(stage);
        stage.setOnCloseRequest((WindowEvent event) -> {
            HomeProfessor.getStage().show();
        });
    }
}
