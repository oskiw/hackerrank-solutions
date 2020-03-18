import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class FraudulentActivityNotifications {

    static int activityNotifications(int[] expenditure, int d) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int[] initialExpenditures = new int[d];
        System.arraycopy(expenditure, 0, initialExpenditures, 0, d);
        Arrays.sort(initialExpenditures);
        for (int i = 0; i < d / 2; i++) {
            maxHeap.add(initialExpenditures[i]);
        }
        for (int i = d / 2; i < d; i++) {
            minHeap.add(initialExpenditures[i]);
        }

        int result = 0;
        for (int i = d; i < expenditure.length; i++) {
            if (expenditure[i] - 2d * getMedian(minHeap, maxHeap) > -0.0000000000001) {
                result++;
            }
            remove(minHeap, maxHeap, expenditure[i - d]);
            insert(minHeap, maxHeap, expenditure[i]);
        }

        return result;
    }

    private static void insert(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap, int value) {
        if (value <= maxHeap.peek()) {
            maxHeap.add(value);
        } else {
            minHeap.add(value);
        }

        rebalance(minHeap, maxHeap);
    }

    private static void rebalance(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
        if (maxHeap.size() - minHeap.size() > 1) {
            int topMax = maxHeap.remove();
            minHeap.add(topMax);
        } else if (minHeap.size() - maxHeap.size() > 1) {
            int topMin = minHeap.remove();
            maxHeap.add(topMin);
        }
    }

    private static void remove(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap, int value) {
        if (value <= maxHeap.peek()) {
            maxHeap.remove(value);
        } else {
            minHeap.remove(value);
        }

        rebalance(minHeap, maxHeap);
    }

    private static double getMedian(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else if (maxHeap.size() < minHeap.size()) {
            return minHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2d;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
