package Practicing_for_exam.Lab5.Sheet7_App3;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {
        int x = 5;
        int y = 5;

        Object P6 = new Object(), P10 = new Object();
        CountDownLatch T11 = new CountDownLatch(3);

        Sender s = new Sender(1, 7, (int) (Math.random() * 1 + 2), T11, P6, P10);
        Receiver r1 = new Receiver(s, 1, x, (int) (Math.random() * 2 + 3), T11);
        Receiver r2 = new Receiver(s, 2, y, (int) (Math.random() * 2 + 4), T11);

        s.start();
        r1.start();
        r2.start();

        try {
            T11.await();
            System.out.println("Synchronization done.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
