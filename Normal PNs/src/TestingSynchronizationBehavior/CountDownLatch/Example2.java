package TestingSynchronizationBehavior.CountDownLatch;

import TestingSynchronizationBehavior.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

// this is an example taken from baeldung/com/java-countdown-latch

class WorkerTest implements Runnable {
    private List<String> outputScraper;
    private CountDownLatch countDownLatch;

    public WorkerTest(List<String> outputScraper, CountDownLatch countDownLatch) {
        this.outputScraper  = outputScraper;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        doSomeWork();
        outputScraper.add("Counter Down");
        countDownLatch.countDown();
    }

    private void doSomeWork() {
        System.out.println(Color.GREEN + Thread.currentThread().getName() + Color.RESET + " - Doing some work");
    }
}

class MainTest {
    public static void main(String[] args) throws InterruptedException {
        List<String> outputScraper = Collections.synchronizedList(new ArrayList<String>());
        CountDownLatch countDownLatch = new CountDownLatch(5);

        List<Thread> workers = Stream
                .generate(() -> new Thread(new WorkerTest(outputScraper, countDownLatch)))
                .limit(5)
                .collect(toList());

        workers.forEach(Thread::start);
        countDownLatch.await();
        outputScraper.add("Latch released");

        System.out.println("At the end the list should contain 5 (maxSize's value) Counted down.");
        System.out.println(Color.CYAN + "List contains: " + Color.RESET);
        for (String s : outputScraper) {
            System.out.println(s);
        }
    }
}