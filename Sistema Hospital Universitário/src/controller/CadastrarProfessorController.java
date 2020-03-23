/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.dao.ProfessorDAO;
import model.domain.Professor;
import view.CadastrarProfessor;
import view.MainFrame;

/**
 * FXML Controller class
 *
 * @author serbi
 */
public class CadastrarProfessorController implements Initializable {

    @FXML
    private Label nomeLabel;
    @FXML
    private Label matriculaLabel;
    @FXML
    private Label senhaLabel;
    @FXML
    private Label crmLabel;
    @FXML
    private Label titulacaoLabel;
    @FXML
    private TextField nomeField;
    @FXML
    private TextField matriculaField;
    @FXML
    private TextField crmField;
    @FXML
    private TextField titulacaoField;
    @FXML
    private PasswordField senhaField;
    @FXML
    private Button cancelar;
    @FXML
    private Button cadastrar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void cadastrarAction(ActionEvent event) {
        ProfessorDAO professorDAO = new ProfessorDAO();
        String nome = nomeField.getText().trim().toUpperCase();
        String matricula = matriculaField.getText().trim().concat("P");
        String senha = senhaField.getText();
        String titulacao = titulacaoField.getText().trim();
        String crm = crmField.getText().trim();
        Professor professor = new Professor(nome, matricula, senha, crm, titulacao);
        boolean result = professorDAO.create(professor);
        Alert alert;
        if (!result) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Information Dialog");
            alert.setContentText("Cadastro realizado com sucesso!");
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Information Dialog");
            alert.setContentText("Erro ao realizar cadastro.");
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
