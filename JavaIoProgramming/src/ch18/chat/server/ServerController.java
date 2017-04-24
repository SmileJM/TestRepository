package ch18.chat.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ServerController implements Initializable {

    @FXML
    private Button btnStartStop;
    @FXML
    private TextArea txtDiplay;
    private ServerSocket serverSocket;
    private ExecutorService executorService;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnStartStop.setOnAction(e -> handleBtnStartStop(e));
    }

    @FXML
    private void handleBtnStartStop(ActionEvent event) {
        if (btnStartStop.getText().equals("시작")) {
            startServer();
            btnStartStop.setText("멈춤");
        } else if (btnStartStop.getText().equals("멈춤")) {
            stopServer();
            btnStartStop.setText("시작");
        }
    }

    private void startServer() {
        executorService = Executors.newFixedThreadPool(20);
        Runnable acceptTask = () -> {
            try {
                serverSocket = new ServerSocket();
                serverSocket.bind(new InetSocketAddress("192.168.3.21", 50001));

                while (true) {
                    Socket socket = serverSocket.accept();
                    String infoData = "[연결성공: " + socket.getRemoteSocketAddress() + "]";
                    Platform.runLater(() -> display(infoData));
                }

            } catch (IOException ex) {
                stopServer();
            }
        };
        executorService.submit(acceptTask);

    }

    private void stopServer() {
    }

    private void display(String text) {
        txtDiplay.appendText(text + "\n");
    }

    class Client {
        Socket socket;
        Client(Socket socket) {
            this.socket = socket;
            receive();
        }
        
        private void receive() {
            
        }
        
        private void send() {
            
        }
    }
}
