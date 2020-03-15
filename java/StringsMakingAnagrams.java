import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class StringsMakingAnagrams {

    // Complete the makeAnagram function below.
    static int makeAnagram(String a, String b) {
        char[] ca = a.toCharArray();
        char[] cb = b.toCharArray();
        Arrays.sort(ca);
        Arrays.sort(cb);

        int sa = 0, sb = 0;
        int result = 0;
        while (sa < a.length() && sb < b.length()) {
            if (ca[sa] == cb[sb]) {
                sa++;
                sb++;
            } else if (ca[sa] < cb[sb]) {
                result++;
                sa++;
            } else if (ca[sa] > cb[sb]) {
                result++;
                sb++;
            }
        }

        result += a.length() - sa + b.length() - sb;
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String a = scanner.nextLine();

        String b = scanner.nextLine();

        int res = makeAnagram(a, b);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
