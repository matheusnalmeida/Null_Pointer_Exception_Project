package app.controller.medico;

import app.view.medico.HomeMedico;
import app.view.medico.ViewMedico;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class HomeMedicoController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void exame(ActionEvent evt) {

    }

    public void listarRelatorios(ActionEvent evt) {

    }

    public void meusDados(ActionEvent evt) {
        try {
            ViewMedico viewMedico = new ViewMedico();
            viewMedico.start(new Stage());
            HomeMedico.getStage().close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
