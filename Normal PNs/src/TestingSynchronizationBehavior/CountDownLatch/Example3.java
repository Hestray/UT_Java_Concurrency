package TestingSynchronizationBehavior.CountDownLatch;


import TestingSynchronizationBehavior.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

// this example is also based on Example #2 and on the same source as the previous example
class WorkerComplete implements Runnable {
    private List<String> outputScraper;
    private CountDownLatch readyThreadCounter;
    private CountDownLatch callingThreadBlocker;
    private CountDownLatch completedThreadCounter;

    public WorkerComplete(
            List<String> outputScraper,
            CountDownLatch readyThreadCounter,
            CountDownLatch callingThreadBlocker,
            CountDownLatch completedThreadCounter
    ) {
        this.outputScraper           = outputScraper;
        this.readyThreadCounter      = readyThreadCounter;
        this.callingThreadBlocker    = callingThreadBlocker;
        this.completedThreadCounter  = completedThreadCounter;
    }

    @Override
    public void run() {
        readyThreadCounter.countDown();

        try {
            callingThreadBlocker.await();
            System.out.println(Color.GREEN + Thread.currentThread().getName() + Color.RESET + " - Worker working.");
            outputScraper.add("Counted down.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            completedThreadCounter.countDown();
        }
    }
}

class MainComplete {
    public static void main(String[] args) throws InterruptedException {
        int NUM_WORKERS = 5;

        List<String>    outputScraper           = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch  readyThreadCounter      = new CountDownLatch(NUM_WORKERS);
        CountDownLatch  callingThreadBlocker    = new CountDownLatch(1);
        CountDownLatch  completedThreadCounter  = new CountDownLatch(NUM_WORKERS);

        List<Thread> workers = Stream
                .generate(() -> new Thread(new WorkerComplete(
                        outputScraper, readyThreadCounter, callingThreadBlocker, completedThreadCounter
                )))
                .limit(NUM_WORKERS)
                .collect(toList());

        workers.forEach(Thread::start);     // start all workers
        readyThreadCounter.await();         // this makes sure that all the threads are started
        outputScraper.add("Workers ready");
        callingThreadBlocker.countDown();   // this allows the threads to start their activity
        completedThreadCounter.await();     // this makes sure that every thread ends at the same time
        outputScraper.add("Workers complete");

        System.out.println(Color.CYAN + "List: " + Color.RESET);
        for (String s : outputScraper) {
            System.out.println(s);
        }
    }
}