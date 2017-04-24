package ch18.exam29.server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatServer extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("server.fxml"));
       
        Scene scene = new Scene(parent);
        primaryStage.setTitle("채팅 서버");
        primaryStage.setScene(scene);        
        
        // @@@@@@ 잘 알아두기
        primaryStage.setOnCloseRequest(e->{
            ServerController.instance.stopServer();
        });
        //
        primaryStage.show();
    }
   
    public static void main(String[] args) {
        launch(args);
    }
}
