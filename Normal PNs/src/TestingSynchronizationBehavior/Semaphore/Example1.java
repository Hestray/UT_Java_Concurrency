package TestingSynchronizationBehavior.Semaphore;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

// example taken from www.baeldung.com/java-semaphore
class LoginQueueUsingSemaphore {
    private Semaphore semaphore;

    public LoginQueueUsingSemaphore(int slotLimit) {
        semaphore = new Semaphore(slotLimit);
    }

    boolean tryLogin() {
        return semaphore.tryAcquire();
    }

    void logout() {
        semaphore.release();
    }

    int availableSlots() {
        return semaphore.availablePermits();
    }
}

class MainSemaphore {
    public static void main(String[] args) {
        int slots = 10;
        ExecutorService executor = Executors.newFixedThreadPool(slots);
        LoginQueueUsingSemaphore queue = new LoginQueueUsingSemaphore(slots);

        IntStream.range(0, slots)
                .forEach(user -> executor.execute(queue::tryLogin));
        executor.shutdown();

        System.out.println("Available slots: " + queue.availableSlots());
        System.out.println("Trying to login yields: " + queue.tryLogin());
    }
}
