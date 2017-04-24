package ch17.exam15;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class RootController implements Initializable {
    @FXML private TextArea textArea1;
    @FXML private TextArea textArea2;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        단방향
//        textArea2.textProperty().bind(textArea1.textProperty());
        
//        양방향1
//        textArea2.textProperty().bindBidirectional(textArea1.textProperty());
//        양방향2
        Bindings.bindBidirectional(textArea1.textProperty(), textArea2.textProperty());        
    }
}
