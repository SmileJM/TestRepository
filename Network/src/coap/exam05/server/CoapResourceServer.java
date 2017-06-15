package coap.exam05.server;

import coap.exam04.server.*;
import coap.exam02.server.*;
import coap.exam01.server.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.proxy.DirectProxyCoapResolver;
import org.eclipse.californium.proxy.ProxyHttpServer;
import org.eclipse.californium.proxy.resources.ForwardingResource;
import org.eclipse.californium.proxy.resources.ProxyCoapClientResource;

public class CoapResourceServer {
    // Field
    private CoapServer coapServer;
    // Constructor
    public CoapResourceServer() throws IOException {
        coapServer = new CoapServer();
        InetSocketAddress isa1 = new InetSocketAddress("192.168.3.50", 5683);
        // localhost 로 요청했을 때도 접속되도록
        InetSocketAddress isa2 = new InetSocketAddress("localhost", 5683);
        coapServer.addEndpoint(new CoapEndpoint(isa1));
        coapServer.addEndpoint(new CoapEndpoint(isa2));
        // coapServer CoapResource01()을 관리하게 됨
        coapServer.add(new CoapResource01());
        coapServer.add(new CoapResource02());
        coapServer.add(new CoapResource03());
        coapServer.add(new CoapResource04());
        coapServer.add(new CoapResource05());
        coapServer.add(new CoapResource06());
        
        // coap > coap 포워드 프록시 설정
        // "coap2coap" 는 리소스의 식별명
        ForwardingResource coap2coap = new ProxyCoapClientResource("coap2coap"); 
        coapServer.add(coap2coap);
        
        // http > coap 포워드 프록시 설정
        // 외부에서 접근시 http://아이피:9090/proxy/coap://아이피/resourceXX
        ProxyHttpServer httpServer = new ProxyHttpServer(9090);
        httpServer.setProxyCoapResolver(new DirectProxyCoapResolver(coap2coap));
        
        coapServer.start();
    }
    // Method
    public void shutdown() {
        coapServer.stop();
        coapServer.destroy();
    }

public static void main(String[] args) throws IOException {
//        CoapResourceServer server = new CoapResourceServer();
        new CoapResourceServer();
        // 모든 장치가 같은 포트를 사용해야 하므로 기본 포트 사용
        System.out.println("CoAP server is listening on port 5693");
    }    
}
