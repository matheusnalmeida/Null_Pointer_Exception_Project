package app.controller.aluno;

import app.model.dao.PacienteDAO;
import app.model.domain.Paciente;
import app.utilits.CPF;
import app.view.aluno.CadastrarPaciente;
import app.view.aluno.HomeAluno;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

public class CadastroPacienteController implements Initializable {

    @FXML
    private JFXButton cadastrarBotao;
    @FXML
    private JFXButton cancelarBotao;
    @FXML
    private JFXTextField nomeField;
    @FXML
    private JFXTextField cpfField;
    @FXML
    private JFXTextField sexoField;
    @FXML
    private JFXTextField corField;
    @FXML
    private JFXDatePicker dataNascimentoField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void cadastrarAction(ActionEvent event) {
        String cpfValue = this.cpfField.getText().trim().toLowerCase();
        String nome = this.nomeField.getText().trim().toUpperCase();
        String cor = this.corField.getText().trim();
        String sexo = this.sexoField.getText().trim();
        Alert alert;
        try {
            CPF cpf = new CPF(cpfValue);
            PacienteDAO pacienteDAO = new PacienteDAO();
            Paciente paciente = new Paciente(nome, sexo, cor, cpf.getCpfString(), this.dataNascimentoField.getValue());
            boolean result = pacienteDAO.create(paciente);
            if (result) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Conclusão de cadastro");
                alert.setContentText("Paciente cadastrado com sucesso.");
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Conclusão de cadastro");
                alert.setContentText("Não foi possível cadastrar o paciente");
            }
        } catch (Exception exception) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Erro no cadastro.");
            alert.setContentText(exception.getMessage());
        }
        alert.showAndWait();
        CadastrarPaciente.getStage().close();
        HomeAluno.getStage().show();
    }

    public void cancelarAction(ActionEvent event) {
        CadastrarPaciente.getStage().close();
        HomeAluno.getStage().show();
    }
}
