/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.Principal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Matheus Nunes
 */
public class MainFrameController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField campoMatricula;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private Button botaoLogin;

    @FXML
    void acaoBotaoLogin(ActionEvent event) {
        String matricula = this.campoMatricula.getText();
        String senha = this.campoSenha.getText();
        if (matricula.equals("admin") && senha.equals("admin")) {
            try {
                Principal tela_principal = new Principal();
                tela_principal.start(new Stage());
            } catch (Exception ex) {
            }
        } else {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
