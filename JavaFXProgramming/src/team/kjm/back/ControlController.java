package team.kjm.back;

import team.kjm.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class ControlController implements Initializable {

    @FXML
    private Button btnLight;
    @FXML
    private Button btnGas;
    @FXML
    private Button btnDoor;
    @FXML
    private Button btnSound;
    @FXML
    private StackPane stackPane;
    
    private static StackPane rootPane;
    private Parent parent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rootPane = stackPane;

        btnLight.setOnAction(e -> handleBtnLight(e));
        btnGas.setOnAction(e -> handleBtnGas(e));
        btnDoor.setOnAction(e -> handleBtnDoor(e));
        btnSound.setOnAction(e -> handleBtnSound(e));
    }

    private void handleBtnLight(ActionEvent e) {
        try {
            rootPane.getChildren().clear();
            parent = FXMLLoader.load(getClass().getResource("lighting.fxml"));
            
            stackPane.getChildren().add(parent);
            
            
            
        } catch (IOException ex) {
            
        }
    }

    private void handleBtnGas(ActionEvent e) {
    }

    private void handleBtnDoor(ActionEvent e) {
    }

    private void handleBtnSound(ActionEvent e) {
    }

}
