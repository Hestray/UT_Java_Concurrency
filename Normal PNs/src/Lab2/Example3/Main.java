package Lab2.Example3;

import java.util.Scanner;

class JoinTestThread extends Thread {
    static long sum = 0;
    long number;

    Thread t;
    JoinTestThread(String n, Thread t, long number) {
        this.setName(n);
        this.t = t;
        this.number = number;
    }

    public void run() {
        System.out.println(this.getName() + " has entered the run() method.");
        try {
            if (t != null) t.join();
            System.out.println(this.getName() + " executing operations.");

            // determine all dividers/divisors
            for(int i = 2; i <= this.number / 2; i++) {
                if (this.number % i == 0)
                    // add to the sum
                    this.sum += i;
            }
            this.sum += 1 + this.number;

            // print the value
            System.out.println(this.getName() + " determined that for the number "
            + this.number + ", its divisors' sum + previous runs is " + this.sum);

            System.out.println(this.getName() + " has terminated operation.");
        } catch(Exception e) { e.printStackTrace(); }
    }
}

public class Main {
    static long first, second;

    public static void main(String[] args) {
        menu();

        JoinTestThread w1 = new JoinTestThread("Thread 1", null, first);
        JoinTestThread w2 = new JoinTestThread("Thread 2", w1, second);
        w1.start();
        w2.start();
    }


    private static void menu() {
        System.out.println("Give me two numbers. First one must be > 50000 and second one must be between 20000 and 50000.");
        Scanner reader = new Scanner(System.in);

        // for easy tests, run:
        // first = 50; // its divisors: 1 2 5 10 25 50 => 93
        // second = 14; // its divisors: 1 2 7 14      => 24    => 93 + 24 = 117

        System.out.println("Enter first number: ");
        do {
            first = reader.nextLong();
            if (first <= 50000) System.out.println("Please make sure it's greater than 50000");
        } while (first <= 50000);


        System.out.println("Enter second number: ");
        do {
            second = reader.nextLong();
            if (second <= 20000 || second > 50000) System.out.println("Please make sure it's greater than 20000, but less than 50000");
        } while (second <= 20000 || second > 50000);
    }

}
