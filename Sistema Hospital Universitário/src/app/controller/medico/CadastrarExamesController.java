package app.controller.medico;

import app.model.dao.EmissaoPedidoExameDAO;
import app.model.dao.PedidoExameDAO;
import app.model.domain.EmissaoPedidoExame;
import app.model.domain.Medico;
import app.model.domain.Paciente;
import app.model.domain.PedidoExame;
import app.utilits.Sistema;
import app.view.medico.CadastrarExames;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.WindowEvent;

public class CadastrarExamesController implements Initializable {

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
    void cadastrarAcao(ActionEvent event) {
        PedidoExameDAO pedidoExameDao = new PedidoExameDAO();
        EmissaoPedidoExameDAO emissaoPedidoExameDao = new EmissaoPedidoExameDAO();
        String recomendacoes = this.recomendacoes.getText().trim();
        String hipoteses = this.hipoteses.getText().trim();
        String tipoDeExame = this.tipoExame.getText().trim();
        String cpfPaciente = this.cpfDoPaciente.getText().trim();
        LocalDate dataDoExame = this.dataExame.getValue();
        String dataEmissao = LocalDateTime.now().toString();
        Paciente paciente = new Paciente();
        paciente.setCpf(cpfPaciente);
        if(this.validaCamposVazio()){
            Medico medico_atual = (Medico) Sistema.getSessao().getUsuario();
            PedidoExame novoPedidoExame = new PedidoExame(recomendacoes,dataDoExame.toString(),hipoteses,tipoDeExame);
            boolean pedidoCadastrado = pedidoExameDao.create(novoPedidoExame);
            EmissaoPedidoExame emissaoPedidoExame = new EmissaoPedidoExame(dataEmissao,medico_atual,paciente,novoPedidoExame);
            boolean emissaoPedidoCadastrada = emissaoPedidoExameDao.create(emissaoPedidoExame);
            if(pedidoCadastrado && emissaoPedidoCadastrada){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Information Dialog");
                alert.setContentText("Cadastro de exame realizado com sucesso");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Information Dialog");
                alert.setContentText("Erro ao realizar cadastro.");
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Dados Invalidos!");
            alert.showAndWait();
        }
        this.cancelarAcao(event);
    }

    @FXML
    void cancelarAcao(ActionEvent event) {
        CadastrarExames.getStage().fireEvent(
                new WindowEvent(
                        CadastrarExames.getStage(),
                        WindowEvent.WINDOW_CLOSE_REQUEST
                )
        );
    }

    boolean validaCamposVazio() {
        return !"".equals(this.recomendacoes.getText())
                && !"".equals(this.hipoteses.getText())
                && !"".equals(this.tipoExame.getText())
                && null != this.dataExame.getValue();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
