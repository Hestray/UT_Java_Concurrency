package Lab4.Sheet5_Reentrant;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Lock l = new ReentrantLock();
        Fir f1, f2;

        f1 = new Fir(1, l);
        f2 = new Fir(2, l);

        f1.start();
        f2.start();
    }
}
