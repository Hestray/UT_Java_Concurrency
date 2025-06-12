package Lab2.App1;

public class WThread extends Thread {
    FileService service;

    public WThread(FileService service) {
        this.service = service;
    }

    public void run() {
        while (!Main.isStopThreads()) {
            String msg = String.valueOf(Math.round(Math.random() * 100));
            // added synchronized statement here
            synchronized (service) {
                service.write(msg);
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
