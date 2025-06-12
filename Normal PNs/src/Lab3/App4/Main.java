package Lab3.App4;

public class Main {
    public static void main(String[] args) {
        Object  P6    = new Object(),
                P10   = new Object();

        Sender   t1 = new Sender    (new Object[]{P6, P10}, 2, 3, 1);  // Thread 1
        Receiver t2 = new Receiver  (t1, 3, 5, 0);  // Thread 2
        Receiver t3 = new Receiver  (t1, 4, 6, 0);  // Thread 3

        t1.start();
        t2.start();
        t3.start();
    }
}
