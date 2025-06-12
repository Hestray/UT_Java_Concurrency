package Lab3.App3;

public class Main {
    public static void main(String[] args) {
        Object P8 = new Object();

        MyThread t1 = new MyThread(P8, 3, 6, 5);
        MyThread t2 = new MyThread(P8, 4, 7, 3);
        MyThread t3 = new MyThread(P8, 5, 7, 6);

        t1.start();
        t2.start();
        t3.start();
    }
}
