/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

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

    @FXML private Button cadastrarAluno;
    @FXML private Button cadastrarProfessor;
    @FXML private Button cadastrarPaciente;
    @FXML private Button relatorios;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
