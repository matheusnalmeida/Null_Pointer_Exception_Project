/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.view.aluno;

import app.controller.aluno.EditarConsultaController;
import app.model.domain.ConsultaAux;
import app.model.domain.ImagemRelatorio;
import app.view.professor.ListarConsultasProfessor;
import java.util.List;
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
public class EditarConsulta extends Application {

    private static Stage stage;

    private List<ImagemRelatorio> imagens;
    private ConsultaAux consulta;

    public EditarConsulta(List<ImagemRelatorio> imagens, ConsultaAux consulta) {
        this.imagens = imagens;
        this.consulta = consulta;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        EditarConsulta.stage = stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/view/fxml/aluno/EditarCA.fxml"));
        Parent root = (Parent) loader.load();
        EditarConsultaController controller = loader.getController();
        controller.recebeDados(consulta, imagens);
        stage.setScene(new Scene(root));
        EditarConsulta.stage = stage;
        stage.show();
        stage.setOnCloseRequest((WindowEvent event) -> {
            if (CrudConsultas.getStage() != null) {
                CrudConsultas.getStage().show();
            } else {
                ListarConsultasProfessor.getStage().show();
            }
            EditarConsulta.getStage().close();
        });
    }

}
