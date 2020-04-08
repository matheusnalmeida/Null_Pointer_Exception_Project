/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author serbi
 */
public class RelatorioController implements Initializable {

    @FXML
    private Label codigoLabel;
    @FXML
    private Label descricaoLabel;
    @FXML
    private Label crmMedicoLabel;
    @FXML
    private Label dataRelatorioLabel;
    @FXML
    private Label dataAutorizacaoRelatorioLabel;
    @FXML
    private Label arquivoLabel;
    @FXML
    private TextField codigoField;
    @FXML
    private TextField descricaoField;
    @FXML
    private TextField crmMedicoField;
    @FXML
    private DatePicker dataRelatorioField;
    @FXML
    private DatePicker dataAutorizacaoRelatorioField;
    @FXML
    private Hyperlink arquivoLink;
    @FXML
    private Button cancelar;
    @FXML
    private Button cadastrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
