package Lab4.Sheet7_App4;

import java.util.concurrent.Semaphore;

public class MyThread extends Thread {
    Semaphore s;
    int activity, delay;

    MyThread(Semaphore s, int activity, int delay) {
        this.s = s;
        this.activity = activity;
        this.delay = delay;
    }

    public void run() {
        System.out.println("Thread " + this.getName() + " - STATE 1");

        try {
            this.s.acquire(1);

            System.out.println("Thread " + this.getName() + " - STATE 2");
            for (int i = 0; i < this.activity * 100000; i++) { i++; i--; }

            this.s.release(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread " + this.getName() + " - STATE 3");
        try {
            Thread.sleep(this.delay * 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread " + this.getName() + " - STATE 4");
    }
}
