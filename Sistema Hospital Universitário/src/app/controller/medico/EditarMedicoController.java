package app.controller.medico;

import app.model.dao.MedicoDAO;
import app.model.domain.Medico;
import app.utilits.EncryptionPassword;
import app.utilits.Sistema;
import app.view.medico.EditarMedico;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.WindowEvent;

public class EditarMedicoController implements Initializable{

    @FXML
    private ImageView Icon;

    @FXML
    private Label Nome1;

    @FXML
    private Label Nome2;

    @FXML
    private JFXTextField nomeField;

    @FXML
    private JFXTextField crmField;

    @FXML
    private JFXTextField matriculaField;

    @FXML
    private JFXPasswordField senhaField;

    @FXML
    private ImageView Icon1;

    @FXML
    private JFXPasswordField novaSenhaTextField;

    @FXML
    private JFXButton editar;

    @FXML
    private JFXButton cancelar;

    @FXML
    void cancelarAction(ActionEvent event) {
        EditarMedico.getStage().fireEvent(
                new WindowEvent(
                        EditarMedico.getStage(),
                        WindowEvent.WINDOW_CLOSE_REQUEST
                )
        );
    }

    @FXML
    void editar(ActionEvent event) {
        if (this.validaCamposVazio() && this.validaSenha()) {
            MedicoDAO medicoDao = new MedicoDAO();
            Medico medico = (Medico) Sistema.getSessao().getUsuario();
            medico.setSenha(EncryptionPassword.encrypt(this.novaSenhaTextField.getText()));
            boolean alterado = medicoDao.update(medico);
            if (alterado) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Dados Alterados Com Sucesso!");
                alert.showAndWait();
                this.cancelarAction(event);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Dados Invalidos!");
            alert.showAndWait();
        }
    }

    boolean validaSenha() {
        return EncryptionPassword.encrypt(this.senhaField.getText())
                .equals(Sistema.getSessao().getUsuario().getSenha());
    }

    boolean validaCamposVazio() {
        return !"".equals(this.senhaField.getText())
                && !"".equals(this.novaSenhaTextField.getText());
    }

    public void preencherCampos() {
        Medico medico = (Medico) Sistema.getSessao().getUsuario();
        this.nomeField.setText(medico.getNome());
        this.crmField.setText(medico.getCrm());
        this.matriculaField.setText(medico.getMatricula());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.preencherCampos();
    }
}
