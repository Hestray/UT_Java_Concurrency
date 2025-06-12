package Lab5.App2;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Lock P9 = new ReentrantLock();
        Lock P10 = new ReentrantLock();
        CyclicBarrier T8 = new CyclicBarrier(4);

        while(true) {
            MyThread t1 = new MyThread(0, new Lock[]{P9}, T8,
                    (int) Math.round(Math.random() * 2 + 2), 4);
            MyThread t2 = new MyThread(1, new Lock[]{P10}, T8,
                    (int) Math.round(Math.random() * 3 + 3), 3);
            MyThread t3 = new MyThread(2, new Lock[]{P9, P10}, T8,
                    (int) Math.round(Math.random() * 3 + 2), 5);

            t1.start();
            t2.start();
            t3.start();

            try {
                T8.await();
                T8.reset();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
