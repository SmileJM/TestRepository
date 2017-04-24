package ch18.homework3;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class UdpSendExample {
    public static void main(String[] args) throws Exception {        
        DatagramSocket datagramSocket = new DatagramSocket();
        
        System.out.println("[발신 시작]");
        for(int i=1; i<5; i++) {
            String strData = "메시지 " + i;
            byte[] data = strData.getBytes("UTF-8");
            // datagramPacket 에 데이터를 싣고 보냄
            DatagramPacket datagramPacket = new DatagramPacket(
                    data,
                    data.length,
                    new InetSocketAddress("localhost", 50005)
            );
            
            datagramSocket.send(datagramPacket);
            System.out.println("[보낸 바이트 수]: " + data.length + " bytes");
        }
        System.out.println("[발신 종료]");
        datagramSocket.close();
    }
}
