package Practicing_for_exam.Extra.T2_1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Lock P9 = new ReentrantLock();
        Lock P11 = new ReentrantLock();
        CyclicBarrier T7 = new CyclicBarrier(3);

        while (true) {
            System.out.println("Main thread - STATE 0 ");// just for ease of sight

            MyThread t1 = new MyThread(P9, P11, T7, 1, 6, (int) (Math.random() * 4 + 4));
            MyThread t2 = new MyThread(P9, P11, T7, 2, 7, (int) (Math.random() * 3 + 2));

            t1.start();
            t2.start();

            try {
                T7.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
