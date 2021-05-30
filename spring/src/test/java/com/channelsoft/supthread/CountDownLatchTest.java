package com.channelsoft.supthread;

/**
 * 火箭发射器
 *
 * @author Ma_wei
 * @since 2020-05-07 10:08
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws Exception {
        TestCountDown testCountDown = new TestCountDown();
        for (int i = 1; i <= 20; i++) {
            String threadName = "countName" + i;
            new Thread(() -> {
                try {
                    testCountDown.testCountDownSync(threadName);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
        System.out.println("Clear Door");
    }

}
