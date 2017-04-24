package ch18.exam28;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class SendExample {
    public static void main(String[] args) throws SocketException, IOException, IOException {
        DatagramSocket datagramSocket = new DatagramSocket();  
        String strData = "hello";
        byte[] data = strData.getBytes();        
        
        DatagramPacket packet = new DatagramPacket(
            data,
            data.length,
            new InetSocketAddress("192.168.3.18", 50002)
        );        
        datagramSocket.send(packet);
        
        datagramSocket.close();
    }
}
