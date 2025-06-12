package Lab3.App4;

class Sender extends Thread {
    Object[]    monitors;
    int         acMax,
                acMin,
                delay;

    Sender(Object[] monitors, int acMin, int acMax, int delay) {
        this.monitors = monitors;
        this.acMin = acMin;
        this.acMax = acMax;
        this.delay = delay;
    }

    public void run() {
        System.out.println(this.getName() + "- State 1 has been reached.");

        try {
            if (delay != 0) { Thread.sleep(delay * 500); }
        } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println(this.getName() + "- State 2 has been reached.");
        int k = (int) Math.round ( Math.random() * (acMax - acMin) + acMin );
        for(int i = 0; i < k * 100000; i++) { i++; i--; }

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
