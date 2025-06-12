package Practicing_for_exam.Lab4.Sheet7_App4;

import java.util.concurrent.Semaphore;

public class MyThread extends Thread {
    Semaphore s;
    int name, permits, delay, Act;

    public MyThread(int name, Semaphore s, int permits, int delay, int Act) {
        this.name = name;
        this.setName("PN-" + name);
        this.s = s;
        this.permits = permits;
        this.delay = delay;
        this.Act = Act;
    }

    public void run() {
        while(true) {
            System.out.println("Thread " + this.getName() + " - STATE 1");

            try {
                this.s.acquire(permits);

                System.out.println("Thread " + this.getName() + " - STATE 2");
                for (int i = 0; i < Act * 100000; i++) { i++; i--; }
            } catch (Exception e) {
                e.printStackTrace();
            } finally  {
                this.s.release(permits);
            }

            System.out.println("Thread " + this.getName() + " - STATE 3");
            try {
                Thread.sleep(delay * 500);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Thread " + this.getName() + " - STATE 4");
        }
    }
}
