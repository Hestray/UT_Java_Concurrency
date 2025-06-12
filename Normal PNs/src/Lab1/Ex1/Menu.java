package Lab1.Ex1;

import java.util.Scanner;

public class Menu {
    // members
    Calculator calc = new Calculator();
    int choice      = 0;
    String loop     = "y";
    float result;

    // methods
    public void menu() {
        // greeting
        greeting();

        // loop for exercise
        while (loop.equals("y") && choice != 5) {
            // numbers
            System.out.println("Please enter your first number: ");
            Scanner in = new Scanner(System.in);
            float num1 = in.nextFloat();
            System.out.println("Please enter your second number: ");
            float num2 = in.nextFloat();

            // selection
            System.out.println("What operation do you want to perform?");
            System.out.println("\t1. Addition\n" +
                    "\t2. Subtraction\n" +
                    "\t3. Multiplication\n" +
                    "\t4. Division");
            System.out.println("To exit, enter 5. To select an operation, enter a number 1-4.");
            System.out.println("Enter your choice: ");
            choice = in.nextInt();

            do {
                choiceSelection(num1, num2, choice);
            } while (choice < 1 || choice > 5);

            if (choice != 5) {
                // print result
                System.out.println("Your result is: ");
                System.out.printf("%.2f\n", result);

                // check for loop
                System.out.println("Do you want to continue? (y/n)");
                System.out.println("Enter your choice: ");
                in.nextLine(); // to consume the 'skip' due to enter
                loop = in.nextLine();
            }

        }
        System.out.println("Thank you for using our program!");
    }

    void choiceSelection(float num1, float num2, int choice) {
        switch(choice) {
            case 1:
                this.result = calc.addition(num1, num2);
                break;
            case 2:
                this.result = calc.subtraction(num1, num2);
                break;
            case 3:
                this.result = calc.multiplication(num1, num2);
                break;
            case 4:
                this.result = calc.division(num1, num2);
                break;
            case 5:
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    public void greeting() {
        System.out.println("Hello and welcome to my program!");
    }
}
