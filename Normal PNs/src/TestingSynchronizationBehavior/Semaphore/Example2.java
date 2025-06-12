package TestingSynchronizationBehavior.Semaphore;

import TestingSynchronizationBehavior.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

class Summing extends Thread {
    // generates 2 numbers and their sum
    private Semaphore s;
    private int sum = 0;

    Summing(Semaphore s, int name) {
        this.s = s;
        this.setName("Summing-" + name);
    }

    public void run() {
        System.out.println(Color.RED + "Summing thread " + Color.RESET + this.getName() + ": activity started.");

        try {
            this.s.acquire();

            System.out.println(Color.RED + "Summing thread " + Color.RESET + this.getName() + ": generating numbers...");
            try {
                Thread.sleep(3000);
                int n1 = (int) (Math.random() * 10);
                int n2 = (int) (Math.random() * 10);

                System.out.println(Color.RED + "Summing thread " + Color.RESET + this.getName() + ": calculating sum...");
                try {
                    Thread.sleep(1000);
                    this.sum = n1 + n2;
                    System.out.println(Color.RED + "Summing thread " + Color.RESET + this.getName() + ": sum calculated is " + this.sum);
                } catch (InterruptedException e) {
                    System.out.println(Color.RED + "Summing thread " + Color.RESET + this.getName() + ": sum calculation interrupted.");
                }
            } catch (InterruptedException e) {
                System.out.println(Color.RED + "Summing thread " + Color.RESET + this.getName() + ": number generation interrupted.");
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            this.s.release();
        }

        System.out.println(Color.RED + "Summing thread " + Color.RESET + this.getName() + ": check out.");
    }

    public int getSum() {
        return sum;
    }
}

class Average extends Thread {
    // calculates the sums of the Summing threads
    // attributes for average deter.
    private CyclicBarrier barrier;
    private List<Integer> elements = Collections.synchronizedList(new ArrayList<Integer>());
    private int result = 0;

    // attributes for generating numbers and loops
    private Semaphore s;
    private static int index = 0;

    Average(CyclicBarrier barrier, Semaphore s, int name) {
        this.setName("Average-" + name);
        this.barrier    = barrier;
        this.s          = s;
    }

    public void addElement(int elem) {
        this.elements.add(elem);
    }

    private void calcAvg() {
        for (Integer elem : elements) {
            this.result += elem;
        }
        Main.results[Main.index++] = (int) (this.result / elements.size());
    }

    private void startSum() {
        Summing s1 = new Summing(this.s, index+1);
        Summing s2 = new Summing(this.s, index+2);
        Summing s3 = new Summing(this.s, index+3);

        s1.start();
        s2.start();
        s3.start();

        addElement(s1.getSum());
        addElement(s2.getSum());
        addElement(s3.getSum());

        index += 3;
    }

    public void run() {
        System.out.println(Color.BLUE + "Average thread " + Color.RESET + this.getName() + ": activity started.");

        // activity of the average
        System.out.println(Color.BLUE + "Average thread " + Color.RESET + this.getName() + ": retrieving the sums...");
        try {
            Thread.sleep(2000);
            startSum();
        } catch (InterruptedException e) {
            System.out.println(Color.BLUE + "Average thread " + Color.RESET + this.getName() + ": retrieving sums interrupted.");
        }

        System.out.println(Color.BLUE + "Average thread " + Color.RESET + this.getName() + ": the elements so far are: " + this.elements);

        System.out.println(Color.BLUE + "Average thread " + Color.RESET + this.getName() + ": calculating average...");
        try {
            Thread.sleep(5000);
            calcAvg();
        } catch (InterruptedException e) {
            System.out.println(Color.BLUE + "Average thread " + Color.RESET + this.getName() + ": average interrupted.");
        }
        // end of activity

        // synchronization activity
        try {
            System.out.println(Color.BLUE + "Average thread " + Color.RESET + this.getName() + ": waiting for main...");
            this.barrier.await();
        } catch (InterruptedException e) {
            System.out.println(Color.BLUE + "Average thread " + Color.RESET + this.getName() + ": await interrupted.");
        } catch (BrokenBarrierException e) {
            System.out.println(Color.BLUE + "Average thread " + Color.RESET + this.getName() + ": barrier broken.");
        }

        System.out.println(Color.BLUE + "Average thread " + Color.RESET + this.getName() + ": checking out.");
    }


}

class Main {
    static int[] results     = new int[3];
    static int index         = 0;

    public static void main(String[] args) {
        int _WORKERS        = 3;    // how many threads utilize the semaphore
        int _CYC_THREADS    = 2;    // how many threads are participating in breaking the barrier: main and 1 average
        int _LOOPS          = 3;    // how many times I want to compute average
        Semaphore semaphore     = new Semaphore(_WORKERS);
        CyclicBarrier barrier   = new CyclicBarrier(_CYC_THREADS);

        while (index < _LOOPS) {
            System.out.println(Color.CYAN + "ITERATION NO. " + (index+1) + Color.RESET);

            System.out.println(Color.GREEN + "Main thread - " + Color.RESET + Thread.currentThread().getName() + ": activity started.");

            Average avg = new Average(barrier, semaphore, index+1);
            avg.start();

            System.out.println(Color.GREEN + "Main thread - " + Color.RESET + Thread.currentThread().getName() + ": waiting for barrier...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(Color.GREEN + "Main thread - " + Color.RESET + Thread.currentThread().getName() + ": sleep interrupted.");
            }

            try {
                barrier.await();
            } catch (InterruptedException e) {
                System.out.println(Color.GREEN + "Main thread - " + Color.RESET + Thread.currentThread().getName() + ": await interrupted.");
            } catch (BrokenBarrierException e) {
                System.out.println(Color.GREEN + "Main thread - " + Color.RESET + Thread.currentThread().getName() + ": barrier broken.");
            }
            System.out.println(Color.GREEN + "Main thread - " + Color.RESET + Thread.currentThread().getName() + ": checking out.");
        }
        System.out.println(Color.GREEN + "Main thread - " + Color.RESET + "results: " + Arrays.toString(results));
    }

}