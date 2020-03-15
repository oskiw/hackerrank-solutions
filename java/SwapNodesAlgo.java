import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class SwapNodesAlgo {

    static class Node {
        int data, level;
        Node left, right;

        Node(int data, int level) {
            this.data = data;
            this.level = level;
        }
    }

    static void traverse(Node curr, List<Integer> output) {
        if (curr == null) return;

        traverse(curr.left, output);
        output.add(curr.data);
        traverse(curr.right, output);
    }

    static void swapLevels(List<Node> nodes) {
        if (nodes != null) {
            for (Node node : nodes) {
                Node tmp = node.left;
                node.left = node.right;
                node.right = tmp;
            }
        }
    }

    static int[][] swapNodes(int[][] indexes, int[] queries) {
        Queue<Node> nodes = new LinkedList<>();
        Node root = new Node(1, 1);
        nodes.add(root);

        int maxL = 1;

        Map<Integer, List<Node>> levels = new HashMap<>();
        List<Node> list = new ArrayList<>();
        list.add(root);
        levels.put(1, list);

        for (int[] children : indexes) {
            Node parent = nodes.remove();

            if (children[0] == -1) {
                parent.left = null;
            } else {
                int l = parent.level + 1;
                if (maxL < l) maxL = l;
                Node left = new Node(children[0], l);
                parent.left = left;
                nodes.add(left);
                if (levels.containsKey(l)) {
                    List<Node> nodesPerLevel = levels.get(l);
                    nodesPerLevel.add(left);
                    levels.put(l, nodesPerLevel);
                } else {
                    List<Node> listL = new ArrayList<>();
                    listL.add(left);
                    levels.put(l, listL);
                }
            }

            if (children[1] == -1) {
                parent.right = null;
            } else {
                int l = parent.level + 1;
                if (maxL < l) maxL = l;
                Node right = new Node(children[1], l);
                parent.right = right;
                nodes.add(right);
                if (levels.containsKey(l)) {
                    List<Node> nodesPerLevel = levels.get(l);
                    nodesPerLevel.add(right);
                    levels.put(l, nodesPerLevel);
                } else {
                    List<Node> listR = new ArrayList<>();
                    listR.add(right);
                    levels.put(l, listR);

                }
            }
        }

        int[][] result = new int[queries.length][indexes.length];

        for (int q = 0; q < queries.length; q++) {
            int i = 1;
            while (i * queries[q] <= maxL) {
                swapLevels(levels.get(i * queries[q]));
                i++;
            }

            ArrayList<Integer> output = new ArrayList<>();
            traverse(root, output);

            int[] traversal = new int[output.size()];
            for (int j = 0; j < output.size(); j++) {
                traversal[j] = output.get(j);
            }
            result[q] = traversal;
        }

        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(scanner.nextLine().trim());

        int[][] indexes = new int[n][2];

        for (int indexesRowItr = 0; indexesRowItr < n; indexesRowItr++) {
            String[] indexesRowItems = scanner.nextLine().split(" ");

            for (int indexesColumnItr = 0; indexesColumnItr < 2; indexesColumnItr++) {
                int indexesItem = Integer.parseInt(indexesRowItems[indexesColumnItr].trim());
                indexes[indexesRowItr][indexesColumnItr] = indexesItem;
            }
        }

        int queriesCount = Integer.parseInt(scanner.nextLine().trim());

        int[] queries = new int[queriesCount];

        for (int queriesItr = 0; queriesItr < queriesCount; queriesItr++) {
            int queriesItem = Integer.parseInt(scanner.nextLine().trim());
            queries[queriesItr] = queriesItem;
        }

        int[][] result = swapNodes(indexes, queries);

        for (int resultRowItr = 0; resultRowItr < result.length; resultRowItr++) {
            for (int resultColumnItr = 0; resultColumnItr < result[resultRowItr].length; resultColumnItr++) {
                bufferedWriter.write(String.valueOf(result[resultRowItr][resultColumnItr]));

                if (resultColumnItr != result[resultRowItr].length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            if (resultRowItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
