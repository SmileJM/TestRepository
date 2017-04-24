package ch17.exam30;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class RootController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnChangeCSS;

    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        anchorPane.getStylesheets().add(getClass().getResource("root.css").toString());    
        // 테마변경
        btnChangeCSS.setOnAction( e -> {
            // 기존의 내용은 지우고
            anchorPane.getStylesheets().clear();
            // 새로운 태마를 적용
            anchorPane.getStylesheets().add(getClass().getResource("root2.css").toString());    
        });
    }    
    
}
