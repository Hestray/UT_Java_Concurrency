package Lab1.Ex2;

public class Main {
    public static void main(String[] args) {
        int[][] matrix1 = {
                {2, 3, 1},
                {7, 1, 6},
                {9, 2, 4}
        };
        int[][] matrix2 = {
                {8, 5, 3},
                {3, 9, 2},
                {2, 7, 3}
        };

        Matrix m1 = new Matrix(matrix1, 3, 3);
        Matrix m2 = new Matrix(matrix2, 3, 3);

        // menu
        Menu mainMenu = new Menu();
        mainMenu.menu(m1, m2);
    }
}
