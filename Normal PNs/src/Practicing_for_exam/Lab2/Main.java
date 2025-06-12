package Practicing_for_exam.Lab2;

public class Main {
    private static boolean stopThreads = false;
    public static void main(String[] args) {
//        Exercises.exercise1();
//        Exercises.exercise2();
//        Exercises.exercise3();
//        Exercises.exercise4();
        Exercises.exercise5();
    }

    public static boolean isStopThreads() {
        return stopThreads;
    }
}

class Exercises {
    static void exercise1() {
        FileService fileService = new FileService("DatetimeFile.txt");
        RThread rt = new RThread(fileService);
        WThread wt = new WThread(fileService);

        rt.start();
        wt.start();
    }

    static void exercise2() {
        Buffer buffer = new Buffer();
        Producer producer = new Producer(buffer);
        Consumer consumer_1 = new Consumer(buffer);
        Consumer consumer_2 = new Consumer(buffer);

        producer.start();
        consumer_1.start();
        consumer_2.start();
    }

    static void exercise3() {
        JoinTestThread t1 = new JoinTestThread("Thread 1", null, 75000);
        JoinTestThread t2 = new JoinTestThread("Thread 2", t1, 25000);

        t1.start();
        t2.start();
    }

    static void exercise4() {
        ThreadGroup tg = new ThreadGroup("Thread Group");
        Ex t1 = new Ex(tg, "Thread #1");
        Ex t2 = new Ex(tg, "Thread #2");
        Ex t3 = new Ex(tg, "Thread #3");

        t1.start(); t2.start(); t3.start();

        try {
            Thread.sleep(1000);
        } catch (Exception e) { e.printStackTrace(); }

        System.out.println(tg.activeCount() + " threads active");

        Thread[] threads = new Thread[tg.activeCount()];
        tg.enumerate(threads);
        for (Thread thread : threads) {
            System.out.println(thread.getName());
        }

        t1.stopThread();

        try {
            Thread.sleep(1000);
        } catch (Exception e) { e.printStackTrace(); }

        System.out.println(tg.activeCount() + " threads active");
        tg.interrupt();
    }

    static void exercise5() {
        WriteThread writeThread = new WriteThread();
        ReadThread readThread = new ReadThread();

        try {
            readThread.connect(writeThread.getPo());

            writeThread.start();
            readThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
