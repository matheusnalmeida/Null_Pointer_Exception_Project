package app.controller.aluno;

import app.view.aluno.CrudConsultas;
import app.view.aluno.GerarConsulta;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CrudConsultasController implements Initializable {

    @FXML
    private Button novaConsultaBotao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void adicionarConsulta(ActionEvent evt) {
        try {
            GerarConsulta gerarConsulta = new GerarConsulta();
            gerarConsulta.start(new Stage());
            CrudConsultas.getStage().close();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
