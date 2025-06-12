package Lab4.Sheet4_App2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Lock        P9      = new ReentrantLock(),
                    P10     = new ReentrantLock();
        int[]   activity    = new int[] {
                    // for left-side thread
                    (int) Math.round(Math.random() * (4 - 2) + 2),
                    (int) Math.round(Math.random() * (6 - 4) + 4),
                    // for left-side thread
                    (int) Math.round(Math.random() * (5 - 3) + 3),
                    (int) Math.round(Math.random() * (7 - 5) + 5)
        };

        MyThread    t1 = new MyThread(new Lock[] {P9, P10}, 1, 4, new int[] {activity[0], activity[1] }),
                    t2 = new MyThread(new Lock[] {P9, P10}, 2, 5, new int[] {activity[2], activity[3] });

        t1.start();
        t2.start();
    }
}
