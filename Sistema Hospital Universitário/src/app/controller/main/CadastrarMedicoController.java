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
    /*@FXML
    private JFXTextField cpfField;*/
    @FXML
    private JFXPasswordField senhaField;
    @FXML
    private JFXButton cancelar;
    @FXML
    private JFXButton cadastrar;
    @FXML
    private AnchorPane anchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
            //String cpf = this.cpfField.getText().trim();
            String crm = this.crmField.getText().trim();
            String senha = this.senhaField.getText();
            senha = EncryptionPassword.encrypt(senha);
            try {
                CRM crmValido = new CRM(crm);
                MatriculaGenerator matriculaGenerator = new MatriculaGenerator();
                String matricula = matriculaGenerator.gerarMatricula("M");
                /*char aux1[] = cpf.toCharArray();
            char digito1 = aux1[aux1.length - 2];
            char digito2 = aux1[aux1.length - 1];
            String aux2 = Character.toString(digito1);
            String aux3 = Character.toString(digito2);
            Medico medico = new Medico(nome, matricula, senha, crmValido.getCrm(), Integer.parseInt(aux2 + aux3));*/
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
