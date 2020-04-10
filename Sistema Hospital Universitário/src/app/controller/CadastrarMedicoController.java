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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import app.model.dao.old.MedicoDAO;
import app.model.domain.Medico;
import app.view.CadastrarMedico;
import app.view.MainFrame;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class CadastrarMedicoController implements Initializable {

    @FXML
    private Label nomeLabel;
    @FXML
    private Label crmLabel;
    @FXML
    private Label matriculaLabel;
    @FXML
    private Label senhaLabel;
    @FXML
    private TextField nomeField;
    @FXML
    private TextField crmField;
    @FXML
    private TextField matriculaField;
    @FXML
    private PasswordField senhaField;
    @FXML
    private Button cancelar;
    @FXML
    private Button cadastrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void cadastrarAction(ActionEvent event) {
        String nome = nomeField.getText().trim().toUpperCase();
        String matricula = matriculaField.getText().trim().concat("M");
        String crm = crmField.getText().trim();
        String senha = senhaField.getText();
        Medico medico = new Medico(nome, matricula, senha, crm);
        MedicoDAO medicoDAO = new MedicoDAO();
        boolean result = medicoDAO.create(medico);
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
        CadastrarMedico.getStage().close();
        MainFrame.getStage().show();
    }

    @FXML
    public void cancelarAction(ActionEvent event) {
        CadastrarMedico.getStage().close();
        MainFrame.getStage().show();
    }
}
