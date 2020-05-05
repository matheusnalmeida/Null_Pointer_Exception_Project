package app.controller.aluno;

import app.model.dao.PacienteAlunoRelatorioDAO;
import app.model.dao.RelatorioDAO;
import app.model.domain.Aluno;
import app.model.domain.Paciente;
import app.model.domain.PacienteAlunoRelatorio;
import app.model.domain.Relatorio;
import app.utilits.CPF;
import app.utilits.Sistema;
import app.view.aluno.GerarConsulta;
import app.view.aluno.HomeAluno;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

public class GerarConsultaController implements Initializable {

    @FXML
    private JFXTextField cpfField;
    @FXML
    private JFXTextArea descricaoField;
    @FXML
    private JFXDatePicker dataAtendimentoField;
    @FXML
    private JFXButton gerarBotao;
    @FXML
    private JFXButton cancelarBotao;
    @FXML
    private JFXTimePicker horarioField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void gerarConsulta(ActionEvent evt) {
        LocalDateTime dataAtendimento = LocalDateTime.of(this.dataAtendimentoField.getValue(), this.horarioField.getValue());
        String descricao = this.descricaoField.getText();
        String cpfValue = this.cpfField.getText();
        Alert alert;
        try {
            CPF cpf = new CPF(cpfValue);
            Paciente paciente = new Paciente();
            paciente.setCpf(cpfValue);
            RelatorioDAO relatorioDAO = new RelatorioDAO();
            Relatorio relatorio = new Relatorio();
            relatorio.setDescricao(descricao);
            if (relatorioDAO.create(relatorio)) {
                PacienteAlunoRelatorioDAO pacienteAlunoRelatorioDAO = new PacienteAlunoRelatorioDAO();
                Aluno aluno = (Aluno) Sistema.getSessao().getUsuario();
                PacienteAlunoRelatorio pacienteAlunoRelatorio = new PacienteAlunoRelatorio(dataAtendimento.toString(), paciente, aluno);
                pacienteAlunoRelatorio.setRelatorio(relatorio);
                if (pacienteAlunoRelatorioDAO.create(pacienteAlunoRelatorio)) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Cadastro realizado.");
                    alert.setContentText("Cadastro da consulta realizado com sucesso!");
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Erro ao gerar consulta.");
                    alert.setContentText("Não foi possível conectar ao banco de dados.");
                }
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Erro ao gerar consulta.");
                alert.setContentText("Não foi possível conectar ao banco de dados.");
            }
        } catch (Exception exception) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Erro ao gerar consulta.");
            alert.setContentText(exception.getMessage());
        }
        alert.showAndWait();
        GerarConsulta.getStage().close();
        HomeAluno.getStage().show();
    }

    public void cancelarAcao(ActionEvent evt) {
        HomeAluno.getStage().show();
        GerarConsulta.getStage().close();
    }
}
