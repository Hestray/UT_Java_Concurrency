package Practicing_for_exam.Lab4.Sheet5_App2;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore P9 = new Semaphore(4);
        MyThread t1 = new MyThread(P9, 1, 2, (int) (Math.random() * (5 - 2) + 2), 2);
        MyThread t2 = new MyThread(P9, 2, 2, (int) (Math.random() * (6 - 3) + 3), 2);

        t1.start();
        t2.start();
    }
}
