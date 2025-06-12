package Practicing_for_exam.Lab1.Application2;

import java.util.Observable;

public class ThreadCounter extends Observable implements Runnable {
    int id;
    private Window win;
    private int procLoad;
    private int priority; // add this
    int counter = 0;

    public ThreadCounter(int id, Window win, int procLoad, int priority){
        this.id = id;
        this.win = win;
        this.procLoad = procLoad;
        this.setPriority(priority);
    }

    public void run() {
        while (counter < 1000) {
            for (int j = 0; j < this.procLoad; j++) {
                j++; j--;
            }

            counter++;
            this.setChanged();      // add this
            this.notifyObservers(); // add this

            this.win.setProgressValue(id, counter);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    public void setPriority(int priority) { // add this
        this.priority = priority;
    }
}
