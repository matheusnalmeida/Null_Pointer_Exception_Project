package app.controller.medico;

import app.view.medico.CadastrarExames;
import app.view.medico.CrudPedidosExame;
import app.view.medico.CrudRelatorio;
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
        try {
            CadastrarExames cadastrarExamesMedico = new CadastrarExames();
            cadastrarExamesMedico.start(new Stage());
            HomeMedico.getStage().close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void listaRelatorios(ActionEvent evt) {
        try {
            CrudRelatorio crudRelatorio = new CrudRelatorio();
            crudRelatorio.start(new Stage());
            CrudRelatorio.getStage().show();
            HomeMedico.getStage().close();
        } catch (Exception exception) {
        }
    }

    public void listaPedidosExame(ActionEvent evt) {
        try {
            CrudPedidosExame crudPedidosExame = new CrudPedidosExame();
            crudPedidosExame.start(new Stage());
            CrudPedidosExame.getStage().show();
            HomeMedico.getStage().close();
        } catch (Exception exception) {
        }
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
