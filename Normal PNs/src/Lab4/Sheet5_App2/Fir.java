package Lab4.Sheet5_App2;

import java.util.concurrent.Semaphore;

public class Fir extends Thread {
    int name, delay, k, permit;
    Semaphore s;

    Fir (int n, Semaphore sa, int delay, int k, int permit) {
        this.name   = n;
        this.s      = sa;
        this.delay  = delay;
        this.k      = k;
        this.permit = permit;
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Thread " + name + " State 1");
                Thread.sleep(this.delay * 500);

                System.out.println("Thread " + name + " State 2");
                this.s.acquire(this.permit); // critical region
                System.out.println("Thread " + name + " took a token from the semaphore");

                System.out.println("Thread " + name + " State 3");
                for (int i = 0; i < k * 100000; i++) {
                    i++; i--;
                }
                this.s.release(this.permit); // end of critical region

                System.out.println("Thread " + name + " released a token from the semaphore");
                System.out.println("Thread " + name + " State 4");
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
