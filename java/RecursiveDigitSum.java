import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class RecursiveDigitSum {

    // Complete the superDigit function below.
    static int superDigit(String n, int k) {
        long result = 0;

        for (int i = 0; i < n.length(); i++) {
            long val = Character.getNumericValue(n.charAt(i));
            result += val;
        }
        result *= k;

        while (result > 9) {
            String in = String.valueOf(result);
            result = 0;
            for (int i = 0; i < in.length(); i++) {
                long val = Character.getNumericValue(in.charAt(i));
                result += val;
            }
        }
        return (int) result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        String n = nk[0];

        int k = Integer.parseInt(nk[1]);

        int result = superDigit(n, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
