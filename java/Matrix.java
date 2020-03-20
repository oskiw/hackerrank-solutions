import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Matrix {

    static int getLeader(int[] leaders, int k) {
        while (leaders[k] != k) {
            k = leaders[k];
        }
        return k;
    }

    static int minTime(int n, int[][] roads, int[] machines) {
        boolean[] groupHasMachine = new boolean[n];
        for (int machine : machines) {
            groupHasMachine[machine] = true;
        }

        int[] leaders = new int[n];
        for (int i = 0; i < n; i++) {
            leaders[i] = i;
        }

        Arrays.sort(roads, (r1, r2) -> Integer.compare(r2[2], r1[2]));

        int result = 0;
        for (int[] road : roads) {
            int c1 = road[0];
            int c2 = road[1];
            int value = road[2];

            int leader1 = getLeader(leaders, c1);
            int leader2 = getLeader(leaders, c2);
            if (groupHasMachine[leader1] && groupHasMachine[leader2]) {
                result += value;
            } else if (groupHasMachine[leader1]) {
                leaders[leader2] = leader1;
            } else {
                leaders[leader1] = leader2;
            }
        }

        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[][] roads = new int[n - 1][3];

        for (int i = 0; i < n - 1; i++) {
            String[] roadsRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int roadsItem = Integer.parseInt(roadsRowItems[j]);
                roads[i][j] = roadsItem;
            }
        }

        int[] machines = new int[k];

        for (int i = 0; i < k; i++) {
            int machinesItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            machines[i] = machinesItem;
        }

        int result = minTime(n, roads, machines);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
