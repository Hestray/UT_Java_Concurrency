package Practicing_for_exam.Lab2;

class JoinTestThread extends Thread {
    Thread t;
    private static int sum = 0;
    private int value;

    JoinTestThread(String name, Thread t, int value) {
        this.setName(name);
        this.t = t;
        this.value = value;
    }

    public void run() {
        System.out.println("Thread " + this.getName() + " has entered the method");

        try {
            if (t != null) {
                t.join();
            }

            System.out.println("Thread " + this.getName() + " executes");

            if (this.value != 0) {
                if (this.value % 2 == 0)
                    sum += 1 + this.value/2 + this.value;
                else sum += 1 + this.value;
            }

            for (int i = 2; i < this.value / 2; i++) {
                if (this.value % i == 0)
                    sum += i;
            }

            Thread.sleep(3000);
            System.out.println("Sum of " + this.value + " is " + sum);
            System.out.println("Thread " + this.getName() + " finished");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
