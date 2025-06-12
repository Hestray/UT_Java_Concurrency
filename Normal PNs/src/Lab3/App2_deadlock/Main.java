package Lab3.App2_deadlock;

public class Main {
    public static void main(String[] args) {
        Object  P9  = new Object(),
                P10 = new Object();
        new MyThread(new Object[]{P9, P10}, 4, new int[]{4, 6}, new int[]{2, 4}).start();
        new MyThread(new Object[]{P9, P10}, 5, new int[]{5, 7}, new int[]{3, 5}).start();

        // because of the deadlock, nothing is happening (in most cases)
    }
}
