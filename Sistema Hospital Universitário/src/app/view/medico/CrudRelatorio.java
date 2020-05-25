package app.view.medico;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CrudRelatorio extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        CrudRelatorio.stage = stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader root = new FXMLLoader(getClass().getResource("/app/view/fxml/medico/CrudRelatorio.fxml"));
        Scene scene = new Scene(root.load());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Relatórios");
        stage.centerOnScreen();
        stage.show();
        CrudRelatorio.setStage(stage);
        stage.setOnCloseRequest((WindowEvent event) -> {
            HomeMedico.getStage().show();
        });
    }
}
