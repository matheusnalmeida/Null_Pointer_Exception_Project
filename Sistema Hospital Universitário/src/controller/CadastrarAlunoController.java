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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    private Label enderecoLabel;
    @FXML
    private Label dataNascimentoLabel;
    @FXML
    private Label nomeProfessorLabel;
    @FXML
    private TextField nomeField;
    @FXML
    private TextField matriculaField;
    @FXML
    private TextField enderecoField;
    @FXML
    private TextField professorField;
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
}
