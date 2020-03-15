import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class RoadsAndLibraries {

    static int[] roots;

    static int root(int node) {
        int cur = node;
        while (cur != roots[cur]) {
            cur = roots[cur];
        }
        return cur;
    }

    // Complete the roadsAndLibraries function below.
    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
        if (c_lib <= c_road) return ((long)c_lib) * ((long)n);

        roots = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            roots[i] = i;
        }

        long roadsNo = 0;
        for (int[] edge : cities) {
            int a = edge[0];
            int b = edge[1];
            int rootA = root(a);
            int rootB = root(b);

            //union
            if (rootA != rootB) {
                roots[rootA] = rootB;
                roadsNo++;
            }
        }

        Set<Integer> libraries = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            libraries.add(root(i));
        }

        long librariesNo = libraries.size();

        return (roadsNo * (long) c_road) + (librariesNo * (long) c_lib);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nmC_libC_road = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmC_libC_road[0]);

            int m = Integer.parseInt(nmC_libC_road[1]);

            int c_lib = Integer.parseInt(nmC_libC_road[2]);

            int c_road = Integer.parseInt(nmC_libC_road[3]);

            int[][] cities = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] citiesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int citiesItem = Integer.parseInt(citiesRowItems[j]);
                    cities[i][j] = citiesItem;
                }
            }

            long result = roadsAndLibraries(n, c_lib, c_road, cities);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
