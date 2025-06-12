package Practicing_for_exam.Lab5.Sheet7_App2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;

public class MyThread extends Thread {
    Lock[] P;
    CyclicBarrier barrier;
    int name, delay, activity;

    MyThread(Lock[] P, CyclicBarrier barrier, int name, int delay, int activity) {
        this.P = P;
        this.barrier = barrier;
        this.name = name;
        this.setName("PN-" + name);
        this.delay = delay;
        this.activity = activity;
    }

    public void run() {
        System.out.println("Thread " + this.getName() + " - STATE 1");

        if(this.P.length == 1) {
            this.P[0].lock();

            System.out.println("Thread " + this.getName() + " - STATE 2");
            for (int i = 0; i < activity * 100000; i++) { i++; i--; }

            try {
                Thread.sleep(delay * 500);
            } catch (InterruptedException e) {
                System.out.println("Thread " + this.getName() + " - sleep interrupted.");
            }

            this.P[0].unlock();
        } else {
            this.P[0].lock();
            this.P[1].lock();

            System.out.println("Thread " + this.getName() + " - STATE 2");
            for (int i = 0; i < activity * 100000; i++) { i++; i--; }

            try {
                Thread.sleep(delay * 500);
            } catch (InterruptedException e) {
                System.out.println("Thread " + this.getName() + " - sleep interrupted.");
            }

            this.P[0].unlock();
            this.P[1].unlock();
        }

        System.out.println("Thread " + this.getName() + " - STATE 3");

        try {
            this.barrier.await();
        } catch (InterruptedException e) {
            System.out.println("Thread " + this.getName() + " - barrier interrupted.");
        } catch (BrokenBarrierException e) {
            System.out.println("Thread " + this.getName() + " - barrier broken.");
        }
    }
}
