import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SpecialStringAgain {

    public static class Node {
        char c = '-';
        int k = -1;

        public Node(char c_, int k_) {
            c = c_;
            k = k_;
        }

        public Node() {}

        public void clear() {
            c = '-';
            k = -1;
        }

        public void set(Node node) {
            c = node.c;
            k = node.k;
        }
    }

    static boolean isEmpty(Node node) {
        return node == null || node.k == -1;
    }

    // Complete the substrCount function below.
    static long substrCount(int n, String s) {

        if (n == 0) return 0;
        if (n == 1) return 1;

        Node p1 = null, p2 = new Node(), p3 = new Node();

        p1 = new Node(s.charAt(0), 1);

        long result = 1;
        for (int i = 1; i < n; i++) {
            if (p1.c == s.charAt(i)) {
                p1.k += 1;
                result += reduce(p1, p2, p3);
            } else {
                shift(p1, p2, p3);
                p1 = new Node(s.charAt(i), 1);
                result += reduce(p1, p2, p3);
            }
        }

        return result;
    }

    static long reduce(Node p1, Node p2, Node p3) {
        if (isEmpty(p2) && isEmpty(p3)) {
            return p1.k;
        } else if (isEmpty(p3)) {
            if (p1.k > 1) {
                p2.clear();
            }
            return p1.k;
        } else if (p1.c != p3.c || p2.k > 1) {
            p3.clear();
            return p1.k;
        } else {
            if (p1.k > p3.k) {
                p3.clear();
                p2.clear();
                return p1.k;
            } else {
                return p1.k + 1;
            }
        }
    }

    static void shift(Node p1, Node p2, Node p3) {
        p3.set(p2);
        p2.set(p1);
        p1.clear();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        long result = substrCount(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
