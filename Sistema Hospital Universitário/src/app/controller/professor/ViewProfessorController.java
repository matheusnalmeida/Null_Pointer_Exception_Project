package app.controller.professor;

import app.model.domain.Professor;
import app.utilits.Sistema;
import app.view.professor.EditarProfessor;
import app.view.professor.HomeProfessor;
import app.view.professor.ViewProfessor;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ViewProfessorController implements Initializable {

    @FXML
    private ImageView Icon;

    @FXML
    private Label Nome1;

    @FXML
    private Label Nome2;

    @FXML
    private Label tituloLabel;

    @FXML
    private ImageView Icon1;

    @FXML
    private Label tituloLabel1;

    @FXML
    private Label tituloLabel11;

    @FXML
    private Label tituloLabel111;

    @FXML
    private Label tituloLabel1111;

    @FXML
    private Label tituloLabel11111;

    @FXML
    private Label nome;

    @FXML
    private Label matricula;

    /*@FXML
    private Label senha;*/

    @FXML
    private Label titulacao;

    @FXML
    private Label crm;

    @FXML
    private JFXButton alterar;

    @FXML
    private JFXButton voltar;

    @FXML
    void alterar(ActionEvent event) {
        try {
            EditarProfessor editProfessor = new EditarProfessor();
            editProfessor.start(new Stage());
            ViewProfessor.getStage().close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        ViewProfessor.getStage().fireEvent(
                        new WindowEvent(
                                ViewProfessor.getStage(),
                                WindowEvent.WINDOW_CLOSE_REQUEST
                        )
                );
    }
    
    public void preencherCampos(){
        Professor professor = (Professor) Sistema.getSessao().getUsuario();
        this.nome.setText(professor.getNome());
        this.crm.setText(professor.getCrm());
        this.titulacao.setText(professor.getTitulacao());
        this.matricula.setText(professor.getMatricula());
        //this.senha.setText(professor.getSenha());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.preencherCampos();
    }
}