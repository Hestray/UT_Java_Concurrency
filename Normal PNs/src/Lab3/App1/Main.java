package Lab3.App1;

import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Object  P9  = new Object(),
                P10 = new Object();
        new MyThread(P9,   2, 4, 4).start();
        new MyThread(Arrays.asList(P9, P10),          2, 5, 5).start();
        new MyThread(P10,  3, 6, 3).start();
    }
}
