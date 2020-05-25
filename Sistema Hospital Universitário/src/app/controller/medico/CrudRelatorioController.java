package app.controller.medico;

import app.model.dao.PacienteAlunoRelatorioDAO;
import app.model.domain.ConsultaAux;
import app.model.domain.Medico;
import app.model.domain.PacienteAlunoRelatorio;
import app.utilits.Sistema;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
                String dataConsulta = pacienteAlunoRelatorio.getDataAtendimento();
                String crmMedico = pacienteAlunoRelatorio.getMedicoAutorizacao() == null ? "" : pacienteAlunoRelatorio.getMedicoAutorizacao().getCrm();
                String descricao = pacienteAlunoRelatorio.getDescricao();
                String dataAutorizacao = pacienteAlunoRelatorio.getDataAutorizacao();
                String nomeMedico = pacienteAlunoRelatorio.getMedicoAutorizacao() == null ? "" : pacienteAlunoRelatorio.getMedicoAutorizacao().getNome();
                String matriculaAluno = pacienteAlunoRelatorio.getAluno().getMatricula();
                String nomeAluno = pacienteAlunoRelatorio.getAluno().getNome();
                ConsultaAux consultaAux = new ConsultaAux();
                consultaAux.setCodigoConsulta(codigoConsulta);
                consultaAux.setNomePaciente(nomePaciente);
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

    }

    public void autorizarAction(ActionEvent evt) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        ConsultaAux consultaAux = this.tableViewPacienteAlunoRelatorio.getSelectionModel().getSelectedItem();
        if (consultaAux != null) {
            if (!consultaAux.getMedicoAutorizacao().equals("")) {
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
            }
        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Erro de seleção");
            alert.setContentText("Nenhum relatório foi selecionado.");
        }
        alert.showAndWait();
    }
}
