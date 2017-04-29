package team.kjm.back.kjm3;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class RootController implements Initializable {

    @FXML
    private ImageView imgView1;
    @FXML
    private ImageView imgView2;
    @FXML
    private ImageView imgView3;
    @FXML
    private ImageView imgView4;
    @FXML
    private ImageView imgView5;
    @FXML
    private ImageView imgView6;
    @FXML
    private ImageView imgView7;
    @FXML
    private ImageView imgView8;
    @FXML
    private StackPane stackPane;

    private static StackPane rootPane;

    private Parent parent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rootPane = stackPane;
        imgView1.setOnDragDropped((event) -> {
            handleDragBtn(event, 1);
        });
        imgView1.setOnMouseClicked(e -> handleBtn(e, 1));
        imgView2.setOnMouseClicked(e -> handleBtn(e, 2));
        imgView3.setOnMouseClicked(e -> handleBtn(e, 3));
        imgView4.setOnMouseClicked(e -> handleBtn(e, 4));
        imgView5.setOnMouseClicked(e -> handleBtn(e, 5));
        imgView6.setOnMouseClicked(e -> handleBtn(e, 6));
        imgView7.setOnMouseClicked(e -> handleBtn(e, 7));
        imgView8.setOnMouseClicked(e -> handleBtn(e, 8));
    }

    private void handleBtn(MouseEvent e, int num) {
        System.out.println(num);
        try {
            rootPane.getChildren().clear();
            switch (num) {
                case 1:
                    parent = FXMLLoader.load(getClass().getResource("menu1.fxml"));
                    break;
                case 2:
                    parent = FXMLLoader.load(getClass().getResource("menu2.fxml"));
                    break;
                case 3:
                    parent = FXMLLoader.load(getClass().getResource("menu3.fxml"));
                    break;
                case 4:
                    parent = FXMLLoader.load(getClass().getResource("menu4.fxml"));
                    break;
                case 5:
                    parent = FXMLLoader.load(getClass().getResource("menu5.fxml"));
                    break;
                case 6:
                    parent = FXMLLoader.load(getClass().getResource("menu6.fxml"));
                    break;
                case 7:
                    parent = FXMLLoader.load(getClass().getResource("menu7.fxml"));
                    break;
                case 8:
                    parent = FXMLLoader.load(getClass().getResource("menu8.fxml"));
                    break;
            }

            // login.fxml 이 올라오는데 stackPane위에 BorderPane이 올라옴
            stackPane.getChildren().add(parent);
            // x좌표를 이동시켜서 스택패인에서는 보이지 않게 됨
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

    private void handleDragBtn(DragEvent event, int num) {
        System.out.println(num);
        try {
            rootPane.getChildren().clear();
            switch (num) {
                case 1:
                    parent = FXMLLoader.load(getClass().getResource("menu1.fxml"));
                    break;
                case 2:
                    parent = FXMLLoader.load(getClass().getResource("menu2.fxml"));
                    break;
                case 3:
                    parent = FXMLLoader.load(getClass().getResource("menu3.fxml"));
                    break;
                case 4:
                    parent = FXMLLoader.load(getClass().getResource("menu4.fxml"));
                    break;
                case 5:
                    parent = FXMLLoader.load(getClass().getResource("menu5.fxml"));
                    break;
                case 6:
                    parent = FXMLLoader.load(getClass().getResource("menu6.fxml"));
                    break;
                case 7:
                    parent = FXMLLoader.load(getClass().getResource("menu7.fxml"));
                    break;
                case 8:
                    parent = FXMLLoader.load(getClass().getResource("menu8.fxml"));
                    break;
            }

            // login.fxml 이 올라오는데 stackPane위에 BorderPane이 올라옴
            stackPane.getChildren().add(parent);
            // x좌표를 이동시켜서 스택패인에서는 보이지 않게 됨
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
}
