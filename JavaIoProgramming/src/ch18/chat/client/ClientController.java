
package ch18.chat.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class ClientController implements Initializable {

    @FXML
    private Button btnConn;
    @FXML
    private TextField txtText;
    @FXML
    private Button btnSend;
    @FXML
    private TextArea txtDisplay;
    Socket socket;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnConn.setOnAction(e->handleBtnConn(e));
        btnSend.setOnAction(e->handleBtnSend(e));
       
    }    

    @FXML
    private void handleBtnConn(ActionEvent event) {
        if(btnConn.getText().equals("연결")) {
            startClient();
            btnConn.setText("끊기");
        } else if(btnConn.getText().equals("끊기")) {
            stopClient();
            btnConn.setText("연결");
        }
    }

    @FXML
    private void handleBtnSend(ActionEvent event) {
        send();
    }

    private void startClient() {
        socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("192.168.3.21", 50001));
        } catch (IOException ex) {
            
        }
        
    }

    private void stopClient() {
    }

    private void send() {
    }
    
}
