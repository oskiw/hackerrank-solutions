import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class RecursionDavisStaircase {

    static long MOD = 10000000007L;

    // Complete the stepPerms function below.
    static int stepPerms(int n) {
        long[] result = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            result[i] = 0;
        }
        result[0] = 1;

        for (int i = 0; i < n; i++) {
            if (result[i] > 0) {
                if (i + 3 <= n) result[i + 3] = (result[i + 3] + result[i]) % MOD;
                if (i + 2 <= n) result[i + 2] = (result[i + 2] + result[i]) % MOD;
                if (i + 1 <= n) result[i + 1] = (result[i + 1] + result[i]) % MOD;
            }
        }

        return (int) (result[n] % MOD);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int s = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int sItr = 0; sItr < s; sItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int res = stepPerms(n);

            bufferedWriter.write(String.valueOf(res));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
