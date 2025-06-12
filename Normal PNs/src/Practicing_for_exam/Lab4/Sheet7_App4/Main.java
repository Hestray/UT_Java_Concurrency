package Practicing_for_exam.Lab4.Sheet7_App4;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore P8 = new Semaphore(2);

        MyThread t1 = new MyThread(1, P8, 1, 5, (int) (Math.random() * 3 + 3));
        MyThread t2 = new MyThread(2, P8, 1, 3, (int) (Math.random() * 3 + 4));
        MyThread t3 = new MyThread(3, P8, 1, 6, (int) (Math.random() * 2 + 5));

        t1.start();
        t2.start();
        t3.start();
    }
}
