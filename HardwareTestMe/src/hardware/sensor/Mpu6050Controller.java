//package hardware.sensor;
//
//import com.pi4j.io.i2c.I2CBus;
//import com.pi4j.io.i2c.I2CDevice;
//import com.pi4j.io.i2c.I2CFactory;
//import java.io.IOException;
//
//public class Mpu6050Controller {
//
//    private static byte[] accelData = new byte[6];
//
//    private static I2CBus bus = null;
//    private static I2CDevice mpu6050 = null;
//
//    public static void initialize() throws IOException, InterruptedException, I2CFactory.UnsupportedBusNumberException {
//        initializeI2C();
//        configureMpu6050();
//    }
//
//    private static void initializeI2C() throws IOException, I2CFactory.UnsupportedBusNumberException {
//        System.out.println("Creating I2C bus");
//        bus = I2CFactory.getInstance(I2CBus.BUS_1);
//        System.out.println("Creating I2C device");
//        mpu6050 = bus.getDevice(0x68);
//    }
//
//    private static void configureMpu6050() throws IOException, InterruptedException {
//
//        //1 Waking the device up
//        writeConfigRegisterAndValidate(
//            "Waking up device",
//            "Wake-up config succcessfully written: ",
//            Mpu6050Registers.MPU6050_RA_PWR_MGMT_1,
//            Mpu6050RegisterValues.MPU6050_RA_PWR_MGMT_1);
//
//        //2 Configure sample rate
//        writeConfigRegisterAndValidate(
//            "Configuring sample rate",
//            "Sample rate succcessfully written: ",
//            Mpu6050Registers.MPU6050_RA_SMPLRT_DIV,
//            Mpu6050RegisterValues.MPU6050_RA_SMPLRT_DIV);
//
//        //3 Setting global config
//        writeConfigRegisterAndValidate(
//            "Setting global config (digital low pass filter)",
//            "Global config succcessfully written: ",
//            Mpu6050Registers.MPU6050_RA_CONFIG,
//            Mpu6050RegisterValues.MPU6050_RA_CONFIG);
//
//        //4 Configure Gyroscope
//        writeConfigRegisterAndValidate(
//            "Configuring gyroscope",
//            "Gyroscope config successfully written: ",
//            Mpu6050Registers.MPU6050_RA_GYRO_CONFIG,
//            Mpu6050RegisterValues.MPU6050_RA_GYRO_CONFIG);
//
//        //5 Configure Accelerometer
//        writeConfigRegisterAndValidate(
//            "Configuring accelerometer",
//            "Accelerometer config successfully written: ",
//            Mpu6050Registers.MPU6050_RA_ACCEL_CONFIG,
//            Mpu6050RegisterValues.MPU6050_RA_ACCEL_CONFIG);
//
//        //6 Configure interrupts
//        writeConfigRegisterAndValidate(
//            "Configuring interrupts",
//            "Interrupt config successfully written: ",
//            Mpu6050Registers.MPU6050_RA_INT_ENABLE,
//            Mpu6050RegisterValues.MPU6050_RA_INT_ENABLE);
//
//        //7 Configure low power operations
//        writeConfigRegisterAndValidate(
//            "Configuring low power operations",
//            "Low power operation config successfully written: ",
//            Mpu6050Registers.MPU6050_RA_PWR_MGMT_2,
//            Mpu6050RegisterValues.MPU6050_RA_PWR_MGMT_2);
//
//        for (byte i = 1; i <= 120; i++) {
//            byte registerData = Mpu6050Controller.readRegister(i);
//            System.out.println(i + "\t\tRegisterData:" + Helper.formatBinary(registerData));
//        }
//
//        mpu6050.read(0x3B, accelData, 0, 6);
//        for (int j = 0; j < 100; j++) {
//            /*
//								 for(int i=0;i<6;i++){
//								 System.out.print(accelData[i]);
//								 
//								 }
//								 System.out.println("--");
//								 Thread.sleep(1000);
//             */
//
//            //byte value1=Mpu6050Controller.readRegister(Mpu6050.Mpu6050Registers.MPU6050_RA_GYRO_XOUT_H);
//            //double value=readWord2C(Mpu6050.Mpu6050Registers.MPU6050_RA_ACCEL_XOUT_H) / 16384;
//            //	System.out.println(value);
//            //	Thread.sleep(500);
//            double value = readWord2C(Mpu6050.Mpu6050Registers.MPU6050_RA_GYRO_XOUT_H) / 131;
//            System.out.println(value);
//            Thread.sleep(500);
//
//        }
//
//        System.exit(0);
//    }
//
//    private static void writeRegister(byte register, byte data) throws IOException {
//        mpu6050.write(register, data);
//    }
//
//    public static byte readRegister(byte register) throws IOException {
//        int data = mpu6050.read(register);
//        return (byte) data;
//    }
//
//    public static byte readRegister() throws IOException {
//        int data = mpu6050.read();
//        return (byte) data;
//    }
//
//    public static void writeConfigRegisterAndValidate(String initialText, String successText, byte register, byte registerData) throws IOException {
//        System.out.println(initialText);
//        writeRegister(register, registerData);
//        byte returnedRegisterData = Mpu6050Controller.readRegister(register);
//        if (returnedRegisterData == registerData) {
//            System.out.println(successText + Helper.formatBinary(returnedRegisterData));
//        } else {
//            throw new RuntimeException("Tried to write " + Helper.formatBinary(registerData) + " to "
//                + register + ", but validiating value returned " + Helper.formatBinary(returnedRegisterData));
//        }
//    }
//
//    public static double readWord2C(byte addr) throws IOException {
//        double value = readWord(addr);
//
//        if (value >= 0x8000) {
//            value = (double) -((65535 - value) + 1);
//        } else {
//            value = (double) value;
//        }
//
//        return value;
//    }
//
//    public static double readWord(byte addr) throws IOException {
//        int high = readRegister(addr);
//        int low = readRegister((byte) (addr + 1));
//        double value = (high << 8) + low;
//
//        return value;
//    }
//
//}
