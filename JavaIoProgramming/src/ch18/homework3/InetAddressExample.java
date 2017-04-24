package ch18.homework3;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class InetAddressExample {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress[] ia = InetAddress.getAllByName("www.naver.com");
        for(InetAddress i : ia) {
            System.out.println("www.naver.com IP 주소: " + i.getHostAddress());    
        }        
    }
}
