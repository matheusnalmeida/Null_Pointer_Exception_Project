package app.controller.medico;

import app.model.dao.MedicoDAO;
import app.model.domain.EmissaoPedidoExame;
import app.model.domain.Medico;
import app.model.domain.PedidoExameAux;
import app.utilits.Sistema;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

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
                PedidoExameAux pedidoExameAux = new PedidoExameAux(codigo, dataExame, nomePaciente, hipoteseDiagnostica, recomendacoes, tipoExame);
                pedidoExameAuxList.add(pedidoExameAux);
            }
            this.observableListEmissaoPedidoExame = FXCollections.observableArrayList(pedidoExameAuxList);
            this.pedidosExameTableView.setItems(this.observableListEmissaoPedidoExame);
        }
    }

    public void visualizarAction(ActionEvent evt) {

    }

    public void editarAction(ActionEvent evt) {
        
    }
}
