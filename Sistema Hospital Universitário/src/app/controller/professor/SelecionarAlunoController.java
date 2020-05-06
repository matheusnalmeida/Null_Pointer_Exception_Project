package app.controller.professor;

import app.model.dao.AlunoDAO;
import app.model.dao.ProfessorDAO;
import app.model.domain.Aluno;
import app.model.domain.Professor;
import app.utilits.Sistema;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
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

public class SelecionarAlunoController implements Initializable {

    private List<Aluno> listAlunos;
    private ObservableList<Aluno> observableListAlunos;
    @FXML
    private TableView<Aluno> tableViewAlunos;
    @FXML
    private TableColumn<Aluno, String> tableColumnAlunoNome;
    @FXML
    private TableColumn<Aluno, String> tableColumnAlunoMatricula;
    @FXML
    private TableColumn<Aluno, Integer> tableColumnAlunoAnoResidencia;
    @FXML
    private TableColumn<Aluno, String> tableColumnAlunoDataNascimento;
    @FXML
    private JFXButton adicionarBotao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.carregarTableViewAlunos();
        if (this.tableViewAlunos.getItems().isEmpty()) {
            this.adicionarBotao.setDisable(true);
        }
    }

    public void adicionarAction(ActionEvent evt) {
        Aluno aluno = this.tableViewAlunos.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        if (aluno != null) {
            AlunoDAO alunoDAO = new AlunoDAO();
            aluno.setProfessor((Professor) Sistema.getSessao().getUsuario());
            if (alunoDAO.update(aluno)) {
                alert.setTitle("Operação concluida");
                alert.setContentText("Aluno adicionado com sucesso");
                this.tableViewAlunos.getItems().remove(aluno);
                if (this.tableViewAlunos.getItems().isEmpty()) {
                    this.adicionarBotao.setDisable(true);
                }
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Erro banco de dados");
                alert.setContentText("Não foi possível conectar ao banco de dados.");
            }
        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Erro de seleção");
            alert.setContentText("Nenhum aluno foi selecionado.");
        }
        alert.showAndWait();
    }

    public void carregarTableViewAlunos() {
        this.tableColumnAlunoNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        this.tableColumnAlunoMatricula.setCellValueFactory(new PropertyValueFactory<>("Matricula"));
        this.tableColumnAlunoDataNascimento.setCellValueFactory(new PropertyValueFactory<>("DataNascimento"));
        this.tableColumnAlunoAnoResidencia.setCellValueFactory(new PropertyValueFactory<>("AnoResidencia"));
        ProfessorDAO professorDAO = new ProfessorDAO();
        this.listAlunos = professorDAO.getAlunosSemProfessor();
        if (this.listAlunos != null) {
            this.observableListAlunos = FXCollections.observableArrayList(this.listAlunos);
            this.tableViewAlunos.setItems(this.observableListAlunos);
        }
    }
}
