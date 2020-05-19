package app.controller.professor;

import app.model.dao.ImagemRelatorioDAO;
import app.model.dao.ProfessorDAO;
import app.model.domain.ConsultaAux;
import app.model.domain.ImagemRelatorio;
import app.model.domain.PacienteAlunoRelatorio;
import app.model.domain.Professor;
import app.utilits.Sistema;
import app.view.aluno.EditarConsulta;
import app.view.professor.ListarConsultasProfessor;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ListarConsultasProfessorController implements Initializable {

    private List<PacienteAlunoRelatorio> listPacienteAlunoRelatorio;
    private ObservableList<ConsultaAux> observableListConsultaAux;
    @FXML
    private TableView<ConsultaAux> tableViewPacienteAlunoRelatorio;
    @FXML
    private TableColumn<ConsultaAux, Integer> tableColumnCodigoConsulta;
    @FXML
    private TableColumn<ConsultaAux, String> tableColumnMatriculaAluno;
    @FXML
    private TableColumn<ConsultaAux, String> tableColumnNomeAluno;
    @FXML
    private TableColumn<ConsultaAux, String> tableColumnNomePaciente;
    @FXML
    private TableColumn<ConsultaAux, String> tableColumnDataConsulta;
    @FXML
    private TableColumn<ConsultaAux, String> tableColumnDescricao;
    @FXML
    private TableColumn<ConsultaAux, String> tableColumnAutorizacaoMedica;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.carregarTableViewConsultas();
    }

    public void carregarTableViewConsultas() {
        this.tableViewPacienteAlunoRelatorio.getItems().clear();
        ProfessorDAO professorDAO = new ProfessorDAO();
        this.listPacienteAlunoRelatorio = professorDAO.getRelatoriosAlunos((Professor) Sistema.getSessao().getUsuario());
        this.tableColumnCodigoConsulta.setCellValueFactory(new PropertyValueFactory<>("CodigoConsulta"));
        this.tableColumnMatriculaAluno.setCellValueFactory(new PropertyValueFactory<>("MatriculaAluno"));
        this.tableColumnNomeAluno.setCellValueFactory(new PropertyValueFactory<>("NomeAluno"));
        this.tableColumnNomePaciente.setCellValueFactory(new PropertyValueFactory<>("NomePaciente"));
        this.tableColumnDataConsulta.setCellValueFactory(new PropertyValueFactory<>("DataConsulta"));
        this.tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("Descricao"));
        this.tableColumnAutorizacaoMedica.setCellValueFactory(new PropertyValueFactory<>("MedicoAutorizacao"));
        if (this.listPacienteAlunoRelatorio != null) {
            List<ConsultaAux> consultaAuxList = new ArrayList<>();
            for (PacienteAlunoRelatorio pacienteAlunoRelatorio : this.listPacienteAlunoRelatorio) {
                int codigoConsulta = pacienteAlunoRelatorio.getCodigo();
                String nomePaciente = pacienteAlunoRelatorio.getPaciente().getNome();
                String dataConsulta = pacienteAlunoRelatorio.getDataAtendimento();
                String crmMedico = pacienteAlunoRelatorio.getMedicoAutorizacao() == null ? "" : pacienteAlunoRelatorio.getMedicoAutorizacao().getCrm();
                String descricao = pacienteAlunoRelatorio.getDescricao();
                String matriculaAluno = pacienteAlunoRelatorio.getAluno().getMatricula();
                String nomeAluno = pacienteAlunoRelatorio.getAluno().getNome();
                ConsultaAux consultaAux = new ConsultaAux();
                consultaAux.setCodigoConsulta(codigoConsulta);
                consultaAux.setMatriculaAluno(matriculaAluno);
                consultaAux.setNomeAluno(nomeAluno);
                consultaAux.setNomePaciente(nomePaciente);
                consultaAux.setMedicoAutorizacao(crmMedico);
                consultaAux.setDescricao(descricao);
                consultaAux.setDataConsulta(dataConsulta);
                consultaAuxList.add(consultaAux);
            }
            this.observableListConsultaAux = FXCollections.observableArrayList(consultaAuxList);
            this.tableViewPacienteAlunoRelatorio.setItems(this.observableListConsultaAux);
        }
    }
    
    
    @FXML
    void itemClicked(MouseEvent event) {
        ConsultaAux consultaSelecionada = this.tableViewPacienteAlunoRelatorio.getSelectionModel().getSelectedItem();
        if (consultaSelecionada != null) {
            PacienteAlunoRelatorio p = new PacienteAlunoRelatorio();
            p.setCodigo(consultaSelecionada.getCodigoConsulta());
            ImagemRelatorioDAO imagemRelatorioDao = new ImagemRelatorioDAO();
            List<ImagemRelatorio> imagemRelatorio = imagemRelatorioDao.selectAll(p);
            try {
                EditarConsulta editarConsulta = new EditarConsulta(imagemRelatorio,consultaSelecionada);
                editarConsulta.start(new Stage());
                ListarConsultasProfessor.getStage().close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
