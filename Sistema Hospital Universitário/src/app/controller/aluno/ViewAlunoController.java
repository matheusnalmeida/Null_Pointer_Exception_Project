package app.controller.aluno;

import app.model.domain.Aluno;
import app.utilits.Sistema;
import app.view.aluno.EditarAluno;
import app.view.aluno.ViewAluno;
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

/**
 *
 * @author Matheus Nunes
 */
public class ViewAlunoController  implements Initializable {

    
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
    private Label tituloLabel111;

    @FXML
    private Label tituloLabel1111;

    @FXML
    private Label tituloLabel11111;

    @FXML
    private Label tituloLabel111111;

    @FXML
    private Label nome;

    @FXML
    private Label matricula;

    @FXML
    private Label anoResidencia;

    @FXML
    private Label dataNascimento;

    @FXML
    private Label matriculaProfessor;

    @FXML
    private JFXButton alterar;

    @FXML
    private JFXButton voltar;

    @FXML
    void alterar(ActionEvent event) {
        try {
            EditarAluno editarAluno = new EditarAluno();
            editarAluno.start(new Stage());
            ViewAluno.getStage().close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        ViewAluno.getStage().fireEvent(
                new WindowEvent(
                        ViewAluno.getStage(),
                        WindowEvent.WINDOW_CLOSE_REQUEST
                )
        );
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.preencherCampos();
    }
    
    public void preencherCampos() {
        Aluno aluno = (Aluno) Sistema.getSessao().getUsuario();
        this.nome.setText(aluno.getNome());
        this.matricula.setText(aluno.getMatricula());
        this.anoResidencia.setText(Integer.toString(aluno.getAnoResidencia()));
        this.dataNascimento.setText(aluno.getDataNascimento());
        this.matriculaProfessor.setText(aluno.getProfessor().getMatricula());
    }
}
