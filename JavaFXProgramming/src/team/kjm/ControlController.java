package team.kjm;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

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
            parent.setTranslateX(800);
            // parent.translateXProperty() 을 0까지             
            KeyValue keyValue = new KeyValue(parent.translateXProperty(), 0);
            // 0.1초간 진행
            KeyFrame keyFrame = new KeyFrame(Duration.millis(100), keyValue);
            // 아래 두개를 쌍으로
            Timeline timeline = new Timeline();
            // 애니메이션을 여러개 정의해 놓고 진행 가능
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void handleBtnGas(ActionEvent e) {
        try {
            stackPane.getChildren().clear();
            parent = FXMLLoader.load(getClass().getResource("gas.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stackPane.getChildren().add(parent);
    }

    private void handleBtnDoor(ActionEvent e) {
    }

    private void handleBtnSound(ActionEvent e) {
    }

}
