package hardware.sensor;

import hardware.converter.PCF8591;

public class Myoware {

    // Field
    private PCF8591 pcf8591;

    // Constructor
    public Myoware(PCF8591 pcf8591) {
        this.pcf8591 = pcf8591;
    }

    //Method
    public double getValue() throws Exception {
        // analogRead(): 0 ~ 255
        int analogVal = pcf8591.analogRead();

        double voltage = analogVal * (3.3 / 255.0);

//        return (int) (voltage * 100) / 100.0;
        return voltage;
    }

    public static void main(String[] args) throws Exception {
        PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN0);
        Myoware myoware = new Myoware(pcf8591);
        while (true) {
            double voltage = myoware.getValue();
            System.out.println("voltage: " + voltage);
            Thread.sleep(500);
        }
    }
}
