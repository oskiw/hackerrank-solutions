import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MaxMin {

    static int min(int[] arr, int start, int len) {
        int min = Integer.MAX_VALUE;
        for (int i = start; i < start + len; i++) {
            if (arr[i] < min) min = arr[i];
        }
        return min;
    }

    static int max(int[] arr, int start, int len) {
        int max = 0;
        for (int i = start; i < start + len; i++) {
            if (arr[i] > max) max = arr[i];
        }
        return max;
    }

    // Complete the maxMin function below.
    static int maxMin(int k, int[] arr) {
        Arrays.sort(arr);
        int minTotal = Integer.MAX_VALUE;
        for (int i = 0; i <= arr.length - k; i++) {
            int cur = max(arr, i, k) - min(arr, i, k);
            if (cur < minTotal) minTotal = cur;
        }

        return minTotal;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int k = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int arrItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            arr[i] = arrItem;
        }

        int result = maxMin(k, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
