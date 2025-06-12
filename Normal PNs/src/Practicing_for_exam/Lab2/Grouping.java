package Practicing_for_exam.Lab2;

import com.sun.media.jfxmediaimpl.HostUtils;

class Ex extends Thread {
    private boolean stop;

    public Ex(ThreadGroup group, String name) {
        super(group, name);
        stop = false;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running");

        try {
            for (int i = 1; i < 1000; i++) {
                System.out.println(".");
                Thread.sleep(250);
                synchronized (this) {
                    if (stop) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName() + " is interrupted");
        }

        System.out.println(Thread.currentThread().getName() + " is exiting");
    }

    public void stopThread() {
        stop = true;
    }
}
