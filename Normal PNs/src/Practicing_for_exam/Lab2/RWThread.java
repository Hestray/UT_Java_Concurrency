package Practicing_for_exam.Lab2;

import java.io.*;
import java.util.Date;

class RThread extends Thread {
    FileService fileService;

    public RThread(FileService fileService) {
        this.fileService = fileService;
    }

    public void run() {
        while (!Main.isStopThreads()) {
            try {
                String msgRead;
                synchronized (fileService) {
                    msgRead = fileService.read();
                }
                System.out.println(msgRead);

                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

class WThread extends Thread {
    FileService fileService;

    public WThread(FileService fileService) {
        this.fileService = fileService;
    }

    public void run() {
        while (!Main.isStopThreads()) {
            String msg = String.valueOf(Math.round(Math.random() * 100));
            synchronized (fileService) {
                fileService.write(msg);
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class FileService {
    private String          fileName;
    private BufferedReader in;
    private PrintWriter out;

    public FileService(String fName) {
        fileName = fName;
        try {
            out = new PrintWriter(new FileWriter(fileName, true));
            in  = new BufferedReader((new FileReader(fileName)));
        } catch (Exception e) { e.printStackTrace(); }
    }

    public String read() throws IOException {
        String iterator, last = "no message to read";

        while ((iterator = in.readLine()) != null) {
            last = new Date(System.currentTimeMillis()) + " - " + iterator;
        }

        return last;
    }

    public void write(String msg) {
        Date date = new Date(System.currentTimeMillis());
        out.println("Date: " + date);
        out.println("Message: " + msg);
        out.flush();
    }
}
