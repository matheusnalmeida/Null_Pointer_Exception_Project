package app.controller.medico;

import app.model.dao.EmissaoPedidoExameDAO;
import app.model.dao.PacienteDAO;
import app.model.dao.PedidoExameDAO;
import app.model.domain.EmissaoPedidoExame;
import app.model.domain.Medico;
import app.model.domain.Paciente;
import app.model.domain.PedidoExame;
import app.utilits.Sistema;
import app.view.medico.CadastrarExames;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.WindowEvent;

public class CadastrarExamesController implements Initializable {

    private List<Paciente> listPaciente;
    private ObservableList<Paciente> observableListPaciente;

    @FXML
    private ImageView Icon;

    @FXML
    private Label Nome1;

    @FXML
    private Label Nome2;

    @FXML
    private JFXTextField tipoExame;

    @FXML
    private JFXDatePicker dataExame;

    @FXML
    private JFXTimePicker horarioExame;

    @FXML
    private Label tituloLabel;

    @FXML
    private JFXTextArea recomendacoes;

    @FXML
    private JFXTextArea hipoteses;

    @FXML
    private JFXButton cadastrar;
    @FXML
    private JFXTextField cpfDoPaciente;
    @FXML
    private JFXButton cancelar;
    @FXML
    private JFXComboBox comboboxPaciente;

    @FXML
    public void cadastrarAcao(ActionEvent event) {
        PedidoExameDAO pedidoExameDao = new PedidoExameDAO();
        EmissaoPedidoExameDAO emissaoPedidoExameDao = new EmissaoPedidoExameDAO();
        String recomendacoes = this.recomendacoes.getText().trim();
        String hipoteses = this.hipoteses.getText().trim();
        String tipoDeExame = this.tipoExame.getText().trim();
        //String cpfPaciente = this.cpfDoPaciente.getText().trim();
        Paciente paciente = (Paciente) this.comboboxPaciente.getSelectionModel().getSelectedItem();
        LocalDate dataDoExame = this.dataExame.getValue();
        LocalTime horarioExame = this.horarioExame.getValue();
        String dataEmissao = LocalDateTime.now().toString();
        //Paciente paciente = new Paciente();
        //paciente.setCpf(cpfPaciente);
        if (this.validaCamposVazio() && paciente != null) {
            Medico medico_atual = (Medico) Sistema.getSessao().getUsuario();
            PedidoExame novoPedidoExame = new PedidoExame(recomendacoes, dataDoExame.toString() + " " + horarioExame.toString(), hipoteses, tipoDeExame);
            boolean pedidoCadastrado = pedidoExameDao.create(novoPedidoExame);
            EmissaoPedidoExame emissaoPedidoExame = new EmissaoPedidoExame(dataEmissao, medico_atual, paciente, novoPedidoExame);
            boolean emissaoPedidoCadastrada = emissaoPedidoExameDao.create(emissaoPedidoExame);
            if (pedidoCadastrado && emissaoPedidoCadastrada) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Information Dialog");
                alert.setContentText("Cadastro de exame realizado com sucesso");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Information Dialog");
                alert.setContentText("Erro ao realizar cadastro.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Dados Invalidos!");
            alert.showAndWait();
        }
        this.cancelarAcao(event);
    }

    @FXML
    public void cancelarAcao(ActionEvent event) {
        CadastrarExames.getStage().fireEvent(
                new WindowEvent(
                        CadastrarExames.getStage(),
                        WindowEvent.WINDOW_CLOSE_REQUEST
                )
        );
    }

    public boolean validaCamposVazio() {
        return !"".equals(this.recomendacoes.getText().trim())
                && !"".equals(this.hipoteses.getText().trim())
                && !"".equals(this.tipoExame.getText().trim())
                && null != this.dataExame.getValue();
    }

    private void carregarComboBox() {
        PacienteDAO pacienteDAO = new PacienteDAO();
        this.listPaciente = pacienteDAO.selectAll();
        if (this.listPaciente != null) {
            this.observableListPaciente = FXCollections.observableArrayList(this.listPaciente);
            this.comboboxPaciente.setItems(this.observableListPaciente);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.carregarComboBox();
    }
}
