package hardware.converter;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

public class PCF8591 {

    // Field
    public static final int AIN0 = 0x40;
    public static final int AIN1 = 0x41;
    public static final int AIN2 = 0x42;
    public static final int AIN3 = 0x43;

    private int i2cAddress; // 장치번호
    private int ainNo;
    private int analogVal; // 아날로그 값 저장할 목적

    // Constructor
    public PCF8591(int i2cAddress, int ainNo) {
        this.i2cAddress = i2cAddress;
        this.ainNo = ainNo;
    }

    // Method
    public int analogRead() throws Exception {
        // 무조건 I2CBus.BUS_1 만 사용 가능
        I2CBus i2cBus = I2CFactory.getInstance(I2CBus.BUS_1);
        // 무조건 48이 아닌 주소값을 얻어옴
        // 버스객체에서 디바이스를 얻어옴
        // 위 아래의 디바이스 용도가 다르기 때문에 필드로 설정하지 않음(read(), write())
        I2CDevice i2cDevice = i2cBus.getDevice(i2cAddress);
        // dummy read ( 첫번째에 제대로 된 값을 읽지 못할 수 있으므로 )
        i2cDevice.read(ainNo);
        analogVal = i2cDevice.read(ainNo);
        return analogVal;
    }

    // 외부에서 받은 값을 AOUT 핀으로 출력시킴
    public void analogWrite(byte value) throws Exception {
        I2CBus i2cBus = I2CFactory.getInstance(I2CBus.BUS_1);
        I2CDevice i2cDevice = i2cBus.getDevice(i2cAddress);
        i2cDevice.write(AIN0, value);
    }

    public static void main(String[] args) throws Exception {
        PCF8591 test = new PCF8591(0x48, AIN0);
        while(true) {
            // 아날로그 값 읽기
            int value = test.analogRead();
            System.out.println(value);
            // 아날로그 값 출력
            test.analogWrite((byte)value);
            Thread.sleep(200);
        }
    }
}
