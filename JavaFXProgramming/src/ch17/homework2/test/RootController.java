package ch17.homework2.test;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Ellipse;

public class RootController implements Initializable{
    @FXML private Ellipse ellipse;    
    @FXML private AnchorPane root;    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ellipse.centerXProperty().bind(Bindings.divide(root.widthProperty(), 2));
        ellipse.centerYProperty().bind(Bindings.divide(root.heightProperty(), 2));
        ellipse.radiusXProperty().bind(Bindings.divide(root.widthProperty(), 4));
        ellipse.radiusYProperty().bind(Bindings.divide(root.heightProperty(), 4));
        
    }
}
