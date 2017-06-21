package sensingcar;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sensingcar.coap.server.CoapResourceServer;

public class SensingCar {

    // Field
    private static final Logger logger = LoggerFactory.getLogger(SensingCar.class);
    private CoapResourceServer coapResourceServer;

    // Constructor
    public SensingCar() throws Exception {
        coapResourceServer = new CoapResourceServer();

    }

    // Method
    public void start() {
        logger.info("실행");
        System.out.println("SensingCar Start!");
        coapResourceServer.start();

    }

    public void stop() {
        logger.info("실행");
        System.out.println("SensingCar Stop!");
        coapResourceServer.stop();

    }

    public static void main(String[] args) throws Exception {
        SensingCar sensingCar = new SensingCar();
        sensingCar.start();
        System.out.print("[Input command('q' to quit)]: ");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        if (command.equals("q")) {
            sensingCar.stop();
        }
    }
}
