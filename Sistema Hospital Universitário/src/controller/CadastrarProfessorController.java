/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author serbi
 */
public class CadastrarProfessorController implements Initializable {

    @FXML
    private Label nomeLabel;
    @FXML
    private Label crmLabel;
    @FXML
    private Label titulacaoLabel;
    @FXML
    private Label senhaLabel;
    @FXML
    private Label matriculaLabel;
    @FXML
    private TextField nomeField;
    @FXML
    private TextField crmField;
    @FXML
    private TextField titulacaoField;
    @FXML
    private PasswordField senhaField;
    @FXML
    private TextField matriculaField;
    @FXML
    private Button cancelar;
    @FXML
    private Button cadastrar;
    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
