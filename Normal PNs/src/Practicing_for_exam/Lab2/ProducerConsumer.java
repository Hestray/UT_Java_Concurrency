package Practicing_for_exam.Lab2;

import java.util.ArrayList;

class Producer implements Runnable {
    private Buffer buffer;
    private Thread thread;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void run() {
        while (true) {
            buffer.add(Math.random());
            System.out.println("Produce " + thread.getName() + " gave a task.");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer extends Thread {
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        while (true) {
            System.out.println("Consumer " + this.getName() + " received:\t " + buffer.get());
        }
    }
}

class Buffer {
    ArrayList<Double> content = new ArrayList<>();
    synchronized void add(double value) {
        content.add(new Double(value));
        notify();
    }

    synchronized double get() {
        double d = -1;

        try {
            if (content.size() == 0) wait();
            d = content.get(0);
            content.remove(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return d;
    }
}