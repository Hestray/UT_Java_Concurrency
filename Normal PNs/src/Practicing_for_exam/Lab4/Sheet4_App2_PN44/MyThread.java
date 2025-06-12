package Practicing_for_exam.Lab4.Sheet4_App2_PN44;

import java.util.concurrent.locks.Lock;

public class MyThread extends Thread {
    Lock P9, P10;
    int delay, name;
    int[] activity;

    MyThread(int name, Lock P9, Lock P10, int delay, int[] activity) {
        this.setName("PN-" + name);
        this.P9 = P9;
        this.P10 = P10;

        this.delay = delay;
        this.activity = activity;
    }

    public void run() {
        System.out.println("Thread " + this.getName() + " - STATE 1");
        for (int i = 0; i < activity[0] * 100000; i++) {i--; i++;}

        if (this.name == 1) {
            // left-side thread
            if (P9.tryLock()) {
                System.out.println("Thread " + this.getName() + " - STATE 2");
                for (int i = 0; i < activity[1] * 100000; i++) {i--; i++;}

                if (P10.tryLock()) {
                    System.out.println("Thread " + this.getName() + " - STATE 3");

                    try {
                        Thread.sleep(delay * 500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    P10.unlock();
                } else {
                    System.out.println("Thread " + this.getName() + " - P10 could not be acquired.");
                }

                P9.unlock();
            } else {
                System.out.println("Thread " + this.getName() + " - P9 could not be locked");
            }
        } else {
            // right-side thread
            if (P10.tryLock()) {
                System.out.println("Thread " + this.getName() + " - STATE 2");
                for (int i = 0; i < activity[1] * 100000; i++) {i--; i++;}

                if (P9.tryLock()) {
                    System.out.println("Thread " + this.getName() + " - STATE 3");

                    try {
                        Thread.sleep(delay * 500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    P9.unlock();
                } else {
                    System.out.println("Thread " + this.getName() + " - P9 could not be acquired.");
                }

                P10.unlock();
            } else {
                System.out.println("Thread " + this.getName() + " - P10 could not be locked");
            }

            System.out.println("Thread " + this.getName() + " - STATE 4");
        }
    }
}
