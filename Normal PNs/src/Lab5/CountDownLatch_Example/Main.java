package Lab5.CountDownLatch_Example;

import java.util.concurrent.CountDownLatch;

class MyThread extends Thread {
    CountDownLatch latch;
    int i = 1;

    MyThread(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        activity();
        latch.countDown();
        System.out.println("Latch in thread " + this.getName() + " has " + latch.getCount() + " counters left.");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        activity();
    }

    private void activity() {
        System.out.println("Thread " + this.getName() + " activity " + i);
        i++;

        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);
        MyThread t1 = new MyThread(latch);
        MyThread t2 = new MyThread(latch);

        t1.start();
        t2.start();
    }
}
