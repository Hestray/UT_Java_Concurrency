package Practicing_for_exam.Lab1;

public class Example2 implements Runnable {
    // same as Example 1, but using implements Runnable
    private int counter = 0;
    private String name;

    // can't name it because  it implements an interface
    // workaround is to use member field
    Example2(String name) {
        this.name = name;
    }

    public void run() {
        while (counter < 20) {
            System.out.println(this.name + ":\t " + counter);
            counter++;

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                System.out.println("Thread " + this.name + " was interrupted");
            }
        }

        System.out.println(this.name + ":\t " + counter);
    }

    // If I don't feel like casting to Thread, I can implement the start() function as such:
    public void start(){
        new Thread(this).start();
    }
    // this automatically casts to Thread
}
