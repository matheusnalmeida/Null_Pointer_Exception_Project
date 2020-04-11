package app.controller;

import app.model.domain.Usuario;
import app.utilits.CredenciaisInvalidasException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import app.view.CadastrarAluno;
import app.view.CadastrarMedico;
import app.view.CadastrarProfessor;
import app.view.MainFrame;
import app.view.Principal;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Matheus Nunes
 */
public class MainFrameController implements Initializable {

    @FXML
    private JFXTextField campoMatricula;
    @FXML
    private JFXPasswordField campoSenha;
    @FXML
    private JFXButton botaoLogin;
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
    private Label tituloLabel1;
    @FXML
    private Label tituloLabel2;
    @FXML
    private ImageView imagem;

    @FXML
    public void acaoBotaoLogin(ActionEvent event) {
        /*Cada tipo de entidade do sistema tem uma senha que irá terminar com a primeira letra
        //que identifica o nome da entidade. A partir dessa última letra, redirecionar o usuário
        para uma home adequada.*/
        String matricula = this.campoMatricula.getText().trim();
        String senha = this.campoSenha.getText();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Erro login");
        alert.setContentText("Erro ao realizar cadastro.");
        try {
            if (Usuario.autenticar(matricula, senha)) {
                try {
                    Principal principal = new Principal();
                    principal.start(new Stage());
                    MainFrame.getStage().close();
                    this.campoMatricula.setText("");
                    this.campoSenha.setText("");
                } catch (Exception exception) {
                }
            } else {
                alert.setContentText("Credenciais Inválidas.");
            }
        } catch (CredenciaisInvalidasException exception) {
            alert.setContentText(exception.getMessage());
            alert.showAndWait();
        }
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
    }
}
