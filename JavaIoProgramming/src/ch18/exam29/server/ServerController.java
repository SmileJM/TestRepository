package ch18.exam29.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ServerController implements Initializable {

    // @@@@@@ 
    public static ServerController instance;

    @FXML
    private Button btnStartStop;
    @FXML
    private TextArea txtDisplay;

    private ExecutorService executorService;
    private ServerSocket serverSocket;
    private List<Client> connections = new Vector<Client>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // @@@@@@ 
        // this 자기 자신의 객체
        ServerController.instance = this;
        btnStartStop.setOnAction(e -> handleBtnStartStop(e));
    }

    private void handleBtnStartStop(ActionEvent e) {
        if (btnStartStop.getText().equals("시작")) {
            startServer();
        } else if (btnStartStop.getText().equals("멈춤")) {
            stopServer();
        }
    }

    private void startServer() {
        // ThreadPool 생성
        executorService = Executors.newFixedThreadPool(20);

        // ServerSocket 생성
        try {
            serverSocket = new ServerSocket();
            // 포트 바인딩
            serverSocket.bind(new InetSocketAddress("192.168.3.21", 50001));
        } catch (IOException ex) {
            stopServer();
            // 예외 발생시 종료시킴
            return;
        }

        // 연결 수락 작업 정의
        Runnable acceptTask = () -> {
            // UI 변경 코드
            Platform.runLater(() -> {
                btnStartStop.setText("멈춤");
                display("[서버시작]");
            });

            while (true) {
                // 수락 코드
                try {
                    Socket socket = serverSocket.accept();
                    // 연결된 클라이언트 정보 출력
                    String clientInfo = "[연결 수락: " + socket.getRemoteSocketAddress() + "]";
                    Platform.runLater(() -> display(clientInfo));
                    // 통신용 Client 객체 생성
                    Client client = new Client(socket);
                    // Vector에 클라언트저장
                    connections.add(client);
                    // 총 Client 수 출력                    
                    Platform.runLater(() -> display("[연결 갯수: " + connections.size() + "]"));
                } catch (IOException ex) {
                    stopServer();
                    break;
                }
            }
        };
        // 스레드풀의 작업큐에 넣기
        executorService.submit(acceptTask);
    }

    public void stopServer() {
        try {
            // how1
//            Iterator<Client> iterator = connections.iterator();
//            while(iterator.hasNext()){
//                Client client = iterator.next();
//                client.socket.close();
//                // client를 Vector 에서 제거하는 것
//                iterator.remove();
//            }

            // how2
            // Client 객체와 소켓 제거
            for (Client client : connections) {
                client.socket.close();
            }
            connections.clear();

            //
            serverSocket.close();
            executorService.shutdownNow();
            Platform.runLater(() -> {
                btnStartStop.setText("멈춤");
                display("[서버멈춤]");
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void display(String text) {
        txtDisplay.appendText(text + "\n");
    }

    class Client {

        Socket socket;

        public Client(Socket socket) {
            this.socket = socket;
            // 클라이언트 생성과 동시에 항상 받을 준비
            receive();
        }

        void receive() {
            // 받기 작업 정의
            Runnable receiveTask = () -> {
                try {
                    while (true) {
                        InputStream inputStream = socket.getInputStream();
                        byte[] byteArr = new byte[100];
                        int readBytes = inputStream.read(byteArr);
                        if (readBytes == -1) {
                            throw new Exception();
                        }
                        String strData = "[ " + socket.getRemoteSocketAddress() + "]: ";
                        strData += new String(byteArr, 0, readBytes);
                        //                String message = "[ " + socket.getInetAddress().getHostAddress() + "]: ";
                        for (Client client : connections) {
                            client.send(strData);
                        }
                    }
                } catch (Exception ex) {
                    try {
                        String clientInfo = "[연결 끊김: " + socket.getRemoteSocketAddress() + "]";
                        Platform.runLater(() -> display(clientInfo));
                        socket.close();
                        // 여기서 Client.this 는 client 객체 
                        connections.remove(Client.this);
                        Platform.runLater(() -> display("[연결 갯수: " + connections.size() + "]"));
                    } catch (IOException ex1) {
                    }
                }
            };
            // 스레드풀의 작업 큐에 작업 넣기
            executorService.submit(receiveTask);
        }

        void send(String message) {
            try {
                OutputStream os = socket.getOutputStream();

                byte[] byteArr = message.getBytes();
                os.write(byteArr);
                os.flush();
            } catch (IOException ex) {
                // 문제가 생긴 클라이언트를 제거
                connections.remove(Client.this);
                try {
                    socket.close();
                } catch (IOException ex1) {
                }
            }
        }
    }
}
