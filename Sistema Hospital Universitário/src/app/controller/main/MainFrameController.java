package app.controller.main;

import app.model.domain.Aluno;
import app.model.domain.Medico;
import app.model.domain.Professor;
import app.model.domain.Usuario;
import app.utilits.CredenciaisInvalidasException;
import app.utilits.Sistema;
import app.view.aluno.HomeAluno;
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
import app.view.main.CadastrarAluno;
import app.view.main.CadastrarMedico;
import app.view.main.CadastrarProfessor;
import app.view.main.MainFrame;
import app.view.medico.HomeMedico;
import app.view.professor.HomeProfessor;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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
    private AnchorPane anchorPane;

    @FXML
    public void acaoBotaoLogin(ActionEvent event) {
        String matricula = this.campoMatricula.getText().trim();
        String senha = this.campoSenha.getText();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Erro login");
        try {
            if (Usuario.autenticar(matricula, senha)) {
                if (Sistema.getSessao().getUsuario() instanceof Aluno) {
                    Aluno aluno = (Aluno) Sistema.getSessao().getUsuario();
                    if (aluno.getProfessor() == null) {
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        alert.setTitle("Aguarde autorização");
                        alert.setContentText("Aguarde até que o seu professor autorize seu acesso ao sistema.");
                        alert.showAndWait();
                    } else {
                        HomeAluno homeAluno = new HomeAluno();
                        homeAluno.start(new Stage());
                        MainFrame.getStage().close();
                    }
                } else if (Sistema.getSessao().getUsuario() instanceof Professor) {
                    HomeProfessor homeProfessor = new HomeProfessor();
                    homeProfessor.start(new Stage());
                    MainFrame.getStage().close();
                } else if (Sistema.getSessao().getUsuario() instanceof Medico) {
                    HomeMedico homeMedico = new HomeMedico();
                    homeMedico.start(new Stage());
                    MainFrame.getStage().close();
                }
            } else {
                alert.setContentText("Credenciais Inválidas.");
            }
        } catch (CredenciaisInvalidasException exception) {
            alert.setContentText(exception.getMessage());
            alert.showAndWait();
        } catch (Exception exception) {
        }
        this.campoMatricula.setText("");
        this.campoSenha.setText("");
    }

    @FXML
    public void cadastroProfessor(ActionEvent event) {
        try {
            CadastrarProfessor cadastrarProfessor = new CadastrarProfessor();
            cadastrarProfessor.start(new Stage());
            MainFrame.getStage().close();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
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
