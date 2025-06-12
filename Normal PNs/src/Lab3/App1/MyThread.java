package Lab3.App1;
import java.util.List;

class MyThread extends Thread {
    // monitors
    Object[]    monitors;
    Object      monitor;
    // for delays and timings
    int delay;
    int acMin;
    int acMax;

    // methods
    MyThread(Object monitor, int delay, int acMin, int acMax) {
        this.monitor = monitor;
        this.delay = delay;
        this.acMin = acMin;
        this.acMax = acMax;
    }

    MyThread(Object[] monitors, int acMin, int acMax, int delay) {
        this.monitors   = monitors;
        this.acMin      = acMin;
        this.acMax      = acMax;
        this.delay      = delay;
    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");

        if(monitor != null) {
            synchronized(monitor) {
                System.out.println(this.getName() + " - STATE 2");

                int k = (int) (Math.random() * (acMax - acMin) + acMin);
                for (int i = 0; i < k * 100000; i++) {
                    i++; i--;
                }

                try {
                    Thread.sleep(delay * 500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        } else {
            synchronized(monitors[0]) {
                synchronized(monitors[1]) {
                    System.out.println(this.getName() + " - STATE 2");

                    int k = (int) (Math.random() * (acMax - acMin) + acMin);
                    for (int i = 0; i < k * 100000; i++) {
                        i++; i--;
                    }

                    try {
                        Thread.sleep(delay * 500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        System.out.println(this.getName() + " - STATE 3");
    }
}
