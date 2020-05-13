package app.controller.aluno;

import app.model.dao.PacienteAlunoRelatorioDAO;
import app.model.domain.Aluno;
import app.model.domain.ImagemRelatorio;
import app.model.domain.Paciente;
import app.model.domain.PacienteAlunoRelatorio;
import app.utilits.CPF;
import app.utilits.Sistema;
import app.view.aluno.GerarConsulta;
import app.view.aluno.HomeAluno;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    @FXML
    private JFXListView<String> listArquivos;
    @FXML
    private JFXButton chooseFile;

    private List<Byte[]> listaDeImagens;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.listaDeImagens = new LinkedList<>();
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
            PacienteAlunoRelatorioDAO pacienteAlunoRelatorioDAO = new PacienteAlunoRelatorioDAO();
            Aluno aluno = (Aluno) Sistema.getSessao().getUsuario();
            PacienteAlunoRelatorio pacienteAlunoRelatorio = new PacienteAlunoRelatorio(dataAtendimento.toString(), paciente, aluno);
            pacienteAlunoRelatorio.setDescricao(descricao);
            //Cadastrando imagens no banco
            for (Byte[] imagem : this.listaDeImagens) {
                ImagemRelatorio imagemRelatorio = new ImagemRelatorio(pacienteAlunoRelatorio, imagem);
                //TODO => Inserir os dados na tabela de relatorios
            }
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

    @FXML
    void chooseFile(ActionEvent event) {
        Alert alert;
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "Arquivos de imagem", "*.png", "*.jpg");
        fc.setSelectedExtensionFilter(extFilter);
        File arquivoSelecionado = fc.showOpenDialog(null);
        File file;
        try {
            byte[] fileContent = Files.readAllBytes(arquivoSelecionado.toPath());
            Byte[] byteObjects = new Byte[fileContent.length];
            int i = 0;
            for (byte b : fileContent) {
                byteObjects[i++] = b;
            }
            if (arquivoSelecionado != null) {
                listArquivos.getItems().add(arquivoSelecionado.getName());
                this.listaDeImagens.add(byteObjects);
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Erro ao carregar Arquivo");
                alert.setContentText("O arquivo escolhido nao é valido");
                alert.showAndWait();
            }
        } catch (FileNotFoundException ex) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Arquivo nao encontrado");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        } catch (IOException ex) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Arquivo nao encontrado");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    public void cancelarAcao(ActionEvent evt) {
        HomeAluno.getStage().show();
        GerarConsulta.getStage().close();
    }
}
