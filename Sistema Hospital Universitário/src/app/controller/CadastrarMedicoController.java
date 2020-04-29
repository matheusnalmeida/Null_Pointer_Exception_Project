package app.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import app.model.dao.MedicoDAO;
import app.model.domain.Medico;
import app.utilits.CPFInvalidoException;
import app.utilits.CRM;
import app.utilits.CRMInvalidoException;
import app.utilits.MatriculaGenerator;
import app.view.CadastrarMedico;
import app.view.MainFrame;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void cadastrarAction(ActionEvent event) {
        String nome = this.nomeField.getText().trim().toUpperCase();
        //String cpf = this.cpfField.getText().trim();
        String crm = this.crmField.getText().trim();
        String senha = this.senhaField.getText();
        Alert alert;
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
