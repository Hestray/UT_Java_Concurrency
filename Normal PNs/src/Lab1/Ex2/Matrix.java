package Lab1.Ex2;

public class Matrix {
    int[][] matrix;
    int rowLen = 3;
    int colLen = 3;

    // methods
    // default constructor
    Matrix() {
        this.matrix = new int[3][3];
    }

    // for empty matrix of chosen size
    Matrix(int rowLen, int colLen) {
        this.matrix = new int[rowLen][colLen];
        this.rowLen = rowLen;
        this.colLen = colLen;
    }

    // giving a matrix to this object
    Matrix(int[][] matrix, int rowLen, int colLen) {
        this.matrix = matrix;
        this.rowLen = rowLen;
        this.colLen = colLen;
    }

    public int getColLen() {
        return colLen;
    }

    public int getRowLen() {
        return rowLen;
    }

    public int[][] getMatrix() {
        return matrix;
    }
}
