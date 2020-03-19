import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MakingCandies {

    static long minimumPasses(long m, long w, long p, long n) {
        long passNo = 0;
        long min = Long.MAX_VALUE;
        long totalCandies = 0;

        while (passNo + 1 < min) {

            BigInteger test = BigInteger.valueOf(m).multiply(BigInteger.valueOf(w));
            if (test.compareTo(BigInteger.valueOf(n)) >= 0) return passNo + 1;

            long candies = m * w;

            long toComplete = n - totalCandies;
            //check rounds count if we don't buy anymore
            long val = toComplete / candies;
            if (toComplete - val * candies > 0) val++;
            if (val + passNo < min) min = val + passNo;

            long availableCandies = totalCandies + candies;
            long buy = availableCandies / p;
            if (buy > 0) {
                long total = (m + w + buy);
                long mid = total / 2 + (total % 2 == 1 ? 1 : 0);
                if (m >= mid) {
                    w += buy;
                } else if (w >= mid) {
                    m += buy;
                } else {
                    w = mid;
                    m = total / 2;
                }
                totalCandies += (candies - buy * p);
                passNo++;
            } else {
                long toReach = p - totalCandies;
                long roundsToSkip = toReach / candies + (toReach % candies != 0 ? 1 : 0);
                passNo += (roundsToSkip - 1);
                totalCandies += (roundsToSkip - 1) * candies;
            }
        }

        return min;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] mwpn = scanner.nextLine().split(" ");

        long m = Long.parseLong(mwpn[0]);

        long w = Long.parseLong(mwpn[1]);

        long p = Long.parseLong(mwpn[2]);

        long n = Long.parseLong(mwpn[3]);

        long result = minimumPasses(m, w, p, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
