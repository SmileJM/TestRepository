package coap.exam02.server;

import coap.exam01.server.*;
import java.net.InetSocketAddress;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.CoapEndpoint;

public class CoapResourceServer {
    // Field
    private CoapServer coapServer;
    // Constructor
    public CoapResourceServer() {
        coapServer = new CoapServer();
        InetSocketAddress isa = new InetSocketAddress("192.168.3.50", 5683);
        coapServer.addEndpoint(new CoapEndpoint(isa));
        // coapServer CoapResource01()을 관리하게 됨
        coapServer.add(new CoapResource01());
        coapServer.add(new CoapResource02());
        coapServer.start();
    }
    // Method
    public void shutdown() {
        coapServer.stop();
        coapServer.destroy();
    }

public static void main(String[] args) {
        CoapResourceServer server = new CoapResourceServer();
        // 모든 장치가 같은 포트를 사용해야 하므로 기본 포트 사용
        System.out.println("CoAP server is listening on port 5693");
    }
    
}
