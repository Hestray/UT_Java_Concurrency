package Practicing_for_exam.Lab5.Sheet7_App1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class MyThread extends Thread {
    Semaphore P9, P10;
    CountDownLatch T8;
    int name, activity1, activity2, delay;

    MyThread(Semaphore P9, Semaphore P10, CountDownLatch T8, int name, int activity1, int activity2, int delay) {
        this.P9 = P9;
        this.P10 = P10;
        this.T8 = T8;
        this.name = name;
        this.setName("PN-" + name);
        this.activity1 = activity1;
        this.activity2 = activity2;
        this.delay = delay;
    }

    public void run() {
        System.out.println("Thread " + this.getName() + " - STATE 1");
        for (int i = 0; i < activity1 * 100000; i++) { i++; i--; }

        if (this.name == 1) {
            if (P9.tryAcquire()) {
                System.out.println("Thread " + this.getName() + " - STATE 2");
                for (int i = 0; i < activity2 * 100000; i++) { i++; i--; }

                if (P10.tryAcquire()) {
                    System.out.println("Thread " + this.getName() + " - STATE 3");

                    try {
                        Thread.sleep(delay * 500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    P10.release();
                } else {
                    System.out.println("Thread " + this.getName() + " - P10 cannot be acquired.");
                }
                P9.release();
            } else {
                System.out.println("Thread " + this.getName() + " - P9 cannot be acquired.");
            }
        } else {
            if (P10.tryAcquire()) {
                System.out.println("Thread " + this.getName() + " - STATE 2");
                for (int i = 0; i < activity2 * 100000; i++) { i++; i--; }

                if (P9.tryAcquire()) {
                    System.out.println("Thread " + this.getName() + " - STATE 3");

                    try {
                        Thread.sleep(delay * 500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    P9.release();
                } else {
                    System.out.println("Thread " + this.getName() + " - P9 cannot be acquired.");
                }
                P10.release();
            } else {
                System.out.println("Thread " + this.getName() + " - P10 cannot be acquired.");
            }
        }

        System.out.println("Thread " + this.getName() + " - STATE 4");

        try {
            this.T8.countDown();
            this.T8.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
