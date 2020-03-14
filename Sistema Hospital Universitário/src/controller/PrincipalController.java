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

/**
 * FXML Controller class
 *
 * @author serbi
 */
public class PrincipalController implements Initializable {

    @FXML  private Button txtCadastrarAluno;
    @FXML private Button txtCadastrarProfessor;
    @FXML private Button txtCadastrarPaciente;
    @FXML private Button txtRelatorios;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
