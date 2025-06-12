package Practicing_for_exam.Lab5.Sheet7_App3;

import java.util.concurrent.CountDownLatch;

public class Sender extends Thread {
    int name, delay, activity;
    CountDownLatch latch;
    Object P6, P10;

    Sender(int name, int delay, int activity, CountDownLatch latch, Object P6, Object P10) {
        this.name = name;
        this.setName("Sender-" + name);
        this.delay = delay;
        this.activity = activity;
        this.latch = latch;
        this.P6 = P6;
        this.P10 = P10;
    }

    public void run() {
        System.out.println("Sender " + this.getName() + " - STATE 1");
        try {
            Thread.sleep(delay * 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Sender " + this.getName() + " - STATE 2");
        for (int i = 0; i < activity * 100000; i++) { i--; i++; }

        synchronized (P6) {
            P6.notify();
        }

        synchronized (P10) {
            P10.notify();
        }

        System.out.println("Sender " + this.getName() + " - STATE 3");

        try {
            latch.countDown();
            latch.await();
        } catch (InterruptedException e) {
            System.out.println("Sender " + this.getName() + " - latch interrupted.");
        }
    }
}
