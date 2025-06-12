package Practicing_for_exam.Lab3;

class Sender_App4 extends Thread {
    Object[] monitors;
    int delay,
        acMin,
        acMax;

    Sender_App4(String name, Object[] monitors, int delay, int acMin, int acMax) {
        this.setName(name);
        this.monitors = monitors;
        this.delay = delay;
        this.acMin = acMin;
        this.acMax = acMax;
    }

    public void run() {
        System.out.println(this.getName() + "- State 1 has been reached.");

        try {
            Thread.sleep(delay * 500);
        } catch (Exception e) { e.printStackTrace(); }

        System.out.println(this.getName() + "- State 2 has been reached.");
        int k = (int) Math.round( Math.random() * (acMax - acMin) + acMin );
        for (int i = 0; i < k * 100000; i++) {
            i++; i--;
        }

        synchronized (monitors[0]) {
            monitors[0].notify();
        }

        synchronized (monitors[1]) {
            monitors[1].notify();
        }

        System.out.println(this.getName() + "- State 3 has been reached.");
    }

    public Object[] getMonitors() {
        return monitors;
    }
}

class Receiver_App4 extends Thread {
    Object monitor;
    int acMin,
        acMax;
    Sender_App4 s;
    static int counterThreads = 0;

    Receiver_App4(String name, Sender_App4 s, int acMin, int acMax) {
        this.setName(name);
        this.s = s;
        if (counterThreads < s.getMonitors().length) {
            this.monitor = s.getMonitors()[counterThreads];
            counterThreads++;
        }
        this.acMin = acMin;
        this.acMax = acMax;
    }

    public void run() {
        System.out.println(this.getName() + " - State 1 has been reached");

        synchronized (monitor) {
            try {
                monitor.wait();
            } catch (Exception e) { e.printStackTrace(); }
        }

        System.out.println(this.getName() + " - State 2 has been reached");
        int k = (int) Math.round( Math.random() * (acMax - acMin) + acMin );
        for (int i = 0; i < k * 100000; i++) {
            i++; i--;
        }

        System.out.println(this.getName() + " - State 3 has been reached");

        synchronized (monitor) {
            try {
                s.join();
            } catch (Exception e) { e.printStackTrace(); }
        }
    }
}
