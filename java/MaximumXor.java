import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MaximumXor {

    static short MAX_BIT = 30;

    static class Node {
        boolean one;
        Node left, right;

        public Node(boolean o) {
            one = o;
        }
    }

    static boolean isBitSet(int number, short bitNo) {
        return (number & (1 << bitNo)) != 0;
    }

    static int setBit(int number, short bitNo) {
        return number | (1 << bitNo);
    }

    static Node buildBitsTree(int[] arr) {
        Node root = new Node(false);

        for (int a : arr) {
            Node cur = root;
            for (short bit = MAX_BIT; bit >= 0; bit--) {
                if (isBitSet(a, bit)) {
                    if (cur.right != null) {
                        cur = cur.right;
                    } else {
                        Node newNode = new Node(true);
                        cur.right = newNode;
                        cur = newNode;
                    }
                } else {
                    if (cur.left != null) {
                        cur = cur.left;
                    } else {
                        Node newNode = new Node(false);
                        cur.left = newNode;
                        cur = newNode;
                    }
                }
            }
        }

        return root;
    }

    static int[] maxXor(int[] arr, int[] queries) {

        Node root = buildBitsTree(arr);

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int sum = 0;
            Node cur = root;
            for (short bit = MAX_BIT; bit >= 0; bit--) {
                if (isBitSet(queries[i], bit)) {
                    if (cur.left != null) {
                        cur = cur.left;
                        sum = setBit(sum, bit);
                    } else {
                        cur = cur.right;
                    }
                } else {
                    if (cur.right != null) {
                        cur = cur.right;
                        sum = setBit(sum, bit);
                    } else {
                        cur = cur.left;
                    }
                }
            }
            result[i] = sum;
        }

        return result;
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

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] queries = new int[m];

        for (int i = 0; i < m; i++) {
            int queriesItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            queries[i] = queriesItem;
        }

        int[] result = maxXor(arr, queries);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
