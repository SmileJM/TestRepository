package ch17.homework0.ex01;

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
    private CheckBox btn1;
    @FXML
    private CheckBox btn2;
    @FXML
    private RadioButton radio1;
    @FXML
    private RadioButton radio2;
    @FXML
    private RadioButton radio3;
    @FXML
    private ImageView btnImageView;
    @FXML
    private ImageView radioImageView;
    @FXML
    private Button btnClose;
    @FXML
    private ToggleGroup radioGroup;


    @Override
    public void initialize(URL url, ResourceBundle rb) {         
        radioGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                String image = newValue.getUserData() + ".png";
                radioImageView.setImage(new Image(getClass().getResource("images/" + image).toString()));
            }            
        });
       btnClose.setOnAction( e-> Platform.exit());
        
    }    

    @FXML
    private void handleChkAction(ActionEvent event) {
        if(btn1.isSelected() && btn2.isSelected()) {
            btnImageView.setImage(new Image(getClass().getResource("images/geek-glasses-hair.gif").toString()));
        } else if(btn1.isSelected()) {
            btnImageView.setImage(new Image(getClass().getResource("images/geek-glasses.gif").toString()));
        } else if(btn2.isSelected()) {
            btnImageView.setImage(new Image(getClass().getResource("images/geek-hair.gif").toString()));
        } else {
            btnImageView.setImage(new Image(getClass().getResource("images/geek.gif").toString()));
        }
    }
}
