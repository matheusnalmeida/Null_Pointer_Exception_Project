package app.controller.aluno;

import app.model.dao.ImagemRelatorioDAO;
import app.model.dao.PacienteAlunoRelatorioDAO;
import app.model.dao.PacienteDAO;
import app.model.domain.Aluno;
import app.model.domain.ImagemRelatorio;
import app.model.domain.Paciente;
import app.model.domain.PacienteAlunoRelatorio;
import app.utilits.CPF;
import app.utilits.Sistema;
import app.view.aluno.CrudConsultas;
import app.view.aluno.GerarConsulta;
import app.view.aluno.MostrarImagem;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GerarConsultaController implements Initializable {

    private List<Paciente> listPaciente;
    private ObservableList<Paciente> observableListPaciente;

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

    @FXML
    private JFXComboBox comboboxPaciente;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.listaDeImagens = new ArrayList<>();
        this.carregarComboBox();;
    }

    public void gerarConsulta(ActionEvent evt) {
        LocalDateTime dataAtendimento = LocalDateTime.of(this.dataAtendimentoField.getValue(), this.horarioField.getValue());
        String descricao = this.descricaoField.getText();
        //String cpfValue = this.cpfField.getText();
        Alert alert;
        try {
            /*CPF cpf = new CPF(cpfValue);
            Paciente paciente = new Paciente();
            paciente.setCpf(cpfValue);*/
            Paciente paciente = (Paciente) this.comboboxPaciente.getSelectionModel().getSelectedItem();
            PacienteAlunoRelatorioDAO pacienteAlunoRelatorioDAO = new PacienteAlunoRelatorioDAO();
            Aluno aluno = (Aluno) Sistema.getSessao().getUsuario();
            PacienteAlunoRelatorio pacienteAlunoRelatorio = new PacienteAlunoRelatorio(dataAtendimento.toString(), paciente, aluno);
            pacienteAlunoRelatorio.setDescricao(descricao);
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
            int contador = 0;
            for (Byte[] imagem : this.listaDeImagens) {
                StringBuilder nomeArquivo = new StringBuilder();
                nomeArquivo.append(this.listArquivos.getItems().get(contador));
                contador++;
                ImagemRelatorioDAO imagemRelatorioDao = new ImagemRelatorioDAO();
                ImagemRelatorio imagemRelatorio = new ImagemRelatorio(pacienteAlunoRelatorio, imagem, nomeArquivo.toString());
                imagemRelatorioDao.create(imagemRelatorio);
            }
        } catch (Exception exception) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Erro ao gerar consulta.");
            alert.setContentText(exception.getMessage());
        }
        alert.showAndWait();
        GerarConsulta.getStage().close();
        CrudConsultas.getStage().show();
    }

    @FXML
    public void itemClicked(MouseEvent event) {
        try {
            if (this.listArquivos.getSelectionModel().getSelectedItem() != null) {
                Alert alert;
                String valor = this.listArquivos.getSelectionModel().getSelectedItem();
                int indiceSelecionado = this.listArquivos.getSelectionModel().getSelectedIndex();
                if (event.getButton().equals(MouseButton.SECONDARY)) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Remover Imagem");
                    alert.setContentText("Deseja remover a imagem selecionada?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        this.listArquivos.getItems().remove(indiceSelecionado);
                        this.listaDeImagens.remove(indiceSelecionado);
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Remover Imagem");
                        alert.setContentText("Item removido com sucesso");
                    }
                } else if (event.getButton().equals(MouseButton.PRIMARY)) {
                    MostrarImagem mostrarImagem = new MostrarImagem();
                    MostrarImagem.setImagem(this.listaDeImagens.get(indiceSelecionado));
                    mostrarImagem.start(new Stage());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void chooseFile(ActionEvent event) {
        Alert alert;
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "Arquivos de imagem", "*.png", "*.jpg");
        fc.setSelectedExtensionFilter(extFilter);
        File arquivoSelecionado = fc.showOpenDialog(null);
        if (!listArquivos.getItems().contains(arquivoSelecionado.getName())) {
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
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Arquivo ja existentes");
            alert.setContentText("Ja foi selecionado um arquivo com o nome informado");
            alert.showAndWait();
        }
    }

    public void cancelarAcao(ActionEvent evt) {
        CrudConsultas.getStage().show();
        GerarConsulta.getStage().close();
    }

    private void carregarComboBox() {
        PacienteDAO pacienteDAO = new PacienteDAO();
        this.listPaciente = pacienteDAO.selectAll();
        if (this.listPaciente != null) {
            this.observableListPaciente = FXCollections.observableArrayList(this.listPaciente);
            this.comboboxPaciente.setItems(this.observableListPaciente);
        }
    }
}
