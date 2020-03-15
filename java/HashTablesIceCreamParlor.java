import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class HashTablesIceCreamParlor {

    static void whatFlavors(int[] cost, int money) {
        Set<Integer> costLeftover = new HashSet<>();

        for (int i = 0; i < cost.length; i++) {
            if (costLeftover.contains(money - cost[i])) {
                //found a match
                for (int j = 0; j < cost.length; j++) {
                    if (cost[j] == money - cost[i]) {
                        System.out.print(j + 1 + " ");
                        break;
                    }
                }
                for (int j = 0; j < cost.length; j++) {
                    if (cost[j] == cost[i]) {
                        System.out.println(i + 1);
                        break;
                    }
                }
                return;
            } else {
                costLeftover.add(cost[i]);
            }
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int money = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] cost = new int[n];

            String[] costItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int costItem = Integer.parseInt(costItems[i]);
                cost[i] = costItem;
            }

            whatFlavors(cost, money);
        }

        scanner.close();
    }
}
