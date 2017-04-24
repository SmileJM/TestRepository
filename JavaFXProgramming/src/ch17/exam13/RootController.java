package ch17.exam13;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class RootController implements Initializable{
    @FXML private Button btn1;
    @FXML private Button btn2;
    @FXML private Button btn3;
    @FXML private Button btn4;
    @FXML private Button btn5;
    @FXML private Button btn6;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        how1
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("버튼1이 클릭됨");
            }
        });
//        how2
//        추천하는 방법
        btn2.setOnAction( event -> {
            System.out.println("버튼2가 클릭됨");
        });
        
        btn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("버튼4가 클릭됨");
            }
        });
        
        btn5.setOnAction( event -> System.out.println("버튼5가 클릭됨"));
       
    }
//    how3
    public void btn3Handle(ActionEvent event) {
        System.out.println("버튼3이 클릭됨");
    }   
    public void btn6Handle(ActionEvent event) {
        System.out.println("버튼6이 클릭됨");
    }
}
