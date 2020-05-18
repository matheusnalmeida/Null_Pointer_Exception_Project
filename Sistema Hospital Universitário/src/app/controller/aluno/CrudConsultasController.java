package app.controller.aluno;

import app.model.dao.AlunoDAO;
import app.model.dao.ImagemRelatorioDAO;
import app.model.dao.PacienteAlunoRelatorioDAO;
import app.model.domain.Aluno;
import app.model.domain.ConsultaAux;
import app.model.domain.ImagemRelatorio;
import app.model.domain.PacienteAlunoRelatorio;
import app.utilits.Sistema;
import app.view.aluno.CrudConsultas;
import app.view.aluno.EditarConsulta;
import app.view.aluno.GerarConsulta;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CrudConsultasController implements Initializable {

    private List<PacienteAlunoRelatorio> listPacienteAlunoRelatorio;
    private ObservableList<ConsultaAux> observableListConsultaAux;
    @FXML
    private JFXButton novaConsultaBotao;
    @FXML
    private JFXButton removerConsulta;
    @FXML
    private TableView<ConsultaAux> tableViewPacienteAlunoRelatorio;
    @FXML
    private TableColumn<ConsultaAux, Integer> tableColumnCodigoConsulta;
    @FXML
    private TableColumn<ConsultaAux, String> tableColumnCpfPaciente;
    @FXML
    private TableColumn<ConsultaAux, String> tableColumnNomePaciente;
    @FXML
    private TableColumn<ConsultaAux, String> tableColumnDataConsulta;
    @FXML
    private TableColumn<ConsultaAux, String> tableColumnMedicoAutorizacao;
    @FXML
    private TableColumn<ConsultaAux, String> tableColumnDescricao;
    @FXML
    private TableColumn<ConsultaAux, String> tableColumnDataAutorizacao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.carregarTableViewPacienteAlunoRelatorio();
    }

    public void adicionarConsulta(ActionEvent evt) {
        try {
            GerarConsulta gerarConsulta = new GerarConsulta();
            gerarConsulta.start(new Stage());
            CrudConsultas.getStage().close();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void removerConsulta(ActionEvent evt) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        ConsultaAux consultaAux = this.tableViewPacienteAlunoRelatorio.getSelectionModel().getSelectedItem();
        if (consultaAux != null) {
            PacienteAlunoRelatorio pacienteAlunoRelatorio = new PacienteAlunoRelatorio();
            pacienteAlunoRelatorio.setCodigo(consultaAux.getCodigoConsulta());
            if (consultaAux.getMedicoAutorizacao().equals("")) {
                PacienteAlunoRelatorioDAO pacienteAlunoRelatorioDAO = new PacienteAlunoRelatorioDAO();
                if (pacienteAlunoRelatorioDAO.delete(pacienteAlunoRelatorio)) {
                    alert.setContentText("Consulta removida com sucesso.");
                    this.tableViewPacienteAlunoRelatorio.getItems().remove(consultaAux);
                } else {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("Não é possível remover a consulta.");
                }
            } else {
                alert.setContentText("Não é possível remover a consulta, pois o relatório associado a ela já foi autorizado.");
            }
        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Erro de seleção");
            alert.setContentText("Nenhuma consulta foi selecionada.");
        }
        alert.showAndWait();
    }

    public void carregarTableViewPacienteAlunoRelatorio() {
        AlunoDAO alunoDAO = new AlunoDAO();
        this.tableViewPacienteAlunoRelatorio.getItems().clear();
        this.listPacienteAlunoRelatorio = alunoDAO.getRelatorios((Aluno) Sistema.getSessao().getUsuario());
        this.tableColumnCodigoConsulta.setCellValueFactory(new PropertyValueFactory<>("CodigoConsulta"));
        this.tableColumnCpfPaciente.setCellValueFactory(new PropertyValueFactory<>("CpfPaciente"));
        this.tableColumnNomePaciente.setCellValueFactory(new PropertyValueFactory<>("NomePaciente"));
        this.tableColumnDataConsulta.setCellValueFactory(new PropertyValueFactory<>("DataConsulta"));
        this.tableColumnMedicoAutorizacao.setCellValueFactory(new PropertyValueFactory<>("CrmMedico"));
        this.tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("Descricao"));
        this.tableColumnDataAutorizacao.setCellValueFactory(new PropertyValueFactory<>("DataAutorizaoRelatorio"));
        if (!this.listPacienteAlunoRelatorio.isEmpty()) {
            List<ConsultaAux> consultaAuxList = new ArrayList<>();
            for (PacienteAlunoRelatorio pacienteAlunoRelatorio : this.listPacienteAlunoRelatorio) {
                int codigoConsulta = pacienteAlunoRelatorio.getCodigo();
                String cpfPaciente = pacienteAlunoRelatorio.getPaciente().getCpf();
                String nomePaciente = pacienteAlunoRelatorio.getPaciente().getNome();
                String dataConsulta = pacienteAlunoRelatorio.getDataAtendimento();
                String crmMedico = pacienteAlunoRelatorio.getMedicoAutorizacao() == null ? "" : pacienteAlunoRelatorio.getMedicoAutorizacao().getCrm();
                String descricao = pacienteAlunoRelatorio.getDescricao();
                String dataAutorizacao = pacienteAlunoRelatorio.getDataAutorizacao();
                ConsultaAux consultaAux = new ConsultaAux(codigoConsulta, cpfPaciente, nomePaciente, dataConsulta, crmMedico, descricao, dataAutorizacao);
                consultaAuxList.add(consultaAux);
            }
            this.observableListConsultaAux = FXCollections.observableArrayList(consultaAuxList);
            this.tableViewPacienteAlunoRelatorio.setItems(this.observableListConsultaAux);
        }
    }

    @FXML
    void itemSelected(MouseEvent event) {
        ConsultaAux consultaSelecionada = this.tableViewPacienteAlunoRelatorio.getSelectionModel().getSelectedItem();
        if (consultaSelecionada != null) {
            PacienteAlunoRelatorio p = new PacienteAlunoRelatorio();
            p.setCodigo(consultaSelecionada.getCodigoConsulta());
            ImagemRelatorioDAO imagemRelatorioDao = new ImagemRelatorioDAO();
            List<ImagemRelatorio> imagemRelatorio = imagemRelatorioDao.selectAll(p);
            try {
                EditarConsulta editarConsulta = new EditarConsulta(imagemRelatorio,consultaSelecionada);
                editarConsulta.start(new Stage());
                CrudConsultas.getStage().close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
