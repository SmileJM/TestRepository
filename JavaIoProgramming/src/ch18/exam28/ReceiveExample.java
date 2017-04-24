package ch18.exam28;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ReceiveExample {
    public static void main(String[] args) throws SocketException, IOException{
        // 받는 쪽은 반드시 포트 번호가 있어야 함
        DatagramSocket datagramSocket = new DatagramSocket(50002);  
        DatagramPacket packet = new DatagramPacket(new byte[100], 100);
        datagramSocket.receive(packet);
        byte[] data = packet.getData();
        int readBytes = packet.getLength();
        String strData = new String(data, 0, readBytes);
        System.out.println("받은 데이터: " + strData);
        
        datagramSocket.close();
    }
}
