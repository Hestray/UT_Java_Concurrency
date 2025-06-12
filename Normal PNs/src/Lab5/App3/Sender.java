package Lab5.App3;

import java.util.concurrent.CountDownLatch;

public class Sender extends Thread {
    CountDownLatch T11;
    Object[] monitors;
    int activity, delay;

    Sender(CountDownLatch T11, Object[] monitors, int name, int activity, int delay) {
        this.T11 = T11;
        this.monitors = monitors;
        this.setName(String.valueOf(name));
        this.activity = activity;
        this.delay = delay;
    }

    public void run() {
        System.out.println(this.getName() + "- State 1 has been reached.");

        try {
            Thread.sleep(delay * 500);
        } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println(this.getName() + "- State 2 has been reached.");
        for(int i = 0; i < activity * 100000; i++) { i++; i--; }

        synchronized (monitors[0]) {
            monitors[0].notify();
        }
        synchronized (monitors[1]) {
            monitors[1].notify();
        }

        System.out.println(this.getName() + "- State 3 has been reached.");

        try {
            this.T11.countDown();
            this.T11.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Object[] getMonitors() {
        return this.monitors;
    }
}
