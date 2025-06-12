package Practicing_for_exam.Lab3;

public class Main {
    public static void main(String[] args) {
//        Exercises.example1();
//        Exercises.app1();
//        Exercises.app4();
        Exercises.repeatedApp4();
    }
}

class Exercises {
    private static Object   P8  = new Object(),
                            P9  = new Object(),
                            P10 = new Object();
    static void example1() {
        Example1 t1 = new Example1(P8, 3, 6, 2, 4);
        Example1 t2 = new Example1(P8, 4, 7, 3, 5);
        t1.start();
        t2.start();
    }

    static void app1() {
        App1 t1 = new App1(P9, 4, 2, 4);
        App1 t2 = new App1(P9, P10, 3, 3, 6);
        App1 t3 = new App1(P10, 5, 2, 5);

        t1.start();
        t2.start();
        t3.start();
    }

    static void app4() {
        Sender s = new Sender(new Object[]{P8, P10}, 7, 2, 3);
        Receiver r1 = new Receiver(s, 3, 5);
        Receiver r2 = new Receiver(s, 4, 6);


        s.start();
        r1.start();
        r2.start();
    }

    static void repeatedApp4() {
        Sender_App4 s = new Sender_App4("Sender", new Object[]{P9, P10}, 7, 2, 3);
        Receiver_App4 r1 = new Receiver_App4("Receiver #1", s,3, 5);
        Receiver_App4 r2 = new Receiver_App4("Receiver #2", s,4, 6);

        s.start();
        r1.start();
        r2.start();
        }
}
