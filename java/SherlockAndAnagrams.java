import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class SherlockAndAnagrams {

    static boolean isAnagram(String s, int fs, int ss, int len) {
        char[] c1 = s.substring(fs, fs + len).toCharArray();
        char[] c2 = s.substring(ss - len + 1, ss + 1).toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        return Arrays.equals(c1, c2);
    }

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
        int result = 0;
        for (int len = 1; len <= s.length(); len++) {
            for (int i = 0; i <= s.length() - len; i++) {
                for (int j = s.length() - 1; j >= i + len; j--) {
                    if (isAnagram(s, i, j, len)) result++;
                }
            }
        }
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
