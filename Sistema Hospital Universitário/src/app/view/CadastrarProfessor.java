package app.view;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CadastrarProfessor extends Application {

    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("CadastrarProfessor.fxml"));
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
