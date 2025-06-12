package Practicing_for_exam.Lab3;

class Example1 extends Thread {
    int acMin,
        acMax,
        delayMin,
        delayMax;
    Object mutex;

    Example1(Object mutex, int acMin, int acMax, int delayMin, int delayMax) {
        this.mutex = mutex;
        this.acMin = acMin;
        this.acMax = acMax;
        this.delayMin = delayMin;
        this.delayMax = delayMax;
    }

    public void run() {
        System.out.println(this.getName() + " STATE 1 has been reached");

        try {
            int delay = (int)Math.round(Math.random() * (delayMax - delayMin) + delayMin);
            Thread.sleep(delay * 500);
        } catch (Exception e) { e.printStackTrace(); }

        System.out.println(this.getName() + " STATE 2 has been reached");

        synchronized (mutex) {
            int k = (int) Math.round(Math.random() * (acMax - acMin) + acMin);
            for (int i = 0; i < k * 100000; i++) {
                i++; i--;
            }
            System.out.println(this.getName() + " STATE 3 has been reached");
        }

        System.out.println(this.getName() + " STATE 4 has been reached");
    }
}


class App1 extends Thread {
    Object P9, P10;
    int delay,
        acMin,
        acMax;

    App1(Object P9, int delay, int acMin, int acMax) {
        this.P9 = P9;
        this.delay = delay;
        this.acMin = acMin;
        this.acMax = acMax;
    }

    App1(Object P9, Object P10, int delay, int acMin, int acMax) {
        this.P9 = P9;
        this.P10 = P10;
        this.delay = delay;
        this.acMin = acMin;
        this.acMax = acMax;
    }

    public void run() {
        System.out.println(this.getName() + " STATE 1 has been reached");

        if (P10 == null) {
            synchronized (P9) {
                System.out.println(this.getName() + " STATE 2 has been reached");

                int k = (int) Math.round(Math.random() * (acMax - acMin) + acMin);
                for (int i = 0; i < k * 100000; i++) {
                    i++; i--;
                }

                try {
                    Thread.sleep(delay * 500);
                } catch (Exception e) { e.printStackTrace(); }
            }
        } else {
            synchronized (P9) {
                synchronized (P10) {
                    System.out.println(this.getName() + " STATE 2 has been reached");

                    int k = (int) Math.round(Math.random() * (acMax - acMin) + acMin);
                    for (int i = 0; i < k * 100000; i++) {
                        i++; i--;
                    }

                    try {
                        Thread.sleep(delay * 500);
                    } catch (Exception e) { e.printStackTrace(); }
                }
            }
        }

        System.out.println(this.getName() + " STATE 3 has been reached");
    }
}

class Sender extends Thread {
    Object[] P;
    int delay,
        acMin,
        acMax;

    Sender(Object[] P, int delay, int acMin, int acMax) {
        this.P = P;
        this.delay = delay;
        this.acMin = acMin;
        this.acMax = acMax;
    }

    public void run() {
        System.out.println(this.getName() + " STATE 1 has been reached");

        try {
            Thread.sleep(delay * 500);
        } catch (Exception e) { e.printStackTrace(); }

        System.out.println(this.getName() + " STATE 2 has been reached");
        int k = (int)Math.round(Math.random() * (acMax - acMin) + acMin);
        for (int i = 0; i < k * 100000; i++) {
            i++; i--;
        }

        synchronized (P[0]) {
            P[0].notify();
        }

        synchronized (P[1]) {
            P[1].notify();
        }

        System.out.println(this.getName() + " STATE 3 has been reached");
    }

    public Object[] getP() {
        return P;
    }
}

class Receiver extends Thread {
    Object P;
    private static int noMonitors = 0;
    Sender s;
    int acMin,
        acMax;

    Receiver(Sender s, int acMin, int acMax) {
        this.s = s;
        if (noMonitors < s.getP().length) {
            this.P = s.getP()[noMonitors];
            noMonitors++;
        }
        this.acMin = acMin;
        this.acMax = acMax;
    }

    public void run() {
        System.out.println(this.getName() + " STATE 1 has been reached");

        synchronized (P) {
            try {
                P.wait();
            } catch (Exception e) { e.printStackTrace(); }
        }

        System.out.println(this.getName() + " STATE 2 has been reached");
        int k = (int) Math.round(Math.random() * (acMax - acMin) + acMin);
        for (int i = 0; i < k * 100000; i++) {
            i++; i--;
        }

        System.out.println(this.getName() + " STATE 3 has been reached");
        try {
            this.s.join();
        } catch (Exception e) { e.printStackTrace(); }
    }

}