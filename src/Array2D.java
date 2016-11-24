import java.util.Scanner;

/*
 * https://www.hackerrank.com/challenges/2d-array
 *
 * Created by oskiw on 23/06/2016.
 */
public class Array2D {

    private final static int MATRIX_SIZE = 6;

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int max = Integer.MIN_VALUE;

        int[][] matrix = new int[MATRIX_SIZE][MATRIX_SIZE];

        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                matrix[i][j] = reader.nextInt();
            }
        }

        for (int i = 1; i < MATRIX_SIZE - 1; i++) {
            for (int j = 1; j < MATRIX_SIZE - 1; j++) {
                int current = calculateSum(matrix, i, j);
                if (current > max) {
                    max = current;
                }
            }
        }
        reader.close();
        System.out.println(max);
    }

    private static int calculateSum(int[][] matrix, int x, int y) {
        return matrix[x][y]
                + matrix[x-1][y]
                + matrix[x-1][y+1]
                + matrix[x-1][y-1]
                + matrix[x+1][y]
                + matrix[x+1][y+1]
                + matrix[x+1][y-1];
    }
}