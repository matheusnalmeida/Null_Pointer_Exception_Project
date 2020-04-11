/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author serbi
 */
public class RelatorioController implements Initializable {

    @FXML
    private Label arquivoLabel;
    @FXML
    private JFXTextField codigoField;
    @FXML
    private JFXTextField descricaoField;
    @FXML
    private JFXTextField crmMedicoField;
    @FXML
    private JFXDatePicker dataRelatorioField;
    @FXML
    private JFXDatePicker dataAutorizacaoRelatorioField;
    @FXML
    private Hyperlink arquivoLink;
    @FXML
    private JFXButton cancelar;
    @FXML
    private JFXButton cadastrar;
    @FXML
    private ImageView imagem;
    @FXML
    private Label titulo1;
    @FXML
    private Label titulo2;
    @FXML
    private Label titulo3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
