package Practicing_for_exam.Lab5.Sheet7_App1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore P9 = new Semaphore(1);
        Semaphore P10 = new Semaphore(1);

        while (true) {
            System.out.println("Main thread - STATE 0");

            CountDownLatch T8 = new CountDownLatch(2);

            MyThread t1 = new MyThread(P9, P10, T8, 1,
                    (int) (Math.random() * 2 + 2), (int) (Math.random() * 2 + 4), 4);
            MyThread t2 = new MyThread(P9, P10, T8, 2,
                    (int) (Math.random() * 2 + 3), (int) (Math.random() * 2 + 5), 5);

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
