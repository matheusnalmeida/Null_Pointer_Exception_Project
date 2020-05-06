package app.controller.professor;

import app.model.dao.AlunoDAO;
import app.model.dao.ProfessorDAO;
import app.model.domain.Aluno;
import app.model.domain.Professor;
import app.utilits.Sistema;
import app.view.professor.MinhaTurma;
import app.view.professor.SelecionarAluno;
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
import javafx.stage.Stage;

public class MinhaTurmaController implements Initializable {

    private List<Aluno> listAlunos;
    private ObservableList<Aluno> observableListAlunos;

    @FXML
    private JFXButton adicionarBotao;
    @FXML
    private TableView<Aluno> tableViewAlunos;
    @FXML
    private TableColumn<Aluno, String> tableColumnAlunoNome;
    @FXML
    private TableColumn<Aluno, String> tableColumnAlunoMatricula;
    @FXML
    private TableColumn<Aluno, String> tableColumnAlunoDataNascimento;
    @FXML
    private JFXButton removerBotao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.carregarTableViewAlunos();
        if (this.tableViewAlunos.getItems().isEmpty()) {
            this.removerBotao.setDisable(true);
        }
    }

    public void adicionarTurmaAction(ActionEvent evt) {
        MinhaTurma.getStage().close();
        try {
            SelecionarAluno selecionarAluno = new SelecionarAluno();
            selecionarAluno.start(new Stage());
        } catch (Exception exception) {
        }
    }

    public void carregarTableViewAlunos() {
        this.removerBotao.setDisable(false);
        this.tableViewAlunos.getItems().clear();
        this.tableColumnAlunoNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        this.tableColumnAlunoMatricula.setCellValueFactory(new PropertyValueFactory<>("Matricula"));
        this.tableColumnAlunoDataNascimento.setCellValueFactory(new PropertyValueFactory<>("DataNascimento"));
        ProfessorDAO professorDAO = new ProfessorDAO();
        this.listAlunos = professorDAO.getAlunos((Professor) Sistema.getSessao().getUsuario());
        if (this.listAlunos != null) {
            this.observableListAlunos = FXCollections.observableArrayList(this.listAlunos);
            this.tableViewAlunos.setItems(this.observableListAlunos);
        }
    }

    public void removerAlunoAction(ActionEvent evt) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Aluno aluno = this.tableViewAlunos.getSelectionModel().getSelectedItem();
        if (aluno != null) {
            aluno.setProfessor(null);
            AlunoDAO alunoDAO = new AlunoDAO();
            if (alunoDAO.update(aluno)) {
                this.tableViewAlunos.getItems().remove(aluno);
                alert.setTitle("Operação concluida");
                alert.setContentText("Aluno removido com sucesso");
                if (this.tableViewAlunos.getItems().isEmpty()) {
                    this.removerBotao.setDisable(true);
                }
            } else {
                alert.setTitle("Erro banco de dados");
                alert.setContentText("Não foi possível conectar ao banco de dados.");
            }
        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Erro de seleção");
            alert.setContentText("Nenhum aluno foi selecionado");
        }
        alert.showAndWait();
    }
}
