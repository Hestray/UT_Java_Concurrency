package Lab3.App2_noDeadlock;

class MyThread extends Thread {
    Object[]    monitors;
    int[]       acMax,
                acMin;
    int         delay;

    MyThread(Object[] monitors, int delay, int[] acMax, int[] acMin) {
        this.monitors   = monitors;
        this.delay      = delay;
        this.acMax      = acMax;
        this.acMin      = acMin;
    }

    public void run() {
        System.out.println(this.getName() + " - State 1 has been reached");
        int k = (int) Math.round( Math.random() * (acMax[0] - acMin[0]) + acMin[0] );
        for (int i = 0; i < k * 100000; i++) { i++; i--; }

        synchronized (monitors[0]) {
            synchronized (monitors[1]) {
                System.out.println(this.getName() + " - State 2 has been reached");
                k = (int) Math.round(Math.random() * (acMax[1] - acMin[1]) + acMin[1]);
                for (int i = 0; i < k * 100000; i++) {
                    i++;
                    i--;
                }

                System.out.println(this.getName() + " - State 3 has been reached");

                try {
                    Thread.sleep(delay * 500);
                } catch (Exception e) { e.printStackTrace(); }
            }
        }


        System.out.println(this.getName() + " - State 4 has been reached");
    }
}
