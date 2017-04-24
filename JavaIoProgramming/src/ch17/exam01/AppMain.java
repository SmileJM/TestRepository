package ch17.exam01;

import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class AppMain extends Application{

    public AppMain() {
        System.out.println(Thread.currentThread().getName() + " Constructor");
    }

    @Override
    public void init() throws Exception {
        System.out.println(Thread.currentThread().getName() + " init");
        Parameters params = getParameters();
        List<String> list = params.getRaw();
        for(String param : list) {
            System.out.println(param);
        }
        
    }
    
    
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(Thread.currentThread().getName() + " start");
        stage.show();
//        stage.close();
    }

    @Override
    public void stop() throws Exception {
        System.out.println(Thread.currentThread().getName() + " stop");
    }
    
    public static void main(String[] args) {
        
        launch(args);
        Platform.exit();
    }
}
