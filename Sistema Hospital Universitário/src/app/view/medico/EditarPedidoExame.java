package app.view.medico;

import app.controller.medico.EditarPedidoExameController;
import app.model.domain.Paciente;
import app.model.domain.PedidoExame;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class EditarPedidoExame extends Application {

    private static Stage stage;
    private PedidoExame pedidoExame;
    private Paciente paciente;
    private static int codigo;

    public EditarPedidoExame(PedidoExame pedidoExame, Paciente paciente) {
        this.pedidoExame = pedidoExame;
        this.paciente = paciente;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        EditarPedidoExame.stage = stage;
    }

    public static int getCodigo() {
        return codigo;
    }

    public static void setCodigo(int codigo) {
        EditarPedidoExame.codigo = codigo;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/view/fxml/medico/EditarPedidoExame.fxml"));
        Parent root = (Parent) loader.load();
        EditarPedidoExameController controller = loader.getController();
        stage.setScene(new Scene(root));
        EditarPedidoExame.setStage(stage);
        stage.setOnCloseRequest((WindowEvent event) -> {
            CrudPedidosExame.getStage().show();
            EditarPedidoExame.getStage().close();
        });
        String dataHorarioExame = this.pedidoExame.getDataExame();
        String dataExame[] = dataHorarioExame.split(" ");
        LocalDate localDate = this.processarDataExame(dataExame[0]);
        LocalTime localTime = this.processarHorarioExame(dataExame[1]);
        String recomendacoes = this.pedidoExame.getRecomendacoes();
        String hipoteseDiagnostica = this.pedidoExame.getHipoteseDiagnostica();
        String tipoExame = this.pedidoExame.getTipoExame();
        controller.preencherCampos(localDate, localTime, recomendacoes, hipoteseDiagnostica, this.paciente.getCpf(), tipoExame);
        EditarPedidoExame.setCodigo(this.pedidoExame.getCodigo());
    }

    private LocalDate processarDataExame(String dataExame) {
        String aux1[] = dataExame.split("-");
        int ano = Integer.parseInt(aux1[0]);
        int mes = Integer.parseInt(aux1[1]);
        int dia = Integer.parseInt(aux1[2]);
        LocalDate localDate = LocalDate.of(ano, mes, dia);
        return localDate;
    }

    private LocalTime processarHorarioExame(String horarioExame) {
        String aux2[] = horarioExame.split(":");
        int horas = Integer.parseInt(aux2[0]);
        int minutos = Integer.parseInt(aux2[1]);
        LocalTime localTime = LocalTime.of(horas, minutos);
        return localTime;
    }
}
