import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SherlockAndTheValidString {

    static String isValid(String s) {
        Map<Character, Integer> reps = new HashMap<>();

        for (char c : s.toCharArray()) {
            int val = reps.containsKey(c) ? reps.get(c) : 0;
            reps.put(c, val + 1);
        }

        int min = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;
        for (int r : reps.values()) {
            if (min > r) {
                if (min < secondMin) secondMin = min;
                min = r;
            } else if (secondMin > r) {
                secondMin = r;
            }
        }

        if (min == 1) {
            boolean oneUsed = false;
            boolean breach = false;
            for (int r : reps.values()) {
                if (r > secondMin) {
                    breach = true;
                    break;
                }
                if (r == 1 && oneUsed) {
                    breach = true;
                    break;
                }
                if (r == 1) oneUsed = true;
            }
            if (!breach) return "YES";

        }

        boolean removalUsed = false;
        for (int r : reps.values()) {
            if (r - min == 1 && !removalUsed) {
                removalUsed = true;
            } else if (r - min >= 1) {
                return "NO";
            }
        }

        return "YES";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
