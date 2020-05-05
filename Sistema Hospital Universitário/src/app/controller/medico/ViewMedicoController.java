package app.controller.medico;

import app.model.domain.Medico;
import app.utilits.Sistema;
import app.view.medico.EditarMedico;
import app.view.medico.ViewMedico;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ViewMedicoController implements Initializable {

    @FXML
    private ImageView Icon;

    @FXML
    private Label Nome1;

    @FXML
    private Label Nome2;

    @FXML
    private Label tituloLabel;

    @FXML
    private ImageView Icon1;

    @FXML
    private Label tituloLabel1;

    @FXML
    private Label tituloLabel11;

    @FXML
    private Label tituloLabel111;

    @FXML
    private Label tituloLabel11111;

    @FXML
    private Label nome;

    @FXML
    private Label matricula;

    @FXML
    private Label senha;

    @FXML
    private Label crm;

    @FXML
    private JFXButton alterar;

    @FXML
    private JFXButton voltar;

    @FXML
    void alterar(ActionEvent event) {
        try {
            EditarMedico editarMedico = new EditarMedico();
            editarMedico.start(new Stage());
            ViewMedico.getStage().close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        ViewMedico.getStage().fireEvent(
                new WindowEvent(
                        ViewMedico.getStage(),
                        WindowEvent.WINDOW_CLOSE_REQUEST
                )
        );
    }

    public void preencherCampos() {
        Medico medico = (Medico) Sistema.getSessao().getUsuario();
        this.nome.setText(medico.getNome());
        this.crm.setText(medico.getCrm());
        this.matricula.setText(medico.getMatricula());
        this.senha.setText(medico.getSenha());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.preencherCampos();
    }

}
