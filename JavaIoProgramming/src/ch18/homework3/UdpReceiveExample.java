package ch18.homework3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpReceiveExample extends Thread {
    public static void main(String[] args) throws SocketException, IOException, InterruptedException {        
        DatagramSocket datagramSocket = new DatagramSocket(50005);
        // 작업 스레드 생성
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("[수신 시작]");
                try {
                    while(true) {
                        // 데이터를 받기 위해 DatagramPacket 생성
                        DatagramPacket datagramPacket = new DatagramPacket(new byte[100], 100);
                        // receive() 메소드는 데이터를 받을 때 까지 블로킹 됨
                        datagramSocket.receive(datagramPacket);
                        
                        String data = new String(datagramPacket.getData(), 0, datagramPacket.getLength(), "UTF-8");
                        System.out.println("[받은 내용: " + datagramPacket.getSocketAddress() + "]" + data);
                    }
                } catch(Exception e) {
                    System.out.println("[수신 종료]");
                }
                
            }
        };
        thread.start();
        
        Thread.sleep(10000);
        datagramSocket.close();
        
    }
}
