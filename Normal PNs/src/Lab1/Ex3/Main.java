package Lab1.Ex3;

import java.util.Random;

public class Main {

    public static int min(int[] arr, int bound) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < bound) {
                bound = arr[i];
            }
        }
        return bound;
    }

    public static int max(int[] arr) {
        int value = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > value) {
                value = arr[i];
            }
        }
        return value;
    }

    public static void main(String[] args) {
        Random value = new Random();
        int bound    = 1000;
        int[] arr    = new int[10];
        System.out.println("The array is: ");
        for (int i = 0; i < 10; i++) {
            arr[i] = value.nextInt(bound);
            System.out.print(arr[i] + " ");
        }

        System.out.println("\nThe minimum value from the array is: " + min(arr, bound));
        System.out.println("The maximum value from the array is: " + max(arr));
    }
}
