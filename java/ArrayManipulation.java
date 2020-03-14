import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class ArrayManipulation {

    static class Query {
        int v, l;

        public Query(int v, int l) {
            this.v = v;
            this.l = l;
        }
    }

    // Complete the arrayManipulation function below.
    static long arrayManipulation(int n, int[][] queries) {

        ArrayList<Query> listA = new ArrayList<>(queries.length);
        ArrayList<Query> listB = new ArrayList<>(queries.length);

        for (int[] query : queries) {
            listA.add(new Query(query[0], query[2]));
            listB.add(new Query(query[1], query[2]));
        }

        listA.sort((o1, o2) -> Integer.compare(o1.v, o2.v));
        listB.sort((o1, o2) -> Integer.compare(o1.v, o2.v));


        int ca = 0, cb = 0;
        long curVal = 0, max = 0;

        while (ca < queries.length) {
            if (listA.get(ca).v <= listB.get(cb).v) {
                curVal += listA.get(ca).l;
                if (max < curVal) max = curVal;
                ca++;
            } else {
                curVal -= listB.get(cb).l;
                cb++;
            }
        }

        return max;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] queries = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        long result = arrayManipulation(n, queries);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
