package Lab4.Sheet4_App2;

import java.util.concurrent.locks.Lock;

public class MyThread extends Thread {
    Lock[] P;
    int name, delay;
    int[] activity;

    MyThread(Lock[] P, int name, int delay, int[] activity) {
        this.P = P;
        this.name = name;
        this.delay = delay;
        this.activity = activity;
    }

    public void run() {
        System.out.println("Thread " + name + " state 1");
        for (int i = 0; i < activity[0] * 100000; i++) { i++; i--; }

        if (name == 1) {
            if (P[0].tryLock()) { // left-side thread; P9
                try {
                    System.out.println("Thread " + name + " state 2");
                    for (int i = 0; i < activity[1] * 100000; i++) {
                        i++;
                        i--;
                    }

                    if (P[1].tryLock()) { // P10
                        try {
                            System.out.println("Thread " + name + " state 3");

                            Thread.sleep(delay * 500);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            P[1].unlock();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    P[0].unlock();
                }
            }
        } else {
            if (P[1].tryLock()) { // right-side thread; P10
                try {
                    System.out.println("Thread " + name + " state 2");
                    for (int i = 0; i < activity[1] * 100000; i++) { i++; i--; }

                    if (P[0].tryLock()) { // P9
                        try {
                            System.out.println("Thread " + name + " state 3");

                            Thread.sleep(delay * 500);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            P[0].unlock();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    P[1].unlock();
                }
            }
        }

        System.out.println("Thread " + name + " state 4");
    }
}
