/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author serbi
 */
public class CadastrarPacienteController implements Initializable {

    @FXML
    private Label tituloLabel;
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
}
