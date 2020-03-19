import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MergeSortCountingInversions {

    static void swap(int arr[], int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    static long merge(int arr[], int i, int mid, int j) {

        int[] leftArr = new int[mid - i + 1];
        int[] rightArr = new int[j - mid];

        System.arraycopy(arr, i, leftArr, 0, mid - i + 1);
        System.arraycopy(arr, mid + 1, rightArr, 0, j - mid);

        int a = 0;
        int b = 0;
        int swaps = 0;
        long curInvs = 0;
        while (a < mid - i + 1 || b < j - mid) {
            if (b == j - mid) {
                arr[i + a + b] = leftArr[a];
                a++;
            } else if (a == mid - i + 1) {
                arr[i + a + b] = rightArr[b];
                b++;
            } else if (leftArr[a] > rightArr[b]) {
                curInvs += (mid + 1 + b) - (i + a) - swaps;
                arr[i + a + b] = rightArr[b];
                b++;
                swaps++;
            } else {
                arr[i + a + b] = leftArr[a];
                a++;
            }
        }

        return curInvs;
    }

    static long sort(int arr[], int i, int j) {
        long inversions = 0;
        if (i >= arr.length) return 0;
        if (i == j) return inversions;
        if (i + 1 == j) {
            if (arr[i] > arr[j]) {
                swap(arr, i, j);
                return ++inversions;
            }
            return inversions;
        }

        int mid = (i + j) / 2;
        inversions += sort(arr, i, mid);
        inversions += sort(arr, mid + 1, j);

        inversions += merge(arr, i, mid, j);

        return  inversions;
    }

    // Complete the countInversions function below.
    static long countInversions(int[] arr) {
        int[] orig = new int[arr.length];
        System.arraycopy(arr, 0, orig, 0, arr.length);
        long inv =  sort(arr, 0, arr.length - 1);
        return inv;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            long result = countInversions(arr);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}