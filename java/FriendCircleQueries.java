import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class FriendCircleQueries {

    static Map<Integer, Integer> maxMap = new HashMap<>();
    static int maxSoFar = 0;

    static int find(Map<Integer, Integer> parents, int x) {
        int cur = x;
        while (parents.containsKey(cur) && parents.get(cur) != cur) {
            cur = parents.get(cur);
        }

        return cur;
    }

    static void union(Map<Integer, Integer> parents, int pa, int pb) {
        if (pa == pb) return;

        if (!parents.containsKey(pa)) {
            maxMap.put(pa, 1);
            parents.put(pa, pa);
        }
        if (!parents.containsKey(pb)) {
            maxMap.put(pb, 1);
            parents.put(pb, pb);
        }

        if (maxMap.get(pa) < maxMap.get(pb)) {
            parents.put(pa, pb);
            maxMap.put(pb, maxMap.get(pa) + maxMap.get(pb));
            if (maxSoFar < maxMap.get(pb)) maxSoFar = maxMap.get(pb);
        } else {
            parents.put(pb, pa);
            maxMap.put(pa, maxMap.get(pa) + maxMap.get(pb));
            if (maxSoFar < maxMap.get(pa)) maxSoFar = maxMap.get(pa);

        }
    }

    // Complete the maxCircle function below.
    static int[] maxCircle(int[][] queries) {
        Map<Integer, Integer> parents = new HashMap<>(queries.length * 2);

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int a = queries[i][0];
            int b = queries[i][1];

            int pa = find(parents, a);
            int pb = find(parents, b);

            union(parents, pa, pb);
            result[i] = maxSoFar;
        }
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] queries = new int[q][2];

        for (int i = 0; i < q; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        int[] ans = maxCircle(queries);

        for (int i = 0; i < ans.length; i++) {
            bufferedWriter.write(String.valueOf(ans[i]));

            if (i != ans.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
