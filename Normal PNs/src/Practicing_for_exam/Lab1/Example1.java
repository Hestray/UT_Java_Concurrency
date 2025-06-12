package Practicing_for_exam.Lab1;

public class Example1 extends Thread {
    private int counter = 0;

    Example1(String name) {
        super(name);
    }

    public void run() {
        while (counter < 20) {
            System.out.println(this.getName() + ":\t " + counter);
            counter++;

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                System.out.println("Thread " + this.getName() + " was interrupted");
            }
        }

        System.out.println(this.getName() + ":\t " + counter);
    }
}
