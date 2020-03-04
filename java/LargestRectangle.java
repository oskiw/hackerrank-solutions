import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class LargestRectangle {

    public static class Node {
        int size, counter;

        public Node(int s, int c) {
            size = s;
            counter = c;
        }

    }

    // Complete the largestRectangle function below.
    static long largestRectangle(int[] h) {
        Stack<Node> stack = new Stack<>();
        int max = 0;

        for (int i = 0; i < h.length; i++) {
            if (stack.isEmpty() || h[i] > stack.peek().size) {
                stack.push(new Node(h[i], 1));
                if (max < h[i]) max = h[i];
            } else if (h[i] == stack.peek().size) {
                Node topNode = stack.peek();
                topNode.counter += 1;
                if (max < topNode.size * topNode.counter) max = topNode.size * topNode.counter;
            } else {
                int currentRemoved = 0;
                while (!stack.isEmpty() && stack.peek().size > h[i]) {
                    Node node = stack.pop();
                    if (max < (currentRemoved + node.counter) * node.size) max = (currentRemoved + node.counter) * node.size;
                    currentRemoved += node.counter;
                }
                if (stack.isEmpty() || h[i] > stack.peek().size) {
                    stack.push(new Node(h[i], currentRemoved + 1));
                    if (max < h[i] * (currentRemoved + 1)) max = h[i] * (currentRemoved + 1);
                } else if (h[i] == stack.peek().size) {
                    Node topNode = stack.peek();
                    topNode.counter += currentRemoved + 1;
                    if (max < topNode.size * topNode.counter) max = topNode.size * topNode.counter;
                }
            }
        }

        int currentRemoved = 0;
        while (!stack.empty()) {
            Node node = stack.pop();
            if (max < (currentRemoved + node.counter) * node.size) max = (currentRemoved + node.counter) * node.size;
            currentRemoved += node.counter;
        }

        return max;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] h = new int[n];

        String[] hItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int hItem = Integer.parseInt(hItems[i]);
            h[i] = hItem;
        }

        long result = largestRectangle(h);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
