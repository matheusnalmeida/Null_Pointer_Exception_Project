package app.view.professor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Matheus Nunes
 */
public class EditarProfessor extends Application{

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        EditarProfessor.stage = stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/view/fxml/professor/EditarProfessor.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Dados");
        stage.centerOnScreen();
        stage.show();
        EditarProfessor.setStage(stage);
        stage.setOnCloseRequest((WindowEvent event) -> {
            ViewProfessor.getStage().show();
        });
    }


}
