package Lab5.App2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;

public class MyThread extends Thread {
    Lock[] P;
    CyclicBarrier T8;
    int name;
    int activity, delay;

    MyThread(int name, Lock[] P, CyclicBarrier T8, int activity, int delay) {
        this.setName(String.valueOf(name));
        this.P = P;
        this.T8 = T8;
        this.activity = activity;
        this.delay = delay;
    }

    public void run() {
        System.out.println("Thread " + this.getName() + " - STATE 1");

        if (this.name == 0 || this.name == 2) { // LEFT-MOST OR RIGHT-MOST THREAD
            this.P[0].lock();

            System.out.println("Thread " + this.getName() + " - STATE 2");
            for (int i = 0; i < activity * 100000; i++) { i++; i--; }

            try {
                Thread.sleep(delay * 500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            this.P[0].unlock();
        } else {
            this.P[0].lock();
            this.P[1].lock();

            System.out.println("Thread " + this.getName() + " - STATE 2");
            for (int i = 0; i < activity * 100000; i++) { i++; i--; }

            try {
                Thread.sleep(500 * delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            this.P[1].unlock();
            this.P[0].unlock();
        }

        System.out.println("Thread " + this.getName() + " - STATE 3");

        try {
            this.T8.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
