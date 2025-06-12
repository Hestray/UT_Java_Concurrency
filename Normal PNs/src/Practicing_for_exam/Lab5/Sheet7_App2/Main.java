package Practicing_for_exam.Lab5.Sheet7_App2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Lock P9 = new ReentrantLock();
        Lock P10 = new ReentrantLock();
        CyclicBarrier T8 = new CyclicBarrier(4);

        while (true) {
            System.out.println("Main thread - STATE 0");

            MyThread t1 = new MyThread(new Lock[] {P9}, T8, 1, 4, (int)(Math.random() * 2 + 2));
            MyThread t2 = new MyThread(new Lock[] {P9, P10}, T8, 2, 3, (int)(Math.random() * 3 + 3));
            MyThread t3 = new MyThread(new Lock[] {P10}, T8, 3, 5, (int)(Math.random() * 3 + 2));

            t1.start();
            t2.start();
            t3.start();

            try {
                T8.await();
                T8.reset(); // just in case that i've set the wrong number of barriers
            } catch (InterruptedException e) {
                System.out.println("Main thread - barrier interrupted.");
            } catch (BrokenBarrierException e) {
                System.out.println("Main thread - barrier broken.");
            }
        }
    }
}
