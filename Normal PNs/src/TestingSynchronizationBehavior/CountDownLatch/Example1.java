package TestingSynchronizationBehavior.CountDownLatch;

import TestingSynchronizationBehavior.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

class Worker extends Thread {
    CountDownLatch latch;
    int num1, num2;
    int name;

    Worker(int name, CountDownLatch latch, int num1, int num2) {
        this.name = name;
        this.setName("Worker-" + name);
        this.num1 = num1;
        this.num2 = num2;
        this.latch = latch;
    }

    public void run() {
        System.out.println(Color.RED + "Worker " + Color.RESET + this.getName() + ": activity started.");

        if (this.name % 2 != 0) {
            System.out.println(Color.RED + "Worker " + Color.RESET + this.getName() + ": " + this.num1 + " + " + this.num2 + " = " + (this.num1 + this.num2));
            Result.addToResults(this.num1 + this.num2);
        } else {
            System.out.println(Color.RED + "Worker " + Color.RESET + this.getName() + ": " + this.num1 + " - " + this.num2 + " = " + (this.num1 - this.num2));
            Result.addToResults(this.num1 - this.num2);
        }

        try {
            Thread.sleep(2000 * this.name);
        } catch (InterruptedException e) {
            System.out.println(Color.RED + "Worker " + Color.RESET + this.getName() + ": SLEEP INTERRUPTED.");
        }

        System.out.println(Color.RED + "Worker " + Color.RESET + this.getName() + ": before latch, count = " + this.latch.getCount());
        this.latch.countDown();

        System.out.println(Color.RED + "Worker " + Color.RESET + this.getName() + ": check out.");

        try {
            this.latch.await();
        } catch (InterruptedException e) {
            System.out.println(Color.RED + "Worker " + Color.RESET + this.getName() + " was interrupted.");
        }
    }
}

class Result extends Thread {
    CountDownLatch latch;
    static List<Integer> results = Collections.synchronizedList(new ArrayList<Integer>());

    Result(CountDownLatch latch) {
        this.setName("Result-" + this.getId());
        this.latch = latch;
    }

    public static void addToResults(int value) {
        results.add(value);
    }

    public void run() {
        System.out.println(Color.BLUE + "Result " + Color.RESET + this.getName() + ": activity started.");
        System.out.println(Color.BLUE + "Result " + Color.RESET + this.getName() + ": before latch, count = " + this.latch.getCount());

        this.latch.countDown();


        try {
            System.out.println(Color.BLUE + "Result " + Color.RESET + this.getName() + ": check out.");

            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                System.out.println(Color.RED + "Result " + Color.RESET + this.getName() + "'s sleep was interrupted.");
            }

            this.latch.await();
        } catch (InterruptedException e) {
            System.out.println(Color.RED + "Result " + Color.RESET + this.getName() + " was interrupted.");
        }
    }
}

class Generator {
    public static int[] generate(int SIZE) {
        int[] array = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            array[i] = (int) (Math.random() * 10);
        }

        return array;
    }

    public static void display(int[] array) {
        System.out.println("Your array is: " + Arrays.toString(array));
    }
}

class Main {
    public static void main(String[] args) {
        int THREAD_WORKERS  = 4;
        int ARRAY_SIZE      = 12; // any multiple of 4
        int[] numbers       = Generator.generate(ARRAY_SIZE);
        int iter            = 0;

        Generator.display(numbers);

        while (iter < ARRAY_SIZE) {
            System.out.println(Color.CYAN + "ITERATION NUMBER " + (iter/4 + 1) + Color.RESET);

            Generator.display(new int[] {numbers[iter], numbers[iter+1], numbers[iter+2], numbers[iter+3]});

            CountDownLatch latch    = new CountDownLatch(THREAD_WORKERS);
            Worker w1 = new Worker(1, latch, numbers[iter], numbers[iter + 1]);
            Worker w2 = new Worker(2, latch, numbers[iter + 2], numbers[iter + 3]);
            Result r  = new Result(latch);

            w1.start();
            w2.start();
            r .start();

            System.out.println(Color.GREEN + Thread.currentThread().getName() + Color.RESET + ": before latch, count = " + latch.getCount());
            latch.countDown();

            System.out.println(Color.GREEN + Thread.currentThread().getName() + Color.RESET + " checking out.");

            try {
                latch.await();
            } catch (InterruptedException e) {
                System.out.println(Color.GREEN + Thread.currentThread().getName() + " was interrupted.");
            }

            iter += 4;
        }

        System.out.println(Color.BLUE + "Result" + Color.RESET + ": results are: " + Result.results.toString());
    }
}
