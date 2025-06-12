package TestingSynchronizationBehavior.CyclicBarrier;

import TestingSynchronizationBehavior.Color;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class AggregatorDemo implements Runnable {
    int[] numbers;

    AggregatorDemo() {
        new CyclicBarrierDemo();
        numbers = CyclicBarrierDemo.genNumbers();
        System.out.println("The numbers generated are: " + Arrays.toString(numbers));
    }

    public void run() {
        System.out.println(Color.PURPLE + "In the aggregator thread: " + Color.RESET + "name of thread: " + Thread.currentThread().getName());
        System.out.println(Color.PURPLE + "Aggregator: " + Color.RESET + Thread.currentThread().getName() + " is executing the calculus.");

        Worker w1 = new Worker(1, numbers[Main.iter], numbers[Main.iter + 1], 3);
        Worker w2 = new Worker(2, numbers[Main.iter + 2], numbers[Main.iter + 3], 6);
        Chatty c  = new Chatty(1, "First round of applause!");

        w1.start();
        w2.start();
        c.start();

        try {
            System.out.println("Number of participants to break barrier: " + CyclicBarrierDemo.barrier.getParties());

            CyclicBarrierDemo.barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println("Results are: \n"
                + "Worker 1 " + w1.getName() + ":\t " + w1.sum + "\n"
                + "Worker 2 " + w2.getName() + ":\t " + w2.sum);
    }
}

class Worker extends Thread {
    int num1, num2;
    int sum = 0;
    int delay, name;

    Worker(int name, int num1, int num2, int delay) {
        this.name = name;
        this.setName("Worker-" + name);
        this.num1 = num1;
        this.num2 = num2;
        this.delay = delay;
    }

    public void run() {
        System.out.println(Color.RED + "Worker " + Color.RESET + this.getName() + " activity started.");

        if (this.name % 2 == 0) {
            this.sum = num1 + num2;
        } else {
            this.sum = num1 - num2;
        }

        System.out.println(Color.RED + "Worker " + Color.RESET + this.getName() + " activity resulted in " + this.sum);
        try {
            Thread.sleep(delay*500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(Color.RED + "Worker " + Color.RESET + this.getName() + " WAITING FOR SYNC.");
            CyclicBarrierDemo.barrier.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            System.out.println(Color.RED + "The barrier for WORKER " + this.getName() + " has been broken." + Color.RESET);
//            throw new RuntimeException(e);
        }

        System.out.println(Color.GREEN + "Worker " + Color.RESET + this.getName() + " checking out.");
    }
}

class Chatty extends Thread {
    String msg;

    Chatty(int name, String msg) {
        this.setName("Chatty-" + name);
        this.msg = msg;
    }

    public void run() {
        System.out.println(Color.BLUE + "Chatty " + Color.RESET + this.getName() + " is about to talk.");

        System.out.println(Color.BLUE + "Chatty's message: " + Color.RESET + this.getName() + " " + msg);

        try {
            Thread.sleep(7500); // for an experiment, if we increase or decrease this, we can see
                                      // who the barrier delegates the Runnable exec to
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("BEFORE AWAIT: ");
            System.out.println(Color.BLUE + "From Chatty " + Color.RESET + this.getName() + ":\tThere are " +
                                Color.YELLOW + CyclicBarrierDemo.barrier.getNumberWaiting() + " participants waiting." + Color.RESET);
            CyclicBarrierDemo.barrier.await();
            System.out.println("AFTER AWAIT: ");
            System.out.println(Color.BLUE + "From Chatty " + Color.RESET + this.getName() + ":\tThere are " +
                    Color.YELLOW + CyclicBarrierDemo.barrier.getNumberWaiting() + " participants waiting." + Color.RESET);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            System.out.println(Color.BLUE + "The barrier for CHATTY has been broken." + Color.RESET);
//            throw new RuntimeException(e);
        }

    }
}

class CyclicBarrierDemo {
    static CyclicBarrier barrier;
    private static int NUM_WORKERS;
    private static int count;

    CyclicBarrierDemo() {
        setCount(12);
        setNumWorkers(5);
        setBarrier();
    }

    public static int[] genNumbers() {
        int[] numbers = new int[count];
        for (int i = 0; i < count; i++) {
            numbers[i] = (int) (Math.random() * 10);
        }

        return numbers;
    }

    public static void setCount(int count) {
        CyclicBarrierDemo.count = count;
    }

    public static void setNumWorkers(int numWorkers) {
        NUM_WORKERS = numWorkers;
    }

    public static void setBarrier() {
        CyclicBarrierDemo.barrier = new CyclicBarrier(NUM_WORKERS,
                () -> System.out.println(Color.YELLOW + "The thread " + Thread.currentThread().getName() +
                        " says that: The calculus is now finished!" + Color.RESET));
    }

    public static int getCount() {
        return count;
    }
}

class Main {
    static int iter = 0;

    public static void main(String[] args) {
        System.out.println(Color.GREEN + "In the main thread: " + Color.RESET + "name of thread: " + Thread.currentThread().getName());
        AggregatorDemo ag = new AggregatorDemo();

        while(iter < CyclicBarrierDemo.getCount()) {
            System.out.println(Color.CYAN + "ITERATION NUMBER " + (iter / 4 + 1) + Color.RESET);
            new Thread(ag).start();

            try {
                CyclicBarrierDemo.barrier.await();
                System.out.println(Thread.currentThread().getName() + " WAITED FOR BARRIER");
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                System.out.println(Color.RED + "The barrier was broken" + Color.RESET);
            }

            System.out.println("BEFORE RESET: ");
            System.out.println(Color.RED + "Statistics:" + Color.RESET +
                    "\nNumber of threads waiting: \t" + CyclicBarrierDemo.barrier.getNumberWaiting() +
                    "\nNumber of participants: \t" + CyclicBarrierDemo.barrier.getParties());

            try {
                System.out.println(Thread.currentThread().getName() + " will now sleep for a bit");
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Before resetting, let's see how many are still waiting: " + CyclicBarrierDemo.barrier.getNumberWaiting());

            CyclicBarrierDemo.barrier.reset();

            System.out.println("AFTER RESET: ");
            System.out.println(Color.RED + "Statistics:" + Color.RESET +
                    "\nNumber of threads waiting: \t" + CyclicBarrierDemo.barrier.getNumberWaiting() +
                    "\nNumber of participants: \t" + CyclicBarrierDemo.barrier.getParties());

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            iter += 4;
        }
    }
}

// CONCLUSION
// The number of threads waiting on the barrier are the ones that, so it will reflect that
// if there are less parties on the cyclic barrier than the total number of threads that should
// be waiting, then there will be a broken barrier such that the program can move on. So the number of parties
// should reflect the number of threads calling await
// For the sake of synchronizing with main as well, the barrier can be used on main as well