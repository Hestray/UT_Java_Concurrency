package Practicing_for_exam.Lab4.Sheet4_App2_PN44;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Lock P9 = new ReentrantLock();
        Lock P10 = new ReentrantLock();

        MyThread t1 = new MyThread(1, P9, P10, 4,
                new int[] {
                        (int) (Math.random() * (4 - 2) + 2),
                        (int) (Math.random() * 2 + 4)
                });
        MyThread t2 = new MyThread(2, P9, P10, 5,
                        new int[] {
                                (int) (Math.random() * (5 - 3) + 3),
                                (int) (Math.random() * 2 + 4)
                        });

        t1.start();
        t2.start();
    }
}
