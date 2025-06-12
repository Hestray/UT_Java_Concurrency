package Practicing_for_exam.Lab2;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

class WriteThread extends Thread {
    private PipedOutputStream po;

    WriteThread() {
        this.po = new PipedOutputStream();
    }

    public void run() {
        try {
            while(true) {
                int d = (int) (10 * Math.random());
                System.out.println("Writing thread is sending: " + d);
                po.write(d);
                sleep(400);
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    PipedOutputStream getPo() { return po; }
}

class ReadThread extends Thread {
    private PipedInputStream pi;

    ReadThread() {
        this.pi = new PipedInputStream();
    }

    public void run() {
        try {
            while(true) {
                if (pi.available() > 0) {
                    System.out.println("Read Thread has received: " + pi.read());
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    void connect(PipedOutputStream os) throws Exception {
        pi.connect(os);
    }
}
