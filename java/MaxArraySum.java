import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MaxArraySum {

    static int maxSubsetSum(int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0 ; i < result.length; i++) {
            result[i] = Integer.MIN_VALUE;
        }

        for (int i = 0; i < arr.length - 2; i++) {
            int val = Math.max(arr[i], result[i]);

            if (i + 2 < arr.length) {
                int candidate = val + (arr[i + 2] > 0 ? arr[i + 2] : 0);
                if (result[i + 2] < candidate) result[i + 2] = candidate;
            }

            if (i + 3 < arr.length) {
                int candidate = val + (arr[i + 3] > 0 ? arr[i + 3] : 0);
                if (result[i + 3] < candidate) result[i + 3] = candidate;
            }
        }

        int max = Integer.MIN_VALUE;
        for (int val : result) {
            if (max < val) max = val;
        }

        return max;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = maxSubsetSum(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
