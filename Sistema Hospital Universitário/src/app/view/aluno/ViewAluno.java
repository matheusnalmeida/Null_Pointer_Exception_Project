package app.view.aluno;

import app.controller.aluno.ViewAlunoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Matheus Nunes
 */
public class ViewAluno extends Application{
     private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        ViewAluno.stage = stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader root = new FXMLLoader(getClass().getResource("/app/view/fxml/aluno/ViewAluno.fxml"));
        Scene scene = new Scene(root.load());
        ViewAlunoController controller = root.getController();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Dados");
        stage.centerOnScreen();
        stage.show();
        stage.setOnShowing((WindowEvent event) -> {
            controller.preencherCampos();
        });
        ViewAluno.setStage(stage);
        stage.setOnCloseRequest((WindowEvent event) -> {
            HomeAluno.getStage().show();
        });
    }
}
