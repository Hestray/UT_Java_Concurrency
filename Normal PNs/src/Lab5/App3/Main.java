package Lab5.App3;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {
        int x = 5;
        int y = 5;

        CountDownLatch T11 = new CountDownLatch(3);
        Object P6 = new Object(), P10 = new Object();

        Sender s = new Sender(T11, new Object[] {P6, P10}, 0,
                (int)Math.round(Math.random() * 1 + 2), 7);
        Receiver r1 = new Receiver(s, T11, 0,
                (int)Math.round(Math.random() * 2 + 3), x);
        Receiver r2 = new Receiver(s, T11, 1,
                (int)Math.round(Math.random() * 2 + 4), y);

        s.start();
        r1.start();
        r2.start();
    }
}
