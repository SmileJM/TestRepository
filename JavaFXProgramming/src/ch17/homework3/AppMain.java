package ch17.homework3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMain extends  Application{

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("root.fxml"));
       
        Scene scene = new Scene(parent);
        stage.setTitle("Game Rank!");
        stage.setScene(scene);        
        stage.show();
    }
    
    public static void main(String[] args) {
       launch(args);
    }
}
