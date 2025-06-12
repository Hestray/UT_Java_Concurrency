package Lab5.Exchange;

import java.util.List;
import java.util.concurrent.Exchanger;

public class ExchangerTestSimple {
    public static void main(String[] args) {
        Exchanger<List<Integer>> exchanger = new Exchanger<List<Integer>>();
        MyThread t1 = new MyThread(1000, exchanger, "Duke");
        MyThread t2 = new MyThread(5000, exchanger, "Wild Wings");

        t1.start();
        t2.start();
    }
}
