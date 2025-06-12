package Practicing_for_exam.Lab1.Application1;

public class ThreadCounter extends Thread {
    private int id;
    private Window win;
    private int procLoad;
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

            this.win.setProgressValue(id, counter);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    public void addObserver(Window win) {

    }
}
