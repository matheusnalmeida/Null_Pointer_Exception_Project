package app.controller.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import app.model.dao.ProfessorDAO;
import app.model.domain.Professor;
import app.utilits.CRM;
import app.utilits.EncryptionPassword;
import app.utilits.MatriculaGenerator;
import app.view.main.CadastrarProfessor;
import app.view.main.MainFrame;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author serbi
 */
public class CadastrarProfessorController implements Initializable {

    @FXML
    private Label tituloLabel;
    @FXML
    private JFXTextField nomeField;
    @FXML
    private JFXTextField crmField;
    @FXML
    private JFXTextField titulacaoField;
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
                && !this.senhaField.getText().equals("")
                && !this.titulacaoField.getText().equals("");
    }

    @FXML
    public void cadastrarAction(ActionEvent event) {
        Alert alert;
        if (validaCampos()) {
            ProfessorDAO professorDAO = new ProfessorDAO();
            String nome = this.nomeField.getText().trim().toUpperCase();
            String senha = this.senhaField.getText();
            String titulacao = this.titulacaoField.getText().trim();
            String crm = this.crmField.getText().trim();
            senha = EncryptionPassword.encrypt(senha);
            try {
                CRM crmValido = new CRM(crm);
                MatriculaGenerator matriculaGenerator = new MatriculaGenerator();
                String matricula = matriculaGenerator.gerarMatricula("P");
                Professor professor = new Professor(nome, matricula, senha, crmValido.getCrm(), titulacao);
                boolean result = professorDAO.create(professor);
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
        CadastrarProfessor.getStage().close();
        MainFrame.getStage().show();
    }

    @FXML
    public void cancelarAction(ActionEvent event) {
        CadastrarProfessor.getStage().close();
        MainFrame.getStage().show();
    }
}
