package app.controller.medico;

import app.model.dao.ImagemRelatorioDAO;
import app.model.dao.PacienteAlunoRelatorioDAO;
import app.model.domain.ConsultaAux;
import app.model.domain.ImagemRelatorio;
import app.model.domain.Medico;
import app.model.domain.PacienteAlunoRelatorio;
import app.utilits.Sistema;
import app.view.medico.CrudRelatorio;
import com.aspose.pdf.HtmlSaveOptions;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CrudRelatorioController implements Initializable {

    private List<PacienteAlunoRelatorio> listPacienteAlunoRelatorio;
    private ObservableList<ConsultaAux> observableListConsultaAux;
    @FXML
    private TableView<ConsultaAux> tableViewPacienteAlunoRelatorio;
    @FXML
    private TableColumn<ConsultaAux, Integer> tableColumnCodigoConsulta;
    @FXML
    private TableColumn<ConsultaAux, String> tableColumnDataConsulta;
    @FXML
    private TableColumn<ConsultaAux, String> tableColumnMatriculaAluno;
    @FXML
    private TableColumn<ConsultaAux, String> tableColumnNomeAluno;
    @FXML
    private TableColumn<ConsultaAux, String> tableColumnNomePaciente;
    @FXML
    private TableColumn<ConsultaAux, String> tableColumnDescricao;
    @FXML
    private TableColumn<ConsultaAux, String> tableColumnDataAutorizacao;
    @FXML
    private TableColumn<ConsultaAux, String> tableColumnCRMMedicoAutorizacao;
    @FXML
    private TableColumn<ConsultaAux, String> tableColumnNomeMedicoAutorizacao;
    @FXML
    private JFXButton vizualizarBotao;
    @FXML
    private JFXButton autorizarBotao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.carregarTableView();
    }

    private void carregarTableView() {
        PacienteAlunoRelatorioDAO pacienteAlunoRelatorioDAO = new PacienteAlunoRelatorioDAO();
        this.listPacienteAlunoRelatorio = pacienteAlunoRelatorioDAO.selectAll();
        this.tableViewPacienteAlunoRelatorio.getItems().clear();
        this.tableColumnCodigoConsulta.setCellValueFactory(new PropertyValueFactory<>("CodigoConsulta"));
        this.tableColumnNomePaciente.setCellValueFactory(new PropertyValueFactory<>("NomePaciente"));
        this.tableColumnDataConsulta.setCellValueFactory(new PropertyValueFactory<>("DataConsulta"));
        this.tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("Descricao"));
        this.tableColumnDataAutorizacao.setCellValueFactory(new PropertyValueFactory<>("DataAutorizacao"));
        this.tableColumnNomeAluno.setCellValueFactory(new PropertyValueFactory<>("NomeAluno"));
        this.tableColumnMatriculaAluno.setCellValueFactory(new PropertyValueFactory<>("MatriculaAluno"));
        this.tableColumnCRMMedicoAutorizacao.setCellValueFactory(new PropertyValueFactory<>("MedicoAutorizacao"));
        this.tableColumnNomeMedicoAutorizacao.setCellValueFactory(new PropertyValueFactory<>("NomeMedicoAutorizacao"));
        if (this.listPacienteAlunoRelatorio != null && !this.listPacienteAlunoRelatorio.isEmpty()) {
            List<ConsultaAux> consultaAuxList = new ArrayList<>();
            for (PacienteAlunoRelatorio pacienteAlunoRelatorio : this.listPacienteAlunoRelatorio) {
                int codigoConsulta = pacienteAlunoRelatorio.getCodigo();
                String nomePaciente = pacienteAlunoRelatorio.getPaciente().getNome();
                String cpfPaciente = pacienteAlunoRelatorio.getPaciente().getCpf();
                String dataConsulta = pacienteAlunoRelatorio.getDataAtendimento();
                String crmMedico = pacienteAlunoRelatorio.getMedicoAutorizacao() == null ? "Não autorizado"
                        : pacienteAlunoRelatorio.getMedicoAutorizacao().getCrm();
                String descricao = pacienteAlunoRelatorio.getDescricao();
                String dataAutorizacao = pacienteAlunoRelatorio.getDataAutorizacao();
                String nomeMedico = pacienteAlunoRelatorio.getMedicoAutorizacao() == null ? "Não autorizado"
                        : pacienteAlunoRelatorio.getMedicoAutorizacao().getNome();
                String matriculaAluno = pacienteAlunoRelatorio.getAluno().getMatricula();
                String nomeAluno = pacienteAlunoRelatorio.getAluno().getNome();
                ConsultaAux consultaAux = new ConsultaAux();
                consultaAux.setCodigoConsulta(codigoConsulta);
                consultaAux.setNomePaciente(nomePaciente);
                consultaAux.setCpfPaciente(cpfPaciente);
                consultaAux.setDataConsulta(dataConsulta);
                consultaAux.setMedicoAutorizacao(crmMedico);
                consultaAux.setDescricao(descricao);
                consultaAux.setDataAutorizacao(dataAutorizacao);
                consultaAux.setNomeMedicoAutorizacao(nomeMedico);
                consultaAux.setMatriculaAluno(matriculaAluno);
                consultaAux.setNomeAluno(nomeAluno);
                consultaAuxList.add(consultaAux);
            }
            this.observableListConsultaAux = FXCollections.observableArrayList(consultaAuxList);
            this.tableViewPacienteAlunoRelatorio.setItems(this.observableListConsultaAux);
        }
    }

    public void vizualizarAction(ActionEvent evt) {
        ConsultaAux consultaAux = this.tableViewPacienteAlunoRelatorio.getSelectionModel().getSelectedItem();
        if (consultaAux != null) {
            ImagemRelatorioDAO imagemRelatorio = new ImagemRelatorioDAO();
            PacienteAlunoRelatorio pacienteAlunoRelatorio = new PacienteAlunoRelatorio();
            pacienteAlunoRelatorio.setCodigo(consultaAux.getCodigoConsulta());
            List<ImagemRelatorio> imagens = imagemRelatorio.selectAll(pacienteAlunoRelatorio);
            this.generatePdf(consultaAux, imagens);
            File f = new File("relatorio.pdf");
            try {
                Desktop.getDesktop().browse(f.toURI());
            } catch (IOException ex) {
            }
        }
    }

    public void autorizarAction(ActionEvent evt) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        ConsultaAux consultaAux = this.tableViewPacienteAlunoRelatorio.getSelectionModel().getSelectedItem();
        if (consultaAux != null) {
            if (!consultaAux.getMedicoAutorizacao().equals("Não autorizado")) {
                alert.setTitle("Autorização");
                alert.setContentText("O relatório já está autorizado.");
            } else {
                LocalDateTime dataAutorizacao = LocalDateTime.now();
                Medico medico = (Medico) Sistema.getSessao().getUsuario();
                PacienteAlunoRelatorioDAO pacienteAlunoRelatorioDAO1 = new PacienteAlunoRelatorioDAO();
                PacienteAlunoRelatorio pacienteAlunoRelatorio = new PacienteAlunoRelatorio();
                pacienteAlunoRelatorio.setCodigo(consultaAux.getCodigoConsulta());
                pacienteAlunoRelatorio = pacienteAlunoRelatorioDAO1.read(pacienteAlunoRelatorio);
                pacienteAlunoRelatorio.setDataAutorizacao(dataAutorizacao.toString());
                pacienteAlunoRelatorio.setMedicoAutorizacao(medico);
                consultaAux.setDataAutorizacao(dataAutorizacao.toString());
                consultaAux.setMedicoAutorizacao(medico.getCrm());
                consultaAux.setNomeMedicoAutorizacao(medico.getNome());
                PacienteAlunoRelatorioDAO pacienteAlunoRelatorioDAO2 = new PacienteAlunoRelatorioDAO();
                pacienteAlunoRelatorioDAO2.update(pacienteAlunoRelatorio);
                alert.setTitle("Autorização concluída.");
                alert.setContentText("O relatório foi autorizado com sucesso.");
                this.tableViewPacienteAlunoRelatorio.refresh();
            }
        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Erro de seleção");
            alert.setContentText("Nenhum relatório foi selecionado.");
        }
        alert.showAndWait();
    }

    private void generatePdf(ConsultaAux consultaAux, List<ImagemRelatorio> imagens) {
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream("relatorio.pdf");
            PdfWriter writer = new PdfWriter(fileOut);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            pdf.addNewPage(1);
            //Titulo Principal
            Paragraph titulo = new Paragraph("Dados Da Consulta");
            titulo.setTextAlignment(TextAlignment.CENTER);
            titulo.setFontSize(20);
            document.add(titulo);
            document.add(new Paragraph("\n\n"));
            document.add(new LineSeparator(new SolidLine()));
            //Sessao Paciente
            Paragraph tituloPaciente = new Paragraph("Dados Do Paciente");
            tituloPaciente.setTextAlignment(TextAlignment.CENTER);
            tituloPaciente.setFontSize(15);
            document.add(tituloPaciente);
            com.itextpdf.layout.element.List listPaciente = new com.itextpdf.layout.element.List()
                    .setSymbolIndent(12)
                    .setListSymbol("\u2022");
            ListItem nomePaciente = new ListItem("Nome do paciente: " + consultaAux.getNomePaciente());
            nomePaciente.setFontSize(12);
            ListItem cpfPaciente = new ListItem("CPF do paciente: " + consultaAux.getCpfPaciente());
            cpfPaciente.setFontSize(12);
            listPaciente.add(nomePaciente)
                    .add(cpfPaciente);
            document.add(listPaciente);
            document.add(new Paragraph("\n"));
            document.add(new LineSeparator(new SolidLine()));
            //Sessao Medico
            Paragraph tituloMedico = new Paragraph("Dados Do Medico");
            tituloMedico.setTextAlignment(TextAlignment.CENTER);
            tituloMedico.setFontSize(15);
            document.add(tituloMedico);
            com.itextpdf.layout.element.List listMedico = new com.itextpdf.layout.element.List()
                    .setSymbolIndent(12)
                    .setListSymbol("\u2022");
            ListItem nomeMedico = new ListItem("Nome do medico: " + consultaAux.getNomeMedicoAutorizacao());
            nomeMedico.setFontSize(12);
            ListItem crmMedico = new ListItem("CRM do medico: " + consultaAux.getMedicoAutorizacao());
            crmMedico.setFontSize(12);
            listMedico.add(nomeMedico)
                    .add(crmMedico);
            document.add(listMedico);
            document.add(new Paragraph("\n"));
            document.add(new LineSeparator(new SolidLine()));
            //Dados Gerais
            Paragraph tituloDadosGerais = new Paragraph("Dados Gerais");
            tituloDadosGerais.setTextAlignment(TextAlignment.CENTER);
            tituloDadosGerais.setFontSize(15);
            document.add(tituloDadosGerais);
            com.itextpdf.layout.element.List listDadosGerais = new com.itextpdf.layout.element.List()
                    .setSymbolIndent(12)
                    .setListSymbol("\u2022");
            String[] dataProcessada = consultaAux.getDataConsulta().split("T");
            ListItem dataDaConsulta = new ListItem("Data da consulta: " + dataProcessada[0]);
            nomeMedico.setFontSize(12);
            ListItem horarioDaConsulta = new ListItem("Horario da consulta: " + dataProcessada[1]);
            crmMedico.setFontSize(12);
            ListItem descricaoDaConsulta = new ListItem("Descrição da consulta: " + consultaAux.getDescricao());
            descricaoDaConsulta.setFontSize(12);
            listDadosGerais.add(dataDaConsulta)
                    .add(horarioDaConsulta)
                    .add(descricaoDaConsulta);
            document.add(listDadosGerais);
            document.add(new Paragraph("\n"));
            document.add(new LineSeparator(new SolidLine()));
            //Adicionando imagens no pdf
            if (imagens != null) {
                Paragraph tituloImagens = new Paragraph("Imagens");
                tituloImagens.setTextAlignment(TextAlignment.CENTER);
                tituloImagens.setFontSize(15);
                document.add(tituloImagens);
                for (ImagemRelatorio imagem : imagens) {
                    byte[] imagemConvertida = new byte[imagem.getArquivo().length];
                    int cont = 0;
                    for (Byte bite : imagem.getArquivo()) {
                        imagemConvertida[cont++] = bite;
                    }
                    document.add(new Image(ImageDataFactory.create(imagemConvertida)));
                }
            }
            document.close();
        } catch (FileNotFoundException ex) {
        } finally {
            try {
                fileOut.close();
            } catch (IOException ex) {
            }
        }
    }
}
