package Lab5.App1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore P9 = new Semaphore(1);
        Semaphore P10 = new Semaphore(1);

        while(true) {
            CountDownLatch T8 = new CountDownLatch(2);

            MyThread t1 = new MyThread(0, P9, P10, T8, 4, new int[]{
                    (int) Math.round(Math.random() * 2 + 2),
                    (int) Math.round(Math.random() * 2 + 4)});
            MyThread t2 = new MyThread(1, P9, P10, T8, 5, new int[]{
                    (int) Math.round(Math.random() * 2 + 3),
                    (int) Math.round(Math.random() * 2 + 5)});

            t1.start();
            t2.start();

            try {
                T8.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
