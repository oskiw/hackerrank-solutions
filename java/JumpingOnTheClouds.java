import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class JumpingOnTheClouds {

    static int jumpingOnClouds(int[] c) {
        int[] min = new int[c.length];
        for (int i = 0; i < min.length; i++) min[i] = Integer.MAX_VALUE;

        min[0] = 0;

        for (int i = 0; i < min.length; i++) {
            if ((c[i] == 0) && min[i] < Integer.MAX_VALUE) {
                if (i + 2 < min.length && c[i + 2] == 0) {
                    if (min[i + 2] > min[i] + 1) min[i + 2] = min[i] + 1;
                }
                if (i + 1 < min.length && c[i + 1] == 0) {
                    if (min[i + 1] > min[i] + 1) min[i + 1] = min[i] + 1;
                }
            }
        }

        return min[min.length - 1];
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] c = new int[n];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int result = jumpingOnClouds(c);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
