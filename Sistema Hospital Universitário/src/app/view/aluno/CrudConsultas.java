package app.view.aluno;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CrudConsultas extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        CrudConsultas.stage = stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/view/fxml/aluno/CrudConsultas.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Consultas");
        stage.centerOnScreen();
        stage.show();
        CrudConsultas.setStage(stage);
        CadastrarPaciente.setStage(stage);
        stage.setOnCloseRequest((WindowEvent event) -> {
            HomeAluno.getStage().show();
        });
    }
}
