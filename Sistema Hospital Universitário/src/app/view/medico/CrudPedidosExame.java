package app.view.medico;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CrudPedidosExame extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        CrudPedidosExame.stage = stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/view/fxml/medico/CrudPedidosExames.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Pedidos de exame realizados");
        stage.centerOnScreen();
        stage.show();
        CrudPedidosExame.setStage(stage);
        stage.setOnCloseRequest((WindowEvent event) -> {
            HomeMedico.getStage().show();
        });
    }
}
