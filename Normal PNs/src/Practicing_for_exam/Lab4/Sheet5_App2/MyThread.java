package Practicing_for_exam.Lab4.Sheet5_App2;

import java.util.concurrent.Semaphore;

public class MyThread extends Thread {
    Semaphore s;
    int name, delay, activity, permits;

    MyThread(Semaphore s, int name, int delay, int activity, int permits) {
        this.s = s;
        this.name = name;
        this.setName("PN-" + name);
        this.delay = delay;
        this.activity = activity;
        this.permits = permits;
    }

    public void run() {
        while (true) {
            System.out.println("Thread " + this.getName() + " - STATE 1");
            try {
                Thread.sleep(delay * 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Thread " + this.getName() + " - STATE 2");
            try {
                this.s.acquire(permits);

                System.out.println("Thread " + this.getName() + " - STATE 3");
                for (int i = 0; i < activity * 100000; i++) { i++; i--; }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                this.s.release(permits);
            }

            System.out.println("Thread " + this.getName() + " - STATE 4");
        }
    }
}
