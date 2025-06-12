package Lab1.Ex2;

import java.util.Scanner;

public class Menu {
    int choice = 0;
    int[][] result;
    String loop = "y";

    public void menu(Matrix m1, Matrix m2) {
        System.out.println("Hello and welcome to a matrix calculator!");
        System.out.println("You can multiple, add or subtract the following two matrices:");
        System.out.println("Matrix 1:");
        printMatrix(m1);
        System.out.println("Matrix 2:");
        printMatrix(m2);

        while (loop.equals("y") && choice != 4) {
            System.out.println("Choose an option:");
            System.out.println("1. Add");
            System.out.println("2. Subtract");
            System.out.println("3. Multiply");
            System.out.println("4. Exit");

            System.out.println("Enter your choice:");
            Scanner in = new Scanner(System.in);
            this.choice = in.nextInt();

            do {
                choice(m1, m2);
            } while(this.choice < 1 || this.choice > 4);

            if (this.choice != 4) {
                printMatrix(new Matrix(this.result, m1.getRowLen(), m1.getColLen()));

                // check for loop
                System.out.println("Do you want to continue? (y/n)");
                System.out.println("Enter your choice: ");
                in.nextLine(); // to consume the 'skip' due to enter
                this.loop = in.nextLine();
            }
            System.out.println("Thank you for using this!");
        }
    }

    public void printMatrix(Matrix m) {
        if (m == null) System.out.println("Matrix is null");
        for (int i = 0; i < m.getRowLen(); i++) {
            for (int j = 0; j < m.getColLen(); j++) {
                System.out.print(m.getMatrix()[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void choice(Matrix m1, Matrix m2) {
        switch(this.choice) {
            case 1:
                this.result = MatrixOperations.addition(m1, m2);
                break;
            case 2:
                this.result = MatrixOperations.subtraction(m1, m2);
                break;
            case 3:
                this.result = MatrixOperations.multiplication(m1, m2);
                break;
            case 4:
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }
}
