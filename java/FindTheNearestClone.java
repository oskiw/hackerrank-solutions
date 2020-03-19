import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FindTheNearestClone {

    static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, int val) {
        Queue<Integer> nodesToCheck = new LinkedList<>();
        int[] addedById = new int[graphNodes];
        int[] minDist = new int[graphNodes];

        for (int i = 0; i < graphNodes; i++) {
            addedById[i] = -1;
            minDist[i] = Integer.MAX_VALUE;
        }

        List<List<Integer>> neighbours = new ArrayList<>(graphNodes);

        for (int i = 0; i < graphNodes; i++) {
            neighbours.add(new ArrayList<>());
        }

        for (int i = 0; i < graphFrom.length; i++) {
            neighbours.get(graphFrom[i] - 1).add(graphTo[i] - 1);
            neighbours.get(graphTo[i] - 1).add(graphFrom[i] - 1);
        }

        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == val) {
                nodesToCheck.add(i);
                addedById[i] = i;
                minDist[i] = 0;
            }
        }

        Integer curNode;
        while ((curNode = nodesToCheck.poll()) != null) {
            int dist = minDist[curNode] + 1;
            for (Integer n : neighbours.get(curNode)) {
                if (addedById[n] < 0) {
                    addedById[n] = addedById[curNode];
                    minDist[n] = dist;
                    nodesToCheck.add(n);
                } else if (addedById[n] != addedById[curNode]) {
                    return minDist[n] + dist;
                }
            }
        }

        return -1;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] graphNodesEdges = scanner.nextLine().split(" ");
        int graphNodes = Integer.parseInt(graphNodesEdges[0].trim());
        int graphEdges = Integer.parseInt(graphNodesEdges[1].trim());

        int[] graphFrom = new int[graphEdges];
        int[] graphTo = new int[graphEdges];

        for (int i = 0; i < graphEdges; i++) {
            String[] graphFromTo = scanner.nextLine().split(" ");
            graphFrom[i] = Integer.parseInt(graphFromTo[0].trim());
            graphTo[i] = Integer.parseInt(graphFromTo[1].trim());
        }

        long[] ids = new long[graphNodes];

        String[] idsItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < graphNodes; i++) {
            long idsItem = Long.parseLong(idsItems[i]);
            ids[i] = idsItem;
        }

        int val = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int ans = findShortest(graphNodes, graphFrom, graphTo, ids, val);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
