package Lab5.App1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class MyThread extends Thread {
    Semaphore P9, P10;
    CountDownLatch T8;
    int name;
    int activity1, activity2, delay;

    MyThread(int name, Semaphore P9, Semaphore P10, CountDownLatch T8, int delay, int[] activity) {
        this.setName(String.valueOf(name));
        this.P9 = P9;
        this.P10 = P10;
        this.T8 = T8;
        this.activity1 = activity[0];
        this.activity2 = activity[1];
        this.delay = delay;
    }

    public void run() {
        System.out.println("Thread " + this.getName() + " - STATE 1");
        for (int i = 0; i < activity1 * 100000; i++) { i++; i--; }

        if (this.name == 0) {   // LEFT-SIDE THREAD
            if (this.P9.tryAcquire(1)) {    // TRY TO ACQUIRE PERMIT FOR SEMAPHORE P9
                try {
                    System.out.println("Thread " + this.getName() + " - STATE 2");
                    for (int i = 0; i < activity2 * 100000; i++) { i++; i--; }

                    if (this.P10.tryAcquire(1)) {
                        try {   // TRY TO ACQUIRE PERMIT FOR SEMAPHORE P10
                            System.out.println("Thread " + this.getName() + " - STATE 3");

                            Thread.sleep(delay * 500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            this.P10.release(1);
                        }
                    } else {
                        System.out.println("Thread " + this.getName() + " - could not acquire semaphore P10");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    this.P9.release(1);
                }
            } else {
                System.out.println("Thread " + this.getName() + " - could not acquire semaphore P9");
            }
        } else {    // RIGHT-SIDE THREAD
            if (this.P10.tryAcquire(1)) {
                try {
                    System.out.println("Thread " + this.getName() + " - STATE 2");
                    for (int i = 0; i < activity2 * 100000; i++) { i++; i--; }

                    if (this.P9.tryAcquire(1)) {
                        try {
                            System.out.println("Thread " + this.getName() + " - STATE 3");

                            Thread.sleep(delay * 500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            this.P9.release(1);
                        }
                    } else {
                        System.out.println("Thread " + this.getName() + " - could not acquire semaphore P9");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    this.P9.release(1);
                }
            } else {
                System.out.println("Thread " + this.getName() + " - could not acquire semaphore P10");
            }
        }

        System.out.println("Thread " + this.getName() + " - STATE 4");

        try {
            this.T8.countDown();
            this.T8.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
