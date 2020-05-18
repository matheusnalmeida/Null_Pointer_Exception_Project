package app.controller.main;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import app.view.main.CadastrarAluno;
import app.view.main.MainFrame;
import app.model.dao.AlunoDAO;
import app.model.domain.Aluno;
import app.utilits.EncryptionPassword;
import app.utilits.MatriculaGenerator;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author serbi
 */
public class CadastrarAlunoController implements Initializable {

    @FXML
    private Label tituloLabel;
    @FXML
    private JFXTextField nomeField;
    @FXML
    private JFXTextField anoResidenciaField;
    @FXML
    private JFXPasswordField senhaField;
    @FXML
    private JFXButton cancelar;
    @FXML
    private JFXButton cadastrar;
    @FXML
    private JFXDatePicker dataNascimentoField;
    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public boolean validaCampos() {
        return !this.nomeField.getText().trim().equals("")
                && !this.anoResidenciaField.getText().trim().equals("")
                && !this.senhaField.getText().equals("")
                && this.dataNascimentoField.getValue() != null;
    }

    @FXML
    public void cadastrarAcao(ActionEvent event) {
        Alert alert;
        if (validaCampos()) {
            AlunoDAO alunoDAO = new AlunoDAO();
            String nome = this.nomeField.getText().trim().toUpperCase();
            String senha = this.senhaField.getText();
            senha = EncryptionPassword.encrypt(senha);
            String anoResidencia = this.anoResidenciaField.getText().trim();
            LocalDate dataNascimento = this.dataNascimentoField.getValue();
            MatriculaGenerator matriculaGeneretor = new MatriculaGenerator();
            try {
                String matricula = matriculaGeneretor.gerarMatricula("A");
                Aluno aluno = new Aluno(nome, matricula, senha, Integer.parseInt(anoResidencia), dataNascimento.toString());
                boolean result = alunoDAO.create(aluno);
                if (result) {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Information Dialog");
                    alert.setContentText("Cadastro realizado com sucesso!\nSua matrícula: " + matricula + "\nFique aguardando até que seu professor autorize o seu acesso ao sistema.");
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Information Dialog");
                    alert.setContentText("Erro ao realizar cadastro.");
                }
            } catch (Exception exception) {
                alert = new Alert(AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Information Dialog");
                alert.setContentText(exception.getMessage());
            }
        } else {
            alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Information Dialog");
            alert.setContentText("Preencha todos os campos obrigatorios!");
        }
        alert.showAndWait();
        CadastrarAluno.getStage().close();
        MainFrame.getStage().show();
    }

    @FXML
    public void cancelarAcao(ActionEvent event) {
        CadastrarAluno.getStage().close();
        MainFrame.getStage().show();
    }
}
