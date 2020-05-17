package app.view.medico;

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
public class CadastrarExames extends Application{
     private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        CadastrarExames.stage = stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/view/fxml/medico/CadastrarExames.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Cadastrar Exame");
        stage.centerOnScreen();
        stage.show();
        CadastrarExames.setStage(stage);
        stage.setOnCloseRequest((WindowEvent event) -> {
            HomeMedico.getStage().show();
        });
    }
}
