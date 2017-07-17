package hardware.sensor;

import static com.pi4j.wiringpi.I2C.wiringPiI2CReadReg8;
import static com.pi4j.wiringpi.I2C.wiringPiI2CSetup;
import static com.pi4j.wiringpi.I2C.wiringPiI2CWriteReg8;

public class Gyro {

    // Field
    private static int i2cAddress = 0x68;
    private static int linuxFilehandle;

    private int acclX;
    private int acclY;
    private int acclZ;

    private int gyroX;
    private int gyroY;
    private int gyroZ;

    private double acclX_scaled;
    private double acclY_scaled;
    private double acclZ_scaled;

    private double gyroX_scaled;
    private double gyroY_scaled;
    private double gyroZ_scaled;

    // Method
    int readWord2C(int addr) {
        int val;
        val = wiringPiI2CReadReg8(linuxFilehandle, addr);
        val = val << 8;
        val += wiringPiI2CReadReg8(linuxFilehandle, addr + 1);
        if (val >= 0x8000) {
            val = -(65536 - val);
        }

        return val;
    }

    double dist(double a, double b) {
        return Math.sqrt((a * a) + (b * b));
    }

    double getYRotation(double x, double y, double z) {
        double radians;
        radians = Math.atan2(x, dist(y, z));
        return -(radians * (180.0 / Math.PI));
    }

    double getXRotation(double x, double y, double z) {
        double radians;
        radians = Math.atan2(y, dist(x, z));
        return (radians * (180.0 / Math.PI));
    }

    public void getAccl() throws InterruptedException {
        while (true) {
            acclX = readWord2C(0x3B);
            acclY = readWord2C(0x3D);
            acclZ = readWord2C(0x3F);

            acclX_scaled = acclX / 16384.0;
            acclY_scaled = acclY / 16384.0;
            acclZ_scaled = acclZ / 16384.0;
            System.out.println("\t\tacclZ: " + acclZ);
//            System.out.println("acclX: " + acclX + "\t\tacclY: " + acclY + "\t\tacclZ: " + acclZ);
//            System.out.println("My acclX_scaled: " + acclX_scaled + " | My acclY_scaled: " + acclY_scaled + " | My acclZ_scaled: " + acclZ_scaled);
//            System.out.println("My acclX_scaled: " + acclX_scaled);
//            System.out.println("My acclY_scaled: " + acclY_scaled);
//            System.out.println("My acclZ_scaled: " + acclZ_scaled);

//            System.out.println("My X rotation: " + getXRotation(acclX_scaled, acclY_scaled, acclZ_scaled) + " | My Y rotation: " + getYRotation(acclX_scaled, acclY_scaled, acclZ_scaled));
//            System.out.println("My X rotation: " + getXRotation(acclX_scaled, acclY_scaled, acclZ_scaled));
//            System.out.println("My Y rotation: " + getYRotation(acclX_scaled, acclY_scaled, acclZ_scaled));
            Thread.sleep(300);
        }
    }

    public void getGyro() throws InterruptedException {
        while (true) {
            gyroX = readWord2C(0x43);
            gyroY = readWord2C(0x45);
            gyroZ = readWord2C(0x47);

            gyroX_scaled = gyroX / 131.0;
            gyroY_scaled = gyroY / 131.0;
            gyroZ_scaled = gyroZ / 131.0;

//            System.out.println("gyroX :" + gyroX + "\t\tgyroY :" + gyroY + "\t\tgyroZ :" + gyroZ);
//            System.out.println("My gyroX_scaled: " + gyroX_scaled + " | My gyroY_scaled: " + gyroY_scaled + " | My gyroZ_scaled: " + gyroZ_scaled);
//            System.out.println("My gyroX_scaled: " + gyroX_scaled);
//            System.out.println("My gyroY_scaled: " + gyroY_scaled);
//            System.out.println("My gyroZ_scaled: " + gyroZ_scaled);

            Thread.sleep(500);
        }
    }

    public static void main(String[] args) throws Exception {
        linuxFilehandle = wiringPiI2CSetup(i2cAddress);
        wiringPiI2CWriteReg8(linuxFilehandle, 0x6B, 0x00);

        Gyro gyro = new Gyro();
        gyro.getAccl();
//        gyro.getGyro();

    }
}
