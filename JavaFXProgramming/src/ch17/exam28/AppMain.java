package ch17.exam28;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMain extends Application{
    public static Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        AppMain.primaryStage = primaryStage;
//        Parent parent = FXMLLoader.load(getClass().getResource("root.fxml"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("root.fxml"));
        Parent parent = loader.load();
        // 위와 같이 하는 것은 getController() 를 통해서 컨트롤러 객체를 얻을 수 있고
        // 그것으로 primaryStage 값을 줄 수 있기 때문
        RootController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);       
        
        Scene scene = new Scene(parent);
        primaryStage.setTitle("Menu");
        primaryStage.setScene(scene);        
        primaryStage.show();
    }
   
    public static void main(String[] args) {
        launch(args);
    }
}
