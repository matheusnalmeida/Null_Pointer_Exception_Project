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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.dao.MedicoDAO;
import model.domain.Medico;
import view.CadastrarMedico;
import view.MainFrame;

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
        Medico medico = new Medico(nome, crm, matricula, senha);
        MedicoDAO medicoDAO = new MedicoDAO();
        medicoDAO.create(medico);
    }

    @FXML
    public void cancelarAction(ActionEvent event) {
        CadastrarMedico.getStage().close();
        MainFrame.getStage().show();
    }
}
