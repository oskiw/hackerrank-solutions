
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Candies {

    // Complete the candies function below.
    static long candies(int n, int[] arr) {
        int[] arrRight = new int[arr.length];
        int[] arrLeft = new int[arr.length];

        arrLeft[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] < arr[i]) {
                arrLeft[i] = arrLeft[i - 1] + 1;
            } else {
                arrLeft[i] = 1;
            }
        }

        arrRight[arr.length - 1] = 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                arrRight[i] = arrRight[i + 1] + 1;
            } else {
                arrRight[i] = 1;
            }
        }

        long result = 0;
        for (int i = 0; i < arr.length; i++) {
            result += Math.max(arrLeft[i], arrRight[i]);
        }

        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int arrItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            arr[i] = arrItem;
        }

        long result = candies(n, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
