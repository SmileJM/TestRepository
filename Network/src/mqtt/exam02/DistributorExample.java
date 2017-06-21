package mqtt.exam02;

import java.io.IOException;
import java.util.Scanner;
import org.eclipse.paho.client.mqttv3.MqttException;

public class DistributorExample {

    public static void main(String[] args) throws MqttException, IOException {
        Distributor distributor = new Distributor();
        distributor.subscribe();
        System.out.print("[input message or q(quit)]: ");
        System.in.read();
        distributor.close();
    }

}
