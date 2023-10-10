import java.util.ArrayList;
import java.util.List;

class SparseMatrix {
    private final int numRows;
    private final int numCols;
    private final List<int[]> elements;

    public SparseMatrix(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.elements = new ArrayList<>();
    }

    public void addElement(int row, int col, int value) {
        if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
            throw new IllegalArgumentException("Invalid row or column index");
        }
        if (value != 0) {
            elements.add(new int[]{row, col, value});
        }
    }

    public void printMatrix() {
        int[][] result = new int[numRows][numCols];
        for (int[] element : elements) {
            int row = element[0];
            int col = element[1];
            int value = element[2];
            result[row][col] = value;
        }

        for (int[] row : result) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public static SparseMatrix addMatrices(SparseMatrix matrix1, SparseMatrix matrix2) {
        if (matrix1.numRows != matrix2.numRows || matrix1.numCols != matrix2.numCols) {
            throw new IllegalArgumentException("Matrix dimensions do not match");
        }

        SparseMatrix result = new SparseMatrix(matrix1.numRows, matrix1.numCols);

        for (int[] element : matrix1.elements) {
            result.addElement(element[0], element[1], element[2]);
        }

        for (int[] element : matrix2.elements) {
            int row = element[0];
            int col = element[1];
            int value = element[2];
            int existingValue = result.getElementValue(row, col);
            result.addElement(row, col, existingValue + value);
        }

        return result;
    }

    private int getElementValue(int row, int col) {
        for (int[] element : elements) {
            if (element[0] == row && element[1] == col) {
                return element[2];
            }
        }
        return 0;
    }
}

public class SparseMatrixAddition {
    public static void main(String[] args) {
        SparseMatrix matrix1 = new SparseMatrix(3, 3);
        matrix1.addElement(0, 0, 1);
        matrix1.addElement(0, 2, 2);
        matrix1.addElement(1, 1, 3);

        SparseMatrix matrix2 = new SparseMatrix(3, 3);
        matrix2.addElement(0, 0, 4);
        matrix2.addElement(1, 1, 5);
        matrix2.addElement(2, 2, 6);

        SparseMatrix result = SparseMatrix.addMatrices(matrix1, matrix2);

        System.out.println("Matrix 1:");
        matrix1.printMatrix();

        System.out.println("Matrix 2:");
        matrix2.printMatrix();

        System.out.println("Result:");
        result.printMatrix();
    }
}
