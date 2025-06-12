package Lab5.App3;

import java.util.concurrent.CountDownLatch;

public class Receiver extends Thread {
    CountDownLatch T11;
    Object monitor;
    Sender s;
    int activity, delay;
    static int thCounter = 0;

    Receiver(Sender s, CountDownLatch T11, int name, int activity, int delay) {
        this.T11 = T11;
        this.s = s;
        if (thCounter < this.s.getMonitors().length) {
            this.monitor = this.s.getMonitors()[thCounter];
            thCounter++;
        }
        this.setName(String.valueOf(name));
        this.activity = activity;
        this.delay = delay;
    }

    public void run() {
        System.out.println("Receiver " + this.getName() + " - STATUS 1");

        synchronized (this.monitor) {
            try {
                Thread.sleep(delay * 500);
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Receiver " + this.getName() + " - STATUS 2");
        for(int i = 0; i < activity * 100000; i++) { i++; i--; }

        System.out.println("Receiver " + this.getName() + " - STATUS 3");

        try {
            this.T11.countDown();
            this.T11.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
