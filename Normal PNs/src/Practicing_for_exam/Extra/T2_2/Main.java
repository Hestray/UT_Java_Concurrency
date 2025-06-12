package Practicing_for_exam.Extra.T2_2;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Object P10 = new Object();
        Semaphore P11 = new Semaphore(3);

        while (true) {
            System.out.println("Main thread - STATE 0"); // just to verify myself

            MyThread t1 = new MyThread(P10, 1, 4, (int)(Math.random() * 3 + 3));
            MyThread t2 = new MyThread(P11, P10, 2, 5,
                    (int)(Math.round(Math.random() * 2 + 5)),3);
            MyThread t3 = new MyThread(P11, P10, 3, 6,
                    (int)(Math.round(Math.random() * 2 + 2)),2);

            t1.start();
            t2.start();
            t3.start();

            try {
                t1.join();
                t2.join();
                t3.join();
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted while waiting for child threads");
            }
        }
    }
}
