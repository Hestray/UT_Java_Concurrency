package Practicing_for_exam.Extra.T2_2;

import java.util.concurrent.Semaphore;

public class MyThread extends Thread {
    int name, delay, activity, permits;
    Semaphore P11;
    Object P10; // monitor
    Thread t; // thread to join on

    MyThread(Object P10, int name, int delay, int activity) {
        this.t = t;
        this.P10 = P10;
        this.name = name;
        this.setName("PN-" + name);
        this.delay = delay;
        this.activity = activity;
    }

    MyThread(Semaphore P11, Object P10, int name, int delay, int activity, int permits) {
        this.t = t;
        this.P11 = P11;
        this.P10 = P10;
        this.name = name;
        this.setName("PN-" + name);
        this.delay = delay;
        this.activity = activity;
        this.permits = permits;
    }

    public void run() {
        System.out.println("Thread " + this.getName() + " - STATUS 1");

        if (this.name == 1) {   // right-most thread
            synchronized(this.P10) {
                System.out.println("Thread " + this.getName() + " - STATUS 2");
                for (int i = 0; i < this.activity * 100000; i++) { i--; i++; }

                try {
                    Thread.sleep(delay * 500);
                } catch (InterruptedException e) {
                    System.out.println("Thread " + this.getName() + " - sleep interrupted");
                }
            }
        } else {
            if (this.name == 2) { // middle thread
                if (this.P11.tryAcquire(permits)) {
                    synchronized (this.P10) {
                        System.out.println("Thread " + this.getName() + " - STATUS 2");
                        for (int i = 0; i < this.activity * 100000; i++) {
                            i--;
                            i++;
                        }

                        try {
                            Thread.sleep(delay * 500);
                        } catch (InterruptedException e) {
                            System.out.println("Thread " + this.getName() + " - sleep interrupted");
                        }
                    }

                    this.P11.release(permits);
                }
            } else { // if name is 3 (right-most thread)
                if (this.P11.tryAcquire(permits)) {
                    System.out.println("Thread " + this.getName() + " - STATUS 2");
                    for (int i = 0; i < this.activity * 100000; i++) {
                        i--;
                        i++;
                    }

                    try {
                        Thread.sleep(delay * 500);
                    } catch (InterruptedException e) {
                        System.out.println("Thread " + this.getName() + " - sleep interrupted");
                    }

                    this.P11.release(permits);
                } else {
                    System.out.println("Thread " + this.getName() + " - P11 could not be acquired.");
                }
            }
        }

        System.out.println("Thread " + this.getName() + " - STATUS 3");
    }
}
