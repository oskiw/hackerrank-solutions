import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class ConnectedCellsInAGrid {

    public static class Coordinate {
        int x, y;

        public Coordinate(int i, int j) {
            x = i;
            y = j;
        }

        public Coordinate check(boolean[][] checked) {
            checked[x][y] = true;
            return this;
        }
    }


    static int dfs(int[][] matrix, boolean[][] checked, int i, int j) {
        int area = 0;
        checked[i][j] = true;

        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(new Coordinate(i, j));
        checked[i][j] = true;

        while (!queue.isEmpty()) {
            Coordinate curr = queue.remove();
            area++;

            if (curr.x - 1 >= 0 && !checked[curr.x - 1][curr.y] && matrix[curr.x - 1][curr.y] == 1)queue.add(new Coordinate(curr.x - 1, curr.y).check(checked));
            if (curr.x + 1 < matrix.length && !checked[curr.x + 1][curr.y] && matrix[curr.x + 1][curr.y] == 1) queue.add(new Coordinate(curr.x + 1, curr.y).check(checked));

            if (curr.y - 1 >= 0 && !checked[curr.x][curr.y - 1] && matrix[curr.x][curr.y - 1] == 1) queue.add(new Coordinate(curr.x, curr.y - 1).check(checked));
            if (curr.y + 1 < matrix[0].length && !checked[curr.x][curr.y + 1] && matrix[curr.x][curr.y + 1] == 1) queue.add(new Coordinate(curr.x, curr.y + 1).check(checked));

            if (curr.x - 1 >= 0 && curr.y - 1 >= 0 && !checked[curr.x - 1][curr.y - 1] && matrix[curr.x - 1][curr.y - 1] == 1) queue.add(new Coordinate(curr.x - 1, curr.y - 1).check(checked));
            if (curr.x - 1 >= 0 && curr.y + 1 < matrix[0].length && !checked[curr.x - 1][curr.y + 1] && matrix[curr.x - 1][curr.y + 1] == 1) queue.add(new Coordinate(curr.x - 1, curr.y + 1).check(checked));

            if (curr.x + 1 < matrix.length && curr.y - 1 >= 0 && !checked[curr.x + 1][curr.y - 1] && matrix[curr.x + 1][curr.y - 1] == 1) queue.add(new Coordinate(curr.x + 1, curr.y - 1).check(checked));
            if (curr.x + 1 < matrix.length && curr.y + 1 < matrix[0].length && !checked[curr.x + 1][curr.y + 1] && matrix[curr.x + 1][curr.y + 1] == 1) queue.add(new Coordinate(curr.x + 1, curr.y + 1).check(checked));
        }

        return area;
    }

    // Complete the connectedCell function below.
    static int connectedCell(int[][] matrix) {
        boolean[][] checked = new boolean[matrix.length][matrix[0].length];
        int max = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1 && !checked[i][j]) {
                    int currentResult = dfs(matrix, checked, i, j);
                    if (currentResult > max) max = currentResult;
                }
            }
        }

        return max;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] matrixRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < m; j++) {
                int matrixItem = Integer.parseInt(matrixRowItems[j]);
                matrix[i][j] = matrixItem;
            }
        }

        int result = connectedCell(matrix);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
