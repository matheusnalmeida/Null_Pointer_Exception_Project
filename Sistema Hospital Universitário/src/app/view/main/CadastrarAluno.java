package app.view.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CadastrarAluno extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/view/fxml/main/CadastrarAluno.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Cadastrar Aluno");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
        setStage(stage);
        stage.setOnCloseRequest((WindowEvent event) -> {
            MainFrame.getStage().show();
        });
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        CadastrarAluno.stage = stage;
    }
}
