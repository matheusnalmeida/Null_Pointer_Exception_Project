package app.view.main;

import app.model.dao.RelatorioTemplateDAO;
import app.model.domain.RelatorioTemplate;
import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Matheus Nunes
 */
public class MainFrame extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        MainFrame.stage = stage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/view/fxml/main/MainFrame.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Inicio");
        stage.centerOnScreen();
        stage.show();
        MainFrame.setStage(stage);
        //Rodar essa parte apenas 1 vez
        /*File file = new File("c:\\relatorio.pdf");
        try {
            RelatorioTemplate relatorioTemplate = new RelatorioTemplate(file);
            RelatorioTemplateDAO relatorioTemplateDAO = new RelatorioTemplateDAO();
            relatorioTemplateDAO.create(relatorioTemplate);
        } catch (Exception exception) {
        }*/
        /*RelatorioTemplateDAO relatorioTemplateDAO = new RelatorioTemplateDAO();
        RelatorioTemplate relatorioTemplate = new RelatorioTemplate();
        relatorioTemplate.setCodigo(1);
        relatorioTemplate = relatorioTemplateDAO.read(relatorioTemplate);
        relatorioTemplate.writeBytes("g:\\");*/
    }
}
