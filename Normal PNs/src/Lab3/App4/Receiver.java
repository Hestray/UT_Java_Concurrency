package Lab3.App4;

import java.util.Arrays;

public class Receiver extends Thread {
    Object      monitors;
    int         acMax,
                acMin,
                delay;
    static int  thCount = 0;
    Sender      sender;
    Receiver(Sender sender, int acMin, int acMax, int delay) {
        this.sender = sender;
        if (thCount < this.sender.getMonitors().length) {
            this.monitors = this.sender.getMonitors()[thCount];
            thCount++;
        }
        this.acMin = acMin;
        this.acMax = acMax;
        this.delay = delay;
    }

    public void run() {
        System.out.println(this.getName() + "- State 1 has been reached.");

        synchronized (monitors) {
            try {
                monitors.wait();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }

        System.out.println(this.getName() + "- State 2 has been reached.");
        int k = (int) Math.round ( Math.random() * (acMax - acMin) + acMin );
        for(int i = 0; i < k * 100000; i++) { i++; i--; }

        System.out.println(this.getName() + "- State 3 has been reached.");

        try {
            this.sender.join();
        } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("Receiver joined.");
    }
}
