package ch18.exam29.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatCient extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("client.fxml"));
       
        Scene scene = new Scene(parent);
        primaryStage.setTitle("채팅 클라이언트");
        primaryStage.setScene(scene);        
        primaryStage.show();
    }
   
    public static void main(String[] args) {
        launch(args);
    }
}
