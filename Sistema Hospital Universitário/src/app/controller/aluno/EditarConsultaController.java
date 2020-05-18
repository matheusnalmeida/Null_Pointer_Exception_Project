package app.controller.aluno;

import app.model.dao.PacienteAlunoRelatorioDAO;
import app.model.domain.ConsultaAux;
import app.model.domain.ImagemRelatorio;
import app.model.domain.PacienteAlunoRelatorio;
import app.view.aluno.CrudConsultas;
import app.view.aluno.EditarConsulta;
import app.view.aluno.MostrarImagem;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EditarConsultaController implements Initializable {

    @FXML
    private ImageView Icon;

    @FXML
    private Label Nome1;

    @FXML
    private Label Nome2;

    @FXML
    private JFXTextField cpfField;

    @FXML
    private Label tituloLabel;

    @FXML
    private ImageView Icon1;

    @FXML
    private JFXTextArea descricaoField;

    @FXML
    private JFXButton chooseFile;

    @FXML
    private JFXListView<String> listArquivos;

    @FXML
    private JFXTextField dataDaConsultaFIeld;

    @FXML
    private JFXTextField horarioDaConsultaFIeld;

    @FXML
    private JFXButton gerarBotao;

    @FXML
    private JFXButton cancelarBotao;

    private ConsultaAux consultaAux;

    private List<ImagemRelatorio> listaDeImagens;

    @FXML
    void cancelarAcao(ActionEvent event) {
        CrudConsultas.getStage().show();
        EditarConsulta.getStage().close();
    }

    @FXML
    void chooseFile(ActionEvent event) {
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
                    ImagemRelatorio novaImagem = new ImagemRelatorio();
                    novaImagem.setCodigo(this.consultaAux.getCodigoConsulta());
                    novaImagem.setNomeArquivo(arquivoSelecionado.getName());
                    novaImagem.setArquivo(byteObjects);
                    this.listaDeImagens.add(novaImagem);
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

    @FXML
    void gerarConsulta(ActionEvent event) {
        if (!"".equals(this.descricaoField.getText().trim())) {
            //Atualizando descricao da consulta
            PacienteAlunoRelatorioDAO pacienteAlunoRelatorioDao = new PacienteAlunoRelatorioDAO();
            PacienteAlunoRelatorio pacienteAlunoRelatorio = new PacienteAlunoRelatorio();
            pacienteAlunoRelatorio.setCodigo(this.consultaAux.getCodigoConsulta());
            PacienteAlunoRelatorio pacienteAlterar = pacienteAlunoRelatorioDao.read(pacienteAlunoRelatorio);
            pacienteAlterar.setDescricao(this.descricaoField.getText().trim());
            pacienteAlunoRelatorioDao.update(pacienteAlterar);
        }
    }

    @FXML
    void itemClicked(MouseEvent event) {
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
                    MostrarImagem.setImagem(this.listaDeImagens.get(indiceSelecionado).getArquivo());
                    mostrarImagem.start(new Stage());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void atualizaImagens() {
        if (this.listaDeImagens != null) {
            for (ImagemRelatorio imagem : this.listaDeImagens) {
                this.listArquivos.getItems().add(imagem.getNomeArquivo());
            }
        }
    }

    public void preencherCampos() {
        this.cpfField.setText(this.consultaAux.getCpfPaciente());
        this.dataDaConsultaFIeld.setText(this.consultaAux.getDataConsulta());
        this.descricaoField.setText(this.consultaAux.getDescricao());
        this.atualizaImagens();
    }

    public void recebeDados(ConsultaAux consultaAux, List<ImagemRelatorio> listaDeImagens) {
        this.consultaAux = consultaAux;
        this.listaDeImagens = listaDeImagens;
        this.preencherCampos();
    }
}
