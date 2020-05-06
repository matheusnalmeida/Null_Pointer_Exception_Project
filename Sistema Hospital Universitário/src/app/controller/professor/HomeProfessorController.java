package app.controller.professor;

import app.view.professor.HomeProfessor;
import app.view.professor.MinhaTurma;
import app.view.professor.ViewProfessor;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class HomeProfessorController implements Initializable {
    
    @FXML
    private JFXButton minhaTurmaBotao;
    @FXML
    private JFXButton listarConsultasBotao;
    @FXML
    private JFXButton meusDadosBotao;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void minhaTurma(ActionEvent evt) {
        MinhaTurma minhaTurma = new MinhaTurma();
        try {
            minhaTurma.start(new Stage());
            HomeProfessor.getStage().close();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void listarConsulta(ActionEvent evt) {

    }

    public void meusDados(ActionEvent evt) {
        try {
            ViewProfessor viewProfessor = new ViewProfessor();
            viewProfessor.start(new Stage());
            HomeProfessor.getStage().close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
