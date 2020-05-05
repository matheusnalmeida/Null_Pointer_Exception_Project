package app.controller.professor;

import app.model.dao.ProfessorDAO;
import app.model.domain.Professor;
import app.utilits.EncryptionPassword;
import app.utilits.Sistema;
import app.view.professor.EditarProfessor;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.WindowEvent;
import javafx.scene.control.Alert;

public class EditarProfessorController implements Initializable{
    
    @FXML
    private ImageView Icon;

    @FXML
    private Label Nome1;

    @FXML
    private Label Nome2;

    @FXML
    private JFXTextField nomeField;

    @FXML
    private JFXTextField crmField;

    @FXML
    private JFXTextField titulacaoField;

    @FXML
    private JFXTextField matriculaField;

    @FXML
    private JFXPasswordField senhaField;

    @FXML
    private Label tituloLabel;

    @FXML
    private ImageView Icon1;

    @FXML
    private JFXButton alterar;

    @FXML
    private JFXButton cancelar;

    @FXML
    private JFXPasswordField novaSenha;

    @FXML
    void alterar(ActionEvent event) {
        if(this.validaCamposVazio() && this.validaSenha()){
            ProfessorDAO professorDao = new ProfessorDAO();
            Professor professor = (Professor) Sistema.getSessao().getUsuario();
            professor.setSenha(EncryptionPassword.encrypt(this.novaSenha.getText()));
            professor.setTitulacao(this.titulacaoField.getText());
            boolean alterado = professorDao.update(professor);
            if(alterado){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Dados Alterados Com Sucesso!");
                alert.showAndWait();
                this.cancelarAction(event);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Dados Invalidos!");
            alert.showAndWait();
        }
    }
    
    boolean validaSenha(){
        return EncryptionPassword.encrypt(this.senhaField.getText()).equals
        (Sistema.getSessao().getUsuario().getSenha());
    }
    
    boolean validaCamposVazio(){
        return !"".equals(this.titulacaoField.getText()) && 
                !"".equals(this.senhaField.getText()) && 
                !"".equals(this.novaSenha.getText());
    }
    
    @FXML
    void cancelarAction(ActionEvent event) {
        EditarProfessor.getStage().fireEvent(
                new WindowEvent(
                        EditarProfessor.getStage(),
                        WindowEvent.WINDOW_CLOSE_REQUEST
                )
        );
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.preencherCampos();
    }
    
    public void preencherCampos(){
        Professor professor = (Professor) Sistema.getSessao().getUsuario();
        this.nomeField.setText(professor.getNome());
        this.crmField.setText(professor.getCrm());
        this.titulacaoField.setText(professor.getTitulacao());
        this.matriculaField.setText(professor.getMatricula());
    }
}
