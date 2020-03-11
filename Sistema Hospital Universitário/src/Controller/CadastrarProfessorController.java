/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author serbi
 */
public class CadastrarProfessorController implements Initializable {
    
    @FXML private AnchorPane txtSenhaProfessor;
    @FXML private TextField txtNomeProfessor;
    @FXML private TextField txtCRMProfessor;
    @FXML private TextField txtTitulacaoProfessor;
    @FXML private PasswordField txt;
    @FXML private Button txtCadastrarProfessor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
