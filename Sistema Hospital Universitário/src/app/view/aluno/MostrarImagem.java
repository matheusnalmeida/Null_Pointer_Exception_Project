package app.view.aluno;

import java.io.ByteArrayInputStream;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Matheus Nunes
 */
public class MostrarImagem extends Application {

    private static Stage stage;
    private static Byte[] imagem;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        MostrarImagem.stage = stage;
    }

    public static Byte[] getImagem() {
        return imagem;
    }

    public static void setImagem(Byte[] imagem) {
        MostrarImagem.imagem = imagem;
    }

    @Override
    public void start(Stage stage) throws Exception {
        byte[] bytes = new byte[this.imagem.length];
        int j = 0;
        for (Byte b : this.imagem) {
            bytes[j++] = b.byteValue();
        }
        Image img1 = new Image(new ByteArrayInputStream(bytes));
        BorderPane pane = new BorderPane();
        ImageView img = new ImageView(img1);
        img.fitWidthProperty().bind(stage.widthProperty());
        img.fitHeightProperty().bind(stage.heightProperty());
        pane.setCenter(img);
        stage.setResizable(false);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
