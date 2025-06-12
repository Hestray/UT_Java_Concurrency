package Lab5.CyclicBarrier_Example;

import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {
        main1();
//        main2();
    }

    private static void main1() {
        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + " executes - Barrier routine");
            }
        });
        MyThread t1 = new MyThread(barrier);
        MyThread t2 = new MyThread(barrier);
        MyThread t3 = new MyThread(barrier);

        t1.start();
        t2.start();
        t3.start();
    }

    private static void main2() {
        CyclicBarrier barrier = new CyclicBarrier(3);
        MyThread t1 = new MyThread(barrier);
        MyThread t2 = new MyThread(barrier);
        MyThread t3 = new MyThread(barrier);

        t1.start();
        t2.start();
        t3.start();
    }
}
