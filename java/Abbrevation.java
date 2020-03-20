import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Abbrevation {

    /**
     * Dynamic programming solution
     */

    static String abbreviation(String a, String b) {
        boolean[][] m = new boolean[b.length() + 1][a.length() + 1];

        m[0][0] = true;

        for (int i = 0; i <= b.length(); i++) {
            for (int j = 1; j <= a.length(); j++) {
                if (i == 0) {
                  if (!isUpperCase(a.charAt(j - 1))) {
                      m[i][j] = m[i][j - 1];
                  }
                } else if (a.charAt(j - 1) == b.charAt(i - 1)) {
                    m[i][j] = m[i - 1][j - 1];
                } else if (Character.toUpperCase(a.charAt(j - 1)) == b.charAt(i - 1)) {
                    m[i][j] = m[i - 1][j - 1] || m[i][j - 1];
                } else if (!isUpperCase(a.charAt(j - 1))) {
                    m[i][j] = m[i][j - 1];
                }
            }
        }

        return m[b.length()][a.length()] ? "YES" : "NO";
    }

    static boolean isUpperCase(char c) {
        return (c >= 'A' && c <= 'Z');
    }

    /**
     * Recursive solution with timeouts on 3 tests.
     */

//    static int[] countReps = new int[100];
//    static boolean check(String a, String b, int posA, int posB) {
//        if (posB >= b.length()) {
//            for (int i = posA; i < a.length(); i++) {
//                char ac = a.charAt(i);
//                if (isUpperCase(ac)) return false;
//            }
//            return true;
//        }
//
//        if (a.length() - posA < b.length() - posB) return false;
//
//        boolean result = false;
//        char cb = b.charAt(posB);
//        if (Character.toUpperCase(a.charAt(posA)) == cb) {
//            if (countReps[cb] > 0) {
//                countReps[cb]--;
//                result = check(a, b, posA + 1, posB + 1);
//                countReps[cb]++;
//            }
//        }
//        if (a.charAt(posA) != b.charAt(posB)) {
//            if (isUpperCase(a.charAt(posA))) return false;
//            result |= check(a, b, posA + 1, posB);
//        }
//        return result;
//    }
//
//    static String abbreviation(String a, String b) {
//        for (char c : b.toCharArray()) {
//            countReps[c]++;
//        }
//        return check(a, b, 0, 0) ? "YES" : "NO";
//    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String a = scanner.nextLine();

            String b = scanner.nextLine();

            String result = abbreviation(a, b);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}