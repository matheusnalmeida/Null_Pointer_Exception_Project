package app.view.aluno;

import app.controller.aluno.CrudConsultasController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
        FXMLLoader root = new FXMLLoader(getClass().getResource("/app/view/fxml/aluno/CrudConsultas.fxml"));
        Scene scene = new Scene(root.load());
        stage.setScene(scene);
        CrudConsultasController controller = root.getController();
        stage.setResizable(false);
        stage.setTitle("Suas Consultas");
        stage.centerOnScreen();
        stage.show();
        CrudConsultas.setStage(stage);
        stage.setOnShowing((WindowEvent event) -> {
            controller.carregarTableViewPacienteAlunoRelatorio();
        });
        stage.setOnCloseRequest((WindowEvent event) -> {
            HomeAluno.getStage().show();
        });
    }
}
