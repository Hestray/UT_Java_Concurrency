package Lab1.App2;

import java.util.Observable;

public class Fir extends Observable implements Runnable {
    int id;
    Window win;
    int processorLoad;
    private int priority;
    public int c = 0;
    Thread t;

    Fir(int id,int priority,Window win, int procLoad){
        this.id             = id;
        this.win            = win;
        this.processorLoad  = procLoad;
        this.setPriority(priority);
    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    public void run(){

        while(c<1000){

            for(int j=0;j<this.processorLoad;j++){
                j++;j--;
            }

            c++;
            this.setChanged();
            this.notifyObservers(c);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {}
        }
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}