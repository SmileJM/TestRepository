
package ch17.exam38;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;


public class RootController implements Initializable {

    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label label;
    @FXML
    private Label lblWorkDone;
    @FXML
    private Button btnStart;
    @FXML
    private Button btnStop;

    private Task<Integer> task;
    @FXML
    private Label lblResult;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnStart.setOnAction( e -> handleBtnStart(e));
        btnStop.setOnAction( e -> handleBtnStop(e));       
    }    

    private void handleBtnStart(ActionEvent e) {
        task = new Task<Integer>(){
            @Override
            protected Integer call() throws Exception {
                int result = 0;
//                System.out.println("call()...");
                for(int i=0; i<=100; i++) {
                    result += i;
                    // progress 를 수정하는 역할
                    // 첫번째 값 - 현재 값
                    // 두번째 값 - i의 최대값
                    // how1
//                    updateProgress(i, 100);               
//                    updateMessage(String.valueOf(i) + " %");

                    // how2
                    double value = i;

                    Platform.runLater(() -> {
                        progressBar.setProgress(value/100);
                        lblWorkDone.setText(String.valueOf(value));
                    });
                    
                    if(task.isCancelled()) break;                    
                    try {                    
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        break;
                    }
                }
                return result;
            }     

            @Override
            protected void succeeded() {
                // 이 안에서는 UI 변경 작업 가능
                // call() 의결과값을 받으려면 getValue() 호출
                int result = getValue();
                lblResult.setText(String.valueOf(result));
                System.out.println("succeeded:" + Thread.currentThread().getName());
            }

            @Override
            protected void cancelled() {
                System.out.println("cancelled:" + Thread.currentThread().getName());
                lblResult.setText(String.valueOf("작업 취소"));
            }

            @Override
            protected void failed() {
                System.out.println("failed:" + Thread.currentThread().getName());
                lblResult.setText(String.valueOf("작업 실패"));
            }
            
            
        };
        // 속성감시
        // how1
//        progressBar.progressProperty().bind(task.progressProperty());
//        lblWorkDone.textProperty().bind(task.messageProperty());
        // 작업스레드는 값을 변경하는 역할
        // JavaFX Application Thread 는 속성감시(UI변경)
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    private void handleBtnStop(ActionEvent e) {
        task.cancel();
    }
    
}
