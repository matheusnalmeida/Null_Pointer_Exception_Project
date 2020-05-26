package app.controller.medico;

import app.model.dao.PedidoExameDAO;
import app.model.domain.PedidoExame;
import app.view.medico.CrudPedidosExame;
import app.view.medico.EditarPedidoExame;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

public class EditarPedidoExameController implements Initializable {

    @FXML
    private JFXButton concluirBotao;
    @FXML
    private JFXButton cancelarBotao;
    @FXML
    private JFXTextField cpfPacienteField;
    @FXML
    private JFXTextField tipoExameField;
    @FXML
    private JFXTextArea recomendacoesArea;
    @FXML
    private JFXTextArea hipotesesArea;
    @FXML
    private JFXDatePicker dataExamePicker;
    @FXML
    private JFXTimePicker horarioPicker;

    public JFXTextField getCpfPacienteField() {
        return cpfPacienteField;
    }

    public JFXTextField getTipoExameField() {
        return tipoExameField;
    }

    public JFXTextArea getRecomendacoesArea() {
        return recomendacoesArea;
    }

    public JFXTextArea getHipotesesArea() {
        return hipotesesArea;
    }

    public JFXDatePicker getDataExamePicker() {
        return dataExamePicker;
    }

    public JFXTimePicker getHorarioPicker() {
        return horarioPicker;
    }

    public void preencherCampos(LocalDate dataExame, LocalTime horarioExame,
            String recomendacoes, String hipoteseDiagnostica, String cpfPaciente, String tipoExame) {
        this.dataExamePicker.setValue(dataExame);
        this.horarioPicker.setValue(horarioExame);
        this.recomendacoesArea.setText(recomendacoes);
        this.hipotesesArea.setText(hipoteseDiagnostica);
        this.cpfPacienteField.setText(cpfPaciente);
        this.tipoExameField.setText(tipoExame);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void concluirAction(ActionEvent evt) {
        PedidoExameDAO pedidoExameDAO = new PedidoExameDAO();
        PedidoExame pedidoExame = new PedidoExame(this.recomendacoesArea.getText(),
                this.dataExamePicker.getValue().toString() + " " + this.horarioPicker.getValue().toString(),
                this.hipotesesArea.getText(), this.tipoExameField.getText());
        pedidoExame.setCodigo(EditarPedidoExame.getCodigo());
        Alert alert;
        if (pedidoExameDAO.update(pedidoExame)) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Edição concluída com sucesso.");
            alert.setContentText("O pedido de exame foi alterado com sucesso.");
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Não foi possível concluir a edição");
            alert.setContentText("Erro ao conectar com o banco de dados.");
        }
        alert.showAndWait();
        this.cancelarAction(evt);
    }

    public void cancelarAction(ActionEvent evt) {
        EditarPedidoExame.getStage().close();
        CrudPedidosExame.getStage().show();
    }
}
