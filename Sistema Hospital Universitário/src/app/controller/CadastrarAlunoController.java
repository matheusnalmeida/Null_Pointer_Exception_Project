/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import app.view.CadastrarAluno;
import app.view.MainFrame;
import app.model.dao.AlunoDAO;
import app.model.domain.Aluno;

/**
 * FXML Controller class
 *
 * @author serbi
 */
public class CadastrarAlunoController implements Initializable {

    @FXML
    private Label nomeLabel;
    @FXML
    private Label matriculaLabel;
    @FXML
    private Label senhaLabel;
    @FXML
    private Label anoResidencialLabel;
    @FXML
    private Label dataNascimentoLabel;
    @FXML
    private Label crmProfessorLabel;
    @FXML
    private TextField nomeField;
    @FXML
    private TextField matriculaField;
    @FXML
    private TextField anoResidenciaField;
    @FXML
    private TextField crmProfessorField;
    @FXML
    private PasswordField senhaField;
    @FXML
    private Button cancelar;
    @FXML
    private Button cadastrar;
    @FXML
    private DatePicker dataNascimentoField;

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
        String matricula = this.matriculaField.getText().trim().concat("A");
        String senha = this.senhaField.getText();
        String anoResidencia = this.anoResidenciaField.getText().trim();
        LocalDate dataNascimento = this.dataNascimentoField.getValue();
        String crmProfessor = this.crmProfessorField.getText().trim();
        Aluno aluno = new Aluno(nome, matricula, senha, Integer.parseInt(anoResidencia), dataNascimento);
        boolean result = alunoDAO.create(aluno, crmProfessor);
        Alert alert;
        if (!result) {
            alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Information Dialog");
            alert.setContentText("Cadastro realizado com sucesso!");
        } else {
            alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Information Dialog");
            alert.setContentText("Erro ao realizar cadastro.");
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
