/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import app.model.dao.ProfessorDAO;
import app.model.domain.Professor;
import app.utilits.CPFInvalidoException;
import app.utilits.MatriculaGenerator;
import app.view.CadastrarProfessor;
import app.view.MainFrame;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;

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
    private JFXTextField cpfField;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void cadastrarAction(ActionEvent event) {
        ProfessorDAO professorDAO = new ProfessorDAO();
        String nome = this.nomeField.getText().trim().toUpperCase();
        String cpf = this.cpfField.getText().trim();
        String senha = this.senhaField.getText();
        String titulacao = this.titulacaoField.getText().trim();
        String crm = this.crmField.getText().trim();
        Alert alert;
        try {
            MatriculaGenerator matriculaGenerator = new MatriculaGenerator();
            String matricula = matriculaGenerator.generateToken(cpf, "P");
            char aux1[] = cpf.toCharArray();
            char digito1 = aux1[aux1.length - 2];
            char digito2 = aux1[aux1.length - 1];
            String aux2 = Character.toString(digito1);
            String aux3 = Character.toString(digito2);
            Professor professor = new Professor(nome, matricula, senha, crm, titulacao, Integer.parseInt(aux2 + aux3));
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
        } catch (CPFInvalidoException exception) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Information Dialog");
            alert.setContentText(exception.getMessage());
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
