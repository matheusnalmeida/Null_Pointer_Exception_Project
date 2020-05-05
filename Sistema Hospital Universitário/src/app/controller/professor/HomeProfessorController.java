package app.controller.professor;

import app.view.professor.HomeProfessor;
import app.view.professor.ViewProfessor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class HomeProfessorController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void minhaTurma(ActionEvent evt) {

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
