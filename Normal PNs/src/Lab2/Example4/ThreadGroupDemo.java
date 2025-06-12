package Lab2.Example4;

class ThreadEx extends Thread {
    boolean stop;

    ThreadEx(ThreadGroup tg, String name) {
        super(tg, name);
        stop = false;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " ON.");
        try {
            for (int i = 1; i < 1000; i++) {
                // the for is some form of fail check to make it stop after 999 iterations
                System.out.println("." + i);
                Thread.sleep(250);
                synchronized (this) {
                    if (stop)
                        break;
                }
            }
        } catch (Exception exc) {
            System.out.println(Thread.currentThread().getName() + " interrupted.");
        }
        System.out.println(Thread.currentThread().getName() + " The end.");
    }

    public void stopThread() {
        stop = true;
    }
}


public class ThreadGroupDemo {
    public static void main(String[] args) throws Exception {
        ThreadGroup tg = new ThreadGroup("Group of threads");
        ThreadEx fir1 = new ThreadEx(tg, "ThreadEx #1");
        ThreadEx fir2 = new ThreadEx(tg, "ThreadEx #2");
        ThreadEx fir3 = new ThreadEx(tg, "ThreadEx #3");

        fir1.start();
        fir2.start();
        fir3.start();

        Thread.sleep(1000);
        System.out.println(tg.activeCount() + " Thread in group.");

        Thread thrds[] = new Thread[tg.activeCount()];
        tg.enumerate(thrds);
        for (Thread t : thrds) {
            System.out.println(t.getName());
        }

        fir1.stopThread();
        Thread.sleep(1000);
        System.out.println(tg.activeCount() + " Thread in group.");
        tg.interrupt();
    }
}
