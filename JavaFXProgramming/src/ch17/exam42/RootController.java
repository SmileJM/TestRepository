package ch17.exam42;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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

public class RootController implements Initializable {

    @FXML
    private Button btnLogin;
    @FXML
    private StackPane stackPane;

    public static StackPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rootPane = stackPane;
        btnLogin.setOnAction(e -> handleBtnLogin(e));
    }

    private void handleBtnLogin(ActionEvent e) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));
            // login.fxml 이 올라오는데 stackPane위에 BorderPane이 올라옴
            stackPane.getChildren().add(parent);
            // x좌표를 이동시켜서 스택패인에서는 보이지 않게 됨
            parent.setTranslateX(350);
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
}
