package Practicing_for_exam.Lab5.Sheet7_App3;

import java.util.concurrent.CountDownLatch;

public class Receiver extends Thread {
    int name, delay, activity;
    CountDownLatch latch;
    final Object P;
    Sender s;

    Receiver(Sender s, int name, int delay, int activity, CountDownLatch latch) {
        this.name = name;
        this.setName("Receiver-" + name);

        this.s = s;
        if(name == 1) { this.P = s.P6; }
        else { this.P = s.P10; }

        this.delay = delay;
        this.activity = activity;
        this.latch = latch;
    }

    public void run() {
        System.out.println("Receiver " + this.getName() + " - STATE 1");

        try {
            Thread.sleep(delay * 500);
        } catch (InterruptedException e) {
            System.out.println("Receiver " + this.getName() + " - sleep interrupted.");
        }

        synchronized (this.P) {
            try {
                P.wait();
            } catch (InterruptedException e) {
                System.out.println("Receiver " + this.getName() + " - wait interrupted.");
            }
        }

        System.out.println("Receiver " + this.getName() + " - STATE 2");
        for (int i = 0; i < this.activity * 100000; i++) { i--; i++; }

        System.out.println("Receiver " + this.getName() + " - STATE 3");

        try {
            latch.countDown();
            latch.await();
        } catch (InterruptedException e) {
            System.out.println("Receiver " + this.getName() + " - latch interrupted.");
        }
    }
}
