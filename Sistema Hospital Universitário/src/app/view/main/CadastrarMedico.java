package app.view.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CadastrarMedico extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        CadastrarMedico.stage = stage;
    }

    public CadastrarMedico() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        CadastrarMedico.setStage(stage);
        Parent root = FXMLLoader.load(getClass().getResource("/app/view/fxml/main/CadastrarMedico.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Cadastrar Médico");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        stage.setResizable(false);
        stage.setOnCloseRequest((WindowEvent event) -> {
            MainFrame.getStage().show();
        });
    }
}
