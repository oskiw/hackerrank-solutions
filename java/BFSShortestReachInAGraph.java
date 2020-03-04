import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BFSShortestReachInAGraph {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {

            int n = scanner.nextInt();
            int m = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            ArrayList<List<Integer>> neighbours = new ArrayList<>(n);
            int[] distance = new int[n + 1];

            for (int j = 1; j <= n; j++) {
                neighbours.add(new ArrayList<>());
                distance[j] = -1;
            }

            for (int j = 1; j <= m; j++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                List<Integer> neighboursA = neighbours.get(a - 1);
                neighboursA.add(b);
                List<Integer> neighboursB = neighbours.get(b - 1);
                neighboursB.add(a);
            }

            int start = scanner.nextInt();
            distance[start] = 0;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(start);

            while (!queue.isEmpty()) {
                Integer cur = queue.remove();
                List<Integer> neighboursCur = neighbours.get(cur - 1);
                for (Integer neig : neighboursCur) {
                    if (distance[neig] == -1) {
                        distance[neig] = distance[cur] + 6;
                        queue.add(neig);
                    }
                }
            }

            for (int k = 1; k <= n; k++) {
                if (k != start) {
                    System.out.print(distance[k] + " ");
                }
            }
            System.out.println();
        }
        scanner.close();
    }
}