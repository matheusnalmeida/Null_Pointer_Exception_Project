package app.controller.aluno;

import app.view.aluno.CadastrarPaciente;
import app.view.aluno.HomeAluno;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import app.view.aluno.CrudConsultas;

public class HomeAlunoController implements Initializable {

    @FXML
    private JFXButton cadastrarPaciente;
    @FXML
    private JFXButton gerarConsulta;
    @FXML
    private JFXButton gerirRelatorios;
    @FXML
    private JFXButton meusDados;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void cadastrarPaciente(ActionEvent event) {
        try {
            CadastrarPaciente cadastroPaciente = new CadastrarPaciente();
            HomeAluno.getStage().close();
            cadastroPaciente.start(new Stage());
        } catch (Exception exception) {
        }
    }

    public void gerarConsulta(ActionEvent event) {
        try {
            CrudConsultas crudConsultas = new CrudConsultas();
            HomeAluno.getStage().close();
            crudConsultas.start(new Stage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void meusDados(ActionEvent event) {

    }

    public void gerirRelatorios(ActionEvent event) {

    }
}
