package app.controller.aluno;

import app.model.dao.AlunoDAO;
import app.model.domain.Aluno;
import app.utilits.EncryptionPassword;
import app.utilits.Sistema;
import app.view.aluno.EditarAluno;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.WindowEvent;

/**
 *
 * @author Matheus Nunes
 */
public class EditarAlunoController implements Initializable {


    @FXML
    private ImageView Icon;

    @FXML
    private Label Nome1;

    @FXML
    private Label Nome2;

    @FXML
    private JFXTextField nomeField;

    @FXML
    private JFXTextField matriculaField;

    @FXML
    private JFXPasswordField senhaField;

    @FXML
    private JFXTextField anoResidenciaField;

    @FXML
    private Label tituloLabel;

    @FXML
    private ImageView Icon1;

    @FXML
    private JFXPasswordField novaSenhaTextField;

    @FXML
    private JFXTextField matriculaProfessorField;

    @FXML
    private JFXTextField dataDeNascimentoField;

    @FXML
    private JFXButton alterar;

    @FXML
    private JFXButton cancelar;

    @FXML
    void alterar(ActionEvent event) {
        try {
            if (this.validaCamposVazio() && this.validaSenha()) {
                AlunoDAO alunoDao = new AlunoDAO();
                Aluno aluno = (Aluno) Sistema.getSessao().getUsuario();
                aluno.setSenha(EncryptionPassword.encrypt(this.novaSenhaTextField.getText()));
                aluno.setAnoResidencia(Integer.parseInt(this.anoResidenciaField.getText()));
                boolean alterado = alunoDao.update(aluno);
                if (alterado) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Dados Alterados Com Sucesso!");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Information Dialog");
                    alert.setContentText("Erro ao realizar cadastro.");
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Dados Invalidos!");
                alert.showAndWait();
            }
        } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("É necessario informar um numero no ano de residencia!");
                alert.showAndWait();
        }
        this.cancelarAcao(event);
    }

    @FXML
    void cancelarAcao(ActionEvent event) {
        EditarAluno.getStage().fireEvent(
                new WindowEvent(
                        EditarAluno.getStage(),
                        WindowEvent.WINDOW_CLOSE_REQUEST
                )
        );
    }

    boolean validaSenha() {
        return EncryptionPassword.encrypt(this.senhaField.getText())
                .equals(Sistema.getSessao().getUsuario().getSenha());
    }

    boolean validaCamposVazio() {
        return !"".equals(this.senhaField.getText())
                && !"".equals(this.novaSenhaTextField.getText())
                && !"".equals(this.anoResidenciaField.getText());
    }

    public void preencherCampos() {
        Aluno aluno = (Aluno) Sistema.getSessao().getUsuario();
        this.nomeField.setText(aluno.getNome());
        this.matriculaField.setText(aluno.getMatricula());
        this.anoResidenciaField.setText(Integer.toString(aluno.getAnoResidencia()));
        this.dataDeNascimentoField.setText(aluno.getDataNascimento());
        this.matriculaProfessorField.setText(aluno.getProfessor().getMatricula());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.preencherCampos();
    }
}
