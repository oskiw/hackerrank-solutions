import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MinimumTimeRequired {

    static private boolean isEnoughGoalsPerDay(long day, long[] machines, long goal) {
        long counter = 0;
        for (long machine : machines) {
            counter += day / machine;
            if (counter >= goal) {
                return true;
            }
        }
        return false;
    }

    // Complete the minTime function below.
    static long minTime(long[] machines, long goal) {
        //binary search
        long start = 0;
        long end = 1_000_000_000L * 1_000_000_000L;

        while (start + 1 < end) {
            long middle = (end + start + 1) / 2;
            if (isEnoughGoalsPerDay(middle, machines, goal)) {
                end = middle;
            } else {
                start = middle;
            }
        }

        return isEnoughGoalsPerDay(start, machines, goal) ? start : end;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nGoal = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nGoal[0]);

        long goal = Long.parseLong(nGoal[1]);

        long[] machines = new long[n];

        String[] machinesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long machinesItem = Long.parseLong(machinesItems[i]);
            machines[i] = machinesItem;
        }

        long ans = minTime(machines, goal);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}