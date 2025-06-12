package Practicing_for_exam.Lab1;

import Practicing_for_exam.Lab1.Application1.ThreadCounter;
import Practicing_for_exam.Lab1.Application1.Window;

public class Main {
    public static void main(String[] args) {
//        Exercises.example1();
//        Exercises.example2();
//        Exercises.app1();
        Exercises.app2();
    }
}

class Exercises {
    static void example1() {
        Example1 ex1 = new Example1("Counter1");
        Example1 ex2 = new Example1("Counter2");
        Example1 ex3 = new Example1("Counter3");

        ex1.start();
        ex2.start();
        ex3.start();
    }

    static void example2() {
        Example2 ex1 = new Example2("Counter1");
        Example2 ex2 = new Example2("Counter2");
        Example2 ex3 = new Example2("Counter3");

        // Runnable requires casting to Thread before starting the thread
        new Thread(ex1).start();
        new Thread(ex2).start();
        new Thread(ex3).start();

        // in the example itself, what was used was
        // new Thread(runnableTarget, threadName)
        // eg: new Thread(ex1, "Counter1");
        // it essentially creates a new thread using the runnable we implemented
    }

    static void app1() {
        int noOfThreads  = 10;
        int procLoad     = 1000000;

        Window win = new Window(noOfThreads);
        ThreadCounter t;
        for (int i = 0; i < noOfThreads; i++) {
            t = new ThreadCounter(i, win, procLoad, i+1);
            t.start();
        }
    }

    static void app2() {
        int noOfThreads  = 10;
        int procLoad     = 1000000;

        Window win = new Window(noOfThreads);
        ThreadCounter t;
        for (int i = 0; i < noOfThreads; i++) {
            t = new ThreadCounter(i, win, procLoad, i+1);
            t.addObserver(win); // add this
            t.start();
        }
    }
}
