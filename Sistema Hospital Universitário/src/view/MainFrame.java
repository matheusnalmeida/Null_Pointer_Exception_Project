<<<<<<< HEAD:Sistema Hospital Universitário/src/view/MainFrame.java
package view;
=======
package View;
>>>>>>> 441253f34f44108b06772b738a0321bcb5a002d0:Sistema Hospital Universitário/src/View/MainFrame.java

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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
<<<<<<< HEAD:Sistema Hospital Universitário/src/view/MainFrame.java
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLMain.fxml"));
=======
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("..//Model//FXMLMain.fxml"));
>>>>>>> 441253f34f44108b06772b738a0321bcb5a002d0:Sistema Hospital Universitário/src/View/MainFrame.java
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Inicio");
        stage.show();
    }
}
