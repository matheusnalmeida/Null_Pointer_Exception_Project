package app.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import app.view.CadastrarAluno;
import app.view.MainFrame;
import app.model.dao.AlunoDAO;
import app.model.domain.Aluno;
import app.utilits.CPFInvalidoException;
import app.utilits.MatriculaGenerator;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

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
    private JFXTextField cpfField;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void cadastrarAcao(ActionEvent event) {
        AlunoDAO alunoDAO = new AlunoDAO();
        String nome = this.nomeField.getText().trim();
        String cpf = this.cpfField.getText().trim();
        String senha = this.senhaField.getText();
        String anoResidencia = this.anoResidenciaField.getText().trim();
        LocalDate dataNascimento = this.dataNascimentoField.getValue();
        MatriculaGenerator matriculaGeneretor = new MatriculaGenerator();
        Alert alert;
        try {
            String matricula = matriculaGeneretor.generateToken(cpf, "A");
            char aux1[] = cpf.toCharArray();
            char digito1 = aux1[aux1.length - 2];
            char digito2 = aux1[aux1.length - 1];
            String aux2 = Character.toString(digito1);
            String aux3 = Character.toString(digito2);
            Aluno aluno = new Aluno(nome, matricula, senha, Integer.parseInt(anoResidencia), dataNascimento, Integer.parseInt(aux2 + aux3));
            boolean result = alunoDAO.create(aluno);
            if (result) {
                alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Information Dialog");
                alert.setContentText("Cadastro realizado com sucesso!\nSua matrícula: " + matricula);
            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Information Dialog");
                alert.setContentText("Erro ao realizar cadastro.");
            }
        } catch (CPFInvalidoException exception) {
            alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Information Dialog");
            alert.setContentText(exception.getMessage());
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
