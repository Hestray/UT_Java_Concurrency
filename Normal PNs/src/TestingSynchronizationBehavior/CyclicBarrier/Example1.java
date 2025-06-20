package TestingSynchronizationBehavior.CyclicBarrier;

// example from geeks for geeks

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class Computation1 implements Runnable {
    public static int product = 0;

    public void run() {
        product = 2 * 3;
        try {
            Tester.newBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

class Computation2 implements Runnable {
    public static int sum = 0;

    public void run() {
        // check if newBarrier is broken or not
        System.out.println("Is the barrier broken? - " + Tester.newBarrier.isBroken());
        sum = 10 + 20;
        try {
            Tester.newBarrier.await(3000, TimeUnit.MILLISECONDS);
            // number of parties waiting at the barrier
            System.out.println("Number of parties waiting at the barrier at this point = " + Tester.newBarrier.getNumberWaiting());
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}

class Tester implements Runnable {
    public static CyclicBarrier newBarrier = new CyclicBarrier(3);

    public static void main(String[] args) {
        // parent thread
        Tester test = new Tester();
        Thread t1 = new Thread(test);
        t1.start();
    }

    public void run() {
        System.out.println("Number of parties required to trip the barrier = " + newBarrier.getParties());
        System.out.println("Sum of product and sum = " + (Computation1.product + Computation2.sum));

        // objects on which the child thread has to run
        Computation1 comp1 = new Computation1();
        Computation2 comp2 = new Computation2();

        // creation of child threads
        Thread t1 = new Thread(comp1);
        Thread t2 = new Thread(comp2);

        // moving child threads to runnable state
        t1.start();
        t2.start();

        try {
            Tester.newBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        // barrier breaks as the number of threads waiting for the barrier
        // at this point = 3
        System.out.println("Sum of product and sum = " + (Computation1.product + Computation2.sum));

        // Resetting the newBarrier
        newBarrier.reset();
        System.out.println("Barrier reset successful");
    }
}