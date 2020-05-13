package app.view.aluno;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.ContextMenuEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GerarConsulta extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        GerarConsulta.stage = stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
        Parent root = FXMLLoader.load(getClass().getResource("/app/view/fxml/aluno/GerarConsulta.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Cadastro de consultas");
        stage.centerOnScreen();
        stage.show();
        GerarConsulta.setStage(stage);
        stage.setOnCloseRequest((WindowEvent event) -> {
            CrudConsultas.getStage().show();
        });
    }
}
