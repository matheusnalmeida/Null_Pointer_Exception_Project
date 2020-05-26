package app.controller.medico;

import app.model.dao.EmissaoPedidoExameDAO;
import app.model.dao.MedicoDAO;
import app.model.dao.PedidoExameDAO;
import app.model.domain.EmissaoPedidoExame;
import app.model.domain.Medico;
import app.model.domain.Paciente;
import app.model.domain.PedidoExame;
import app.model.domain.PedidoExameAux;
import app.utilits.Sistema;
import app.view.medico.CrudPedidosExame;
import app.view.medico.EditarPedidoExame;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CrudPedidosExamesController implements Initializable {

    private List<EmissaoPedidoExame> listEmissaoPedidoExame;
    private ObservableList<PedidoExameAux> observableListEmissaoPedidoExame;
    @FXML
    private ImageView imageView;
    @FXML
    private TableView<PedidoExameAux> pedidosExameTableView;
    @FXML
    private TableColumn<PedidoExameAux, Integer> tableColumnCodigoPedidoExame;
    @FXML
    private TableColumn<PedidoExameAux, String> tableColumnDataExame;
    @FXML
    private TableColumn<PedidoExameAux, String> tableColumnNomePaciente;
    @FXML
    private TableColumn<PedidoExameAux, String> tableColumnHipoteseDiagnostica;
    @FXML
    private TableColumn<PedidoExameAux, String> tableColumnRecomendacoes;
    @FXML
    private TableColumn<PedidoExameAux, String> tableColumnTipoExame;
    @FXML
    private JFXButton editarBotao;
    @FXML
    private JFXButton visualizarBotao;
    @FXML
    private JFXButton removerBotao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.carregarTableView();
    }

    private void carregarTableView() {
        MedicoDAO medicoDAO = new MedicoDAO();
        this.pedidosExameTableView.getItems().clear();
        this.tableColumnCodigoPedidoExame.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
        this.tableColumnDataExame.setCellValueFactory(new PropertyValueFactory<>("DataExame"));
        this.tableColumnNomePaciente.setCellValueFactory(new PropertyValueFactory<>("NomePaciente"));
        this.tableColumnHipoteseDiagnostica.setCellValueFactory(new PropertyValueFactory<>("HipoteseDiagnostica"));
        this.tableColumnRecomendacoes.setCellValueFactory(new PropertyValueFactory<>("Recomendacoes"));
        this.tableColumnTipoExame.setCellValueFactory(new PropertyValueFactory<>("TipoExame"));
        this.listEmissaoPedidoExame = medicoDAO.getPedidosExames((Medico) Sistema.getSessao().getUsuario());
        if (this.listEmissaoPedidoExame != null) {
            List<PedidoExameAux> pedidoExameAuxList = new ArrayList<>();
            for (EmissaoPedidoExame emissaoPedidoExame : this.listEmissaoPedidoExame) {
                int codigo = emissaoPedidoExame.getPedidoExame().getCodigo();
                String dataExame = emissaoPedidoExame.getPedidoExame().getDataExame();
                String nomePaciente = emissaoPedidoExame.getPaciente().getNome();
                String hipoteseDiagnostica = emissaoPedidoExame.getPedidoExame().getHipoteseDiagnostica();
                String recomendacoes = emissaoPedidoExame.getPedidoExame().getRecomendacoes();
                String tipoExame = emissaoPedidoExame.getPedidoExame().getTipoExame();
                String cpfPaciente = emissaoPedidoExame.getPaciente().getCpf();
                PedidoExameAux pedidoExameAux = new PedidoExameAux(codigo, dataExame, nomePaciente, hipoteseDiagnostica, recomendacoes, tipoExame);
                pedidoExameAux.setCpfPaciente(cpfPaciente);
                pedidoExameAuxList.add(pedidoExameAux);
            }
            this.observableListEmissaoPedidoExame = FXCollections.observableArrayList(pedidoExameAuxList);
            this.pedidosExameTableView.setItems(this.observableListEmissaoPedidoExame);
        }
    }

    public void visualizarAction(ActionEvent evt) {

    }

    public void editarAction(ActionEvent evt) {
        PedidoExameAux pedidoExameAux = this.pedidosExameTableView.getSelectionModel().getSelectedItem();
        if (pedidoExameAux != null) {
            Paciente paciente = new Paciente();
            paciente.setCpf(pedidoExameAux.getCpfPaciente());
            String recomendacoes = pedidoExameAux.getRecomendacoes();
            String hipoteseDiagnostica = pedidoExameAux.getHipoteseDiagnostica();
            String tipoExame = pedidoExameAux.getTipoExame();
            PedidoExame pedidoExame = new PedidoExame(recomendacoes, pedidoExameAux.getDataExame(), hipoteseDiagnostica, tipoExame);
            pedidoExame.setCodigo(pedidoExameAux.getCodigo());
            EditarPedidoExame editarPedidoExame = new EditarPedidoExame(pedidoExame, paciente);
            try {
                editarPedidoExame.start(new Stage());
                EditarPedidoExame.getStage().show();
                CrudPedidosExame.getStage().close();
            } catch (Exception exception) {
            }
        }
    }

    public void removerAction(ActionEvent evt) {
        PedidoExameAux pedidoExameAux = this.pedidosExameTableView.getSelectionModel().getSelectedItem();
        if (pedidoExameAux != null) {
            Alert alert;
            EmissaoPedidoExameDAO emissaoPedidoExameDAO = new EmissaoPedidoExameDAO();
            PedidoExameDAO pedidoExameDAO = new PedidoExameDAO();
            PedidoExame pedidoExame = new PedidoExame();
            pedidoExame.setCodigo(pedidoExameAux.getCodigo());
            if (emissaoPedidoExameDAO.deletarPorPedidoExame(pedidoExame) && pedidoExameDAO.delete(pedidoExame)) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Pedido removido");
                alert.setContentText("O pedido de exame foi removido com sucesso!");
                this.pedidosExameTableView.getItems().remove(pedidoExameAux);
                this.pedidosExameTableView.refresh();
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Não foi possível concluir a ação.");
                alert.setContentText("Não foi possível remover o pedido de exame do banco de dados.");
            }
            alert.showAndWait();
        }
    }
}
