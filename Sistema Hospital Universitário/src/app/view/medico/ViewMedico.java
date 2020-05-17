package app.view.medico;

import app.controller.medico.ViewMedicoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Matheus Nunes
 */
public class ViewMedico extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        ViewMedico.stage = stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader root = new FXMLLoader(getClass().getResource("/app/view/fxml/medico/ViewMedico.fxml"));
        Scene scene = new Scene(root.load());
        ViewMedicoController controller = root.getController();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Dados");
        stage.centerOnScreen();
        stage.show();
        stage.setOnShowing((WindowEvent event) -> {
            controller.preencherCampos();
        });
        ViewMedico.setStage(stage);
        stage.setOnCloseRequest((WindowEvent event) -> {
            HomeMedico.getStage().show();
        });
    }
}
