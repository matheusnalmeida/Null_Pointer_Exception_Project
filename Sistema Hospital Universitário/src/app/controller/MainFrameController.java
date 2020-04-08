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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import app.view.CadastrarAluno;
import app.view.CadastrarMedico;
import app.view.CadastrarProfessor;
import app.view.MainFrame;

/**
 * FXML Controller class
 *
 * @author Matheus Nunes
 */
public class MainFrameController implements Initializable {

    @FXML
    private TextField campoMatricula;
    @FXML
    private PasswordField campoSenha;
    @FXML
    private Button botaoLogin;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menu;
    @FXML
    private MenuItem cadastroProfessor;
    @FXML
    private MenuItem cadastroAluno;
    @FXML
    private MenuItem cadastroMedico;
    @FXML
    private Label titulo;
    @FXML
    private Label matriculaLabel;
    @FXML
    private Label senhaLabel;

    @FXML
    public void acaoBotaoLogin(ActionEvent event) {
        /*Cada tipo de entidade do sistema tem uma senha que irá terminar com a primeira letra
        //que identifica o nome da entidade. A partir dessa última letra, redirecionar o usuário
        para uma home adequada.*/
        /*String matricula = this.campoMatricula.getText();
        String senha = this.campoSenha.getText();
        if (matricula.equals("admin") && senha.equals("admin")) {
            try {
                Principal tela_principal = new Principal();
                tela_principal.start(new Stage());
            } catch (Exception ex) {
            }
        } else {
        }*/
    }

    @FXML
    public void cadastroProfessor(ActionEvent event) {
        try {
            CadastrarProfessor cadastrarProfessor = new CadastrarProfessor();
            cadastrarProfessor.start(new Stage());
            MainFrame.getStage().close();
        } catch (Exception exception) {

        }
    }

    @FXML
    public void cadastrarAluno(ActionEvent event) {
        try {
            CadastrarAluno cadastrarAluno = new CadastrarAluno();
            cadastrarAluno.start(new Stage());
            MainFrame.getStage().close();
        } catch (Exception exception) {

        }
    }

    @FXML
    public void cadastrarMedico(ActionEvent event) {
        try {
            CadastrarMedico cadastrarMedico = new CadastrarMedico();
            cadastrarMedico.start(new Stage());
            MainFrame.getStage().close();
        } catch (Exception exception) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
