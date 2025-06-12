package Lab3.App3;

public class MyThread extends Thread {
    Object  monitor;
    int     acMin,
            acMax,
            delay;
    int     k;

    MyThread(Object monitor, int acMin, int acMax, int delay) {
        this.monitor    = monitor;
        this.acMin      = acMin;
        this.acMax      = acMax;
        this.delay      = delay;
    }

    public void run() {
        while (true) {
            System.out.println("\u001B[0m" + this.getName() + "\u001B[31m" + " - State 1 has been reached.");

            synchronized (monitor) {
                System.out.println("\u001B[0m" + this.getName() + "\u001B[36m" + " - State 2 has been reached.");
                k = (int) Math.round( Math.random() * (acMax - acMin) + acMin );
                for (int i = 0; i < k * 100000; i++) { i++; i--; }
            }

            System.out.println("\u001B[0m" + this.getName() + "\u001B[33m" + " - State 3 has been reached.");

            try {
                Thread.sleep(delay * 500);
            } catch (Exception e) { e.printStackTrace(); }

            System.out.println("\u001B[0m" + this.getName() + "\u001B[92m" + " - State 4 has been reached.");
        }
    }
}
