import java.util.stream.IntStream;


/**
 * https://www.hackerrank.com/challenges/bfsshortreach
 *
 * Created by oskiw on 03/04/2018.
 */

public class BFSShortReach {

    private static final int EDGE_WEIGHT = 6;

    private static int[] bfs(int n, int m, int[][] edges, int s) {
        ArrayList<Node> nodes = new ArrayList<>(n + 1);
        IntStream.rangeClosed(0, n).forEach(i -> nodes.add(new Node(i)));

        for (int i = 0; i < m; i++) {
            Node nodeA = nodes.get(edges[i][0]);
            Node nodeB = nodes.get(edges[i][1]);
            nodeA.addNeighbour(nodeB);
            nodeB.addNeighbour(nodeA);
        }

        LinkedList<Node> queue = new LinkedList<>();
        BitSet reachedNodes = new BitSet();

        Node startingNode = nodes.get(s);
        startingNode.distance = 0;
        queue.add(startingNode);

        Node currentNode = queue.poll();
        while (currentNode != null) {
            int currentDistance = currentNode.distance;
            currentNode.neighbours.forEach(neighbour -> {
                if (!reachedNodes.get(neighbour.number)) {
                    neighbour.distance = currentDistance + 1;
                    reachedNodes.set(neighbour.number);
                    queue.add(neighbour);
                }
            });
            currentNode = queue.poll();
        }
        return nodes
                .stream()
                .filter(node -> node.number != s && node.number > 0)
                .map(node -> node.distance)
                .mapToInt(distance -> distance == Integer.MAX_VALUE ? -1 : distance * EDGE_WEIGHT)
                .toArray();
    }

    private static class Node {
        Integer number;
        Set<Node> neighbours = new HashSet<>();
        int distance = Integer.MAX_VALUE;

        Node(int number) {
            this.number = number;
        }

        void addNeighbour(Node neighbour) {
            neighbours.add(neighbour);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            int n = in.nextInt();
            int m = in.nextInt();
            int[][] edges = new int[m][2];
            for(int edges_i = 0; edges_i < m; edges_i++){
                for(int edges_j = 0; edges_j < 2; edges_j++){
                    edges[edges_i][edges_j] = in.nextInt();
                }
            }
            int s = in.nextInt();
            int[] result = bfs(n, m, edges, s);
            for (int i = 0; i < result.length; i++) {
                System.out.print(result[i] + (i != result.length - 1 ? " " : ""));
            }
            System.out.println("");


        }
        in.close();
    }
}

