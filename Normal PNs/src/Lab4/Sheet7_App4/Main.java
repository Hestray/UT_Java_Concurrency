package Lab4.Sheet7_App4;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore s = new Semaphore(2);

        MyThread t1 = new MyThread(s, (int)Math.round(Math.random() * 3 + 4), 3);
        MyThread t2 = new MyThread(s, (int)Math.round(Math.random() * 3 + 3), 5);
        MyThread t3 = new MyThread(s, (int)Math.round(Math.random() * 2 + 5), 6);

        t1.start();
        t2.start();
        t3.start();
    }
}
