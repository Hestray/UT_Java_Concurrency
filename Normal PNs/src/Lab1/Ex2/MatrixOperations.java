package Lab1.Ex2;

public class MatrixOperations {
    // operations
    static int[][] addition(Matrix m1, Matrix m2) {
        if (m1 == null || m2 == null) return null;
        if (m1.getRowLen() != m2.getRowLen() || m1.getColLen() != m2.getColLen()) return null;

        int[][] result = new int[m1.getRowLen()][m1.getColLen()];
        for (int i = 0; i < m1.getRowLen(); i++) {
            for (int j = 0; j < m1.getColLen(); j++) {
                result[i][j] = m1.matrix[i][j] + m2.matrix[i][j];
            }
        }
        return result;
    }

    static int[][] subtraction(Matrix m1, Matrix m2) {
        if (m1 == null || m2 == null) return null;
        if (m1.getRowLen() != m2.getRowLen() || m1.getColLen() != m2.getColLen()) return null;

        int[][] result = new int[m1.getRowLen()][m1.getColLen()];
        for (int i = 0; i < m1.getRowLen(); i++) {
            for (int j = 0; j < m1.getColLen(); j++) {
                result[i][j] = m1.matrix[i][j] - m2.matrix[i][j];
            }
        }

        return result;
    }

    static int[][] multiplication(Matrix m1, Matrix m2) {
        if (m1 == null || m2 == null) return null;
        if (m1.getColLen() != m2.getRowLen()) return null;

        int[][] result = new int[m1.getRowLen()][m1.getColLen()];
        int i, j;
        for (i = 0; i < m1.getRowLen(); i++) {
            for (j = 0; j < m2.getColLen(); j++) {
                for (int k = 0; k < m1.getColLen(); k++) {
                    result[i][j] = result[i][j] + m1.matrix[i][k] * m2.matrix[k][j];
                }
            }
        }

        return result;
    }
}
