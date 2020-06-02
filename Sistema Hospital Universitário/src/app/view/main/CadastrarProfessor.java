package app.view.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CadastrarProfessor extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/view/fxml/main/CadastrarProfessor.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Cadastrar Professor");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        stage.setResizable(false);
        setStage(stage);
        stage.setOnCloseRequest((WindowEvent event) -> {
            MainFrame.getStage().show();
        });
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        CadastrarProfessor.stage = stage;
    }
}
