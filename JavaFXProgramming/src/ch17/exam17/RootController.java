package ch17.exam17;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RootController implements Initializable {

    @FXML
    private Button btnClose;
    @FXML
    private CheckBox cb1;
    @FXML
    private CheckBox cb2;
    @FXML
    private ImageView checkImageView;
    @FXML
    private ToggleGroup rbGroup;
    @FXML
    private ImageView radioImageView;
    @FXML
    private RadioButton rb1;
    @FXML
    private RadioButton rb2;
    @FXML
    private RadioButton rb3;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(Thread.currentThread().getName());
        btnClose.setOnAction(event -> {
            // JavaFX Application Thread 실행
            Platform.exit();
        });
        rbGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
//                how1 객체의 번지를 직접 비교              
//                if(newValue == rb1) {
//                    radioImageView.setImage(new Image(getClass().getResource("images/BubbleChart.png").toString()));                    
//                } else if(newValue == rb2) {
//                    radioImageView.setImage(new Image(getClass().getResource("images/BarChart.png").toString()));                    
//                } else if(newValue == rb3) {
//                    radioImageView.setImage(new Image(getClass().getResource("images/AreaChart.png").toString()));                    
//                } 
                
//               how2 getUserData -> Object 
                String fileName = newValue.getUserData()+ ".png";
                radioImageView.setImage(new Image(getClass().getResource("images/" + fileName).toString()));                    
                
            }
        });

//        cb1.setOnAction( e -> {        
//             handleChkAction(e);
//        }); 
//        cb2.setOnAction( e->{
//            handleChkAction(e);
//        });
    }

    @FXML
    private void handleChkAction(ActionEvent e) {
        if (cb1.isSelected() && cb2.isSelected()) {
            checkImageView.setImage(
            new Image(getClass().getResource("images/geek-glasses-hair.gif").toString()));
        } else if (cb1.isSelected()) {
            checkImageView.setImage(
            new Image(getClass().getResource("images/geek-glasses.gif").toString()));
        } else if (cb2.isSelected()) {
            checkImageView.setImage(
            new Image(getClass().getResource("images/geek-hair.gif").toString()));
        } else {
            checkImageView.setImage(
            new Image(getClass().getResource("images/geek.gif").toString()));
        }
    }
}
