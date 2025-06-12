package Practicing_for_exam.Extra.T2_1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;

public class MyThread extends Thread {
    int name, delay, activity;
    Lock P9, P11;
    CyclicBarrier barrier;

    MyThread(Lock P9, Lock P11, CyclicBarrier barrier, int name, int delay, int activity) {
        this.P9 = P9;
        this.P11 = P11;
        this.barrier = barrier;
        this.name = name;
        this.setName("PN-" + name);
        this.delay = delay;
        this.activity = activity;
    }

    public void run() {
        System.out.println("Thread " + this.getName() + " - STATE 1");

        if (this.name == 1) {
            if (P9.tryLock()) {
                System.out.println("Thread " + this.getName() + " - STATE 2");
                for (int i = 0; i < activity * 100000; i++) { i--; i++; }

                if (P11.tryLock()) {
                    System.out.println("Thread " + this.getName() + " - STATE 3");

                    try {
                        Thread.sleep(delay * 500);
                    } catch (InterruptedException e) {
                        System.out.println("Thread " + this.getName() + " - sleep interrupted.");
                    }

                    P11.unlock();
                } else {
                    System.out.println("Thread " + this.getName() + " - P11 could not be locked.");
                }

                P9.unlock();
            } else {
                System.out.println("Thread " + this.getName() + " - P9 could not be locked.");
            }
        } else {
            if (P11.tryLock()) {
                System.out.println("Thread " + this.getName() + " - STATE 2");
                for (int i = 0; i < activity * 100000; i++) { i--; i++; }

                if (P9.tryLock()) {
                    System.out.println("Thread " + this.getName() + " - STATE 3");

                    try {
                        Thread.sleep(delay * 500);
                    } catch (InterruptedException e) {
                        System.out.println("Thread " + this.getName() + " - sleep interrupted.");
                    }

                    P9.unlock();
                } else {
                    System.out.println("Thread " + this.getName() + " - P9 could not be locked.");
                }

                P11.unlock();
            } else {
                System.out.println("Thread " + this.getName() + " - P11 could not be locked.");
            }
        }

        System.out.println("Thread " + this.getName() + " - STATE 4");

        try {
            this.barrier.await();
        } catch (InterruptedException e) {
            System.out.println("Thread " + this.getName() + " - barrier interrupted");
        } catch (BrokenBarrierException e) {
            System.out.println("Thread " + this.getName() + " - barrier broken");
        }
    }
}
