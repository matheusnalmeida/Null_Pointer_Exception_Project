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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author serbi
 */
public class CadastrarPacienteController implements Initializable {

    @FXML
    private Label nomeLabel;
    @FXML
    private Label cpfLabel;
    @FXML
    private Label dataNascimentoLabel;
    @FXML
    private Label corLabel;
    @FXML
    private Label sexoLabel;
    @FXML
    private TextField nomeField;
    @FXML
    private TextField cpfField;
    @FXML
    private DatePicker dataNascimentoField;
    @FXML
    private TextField corField;
    @FXML
    private TextField sexoField;
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
}
