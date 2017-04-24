package ch17.exam39;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Service;
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
    private Label lblWorkDone;
    @FXML
    private Label lblResult;
    @FXML
    private Button btnStart;
    @FXML
    private Button btnStop;

    private TimeService timeService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnStart.setOnAction(e -> handleBtnStart(e));
        btnStop.setOnAction(e -> handleBtnStop(e));
    }

    private void handleBtnStart(ActionEvent e) {
        if (timeService == null) {
            timeService = new TimeService();
            timeService.start();
            System.out.println("start");
        } else {
            timeService.restart();
        }
    }

    private void handleBtnStop(ActionEvent e) {
        timeService.cancel();
    }

    // Service를 만드는데 내부클래스로 만듬
    // 필드를 사용할 수 있기 때문
    // 또한 이 컨트롤러를 벗어나서는 사용할 필요가 없기 때문
    class TimeService extends Service<Integer> {

        @Override
        protected Task<Integer> createTask() {
            Task<Integer> task = new Task<Integer>() {
                @Override
                protected Integer call() throws Exception {
                    // 시작 버튼을 누르면 자동으로 호출함
                    // 작업스레드가 실행함
                    int sum = 0;
                    for (int i = 1; i <= 100; i++) {
                        sum += i;
                        Thread.sleep(100);
                        int k = i;
                        Platform.runLater(() -> {
                            progressBar.setProgress(k / 100.0);
                            lblWorkDone.setText(String.valueOf(k) + " %");
                        });
                        if(isCancelled()) break;
                    }
                    return sum;
                }
            };
            return task;
        }

        @Override
        protected void succeeded() {
            int result = getValue();
            lblResult.setText(String.valueOf(getValue()));
            System.out.println(result);
        }
    }
}
