package app.controller.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import app.model.dao.MedicoDAO;
import app.model.domain.Medico;
import app.utilits.CRM;
import app.utilits.EncryptionPassword;
import app.utilits.MatriculaGenerator;
import app.view.main.CadastrarMedico;
import app.view.main.MainFrame;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.layout.AnchorPane;

public class CadastrarMedicoController implements Initializable {

    @FXML
    private Label tituloLabel;
    @FXML
    private JFXTextField nomeField;
    @FXML
    private JFXTextField crmField;
    @FXML
    private JFXPasswordField senhaField;
    @FXML
    private JFXButton cancelar;
    @FXML
    private JFXButton cadastrar;
    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public boolean validaCampos() {
        return !this.nomeField.getText().trim().equals("")
                && !this.crmField.getText().trim().equals("")
                && !this.senhaField.getText().equals("");
    }

    @FXML
    public void cadastrarAction(ActionEvent event) {
        Alert alert;
        if (validaCampos()) {
            String nome = this.nomeField.getText().trim().toUpperCase();
            String crm = this.crmField.getText().trim();
            String senha = this.senhaField.getText();
            senha = EncryptionPassword.encrypt(senha);
            try {
                CRM crmValido = new CRM(crm);
                MatriculaGenerator matriculaGenerator = new MatriculaGenerator();
                String matricula = matriculaGenerator.gerarMatricula("M");
                Medico medico = new Medico(nome, matricula, senha, crmValido.getCrm());
                MedicoDAO medicoDAO = new MedicoDAO();
                boolean result = medicoDAO.create(medico);
                if (result) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Information Dialog");
                    alert.setContentText("Cadastro realizado com sucesso!\nSua matrícula: " + matricula);
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Information Dialog");
                    alert.setContentText("Erro ao realizar cadastro.");
                }
            } catch (Exception exception) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Information Dialog");
                alert.setContentText(exception.getMessage());
            }
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Information Dialog");
            alert.setContentText("Preencha todos os campos obrigatorios!");
        }
        alert.showAndWait();
        CadastrarMedico.getStage().close();
        MainFrame.getStage().show();
    }

    @FXML
    public void cancelarAction(ActionEvent event) {
        CadastrarMedico.getStage().close();
        MainFrame.getStage().show();
    }
}
