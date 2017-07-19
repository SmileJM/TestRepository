package hardware.test;

import hardware.sensor.GyroDirection;

public class Type1 {
    // Field

    // Constructor
    // Method
    public static void main(String[] args) throws InterruptedException {
        GyroDirection direction = new GyroDirection();
        direction.init();

        int preYValue = 0;
        int curYValue = 0;
        int motion1 = 0;
        int count = 0;
        while (true) {
            int x = direction.getAcclX();
            curYValue = direction.getAcclY() / 16;

//            System.out.println("y: " + y);
            if (preYValue > 0) {
                if (curYValue > preYValue + 300) {
                    count = 0;
                    motion1++;

                    if (motion1 == 2) {
                        System.out.println("동작1");
                        motion1 = 0;
                        count = 0;
                        System.out.println("y: " + curYValue);
                        Thread.sleep(200);
                        continue;
                    }
                }
            }

            if (curYValue > preYValue + 1000) {
                System.out.println("동작2");
            }

            count++;
            if (count > 10) {
                count = 0;
                motion1 = 0;
            }

            System.out.println("y: " + curYValue);

            preYValue = curYValue;
            Thread.sleep(200);
        }
    }
}
