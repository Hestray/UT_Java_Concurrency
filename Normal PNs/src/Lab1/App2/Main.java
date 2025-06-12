package Lab1.App2;

// the link (controller) between the observer (window) and observable (fir)
public class Main {
    private static final int noOfThreads    = 24;
    private static final int processorLoad  = 1000000;

    public static void main(String args[]){
        Window win = new Window(noOfThreads);
        Fir f;
        for(int i =0; i < noOfThreads; i++){
            f = new Fir(i,i+2,win,processorLoad);
            f.addObserver(win);
            f.start();
        }
    }
}
