package mqtt.exam02;

import java.util.Scanner;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Device2Example {

    public static void main(String[] args) throws MqttException {
        Device device = new Device("device2");
        device.subscribe();
        Scanner scanner = new Scanner(System.in, "UTF-8");
        while (true) {
            System.out.print("[input message or q(quit)]: ");
            String text = scanner.nextLine();
            if (text.equals("q") || text.equals("quit")) {
                break;
            }
            device.publish("device1", text);
        }
        device.close();
    }

}
